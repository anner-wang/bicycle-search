from mxnet import gluon,init,nd,image,contrib,autograd
from mxnet.gluon import nn
import time
import matplotlib.pyplot as plt
import sys
import cv2
name=sys.argv[1]
def cls_predictor(num_anchors,num_classes):
    return nn.Conv2D(num_anchors*(num_classes+1),kernel_size=3,padding=1)
def bbox_predictor(num_anchors):
    return nn.Conv2D(num_anchors*4,kernel_size=3,padding=1)
def flatten_pred(pred):
    return pred.transpose(axes=(0,2,3,1)).flatten()
def concat_preds(preds):
    return nd.concat(*[flatten_pred(pred) for pred in preds],dim=1)
def down_sample_blk(num_filters):
    blk=nn.HybridSequential()
    for _ in range(2):
        blk.add(nn.Conv2D(num_filters,kernel_size=3,padding=1),
                nn.BatchNorm(in_channels=num_filters),
                nn.Activation('relu'))
    blk.add(nn.MaxPool2D(2))
    blk.hybridize()
    return blk
def body_blk():
    blk=nn.HybridSequential()
    for num_filters in [16,32,64]:
        blk.add(down_sample_blk(num_filters))
    return blk
def get_blk(i):
    if i==0:
        blk=body_blk()
    elif i==4:
        blk=nn.GlobalMaxPool2D()
    else:
        blk=down_sample_blk(128)
    return blk
def single_scale_forward(x,blk,size,ratio,cls_predictor,bbox_predictor):
    y=blk(x)
    anchor=contrib.ndarray.MultiBoxPrior(y,sizes=size,ratios=ratio)
    cls_pred=cls_predictor(y)
    bbox_pred=bbox_predictor(y)
    return (y,anchor,cls_pred,bbox_pred)
num_anchors = 5
sizes = [[0.2, 0.272,0.3], [0.37, 0.447,0.495], [0.54, 0.619,0.619], [0.71, 0.79,0.846],
         [0.88, 0.961,0.962]]
ratios = [[0.5,1.25, 0.25]] * 5
class TinySSD(nn.Block):
    def __init__(self,num_classes,verbose=False,**kwargs):
        super(TinySSD, self).__init__(**kwargs)
        self.num_classes=num_classes
        for i in range(5):
            setattr(self,'blk_%d'%i,get_blk(i))
            setattr(self,'cls_%d'%i,cls_predictor(num_anchors,num_classes))
            setattr(self,'bbox_%d'%i,bbox_predictor(num_anchors))
    def forward(self, x):
        anchors,cls_preds,bbox_preds=[None]*5,[None]*5,[None]*5
        for i in range(5):
            x,anchors[i],cls_preds[i],bbox_preds[i]=single_scale_forward(x,getattr(self,'blk_%d'%i),
            sizes[i],ratios[i],getattr(self,'cls_%d'%i),getattr(self,'bbox_%d'%i))
        return (nd.concat(*anchors,dim=1),
               concat_preds(preds=cls_preds).reshape((0,-1,self.num_classes+1)),
                concat_preds(bbox_preds))
net=TinySSD(num_classes=2,verbose=True)
net.initialize(init=init.Xavier())
net.load_parameters('param')
def process_image(filename):
    img=image.imread(filename)
    data=image.imresize(img,1000,1000).astype('float32')
    new_img=data.transpose((2,0,1)).expand_dims(axis=0)
    return new_img,img
def predict(x):
    anchors,cls_preds,bbox_preds=net(x)
    cls_probs=cls_preds.softmax().transpose((0,2,1))
    out=contrib.ndarray.MultiBoxDetection(cls_probs,bbox_preds,anchors)
    idx=[i for i,row in enumerate(out[0]) if row[0].asscalar()!=-1]
    return out[0,idx]
def bbox2rect(bbox,color):
    return plt.Rectangle(
        xy=(bbox[0],bbox[1]),
        width=bbox[2]-bbox[0],
        height=bbox[3]-bbox[1],
        fill=False,
        edgecolor=color,
        linewidth=2)
def add_bbox(axes,bboxes,labels=None,colors=None):
    def _make_list(obj,default_value=None):
        if obj is None:
            obj=default_value
        elif not isinstance(obj,(list,tuple)):
            obj=[obj]
        return obj
    labels=_make_list(labels)
    colors=_make_list(colors,['b','g','r','m','k'])
    for i,bbox in enumerate(bboxes):
        color=colors[i%len(colors)]
        rect=bbox2rect(bbox.asnumpy(),color)
        axes.add_patch(rect)
        if labels and len(labels)>i:
            text_color='k' if color=='w' else 'w'
            axes.text(rect.xy[0],rect.xy[1],labels[i],va='center', ha='center', fontsize=9, color=text_color,
                      bbox=dict(facecolor=color, lw=0))
def display(img,out,thrashold=0.5):
    fig=plt.imshow(img.asnumpy())
    index=0
    for row in out:
        score=row[1].asscalar()
        if (score<thrashold):
            continue
        bbox=[row[2:6]*nd.array(img.shape[0:2]*2)]
        add_bbox(fig.axes,bbox,'%.2f'%score,'r')
	index+=1
    print(index)
    plt.savefig('/user/file/images/'+name+'p.png')
new_img,img=process_image('/user/file/images/'+name+'.jpg')
out=predict(new_img)
display(img,out,thrashold=0.85)

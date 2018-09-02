from mxnet import gluon,init,nd,image,contrib,autograd
from mxnet.gluon import nn
import time
import matplotlib.pyplot as plt
def load_data(batch_size,edge_size=256):
    train_iter=image.ImageDetIter(
        path_imgrec='data.rec',
        path_imgidx='data.idx',
        batch_size=batch_size,
        data_shape=(3,edge_size,edge_size),
        shuffle=True,
        rand_crop=1,
        min_object_covered=0.95,
        max_attempts=200)
    return train_iter
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
batch_size=32
train_data=load_data(batch_size=batch_size)
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
trainer=gluon.Trainer(net.collect_params(),'sgd',{'learning_rate':0.08})
cls_loss = gluon.loss.SoftmaxCrossEntropyLoss()
bbox_loss = gluon.loss.L1Loss()
def calc_loss(cls_preds, cls_labels, bbox_preds, bbox_labels, bbox_masks):
    cls = cls_loss(cls_preds, cls_labels)
    bbox = bbox_loss(bbox_preds * bbox_masks, bbox_labels * bbox_masks)
    return cls + bbox
def cls_metric(cls_preds, cls_labels):
    return (cls_preds.argmax(axis=-1) == cls_labels).mean().asscalar()

def bbox_metric(bbox_preds, bbox_labels, bbox_masks):
    return (bbox_labels - bbox_preds * bbox_masks).abs().mean().asscalar()
def train():
    for epoch in range(1, 100):
        acc, mae = 0, 0
        train_data.reset()
        tic = time.time()
        for i, batch in enumerate(train_data):
            x = batch.data[0]
            y = batch.label[0]
            with autograd.record():
                anchors, cls_preds, bbox_preds = net(x)
                bbox_labels, bbox_mask, cls_labels = contrib.nd.MultiBoxTarget(
                    anchors, y, cls_preds.transpose(axes=(0, 2, 1)))
                l = calc_loss(cls_preds, cls_labels, bbox_preds, bbox_labels, bbox_mask)
            l.backward()
            trainer.step(batch_size)
            acc += cls_metric(cls_preds, cls_labels)
            mae += bbox_metric(bbox_preds, bbox_labels, bbox_mask)
        net.save_parameters('params/'+str(epoch)+'.param')
train()













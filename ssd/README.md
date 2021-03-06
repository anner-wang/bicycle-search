
## SSD 检测图片中的自行车


```python
from gluoncv import model_zoo,data,utils
from matplotlib import pyplot as plt
import cv2
import time
import numpy as np
focalLength=100
CAR_WIDTH=80
PERSON_WIDTH=20
```

## 下载已经训练好的模型


```python
net=model_zoo.get_model('ssd_512_resnet50_v1_voc',pretrained=True)
```

## 导入测试的图片


```python
im_fname='/home/anner/test.jpg'
x,img=data.transforms.presets.ssd.load_test(im_fname,short=512)
```

## 输入网络


```python
class_IDS,scores,bounding_boxs=net(x)
```

## 处理预测结果


```python
for i in range(len(class_IDS[0])):
    left_top_x=bounding_boxs[0][i][0].asscalar()
    left_top_y=bounding_boxs[0][i][1].asscalar()
    right_bottom_x=bounding_boxs[0][i][2].asscalar()
    right_bottom_y=bounding_boxs[0][i][3].asscalar()
    height=right_bottom_y-left_top_y
    width=right_bottom_x-left_top_x
    if scores[0][i]>0.5:
        if class_IDS[0][i]==1:
            cv2.rectangle(img   ,(left_top_x,left_top_y),(right_bottom_x,right_bottom_y),(0,0,255),2)
```

## 展示预测结果


```python
cv2.imshow('image',img)
cv2.waitKey(0)|
```

![](result.png)




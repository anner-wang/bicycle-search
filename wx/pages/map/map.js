// pages/map/map.js
const app=getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    longitude: app.globaldata.longitude,
    latitude: app.globaldata.latitude,
  },

  onLoad: function (options) {

    this.getLocation()
    
  },

  onShow: function () {
    this.setData({
      longitude: app.globaldata.longitude,
      latitude: app.globaldata.latitude,
    })
  },

  getLocation:function(){
    var that = this;

    wx.getLocation({
      type: 'gcj02', //返回可以用于wx.openLocation的经纬度
      success: function (res) {
        console.log("res----------");
        console.log(res);

        that.setData({
          longitude:res.longitude,
          latitude:res.latitude
        });
        app.globaldata.longitude=res.longitude;
        app.globaldata.latitude=res.latitude;
      }
    });
  },
  
  

  camera:function(options){
    
    wx.navigateTo({
      url: '../camera/camera',
    })

  }

 



})
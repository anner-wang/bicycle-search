// pages/camera/camera.js
const app=getApp()

Page({

data: {
  src: app.globaldata.src,
},

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.ctx = wx.createCameraContext()

  },

  takephoto:function(e) {
    this.ctx.takePhoto({
      quality: 'high',
      success: (res) => {
        app.globaldata.src=res.tempImagePath
        
        wx.navigateTo({
          url: '../photo/photo',
        })
      }
    })

  },


})
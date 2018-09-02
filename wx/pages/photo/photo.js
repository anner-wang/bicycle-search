// pages/photo/photo.js
const app=getApp()
var lo;
var la;

Page({

  /**
   * 页面的初始数据
   */
  data: {
     myfunction:'上传',
     src:app.globaldata.src,
     longitude:app.globaldata.longitude,
     latitude:app.globaldata.latitude,
     lo: 0,
     la: 0,
     nickname:app.globaldata.nickname,
     ps:app.globaldata.ps
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    lo = app.globaldata.longitude.toFixed(1);
    la = app.globaldata.latitude.toFixed(1);

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
      this.setData({
        src: app.globaldata.src,
        longitude: app.globaldata.longitude,
        latitude: app.globaldata.latitude,
        lo: app.globaldata.longitude.toFixed(1),
        la:app.globaldata.latitude.toFixed(1),
        nickname:app.globaldata.nickname,
        ps:app.globaldata.ps
      })

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  },

  ps:function(e){
    app.globaldata.ps=e.detail.value;
  },


  upload:function(options){
    var description = app.globaldata.ps;
    console.log("我的输入：")
    console.log(description);
    var na = app.globaldata.nickname;
    console.log(na);

    wx.uploadFile({
      url: 'http://www.anner.wang:54343/Test/upload/image.action',
      filePath: app.globaldata.src,
      name: 'file',
      success: function (res) {
        console.log('文件上传成功');
        wx.request({
          url: 'http://www.anner.wang:54343/Test/upload/data.action',
          data: {
            longitude: app.globaldata.longitude,
            latitude: app.globaldata.latitude,
            name: app.globaldata.nickname,
            ps: app.globaldata.ps
          },
          success: function (res) {

            console.log("数据上传成功")


            wx.showModal({
              title: '提示!',
              content: '上传成功!',
              cancelText: '确定!',
              confirmText: '查看历史',
              success: function (res) {
                if (res.cancel) {
                  wx.redirectTo({
                    url: '../map/map',
                  })
                }

                if (res.confirm) {
                  wx.redirectTo({
                    url: '../history/history',
                  })
                }

              }

            })
          }

        })
      }
      
    })
    
        
  

  }
  


})
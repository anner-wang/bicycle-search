// pages/login/login.js
var app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
      nickname:app.globaldata.nickname,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
  
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

  getUserInfo: function (e) {
    var userInfo = e.detail.userInfo;
    console.log(userInfo);
    console.log(e.detail.userInfo.nickName)
    if (e.detail.userInfo.nickName != null) {
      app.globaldata.nickname = e.detail.userInfo.nickName;
      
      wx.navigateTo({
        url: '../map/map',
      })
    } else {
      console.log('your name is null!')
    }
  }

})
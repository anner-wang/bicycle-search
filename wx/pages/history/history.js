// pages/history/history.js
var app = getApp();
var idtime = new Array();
var iddescription = new Array();
var ididentified = new Array();
var information = new Array();

Page({

  /**
   * 页面的初始数据
   */
  data: {
      nickname:app.globaldata.nickname,
      idtime:[],
      iddescription:[],
      ididentified:[],
      information:[],
      
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;

    wx.request({
      url: 'http://www.anner.wang:54343/Test/upload/confirm.action',
      data:{
        name:app.globaldata.nickname
      },

      success: function (res) {
        console.log(res.data);
        console.log(res.data.length);
        var na = app.globaldata.nickname;
        console.log(na);
        var j = res.data.length - 1;
        console.log(j);
        console.log(res.data[j]);

      for (var i = 0; i < res.data.length; i++) {

        var time = "idtime[" + i + "]";
        var mytime = res.data[i].time;
        idtime[i] = res.data[i].time;
        console.log(res.data[i].time);

        var description = "iddescripion[" + i + "]";
        var mydescription = res.data[i].ps;
        iddescription[i] = res.data[i].ps;
        console.log(res.data[i].ps);

        var identified = "ididentified["+i+"]";
        var myidentified = res.data[i].number;
        ididentified[i] = res.data[i].number;
        console.log(res.data[i].number)

        var info = "information[" + i + "]";
        var myinfo = idtime[i] + '&nbsp;&nbsp;&nbsp;&nbsp;' + ididentified[i] + '&nbsp;&nbsp;&nbsp;&nbsp;' + iddescription[i];
        console.log(myinfo);

        that.setData({
          [time]: mytime,
          [description]: mydescription,
          [identified]:myidentified,
          [info]: myinfo
        })



        }
      }
    })
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
  
  }
})
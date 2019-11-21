// pages/qrdata/qrdata.js
Page({
  data: {
    userid: '',
    BorrowQR: '',
    logs: '',
    id: '',
    count: '',
    scanbol: false,
    combol: false,
    money: '',
    paybol: false,
    scanbol: false,
  },

  onLoad: function (options) {
    console.log("qr" + options.count)
    console.log("qr" + options.id)
    var that = this
    that.setData({
      id: options.id,
      count: options.count

    })


    try {
      var value = wx.getStorageSync('key')
      if (value) {
        that.setData({
          logs: value

        })

      }
    }
    catch (e) {

    }


    wx.request({
      url: 'http://www.titwdj.cn/BorrowBook/AddBorrowQR',
      data: {
        userid: that.data.logs.userid,
        id: that.data.id,
        count: that.data.count,
      },

      success: function (res) {
        console.log(res.data)
        that.setData({
          BorrowQR: res.data
        })
      },

    })
  },

  onShow: function () {
    var that = this;

    //一分钟刷新一次
    this.data.a = setInterval(function () {
      wx.request({
        url: 'http://www.titwdj.cn/BorrowBook/RefreshQRServlet',
        data: {
          userid: that.data.logs.userid,
          id: that.data.id,
          count: that.data.count,
        },

        success: function (res) {
          console.log(res.data)
          that.setData({
            BorrowQR: res.data
          })
        },

      })

    }, 10000)




    wx.request({
      url: 'http://www.titwdj.cn/BorrowBook/getUserInformationServlet',
      data: {
        userid: that.data.logs.userid
      },
      success: function (res) {
        console.log(res.data)
        that.setData({
          money: res.data.money
        })
        console.log("money" + that.data.money)
        console.log("count" + that.data.count)
        if (parseInt(that.data.money) >= parseInt(that.data.count)) {
          that.setData({
            combol: true
          })
        }
        else {
          that.setData({
            combol: false
          })
        }
      }
    })



    //监听管理员是否确认完成
    this.data.b = setInterval(function () {
      wx.request({
        url: 'http://www.titwdj.cn/BorrowBook/ScanBorrowOk',
        data: {
          id: that.data.id
        },
        success: function (res) {
          console.log(res.data)

          if (res.data.bol == 'true') {
            that.setData({
              scanbol: true
            })

            clearInterval(this.data.a);
            clearInterval(this.data.b);
          }
        }
      })
    }, 1000)

  },



  //支付
  pay: function (e) {

    var that = this;
    console.log(that.data.count)
    console.log(e)


    wx.showModal({
      title: '提示',
      content: '确认支付!',
      success: function (res) {


        if (res.confirm) {
          //进行扣款
          wx.request({
            url: 'http://www.titwdj.cn/BorrowBook/BorrowPayServlet',
            data:
            {
              userid: that.data.logs.userid,
              cash: that.data.count,

            },
            success: function (res) {
              console.log(res.data)
              wx.showToast({
                title: '支付成功!',
                icon: 'success',
                duration: 2000
              })
              that.setData({
                paybol: true
              })

            }

          })


        } else if (res.cancel) {
          console.log('用户点击取消')
        }
      }
    })

  },





  onUnload: function () {

    var that = this
    if (that.data.scanbol) {
      console.log("管理员扫描成功，我要离开了!")

      var pages = getCurrentPages();
      var prevPage = pages[pages.length - 2];
      prevPage.setData({
        refreshbol: true
      })

    }


    clearInterval(this.data.a);
    clearInterval(this.data.b);
  },

  onHide: function () {

    var that = this

    if (that.data.scanbol) {
      var pages = getCurrentPages();
      var prevPage = pages[pages.length - 2];
      prevPage.setData({

        refreshbol: true
      })

    }


    clearInterval(this.data.a);
    clearInterval(this.data.b);
  },

})
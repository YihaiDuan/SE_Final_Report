
Page({
  data: {

    logs: '',
    BorrowQR: '',
    id: '',
    count: '',
    totalcase: '',
    totalpay: '',
    count: '',
    pay: false,
    combol: false,
    money: '',
    scanbol: true,
    showdiscount: false,
    showselect: true,

    paybol: false,
  },


  onLoad: function (options) {
    var that = this
    that.setData({
      totalcase: options.totalcase,
      id: options.id,
      totalpay: options.totalpay,
    })
    console.log(options.id)
    console.log("总押金" + that.data.totalcase)
    console.log("总费用" + that.data.totalpay)


  },


  onShow: function () {
    var that = this;
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






    console.log("总押金" + that.data.totalcase)
    console.log("总费用" + that.data.totalpay)

    that.setData({

      count: that.data.totalcase - that.data.totalpay
    })

    console.log("退款菲佣" + that.data.count)
    //退款
    if (parseInt(that.data.count) > 0) {

      console.log("退款")
      that.setData({
        pay: true
      })
    }
    //支付
    else {
      wx.request({
        url: 'https://www.titwdj.cn/BorrowBook/getUserInformationServlet',
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
          if (parseInt(that.data.money) >= parseInt(-that.data.count)) {
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


      console.log("支付")
      that.setData({
        pay: false
      })



    }

    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/AddReturnQR',
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




    this.data.a = setInterval(function () {
      wx.request({
        url: 'https://www.titwdj.cn/BorrowBook/RefreshReturnQR',
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



    //监听管理员是否确认完成
    this.data.b = setInterval(function () {
      wx.request({
        url: 'https://www.titwdj.cn/BorrowBook/ScanReturnOk',
        data: {
          id: that.data.id
        },
        success: function (res) {
          console.log(res.data)

          if (res.data.bol == 'true') {
            that.setData({
              scanbol: false
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
            url: 'https://www.titwdj.cn/BorrowBook/ReturnPayServlet',
            data:
            {
              userid: that.data.logs.userid,
              cash: (-that.data.count),

            },
            success: function (res) {
              console.log(res.data)


              wx.navigateTo({
                url: '../paysuccess/paysuccess?action=returnpay'
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

    if (!that.data.scanbol) {

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

    if (!that.data.scanbol) {

      var pages = getCurrentPages();
      var prevPage = pages[pages.length - 2];
      prevPage.setData({

        refreshbol: true
      })

    }
    clearInterval(this.data.a);
    clearInterval(this.data.b);
  },




  returnbook: function (e) {

    var that = this;
    console.log(that.data.count)
    console.log(e)


    wx.showModal({
      title: '提示',
      content: '确认还书!',
      success: function (res) {


        if (res.confirm) {
          //进行还书
          console.log(that.data.id);

          wx.request({
            url: 'https://www.titwdj.cn/BorrowBook/ScanReturnServlet',
            data:
            {
              userid: that.data.logs.userid,
              count: that.data.count,
              bookid: that.data.id


            },
            success: function (res) {
              console.log(res.data)
              wx.showToast({
                title: '还书成功!',
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

})
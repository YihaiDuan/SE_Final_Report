// pages/preorder/preorder.js
Page({
  data:{
    bookid:'',
    detail:'',
    orderdate:'',
    free:'0',
    cash:'0.5',
    index:'0',
    logs:'',
    current:'',
    endtime:'',
    date:'',
    formId:'',
    money:'',
    paybol:false,
  },

  bindDateChange: function (e) {

    var that=this
    console.log('picker发送选择改变，携带值为', e.detail.value)
    console.log(e)
    this.setData({
      orderdate: e.detail.value
    })
wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/getOrderFreeByTime',
  data:{
date:e.detail.value
  },
  success:function(res)
  {
    console.log(res.data)
  that.setData({
    index:res.data.day
  })
  that.setData({
    cash: 0.5 * that.data.index
  })


  if (parseInt(that.data.money) >= parseInt(that.data.cash)) {
    that.setData({
      paybol: true
    })
  }
  else {
    that.setData({
      paybol: false

    })
  }



  }
})

  },
  
  onLoad:function(options)
  {
    var that=this
    console.log(options.bookid)


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


    that.setData({
      bookid:options.bookid
    })

    

    
    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/BookDetailServlet',
      data:{
bookid:options.bookid
      },
      success:function(res)
      {
console.log(res.data)
that.setData({

detail:res.data
})
      }
    })


  


    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/getOrderTimeServlet',
      success: function (res) 
      {
        console.log(res.data)
       that.setData({
          current: res.data.current,
          endtime:res.data.endtime,
          date:res.data.current,
          orderdate:res.data.current
        })

      },
    })

  },


  onShow:function()
  {

    var that=this

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

        if (parseInt(res.data.money) >=0.5) {
          that.setData({
            paybol: true
          })
        }
        else {
          that.setData({
            paybol: false

          })
        }
      }
    })

 



  },

//支付
pay: function (e) {

    var that = this;
    console.log(e.detail.formId)
    that.setData({
      formId:e.detail.formId
    })


  wx.showModal({
    title: '提示',
    content: '确认支付!',
    success: function (res) {
      if (res.confirm) 
      {
      
        wx.request({
          url: 'https://www.titwdj.cn/BorrowBook/OnlineOrderServlet',
          data:
          {
            status: "0",
            userid: that.data.logs.userid,
            bookid: that.data.bookid,
            formId: that.data.formId,
            orderdate: that.data.orderdate,
            action: 'order',
            deadtime: that.data.orderdate,
            cash:that.data.cash
          },

          success: function (res) {
            console.log(res.data)
            if (res.data == "beyond") {
              that.setData({
                payboard: false,
                paybgbol: false
              })
              wx.showToast({
                title: "你已预订超过两本图书!",
                icon: 'success',
                duration: 5000
              })
            }
            else {
              that.setData({
                detail: res.data,
                payboard: false,
                paybgbol: false

              })

              wx.redirectTo({
                url: '../paysuccess/paysuccess?action=order',
              })

            }
          },
        })





      } else if (res.cancel) {
        console.log('用户点击取消')
      }
    }
  })


  },



})
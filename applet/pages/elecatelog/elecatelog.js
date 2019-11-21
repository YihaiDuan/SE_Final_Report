// pages/catalogue/catalogue.js
Page({
  data:{
 bookid:'',
 booklist:'',
 freeindex:'',
 logs:'',
 bol:'',
user:'',
  },
  onLoad:function(options)
  {

    var that=this
    console.log("我来了")
    console.log(options.bookid)

that.setData({
bookid:options.bookid
})

//获取图书目录
wx.request({
  url: 'http://139.199.23.184/BorrowBook/getEleCateLog',
  data: {
    bookid: that.data.bookid
  },
  success: function (res) {
    console.log(res.data)
    that.setData({
      booklist: res.data
    })
   that.setData({

     freeindex:that.data.booklist.length/3
   })
console.log(that.data.freeindex)
  }

})


  },
  
  onShow:function()
  {
console.log("我来了")
    var that = this

    try {
      var value = wx.getStorageSync('key')
      if (value) {
        that.setData({
          logs: value,
          bol: true
        })

      }
    }
    catch (e) {

    }

if(value)
{
    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/getEleBookDetailByUser',
      data: {
        userid: that.data.logs.userid,
        bookid: that.data.bookid
      },
      success: function (res) {
        console.log(res.data)
        that.setData({
          detail: res.data
        })
        setTimeout(function () {
          wx.hideLoading()
        }, 1000)
      },
    })


    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/getUserInformationServlet',
      data: {
        userid: that.data.logs.userid
      },
      success: function (res) {
        console.log(res.data)
        that.setData({
          user: res.data
        })
      }
    })



}






  },

  nologin: function () {


    wx.showModal({
      title: '提示',
      content: '你尚未登录,请先登录!',
      success: function (res) {
        if (res.confirm) {
          console.log('用户点击确定')

          wx.navigateTo({
            url: '../register/register',
          })
        }
        else if (res.cancel) {
          console.log('用户点击取消')
        }
      }
    })

  },

nograde:function()
{

  wx.showModal({
    title: '提示',
    content: '是否升级为vip会员?',
    success: function (res) {
      if (res.confirm) {
        console.log('用户点击确定')
        wx.navigateTo({
          url: '../grade/grade',
        })
      } else if (res.cancel) {
        console.log('用户点击取消')
      }
    }
  })

}

})
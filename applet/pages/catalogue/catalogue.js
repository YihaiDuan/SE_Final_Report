// pages/catalogue/catalogue.js
Page({
  data:{

 booklist:'',
list:''

  },
  onLoad:function(options){

    var that=this

    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/BookDetailServlet', //仅为示例，并非真实的接口地址
      data: {
        bookid: options.bookid
      },
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {

        console.log(res.data);
        that.setData({
          list: res.data.list
        })

        console.log(that.data.list)

        var str = that.data.list.split("@");
        console.log(str)
        that.setData({
          booklist: str
        })

      }
    })





  },
  
})
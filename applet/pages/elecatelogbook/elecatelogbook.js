// pages/catalogue/catalogue.js
Page({
  data:{
 bookid:'',
 booklist:'',
 freeindex:'',
 logs:'',
 bol:'',
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
  url: 'https://www.titwdj.cn/BorrowBook/getEleCateLog',
  data: {
    bookid: that.data.bookid
  },
  success: function (res) {
    console.log(res.data)
    that.setData({
      booklist: res.data
    })


  }

})


  },
  


})
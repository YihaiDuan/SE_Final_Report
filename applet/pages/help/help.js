// pages/qrdata/qrdata.js
Page({
  data:{

  },


  previewimage: function (e) 
  {
    var current = e.currentTarget.dataset

    var that = this

   console.log(current.url)


    wx.previewImage({
      current: 'https://www.titwdj.cn/BorrowBook/images/' + current.url,

      urls: [
        'https://www.titwdj.cn/BorrowBook/images/' + current.url,

      ],


      success: function (res) {
        console.log(res);
      },
      //也根本不走
      fail: function () {
        console.log('fail')
      }
    })


  },


})
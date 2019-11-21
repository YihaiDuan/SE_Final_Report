// pages/chosen/chosen.js
Page({
  data:
  {

    applydetail: '',
    logs: '',
    userid: '',
    id:''

  },

  onLoad: function (options) 
  {
    // 页面渲染完成
    var that = this;
console.log(options.id)

that.setData({

id:options.id

})
  },

  onShow:function()
  {
var that=this
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


    console.log(that.data.logs.userid)
    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/ApplyDetail',

      data: {
        id: that.data.id
      },

      success: function (res) {
        console.log(res.data)
        if (res.data != '0') {
          that.setData({
            applydetail: res.data
          })

        }

      }

    })
  },


  previewimages: function (e) 
  {


    var current = e.currentTarget.dataset

    var that = this




    wx.previewImage({
      current: 'https://www.titwdj.cn/BorrowBook/images/' + current.url,

      urls: [
        'http://139.199.23.184/BorrowBook/images/' + current.url,

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
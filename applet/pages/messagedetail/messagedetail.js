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
      url: 'https://www.titwdj.cn/BorrowBook/MessageDetail',

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



  noshare: function () {

    wx.showModal({
      title: '提示',
      content: '请先上传个人二维码!',
      success: function (res) {
        if (res.confirm) {
          console.log('用户点击确定')

          wx.navigateTo({

            url: '../mynews/mynews',


          })


        }
        else if (res.cancel) {
          console.log('用户点击取消')
        }
      }
    })

  },

  agreeapply: function (e) {

    var that = this
    var current = e.currentTarget.dataset;
    console.log(current.id)

    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/AgreeApplyDetail',

      data: {
        userid: that.data.logs.userid,
        id: current.id,
        action: 'agree'
      },
      success: function (res) {

        console.log(res.data)
        that.setData({

          applydetail: res.data

        })
      }

    })

  },

  refuseapply: function (e) {
    var that = this
    var current = e.currentTarget.dataset;
    console.log(current.id)

    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/AgreeApplyDetail',

      data: {
        userid: that.data.logs.userid,
        id: current.id,
        action: 'refuse'
      },
      success: function (res) {

        console.log(res.data)

        that.setData({

          applydetail: res.data

        })
      }
    })
  }

})
// pages/chosen/chosen.js
Page({
  data:
  {

    applylist: '',
    logs: '',
    userid: '',
    showbol:true,

  },

  onShow: function () {
    // 页面渲染完成
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


    console.log(that.data.logs.userid)
    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/MyApply',

      data: {
        userid: that.data.logs.userid
      },

      success: function (res) {
        console.log(res.data)
        if(res.data!='0')
        {
          that.setData({
            messagelist: res.data,
            showbol:true
          })
        }
        else
        {

          that.setData({

            showbol:false
          })
        }
      

      }

    })


  },

})
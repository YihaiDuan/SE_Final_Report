// pages/qrdata/qrdata.js
Page({
  data:{
id:'',
bookid:'',
logs:'',
bol:true,
  },
onLoad:function(options)
{
  var that=this
console.log(options.id)
console.log(options.bookid)
that.setData({
id:options.id,
 bookid:options.bookid
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

},
noshare:function()
{
 
  wx.showModal({
    title: '提示',
    content: '你已经分享过,是否到动态圈查看！',
    success: function (res) {
      if (res.confirm) {
        console.log('用户点击确定')

        wx.reLaunch({
          url: '../dynamic/dynamic', 
        })
      } else if (res.cancel) {
        console.log('用户点击取消')
      }
    }
  })

},

share:function(e)
{
console.log(e)
var that=this

wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/AddGroupShare',

  method: 'POST',
  header: {
    'content-type': 'application/x-www-form-urlencoded'
  },
data:{
userid:that.data.logs.userid,
describ:'开团享受租金打折优惠，一起来开团吧',
bookid:that.data.bookid,
groupmainid:that.data.id,
},
success:function(res)
{
console.log(res.data)
  wx.showToast({
    title: '分享成功!',
    icon: 'success',
    duration: 2000
  })

  that.setData({
    bol: false
  })
 
}
})
}


})
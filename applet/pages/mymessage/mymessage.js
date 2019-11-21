// pages/comment/comment.js
Page({
  data:
  {

messagelist:'',
 logs:'',
 userid:'',


  },

onLoad:function()
{
 

  },


onShow:function()
{

  var that=this
  // 页面渲染完成
 

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
    url: 'https://www.titwdj.cn/BorrowBook/getMymessage',

    data: {
      userid: that.data.logs.userid
    },

    success: function (res) {
      console.log(res.data)
      if (res.data != '0') {
        that.setData({
          messagelist: res.data,
         
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
      else if (res.cancel)
       {
        console.log('用户点击取消')
      }
    }
  })

},

agreeapply:function(e)
{

  var that=this
  var current=e.currentTarget.dataset;
  console.log(current.id)

wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/AgreeApply',

  data:{
userid:that.data.logs.userid,
id:current.id,
action:'agree'
  },
  success:function(res)
  {

console.log(res.data)
that.setData({

  messagelist: res.data

})
  }

})

},

refuseapply:function(e)
{
  var that = this
  var current = e.currentTarget.dataset;
  console.log(current.id)

  wx.request({
    url: 'https://www.titwdj.cn/BorrowBook/AgreeApply',

    data: {
      userid: that.data.logs.userid,
      id: current.id,
      action: 'refuse'
    },
    success: function (res) {

      console.log(res.data)

      that.setData({

   messagelist:res.data

      })

    }

  })

}

})

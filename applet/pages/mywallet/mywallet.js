Page({

data:{

logs:'',
money:'0.00',
mesbol:''
},

onShow:function()
{
  var that=this
  try {
    var value = wx.getStorageSync('key')
    if (value) 
    {
      that.setData({
        logs: value

      })

    }
  }
  catch (e) {

  }


wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/getUserWallet',
   data:{
   userid:that.data.logs.userid
   },
   success:function(res)
   {
     console.log(res.data)
     that.setData({

       money: res.data
     })

   }
})


//判断是否有消息
wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/ReadStatusServlet',
  data: {
    userid: that.data.logs.userid
  },
  success: function (res) {
    console.log(res.data)
    that.setData({
      mesbol: res.data
    })

  }

})
},


})
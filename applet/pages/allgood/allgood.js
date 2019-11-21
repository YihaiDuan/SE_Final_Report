// pages/special/special.js
Page({
  data:{
 reducelist:''
  },
  onLoad:function(options)
  {
     var that=this

   wx.request({
     url: 'https://www.titwdj.cn/BorrowBook/getGoodEvaluateTop',
data:{
  action:1
},
     
     success:function(res)
     {
      console.log(res.data)
      that.setData({
       reducelist:res.data
      })
     }
   })


  },


})
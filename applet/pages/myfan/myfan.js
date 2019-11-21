// pages/qrdata/qrdata.js
Page({
  data:{
userid:'',
logs:'',
userlist:'',
showbol:true
  },

onLoad:function(options)
{

  var that=this
console.log(options.userid)
wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/myFan',

data:{
  userid:options.userid
}
,
success:function(res)
{
console.log(res.data)
that.setData({
  userlist: res.data
})

if(that.date.userlist.length!=0)
{
  that.setData({
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
}




 })
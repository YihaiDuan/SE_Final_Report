// pages/mynews/mynews.js
Page({
  data:{
    
     logs:"",
     user:'',
     discountlist:'',
     canuser:''
      
  },

onLoad:function(options)
{

  var that=this
console.log(options.canuser)

that.setData({
  canuser:options.canuser
})
},
  onShow:function()
  {

    var that=this;
 try {
  var value = wx.getStorageSync('key')
  if (value)
   {
that.setData({
logs:value

})

  }
}
 catch (e) {

}

wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/mydiscount',
data:{
  userid:that.data.logs.userid
},
success:function(res)
{
console.log(res.data)
that.setData({
  discountlist:res.data
})
}
})
   
  },




})
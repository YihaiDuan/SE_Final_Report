// pages/QR/QR.js
Page({
  data:
  {
   display:false,
   showQR:'',
  

  },
/*
onShow:function(){
    // 页面渲染完成
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
  },
*/


  onLoad:function(options)
  {

    var that=this;
  
wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/ShowQR',
 
data :{

userid: wx.getStorageSync('key').userid
},


  success: function(res)
  {
   
console.log(res.data);
that.setData({

  showQR:res.data

})
  },
 
})
  },
 





})
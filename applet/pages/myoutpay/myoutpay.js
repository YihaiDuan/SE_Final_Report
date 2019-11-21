Page({
  data:{
    
    logs: '',
    userid:'',
    outpaylist:''
  },


  
onShow :function()
{
var that=this;
 try {
  var value = wx.getStorageSync('key')
  if (value)
   {
that.setData({
logs:value,
bol:true,
})

console.log("登录成功")
console.log(that.data.bol)
  }
}
 catch (e) {
  
}

wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/ShowOutPayByUser',

  data:{
    userid:that.data.logs.userid
  },

  success:function(res)
  {
    console.log(res.data)
    that.setData({
      outpaylist: res.data

    })
   

  }
})



wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/UpdateReadStatus',
  data:{
    userid:that.data.logs.userid,
    action:'outpay'
  },
  success:function(res)
  {
    console.log(res)
  }
})

},





onUnload:function()
{

},

onHide:function(){

  

}


})

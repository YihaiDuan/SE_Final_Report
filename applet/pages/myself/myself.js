// pages/myself/myself.js
Page({
  data:{

   display1:false,
   logs:'',
   bol:false,
   resdata:'',
   Type:'',
   outprint:"",
   referstatus:false,
   bolstatus:'',
  moneyscore:'',
  
  },

  onShow:function()
  {
  
    var that=this;

 try {

  var value = wx.getStorageSync('key')
  console.log(value)
  if (value) 
  {
   
that.setData({
bol:true,
logs:value

})

  }
} 
catch (e) 
{
  
}


if(value)
{
//判断是否有推荐提醒
wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/ReferMessageBol',

  data: {

  userid:that.data.logs.userid

  },
 
  success: function(res)
  {
   
   console.log(res.data)
   if(res.data=='0')
   {
that.setData({

referstatus:true

})


   }
   else{

that.setData({

referstatus:false

})


   }


  }
  
})

//获取积分
wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/getMoneyScore',
  data:{
userid:that.data.logs.userid
  },
  success:function(res)
  {
    console.log(res.data)
that.setData({
  moneyscore:res.data
})
  }
})


}//if

},








nologin:function()
{

wx.showModal({
  title: '提示',
  content: '你尚登录,请先登录!',
  success: function(res) {
    if (res.confirm) {
      console.log('用户点击确定')

wx.navigateTo({

  url: '../register/register',
  
 
})


    } 
    else if (res.cancel) {
      console.log('用户点击取消')
    }
  }
})


},
 
})
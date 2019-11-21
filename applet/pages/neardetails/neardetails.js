// pages/particulars/particulars.js
Page({

  data:{
     

      sharedetail:'',
      distance:'',
      borrowid:'',
      bol:false,
      logs:'',
      applybol:'',


  },
  

onLoad:function(options){

     var that=this;
  wx.showLoading({
  title: '加载中',
}),
  that.setData({

  borrowid: options.borrowid,
  distance: options.distance

  })

console.log(options.borrowid)
console.log(options.distance)


},



onShow:function()
{

    var that=this
  wx.showLoading({
  title: '加载中',
})
 try {
  var value = wx.getStorageSync('key')


  if (value)
   {
that.setData({
logs:value,
bol:true

})

  }
}
 catch (e) 
 {
  
}




if(value)
{

wx.request({

  url: 'https://www.titwdj.cn/BorrowBook/ShowNearBorrowDetail',

  data: {

    borrowid:that.data.borrowid,
    userid:that.data.logs.userid,
  },
  

  success: function(res)
  {
   console.log(res.data)
   that.setData({

sharedetail:res.data

   })

    setTimeout(function(){
   wx.hideLoading()
     },1000)
  },
  
})

}
},


apply:function(e)
{

var that=this
var current=e.currentTarget.dataset;
console.log(current.borrowid)
console.log(current.otherid)
console.log(that.data.logs.userid)

wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/UpApply',

  data:{
userid:that.data.logs.userid,
id:current.borrowid,
otherid:current.otherid

  },

  success:function(res)
  {
console.log(res.data)
    wx.showToast({
      title: '申请成功!',
      icon: 'success',
      duration: 2000
    })
  
    that.setData({

      sharedetail: res.data

    })
  }
})


}

})
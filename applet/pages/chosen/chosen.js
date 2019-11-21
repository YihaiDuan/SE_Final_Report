// pages/chosen/chosen.js
Page({
  data:{


   booknum:'',
   booknew:''


  },
  onShow:function()
  {

    var that=this
   wx.showLoading({
  title: '加载中',
}),
wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/ShowBookNumIndex',

 
  success: function(res){
    console.log(res.data)

    that.setData({

    booknum:res.data

    })
    setTimeout(function(){
   wx.hideLoading()
     },1000)
  
  
  },
  
}),

   
wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/ShowBookNewIndex',

 
  success: function(res){
    console.log(res.data)

    that.setData({

    booknew:res.data

    })

  },
  
})


  },
 

})
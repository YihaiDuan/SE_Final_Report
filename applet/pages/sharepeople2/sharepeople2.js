// pages/sharepeople/sharepeople.js
Page({
  data:{

    userid:'',
     distance:'',
     sharelist:''

  },
  onLoad:function(options)
  {

var that=this
  wx.showLoading({
  title: '加载中',
})
console.log(options.userid)
console.log(options.distance)

that.setData({


distance:options.distance

})


wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/getShareBookByUserid',
  data: {

    userid:options.userid
  },
 

  success: function(res){

    console.log(res.data)

    that.setData({

 sharelist:res.data

    })
   setTimeout(function(){
   wx.hideLoading()
     },1000)

  },

})



    
  },


 
})
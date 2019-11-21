// pages/chosen/chosen.js
Page({
  data:{

collectlist:'',
logs:'',
collectsharelist:'',
logo:true,
currentTab: 0,  
  },

  swichNav: function (e) {
    console.log(e);
    var that = this;
    if (this.data.currentTab === e.target.dataset.current) {
      return false;
    }
    else {
      that.setData({
        currentTab: e.target.dataset.current,
      })
    }
  },
  swiperChange: function (e) {
    console.log(e);
    var that = this;
    this.setData({
      currentTab: e.detail.current,
    })

     if (that.data.currentTab == '1') {
      console.log("我是优惠券")

    }
    

  },  

bindlogo1:function(){
  var that=this
that.setData({

logo:true

})

},
bindlogo2:function(){
  var that=this
that.setData({

logo:false

})

},
  onShow:function()
  {
 var that=this
 wx.getSystemInfo({
   success: function (res) {
     that.setData({

       clicentheight: res.windowHeight - 36

     })


   }
 })
 try {
  var value = wx.getStorageSync('key')
  if (value) {
   
that.setData({

logs:value

})

  }
} catch (e) {
  
}
  
wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/ShowMyCollect',

  data: {

userid:that.data.logs.userid

  },
  
  success: function(res)
  {
  
  console.log(res.data)
that.setData({

collectlist:res.data

})

  },
 
}),

//分享圈的收藏
wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/getCollectByUser',
  data: {

userid:that.data.logs.userid

  },
 
  success: function(res)
  {

    console.log(res.data)
    that.setData({

collectsharelist:res.data

    })
    
  },
 
})





},



deletelibare:function(e)
{
var that=this

var current=e.currentTarget.dataset

console.log(current.id)

wx.showModal({
  title: '提示',
  content: '是否取消收藏!',
  success: function(res) 
  {
    if (res.confirm) 
    {
      console.log('用户点击确定')


wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/DeleteCollectLibara',
  data: {

id:current.id,
userid:that.data.logs.userid
  },
 
  success: function(res)
  {

wx.showToast({
  title: '取消成功!',
  icon: 'success',
  duration: 3000
})

   console.log(res.data)
that.setData({

collectlist:res.data

})

  },
  
})
   
   
   
    } else if (res.cancel) {
      console.log('用户点击取消')
    }
  }
})


},



deleteshare:function(e)
{
var that=this

var current=e.currentTarget.dataset

console.log(current.id)

wx.showModal({
  title: '提示',
  content: '是否取消收藏!',
  success: function(res) {
    if (res.confirm) {
     
     wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/DeleteCollectShare',
  data: {

id:current.id,
userid:that.data.logs.userid
  },
 
  success: function(res)
  {
   console.log(res.data)
wx.showToast({
  title: '取消成功!',
  icon: 'success',
  duration: 3000
})


that.setData({

collectsharelist:res.data

})

  },
  
})


    } 
    else if (res.cancel) {
      console.log('用户点击取消')
    }
  }
})

}
 


})
// pages/particulars/particulars.js
Page({

  data:{
     

      sharedetail:'',
      distance:'',
      shareid:'',
      bol:false,
      logs:''


  },
  

onLoad:function(options){

     var that=this;
  wx.showLoading({
  title: '加载中',
}),
  that.setData({

  shareid: options.shareid,
  distance: options.distance

  })

console.log(options.shareid)
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

  url: 'https://www.titwdj.cn/BorrowBook/ShowShareBookbyidandUser',

  data: {

    shareid:that.data.shareid,
    userid:that.data.logs.userid
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
else
{

wx.request({

  url: 'https://www.titwdj.cn/BorrowBook/ShowShareBookbyId',
  data: {

    shareid:that.data.shareid
  
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

download:function(e)
{


var current=e.currentTarget.dataset

var that=this




 wx.previewImage({
     current: 'https://www.titwdj.cn/BorrowBook/images/'+current.url,
    
     urls: [
       'https://www.titwdj.cn/BorrowBook/images/'+current.url,
        
        ],

     
        success: function(res) {
          console.log(res);
        },
        //也根本不走
        fail: function() {
          console.log('fail')
        }
   })

 
},

collect:function(e)
{
var that=this
var current=e.currentTarget.dataset
console.log(current.shareid)


wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/EditeShareBookByUser',
  data: 
  {
userid:that.data.logs.userid,
shareid:current.shareid,
action:'add'
  },
  success: function(res)
  {
  console.log(res.data)

 that.setData({

sharedetail:res.data

   })

  },
})

},



onShareAppMessage: function () {

  var that = this
  console.log(that.data.shareid)
  console.log(that.data.distance)
  return {
    title: '图书详情',
    path: 'pages/details/details?shareid=' + that.data.shareid + "&distance=" + that.data.distance,

    success: function (res) {
      wx.showToast({
        title: '分享成功！',
        icon: 'success',
        duration: 2000
      })
    },
    fail: function (res) {
      // 转发失败
    }
  }
},



nocollect:function(e)
{

var that=this
  var current=e.currentTarget.dataset
 
     console.log(current.shareid)

wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/EditeShareBookByUser',
  data: 
  {
userid:that.data.logs.userid,
shareid:current.shareid,
action:'delete'
  },
  success: function(res)
  {
  console.log(res)
 that.setData({

sharedetail:res.data

   })

   

  },
 
})

 
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
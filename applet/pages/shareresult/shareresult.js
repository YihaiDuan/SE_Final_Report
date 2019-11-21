// pages/chosen/chosen.js
Page({
  data:{


   booknum:'',
   booknew:'',
    latitude:'0',
    longitude:'0',
    sharelist:'',
    search:''


  },

onLoad:function(options)
{
var that=this
console.log(options.search)

wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/ShareBookSearch',
  data: {

  key:options.search,
  latitude:options.latitude,
  longitude:options.longitude

  },

   method:'POST',

header: {
      'content-type': 'application/x-www-form-urlencoded'
      
  },
  success: function(res)
  {

    console.log(res.data)
    if(res.data!='fail')
    that.setData({

   sharelist:res.data

    })   

  },
 
}) 

  
}

})
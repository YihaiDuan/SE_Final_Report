Page({
data:
{
  longitude:'',
  latitude:'',
  markers: [],
 
},

 onShow: function()
{

  var that=this
    wx.getLocation({
      type: 'gcj02', 
      success: function (res) 
      {

  that.setData({
    latitude: res.latitude,
    longitude: res.longitude
})

console.log(that.data.latitude)
        console.log(that.data.longitude)    
        }
    })

wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/mapservlet',

success:function(e)
{
console.log(e.data);
that.setData({

  markers: e.data
})

},
fail: function(e)
{
}

})
 

},

 
  markertap(e) 
  {

    var that=this
    console.log(e.markerId)

wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/getLocation',

  data:{

  userid:e.markerId

  },
  success :function(res)
  {
console.log(res.data)
wx.openLocation({
  latitude: res.data.latitude,
  longitude: res.data.longitude,
  scale: 18,

})
  },

  fail :function(res)
  {

  }
})

  
  },
  
  
})
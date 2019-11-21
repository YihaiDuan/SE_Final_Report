// pages/chosen/chosen.js
Page({
  data:{


   booknum:'',
   booknew:'',
    latitude:'0',
    longitude:'0',
    sharelist:'',
    search:'',
    logs:'',
    show:false
  },

  
  
  onShow: function ()
   {
   var that=this;
   try {
     var value = wx.getStorageSync('key')
     if (value) 
     {
       that.setData({
         logs: value

       })

     }
   }
   catch (e) {

   }




   
     wx.showLoading({
  title: '加载中',
}),
   
   wx.getLocation({
       type: 'gcj02',
      success: function(res)
       {
        that.setData({
          latitude:res.latitude,
          longitude:res.longitude
        })

console.log(that.data.latitude)
console.log(that.data.longitude)

wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/ShowAllShareBook',
  
  

  data: {

 latitude:res.latitude,
 longitude:res.longitude,
 userid:that.data.logs.userid,

  },
   

  success: function(res)
  {
   console.log(res.data);
that.setData({

sharelist:res.data,
show:true
})

setTimeout(function(){
   wx.hideLoading()
     },1000)
  

  },
  
})


      },

      fail:function(e)
      {


wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/ShowAllShareBook',
  
  

  data: {

 latitude:'0',
 longitude:'0'

  },
   

  success: function(res)
  {
   console.log(res.data);
   that.setData({

sharelist:res.data
})

  },
  
})

      }
    })

  },


formSubmit:function(e)
{

var that=this
that.setData({

   search:e.detail.value.search

  })


console.log(that.data.search)
console.log(that.data.latitude)


wx.navigateTo({
  url: '../shareresult/shareresult?search='+that.data.search+'&latitude='+that.data.latitude+'&longitude='+that.data.longitude
})




}

 


})
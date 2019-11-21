Page({

data:{

url:''

},


ascan:function()
{
var that=this
wx.scanCode({
  success: (res) => {
    
  that.setData({


   url:res.result

  })
        
console.log(that.data.url)

wx.request({
  url: that.data.url,
  
 
  success: function(res){

    console.log(res.data)
    
  }
})



  }
})



 
  








}




    

})
// pages/qrdata/qrdata.js


Page({
  data:{
   userid:'',
   BorrowQR:'',
   id:''

  },



  onLoad:function(options)
  {
    var that=this

     console.log(options.id)
    that.setData({

   id:options.id

    })
   


wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/TransmitBorrowQR',
     data: {

       id:that.data.id

     },
   
     success: function(res){
      
   console.log(res.data)
       that.setData({

      BorrowQR:res.data

       })
   
   
     },
    
   })
  
  },





onShow:function()
{

var that=this;

this.data.b=setInterval(function(){

  wx.request({
    url: 'https://www.titwdj.cn/BorrowBook/TransmitBorrowQR',
    data: {

      id: that.data.id

    },

    success: function (res) {

      console.log(res.data)
   if(res.data=='0')
   {
console.log("我要跳转了")

wx.switchTab({
  url: '../operation/operation?action=returnsuccess',
 
})
   }
   else
   {
     that.setData({

       BorrowQR: res.data

     })

   }
    
    },

  })
},1000)


},



 onUnload:function()
{
  clearInterval(this.data.b);
}


})
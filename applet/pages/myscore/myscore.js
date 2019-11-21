// pages/mynews/mynews.js
Page({
  data:{
    
     logs:"",
     user:'',
     discountlist:''
      
  },

  onShow:function()
  {

    var that=this;
 try {
  var value = wx.getStorageSync('key')
  if (value)
   {
that.setData({
logs:value

})

  }
}
 catch (e) {

}


    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/getUserInformationServlet',
      data: {
        userid: that.data.logs.userid
      },
      success: function (res) {
        console.log(res.data)
        that.setData({
      user:res.data
        })
      }
    })


  },

onLoad:function()
{

  var that=this
wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/ShowScoreDiscount',

  success:function(res)
  {
console.log(res.data)
that.setData({
  discountlist:res.data
})
  }
})
}


})
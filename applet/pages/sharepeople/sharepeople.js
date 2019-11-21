// pages/sharepeople/sharepeople.js
Page({
  data:{

  logs:[],
  
  sharelist:''

  },


  onLoad:function()
  {

 var that=this;
console.log("我的分享")
   wx.showLoading({
  title: '加载中',
})

 try {
  var value = wx.getStorageSync('key')
  if (value) 
  {
   
that.setData({

logs:value

})





  }
} 
catch (e) 
{
  
}



wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/getShareBookByUserid',
  data: {

userid:that.data.logs.userid

  }, 

  success: function(res)
  {
  console.log(res.data);
that.setData({

sharelist:res.data

})
setTimeout(function(){
   wx.hideLoading()
     },1000)
  },
  
})

},


delete:function(e)
{

  var that=this
var current=e.currentTarget.dataset
console.log(current.shareid)



  wx.showModal({
    title: '提示',
    content: '确定删除此书！',
    success: function (res)
     {
      if (res.confirm) {
        console.log('用户点击确定')
        wx.request({
          url: 'https://www.titwdj.cn/BorrowBook/DeleteShareBook',
          data: {

            userid: that.data.logs.userid,
            shareid: current.shareid

          },

          success: function (res) {

            console.log(res.data)

            wx.showToast({
              title: '删除成功!',
              icon: 'success',
              duration: 2000
            })

            that.setData({

              sharelist: res.data

            })

          },

        })
      } else if (res.cancel) {
        console.log('用户点击取消')
      }
    }
  })

}


})
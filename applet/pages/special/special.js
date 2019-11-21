// pages/special/special.js
Page({
  data:{
    id:'',
    topicbyid:'',
    topicbook:'',
    show:false
  },
  onLoad:function(options){
     var that=this

     wx.showLoading({
       title: '加载中',
     }),

     that.setData({

       id:options.id
     })
    console.log(options.id)
    console.log(that.data.id)


   wx.request({
     url: 'https://www.titwdj.cn/BorrowBook/getTopicByidServlet',

     data:{
      id:that.data.id
     },
     success:function(res)
     {


       setTimeout(function () {
         wx.hideLoading()
       }, 1000)
      console.log(res.data)
      that.setData({
   
       topicbyid:res.data,
       show:true
      })

    
     }
   })



   wx.request({
     url: 'https://www.titwdj.cn/BorrowBook/ShowAllTopicByid',

     data: {
       id: that.data.id
     },
     success: function (res) {
       console.log(res.data)
       that.setData({

         topicbook: res.data
       })
     }


   })


  },


})
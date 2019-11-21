
Page({

  data: {
 
groupmainid:'',
group:'',
groupbol:false,
groupfinalybol:false,
groupfailbol:false,
logs:'',
bol:false,
openbol:false,

  },

 onLoad:function(options)
{
var that=this
that.setData({
  groupmainid: options.groupmainid
})
console.log(options.groupmainid)


},


onShow:function()
{
var that=this
  try {
    var value = wx.getStorageSync('key')
    if (value) {
      that.setData({
        logs: value,
        bol:true
      })

    }
  }
  catch (e) {

  }
if(value)
{


//判断用户是否已近参加该团
wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/BolInGroupBol',
  data:{
    userid:that.data.logs.userid,
    groupmainid:that.data.groupmainid
  },
  success:function(res)
  {

    console.log("opengroupbol"+res.data.openbol)
    that.setData({
openbol:res.data.openbol
    })
  }
})

  wx.request({
    url: 'https://www.titwdj.cn/BorrowBook/getGroupInf',
    data: {
      groupmainid: that.data.groupmainid,
    },
    success: function (res) {
      console.log(res.data)

      if (res.data == '2') {
        console.log("团过期")
        that.setData({
          groupfailbol: true,
          groupbol: false,
          groupfinalybol: false,

        })
      }
      else if (res.data == '1') 
      {

          console.log("团满")
          that.setData({
            groupbol: false,
            groupfinalybol: true,
            groupfailbol: false
          })
        
     
      }
      else {
           console.log("团未满")
        that.setData({
          group: res.data,
          groupbol: true,
          groupfinalybol: false,
          groupfailbol: false
       
        })
      }

    }
  })

}
else
{
  wx.request({
    url: 'https://www.titwdj.cn/BorrowBook/getGroupInf',
    data: {
      groupmainid: that.data.groupmainid,
    },
    success: function (res) {
      console.log(res.data)

      if (res.data == '2') {
        that.setData({
          groupfailbol: true,
          groupbol: false,
          groupfinalybol: false,
        })
      }
      else if (res.data == '1') {

        that.setData({
         groupbol: false,
        groupfinalybol: true,
        groupfailbol: false
        })
      
      }
      else {
        that.setData({
          group: res.data,
          groupbol: true,
          groupfinalybol:false,
          groupfailbol:false
        })
      }

    }
  })


}
},

onShareAppMessage:function (){

   var that = this
  
   return {
     title: '拼团分享',
     path: 'pages/sharegroupdetail/sharegroupdetail?groupmainid='+that.data.groupmainid,
     success: function (res) {
       console.log(that.data.groupmainid)
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



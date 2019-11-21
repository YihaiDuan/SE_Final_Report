// pages/myself/myself.js
Page({
  data:{

    items: [
      {name: '24', value: '24h',checked: 'true'},
      {name: '10', value: '10h' },
      {name: '6', value: '6h'},
    ],

   display1:false,
   logs:[],
   bol:false,
   resdata:'',
   Type:'',
   outprint:"",

   referstatus:false,
   bolstatus:'',

  },
scan1:function()
{

var that=this

wx.scanCode({
  success: (res) => {
    console.log(res.result)
    console.log(res.scanType)
  }
}
)
},

 //设置推荐提醒
  switch1Change: function (e)
  {


var that=this
 that.setData({
     display1:e.detail.value
     })

console.log(that.data.display1)




if(e.detail.value)
{

  wx.request({

    url: 'https://www.titwdj.cn/BorrowBook/SetReferStatus',


    data: 
    {

  userid:that.data.logs.userid,
  action:'open'

    },
   success: function(res)
    {

     console.log("打开成功")
    console.log(res.data)

    that.setData({

  bolstatus:res.data

    })

    },
  
  })

}
else
{
  wx.request({
    url: 'https://www.titwdj.cn/BorrowBook/SetReferStatus',
    data: {

  userid:that.data.logs.userid,
  action:'close'
    },
  
    success: function(res)
    {
      console.log("关闭成功")
    console.log(res.data)
    that.setData({

  bolstatus:res.data

    })
    }
  
  })


}
},





//设置频率
submit:function(e)
{

var that=this

 var current=e.currentTarget.dataset

   console.log(current.value)



wx.request({


  url: 'https://www.titwdj.cn/BorrowBook/ChangeP',
  data: 
  {

   userid:that.data.logs.userid,
   p:current.value

  },
  
  success: function(res)
  {
  console.log("修改成功")
  console.log(res.data)
  that.setData({

     bolstatus:res.data,
      

  })


  },
 
})




},





//设置归还提醒

 switch2Change: function (e)
{

var that=this
console.log(e.detail.value)


if(e.detail.value)
{

  wx.request({

    url: 'https://www.titwdj.cn/BorrowBook/SetBorrowWarnStatus',


    data: 
    {

  userid:that.data.logs.userid,
  action:'open'

    },
   success: function(res)
    {

     console.log("打开成功")
    console.log(res.data)
    that.setData({

  bolstatus:res.data

    })

    },
  
  })

}
else
{
  wx.request({
    url: 'https://www.titwdj.cn/BorrowBook/SetBorrowWarnStatus',
    data: {

  userid:that.data.logs.userid,
  action:'close'
    },
  
    success: function(res)
    {
      console.log("关闭成功")
    console.log(res.data)
    that.setData({

  bolstatus:res.data

    })
    }
  
  })


}


  },




  onShow:function()
  {
  
    var that=this;

 try {
  var value = wx.getStorageSync('key')
  if (value) {
   
that.setData({
bol:true,
logs:value

})

  }
} 
catch (e) 
{
  
}


if(value)
{


wx.request({


  url: 'https://www.titwdj.cn/BorrowBook/ReferMessageBol',

  data: {

  userid:that.data.logs.userid

  },
 
  success: function(res)
  {
   
   console.log(res.data)
   if(res.data=='0')
   {
that.setData({

referstatus:true

})


   }
   else{

that.setData({

referstatus:false

})


   }


  }
  
})




//获取设置的状态

wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/getUserSetStatus',

  data: {
    userid:that.data.logs.userid
  },
 
  success: function(res)
  {
   
   console.log(res.data);

   that.setData({

bolstatus: res.data,


   })

   if(res.data.referstatus=='1')
   {
that.setData({

  display1:true
})

   }

  },

})


}//if

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




noshare:function()
{

wx.showModal({
  title: '提示',
  content: '请先上传个人二维码!',
  success: function(res) {
    if (res.confirm) {
      console.log('用户点击确定')

wx.navigateTo({

  url: '../mynews/mynews',
  
 
})


    } 
    else if (res.cancel) {
      console.log('用户点击取消')
    }
  }
})

},



nosharenolocation:function()
{

wx.showModal({
  title: '提示',
  content: '请先上传个人二维码和个人位置!',
  success: function(res) {
    if (res.confirm) {
      console.log('用户点击确定')

wx.navigateTo({

  url: '../mynews/mynews',
  
 
})


    } 
    else if (res.cancel) {
      console.log('用户点击取消')
    }
  }
})

},

nolocation:function()
{


wx.showModal({
  title: '提示',
  content: '请先上传个人位置!',
  success: function(res) {
    if (res.confirm) {
      console.log('用户点击确定')

wx.navigateTo({

  url: '../mynews/mynews',
  
 
})


    } 
    else if (res.cancel) {
      console.log('用户点击取消')
    }
  }
})




},




//扫码分享
sharebookscan :function()
{


var that=this

wx.scanCode({
  success: (res) => 
  {
    console.log(res.scanType)

if(res.scanType=='EAN_13')
{

  wx.request({
    url: 'https://api.douban.com/v2/book/isbn/9787115299710',
    data: {},
    

    success: function(res){
  

  if(res.data.code=='6000')
  {
  
  
   wx.showToast({
  title: '抱歉,书库暂时没有该图书',
  icon: 'success',
  duration: 5000
})

  }
  else{
     
       console.log(res.data);
       console.log(res.data);
        console.log(res.data.summary);
        console.log(res.data.title);
        console.log(res.data.images.large);

          var array=res.data.author
          var arr=''

          for(var i=0;i<array.length;i++)
          {

           arr+=array[i]+"/"
          }
          console.log(arr)

   wx.request({
     url: 'https://www.titwdj.cn/BorrowBook/AddShareBook',
    
     data: {

    userid:that.data.logs.userid,
    booktitle:res.data.title,
    author:arr,
    isbn:res.data.isbn13,
    publish:res.data.publisher,
    cash:res.data.price,
    summary:res.data.summary,
    pagenum:res.data.pages,
    publishdate:res.data.pubdate

     },
   
   method: 'POST',


header: {
      'content-type': 'application/x-www-form-urlencoded'
      
  },
     success: function(res)
     {


if(res.data=="success")
{
wx.showToast({
  title: '分享书籍成功!',
  icon: 'success',
  duration: 3000
})

}
else
{
wx.showToast({
  title: '你已经分享过该书籍!',
  icon: 'success',
  duration: 3000
})

}
     },
    
   })






  }
    

    },
  
  })



}
else
{
wx.showToast({
  title: '请扫描正确的ISBN',
  icon: 'success',
  duration: 3000
})

}
  }
})


},

exit:function()
{

 wx.showModal({
  title: '提示',
  content: '确定登出!',
  success: function(res) {
    if (res.confirm) {
      console.log('用户点击确定')

try {
  wx.removeStorageSync('key')
} catch (e) {
  
}


wx.reLaunch({

  url: '../index/index',
 
})
  
    } else if (res.cancel)
     {
      console.log('用户点击取消')
    }
  }
})



}

})
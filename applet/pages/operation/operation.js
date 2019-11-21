// pages/operation/operation.js

var app = getApp()
Page({
  data:{
   one:false,
   tow:false,
   three:false,
   four:false,
   bookid:'',
   logs:[],
  borrowlist:'',
   returnlist:'',
   orderlist:'',
   returnhistroy:'',
   codetype:'',
   formbol:false,
   warnbol:false,
   currentTab:'',
   grouplist:'',
   groupmember:'',
   groupmain:'',
   clicentheight:0,
   currentTab:[false,false,false]

},
 swichNav: function (e) 
 {  
   
        console.log(e);  
        var that = this;  
        if (this.data.currentTab === e.target.dataset.current) {  
            return false;  
        } else {  
            that.setData({  
                currentTab: e.target.dataset.current,  
            })  
        }  
    },  

comment:function(e)
{

var current=e.currentTarget.dataset
wx.navigateTo({
  url: '../evaluateborrow/evaluateborrow?bookid='+current.bookid+'&borrowtableid='+current.id,

})

},

      swiperChange: function (e) 
      {  
        var that=this
        
        this.setData({  
            currentTab: e.detail.current,  
        })  

  console.log(e.detail.current)

//用户点击了待借栏
  if(that.data.currentTab=='0')
  {
    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/ShowBorrowLan',

      data: {

        userid: that.data.logs.userid
      },

      success: function (res) {
        console.log(res.data)
        that.setData({
          borrowlist: res.data
           })
           },
})
}
//还书区
 if(that.data.currentTab == '1')
{

   wx.request({
     url: 'https://www.titwdj.cn/BorrowBook/ShowBorrowServlet',

     data: {

       userid: that.data.logs.userid
     },

     success: function (res) {
       console.log(res.data)
       that.setData({

         returnlist: res.data

       })

     },

   })
}


  //记录
  if (that.data.currentTab == '3')
{
    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/ShowReturnServlet',
      data: {

        userid: that.data.logs.userid

      },

      success: function (res) {


        console.log(res.data);

        that.setData({

          returnhistroy: res.data


        })
      },
    })
}



},  

      //如果没有登录跳转到登录页面
    onShow: function () {
        // 页面渲染完成
        var that = this;
        wx.getSystemInfo({
  success: function(res) {
    that.setData({
      
    clicentheight:res.windowHeight-120

    })
  

  }
})
     
        try {
          var value = wx.getStorageSync('key')
          if (value) {
          
            that.setData({
              logs: value

            })
            that.setData({

              warnbol: false

            })
          }
          else {


            that.setData({

              warnbol: true

            })
            console.log("你好" + that.data.warnbol)

          }
        }
        catch (e) {
        }

if(value)
{


  try {
    var op = wx.getStorageSync('key2')
    if (op) {
      console.log("指点点过了tab")
      that.setData({
        currentTab: op
 })

    }
    else {
      that.setData({

        currentTab: '0'

      })

    }
  }
  catch (e) {
  }


  wx.request({
    url: 'https://www.titwdj.cn/BorrowBook/ShowBorrowLan',

    data: {

      userid: that.data.logs.userid
    },

    success: function (res) {
      console.log(res.data)
      that.setData({
        borrowlist: res.data
      })
    },
  })

}

      },





corslore2:function(){
  this.setData({
      formbol: false
    })
},



confire: function()
{
  wx.navigateTo({

    url: '../register/register',


  })

},

cancel:function()
{
  wx.reLaunch({
    url: '../index/index',
  })


},


scan1:function()
{

var that=this

wx.scanCode({
  success: (res) => {
    console.log(res.result)
    console.log(res.scanType)


    that.setData({

    bookid:res.result

    })
    

if(res.scanType=='QR_CODE')
 {
 console.log(res.result.substring(0,1))

 if (res.result.substring(0, 1)=='t')
 {
console.log("转借二维码")
   console.log("id" + res.result.substring(14, res.result.length))
wx.navigateTo
({
    url: '../transmitborrowpay/transmitborrowpay?id='+res.result.substring(14,res.result.length),
})

 }
 else
 {

   console.log("长度"+res.result.length)
   if (res.result.length==7)
   {
    that.setData({
   formbol:true
    })
   }
   else
   {
     wx.showToast({
       title: '请扫描正确的图书二维码！',
       icon: 'success',
       duration: 3000
     })


   }

 }


   
 }
 else if(res.scanType=='EAN_13')
 {

    
   

  wx.request({
    url: 'https://www.titwdj.cn/BorrowBook/ScanIsbnServlet',
    data: {

   isbn:res.result

    },
    
    success: function(res)
    {


console.log(res.data)
      if(res.data=="failure")
    {

  wx.showToast({
  title: '书库无此藏书！',
  icon: 'success',
  duration: 3000
    })


}
else
{




wx.navigateTo({
     url: '../particulars/particulars?bookid='+res.data.bookid+"&advice="+res.data.categoryid,
   
     })

}
     

    },
   
  })


 }

  }
})

},





bindsubmit:function(e)
{
    var that=this


   console.log(e.detail.formId)
   console.log(that.data.logs.userid)
  



   wx.request({
     url: 'https://www.titwdj.cn/BorrowBook/AddBorrowMain',
     data:
      {

       userid:that.data.logs.userid,
       formId:e.detail.formId,
       bookid:that.data.bookid
     },
   
     success: function(res)
     {
      console.log(res.data);

if(res.data=="1")
  {
  wx.showToast({
  title: '借书失败,该图书已经被借出!',
  icon: 'success',
  duration: 3000
   })

  }
  

  if(res.data=="borrowlan")
  {
  wx.showToast({
    title: '该图书已经添加到待借图书!',
  icon: 'success',
  duration: 3000
   })

  }


   if(res.data=="2")
  {

 wx.showToast({
  title: '借书失败,请扫码正确的图书二维码!',
  icon: 'success',
  duration: 3000
  })

  }


    if(res.data=="orderfail")
  {

wx.showToast({
  title: '借书失败,该图书已经被预订!',
  icon: '../../images/321.png',
  duration: 3000

})
  }

    if(res.data=="ordersuccess")
  {

wx.showToast({
  title: '预订图书借出成功,借书二维码已生成!',
  icon: 'success',
  duration: 3000

})
  }



    if(res.data=="borrowflow")
  {

wx.showToast({
  title: '借书失败,一次只能借两本!',
  icon: 'success',
  duration: 3000

})
  }


    if(res.data=="success")
  {

wx.showToast({
  title: '已添加到借书单，请尽快完成借书!',
  icon: 'success',
  duration: 3000

})
  }

  that.setData({

    formbol: false

  })



     },

     
    
   })

},


//如果点击了借书栏则发送请求

  scan:function()
  {

    var that =this

  
  },



//如果点击了还书栏则发送请求
  boorow:function()
  {

    var that =this
console.log("我打开了已借图书")
     this.setData({
      tow:!this.data.tow
    })

},



//用户要进行还书
returnbook:function(e)
{

var that=this
console.log(e)
var current=e.currentTarget.dataset;
console.log(current.id)

if(current.returnstatus=='0')
{
wx.showModal({
  title: '提示',
  content: '确认归还此书',
  success: function(res) 
  {
    if (res.confirm) 
    {
      console.log('用户点击确定')

   wx.request({
     url: 'https://www.titwdj.cn/BorrowBook/ConfirmReturnBook',
     data: {

       id:current.id
     },
     
     success: function(res)
     {
    console.log(res.data)

that.setData({

returnlist:res.data

})

    wx.showToast({
  title: '已添加到还书单!',
  icon: 'success',
  duration: 3000
})
     },
     
   })



    } 
    
    
    else if (res.cancel) {
      console.log('用户点击取消')
    }
  }
})

}
else{

wx.showToast({
  title: '该书已经添加到还书二维码',
  icon: 'success',
  duration: 3000
})


}
   

},


//点击预定了栏
order:function(){



var that=this
   this.setData({
      three:!this.data.three
    })

},




histroy:function(){

var that=this

   this.setData({
      four:!this.data.four
    })

},


deleteborrowlan:function(e)
{

var that=this

var  current=e.currentTarget.dataset;

console.log(current.id)

wx.showModal({
  title: '提示',
  content: '是否删除!',
  success: function(res) {
    if (res.confirm) {
      console.log('用户点击确定')

wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/DeleteBorrowLan',
  data: {

    id:current.id,
    userid:that.data.logs.userid
  },
 
  success: function(res)
  {

wx.showToast({
  title: '删除成功!',
  icon: 'success',
  duration: 3000
})

console.log(res.data)

    that.setData({

 borrowlist:res.data

    })

  },
 
})



    } else if (res.cancel) {
      console.log('用户点击取消')
    }
  }
})




},

deleteorderlan:function(e)
{
var that=this
console.log("我是来删除借书单的")
var  current=e.currentTarget.dataset;
console.log(current.id)
console.log(current.bookid)

var content='是否删除!';

if(current.status=='0')
{

content='是否解约!'
}
console.log(content)
console.log(current.status)

wx.showModal({
  title: '提示',
  content: content,
  success: function(res) {
    if (res.confirm) {
      console.log('用户点击确定')

      wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/DeleteOrderLine',

  data: {

userid:that.data.logs.userid,
id:current.id,


  },
  

  success: function(res)
  {
    console.log(res.data)
    wx.showToast({
  title: '解约成功!',
  icon: 'success',
  duration: 3000
})
    that.setData({

    orderlist:res.data

    })


  },
 
})
    } else if (res.cancel) {
      console.log('用户点击取消')
    }
  }
})


},

deletehistroy:function(e)
{

var that=this
console.log("删除")
var  current=e.currentTarget.dataset;
console.log(current.id)

wx.showModal({
  title: '提示',
  content: '是否删除!',
  success: function(res) {
    if (res.confirm) {
      console.log('用户点击确定')

      wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/DeleteBorrowHistroy',

  data: {

userid:that.data.logs.userid,
id:current.id,


  },
  

  success: function(res)
  {
    console.log(res.data)
    wx.showToast({
  title: '删除成功!',
  icon: 'success',
  duration: 3000
})
    that.setData({

    returnhistroy:res.data

    })


  },
 
})
    } else if (res.cancel) {
      console.log('用户点击取消')
    }
  }
})
},


borrowlanbyid:function(e)
{
var current=e.currentTarget.dataset;
var that=this;
var id=current.id
console.log(current.id)

wx.navigateTo({
  url: '../borrowlandetail/borrowlandetail?borrowlanid='+id,
  
})



},
onUnload: function()
{

  var that=this
  try {
    
    wx.setStorageSync('key2', that.data.currentTab)

  }
  catch (e)
  { }  

},
onHide: function()
{
  var that=this
  console.log("我来了")
  try {
    console.log("我来了2")
    console.log(that.data.currentTab)
    wx.setStorageSync('key2', that.data.currentTab)

  }
  catch (e)
  { }  

}


})
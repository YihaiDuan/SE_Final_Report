Page({
data:{
borrowdetail:'',
borrowid:'',
bol:true,
logs:'',
id:'',
combol:false,
money:'',
},

onLoad:function(options)
{
var that=this

that.setData({

id:options.id

})

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

console.log("userid"+that.data.logs.userid)
console.log("你好")

wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/getTransmitBorrowPay',
  data: 
  {
id:that.data.id
  },

  method: 'GET',

  success: function(res)
  {
    console.log(res.data)

    if(res.data!='610')
    {
    that.setData({

      borrowdetail:res.data
    })
    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/getUserInformationServlet',
      data: {
        userid: that.data.logs.userid
      },
      success: function (res) {
        console.log(res.data)
        that.setData({
          money: res.data.money
        })

        if (parseInt(that.data.money) >= parseInt(that.data.borrowdetail.cash)) {
          that.setData({
            combol: true
          })
        }
        else {
          that.setData({
            combol: false
          })
        }
      }
    })
    }
    else
    {
      that.setData({

 bol:false

      })
    }
  
  },
 
})





},


pay:function(e)
{
console.log(e)
console.log(e.detail.formId)
var that=this
var current=e.currentTarget.dataset
console.log(current.borrowid)
console.log(that.data.logs.userid)
 


wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/TransmitServlet',

  data:{
    
     borrowid:that.data.borrowdetail.id,
     userid:that.data.logs.userid,
     formid:e.detail.formId,
     cash:that.data.borrowdetail.cash,
     
  },

  success:function(res)
  {
    console.log(res.data)
    wx.redirectTo({
      url: '../paysuccess/paysuccess?action=tranpay',
    })
  }
})

},


})
Page({

data:{
logs:'',
paymoney:'0',
img_url: 'http://appuat.huihuishenghuo.com/img/',
number: 0,
len: 0,
paybgbol: false,
focus: false,
payboard:false,
month:'1',
paystatus:'',
grade:'',
},

onShow:function()
{
var that=this
  try {
    var value = wx.getStorageSync('key')
    if (value) {
      that.setData({
        logs: value

      })

    }
  }
  catch (e) {

  }



wx.request({
  url: 'http://139.199.23.184/BorrowBook/getUserInformationServlet',
  data:{
userid:that.data.logs.userid
  },
success:function(res)
{
console.log(res.data)
that.setData({

grade:res.data.grade,
paystatus:res.data.paystatus  
})
}
})
},
  hidden:function(){

    var that=this

    that.setData({
      payboard: false,
      paybgbol: false,
  
    })


  },

//支付框显示
pay:function(e)
{
  var that=this
  var current=e.currentTarget.dataset
console.log(current.paymoney)
console.log(current.month)
that.setData({
payboard:true,
  paybgbol:true,
  paymoney:current.paymoney,
  month:current.month
})

},

//支付密码输入
bindHideKeyboard: function (e) 
{

  var that=this
  var lens = e.detail.value.length;
  console.log(lens)
  this.setData({
    len: lens
  })

if(that.data.len=='6')
{
  console.log("密码输入完毕")
if(e.detail.value=='123456')
{
wx.request({
  url: 'http://139.199.23.184/BorrowBook/PayGrade',
  data:{
userid:that.data.logs.userid,totalmoney:that.data.paymoney,
grade:'1',
month:that.data.month
  },
  success:function()
  {
    wx.navigateTo({
      url: '../paysuccess/paysuccess?action=grade',
    })
  }
})


 



}
else
{
  //支付密码错误
  wx.showToast({
    title: '支付密码错误',
    icon: 'success',
    duration: 2000
  })
}
}


  if (e.detail.cursor == 6) {
    console.log(e.detail.cursor)
    wx.hideKeyboard();
    this.setData({
      focus: false,
      pay: false
    })
  }

},

showchahao: function (e) 
{

  var that=this
  wx.showModal({
    title: '提示',
    content: '确认放弃支付',
    success: function (res) {
      if (res.confirm) {
        console.log('用户点击确定')
        that.setData({
          payboard: false,
          paybgbol: false
        })
      } else if (res.cancel) {
        console.log('用户点击取消')
      }
    }
  })

  

},





})
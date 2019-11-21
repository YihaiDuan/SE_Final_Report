Page({

data:{
money:'0.00',
logs: '',
img_url: 'http://appuat.huihuishenghuo.com/img/',
number: 0,
len: 0,
paybgbol: false,
focus: false,
payboard: false,
payType: 'hb',

checkhb: true,
checkwx: false,
checkqb: false,
check4:false,
check5:false,
check6:false,
id: '',
totalmoney: 20,

},

onLoad: function (options) 
{
  var that = this
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
console.log(that.data.logs.userid)

  wx.request({
    url: 'https://www.titwdj.cn/BorrowBook/getUserWallet',
    data: {
      userid: that.data.logs.userid
    },
    success: function (res) {
     
      that.setData({

        money: res.data
      })

    }
  })
},




//选择支付金额
tapCheck: function (e) {
  var id = e.currentTarget.dataset.id;
  var that = this
  
  that.setData({
    id: id
  })
  console.log(id)
  if (id == '1') {
    this.setData({
      checkhb: true,
      checkwx: false,
      checkqb: false,
      check4: false,
      check5: false,
      check6: false,
      totalmoney:20 
    })
    
  } else if (id == '2') {
    this.setData({
      checkhb: false,
      checkwx: true,
      checkqb: false,
      check4: false,
      check5: false,
      check6: false,
      totalmoney: 50
  
    })

    
  }
   else if(id=='3'){
    this.setData({
      checkhb: false,
      checkwx: false,
      checkqb: true,
      check4:false,
      check5:false,
      check6:false,
      totalmoney: 100
  

    })
  }

  else if (id == '4') {
    this.setData({
      checkhb: false,
      checkwx: false,
      checkqb: false,
      check4: true,
      check5: false,
      check6: false,
      totalmoney: 150

    })
  }

  else if (id == '5') {
    this.setData({
      checkhb: false,
      checkwx: false,
      checkqb: false,
      check4: false,
      check5: true,
      check6: false,
      totalmoney: 200

    })
  }

  else if (id == '6') {
    this.setData({
      checkhb: false,
      checkwx: false,
      checkqb: false,
      check4: false,
      check5: false,
      check6: true,
      totalmoney: 248

    })
  }



},

noshow:function()
{
  var that=this

  that.setData({
    payboard: false,
    paybgbol: false

})

},
//支付框显示
pay: function (e) {
  var that = this

  var current = e.currentTarget.dataset
  console.log(current.paymoney)

  that.setData({
    payboard: true,
    paybgbol: true
  })
},

//支付密码输入
bindHideKeyboard: function (e) {

  var that = this
  var lens = e.detail.value.length;
  console.log(lens)
  this.setData({
    len: lens
  })

console.log("]]]]]]]]]]]]"+that.data.totalmoney)
  if (that.data.len == '6') {
    console.log("密码输入完毕")
    if (e.detail.value == '123456') 
    {
      //支付密码正确

var score='';
if(that.data.totalmoney=='20')
{
score=2
}
else if(that.data.totalmoney=='50')
{
score=5
}
else if (that.data.totalmoney == '100') {
  score = 10
}
else if (that.data.totalmoney == '150') {
  score = 15
}
else if (that.data.totalmoney == '200') {
  score = 20
}
else if (that.data.totalmoney == '248') {
  score = 25
}



      wx.request({
        url: 'https://www.titwdj.cn/BorrowBook/ReChargeServlet',
        data: {
          userid: that.data.logs.userid,
          recharge:that.data.totalmoney,
          score:score
        },

        success: function (res)
         {
          console.log(res.data)
/*
  wx.navigateTo({
    url: '../paysuccess/paysuccess?score='+score+"&action=wall",
   
  })*/

wx.redirectTo({
  url: '../paysuccess/paysuccess?score=' + score + "&action=wall",
})

          that.setData({
            payboard: false,
            paybgbol: false
          })
          that.setData({
            money: res.data
          })
        }
      })






    }
    else {
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

showchahao: function (e) {

  var that = this
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
// pages/mynews/mynews.js
Page({
  data:{
    
     logs:"",
     user:'',
     discountdetail:'',
     needscore:'',
     grade:'',
     paybol:true,
     score:''
      
  },

 
onLoad:function(options)
{
var that=this
console.log(options.countid)

that.setData({

  countid:options.countid
})

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
  url: 'https://www.titwdj.cn/BorrowBook/getUserInformationServlet',
  data: {
    userid: that.data.logs.userid
  },
  success: function (res) {
    console.log(res.data)
    that.setData({
      user: res.data,
      grade: res.data.grade,
      score:res.data.score
    })


  }
})


wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/getDiscountScoreByid',
  data:{
    countid:options.countid
  },
  success:function(res)
  {
    console.log(res.data)
that.setData({
    discountdetail:res.data
})

var discount=that.data.discountdetail.discount*100
console.log(discount)
console.log(that.data.grade)
if(that.data.grade=='0')
{
that.setData({
  needscore:discount
})
}
if(that.data.grade=='1')
{
  that.setData({
  needscore: discount*0.8
  })
}
console.log(that.data.needscore)


    if (parseInt(that.data.score) >= parseInt(that.data.needscore)) {
      that.setData({
        paybol: true
      })
    } else {
      that.setData({
        paybol: false
      })
    }
  }



  
})


  

},

pay:function()
{
var that=this

  wx.showModal({
    title: '提示',
    content: '确认兑换！',
    success: function (res) {
      if (res.confirm) 
      {
        wx.request({
          url: 'https://www.titwdj.cn/BorrowBook/BuyDisCount',

          data: {
            userid: that.data.logs.userid,
            countid: that.data.countid,
            needscore: that.data.needscore
          },
          success: function (res) {
            console.log(res.data)
   wx.redirectTo({
  url: '../paysuccess/paysuccess?action=scorechange',
})
       }
        })
      } else if (res.cancel) {

        console.log('用户点击取消')
      }
    }
  })

}


})
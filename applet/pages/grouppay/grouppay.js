Page({


data:{

bookid:'',
groupbook:'',
logs:'',
money:'',
caombol:false,
},

onLoad:function(options)
{

  var that=this
console.log(options.bookid);

that.setData({
bookid:options.bookid
})
wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/getBookinGroup',
   data:{
    bookid:options.bookid
   },
   success:function(res)
   {

    console.log(res.data)
    that.setData({
groupbook:res.data
     })
  
   }
})


 



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
    url: 'https://www.titwdj.cn/BorrowBook/getUserInformationServlet',
    data: {
      userid: that.data.logs.userid
    },
    success: function (res) {
      console.log(res.data)
      that.setData({
        money: res.data.money
      })

      if(parseInt(that.data.money)>=parseInt(that.data.groupbook.cash))
      {
that.setData({
combol:true
})
      }
      else
      {
        that.setData({
combol:false
        })
      }
    }
  })




},



//支付
pay: function (e) {

  var that = this;
console.log(that.data.groupbook.cash)

  console.log(e)
  console.log(e.detail.formId)
  that.setData({
    formId: e.detail.formId
  })

  wx.showModal({
    title: '提示',
    content: '确认支付!',
    success: function (res) {
      if (res.confirm) 
      {
     
        wx.request({
          url: 'https://www.titwdj.cn/BorrowBook/GroupMainServlet',
          data:
          {
            userid: that.data.logs.userid,
            bookid: that.data.bookid,
            cash: that.data.groupbook.cash,
            id: that.data.groupbook.id,
          },
          success: function (res) {
            console.log(res.data)


wx.redirectTo({
  url: '../groupsuccess/groupsuccess?id=' + res.data.id + "&bookid=" + that.data.bookid,
})
          }

        })
     
     
      } else if (res.cancel) {
        console.log('用户点击取消')
      }
    }
  })

},

})
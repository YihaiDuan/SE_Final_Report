Page({


data:{
bookid:'',
groupbook:'',
logs:'',
groupmainid:'',
money:'',
combol:false,
},

onLoad:function(options)
{

  var that=this
console.log(options.bookid);
console.log(options.groupmainid)

that.setData({
  bookid:options.bookid,
  groupmainid:options.groupmainid,
})



  wx.request({
    url: 'https://www.titwdj.cn/BorrowBook/getBookinGroup',
    data: {
      bookid: options.bookid
    },
    success: function (res) {

      console.log(res.data)
      that.setData({
        groupbook: res.data
      })

    }
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

  
},
onShow:function()
{
var that=this
  wx.request({
    url: 'https://www.titwdj.cn/BorrowBook/getUserInformationServlet',
    data: {
      userid: that.data.logs.userid
    },
    success: function (res) {
      console.log(res.data)
      console.log(res.data)
      that.setData({
        money: res.data.money
      })

      if (parseInt(that.data.money) >= parseInt(that.data.groupbook.cash)) {
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

},

pay:function()
{
  var that=this


//拼长id
console.log("拼长id"+that.data.groupmainid)

//团的基本信息id
  console.log("团的基本信息id"+that.data.groupbook.groupid)

  wx.showModal({
    title: '提示',
    content: '确认支付!',
    success: function (res) {
      if (res.confirm) {

        wx.request({
          url: 'https://www.titwdj.cn/BorrowBook/AddGroupMember',
          data:
          {
            userid: that.data.logs.userid,
            bookid: that.data.bookid,
            cash: that.data.groupbook.cash,
            groupmoreid: that.data.groupbook.groupid,
            groupmainid: that.data.groupmainid,
          },
          success: function (res) {
            console.log(res.data)

        


wx.redirectTo({
  url: '../groupsuccess/groupsuccess?id=' + that.data.groupmainid + "&bookid=" + that.data.bookid,
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
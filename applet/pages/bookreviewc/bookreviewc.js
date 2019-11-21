Page({
  data:{
    dynamicdetail:'',
    logs:'',
    dynamicid:'',
    commentlist:'',
    describe:'',
    commentid:'',
    otherid:'',
    nickname:'',
    formbol:false,
    click:'',
    content: '',
    dialogue: false,
    bol:false,
    dynamicid:'',
    admirelist:'',
  },

   
onLoad:function(options)
{

    var that=this;

console.log(options.dynamicid)

that.setData({
dynamicid:options.dynamicid
})


},



onShow :function()
{

var that=this;

 try {
  var value = wx.getStorageSync('key')
  if (value)
   {
that.setData({
logs:value,
bol:true,
})

console.log("登录成功")
console.log(that.data.bol)
  }
}
 catch (e) {
  
}

if(value)
{
  //获取回复和互评列表
  wx.request({
    url: 'https://www.titwdj.cn/BorrowBook/getCommentbyidByUser',

    data: {
      dynamicid: that.data.dynamicid,
      userid:that.data.logs.userid
    },
    success: function (res) {
      console.log(res.data)

      that.setData({
        commentlist: res.data
      })
    }

  })


}

else
{
 
  //获取回复和互评列表
  wx.request({
    url: 'https://www.titwdj.cn/BorrowBook/getCommentbyid',

    data: {
      dynamicid: that.data.dynamicid
    },
    success: function (res) {
      console.log(res.data)

      that.setData({
        commentlist: res.data
      })
    }

  })

}

},





  mainsubmit:function(e)
  {
var that=this
  console.log(e)
  var values=e.detail.value
  console.log(values.describ)
if(values.describ=='')
{
  wx.showToast({
    title: '输入内容不能为空!',
    icon: 'success',
    duration: 2000
  })

}
else
{
    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/AddDynamicComment',
      data: {
        userid: that.data.logs.userid,
        describ: values.describ,
        dynamicid: that.data.dynamicid
      },

      method: 'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },

      success: function (res) {
        wx.showToast({
          title: '发送成功',
          icon: 'success',
          duration: 2000
        })

        console.log(res.data)

        that.setData({
          commentlist: res.data
        })
        that.setData({
          describ: '',
        })
      },
    })
}
  },

replyshow:function(e)
{
  var that=this
var current=e.currentTarget.dataset


  console.log(e)
  console.log(current.commentid)
  console.log(current.otherid)
  console.log(current.nickname)
  console.log(current.dynamicid)
that.setData({
commentid:current.commentid,
otherid:current.otherid,
nickname:current.nickname,
dynamicid:current.dynamicid
})

  that.setData({
    formbol: true,
    click:"回复 "+current.nickname+":"
})
},


replysend:function(e)
{
  var that=this
  var values=e.detail.value
  
console.log("回复"+that.data.nickname)

  if (values.describ == '') {
    wx.showToast({
      title: '输入内容不能为空!',
      icon: 'success',
      duration: 2000
    })

  }
  else
  {

     wx.request({
       url: 'https://www.titwdj.cn/BorrowBook/AddDynamicReply',
       data: {
     userid:that.data.logs.userid,
     describ:"回复"+that.data.nickname+values.describ,
     commentid:that.data.commentid,
     otherid:that.data.otherid,
       dynamicid:that.data.dynamicid
       },
       method: 'POST',
       header: {
         'content-type': 'application/x-www-form-urlencoded'
       },
      
       success: function(res) 
       {
console.log(res.data)
         that.setData({
           commentlist: res.data
         })
       },
       
     })
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




//点赞

Addadmire: function (e) {

  var that = this
  var current = e.currentTarget.dataset

  console.log(current.dynamicid)

  wx.request({
    url: 'https://www.titwdj.cn/BorrowBook/AddDynamicAdmireDetail',
    data: {

      userid: that.data.logs.userid,
      dynamicid: current.dynamicid,
      action: 'add'

    },

    success: function (res) {

      console.log(res.data)
      that.setData({
        dynamicdetail: res.data
      })


      wx.request({
        url: 'https://www.titwdj.cn/BorrowBook/ShowAdmireImg',
        data: {
          dynamicid: that.data.dynamicid
        },
        success: function (res) {

          console.log(res.data)
          that.setData({
            admirelist: res.data
          })

        }
      })

    },

  })
},


//撤销点赞
Deleteadmire: function (e) {
  var that = this
  var current = e.currentTarget.dataset

  console.log(current.dynamicid)


  wx.request({
    url: 'https://www.titwdj.cn/BorrowBook/AddDynamicAdmireDetail',
    data: {

      userid: that.data.logs.userid,
      dynamicid: current.dynamicid,
      action: 'delete'

    },

    success: function (res) {
      console.log(res.data)
      that.setData({
        dynamicdetail: res.data
      })

      wx.request({
        url: 'https://www.titwdj.cn/BorrowBook/ShowAdmireImg',
        data: {
          dynamicid: that.data.dynamicid
        },
        success: function (res) {

          console.log(res.data)
          that.setData({
            admirelist: res.data
          })

        }
      })
    },

  })

},


//关注
AddFan: function (e) {

  var that = this
  var current = e.currentTarget.dataset

  console.log(current.otherid)

  wx.request({
    url: 'https://www.titwdj.cn/BorrowBook/AddFanByDetail',
    data: {

      userid: that.data.logs.userid,
      otherid: current.otherid,
      action: 'add',
      dynamicid:current.dynamicid

    },

    success: function (res) {
      console.log(res.data)
      that.setData({
        dynamicdetail: res.data
      })

    },
  })
},

//取消关注
DeleteFan: function (e) {
  var that = this
  var current = e.currentTarget.dataset

  console.log(current.otherid)

  wx.request({
    url: 'https://www.titwdj.cn/BorrowBook/AddFanByDetail',
    data: {
      userid: that.data.logs.userid,
      otherid: current.otherid,
      action: 'delete',
      dynamicid: current.dynamicid
    },

    success: function (res) {
      console.log(res.data)
      that.setData({
        dynamicdetail: res.data
      })
    },
  })
},

onShareAppMessage: function (res) {
  if (res.from === 'button') {

    console.log(res.target)
  }
  return {
    title: '将动态转发到微信',
    path: '/page/dynamicdetail/dynamicdetail?id=123',
    success: function (res) {
      console.log("转发成功")
    },
    fail: function (res) {
      // 转发失败
    }
  }
},

//图片预览
previewuserimages: function (e) {
  var current = e.currentTarget.dataset
  var that = this
  wx.previewImage({
    current: "https://www.titwdj.cn/BorrowBook/images/" + current.url,
    urls: [
      "https://www.titwdj.cn/BorrowBook/images/" + current.url,
    ],

    success: function (res) {
      console.log(res);
    },
    //也根本不走
    fail: function () {
      console.log('fail')
    }
  })

},




//点赞评论的

AddCommentadmire: function (e) {

  var that = this
  var current = e.currentTarget.dataset

  console.log(current.dynamicid)
  
  wx.request({
    url: 'https://www.titwdj.cn/BorrowBook/AddCommentAdmire',
    data: {

      userid: that.data.logs.userid,
      dynamicid: current.dynamicid,
      commentid:current.commentid,
      action: 'add'

    },

    success: function (res) {

      console.log(res.data)
      that.setData({
        commentlist: res.data
      })

    },

  })
},


//撤销点赞评论的
DeleteCommentadmire: function (e) {
  var that = this
  var current = e.currentTarget.dataset

  console.log(current.dynamicid)
console.log(current.commentid)

  wx.request({
    url: 'https://www.titwdj.cn/BorrowBook/AddCommentAdmire',
    data: {

      userid: that.data.logs.userid,
      dynamicid: current.dynamicid,
      commentid:current.commentid,
      action: 'delete'

    },

    success: function (res) {
      console.log(res.data)
      that.setData({
        commentlist: res.data
      })

    },

  })

},
  
})

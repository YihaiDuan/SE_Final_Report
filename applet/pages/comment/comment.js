// pages/comment/comment.js
Page({
  data:{
   block:true,
    display2:false,
    bollan:false,
    box:false,
    comment:'',
    bookid:'',
    nickname:"",
    userid:"",
    logs:[],
    nickname:'',
    bol:false,
    commentnum:'',
    content:'',
    formbol:'',
    click:'',
    topcomment:'',
    commentnum:'',
    showtopbol:'false',
  },

   
onLoad:function(options){

    var that=this;

     console.log(options.bookid)
    that.setData({

      bookid:options.bookid

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
  }
}
 catch (e) {
  
}

 wx.showLoading({
   title: '加载中',
 }), 

 wx.request({
   url: 'https://www.titwdj.cn/BorrowBook/ShowCommentOne',
   data: {
     bookid: that.data.bookid
   },
   success: function (res) {
     console.log(res.data)
     that.setData({
       commentnum: res.data
     })

     setTimeout(function () {
       wx.hideLoading()
     }, 1000)


   },
 })


if(value)
{
  
  //获取精彩评论
  wx.request({
    url: 'https://www.titwdj.cn/BorrowBook/TopCommentByUser',
    data: {
      userid: that.data.logs.userid,
      bookid: that.data.bookid
    },
    success: function (res) {
      console.log(res.data)
      that.setData({
        topcomment: res.data
      })

      if (res.data.size != 0) {
        for (var i = 0; i < that.data.topcomment.lenght; i++) {
          if (parseInt(that.data.topcomment[i].admirenum) >= 10) {
            that.setData({
              showtopbol: true
            })
            break;
          }
        }
      }

    },
  })



//获取最新评论
wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/AdmireBolServlet',
  data: {

   userid:that.data.logs.userid,
   bookid:that.data.bookid

  },
  
  success: function(res)
  {
     console.log(res.data)
       that.setData({

    comment:res.data

       })
  },
  
})


}

else
{


  //未登录获取精彩评论
  wx.request({
    url: 'https://www.titwdj.cn/BorrowBook/TopCommentNoUser',
    data: {
      bookid: that.data.bookid
    },
    success: function (res) {
      console.log(res.data)
      that.setData({
        topcomment: res.data
      })

      if (res.data.size != 0) {
        for (var i = 0; i < that.data.topcomment.lenght; i++) {
          if (parseInt(that.data.topcomment[i].admirenum) >= 10) {
            that.setData({
              showtopbol: true
            })
   break;
          }
        }
      }
    },
  })
//未登录获取最新评论
  wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/ShowCommentByBookid',
      data: {

      bookid:that.data.bookid
      },
     
    
      success: function(res){

     console.log(res.data)
       that.setData({

    comment:res.data

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

Addadmire:function(e)
{

  var that=this
  var current=e.currentTarget.dataset

 console.log(current.commentid)
 console.log(current.userid)

wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/AddAdmire',
  data: {

userid:that.data.logs.userid,
commentid:current.commentid,
bookid:that.data.bookid

  },
  
  success: function(res)
  {
console.log(res.data)

that.setData({
  comment:res.data
})
//获取精彩评论
wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/TopCommentByUser',
  data: {
    userid: that.data.logs.userid,
    bookid: that.data.bookid
  },
  success: function (res) {
    console.log(res.data)
    that.setData({
      topcomment: res.data
    })

    if (res.data.size != 0) {
      for (var i = 0; i < that.data.topcomment.lenght; i++) {
        if (parseInt(that.data.topcomment[i].admirenum) >= 10) {
          that.setData({
            showtopbol: true
          })
          break;
        }
      }
    }
  },
})

  },
 
})




},


//撤销点赞
Deleteadmire :function(e)
{
 var that=this
  var current=e.currentTarget.dataset

 console.log(current.commentid)
 console.log(current.userid)

wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/DeleteAdmire',
  data: {

userid:that.data.logs.userid,
commentid:current.commentid,
bookid:that.data.bookid

  },
  
  success: function(res)
  {
console.log(res.data)
that.setData({
  comment:res.data
})
//获取精彩评论
wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/TopCommentByUser',
  data: {
    userid: that.data.logs.userid,
    bookid: that.data.bookid
  },
  success: function (res) {
    console.log(res.data)
    that.setData({
      topcomment: res.data
    })

    if (res.data.size != 0) {
      for (var i = 0; i < that.data.topcomment.lenght; i++) {
        if (parseInt(that.data.topcomment[i].admirenum) >= 10) {
          that.setData({
            showtopbol: true
          })
          break;
        }
      }
    }
  },
})
  },
 
})



},



replyshow: function (e) {
  var that = this
  var current = e.currentTarget.dataset


  console.log(e)
  console.log(current.commentid)
  console.log(current.otherid)
  console.log(current.nickname)
  that.setData({
    commentid: current.commentid,
    otherid: current.otherid,
    nickname: current.nickname,
    bookid: current.bookid
  })

  that.setData({
    formbol: true,
    click: "回复 " + current.nickname + ":"
  })
},


replysend: function (e) {
 
  var that = this
  var values = e.detail.value

  console.log("回复" + that.data.nickname)
if(values.content=='')
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
    url: 'https://www.titwdj.cn/BorrowBook/ReplyComment',
    data: {
      bookid: that.data.bookid,
      userid: that.data.logs.userid,
      nickname: that.data.logs.nickname,
      content: "回复" + that.data.nickname + "的评论:" + "  " + e.detail.value.content,
      commentid: that.data.commentid,
      otherid: that.data.otherid
    },
    method: 'POST',
    header: {
      'content-type': 'application/x-www-form-urlencoded'
    },

    success: function (res) {
      console.log(res.data)
      that.setData({
        comment: res.data
      })

      wx.request({
        url: 'https://www.titwdj.cn/BorrowBook/ShowCommentOne',
        data: {
          bookid: that.data.bookid
        },
        success: function (res) {
          console.log(res.data)
          that.setData({
            commentnum: res.data
          })
        },
      })
    },

  })
}
},

//评论提交
CommentSubmit:function(e)
{

var that=this
console.log(e.detail.value.content)
if(e.detail.value.content=='')
{
 
    wx.showToast({
      title: '输入内容不能为空!',
      icon: 'success',
      duration: 2000
    })


  
}
else{
  wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/AddComment',
  data: {

 userid:that.data.logs.userid,
 nickname:that.data.logs.nickname,
 content:e.detail.value.content,
 bookid:that.data.bookid

  },
  method: 'POST',
  header: {
    'content-type': 'application/x-www-form-urlencoded'
  },
  success: function(res)
  {
  wx.showToast({
  title: '发送成功',
  icon: 'success',
  duration: 2000
})
  console.log(res.data)

that.setData({

  comment: res.data
})



  wx.request({
    url: 'https://www.titwdj.cn/BorrowBook/ShowCommentOne',
    data: {
      bookid: that.data.bookid
    },
    success: function (res) {
      console.log(res.data)
      that.setData({
        commentnum: res.data
      })




    },
  })

that.setData({

  content:'',

})


},

})

}




},










  changebox:function(){
 this.setData({
      box:false
    })
  },



  



  change:function(e)
  {
    console.log(e.currentTarget.dataset.nickname)
    console.log(e.currentTarget.dataset.userid)

     var that=this;
      that.setData({
     box:!that.data.box,
     nickname:e.currentTarget.dataset.nickname,
     userid:e.currentTarget.dataset.userid
    })
    console.log(that.data.nickname)
  },

  changeform:function(){
     this.setData({
      display2:!this.data.display2
    })
  },


  
})

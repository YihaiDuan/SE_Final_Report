Page({
  data:{
    dynamiclist: '',
    logs: '',
    dynamicid: '',
    commentlist: '',
    describe: '',
    commentid: '',
    otherid: '',
    nickname: '',
    formbol: false,
    click: '',
    content: '',
    dialogue: false,
    bol: false,
    dynamicid: '',
    admirelist: '',
    commentnblist: '',
    currentTab: '0',  
    bol:false,
    evaluateformbol:false,
    mesbol:'',
    noticelist:''

  },


  swichNav: function (e) {
  
    console.log(e);
    var that = this
    that.setData({
      currentTab: e.target.dataset.current,
    })  



    var that = this;
    var current = e.target.dataset.current
console.info(that.data.current);

  if(current=='1')
  {

    if(that.data.mesbol.bol1=='true')
    {
    //更新已经读的标志位
    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/UpdateReadStatus',

      data: {
        userid: that.data.logs.userid,
        action: 'review'
      },
      success: function (res) {
        console.log("更新成功")
        wx.request({
          url: 'https://www.titwdj.cn/BorrowBook/ReadStatusServlet',
          data: {
            userid: that.data.logs.userid
          },
          success: function (res) {
            console.log(res.data)
            that.setData({
              mesbol: res.data
            })

          }

        })

      }

    })

    }
  }


  },
  swiperChange: function (e) {
    console.log(e);
    var that = this;
    this.setData({
      currentTab: e.detail.current,
    })

    if (that.data.currentTab == '0') {
      console.log("动态消息")
      //获取动态评论消息
      wx.request({
        url: 'https://www.titwdj.cn/BorrowBook/getDynamicMessage',
        data: {

          userid: that.data.logs.userid
        },


        success: function (res) {
          console.log(res.data)

          that.setData({
            dynamiclist: res.data
          })

        }
      })
   
      
    }
    else if (that.data.currentTab == '1')
     {

       console.log("评论消息")
      wx.request({
        url: 'https://www.titwdj.cn/BorrowBook/ShowMyReply',
        data: {
          userid: that.data.logs.userid
        },
        success: function (res) {
          console.log(res.data)
       
            that.setData({
              replylist: res.data
            })
            }
      })
    }
    else {
      console.log("系统通知")

      wx.request({
        url: 'https://www.titwdj.cn/BorrowBook/getNoticeServlet',

        success:function(res)
        {
          console.log(res.data)
          that.setData({
            noticelist:res.data
          })
        }
      })
    }


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


 //判断是否有消息
 wx.request({
   url: 'https://www.titwdj.cn/BorrowBook/ReadStatusServlet',
   data: {
     userid: that.data.logs.userid
   },
   success: function (res) {
     console.log(res.data)
     that.setData({
       mesbol: res.data
     })

   }

 })

//更新标志位
 if (that.data.mesbol.bol2 == 'true' || that.data.mesbol.bol3 == 'true') {
   wx.request({
     url: 'https://www.titwdj.cn/BorrowBook/UpdateReadStatus',

     data: {
       userid: that.data.logs.userid,
       action: 'dynamic'
     },
     success: function (res) {
       console.log("更新成功")
       wx.request({
         url: 'https://www.titwdj.cn/BorrowBook/ReadStatusServlet',
         data: {
           userid: that.data.logs.userid
         },
         success: function (res) {
           console.log(res.data)
           that.setData({
             mesbol: res.data
           })

         }

       })

     }

   })
 }


  //获取动态评论消息
  wx.request({
    url: 'https://www.titwdj.cn/BorrowBook/getDynamicMessage',
    data: {
   
      userid:that.data.logs.userid
    },


    success: function (res) {
      console.log(res.data)

      that.setData({
        dynamiclist: res.data
      })

    }
  })

},



replyshow: function (e) {
  var that = this
  var current = e.currentTarget.dataset


  console.log(e)
  console.log("显示对话框")
  console.log(current.commentid)
  console.log(current.otherid)
  console.log(current.nickname)
  console.log(current.dynamicid)
  that.setData({
    commentid: current.commentid,
    otherid: current.otherid,
    nickname: current.nickname,
    dynamicid: current.dynamicid
  })

  that.setData({
    formbol: true,
    click: "回复 " + current.nickname + ":"
  })
},


evaluatereplyshow: function (e) {
  var that = this
  var current = e.currentTarget.dataset


  console.log(e)
  console.log("显示对话框")
  console.log(current.commentid)
  console.log(current.otherid)
  console.log(current.nickname)
  console.log(current.bookid)
  that.setData({
    commentid: current.commentid,
    otherid: current.otherid,
    nickname: current.nickname,
    bookid: current.bookid
  })

  that.setData({
    evaluateformbol: true,
    click: "回复 " + current.nickname + ":"
  })
},


evaluatereplysend: function (e) {
  var that = this
  var values = e.detail.value

  console.log("回复" + that.data.nickname)
if(values.content=='')
{
  wx.showToast({
    title: '回复内容不能为空!',
    icon: 'success',
    duration: 2000
  })

}
else
{


  wx.request({
    url: 'https://www.titwdj.cn/BorrowBook/ReplyComment',
    data: {
      userid: that.data.logs.userid,
      content: "回复" + that.data.nickname + values.content,
      commentid: that.data.commentid,
      otherid: that.data.otherid,
      bookid: that.data.bookid,
      nickname:that.data.logs.nickname
    },
    method: 'POST',
    header: {
      'content-type': 'application/x-www-form-urlencoded'
    },

    success: function (res) {
      wx.request({
        url: 'https://www.titwdj.cn/BorrowBook/ShowMyReply',
        data: {
          userid: that.data.logs.userid
        },
        success: function (res) {
          console.log(res.data)
    
            that.setData({
              replylist: res.data
            })
          
        }
      })


    },

  })
}
},
replysend: function (e) {
  var that = this
  var values = e.detail.value

  console.log("回复" + that.data.nickname)
if(values.describ=='')
{

  wx.showToast({
    title: '回复内容不能为空!',
    icon: 'success',
    duration: 2000
  })
}
else
{


  wx.request({
    url: 'https://www.titwdj.cn/BorrowBook/AddDynamicReply',
    data: {
      userid: that.data.logs.userid,
      describ: "回复" + that.data.nickname + values.describ,
      commentid: that.data.commentid,
      otherid: that.data.otherid,
      dynamicid: that.data.dynamicid
    },
    method: 'POST',
    header: {
      'content-type': 'application/x-www-form-urlencoded'
    },

    success: function (res) {
      
      wx.request({
        url: 'https://www.titwdj.cn/BorrowBook/getDynamicMessage',
        data: {

          userid: that.data.logs.userid
        },


        success: function (res) {
          console.log(res.data)

          that.setData({
            dynamiclist: res.data
          })

        }
      })
    
    },

  })
}
},



onUnload:function()
{
  console.log("外我进来了动态消息")
    //更新已经读的标志位
var that=this
    if (that.data.mesbol.bol2 == 'true' || that.data.mesbol.bol3 == 'true') {

      console.log("我进来了动态消息")
      wx.request({
        url: 'https://www.titwdj.cn/BorrowBook/UpdateReadStatus',

        data: {
          userid: that.data.logs.userid,
          action: 'dynamic'
        },
        success: function (res) {
          console.log("更新成功")
          wx.request({
            url: 'https://www.titwdj.cn/BorrowBook/ReadStatusServlet',
            data: {
              userid: that.data.logs.userid
            },
            success: function (res) {
              console.log(res.data)
              that.setData({
                mesbol: res.data
              })

            }

          })

        }

      })
    }
  

},

onHide:function(){
  console.log("外我进来了动态消息")
  //更新已经读的标志位
var that=this
  if(that.data.mesbol.bol2 == 'true' || that.data.mesbol.bol3 == 'true') {

    console.log("我进来了动态消息")
    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/UpdateReadStatus',

      data: {
        userid: that.data.logs.userid,
        action: 'dynamic'
      },
      success: function (res) {
        console.log("更新成功")
        wx.request({
          url: 'https://www.titwdj.cn/BorrowBook/ReadStatusServlet',
          data: {
            userid: that.data.logs.userid
          },
          success: function (res) {
            console.log(res.data)
            that.setData({
              mesbol: res.data
            })

          }

        })

      }

    })
  }



}


})

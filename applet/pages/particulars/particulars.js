// pages/particulars/particulars.js
Page({
  data: {
    detail: '',
    bookid: '',
    advice: '',
    comment: '',
    logs: [],
    time: [],
    ordertime: '',
    index: '4',
    indexvalue: '',
    orderbol: false,
    warnbol: false,
    detail2: '',
    bol: false,
    jianjie: false,
    daodu: false,
    shuping: false,
    hanzibol: true,
    advice2: '',
    free: '',
    grouplist: '',
    grouplistbol: 'false',
    maxgroupbol: 'false',
    starimg:'',
    comparelist:'',
    currentTab:[true,true,true],
    list1:'',
    list2:'',
    list3:''

  },
  swichNav:function(e){
    let index =  e.target.dataset.current;
    let current = this.data.currentTab;
    current[index]=!current[index];
    this.setData({
     currentTab:current


    })


  },
  corslore: function () {

    this.setData({
      warnbol: false
    })

  },

  corslore2: function () {
    this.setData({
      orderbol: false
    })
  },



  onLoad: function (options) 
  {
    var that = this;




    that.setData({

      bookid: options.bookid,
      advice: options.advice

    })

    wx.showLoading({
      title: '加载中',
    }),
      console.log(options.advice)

    var that = this

    try {
      var value = wx.getStorageSync('key')


      if (value) {
        that.setData({
          logs: value,
          bol: true

        })

      }
    }
    catch (e) {

    }






    

// wx.request({
//   url: 'https://www.titwdj.cn/BorrowBook/getComparePrice',
// data:{
//   bookid:options.bookid
// },
// success:function(res)
// {
//   console.log(res.data)
// that.setData({
// comparelist:res.data
// })

// }
// })

wx.request({
          url: 'https://www.titwdj.cn/BorrowBook/AdviceServlet', //仅为示例，并非真实的接口地址

          data: {
           // advice: that.data.advice,
            bookid:that.data.bookid
          },
          header: {
            'content-type': 'application/json'
          },
          success: function (res) {

            console.log(res.data);
            that.setData({
              advice2: res.data

            })
          }
        })




  },



  onShow: function () {
    var that = this
    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/ShowCommentOne',
      data: {

        bookid: that.data.bookid

      },

      success: function (res) {
        console.log(res.data)

        that.setData({

          comment: res.data

        })


      },

    })
    console.log("onshow中的bol" + that.data.bol)
    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/BookDetailServlet', //仅为示例，并非真实的接口地址
      data: {
        bookid: that.data.bookid
      },
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {

        console.log(res.data);
        that.setData({
          advice: res.data.typeid
        })

        

      }
    })
    if (that.data.bol) {

      wx.request({
        url: 'https://www.titwdj.cn/BorrowBook/BookDetailByUser',
        data: {
          userid: that.data.logs.userid,
          bookid: that.data.bookid
        },
        success: function (res) {
          console.log(res.data)
          that.setData({
            detail: res.data
          })

          var score = parseFloat(res.data.score) / 2;

          if (score == 0) {
            that.setData({
              starimg: '0.png'
            })

          }
          else if (score > 0 && score <= 0.5) {
            that.setData({
              starimg: '1.png'
            })
          }
          else if (score > 0.5 && score <= 1) {
            that.setData({
              starimg: '2.png'
            })

          }
          else if (score > 1 && score <= 1.5) {
            that.setData({
              starimg: '3.png'
            })
          }
          else if (score > 1.5 && score <= 2) {
            that.setData({
              starimg: '4.png'
            })
          }
          else if (score > 2 && score <= 2.5) {
            that.setData({
              starimg: '5.png'
            })
          }
          else if (score >= 2.5 && score <= 3) {
            that.setData({
              starimg: '6.png'
            })
          }
          else if (score > 3 && score <= 3.5) {
            that.setData({
              starimg: '7.png'
            })
          }
          else if (score > 3.5 && score <= 4) {
            that.setData({
              starimg: '8.png'
            })
          }
          else if (score > 4 && score <= 4.5) {
            that.setData({
              starimg: '9.png'
            })
          }
          else if (score > 4.5 && score <= 5) {
            that.setData({
              starimg: '10.png'
            })
          }

          console.log(that.data.starimg)



          setTimeout(function () {
            wx.hideLoading()
          }, 1000)
        },
      })

      wx.request({
        url: 'https://www.titwdj.cn/BorrowBook/ReferUserClick',
        data:
        {

          userid: that.data.logs.userid,
          categoryid: that.data.advice

        },

        success: function (res) {

          console.log("推荐成功" + res.data)


        },

      })



      wx.request({
        url: 'https://www.titwdj.cn/BorrowBook/UserActionServlet',
        data:{
         userid:that.data.logs.userid,
         bookid:that.data.bookid,
         action:"userbrowse"
        },
        success:function(res)
        {
     console.log("更新成功")


        }
      })
    }
    //未登录
    else {

      wx.request({
        url: 'https://www.titwdj.cn/BorrowBook/BookDetailServlet', //仅为示例，并非真实的接口地址
        data: {
          bookid: that.data.bookid
        },
        header: {
          'content-type': 'application/json'
        },
        success: function (res) {

          console.log(res.data);
          that.setData({
            detail: res.data
          })


          var score = parseFloat(res.data.score) / 2;

          if (score == 0) {
            that.setData({
              starimg: '0.png'
            })

          }
          else if (score > 0 && score <= 0.5) {
            that.setData({
              starimg: '1.png'
            })
          }
          else if (score > 0.5 && score <= 1) {
            that.setData({
              starimg: '2.png'
            })

          }
          else if (score > 1 && score <= 1.5) {
            that.setData({
              starimg: '3.png'
            })
          }
          else if (score > 1.5 && score <= 2) {
            that.setData({
              starimg: '4.png'
            })
          }
          else if (score > 2 && score <= 2.5) {
            that.setData({
              starimg: '5.png'
            })
          }
          else if (score >= 2.5 && score <= 3) {
            that.setData({
              starimg: '6.png'
            })
          }
          else if (score > 3 && score <= 3.5) {
            that.setData({
              starimg: '7.png'
            })
          }
          else if (score > 3.5 && score <= 4) {
            that.setData({
              starimg: '8.png'
            })
          }
          else if (score > 4 && score <= 4.5) {
            that.setData({
              starimg: '9.png'
            })
          }
          else if (score > 4.5 && score <= 5) {
            that.setData({
              starimg: '10.png'
            })
          }

          console.log(that.data.starimg)

          setTimeout(function () {
            wx.hideLoading()
          }, 1000)
        }
      })

    }
    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/ShowGroupMainByBookid',

      data: {
        bookid: that.data.bookid
      },

      success: function (res) {
        console.log(res.data)
        if (res.data != '0') {
          that.setData({
            grouplist: res.data,
            grouplistbol: true,
          })
          console.log(that.data.grouplistbol)
        }
      }

    })


    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/BolGroupMaxNum',

      data: {
        bookid: that.data.bookid,
      },
      success: function (res) {
        console.log("是否开团满" + res.data)
        if (res.data == '1') {
          that.setData({
            maxgroupbol: true
          })
        }
      }

    })
  },

share:function()
{
var that=this
wx.navigateTo({
  url: '../dynamicsend/dynamicsend?bookid=' + that.data.detail.bookid + "&booktitle=" + that.data.detail.booktitle,
})

},

  //用户点击了收藏
  collecting: function () {

    console.log("我点击了")

    var that = this
    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/AddCollectServlet',

      data:
      {

        userid: that.data.logs.userid,
        bookid: that.data.bookid,
        action: 'add'
      },

      success: function (res) {

        console.log(res.data)

        that.setData({

          detail: res.data

        })


      },

    })

  },

  //图书转发
  onShareAppMessage: function (){

    var that = this
    console.log(that.data.bookid)
    console.log(that.data.advice)
    return {
      title: '图书详情',
      path: 'pages/particulars/particulars?bookid=' + that.data.bookid + "&advice=" + that.data.advice,

      success: function (res) {
        wx.showToast({
          title: '分享成功！',
          icon: 'success',
          duration: 2000
        })
      },
      fail: function (res) {
        // 转发失败
      }
    }
  },

  //你已经收藏了
  collectingwarn: function () {

    var that = this
    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/AddCollectServlet',

      data:
      {

        userid: that.data.logs.userid,
        bookid: that.data.bookid,
        action: 'delete'
      },

      success: function (res) {

        console.log(res.data)
        that.setData({
          detail: res.data

        })


      },

    })

  },


  nologin: function () {


    wx.showModal({
      title: '提示',
      content: '你尚未登录,请先登录!',
      success: function (res) {
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



  warnhidden: function () {
    var that = this

    that.setData({
      warnbol: false
    })

  },


  warn: function () {
    wx.showToast({
      title: "你已经设置过有书提醒!",
      icon: 'success',
      duration: 5000
    })
  },

  warnsubmit: function (e) {

    var that = this;
    that.setData({
      warnbol: false
    })
    console.log(e.detail.formId)
    console.log(e)

    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/OnlineOrderServlet',
      data:
      {
        status: "0",
        userid: that.data.logs.userid,
        bookid: that.data.bookid,
        formId: e.detail.formId,
        action: 'warnorder'
      },

      success: function (res) {
        console.log(res.data)
        if (res.data == "fail") {
          wx.showToast({
            title: "你已经设置过此书的有书提醒!",
            icon: 'success',
            duration: 5000
          })

        }
        else {
          that.setData({
            detail: res.data,
          })
          wx.showToast({
            title: "当有用户还书会自动为你提醒!",
            icon: 'success',
            duration: 5000
          })
        }
      },
    })
  },

  maxwarn: function () {
    wx.showToast({
      title: '开团已满',
      icon: 'success',
      duration: 5000
    })
  },


//
outorder:function()
{
  wx.showToast({
    title: '预订失败,一次只能预订两本!',
    icon: 'success',
    duration: 3000
  })
},


//加入到借书栏
  bindsubmit: function (e) {
    var that = this
    console.log(e.detail.formId)
    console.log(that.data.logs.userid)
    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/AddBorrowLan',
      data:
      {
        userid: that.data.logs.userid,
        formId: e.detail.formId,
        bookid: that.data.bookid
      },
      success: function (res) {
        console.log(res.data);
        if (res.data == "borrowflow") {
          wx.showToast({
            title: '添加失败,一次只能添加两本!',
            icon: 'success',
            duration: 3000
          })
        }
        if (res.data == "success") {
          wx.showToast({
            title: '添加成功',
            icon: 'success',
            duration: 5000

          })
          wx.request({
            url: 'https://www.titwdj.cn/BorrowBook/BookDetailByUser',
            data: {
              userid: that.data.logs.userid,
              bookid: that.data.bookid
            },
            success: function (res) {
              console.log(res.data)
              that.setData({
                detail: res.data
              })
            },
          })

        }
        if (res.data == "failure") {
          wx.showToast({
            title: '已无藏书',
            icon: 'success',
            duration: 5000

          })
        }
      },
    })

  },



  order: function () {
    var that = this

    if (that.data.detail.borrownum != '0')
     {
      console.log("还有藏书")
        wx.navigateTo({
          url: '../preorder/preorder?bookid='+that.data.bookid,
        })
    }
    else {
      console.log("该书图书库中已经借空")

      that.setData({
        warnbol: true
      })
    }
  },


nobook:function()
{
  wx.showToast({
    title: '已无藏书',
    icon: 'success',
    duration: 5000
  })

},
copy:function(e)
{

  wx.showToast({
    title: '该商家链接复制成功!',
    icon: 'success',
    duration: 2000
  })
  console.log(e)
var current=e.currentTarget.dataset

  wx.setClipboardData({
    data: current.link,
    success: function (res) {
      wx.getClipboardData({
        success: function (res) {
          console.log(res.data) // data
        }
      })
    }
  })
}

})
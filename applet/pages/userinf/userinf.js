Page({
  data: {
    userid: '',
    currentTab: 0,
    userinf: '',
    bol1: '',
    dynamiclist: '',
    collectlist: '',
    logs: '',
    bol: false,
    fanbol: '0',
    user: '',
    clicentheight: 0,
    clicentheight1: 0,
    dynamicid:''
  },
  swichNav: function (e) {
    console.log(e);
    var that = this;
    if (this.data.currentTab === e.target.dataset.current) {
      return false;
    } else {
      that.setData({
        currentTab: e.target.dataset.current,
      })
    }
  },
  swiperChange: function (e) {
    console.log(e);
    var that = this
    this.setData({
      currentTab: e.detail.current,
    })


    if (that.data.currentTab == 0) {
      console.log("我是收藏")
      wx.request({
        url: 'https://www.titwdj.cn/BorrowBook/ShowMyCollect',

        data: {

          userid: that.data.userid

        },

        success: function (res) {

          console.log(res.data)
          that.setData({

            collectlist: res.data

          })

        },
      })
    }

    if (that.data.currentTab == 1) {
      console.log("我是动态")

      wx.request({
        url: 'https://www.titwdj.cn/BorrowBook/getMyDynamic',
        data: {
          userid: that.data.userid
        },
        success: function (res) {
          console.log(res.data)
          that.setData({
            dynamiclist: res.data
          })
        }
      })

    }

  },
  onLoad: function (options) {
    var that = this;
    wx.getSystemInfo({
      success: function (res) {
        that.setData({

          clicentheight: res.windowHeight - 215,
          clicentheight1: res.windowHeight - 239
        })
      }
    })
    console.log(options.userid)
    console.log(options.bol1)
    that.setData({
      userid: options.userid,
      bol1: options.bol1
    })

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


    // if (value) {
    //   //判断是否关注
    //   wx.request({
    //     url: 'https://www.titwdj.cn/BorrowBook/BolFanUserInf',
    //     data: {
    //       userid: that.data.logs.userid,
    //       otherid: that.data.userid
    //     },
    //     success: function (res) {
    //       console.log(res.data)
    //       that.setData({
    //         fanbol: res.data.fanbol
    //       })
    //     }
    //   })
    // }





    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/ShowMyCollect',

      data: {

        userid: that.data.userid

      },

      success: function (res) {

        console.log(res.data)
        that.setData({

          collectlist: res.data

        })

      },
    })




    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/getUserInf',
      data: {
        userid: options.userid
      },
      success: function (res) {
        console.log(res.data)
        that.setData({
          userinf: res.data
        })
      }
    })




  },
  onShow: function () {
    var that = this
    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/getUserInformationServlet',
      data: {
        userid: that.data.userid
      },
      success: function (res) {
        console.log(res.data)
        that.setData({
          user: res.data
        })
      }
    })


  },

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


  nologin: function () {

    wx.showModal({
      title: '提示',
      content: '你尚登录,请先登录!',
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

  //点赞

  Addadmire: function (e) {

    var that = this
    var current = e.currentTarget.dataset

    console.log(current.dynamicid)

    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/AddDynamicAdmire',
      data: {

        userid: that.data.logs.userid,
        dynamicid: current.dynamicid,
        action: 'add'

      },

      success: function (res) {


        wx.request({
          url: 'https://www.titwdj.cn/BorrowBook/getMyDynamic',
          data: {
            userid: that.data.userid
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
  },


  //撤销点赞
  Deleteadmire: function (e) {
    var that = this
    var current = e.currentTarget.dataset

    console.log(current.dynamicid)


    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/AddDynamicAdmire',
      data: {

        userid: that.data.logs.userid,
        dynamicid: current.dynamicid,
        action: 'delete'

      },

      success: function (res) {

        wx.request({
          url: 'https://www.titwdj.cn/BorrowBook/getMyDynamic',
          data: {
            userid: that.data.userid
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

  },


  //关注
  openfan: function (e) {

    var that = this
    var current = e.currentTarget.dataset

    console.log(current.otherid)

    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/AddFan',
      data: {

        userid: that.data.logs.userid,
        otherid: that.data.userid,
        action: 'add'

      },

      success: function (res) {
        console.log(res.data)
        that.setData({
          dynamiclist: res.data
        })

        wx.request({
          url: 'https://www.titwdj.cn/BorrowBook/BolFanUserInf',
          data: {
            userid: that.data.logs.userid,
            otherid: that.data.userid
          },
          success: function (res) {
            console.log(res.data)
            that.setData({
              fanbol: res.data.fanbol
            })
          }
        })

        wx.request({
          url: 'https://www.titwdj.cn/BorrowBook/getUserInf',
          data: {
            userid: that.data.userid
          },
          success: function (res) {
            console.log(res.data)
            that.setData({
              userinf: res.data
            })
          }
        })

      },
    })
  },
  //取消关注
  closefan: function (e) {
    var that = this
    var current = e.currentTarget.dataset

    console.log(current.otherid)

    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/AddFan',
      data: {
        userid: that.data.logs.userid,
        otherid: that.data.userid,
        action: 'delete'
      },

      success: function (res) {
        console.log(res.data)
        that.setData({
          dynamiclist: res.data
        })

        wx.request({
          url: 'https://www.titwdj.cn/BorrowBook/BolFanUserInf',
          data: {
            userid: that.data.logs.userid,
            otherid: that.data.userid
          },
          success: function (res) {
            console.log(res.data)
            that.setData({
              fanbol: res.data.fanbol
            })
          }
        })


        wx.request({
          url: 'https://www.titwdj.cn/BorrowBook/getUserInf',
          data: {
            userid: that.data.userid
          },
          success: function (res) {
            console.log(res.data)
            that.setData({
              userinf: res.data
            })
          }
        })
      },
    })
  },

setButtonId:function(e)
{
var that=this
console.log(e)
console.log(e.current.Target.dataset)
  var current = e.current.Target.dataset
  
  that.setData({
  dynamicid:current.dynamicid
})
},
  onShareAppMessage: function (res) {
var that=this

console.log(res.data.dynamicid)

    if (res.from === 'button') {

      console.log(res.target)
    }
    return {
      title: '将动态转发到微信',
      path: '/page/dynamicdetail/dynamicdetail?dynamicid='+that.data.dynamicid,
      success: function (res) {
        console.log("转发成功")
      },
      fail: function (res) {
        // 转发失败
      }
    }
  }



})  

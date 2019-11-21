//获取应用实例
var app = getApp()
Page({
  data:
  {
    imgUrls: "",
    books: "",
    category: '',
    abc: '123@456',
    d: '',
    booknum: '',
    booknew: '',
    open: false,

    display1: false,
    logs: '',
    bol: false,
    resdata: '',
    Type: '',
    outprint: "",
    referstatus: false,
    bolstatus: '',
    moneyscore: '',
    user: '',
    discountnum: '',

    //专题数据
    // topiclist: '',
    // topicpagenum: 1,
    // topicall: '0',
    // topicpagemax: '0',

   
    //vip数据
    viplist: '',
    vippagenum: 1,
    vipall: '0',
    vippagemax: '0',



    //推荐数据
    referlist: '',
    referpagenum: 1,
    referall: '0',
    referpagemax: '0',

    //好评数据
    goodlist: '',

    //count://显示计数
    count: 1,
    countbol1: false,
    countbol2: false,
    countbol3: false,
    countbol4: false,

    showload: false,
    showloadall: false,

    mesbol: '',

  },



  sign: function () {

    var that = this
    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/UserSignServlet',
      data: {
        userid: that.data.logs.userid
      },
      success: function (res) {
        // console.log(res.data)
        wx.showToast({
          title: '积分+5！',
          icon: 'success',
          duration: 2000
        })


        //获取用户信息
        wx.request({
          url: 'https://www.titwdj.cn/getUserInformationServlet',
          data: {
            userid: that.data.logs.userid
          },
          success: function (res) {
            // console.log(res.data)
            that.setData({
              user: res.data
            })
          }
        })


      }
    })


  },

  //获取元素自适应后的实际宽度
  getEleWidth: function (w) {
    var real = 0;
    try {
      var res = wx.getSystemInfoSync().windowWidth;
      var scale = (750 / 2) / (w / 2);//以宽度750px设计稿做宽度的自适应
      // console.log(scale);
      real = Math.floor(res / scale);
      return real;
    } catch (e) {
      return false;
      // Do something when catch error
    }
  },
  initEleWidth: function () {
    var delBtnWidth = this.getEleWidth(this.data.delBtnWidth);
    this.setData({
      delBtnWidth: delBtnWidth
    });
  },


  //点击删除按钮事件
  delItem: function (e) {
    //获取列表中要删除项的下标
    var index = e.target.dataset.index;
    var list = this.data.list;
    //移除列表中下标为index的项
    list.splice(index, 1);
    //更新列表的状态
    this.setData({
      list: list
    });
  },

  tap_ch: function (e) {
    if (this.data.open) {
      this.setData({
        open: false
      });
    } else {
      this.setData({
        open: true
      });
    }
  },

  tap_start: function (e) {
    // touchstart事件
    this.data.mark = this.data.newmark = e.touches[0].pageX;
  },
  tap_drag: function (e) {
    // touchmove事件

    /*
     * 手指从左向右移动
     * @newmark是指移动的最新点的x轴坐标 ， @mark是指原点x轴坐标
     */
    this.data.newmark = e.touches[0].pageX;
    if (this.data.mark < this.data.newmark) {
      this.istoright = true;
    }
    /*
     * 手指从右向左移动
     * @newmark是指移动的最新点的x轴坐标 ， @mark是指原点x轴坐标
     */
    if (this.data.mark > this.data.newmark) {
      this.istoright = false;

    }
    this.data.mark = this.data.newmark;

  },
  tap_end: function (e) {
    // touchend事件
    this.data.mark = 0;
    this.data.newmark = 0;
    if (this.istoright) {
      this.setData({
        open: true
      });
    } else {
      this.setData({
        open: false
      });
    }
  },



  onLoad: function (options) {


    wx.showLoading({
      title: '加载中',
    })

    var that = this
    wx.getSystemInfo({
      success: function (res) {
        that.setData({

          clicentheight: res.windowHeight - 120

        })
      }
    })
    //轮播显示
    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/ShowSlideServlet',

      success: function (res) {
        // console.log(res.data)
        that.setData({
          imgUrls: res.data
        })
      }

    })


    //专题分页
    // wx.request({
    //   url: 'https://www.titwdj.cn/BorrowBook/TopicPageServlet',
    //   data: {
    //     topicpagenum: '1'
    //   },
    //   success: function (res) {
    //     // console.log(res.data)
    //     that.setData({
    //       topiclist: res.data.list,
    //       topicall: res.data.maxsize
    //     })
    //     console.log("all" + that.data.topicall)
    //     if (parseInt(that.data.topicall) % 4 > 0) {
    //       that.setData({
    //         topicpagemax: Math.ceil(that.data.topicall / 4)
    //       })
    //     }
    //     else {
    //       that.setData({
    //         topicpagemax: that.data.topicall / 4
    //       })
    //     }
    //     console.log("maxpage" + that.data.topicpagemax)
    //   },
    //   fail: function () {
    //     // fail
    //   },
    //   complete: function () {
    //     wx.hideNavigationBarLoading() //完成停止加载
    //     wx.stopPullDownRefresh() //停止下拉刷新
    //   }
    // })







    //   //降价分页
    //   wx.request({
    //     url: 'https://www.titwdj.cn/BorrowBook/ReducePageServlet',
    //     data: {
    //       reducepagenum: '1'
    //     },
    //     success: function (res) {
    //      //console.log(res.data)
    //       that.setData({
    //         reducelist: res.data.list,
    //         reduceall: res.data.maxsize
    //       })
    //       console.log("all" + that.data.reduceall)
    //       if (parseInt(that.data.reduceall) % 3 > 0) {
    //         that.setData({
    //           reducepagemax: Math.ceil(that.data.reduceall / 3)
    //         })
    //       }
    //       else {
    //         that.setData({
    //           reducepagemax: that.data.reduceall / 3
    //         })
    //       }
    //       console.log("maxpage" + that.data.reducepagemax)
    //     }
    //   })


    //   //组团分页
    //   wx.request({
    //     url: 'https://www.titwdj.cn/BorrowBook/GroupPageServlet',
    //     data: {
    //       grouppagenum: '1'
    //     },
    //     success: function (res) {


    //       setTimeout(function () {
    //         wx.hideLoading()
    //       }, 1000)
    //      //console.log(res.data)
    //       that.setData({
    //         grouplist: res.data.list,
    //         groupall: res.data.maxsize
    //       })
    //       console.log("all" + that.data.groupall)
    //       if (parseInt(that.data.groupall) % 3 > 0) {
    //         that.setData({
    //           grouppagemax: Math.ceil(that.data.groupall / 3)
    //         })
    //       }
    //       else {
    //         that.setData({
    //           grouppagemax: that.data.groupall / 3
    //         })
    //       }
    //       console.log("maxpage" + that.data.grouppagemax)
    //     }
    //   })





  },


  onShow: function () {
    var that = this
    try {
      var value = wx.getStorageSync('key')
      console.log(value)
      if (value) {
        that.setData({
          bol: true,
          logs: value
        })
      }
    }
    catch (e) {

    }


    if (value) {
      //判断是否有推荐提醒
      wx.request({
        url: 'https://www.titwdj.cn/BorrowBook/ReferMessageBol',

        data: {

          userid: that.data.logs.userid

        },

        success: function (res) {

          // console.log(res.data)
          if (res.data == '0') {
            that.setData({

              referstatus: true

            })


          }
          else {

            that.setData({

              referstatus: false

            })


          }


        }

      })

      //获取用户信息
      wx.request({
        url: 'https://www.titwdj.cn/BorrowBook/getUserInformationServlet',
        data: {
          userid: that.data.logs.userid
        },
        success: function (res) {
          // console.log(res.data)
          that.setData({
            user: res.data
          })
        }
      })

      //推荐
      wx.request({
        url: 'https://www.titwdj.cn/BorrowBook/ReferIndexPage',
        data: { 
          referpagenum: '1',
           userid: that.data.logs.userid
       
        },
        success: function (res) {
          // console.log(res.data)
          that.setData({
            referlist: res.data.list,
            referall: res.data.maxsize
          })
          console.log("all" + that.data.referall)
          if (parseInt(that.data.referall) % 3 > 0) {
            that.setData({
              referpagemax: Math.ceil(that.data.referall / 3)
            })
          }
          else {
            that.setData({
              referpagemax: that.data.referall / 3
            })
          }
          console.log("maxpage" + that.data.referpagemax)
        }
      })




    }//if

    //判断是否有消息
    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/ReadStatusServlet',
      data: {
        userid: that.data.logs.userid
      },
      success: function (res) {
        // console.log(res.data)
        that.setData({
          mesbol: res.data
        })

      }

    })




  },





  //专题分页切换
  // TopicChange: function () {
  //   var that = this
  //   console.log("你好")
  //   that.setData({
  //     topicpagenum: parseInt(that.data.topicpagenum) + 1
  //   })

  //   console.log(that.data.topicpagenum)
  //   if (that.data.topicpagenum > that.data.topicpagemax) {

  //     that.setData({
  //       topicpagenum: 1
  //     })

  //     wx.request({
  //       url: 'https://www.titwdj.cn/BorrowBook/TopicPageServlet',

  //       data: {
  //         topicpagenum: that.data.topicpagenum

  //       },
  //       success: function (res) {
  //         // console.log(res.data)
  //         console.log("我是第+" + that.data.topicpagenum)
  //         that.setData({
  //           topiclist: res.data.list,

  //         })
  //       }
  //     })
  //   }
  //   else {
  //     wx.request({
  //       url: 'https://www.titwdj.cn/BorrowBook/TopicPageServlet',

  //       data: {
  //         topicpagenum: that.data.topicpagenum

  //       },
  //       success: function (res) {
  //         // console.log(res.data)
  //         console.log("我是第+" + that.data.topicpagenum)
  //         that.setData({
  //           topiclist: res.data.list,

  //         })
  //       }
  //     })

  //   }
  // },



  //推荐分页切换
  ReferChange: function () {
    var that = this

    that.setData({
      referpagenum: parseInt(that.data.referpagenum) + 1
    })
    if (that.data.referpagenum >= that.data.referpagemax) {

      that.setData({
        referpagenum: 1
      })

      wx.request({
        url: 'https://www.titwdj.cn/BorrowBook/ReferIndexPage',

        data: {
          referpagenum: that.data.referpagenum,
          userid: that.data.logs.userid
        },
        success: function (res) {
          // console.log(res.data)
          // console.log("我是第+" + that.data.referpagenum)
          that.setData({
            referlist: res.data.list,

          })
        }
      })
    }
    else {
      wx.request({
        url: 'https://www.titwdj.cn/BorrowBook/ReferIndexPage',

        data: {
          referpagenum: that.data.referpagenum,
          userid: that.data.logs.userid
        },
        success: function (res) {
          // console.log(res.data)
          // console.log("我是第+" + that.data.referpagenum)
          that.setData({
            referlist: res.data.list,

          })
        }
      })

    }
  },



  // //减价分页切换
  // ReduceChange: function () {
  //   var that = this

  //   that.setData({
  //     reducepagenum: parseInt(that.data.reducepagenum) + 1
  //   })
  //   if (that.data.reducepagenum >=that.data.reducepagemax) {

  //     that.setData({
  //       reducepagenum: 1
  //     })

  //     wx.request({
  //       url: 'https://www.titwdj.cn/BorrowBook/ReducePageServlet',

  //       data: {
  //         reducepagenum: that.data.reducepagenum

  //       },
  //       success: function (res) {
  //        //console.log(res.data)
  //         console.log("我是第+" + that.data.reducepagenum)
  //         that.setData({
  //           reducelist: res.data.list,

  //         })
  //       }
  //     })


  //   }
  //   else {
  //     wx.request({
  //       url: 'https://www.titwdj.cn/BorrowBook/ReducePageServlet',

  //       data: {
  //         reducepagenum: that.data.reducepagenum

  //       },
  //       success: function (res) {
  //        //console.log(res.data)
  //         console.log("我是第+" + that.data.reducepagenum)
  //         that.setData({
  //           reducelist: res.data.list,

  //         })
  //       }
  //     })

  //   }
  // },




  //组团分页切换
  // GroupChange: function () {
  //   var that = this

  //   that.setData({
  //     grouppagenum: parseInt(that.data.grouppagenum) + 1
  //   })
  //   if (that.data.grouppagenum >=that.data.grouppagemax) {

  //     that.setData({
  //       grouppagenum: 1
  //     })
  //     wx.request({
  //       url: 'https://www.titwdj.cn/BorrowBook/GroupPageServlet',

  //       data: {
  //         grouppagenum: that.data.grouppagenum

  //       },
  //       success: function (res) {
  //        //console.log(res.data)
  //         console.log("我是第+" + that.data.grouppagenum)
  //         that.setData({
  //           grouplist: res.data.list,

  //         })
  //       }
  //     })
  //   }
  //   else {
  //     wx.request({
  //       url: 'https://www.titwdj.cn/BorrowBook/GroupPageServlet',

  //       data: {
  //         grouppagenum: that.data.grouppagenum

  //       },
  //       success: function (res) {
  //        //console.log(res.data)
  //         console.log("我是第+" + that.data.grouppagenum)
  //         that.setData({
  //           grouplist: res.data.list,

  //         })
  //       }
  //     })

  //   }
  // },


  //vip分页切换
  VipChange: function () {
    var that = this

    that.setData({
      vippagenum: parseInt(that.data.vippagenum) + 1
    })
    if (that.data.vippagenum >= that.data.vippagemax) {

      that.setData({
        vippagenum: 1
      })
      wx.request({
        url: 'https://www.titwdj.cn/BorrowBook/VipPageServlet',

        data: {
          vippagenum: that.data.vippagenum

        },
        success: function (res) {
          // console.log(res.data)
          // console.log("我是第+" + that.data.vippagenum)
          that.setData({
            viplist: res.data.list,

          })
        }
      })
    }
    else {
      wx.request({
        url: 'https://www.titwdj.cn/BorrowBook/VipPageServlet',

        data: {
          vippagenum: that.data.vippagenum

        },
        success: function (res) {
          // console.log(res.data)
          // console.log("我是第+" + that.data.vippagenum)
          that.setData({
            viplist: res.data.list,

          })
        }
      })

    }
  },


  //首页转发转发
  onShareAppMessage: function () {

    var that = this
    return {
      title: '个性图书推荐首页',
      path: 'pages/index/index',

      success: function (res) {
        // 转发成功
      },
      fail: function (res) {
        // 转发失败
      }
    }
  },


  //扫描找书
  scan1: function () {

    var that = this


    wx.scanCode({
      success: (res) => {
        console.log(res.result)
        console.log(res.scanType)


        that.setData({

          bookid: res.result

        })


        if (res.scanType == 'QR_CODE') {
          console.log(res.result.substring(0, 1))

          if (res.result.substring(0, 1) == 't') {
            wx.navigateTo
              ({
                url: '../transmitborrowpay/transmitborrowpay?id=' + res.result.substring(14, res.result.length),
              })

          }
          else {
            that.setData({
              formbol: true
            })
          }



        }
        else if (res.scanType == 'EAN_13') {
          wx.showLoading({
            title: '加载中',
          })

          if (res.result.length == 13) {

            wx.request({
              url: 'https://www.titwdj.cn/BorrowBook/ScanIsbnServlet',
              data: {

                isbn: res.result

              },

              success: function (res) {

                setTimeout(function () {
                  wx.hideLoading()
                }, 1000)

               //console.log(res.data)
                if (res.data == "failure") {

                  wx.showToast({
                    title: '书库无此藏书！',
                    icon: 'success',
                    duration: 3000
                  })


                }
                else {

                  wx.navigateTo({
                    url: '../particulars/particulars?bookid=' + res.data.bookid + "&advice=" + res.data.categoryid,

                  })

                }


              },

            })

          }
          else {
            wx.showToast({
              title: '请扫描正确的条形码！',
              icon: 'success',
              duration: 3000
            })


          }


        }
        else {
          wx.showToast({
            title: '请扫描正确的条形码！',
            icon: 'success',
            duration: 3000
          })


        }
      }
    })

  },


  //上拉加载
  onbottom: function () {

    var that = this
    console.log('circle 下一页');


    //领券和vip
    if (that.data.count == 1) {

      wx.showLoading({
        title: '加载中',
      }),

        console.log(that.data.count)
      //vip分页

      that.setData({
        countbol1: true,
        count: parseInt(that.data.count + 1),
        showload: true
      })


      wx.request({
        url: 'https://www.titwdj.cn/BorrowBook/VipPageServlet',
        data: {
          vippagenum: '1'
        },
        success: function (res) {

          setTimeout(function () {
            wx.hideLoading()
          }, 1000)

         //console.log(res.data)
          that.setData({
            viplist: res.data.list,
            vipall: res.data.maxsize
          })
          console.log("all" + that.data.vipall)
          if (parseInt(that.data.vipall) % 3 > 0) {
            that.setData({
              vippagemax: Math.ceil(that.data.vipall / 3)
            })
          }
          else {
            that.setData({
              vippagemax: that.data.vipall / 3
            })
          }
          console.log("maxpage" + that.data.vippagemax)
        }
      })





    }

    if (that.data.count == 2) {


      wx.showLoading({
        title: '加载中',
      }),


        console.log(that.data.count)



      that.setData({
        countbol2: true,
        count: parseInt(that.data.count + 1),
        showload: true
      })

    }


    //图书推荐
    if (that.data.count == 3) {


      that.setData({
        countbol3: true,
        count: parseInt(that.data.count + 1),
        showload: true
      })



    }

    if (that.data.count == 4) {



      that.setData({
        countbol4: true,
        count: parseInt(that.data.count + 1),
        showload: false,
        showloadall: true,

      })

      //人气热门图书
      wx.request({
        url: 'https://www.titwdj.cn/BorrowBook/ShowBookNumIndex',


        success: function (res) {
         //console.log(res.data)

          that.setData({

            booknum: res.data

          })
          setTimeout(function () {
            wx.hideLoading()
          }, 2000)


        },

      }),


        //最新图书
        wx.request({
          url: 'https://www.titwdj.cn/BorrowBook/ShowBookNewIndex',


          success: function (res) {
           //console.log(res.data)

            that.setData({

              booknew: res.data

            })

          },

        })


      //好评图书
      wx.request({
        url: 'https://www.titwdj.cn/BorrowBook/getGoodEvaluateTop',
        data: {
          action: 0
        },
        success: function (res) {
         //console.log(res.data)
          that.setData({
            goodlist: res.data
          })
        },
      })

    }
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

})

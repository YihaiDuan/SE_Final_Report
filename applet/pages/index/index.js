//获取应用实例
var app = getApp()
Page({
  data: 
  {
  imgUrls:"",
  books:"",
  category:'',
  abc:'123@456',
  d:'',
  booknum: '',
  booknew: '',
  open:false,

  display1: false,
  logs: '',
  bol: false,
  resdata: '',
  Type: '',
  outprint: "",
  referstatus: false,
  bolstatus: '',
  moneyscore: '',
  user:'',
  discountnum:'',

// 分类数据
  typelist: '',
  typeid: '',
  activeIndex: 0,
  content: '菜單一',
  tabs: [],
  contentList: [],

//减价数据
  // reducelist: '',
  // reducepagenum: 1,
  // reduceall: '0',
  // reducepagemax: '0',



//组团数据
  // grouplist: '',
  // grouppagenum: 1,
  // groupall: '0',
  // grouppagemax: '0',

  //vip数据
  viplist: '',
  vippagenum: 1,
  vipall: '0',
  vippagemax: '0',


  // //电子书数据
  // elelist: '',
  // elepagenum: 1,
  // eleall: '0',
  // elepagemax: '0',


  //免费电子书数据
  // freelist: '',
  // freepagenum: 1,
  // freeall: '0',
  // freepagemax: '0',

  //推荐数据
  referlist: '',
  referpagenum: 1,
  referall: '0',
  referpagemax: '0',

//好评数据
goodlist:'',

  //count://显示计数
count:1,
countbol1:false,
countbol2:false,
countbol3:false,
countbol4:false,

showload:false,
showloadall:false,

mesbol:'',

  },



  sign :function()
  {

var that=this
wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/UserSignServlet',
data:{
userid:that.data.logs.userid
},
  success:function(res)
  {

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
  if(this.data.mark < this.data.newmark) {
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



  onLoad:function (options) {


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
      
        that.setData({
          imgUrls: res.data
        })
      }

    })

//分类显示
    var that = this
    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/ShowAllType',


      success: function (res) {
        console.log(res.data)
        that.setData({
          tabs: res.data
        })

        wx.request({

          url: 'https://www.titwdj.cn/BorrowBook/ShowCategoryById',
          data: {
            typeid: that.data.tabs[0].typeid,
          },
          success: function (res) {
            console.log(res.data)
            that.setData({
              contentList: res.data.list
            })
            setTimeout(function () {
              wx.hideLoading()
            }, 1000)
          }


        })
      }
    })
    var vm = this;
    wx.getSystemInfo({
      success: (res) => {
        console.info(res);
        vm.setData({
          deviceWidth: res.windowWidth,
          deviceHeight: res.windowHeight + 50
        });
      }
    });


   
   },

  changeTab: function (e) {

    var that = this
    this.setData({
      activeIndex: e.currentTarget.dataset.index,
      content: e.currentTarget.dataset.name
    })
    console.log(e)

    wx.request({

      url: 'https://www.titwdj.cn/BorrowBook/ShowCategoryById',
      data: {
        typeid: e.currentTarget.dataset.typeid,
      },
      success: function (res) {
        console.log(res.data)
        that.setData({
          contentList: res.data.list
        })

      }


    })

  },

  getMore: function () {
    this.setData({
      contentList: this.data.contentList.concat([
        { text: '菜单:' },
        { text: '菜单:' },
        { text: '菜单:' },
        { text: '菜单:' },
        { text: '菜单:' }
      ])
    });
  },


  onShow:function () {
    var that = this
    try {
      var value = wx.getStorageSync('key')
  
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
        userid:that.data.logs.userid
      },
      success: function (res) {
     
        that.setData({
          referlist: res.data.list,
          referall: res.data.maxsize
        })
      
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
    
      }
    })


      

    }//if

//判断是否有消息
wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/ReadStatusServlet',
data:{
  userid:that.data.logs.userid
},
success:function(res)
{

that.setData({
    mesbol:res.data
})

}

})




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
    


        that.setData({

          bookid: res.result

        })


        if (res.scanType == 'QR_CODE') {
    

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
        else if (res.scanType == 'EAN_13') 
        {
          wx.showLoading({
            title: '加载中',
          })

if (res.result.length == 13)
{

  wx.request({
    url: 'https://www.titwdj.cn/BorrowBook/ScanIsbnServlet',
    data: {

      isbn: res.result

    },

    success: function (res) {

      setTimeout(function () {
        wx.hideLoading()
      }, 1000)

 
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
else
{
  wx.showToast({
    title: '请扫描正确的条形码！',
    icon: 'success',
    duration: 3000
  })


}


        }
        else
        {
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
  onbottom: function () 
  {

    var that=this

   wx.showLoading({
     title: '加载中',
   }),

     that.setData({
       countbol3: true,
       count: parseInt(that.data.count + 1),
        showload: true
     })

     that.setData({
       countbol4: true,
       count: parseInt(that.data.count + 1),
       showload: false,
       showloadall:true,
     
     })

     //人气热门图书
     wx.request({
       url: 'https://www.titwdj.cn/BorrowBook/ShowBookNumIndex',


       success: function (res) {
     

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
        

           that.setData({

             booknew: res.data

           })

         },

       })


     //好评图书
     wx.request({
       url: 'https://www.titwdj.cn/BorrowBook/getGoodEvaluateTop',
data:{
action:0
},
success: function (res) 
{
 


         that.setData({
           goodlist: res.data
         })
 },
 })
  
 }
,






  nologin: function () {

    wx.showModal({
      title: '提示',
      content: '你尚登录,请先登录!',
      success: function (res) {
        if (res.confirm) {


          wx.navigateTo({

            url: '../register/register',


          })


        }
        else if (res.cancel) {

        }
      }
    })


  },

})

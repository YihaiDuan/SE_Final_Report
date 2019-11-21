// pages/bookreview/bookreview.js
Page({
  data:{

    dynamicid:'',
    logs:'',
    userid:'',
    bol:false
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
  onLoad:function(options){
 var that=this
 that.setData({
dynamicid:options.dynamicid

 })
 console.log(that.data.dynamicid)
  },

  onShow:function(){

    var that = this;

    try {
      var value = wx.getStorageSync('key')
      if (value) {
        that.setData({
          logs: value,
          bol: true,
        })

        console.log("登录成功")
        console.log(that.data.bol)
      }
    }
    catch (e) {

    }

    if (value) {
      wx.request({
        url: 'https://www.titwdj.cn/BorrowBook/getDynamicByidByUser',
        data: {
          dynamicid: that.data.dynamicid,
          userid: that.data.logs.userid
        },


        success: function (res) {
          console.log(res.data)

          that.setData({
            dynamicdetail: res.data
          })

        }
      })

  
    }
    else {

      wx.request({
        url: 'https://www.titwdj.cn/BorrowBook/getDynamicByid',
        data: {
          dynamicid: that.data.dynamicid
        },


        success: function (res) {
          console.log(res.data)

          that.setData({
            dynamicdetail: res.data
          })

        }
      })

    }


  },
 

  //点赞

  Addadmire: function (e) {

    var that = this
    var current = e.currentTarget.dataset

    console.log(current.dynamicid)


    var pages = getCurrentPages();
    var prevPage = pages[pages.length - 2];
    console.log(prevPage.data.dynamiclist)
    console.log(prevPage.data.index)
    var index = prevPage.data.index

    prevPage.data.dynamiclist[index].admirebol = 'true'
    prevPage.data.dynamiclist[index].admirenum = parseInt(prevPage.data.dynamiclist[index].admirenum + 1)
    prevPage.setData({
      dynamiclist: prevPage.data.dynamiclist
    })




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


    var pages = getCurrentPages();
    var prevPage = pages[pages.length - 2];
    console.log(prevPage.data.dynamiclist)
    console.log(prevPage.data.index)
    var index = prevPage.data.index

    prevPage.data.dynamiclist[index].admirebol = 'false'
    prevPage.data.dynamiclist[index].admirenum = parseInt(prevPage.data.dynamiclist[index].admirenum -1)
    prevPage.setData({
      dynamiclist: prevPage.data.dynamiclist
    })



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

    var pages = getCurrentPages();
    var prevPage = pages[pages.length - 2];
    console.log(prevPage.data.dynamiclist)
    console.log(prevPage.data.index)
    var index = prevPage.data.index

    prevPage.data.dynamiclist[index].fanbol = '1'
 
    prevPage.setData({
      dynamiclist: prevPage.data.dynamiclist
    })


    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/AddFanByDetail',
      data: {

        userid: that.data.logs.userid,
        otherid: current.otherid,
        action: 'add',
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

  //取消关注
  DeleteFan: function (e) {
    var that = this
    var current = e.currentTarget.dataset

    console.log(current.otherid)

    var pages = getCurrentPages();
    var prevPage = pages[pages.length - 2];
    console.log(prevPage.data.dynamiclist)
    console.log(prevPage.data.index)
    var index = prevPage.data.index

    prevPage.data.dynamiclist[index].fanbol = '0'

    prevPage.setData({
      dynamiclist: prevPage.data.dynamiclist
    })


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

var para='';
    if (res.from === 'button') {

      console.log(res.target)
      console.log(res.target.dataset.dynamicid)
      para = res.target.dataset.dynamicid
    }
    return {
      title: '将动态转发到微信',
      path: '/page/bookreview/bookreview?dynamicid=' +that.data.dynamicid,
      success: function (res) {
        console.log("转发成功")
      },
      fail: function (res) {
        // 转发失败
      }
    }
  },


})
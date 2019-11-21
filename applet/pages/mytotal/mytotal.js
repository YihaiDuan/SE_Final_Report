
var _wxcharts = require('../../mars/plugins/wxcharts')

Page({
  data: {
   
line:'',
curyear:'',
maxyear:'',
minyear:'2013',
logs:'',
readnum:'',
borrownum:'',
dis:'',
day:'',
month:'',
userid:''
  },
  onLoad: function (options)
   {

    wx.showLoading({
      title: '加载中',
    })
    var that=this
that.setData({

  userid:options.userid
})
    try {

      var value = wx.getStorageSync('key')
      console.log(value)
      if (value) {

        that.setData({
          
          logs: value

        })

      }
    }
    catch (e) {

    }


    var myDate = new Date();
     var year= myDate.getFullYear();
      that.setData({

        month:myDate.getMonth(),
        day:myDate.getDay()
      })
     wx.request({
       url: 'http://139.199.23.184/BorrowBook/getUserInformationServlet',
       data: {
         userid: that.data.logs.userid
       },
       success: function (res) {
         console.log(res.data)
         that.setData({
           minyear:res.data.starttime.substring(0,4)
         })
       }
     })
console.log(that.data.minyear)

     that.setData({
curyear:year,
maxyear:year
     })

console.log(year)
//获取用户的注册时间


//获取借书量分布
    wx.request({
      url: 'http://139.199.23.184/BorrowBook/getMonthBorrowServlet',

      data: {
        year: that.data.curyear,
        userid:that.data.logs.userid
      },
      success: function (res) {

        console.log(res.data)
        that.setData({
          line: res.data
        })
        new _wxcharts(that.data.line)
      }

    })

    //获取阅读偏好
   wx.request({
      url: 'http://139.199.23.184/BorrowBook/getReadHobby',

      data: {
        userid: that.data.logs.userid
      },
      success: function (res) {

        console.log(res.data)
        that.setData({
          pie: res.data
        })
        new _wxcharts(that.data.pie)
      }

    })
    

    //获取用户阅读书
   wx.request({
     url: 'http://139.199.23.184/BorrowBook/getReadBorrowData',

     data: {
       userid: that.data.logs.userid
     },
     success: function (res) {

       console.log(res.data)
       that.setData({

     readnum:res.data.readnum,
     borrownum:res.data.borrownum,
     dis:res.data.dis
       })

       setTimeout(function () {
         wx.hideLoading()
       }, 1000)
     /*  var dis = (that.data.dis).toFixed(2)
       that.setData({

         dis: dis
       })
console.log(that.data.dis)*/
     }

   })

  // that.pieShow()
  },
 
addyear:function()
{
var that=this



 if (that.data.curyear<that.data.maxyear)
 {

   that.setData({
     curyear: parseInt(that.data.curyear) + 1
   })

wx.request({
  url: 'http://139.199.23.184/BorrowBook/getMonthBorrowServlet',

  data: {
    year: that.data.curyear,
    userid:that.data.logs.userid
  },
  success: function (res) {
    that.setData({
      line: res.data
    })
    new _wxcharts(that.data.line)
  }

})

 }
 else
 {
   wx.showToast({
     title: '你想到达未来吗',
     icon: 'success',
     duration: 2000
   })

 }

},
 deleteyear:function()
 {
   var that = this
   if (that.data.curyear>that.data.minyear) {

   that.setData({
     curyear: parseInt(that.data.curyear) - 1
   })
   wx.request({
     url: 'http://139.199.23.184/BorrowBook/getMonthBorrowServlet',

     data: {
       year: that.data.curyear,
       userid:that.data.logs.userid
     },
     success: function (res) {
       that.setData({
         line: res.data
       })
       new _wxcharts(that.data.line)
     }

   })

   }
   else
   {
     wx.showToast({
       title: '你还没有诞生呢',
       icon: 'success',
       duration: 2000
     })
   }

 },

  /**
   * @Explain：获取设备信息
   */
  getDeviceInfo: function () {
    let that = this
    wx.getSystemInfo({
      success: function (res) {
        that.setData({
          deviceW: res.windowWidth,
          deviceH: res.windowHeight
        })
      }
    })
  },

/*
  //月销量图
 lineShow: function (){
var that=this
wx.request({
  url: 'http://139.199.23.184/BorrowBook/getMonthBorrowServlet',

  data: {

    year: 2017
  },
  success: function (res) {
  
    that.setData({

      
      line: res.data
    })
    new _wxcharts(that.data.line)
  }

})



  },
*/

//爱好偏好图

  pieShow: function () {
   
    let pie = {
      canvasId: 'pieGraph',
      type: 'pie',
      series: [{
        name: '小说',
        data: '1',
      }, {
        name: '文学',
        data: '1',
      },
      {
        name: '文',
        data: '1',
      }],
      width: 320,
      height: 320,
     // dataLabel: true
    }
    new _wxcharts(pie)
  },

  onShareAppMessage: function (res) {

    var that=this
    console.log(that.data.logs.userid)
    if (res.from === 'button') {
      
      console.log(res.target)
    }
    return {
      title: '我的阅历',
      path: '../mytotal/mytotal?userid=' + that.data.logs.userid,
      success: function (res) {
        // 转发成功
      },
      fail: function (res) {
        // 转发失败
      }
    }
  }

})


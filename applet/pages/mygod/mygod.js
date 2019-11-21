// pages/qrdata/qrdata.js
Page({
  data:{
userid:'',
logs:'',
userlist:'',
showbol:true,
showbol1:true,
showbol2:true,
fanlist:'',
referlist:'',
currentTab:'',
id:'',
  },
  swichNav: function (e) {
    console.log(e);
    var that = this;
   
      that.setData({
        currentTab: e.target.dataset.current,
      })
      console.log(e.target.dataset.current)



  },


  swiperChange: function (e) {


    var that=this
    console.log(e);
    this.setData({
      currentTab: e.detail.current,
    })


if(that.data.currentTab==0)
{

  wx.request({
    url: 'https://www.titwdj.cn/BorrowBook/MyGod',

    data: {
      userid: that.data.userid
    }
    ,
    success: function (res) {
      console.log(res.data)
      that.setData({
        userlist: res.data
      })

      if (that.data.userlist.length != 0) {
        that.setData({
          showbol: true
        })
      }
      else {
        that.setData({
          showbol: false
        })
      }

    }
  })



}
else if(that.data.currentTab==1)
{


  wx.request({
    url: 'https://www.titwdj.cn/BorrowBook/myFan',

    data: {
      userid: that.data.userid
    }
    ,
    success: function (res) {
      console.log(res.data)
      that.setData({
        fanlist: res.data
      })

      if (that.data.fanlist.length != 0) {
        that.setData({
          showbol1: true
        })
      }
      else {
        that.setData({
          showbol1: false
        })
      }
    }
  })

}
else
{


  wx.request({
    url: 'https://www.titwdj.cn/BorrowBook/ReferPerson',

    data: {
      userid: that.data.userid
    }
    ,
    success: function (res) {
      console.log(res.data)
      that.setData({
        referlist: res.data
      })

      if (that.data.referlist.length != 0) {
        that.setData({
          showbol2: true
        })
      }
      else {
        that.setData({
          showbol2: false
        })
      }
    }
  })



}




  },  
onLoad:function(options)
{

  var that=this
console.log(options.userid)
that.setData({
  userid:options.userid,
  id:options.id
})

console.log("//////"+that.data.id)


if(that.data.id=='0')
{
  wx.request({
    url: 'https://www.titwdj.cn/BorrowBook/MyGod',

    data: {
      userid: options.userid
    }
    ,
    success: function (res) {
      console.log(res.data)
      that.setData({
        userlist: res.data
      })

      if (that.data.userlist.length != 0) {
        that.setData({
          showbol: true
        })
      }
      else {
        that.setData({
          showbol: false
        })
      }

    }
  })


}
else{

  that.setData({

    currentTab:1
  })

  wx.request({
    url: 'https://www.titwdj.cn/BorrowBook/myFan',

    data: {
      userid: that.data.userid
    }
    ,
    success: function (res) {
      console.log(res.data)
      that.setData({
        fanlist: res.data
      })

      if (that.data.fanlist.length != 0) {
        that.setData({
          showbol1: true
        })
      }
      else {
        that.setData({
          showbol1: false
        })
      }
    }
  })
}

}






})
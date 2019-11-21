Page({  
    data: 
    {   
        currentTab: 0,  
        cutlist:'',
        discountlist:'',
        grouplist:'',
        logs:'',
        bol:false,

    },  

    swichNav: function (e) {
      console.log(e);
      var that = this;
      if (this.data.currentTab === e.target.dataset.current) {
        return false;
      }
      else {
        that.setData({
          currentTab: e.target.dataset.current,
        })
      }
    },
    swiperChange: function (e) {
      console.log(e);
      var that = this;
      this.setData({
        currentTab: e.detail.current,
      })

      if (that.data.currentTab == '0') {
        console.log("我是减价")
      }
      else if (that.data.currentTab == '1') {
        console.log("我是优惠券")

      }
      else {
        console.log("我是拼多多")
      }


    },  



    onShow: function () {

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

      if (value) 
      {

          wx.request({
            url: 'https://www.titwdj.cn/BorrowBook/getDiscountUser',
            data: {
              userid: that.data.logs.userid
            },
            success: function (res) {
              console.log(res.data)
              that.setData({
                discountlist: res.data
              })
            },
          })
        


        wx.request({
          url: 'https://www.titwdj.cn/BorrowBook/getUserInformationServlet',
          data: {
            userid: that.data.logs.userid
          },
          success: function (res) {
            console.log(res.data)
            that.setData({
              user: res.data
            })
          }
        })

      }
      else
      {

        wx.request({
          url: 'https://www.titwdj.cn/BorrowBook/getActivity',
          data: {
            action: '1'
          },
          success: function (res) {
            console.log(res.data)
            that.setData({

              discountlist: res.data
            })
          },
        })
      }
    },
    



    onLoad: function (options) 
    {  

      var that=this

   
    },  
    

    getCount:function(e)
    {
var that=this
var current=e.currentTarget.dataset

var grade=current.grade
    console.log(current.grade)
    console.log(current.id)


//会员优惠券
if(grade=='1')
{

  if (that.data.user.grade == grade) 
  {
    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/BuyGradeDiscount',
      data: {
        userid: that.data.logs.userid,
        countid: current.id
      },
      success: function (res) {


        wx.showToast({
          title: '领取成功！',
          icon: 'success',
          duration: 2000
        })
        console.log(res.data)
        wx.request({
          url: 'https://www.titwdj.cn/BorrowBook/getDiscountUser',
          data: {
            userid: that.data.logs.userid
          },
          success: function (res) {
            console.log(res.data)
            that.setData({
              discountlist: res.data
            })
          },
        })
      }
    })
  }
  else {
    wx.showModal({
      title: '提示',
      content: '会员专属优惠券,你无法领取，是否升级为会员！',
      success: function (res) {
        if (res.confirm) {
          wx.navigateTo({
            url: '../grade/grade',
          })
        } else if (res.cancel) {
          console.log('用户点击取消')
        }
      }
    })
  }


  
}

else
{
//普通优惠券

  wx.request({
    url: 'https://www.titwdj.cn/BorrowBook/BuyGradeDiscount',
    data: {
      userid: that.data.logs.userid,
      countid: current.id
    },
    success: function (res) {
      console.log(res.data)
      wx.showToast({
        title: '领取成功！',
        icon: 'success',
        duration: 2000
      })


      wx.request({
        url: 'https://www.titwdj.cn/BorrowBook/getDiscountUser',
        data: {
          userid: that.data.logs.userid
        },
        success: function (res) {
          console.log(res.data)
          that.setData({
            discountlist: res.data
          })
        },
      })
    }
  })




}

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

 
})  
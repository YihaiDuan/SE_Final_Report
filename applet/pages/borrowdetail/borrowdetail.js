Page({


data:{

borrowdetail:'',
borrowid:'',
logs:'',
beyondbol:'true',
money:'',
combol:false,
referlist:''

},

onShow:function()
{
  var that = this;

  try {
    var value = wx.getStorageSync('key')
    if (value) {
      that.setData({
        logs: value

      })

    }
  }
  catch (e) {

  }


},

onLoad:function(options)
{
var that=this

console.log(options.borrowid)

wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/ShowBorrowByid',
  data: {

borrowid:options.borrowid

  },
  method: 'GET', 
  success: function(res)
  {
   console.log(res.data)

that.setData({

borrowdetail:res.data

})
//that.data.borrowdetail.fine 
//     if (that.data.borrowdetail.fine > 0) {
//   console.log("不能转借")

// that.setData({
// beyondbol:'false'
// })
// console.log(that.data.beyondbol)

// wx.request({
//   url: 'http://139.199.23.184/BorrowBook/getUserInformationServlet',
//   data: {
//     userid: that.data.logs.userid
//   },
//   success: function (res) {
//     console.log(res.data)
//     that.setData({
//       money: res.data.money
//     })
//     console.log("money" + that.data.money)
//     console.log("fine" + that.data.borrowdetail.fine)

//     if (parseInt(that.data.money) >= parseInt(that.data.borrowdetail.fine)) {
//       that.setData({
//         combol: true
//       })
//     }
//     else {
//       that.setData({
//         combol: false
//       })
//     }
//   }
// })

 
// }
//可以转借
// else
// {

//   console.log("可以转借")
// that.setData({
//   beyondbol: 'true'
// })
  
// }


  },
 
})


  wx.request({
    url: 'https://www.titwdj.cn/BorrowBook/getReferBookSix',
    data: {

      borrowid: options.borrowid,
      action:'borrow'
    },
    success: function (res) {
      console.log(res.data)

      that.setData({

        referlist: res.data
      })

    }
  })
},


havefree:function(e)
{
var current=e.currentTarget.dataset

var that=this
console.log(current.free)
console.log(current.id)
console.log(that.data.logs.userid)

  wx.showModal({
    title: '提示',
    content: '是否还清欠费金额',
    success: function (res) {
      if (res.confirm) 
      {
        console.log('用户点击确定')
      } 
      else if (res.cancel) 
      {
        console.log('用户点击取消')
      }
    }
  })

},

  

//转借
// transmit:function()
// {

//   var that=this
 
// console.log("可以转借")

//   wx.navigateTo({
//     url: '../transmitQR/transmitQR?id=' +that.data.borrowdetail.id,
//   })



// },



//续借
  newborrow:function(e)
  {
    var that = this
    console.log(e)
console.log(e.detail.formId)
    wx.showModal({
      title: '提示',
      content: '确认续借该图书!',
      success: function (res) {
        if (res.confirm) {
          console.log('用户点击确定')



          wx.request({
            url: 'https://www.titwdj.cn/BorrowBook/RenewPayServlet',
            data: {
              borrowid: that.data.borrowdetail.id,
              formid: e.detail.formId,
            },

            success: function (res) 
            {

              wx.showToast({
                title: '续借成功!',
                icon: 'success',
                duration: 2000
              })


              console.log(res.data)
              wx.request({
                url: 'https://www.titwdj.cn/BorrowBook/ShowBorrowByid',
                data: {

                  borrowid: that.data.borrowdetail.id

                },
                method: 'GET',
                success: function (res) {
                  console.log(res.data)

                  that.setData({

                    borrowdetail: res.data

                  })
                  //that.data.borrowdetail.fine 
                  if (that.data.borrowdetail.fine > 0) {
                    console.log("不能转借")

                    that.setData({
                      beyondbol: 'false'
                    })
                    console.log(that.data.beyondbol)

                    wx.request({
                      url: 'https://www.titwdj.cn/BorrowBook/getUserInformationServlet',
                      data: {
                        userid: that.data.logs.userid
                      },
                      success: function (res) {
                        console.log(res.data)
                        that.setData({
                          money: res.data.money
                        })
                        console.log("money" + that.data.money)
                        console.log("fine" + that.data.borrowdetail.fine)

                        if (parseInt(that.data.money) >= parseInt(that.data.borrowdetail.fine)) {
                          that.setData({
                            combol: true
                          })
                        }
                        else {
                          that.setData({
                            combol: false
                          })
                        }
                      }
                    })


                  }
                  //可以转借
                  else {

                    console.log("可以转借")
                    that.setData({
                      beyondbol: 'true'
                    })

                  }


                },

              })



            }
          })

        } else if (res.cancel) {
          console.log('用户点击取消')
        }
      }
    })


  },


  //支付
  pay: function (e) {

    var that = this;
    console.log("逾期费用"+that.data.borrowdetail.fine)
    console.log("id" + that.data.borrowdetail.id)
    console.log(e)


    wx.showModal({
      title: '提示',
      content: '确认支付!',
      success: function (res) {

        if (res.confirm) {

          //进行扣款
          wx.request({
            url: 'https://www.titwdj.cn/BorrowBook/FinePayServlet',
            data:
            {
              userid: that.data.logs.userid,
              cash: that.data.borrowdetail.fine,
              borrowid:that.data.borrowdetail.id

            },
            success: function (res) {
              console.log(res.data)
              wx.showToast({
                title: '支付成功!',
                icon: 'success',
                duration: 2000
              })


              wx.request({
                url: 'https://www.titwdj.cn/BorrowBook/ShowBorrowByid',
                data: {

            borrowid: that.data.borrowdetail.id

                },
                method: 'GET',
                success: function (res) {
                  console.log(res.data)

                  that.setData({

                    borrowdetail: res.data

                  })
                  //that.data.borrowdetail.fine 
                  // if (that.data.borrowdetail.fine > 0) {
                  //   console.log("不能转借")

                  //   that.setData({
                  //     beyondbol: 'false'
                  //   })
                  //   console.log(that.data.beyondbol)

                  //   wx.request({
                  //     url: 'http://139.199.23.184/BorrowBook/getUserInformationServlet',
                  //     data: {
                  //       userid: that.data.logs.userid
                  //     },
                  //     success: function (res) {
                  //       console.log(res.data)
                  //       that.setData({
                  //         money: res.data.money
                  //       })
                  //       console.log("money" + that.data.money)
                  //       console.log("fine" + that.data.borrowdetail.fine)

                  //       if (parseInt(that.data.money) >= parseInt(that.data.borrowdetail.fine)) {
                  //         that.setData({
                  //           combol: true
                  //         })
                  //       }
                  //       else {
                  //         that.setData({
                  //           combol: false
                  //         })
                  //       }
                  //     }
                  //   })


                  // }
                  //可以转借
                  // else {

                  //   console.log("可以转借")
                  //   that.setData({
                  //     beyondbol: 'true'
                  //   })

                  // }


                },

              })





            }

          })


        } else if (res.cancel) {
          console.log('用户点击取消')
        }
      }
    })

  },




})
Page({


 data:{

   booknum:''
   
  },


onShow:function()
{

var that =this

  wx.showLoading({
  title: '加载中',
}),
wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/ShowBookNum',

  success: function(res){

    console.log(res.data)
    that.setData({

   booknum:res.data

    })
    setTimeout(function(){
   wx.hideLoading()
     },1000)
  },
  
})
},

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
          console.log("转借二维码")
          console.log("id" + res.result.substring(14, res.result.length))
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




        wx.request({
          url: 'https://www.titwdj.cn/BorrowBook/ScanIsbnServlet',
          data: {

            isbn: res.result

          },

          success: function (res) {


            console.log(res.data)
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

    }
  })

},





})
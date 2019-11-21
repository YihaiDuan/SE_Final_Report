Page({


 data:{

   booknew:''
   
  },


onShow:function()
{


var that =this
  wx.showLoading({
  title: '加载中',
}),
wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/ShowBookNew',

  success: function(res){

    console.log(res.data)
    that.setData({

   booknew:res.data

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


      
       if (res.scanType == 'EAN_13') {




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
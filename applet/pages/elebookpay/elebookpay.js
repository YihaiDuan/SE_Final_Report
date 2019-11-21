Page({


data:{

bookid:'',
logs:'',
money:'',
combol:false,
bookdetail:'',
realmoney:'',

showselect:true,
showdiscount:'true',
totalpay:'',
typestatus:'',
discountid:'',
yuanpay:''
},

onLoad:function(options)
{

  var that=this

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

console.log(options.bookid);

that.setData({
bookid:options.bookid
})
wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/BookDetailServlet',
   data:{
    bookid:options.bookid
   },
   success:function(res)
   {
    console.log(res.data)
    that.setData({
bookdetail:res.data,
totalpay:res.data.eleprice,
yuanpay:res.data.eleprice
})

    console.log("价格" + that.data.bookdetail.eleprice)

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


        if (parseInt(that.data.money) >= parseInt(that.data.bookdetail.eleprice)) {
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
})




},


//支付
pay: function (e) {

  var that = this;


  wx.showModal({
    title: '提示',
    content: '确认支付!',
    success: function (res) {
      if (res.confirm) 
      {

        var pages = getCurrentPages();
        var prevPage = pages[pages.length - 2]; 
        prevPage.setData({
          readbol:true
        })


if(that.data.discountid!='')
{
        console.log("我是需要改变的打折id" + that.data.discountid)


        wx.request({
          url: 'https://www.titwdj.cn/BorrowBook/UpdateDiscount',

          data: {
            id: that.data.discountid
          },
          success: function (res) {
            console.log("优惠券更新标志位成功!")

          }

        })

}


     
        wx.request({
          url: 'https://www.titwdj.cn/BorrowBook/PayEleBook',
          data:
          {
            userid: that.data.logs.userid,
            bookid: that.data.bookid,
            realmoney: that.data.totalpay,
         
          },
          success: function (res) {
            console.log(res.data)
wx.redirectTo({
  url: '../paysuccess/paysuccess?action=ele',
})
          }

        })
     
      } else if (res.cancel) {
        console.log('用户点击取消')
      }
    }
  })

},

getdiscount:function()
{

  var that=this
that.setData({
  showdiscount:true
})


} , 
  delete: function () {

    var that = this
    that.setData({

      showdiscount: false
    })

  },
})
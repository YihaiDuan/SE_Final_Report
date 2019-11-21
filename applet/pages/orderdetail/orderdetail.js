Page({


data:{

orderdetail:'',
orderid:'',
referlist:''
},

onLoad:function(options)
{
var that=this

console.log(options.orderid)

wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/ShowOrderByid',
  data: {

orderid:options.orderid

  },
  method: 'GET', 
  success: function(res)
  {
   console.log(res.data)

that.setData({

orderdetail:res.data

})


  },
 
})



  wx.request({
    url: 'https://www.titwdj.cn/BorrowBook/getReferBookSix',
    data: {

      orderid: options.orderid,
      action:'order'
    },
    success: function (res) {
      console.log(res.data)

      that.setData({

        referlist: res.data
      })

    }
  })

}

})
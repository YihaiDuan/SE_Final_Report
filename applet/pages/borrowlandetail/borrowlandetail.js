Page({


data:{

borrowdetail:'',
borrowid:'',
referlist:'',

},

onLoad:function(options)
{
var that=this

console.log(options.borrowlanid)

wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/ShowBorrowLanByid',
  data: {

borrowid:options.borrowlanid

  },
  method: 'GET', 

  success: function(res)
  {
   console.log(res.data)

that.setData({

borrowdetail:res.data

})

},
 
})

wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/getReferBookSix',
  data:{

    borrowid:options.borrowlanid,
    action:'borrow'
  },
  success:function(res)
  {
    console.log(res.data)

    that.setData({

      referlist:res.data
    })

  }
})

}

})
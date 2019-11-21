// pages/mynews/mynews.js
Page({
  data:{
    
     logs:"",
     user:'',
     discountlist:'',
     canuser:'',
     pay:'',
     totalpay:''
      
  },

onLoad:function(options)
{

  var that=this
console.log(options.canuser)

that.setData({
  canuser:options.canuser
})


   that.setData({

totalpay:options.totalpay
   })

console.log("我是pay"+that.data.totalpay)

},
  onShow:function()
  {

    var that=this;
 try {
  var value = wx.getStorageSync('key')
  if (value)
   {
that.setData({
logs:value

})

  }
}
 catch (e) {

}

wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/mydiscount',
data:{
  userid:that.data.logs.userid
},
success:function(res)
{
console.log(res.data)
that.setData({
  discountlist:res.data
})
}
})
   
  },



selectdiscount:function(e)
{

    var that = this
    console.log(e)
    var current = e.currentTarget.dataset;
    console.log(current.typestatus)
    console.log(current.discount)
    console.log(current.id)




    //代金券
    if (current.typestatus == '1') {
      if (parseInt(current.discount) < parseInt(that.data.totalpay)) {
     
        prevPage.setData({
          totalpay: parseInt(that.data.totalpay) - parseInt(current.discount),
          typestatus: current.typestatus,
          discount: current.discount,
          showdiscount: 'false',
          discountid: current.id
        })
        
        wx.navigateBack({
          delta: 1,

        })

      }
      else {

        console.log("优惠券更新标志位成功!")
        that.setData({
          totalpay: 0,
        })
        console.log("代金后" + that.data.totalpay)

        var pages = getCurrentPages();
        var prevPage = pages[pages.length - 2];
        console.log("原来的金额" + prevPage.data.totalpay)


        prevPage.setData({

          totalpay: 0,
          typestatus: current.typestatus,
          discount: current.discount,
          showdiscount: 'false',
          discountid: current.id
        })

        wx.navigateBack({
          delta: 1,

        })


      }
    }
    else {
      console.log("优惠券更新标志位成功!")


      var pages = getCurrentPages();
      var prevPage = pages[pages.length - 2];
      console.log("原来的金额" + prevPage.data.totalpay)


      prevPage.setData({

        totalpay: (that.data.totalpay * current.discount).toFixed(2),
        typestatus: current.typestatus,
        discount: current.discount,
        showdiscount: 'false',
        discountid: current.id
      })

      wx.navigateBack({
        delta: 1,

      })

    }
  },



})
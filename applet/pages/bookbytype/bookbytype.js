// pages/chosen/chosen.js
Page({
  data:{

    array:'',
    typename:''

  },
  onLoad:function(options)
  {
    
    var that=this;
    console.log(options.typeid)
    console.log(options.typename)

    that.setData({

typename:options.typename

    })

wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/ShowBookByTypyid',
  data: {

    typeid:options.typeid
  },

  success: function(res)
  {
    console.log(res.data)
    that.setData({

  array:res.data

    })

  },
  
})




  },
  
})
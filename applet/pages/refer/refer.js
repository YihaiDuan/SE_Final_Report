// pages/chosen/chosen.js
Page({
  data:{

referlist:'',

logs:''
  },

  onShow:function()
  {
 var that=this

 try {
  var value = wx.getStorageSync('key')
  if (value) {
   
that.setData({
bol:true,
logs:value

})

  }
} catch (e) {
  
}
  
wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/ShowReferServlet',

  data: {

userid:that.data.logs.userid

  },
  
  success: function(res)
  {
  
  console.log(res.data)
that.setData({

referlist:res.data

})

  },
 
})


  },
 

clickrefer:function(e)
{
 console.log("我来了")
 console.log(e.currentTarget.dataset.referid)

 var current=e.currentTarget.dataset;

console.log(current.bookid)

wx.navigateTo({
  url: '../particulars/particulars?bookid='+current.bookid+"&advice="+current.advice,
  success: function(res){
   
  },
  
})



wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/UpdataReferStatus',
  data: 
  {

      referid:e.currentTarget.dataset.referid
  },
  
  success: function(res)
  {

    console.log(res.data)

  },
 
})

},


deleterefer:function(e)
{
var that=this
 var current=e.currentTarget.dataset;
console.log(current.referid)
wx.showModal({
  title: '提示',
  content: '是否删除',
  success: function(res) {
    if (res.confirm) {
      console.log('用户点击确定')
   
wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/DeleteReferServlet',

  data: {

      referid:current.referid,
      userid:that.data.logs.userid

  },
  method: 'GET',

  success: function(res)
  {
    console.log(res.data)
  
that.setData({


referlist:res.data
})

wx.showToast({
  title: '删除成功!',
  icon: 'success',
  duration: 2000
})
  },

})

   
   
    } else if (res.cancel) {
      console.log('用户点击取消')
    }
  }
})

}


})
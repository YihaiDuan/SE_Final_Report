Page({
data:{
 userid:'',
logs:'',
BorrowQR:'',
showbol:true,
},

onLoad:function()
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

console.log(that.data.logs.userid)

wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/getGroupQR',
data:{
  userid:that.data.logs.userid
},
success:function(res)
{
console.log(res.data)

if(res.data!=0)
{
that.setData({
  BorrowQR:res.data,
  showbol:true
})
}
else
{
  that.setData({
    
    showbol:false
  })

}

}
})



},

onShow: function()
{

  var that=this
  that.data.a = setInterval(
    function () {


      wx.request({
        url: 'https://www.titwdj.cn/BorrowBook/getGroupQR',
        data: {
          userid: that.data.logs.userid
        },
        success: function (res) {
          console.log(res.data)

          if (res.data != 0) {
            that.setData({
              BorrowQR: res.data,
              showbol: true
            })
          }
          else {
            that.setData({

              showbol: false
            })

          }
console.log(that.data.showbol)
        }
      })



    }, 1000)
  },

   onUnload: function () {

  clearInterval(this.data.a);
  // clearInterval(this.data.b);
},

onHide: function () {
  clearInterval(this.data.a);
  // clearInterval(this.data.b);
},

})
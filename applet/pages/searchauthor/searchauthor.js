// pages/search/search.js
Page({
  data:{

  citys:["关键字","图书标题","图书编号","作者"],
  index:3,
  array:'',
  search:'',
  id:'',
  history:true,
    inputValue: '',
    searchsynlist:'',
    histroybol:true,
    author:'',
    searchlist:''
  },


bindPickerChanger:function(e){

  var that=this
    console.log(e.detail.value);
  this.setData({index:e.detail.value})

that.setData({

  author:''
})
  },


onLoad: function(options)
{
console.log(options.author)
var that=this
that.setData({
  author:options.author
})

wx.showLoading({
  title: '加载中',
}),
  wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/SearchServlet',
    data:
    {
      search: that.data.author,
      action: '3'
    },
    method: 'POST',
    header: {
      'content-type': 'application/x-www-form-urlencoded'
    },

    success: function (res) {
      console.log(res.data);
      that.setData({
        searchlist: res.data
      }
      )

      setTimeout(function () {
        wx.hideLoading()
      }, 1000)
    },
  })



},



formSubmit:function(e)
{

  var that=this
  var record=''

  console.log(that.data.index)

  that.setData({
   search:e.detail.value.search
  })

console.log(e.detail.value.search)

  wx.navigateTo({
    url: '../result/result?search=' + that.data.search + '&action=' + that.data.index
  })



},


})
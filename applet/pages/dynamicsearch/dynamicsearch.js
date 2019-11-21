// pages/comment/comment.js
var app = getApp();
Page({
  data:{
  
    nickname:"",
    userid:"",
    logs:'',
    pics: [],
   

    citys: ["关键字", "图书标题", "图书编号", "作者"],

    searchbol: false,
    index: 0,
    array: '',
    search: '',
    id: '',
    inputValue: '',
    searchsynlist: '',
    collectlist: '',
    boxbol:true,
    bookbol:true,
    bookid:'',
    booktitle:'',
    searchlist:'',
    content:'',
    typeid:'',
    bookimages:'',
    author:'',
    searchbol1:false,
    searchbol2:false
  },

  bindPickerChanger: function (e) {
    console.log(e.detail.value);
    this.setData({ index: e.detail.value })

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




wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/ShowMyCollect',
  data: {
    userid: that.data.logs.userid
  },
  success: function (res) {
    console.log(res.data)
    that.setData({
      collectlist: res.data
    })
  },
})

},
 

//搜索函数
  formSubmit: function (e) {

    var that = this
    console.log(that.data.index)
    that.setData({
      search: e.detail.value.search
    })
    console.log(e.detail.value.search)
    wx.showLoading({
      title: '加载中',
    }),
    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/SearchServlet',
      data:
      {
        search: that.data.search,
        action: that.data.index
      },
      method: 'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        setTimeout(function () {
          wx.hideLoading()
        }, 1000)
        console.log(res.data);
        that.setData({
          searchlist: res.data
        })
        if (that.data.size!= 0) {
          that.setData({
            searchbol1: true
          })
     
        }
        else
        {
          that.setData({
            searchbol2: true
          })
        }
      },
    })


  },


selectbook:function(e)
{
  var that = this;
  console.log(e)
  var current = e.currentTarget.dataset

console.log(current.bookid)
console.log(current.booktitle)


var pages = getCurrentPages();
var prevPage = pages[pages.length - 2];
console.log(prevPage.data.bookid)

var index = prevPage.data.index

prevPage.setData({
bookid:current.bookid,
booktitle:current.booktitle,
bookbol:false,
typeid:current.typeid,
bookimages:current.bookimages,
author:current.author
})

wx.navigateBack({
  delta: 1,
})

},
  
})

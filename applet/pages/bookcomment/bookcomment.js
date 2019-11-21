// pages/comment/comment.js
var app = getApp();
Page({
  data:{

    userid:"",
    logs:'',

    nickname: "",
  
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
    boxbol: true,
    bookbol: true,
    bookid: '',
    booktitle: '',
    searchlist: '',
    content: '',
    title:'',
    typeid: '',
    bookimages: '',
    author: '',
    searchbol1:false,
     searchbol2: false
 
  },

  bindPickerChanger: function (e) {
    console.log(e.detail.value);
    this.setData({ index: e.detail.value })

  },

  inputcontent: function (e) {
    console.log(e)
    var that = this
    that.setData({
      content: e.detail.value
    })

    console.log(that.data.content)
  },


  inputtitle: function (e) {
    console.log(e)
    var that = this
    that.setData({
      title: e.detail.value
    })

    console.log(that.data.title)
  },


  onShow: function () {

    // 页面渲染完成
    var that = this;

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
          if (that.data.size != 0) {
            that.setData({
              searchbol1: true
            })

          }
          else {
            that.setData({
              searchbol2: true
            })
          }
        },
      })


  },

submit:function(e)
{
  wx.showLoading({
    title: '加载中',
  })
  var that=this
  var values=e.detail.value
  var userid=that.data.logs.userid
  console.log(values.describ)
 console.log(userid)
 console.log(values.title)
if(values.describ==''||values.title=='')
{
  wx.showToast({
    title: '输入内容不能为空!',
    icon: 'success',
    duration: 2000
  })


}
else
{

 if (that.data.bookid == '') {
   wx.showToast({
     title: '请选择要评论的图书!',
     icon: 'success',
     duration: 5000
   })

 }
 else{

   wx.request({
     url: 'https://www.titwdj.cn/BorrowBook/AddBookComment',
     data: {
       userid: userid,
       describ: values.describ,
       title: values.title,
       bookid: that.data.bookid

     },

     method: 'POST',
     header: {
       'content-type': 'application/x-www-form-urlencoded'
     },
     success: function (res) {
       console.log(res.date)
    

       wx.reLaunch({
         url: '../dynamic/dynamic',
       })

     }
   })

 }

}//if
},

changesearch: function () {
wx.navigateTo({
  url: '../dynamicsearch/dynamicsearch',
})
},




selectbook: function (e) {
  var that = this;
  console.log(e)
  var current = e.currentTarget.dataset

  console.log(current.bookid)
  console.log(current.booktitle)
  that.setData({
    boxbol: true,
    searchbol: false,
    bookid: current.bookid,
    booktitle: current.booktitle,
    bookbol: false,
    typeid: current.typeid,
    bookimages: current.bookimages,
    author: current.author

  })

},

nologin: function () {

  wx.showModal({
    title: '提示',
    content: '你尚登录,请先登录!',
    success: function (res) {
      if (res.confirm) {
        console.log('用户点击确定')

        wx.navigateTo({

          url: '../register/register',


        })


      }
      else if (res.cancel) {
        console.log('用户点击取消')
      }
    }
  })


},
  
})

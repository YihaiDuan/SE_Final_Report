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

input:function(e)
{
console.log(e)
var that=this
that.setData({
   content:e.detail.value
})

console.log(that.data.content)
},

 onLoad:function(options)
{
var that=this
  console.log(options.bookid)
if (options.bookid!=undefined)
{
console.log("我是从分享那边过来的")

wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/BookDetailServlet', //仅为示例，并非真实的接口地址
  data: {
    bookid: options.bookid
  },
  header: {
    'content-type': 'application/json'
  },
  success: function (res) {
    console.log(res.data);
    that.setData({
      bookid: options.bookid,
      booktitle: options.booktitle,
      bookbol: false,
     author:res.data.author,
     bookimages:res.data.bookimages,
     typeid:res.data.category_id
    })
  
  }
})

}
else{
  console.log("我是从动态过来的")
}

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


//图片预览上传
  choose:function() {//这里是选取图片的方法
    var that = this;
    wx.chooseImage({
      count: 9, // 最多可以选择的图片张数，默认9
      sizeType: ['original', 'compressed'], // original 原图，compressed 压缩图，默认二者都有
      sourceType: ['album', 'camera'], // album 从相册选图，camera 使用相机，默认二者都有
      success: function (res) {
        var imgsrc = res.tempFilePaths;

console.log(res.tempFilePaths)

        that.setData({
          pics: imgsrc
        });
      },

    })

  },


//动态上传
submit:function(e)
{

  var that=this
  var pics = this.data.pics;
  var values=e.detail.value
  var userid=that.data.logs.userid

  console.log(values.describ)
 console.log(userid)
 wx.showLoading({
   title: '加载中',
 })

 if(values.describ=='')
 {

   wx.showToast({
     title: '输入内容不能为空!',
     icon: 'success',
     duration: 2000
   })


 }
 else
 {

 if(that.data.bookid=='')
 {
   wx.showToast({
     title: '请选择分享的图书',
     icon: 'success',
     duration: 5000
   })

 }
 else
 {
 
 //有上传图片
if(pics.length!='0')
{
  wx.uploadFile({
    url: 'https://www.titwdj.cn/BorrowBook/AddDynamicServlet',

    filePath: pics[0],
    name: 'file',
    formData: {
      userid:userid,
      describ: values.describ,
      bookid: that.data.bookid,
    },
    success: function (res) {
      var data = res.data

     

      wx.navigateTo({
        url: '../dynamic/dynamic?action=senddynamic'
      })

      console.log("dynamicid===="+data)
      for (var i = 1; i < pics.length; i++) {

        wx.uploadFile({
          url: 'https://www.titwdj.cn/BorrowBook/AddDynamicUpdatePic',

          filePath: pics[i],
          name: 'file',
          formData: {
            dynamicid: res.data,
            userid:that.data.logs.userid,
            id:i,
          },
          success: function (res) {
            var data = res.data
            console.log(data)
         
        


          }
        })
      }

    }
  })
}
else
{
//没有上传图片
wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/AddDynamicNoPic',
data:{
  userid:userid,
  describ:values.describ,
  bookid:that.data.bookid,
},
method: 'POST',
header: {
  'content-type': 'application/x-www-form-urlencoded'
},
success:function(res)
{

  console.log(res.data)
  console.log("发表成功没有带图片")
  wx.navigateTo({
    url: '../dynamic/dynamic?action=senddynamic'
  })
}
})

}//else


 }//if 有图片

 }//if
},


//跳页
changesearch:function()
{

wx.navigateTo({
  url: '../dynamicsearch/dynamicsearch',
})


},




selectbook:function(e)
{
  var that = this;
  console.log(e)
  var current = e.currentTarget.dataset

console.log(current.bookid)
console.log(current.booktitle)
that.setData({
boxbol:true,
searchbol:false,
bookid:current.bookid,
booktitle:current.booktitle,
bookbol:false,
typeid:current.typeid,
bookimages:current.bookimages,
author:current.author

})

},
  
})

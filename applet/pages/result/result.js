//index.js
//获取应用实例
var app = getApp()

Page({
  data: {
    title:"加载中",
    movies:"",
    search:'',
    action:'',
    logs:'',
    bol:false,

    searchKeyword: '',  //需要搜索的字符  
    searchSongList: [], //放置返回数据的数组  
    isFromSearch: true,   // 用于判断searchSongList数组是不是空数组，默认true，空的数组  
    searchPageNum: 1,   // 设置加载的第几次，默认是第一次  
    callbackcount: 15,      //返回数据的个数  
    searchLoading: false, //"上拉加载"的变量，默认false，隐藏  
    searchLoadingComplete: false,  //“没有数据”的变量，默认false，隐藏  

   all:'',

  },


 onLoad:function(options)
{

var that=this;
     console.log(options.action)
     console.log(options.search)

     that.setData({

       action:options.action,
       search:options.search
     })


//进行搜索
if(options.action=='2')
{

  wx.showLoading({
    title: '加载中',
  }),

  console.log("我是根据图书标号搜索的")
  wx.request({
    url: 'https://www.titwdj.cn/BorrowBook/SearchPage',


    data:
    {

      search: escape(options.search),
      action: options.action

    },


    success: function (res) {
      console.log(res.data);
if(res.data.size!=0)
{

  that.setData({
    movies: res.data.list,
    all: res.data.size
  })

}
else
{
  that.setData({
    all: '0'
  })


}
     setTimeout(function () {
        wx.hideLoading()
      }, 1000)


    },

  })

}
else
{

  wx.showLoading({
    title: '加载中',
  }),

  that.fetchSearchList(options.search, options.action);  

}


try {
  var value = wx.getStorageSync('key')


  if (value) {
    that.setData({
      logs: value,
      bol: true

    })

  }
}
catch (e) {

}




}
,



 //搜索，访问网络  
 fetchSearchList: function (search,action) {

   console.log("传参过来的" + search);
   console.log("传参过来的" + action);

   let that = this;
  
  
   //访问网络  
   getSearchMusic(search, that.data.searchPageNum,action, function (data) {
     console.log(data)
      
     console.log(data.curnum)
      that.setData({
        all:data.size
      })

      that.setData({
        curnum: data.curnum
      })

     //判断是否有数据，有则取数据  
     if (data.curnum != '0') {
       let searchList = [];
       //如果isFromSearch是true从data中取出数据，否则先从原来的数据继续添加  
       that.data.isFromSearch ? searchList = data.list : searchList = that.data.movies.concat(data.list)
       that.setData({
         movies: searchList, //获取数据数组  
         searchLoading: true   //把"上拉加载"的变量设为false，显示  
       });
       //没有数据了，把“没有数据”显示，把“上拉加载”隐藏  
     } else {
       that.setData({
         searchLoadingComplete: true, //把“没有数据”设为true，显示  
         searchLoading: false  //把"上拉加载"的变量设为false，隐藏  
       });
     }

     if (data.size <= parseInt(10)) {

       console.log("我进来了")
       that.setData({

         searchLoadingComplete: true, //把“没有数据”设为true，显示  
         searchLoading: false
       })
     }


     if (that.data.searchLoadingComplete)
{
       searchLoading: false 

}

   })

 },

 //滚动到底部触发事件  
 onbottom: function () {
   console.log("我下拉了")
   let that = this;

   if (that.data.searchLoading && !that.data.searchLoadingComplete) {
     that.setData({
       searchPageNum: that.data.searchPageNum + 1,  //每次触发上拉事件，把searchPageNum+1  
       isFromSearch: false  //触发到上拉事件，把isFromSearch设为为false  
     });

     that.fetchSearchList(that.data.search,that.data.action);
   }


 },




searchcount:function(e)
{
var current =e.currentTarget.dataset
console.log(e)
console.log(current.bookid)
var that=this

wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/HotSearchServlet',
data:{
bookid:current.bookid
},
success:function(res)
{
console.log(res.data)

}

})


if(that.data.bol)
{

wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/UserActionServlet',
  data: {
    userid: that.data.logs.userid,
    bookid: current.bookid,
    action: "usersearch"
  },
  success: function (res) {
    console.log("更新成功")


  }
})
}

},






})





function getSearchMusic(search, pageindex, action, callback) {
  wx.request({
    url: 'https://www.titwdj.cn/BorrowBook/SearchPage',
    data: {
     action:action,
     search:search,
     pc:pageindex
    },
    method: 'POST',
    header: {
      'content-type': 'application/x-www-form-urlencoded'
    },
    success: function (res) {

      setTimeout(function () {
        wx.hideLoading()
      }, 1000)
      if (res.statusCode == 200) {
        callback(res.data);
        console.log(res.data)
      }
    }
  })
}

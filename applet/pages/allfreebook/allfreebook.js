// pages/special/special.js
Page({
  data: {
    reducelist: '',
    //降价分页数据
    action: 3,


    searchSongList: [], //放置返回数据的数组  
    isFromSearch: true,   // 用于判断searchSongList数组是不是空数组，默认true，空的数组  
    searchPageNum: 1,   // 设置加载的第几次，默认是第一次  
    searchLoading: false, //"上拉加载"的变量，默认false，隐藏  
    searchLoadingComplete: false,  //“没有数据”的变量，默认false，隐藏  

  },

  /*
    onLoad:function(options)
    {
       var that=this
  
       wx.showLoading({
         title: '加载中',
       }),  
  
     wx.request({
       url: 'https://www.xunjia.net.cn/BorrowBook/AllReduce',
  
       
       success:function(res)
       {
  
         setTimeout(function () {
           wx.hideLoading()
         }, 1000)
        console.log(res.data)
        that.setData({
         reducelist:res.data
        })
       }
     })
  
  
    },
  
  */
  onLoad: function (options) {

    wx.showLoading({
      title: '加载中',
    })

    this.fetchSearchList();

  },





  //搜索，访问网络  
  fetchSearchList: function () {

    let that = this;

    var searchPageNum = that.data.searchPageNum,//把第几次加载次数作为参数  
      callbackcount = that.data.callbackcount; //返回数据的个数  
    //访问网络  
    getSearchMusic(searchPageNum, that.data.action, function (data) {
      console.log(data)

      console.log(data.curnum)

      //判断是否有数据，有则取数据  
      if (data.curnum != '0') {
        let searchList = [];
        //如果isFromSearch是true从data中取出数据，否则先从原来的数据继续添加  
        that.data.isFromSearch ? searchList = data.list : searchList = that.data.reducelist.concat(data.list)
        that.setData({
          reducelist: searchList, //获取数据数组  
          searchLoading: true   //把"上拉加载"的变量设为false，显示  
        });
        //没有数据了，把“没有数据”显示，把“上拉加载”隐藏  
      } else {
        that.setData({
          searchLoadingComplete: true, //把“没有数据”设为true，显示  
          searchLoading: false  //把"上拉加载"的变量设为false，隐藏  
        });
      }
      if (that.data.searchLoadingComplete) {
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

      that.fetchSearchList();
    }

    if (that.data.searchLoadingComplete) {
      searchLoading: false

    }

  },

})






function getSearchMusic(pageindex, action, callback) {

  wx.request({
    url: 'https://www.xunjia.net.cn/BorrowBook/ShowAllIndexPage',
    data: {
      pc: pageindex,
      action: action
    },
    method: 'GET',
    header: { 'content-Type': 'application/json' },
    success: function (res) {
      if (res.statusCode == 200) {
        callback(res.data);
        console.log(res.data)
      }

      setTimeout(function () {
        wx.hideLoading()
      }, 1000)
    }
  })
}

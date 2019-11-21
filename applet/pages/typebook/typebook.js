Page({  
    data: 
    {   
        currentTab: 0,  
        hotlist:'',
        goodlist:'',
        pricelist:'',
        pricebol:'0',
        showloadall:true,
        clientheight:0,
        action:'0',
       

// 热门图书分页
   searchKeyword: '',  //需要搜索的字符  
        searchSongList: [], //放置返回数据的数组  
        isFromSearch: true,   // 用于判断searchSongList数组是不是空数组，默认true，空的数组  
        searchPageNum: 1,   // 设置加载的第几次，默认是第一次  
        callbackcount: 15,      //返回数据的个数  
        searchLoading: false, //"上拉加载"的变量，默认false，隐藏  
        searchLoadingComplete: false,  //“没有数据”的变量，默认false，隐藏  

        all: '',





    },  


    swichNav: function (e) 
    {  
        console.log(e);  
     var that=this
            that.setData({  
                currentTab: e.target.dataset.current,  
            })  
      if (that.data.currentTab == '2')
      {

        that.setData({
          searchPageNum: 1,   //第一次加载，设置1  
          searchSongList: [],  //放置返回数据的数组,设为空  
          isFromSearch: true,  //第一次加载，设置true  
          searchLoading: true,  //把"上拉加载"的变量设为true，显示  
          searchLoadingComplete: false //把“没有数据”设为false，隐藏  
        })  

if(that.data.pricebol==0)
{
that.setData({
  pricebol:'1'
})
}
        if(that.data.pricebol=='1')
        {
          that.setData({
            searchPageNum: 1,   //第一次加载，设置1  
            searchSongList: [],  //放置返回数据的数组,设为空  
            isFromSearch: true,  //第一次加载，设置true  
            searchLoading: true,  //把"上拉加载"的变量设为true，显示  
            searchLoadingComplete: false, //把“没有数据”设为false，隐藏  
         action:'2'
          })  
        
          console.log("我是低到高"+that.data.action)
          
          wx.showLoading({
            title: '加载中',
          })
          that.fetchSearchList(that.data.cateid, '2'); 
          that.setData({
            pricebol: 2
          })
        }
        else
        {
          that.setData({
            searchPageNum: 1,   //第一次加载，设置1  
            searchSongList: [],  //放置返回数据的数组,设为空  
            isFromSearch: true,  //第一次加载，设置true  
            searchLoading: true,  //把"上拉加载"的变量设为true，显示  
            searchLoadingComplete: false, //把“没有数据”设为false，隐藏  
            action: '3'
          })  

          wx.showLoading({
            title: '加载中',
          }),

          console.log("我是高到低action"+that.data.action)
        
          that.fetchSearchList(that.data.cateid, '3'); 
          that.setData({
            pricebol:1
          })
          
        }
        console.log("我在变" + that.data.currentTab)
      }
     
   
    
    },  



    swiperChange: function (e) {  
        console.log(e);  
        var that=this;
        this.setData({  
            currentTab: e.detail.current,  
        })  

if(that.data.currentTab=='0')
{

  that.setData({
    searchPageNum: 1,   //第一次加载，设置1  
    searchSongList: [],  //放置返回数据的数组,设为空  
    isFromSearch: true,  //第一次加载，设置true  
    searchLoading: true,  //把"上拉加载"的变量设为true，显示  
    searchLoadingComplete: false,//把“没有数据”设为false，隐藏  

    action:'0'
  })  


console.log("我是热门")
wx.showLoading({
  title: '加载中',
})

that.fetchSearchList(that.data.cateid, '0');  


}
else if(that.data.currentTab=='1')
{


  that.setData({
    searchPageNum: 1,   //第一次加载，设置1  
    searchSongList: [],  //放置返回数据的数组,设为空  
    isFromSearch: true,  //第一次加载，设置true  
    searchLoading: true,  //把"上拉加载"的变量设为true，显示  
    searchLoadingComplete: false, //把“没有数据”设为false，隐藏  
    action:'1'
  })  


  wx.showLoading({
    title: '加载中',
  }),
console.log("我是好评")

  that.fetchSearchList(that.data.cateid, '1');  

}


},  




    onLoad: function (options) 
    {  
        var that=this
      wx.getSystemInfo({
      
    success: function(res) {
      console.info(res.windowHeight+"========================");
    that.setData({
     clientheight:res.windowHeight-35,
  
    })  
    
  }
})
    

       console.log(options.cateid)
       that.setData({   
         cateid:options.cateid,
         action:0,
       })

     wx.showLoading({
         title: '加载中',
       })
  

//查找热门图书
       that.fetchSearchList(options.cateid,'0');  

  
    },  
    

    //搜索，访问网络  
    fetchSearchList: function (search, action) {

      console.log("传参过来的" + search);
      console.log("传参过来的" + action);

      let that = this;


      //访问网络  
      getSearchMusic(search, that.data.searchPageNum, action, function (data) {
        console.log(data)

        console.log(data.curnum)
        that.setData({
          all: data.size
        })

        that.setData({
          curnum: data.curnum
        })

        //判断是否有数据，有则取数据  
        if (data.curnum != '0') {
          let searchList = [];
          //如果isFromSearch是true从data中取出数据，否则先从原来的数据继续添加  
          that.data.isFromSearch ? searchList = data.list : searchList = that.data.booklist.concat(data.list)
          that.setData({
            booklist: searchList, //获取数据数组  
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
that.setData({

  searchLoading: false  
})
        }

      })

    },

    //滚动到底部触发事件  
    onbottom: function () {

      console.log('circle 下一页');

      console.log("我下拉了")
      let that = this;

      if (that.data.searchLoading && !that.data.searchLoadingComplete) {
        that.setData({
          searchPageNum: that.data.searchPageNum + 1,  
          isFromSearch: false  //触发到上拉事件，把isFromSearch设为为false  
        });

        that.fetchSearchList(that.data.cateid, that.data.action);
      }


      if (that.data.curnum == '0') {
        setTimeout(function () {
          wx.hideLoading()
        }, 1000)
        that.setData({
          searchLoading: false

        })

        console.log(that.data.searchLoading)
      }


    },



/*
    onReachBottom: function () {
      // Do something when page reach bottom.
      console.log('circle 下一页');

      console.log("我下拉了")
      let that = this;

      if (that.data.searchLoading && !that.data.searchLoadingComplete) {
        that.setData({
          searchPageNum: that.data.searchPageNum + 1,  //每次触发上拉事件，把searchPageNum+1  
          isFromSearch: false  //触发到上拉事件，把isFromSearch设为为false  
        });

        that.fetchSearchList(that.data.cateid, that.data.action);
      }


      if (that.data.curnum == '0') {
        setTimeout(function () {
          wx.hideLoading()
        }, 1000)
        that.setData({
          searchLoading: false

        })

        console.log(that.data.searchLoading)
      }

    },

  */


 
})  



function getSearchMusic(search, pageindex, action, callback) {

  console.log("6666666"+action)
  wx.request({
    url: 'https://www.titwdj.cn/BorrowBook/CateBookServlet',
    data: {
      action: action,
      search: search,
      pc: pageindex
    },
    method: 'GET',
    header: { 'content-Type': 'application/json' },
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

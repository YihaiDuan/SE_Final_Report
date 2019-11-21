// pages/comment/comment.js
Page({
  data:{
   block:true,
    display2:false,
    bollan:false,
    box:false,
    comment:'',
    bookid:'',
    nickname:"",
    userid:"",
    logs:[],
    nickname:'',
    bol:false,
    commentnum:'',
    content:'',
    scirl:true,
    isPopping: false,//是否已经弹出  
    animPlus: {},//旋转动画  
    animCollect: {},//item位移,透明度  
    animTranspond: {},//item位移,透明度  
    animInput: {},//item位移,透明度  
  index:'',


 //动态数据
    dynamiclist:'',
    hotdynamiclist:'',


   
    searchKeyword: '',  //需要搜索的字符  
    searchSongList: [], //放置返回数据的数组  
    isFromSearch: true,   // 用于判断searchSongList数组是不是空数组，默认true，空的数组  
    searchPageNum: 1,   // 设置加载的第几次，默认是第一次  
    callbackcount: 15,      //返回数据的个数  
    searchLoading: false, //"上拉加载"的变量，默认false，隐藏  
    searchLoadingComplete: false,  //“没有数据”的变量，默认false，隐藏  
  curnum:0,
    dynamicid:'',
  },
  scroll1:function(e){
    var that = this;
    console.info(e.detail.deltaY);
    if (e.detail.deltaY<0)
    {
       that.setData({
         scirl:false
       });

    }else{
      that.setData({
        scirl: true
      });

    }
  },
  setIndex:function(e)
  {

    var that=this
    console.log(e)
   var current=e.currentTarget.dataset
   console.log("你好"+current.index)
   that.setData({
     index:current.index
   })


  },
  //点击弹出  
  plus: function () {
    if (this.data.isPopping) {
      //缩回动画  
      this.popp();
      this.setData({
        isPopping: false
      })
    } else if (!this.data.isPopping) {
      //弹出动画  
      this.takeback();
      this.setData({
        isPopping: true
      })
    }
  },
  input: function () {
    console.log("input")
  },
  transpond: function () {
    console.log("transpond")
  },
  collect: function () {
    console.log("collect")
  },
  //弹出动画  
  popp: function () {
    //plus顺时针旋转  
    var animationPlus = wx.createAnimation({
      duration: 500,
      timingFunction: 'ease-out'
    })
    var animationcollect = wx.createAnimation({
      duration: 500,
      timingFunction: 'ease-out'
    })
    var animationTranspond = wx.createAnimation({
      duration: 500,
      timingFunction: 'ease-out'
    })
    var animationInput = wx.createAnimation({
      duration: 500,
      timingFunction: 'ease-out'
    })
    animationPlus.rotateZ(180).step();
    animationcollect.translate(40, 40).rotateZ(90).opacity(1).step();
    animationTranspond.translate(40, -40).rotateZ(90).opacity(1).step();
    animationInput.translate(40, -40).rotateZ(90).opacity(1).step();
    this.setData({
      animPlus: animationPlus.export(),
      animCollect: animationcollect.export(),
      animTranspond: animationTranspond.export(),
      animInput: animationInput.export(),
    })
  },
  //收回动画  
  takeback: function () {
    //plus逆时针旋转  
    var animationPlus = wx.createAnimation({
      duration: 500,
      timingFunction: 'ease-out'
    })
    var animationcollect = wx.createAnimation({
      duration: 500,
      timingFunction: 'ease-out'
    })
    var animationTranspond = wx.createAnimation({
      duration: 500,
      timingFunction: 'ease-out'
    })
    var animationInput = wx.createAnimation({
      duration: 500,
      timingFunction: 'ease-out'
    })
    animationPlus.rotateZ(0).step();
    animationcollect.translate(0, 0).rotateZ(0).opacity(0).step();
    animationTranspond.translate(0, 0).rotateZ(0).opacity(0).step();
    animationInput.translate(0, 0).rotateZ(0).opacity(0).step();
    this.setData({
      animPlus: animationPlus.export(),
      animCollect: animationcollect.export(),
      animTranspond: animationTranspond.export(),
      animInput: animationInput.export(),
    })
  },  

   
onLoad:function(options){
    var that=this;
console.log(options.action)
try {
  var value = wx.getStorageSync('key')
  if (value) {
    that.setData({
      logs: value,
      bol: true,
    })

  }
}
catch (e) {

}
      // 动态分页
    //访问第一页
    that.setData({
      searchPageNum: 1,   //第一次加载，设置1  
      searchSongList: [],  //放置返回数据的数组,设为空  
      isFromSearch: true,  //第一次加载，设置true  
      searchLoading: true,  //把"上拉加载"的变量设为true，显示  
      searchLoadingComplete: false //把“没有数据”设为false，隐藏  
    })
    this.fetchSearchList();


wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/ShowHotDynamicServlet',

  success:function(res)
  {
    console.log(res.data)

    that.setData({
      hotdynamiclist:res.data
    })
  }
})






},



//搜索，访问网络  
fetchSearchList: function () {

  let that = this;

   var  searchPageNum = that.data.searchPageNum,//把第几次加载次数作为参数  
    callbackcount = that.data.callbackcount; //返回数据的个数  
  //访问网络  
  getSearchMusic(searchPageNum,that.data.logs.userid, function (data) {
    console.log(data)

    console.log(data.curnum)


    //判断是否有数据，有则取数据  
    if (data.curnum != '0') {
      let searchList = [];
      //如果isFromSearch是true从data中取出数据，否则先从原来的数据继续添加  
      that.data.isFromSearch ? searchList = data.list : searchList = that.data.dynamiclist.concat(data.list)
      that.setData({
        dynamiclist: searchList, //获取数据数组  
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

  refresh:function()
  {

    var that=this
    console.log("我是下拉刷新")
    wx.showLoading({
      title: '加载中',
    }),
    that.setData({
      searchPageNum: 1,   //第一次加载，设置1  
      searchSongList: [],  //放置返回数据的数组,设为空  
      isFromSearch: true,  //第一次加载，设置true  
      searchLoading: true,  //把"上拉加载"的变量设为true，显示  
      searchLoadingComplete: false //把“没有数据”设为false，隐藏  
    })

that.setData({

  dynamiclist:'',
})


    this.fetchSearchList();


  },


//滚动到底部触发事件  
onbottom: function () 
{
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



previewuserimages: function (e) {
  var current = e.currentTarget.dataset
  var that = this
  wx.previewImage({
    current: "https://www.titwdj.cn/BorrowBook/images/"+current.url,
    urls: [
      "https://www.titwdj.cn/BorrowBook/images/"+current.url,
    ],

    success: function (res) {
      console.log(res);
    },
    //也根本不走
    fail: function () {
      console.log('fail')
    }
  })

},


nologin:function()
{

wx.showModal({
  title: '提示',
  content: '你尚登录,请先登录!',
  success: function(res) {
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

//点赞

Addadmire:function(e)
{
  var that=this
  var current=e.currentTarget.dataset
 console.log(current.dynamicid)
 console.log(current.index)


 that.data.dynamiclist[current.index].admirebol = 'true'
 that.data.dynamiclist[current.index].admirenum = parseInt(that.data.dynamiclist[current.index].admirenum) + 1;


 that.setData({
   dynamiclist: that.data.dynamiclist
 })



wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/AddDynamicAdmire',
  data: {

userid:that.data.logs.userid,
dynamicid:current.dynamicid,
action:'add',
  },
  success: function(res)
  {

    if (res.data == '0') {
      wx.showToast({
        title: '您已赞过此资源！',
        icon: 'success',
        duration: 2000
      })


    }
  },
 
})
},


//撤销点赞
Deleteadmire :function(e)
{
 var that=this
  var current=e.currentTarget.dataset

 console.log(current.dynamicid)
 console.log(current.index)
 var a = that.data.dynamiclist

 that.data.dynamiclist[current.index].admirebol = 'false'
 that.data.dynamiclist[current.index].admirenum = parseInt(that.data.dynamiclist[current.index].admirenum) - 1;
 console.log(a)

 that.setData({
   dynamiclist: that.data.dynamiclist
 })
 console.log(that.data.dynamiclist)

wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/AddDynamicAdmire',
  data: {

userid:that.data.logs.userid,
dynamicid:current.dynamicid,
action:'delete'

  },
  
  success: function(res)
  {
 
    if(res.data=='0')
    {
      wx.showToast({
        title: '您已撤销过此资源！',
        icon: 'success',
        duration: 2000
      })


    }
  
  },
 
})

},


//关注
AddFan: function (e) {

  var that = this
  var current = e.currentTarget.dataset

  console.log(current.otherid)

  wx.request({
    url: 'https://www.titwdj.cn/BorrowBook/AddFan',
    data: {

      userid: that.data.logs.userid,
      otherid: current.otherid,
      action: 'add'

    },

    success: function (res) 
    {

      that.data.dynamiclist[current.index].fanbol = '1'

    that.setData({

      dynamiclist:that.data.dynamiclist
    })
      
    },
  })
},

//取消关注
DeleteFan: function (e)
{
  var that = this
  var current = e.currentTarget.dataset

  console.log(current.otherid)

  wx.request({
    url: 'https://www.titwdj.cn/BorrowBook/AddFan',
    data: {
      userid: that.data.logs.userid,
      otherid: current.otherid,
      action:'delete'
    },

    success: function (res) {
     
      that.data.dynamiclist[current.index].fanbol = '0'

      that.setData({

        dynamiclist: that.data.dynamiclist
      })
    },
  })
},



setButtonId:function(e)
{
var that=this
  console.log(e)
var current=e.currentTarget.dataset

console.log(current.dynamicid)

that.setData({
  dynamicid: current.dynamicid
})
},
onShareAppMessage: function (res) {
  

var that=this
console.log(that.data.dynamicid)
  

  
  if (res.from === 'button') {
    
    console.log(res.target)
  }
  return {
    title: '将动态转发到微信',
    path: '/page/dynamicdetail/dynamicdetail?dynamicid='+that.data.dynamicid,
    success: function (res) {
      console.log("转发成功")
    },
    fail: function (res) {
      // 转发失败
    }
  }
}
  
})



function getSearchMusic(pageindex,userid,callback) {

  wx.request({
    url: 'https://www.titwdj.cn/BorrowBook/AllDynamicPageUserServlet',
    data: {
      dypagenum: pageindex,
      userid:userid
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

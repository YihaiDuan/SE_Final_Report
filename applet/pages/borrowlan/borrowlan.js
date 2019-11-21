Page({
data:{
 delBtnWidth:180,
borrowdetail:'',
borrowid:'',
bol:true,
logs:'',
show:true,
selectedAllStatus:false,
count:'0',
id:'',
lists:"",
referlist:'',
nodate:true,

refreshbol:false,


},
checkboxChange: function (e) 
{
  var that=this
  that.setData({
    count:0
  })
  console.log('checkbox发生change事件，携带value值为：', e)
 for(var i=0;i<e.detail.value.length;i++)
 {
   var index = e.detail.value[i];
   console.log(e.detail.value[i])
   var price = parseInt(that.data.lists[i].price)
   this.setData({
     count: parseInt(this.data.count) + price,
  
   });
 }
 console.log(that.data.count)
console.log(that.data.lists.length)
  if (e.detail.value.length == that.data.lists.length)
  {
    console.log("长度相等")
that.setData({
  selectedAllStatus:true

})

  }
  else{
    that.setData({
      selectedAllStatus: false

    })

  }
},


onLoad:function(options){
    // 页面初始化 options为页面跳转所带来的参数

    var that=this
 
    //that.initEleWidth();
    //that.tempData();
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
      url: 'https://www.titwdj.cn/BorrowBook/getBorrowLanInfo',
      data:
      {

        userid: that.data.logs.userid
      },

      method: 'GET',

      success: function (res) {
        console.log(res.data)

        if (res.data != '610') {
          that.setData({
            lists: res.data
          })
        }
        else {
          that.setData({

            nodate: false

          })
        }

      },

    })
  },

onShow: function () {
  var that = this;

  console.log("userid" + that.data.logs.userid)
  console.log("你好")
  if(that.data.refreshbol)
  {


    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/getBorrowLanInfo',
      data:
      {

        userid: that.data.logs.userid
      },

      method: 'GET',

      success: function (res) {
        console.log(res.data)

        if (res.data != '610') {
          that.setData({

            lists: res.data
          })
        }
        else {
          that.setData({

            nodate: false

          })
        }

      },

    })


  }
},
/*



  //一分钟刷新一次
  this.data.a = setInterval(function () {
    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/getBorrowLanInfo',
      data:
      {

        userid: that.data.logs.userid
      },

      method: 'GET',

      success: function (res) {
        console.log(res.data)

        if (res.data != '610') {
      if(res.data.length!=that.data.lists.length)
        {

          //管理员已经扫描或者有借书单过期
        
          that.setData({

            lists: res.data,
            nodate:true
          })
        }
        else
        {

        console.log("管理员还没有确认扫描或者有借书单还没有过期")
        }
        }
        else {
          that.setData({

           nodate:false,

          })
        }

      },

    })

  }, 1000)





*/














touchS: function (e) {
  console.info("touchs");

  if (e.touches.length == 1) {
    this.setData({
      //设置触摸起始点水平方向位置
      startX: e.touches[0].clientX
    });
  }
},
touchM: function (e) {
  console.info(e);
  console.info("touchM123");
  // console.info(this.data.list[1].txtStyle+"输出txtStyle");
  var that = this;
  if (e.touches.length == 1) {
    //手指移动时水平方向位置
    var moveX = e.touches[0].clientX;
    //手指起始点位置与移动期间的差值
    var disX = that.data.startX - moveX;
    var delBtnWidth = that.data.delBtnWidth;
    var txtStyle = "";
    if (disX == 0 || disX < 0) {//如果移动距离小于等于0，文本层位置不变
      txtStyle = "left:0px";
    } else if (disX > 0) {//移动距离大于0，文本层left值等于手指移动距离
      txtStyle = "left:-" + disX + "px";
      if (disX >= delBtnWidth) {
        //控制手指移动距离最大值为删除按钮的宽度
        txtStyle = "left:-" + delBtnWidth + "px";
      }
    }
    //获取手指触摸的是哪一项
    var index = e.currentTarget.dataset.index;
    var list = that.data.lists;
    console.info(index + "=======");
    list[index].txtStyle = txtStyle;
    console.info("出发滑动事件");
    //更新列表的状态
    that.setData({
      lists: list
    });
  }
},

touchE: function (e) {
  console.info("touchE");
  if (e.changedTouches.length == 1) {
    //手指移动结束后水平位置
    var endX = e.changedTouches[0].clientX;
    //触摸开始与结束，手指移动的距离
    var disX = this.data.startX - endX;
    var delBtnWidth = this.data.delBtnWidth;
    //如果距离小于删除按钮的1/2，不显示删除按钮
    var txtStyle = disX > delBtnWidth / 2 ? "left:-" + delBtnWidth + "px" : "left:0px";
    //获取手指触摸的是哪一项
    var index = e.currentTarget.dataset.index;
    var list = this.data.list;
    list[index].txtStyle = txtStyle;
    //更新列表的状态
    this.setData({
      list: list
    });
  }
},
//获取元素自适应后的实际宽度
getEleWidth: function (w) {
  var real = 0;
  try {
    var res = wx.getSystemInfoSync().windowWidth;
    var scale = (750 / 2) / (w / 2);//以宽度750px设计稿做宽度的自适应
    // console.log(scale);
    real = Math.floor(res / scale);
    return real;
  } catch (e) {
    return false;
    // Do something when catch error
  }
},

initEleWidth: function () {
  var delBtnWidth = this.getEleWidth(this.data.delBtnWidth);
  this.setData({
    delBtnWidth: delBtnWidth
  });
},

//多选的时候
bindSelectAll: function() {
var selectedAllStatus = this.data.selectedAllStatus;
this.setData({
selectedAllStatus:!selectedAllStatus
}),
this.data.selectedAllStatus= !selectedAllStatus;
var lists= this.data.lists;
console.log(lists)
 var that=this
if(!selectedAllStatus)
{
  var allcount='0';
for (var i = 0; i < lists.length; i++) {
  
lists[i].selected ='true';
lists[i].price

allcount=parseInt(allcount) + parseInt(lists[i].price)
}
console.log(allcount)
that.setData({
  count:allcount
})
}
else{
for (var i = 0; i < lists.length; i++) 
{  
lists[i].selected ='false';

}
  that.setData({
    count: 0
  })
}
 that.setData({

  lists:lists

})
},


radioChange:function(e){
 var that=this

},
showborrow:function(e){
 var that=this;
 that.setData({

show:false

})
},
hiddenborrow:function(e){
 var that=this;
 that.setData({

show:true

})
},






//删除借书单
deleteborrowlan:function(e)
{

var that=this

var  current=e.currentTarget.dataset;

console.log(current.id)

wx.showModal({
  title: '提示',
  content: '是否删除!',
  success: function(res) {
    if (res.confirm) {
      console.log('用户点击确定')

wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/DeleteBorrowLanInfo',
  data: {

    id:current.id,
    userid:that.data.logs.userid
  },
 
  success: function(res)
  {

wx.showToast({
  title: '删除成功!',
  icon: 'success',
  duration: 3000
})

console.log(res.data)

  
    if(res.data!='610')
    {
    that.setData({

      borrowdetail:res.data
    })
    }
    else
    {
      that.setData({

 bol:false

      })
    }

  },
 
})



    } else if (res.cancel) {
      console.log('用户点击取消')
    }
  }
})




},

submit:function(e)
{
  var that=this
console.log(e)
console.log(e.detail.value.checks)
wx.navigateTo({
  url: '../qrdata/qrdata?count='+that.data.count+"&id="+e.detail.value.checks,
})

},

warn: function () {
  wx.showToast({
    title: '请先选择订单!',
    icon: 'success',
    duration: 2000
  })


},
 onUnload: function () {



},

onHide: function () {
  

},
})
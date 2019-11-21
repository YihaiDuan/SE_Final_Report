// pages/comment/comment.js
var app = getApp();
Page({
  data:{
  
    nickname:"",
    userid:"",
    logs:'',
    bookid:'',
    bol:'',
    borrowtableid:'',
    stars: [0, 1, 2, 3, 4],
    normalSrc: '../../images/3.0.png',
    selectedSrc: '../../images/1.0.png',
    halfSrc: '../../images/2.0.png',
    key: 0,//评分
    evaluate: '',
    num1: 0,
    content: '',
    nickname: '',
    logs: '',
    nicknamebol:true
   


  },
  radioChange: function (e) {

    var that=this
 console.log(e)
 var current=e.currentTarget.dataset
 console.log(current.id)
if(current.id=='1')
{
that.setData({
  nicknamebol:false
})
}else
{
  that.setData({
    nicknamebol:true
  })

}
  },
  changevalue: function (e) {
    var datanum = e.detail.value;
    this.setData({
      num1: datanum.length

    })

  },




  //点击右边,半颗星
  selectLeft: function (e) {
    var key = e.currentTarget.dataset.key;
    var that = this;
    if (this.data.key == 0.5 && e.currentTarget.dataset.key == 0.5) {
      //只有一颗星的时候,再次点击,变为0颗
      key = 0;
    }
    console.log("得" + key + "分")
    this.setData({
      key: key
    })

    if (2 < key <= 3.5) {
      that.setData({
        evaluate: '一般'
      });
    }
    if (key <= 2) {
      that.setData({
        evaluate: '差'
      });
    }
    if (key > 3.5) {
      that.setData({
        evaluate: '好'
      });

    }

    console.log(that.data.key * 2)
  },
  //点击左边,整颗星
  selectRight: function (e) {
    var key = e.currentTarget.dataset.key
    var that = this;
    console.log("得" + key + "分")
    this.setData({
      key: key
    })

    if (2 < key <= 3.5) {
      that.setData({
        evaluate: '一般'
      });

    }
    if (key <= 2) {
      that.setData({
        evaluate: '差'
      });
    }
    if (key > 3.5) {
      that.setData({
        evaluate: '好'
      });

    }
    console.log(that.data.key * 2)
  },


onLoad:function(options)
{
  var that=this
console.log(options.bookid)
console.log(options.borrowtableid)
that.setData({

  bookid:options.bookid,
  borrowtableid:options.borrowtableid
})
try {
  var value = wx.getStorageSync('key')
  if (value) {
    that.setData({
      logs: value,
      bol: true,
    })

    console.log("登录成功")
  }
}
catch (e) {

}

},

submit:function(e)
{
  var that = this


  if (that.data.nicknamebol) {
    that.setData({
      nickname: '匿名'
    })
  }
  else {

    that.setData({
      nickname: that.data.logs.userid
    })
  }
  console.log(e.detail.value.content)
  if (e.detail.value.content == '') {
    wx.showToast({
      title: '内容不能为空，请从新发送',
      image: '../../images/321.png',
      duration: 2000
    })
  }
  else {
    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/AddEvsluateServlet',
      data: {

        userid: that.data.logs.userid,
        nickname: that.data.nickname,
        content: e.detail.value.content,
        bookid: that.data.bookid,
        borrowtableid:that.data.borrowtableid,
        evaluate:that.data.key*2
      },
      method: 'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
      
        console.log(res.data)
      wx.reLaunch({
        url: '../operation/operation',
      })

        that.setData({

          content: '',

        })
      


      },

    })

  }


}
  
})

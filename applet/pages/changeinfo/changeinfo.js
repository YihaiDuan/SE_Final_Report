Page({


data:{

nicknamebol:false,
e_mailbol:false,
sexbol:false,
phonebol:false,
sign:false,
nickname:'',
e_mail:'',
sex:'',
phone:'',

items: [
      {name: '男', value: '男'},
      {name: '女', value: '女', checked: 'true'},
      
    ],
      items1: [
      {name: '军事', value: '军事', checked: 'true'},
      {name: '历史', value: '历史'},
       {name: '教育', value: '教育'},
        {name: '语言', value: '语言'},
         {name: '科学', value: '科学'},
          {name: '医学', value: '医学'},
           {name: '生物', value: '生物'},
           {name: '艺术', value: '艺术'}
    ],

},




onLoad:function(options)
{
   var that=this;

 try {
  var value = wx.getStorageSync('key')
  if (value)
   {
that.setData({
logs:value

})

  }
}
 catch (e) {
  
}

console.log(options.changevalue)

if(options.changevalue=='nickname')
{

 that.setData({

   nicknamebol:true,
   nickname:that.data.logs.nickname

 })

}

if(options.changevalue=='e_mail')
{

 that.setData({

   e_mailbol:true,
   e_mail:that.data.logs.e_mail

 })

}

if(options.changevalue=='sex')
{

 that.setData({

   sexbol:true,
   sex:that.data.logs.sex

 })

}


if(options.changevalue=='phone')
{

 that.setData({

   phonebol:true,
   phone:that.data.logs.phone

 })

console.log(that.data.logs.phone)
}



if (options.changevalue == 'sign') {

  that.setData({

    signbol: true,
    sign: that.data.logs.nicesign

  })

  console.log(that.data.logs.sign)
}

},

changenickname:function(e)
{

    var that=this
console.log(e)
console.log(e.detail.value.nickname)
var nick = 0;
  if (e.detail.value.nickname == '') {
  nick = 1;
}
  if (nick==1)
  {
    wx.showToast({
      title: '昵称不能为空',
      image: '../../images/321.png',
      duration: 3000

    })

  }
  else{
wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/ChangeInfoServlet',

  data:{

userid:that.data.logs.userid,
nickname:e.detail.value.nickname,
action:"nickname"

  },
  
   method:'POST',

header: {
      'content-type': 'application/x-www-form-urlencoded'
      
  },
  success: function(res)
  {
  
console.log("修改成功")

wx.setStorageSync('key',res.data)


wx.navigateTo({
  url: '../mynews/mynews',
})
    
  
  },
 
})
  }
},
changesign: function (e) {

  var that = this
  console.log(e)
  console.log(e.detail.value.sign)
  var sign = 0;
  if (e.detail.value.sign == '') {
    sign = 1;
  }
  if (sign == 1) {
    wx.showToast({
      title: '个性签名不能为空',
      image: '../../images/321.png',
      duration: 3000

    })

  }
  else {
    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/ChangeInfoServlet',

      data: {

        userid: that.data.logs.userid,
        sign: e.detail.value.sign,
        action: "sign"

      },

      method: 'POST',

      header: {
        'content-type': 'application/x-www-form-urlencoded'

      },
      success: function (res) {

        console.log("修改成功")

        wx.setStorageSync('key', res.data)


        wx.navigateTo({
          url: '../mynews/mynews',
        })


      },

    })
  }
},




changemail:function(e)
{
 var that=this
console.log(e)
console.log(e.detail.value.e_mail)
var mail = 0;
  if (e.detail.value.e_mail == '') {
    mail = 1;
}
  if (mail == 1) {
    wx.showToast({
      title: '邮箱不能为空',
      image: '../../images/321.png',
      duration: 3000

    })

  }
  else {
wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/ChangeInfoServlet',

  data:{

userid:that.data.logs.userid,
e_mail:e.detail.value.e_mail,
action:"e_mail"

  },
  
   method:'POST',

header: {
      'content-type': 'application/x-www-form-urlencoded'
      
  },
  success: function(res){

console.log(res)

wx.setStorageSync('key',res.data)

    wx.navigateTo({
  url: '../mynews/mynews',
})
    
  },
 
})
  }

},

changesex:function(e)
{

 var that=this
console.log(e)
console.log(e.detail.value.sex)

wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/ChangeInfoServlet',

  data:{

userid:that.data.logs.userid,
sex:e.detail.value.sex,
action:"sex"

  },
  
   method:'POST',

header: {
      'content-type': 'application/x-www-form-urlencoded'
      
  },
  success: function(res){

console.log(res)

wx.setStorageSync('key',res.data)

    wx.navigateTo({
  url: '../mynews/mynews',
})
    
  },
 
})

},



changephone: function (e) {
  var that = this
  console.log(e)
  console.log(e.detail.value.phone)
  var phone = 0;
  if (e.detail.value.phone == '') {
    phone = 1;
  }
  if (phone == 1) {
    wx.showToast({
      title: '手机号不能为空',
      image: '../../images/321.png',
      duration: 3000

    })

  }
  else {
    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/ChangeInfoServlet',

      data: {

        userid: that.data.logs.userid,
        phone: e.detail.value.phone,
        action: "phone"

      },

      method: 'POST',

      header: {
        'content-type': 'application/x-www-form-urlencoded'

      },
      success: function (res) {

        console.log(res)

        wx.setStorageSync('key', res.data)

        wx.navigateTo({
          url: '../mynews/mynews',
        })

      },

    })
  }

},


})
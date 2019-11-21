// pages/enter/enter.js

var app = getApp()
Page({
  data:{
    items: [
      {name: '男', value: '男', checked: 'true'},
      {name: '女', value: '女'},
    ],
    
   
    userid:'',
    nickname:'',
    password:'',
    e_mail:'',
    phone:'',
    openid:'',
    sex:'',
    newpassword:'',
   

  },
   
  formSubmit:function(e)
  {

var that=this;
var values=e.detail.value;

  console.log(e.detail.value)

  console.log(values.userid)
  console.log(values.nickname)
  console.log(values.sex)
  console.log(app.data.openid)
  console.log(values.newpassword)
  


that.setData({

  userid:values.userid,
  nickname:values.nickname,
  sex:values.sex,
  openid:app.data.openid,
  e_mail:values.e_mail,
  phone:values.phone,
  password:values.password,
  like:values.like

})


 
    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/AddUserServlet',
      data: 
      {

userid:that.data.userid,
nickname:that.data.nickname,
sex:that.data.sex,
openid:that.data.openid,
e_mail:that.data.e_mail,
password:that.data.password,
nicesign:'wu'

      },

      method:'POST',

header: {
      'content-type': 'application/x-www-form-urlencoded'
      
  },


      success: function(res)
      {
        console.log(res.data)

    if(res.data=='success')
    {  

wx.navigateTo({
  url: '../register/register?action=success'
})
     }

    else
    {
wx.showToast({
  title: '注册失败!',
  icon: 'success',
  duration: 4000
})

  }

    },
     
   })

  },

  confirepassword: function (e) {
    var that = this

that.setData({
  password:e.detail.value
})
    console.log(e.detail.value);
    console.log("我被触发了password");
  },

  confirenewpassword:function(e)
  {
var that=this
console.log(e.detail.value);
console.log("我被触发了newpassword");

if(that.data.password!=e.detail.value)
{
  wx.showToast({
    title: '两次密码不一致！',
    icon: 'success',
    duration: 2000
  })
  that.setData({

newpassword:'',
  })
}
}


})
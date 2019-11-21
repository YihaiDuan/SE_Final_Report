// pages/register/register.js
Page({
  data:{
userid:'',
password:'',
userinfo:'',
nickname: '',
openid: '',
sex: '',
canIUse: wx.canIUse('button.open-type.getUserInfo')
},


  onLoad:function(options)
  {

    var that=this

    if(options.action=='success')
    {
      wx.showToast({
        title: '账号注册成功!',
        icon: 'success',
        duration: 2000
      })

    }

    wx.getSetting({
      success: function (res) {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称
          wx.getUserInfo({
            success: function (res) {
              console.log(res.userInfo)
            }
          })
        }
      }
    })
  },


formSubmit:function(e)
{

var that=this;

console.log(e.detail.value)
that.setData({

userid:e.detail.value.userid,
password:e.detail.value.password

})
if(e.detail.value.userid==''||e.detail.value.password==='')
{
    wx.showToast({
  title: '账号或密码不能为空，请从新登录',
  duration: 2000
})
}
else{
wx.request({
  url:'https://www.titwdj.cn/BorrowBook/LoginServlet', 
  data: {
     userid: that.data.userid,
     password: that.data.password
  },
  header: {
      'content-type': 'application/json'
  },


  success: function(res) 
  {
    wx.showToast({
  title: '发送成功',
  icon: 'success',
  duration: 2000
})
    console.log(res.data)






 
if(res.data=='failure')
{


wx.showToast({
  title: '用户名或密码错误,请重新登录!',
  icon: 'success',
  duration: 4000
})


  
}
else
{

try {  
console.log(res.data)
 wx.setStorageSync('key',res.data)  
      
        } 
        catch (e) 
        {}  



//传回参数
var pages = getCurrentPages();
var prevPage = pages[pages.length - 2]; 

       prevPage.setData({
         logs:res.data,
         bol:true
       })

wx.navigateBack({
  delta: 1
})
     
}
},


  fail:function(res)
  {

   console.log("登录失败")
  }
  
})
}
},



wxlogin:function(e)
{
var that=this
 that.setData({
   userinfo:e.detail.userInfo
 })

 if (that.data.userinfo.gender == '1') {
   console.log("男");
   that.setData({
     sex: "男"
   })
 }
 else {
   console.log("女");
   that.setData({
     sex: "女"
   })
 }

 console.log(that.data.userinfo.avatarUrl)

  wx.login({

    success: function (res) {
      if (res.code) {

        wx.request({
          url: 'https://api.weixin.qq.com/sns/jscode2session?appid=wx91dbc8c07e056869&secret=7d670e3ecda557196e9684d5e03aee59&js_code=' + res.code + '&grant_type=authorization_code',
          data: {},
          header: {
            'content-type': 'application/json'
          },
          success: function (res) {
            console.log("我是"+res.data.openid); //返回openid
            console.log(res)
            that.data.openid = res.data.openid
            console.log("openid:" + that.data.openid)
    
          that.setData({
            openid: that.data.openid,
          })


          wx.request({
            url: 'https://www.titwdj.cn/BorrowBook/WXlogin',
            data:
            {

              userid: that.data.openid,
              nickname: that.data.userinfo.nickName,
              sex: that.data.sex,
              openid: that.data.openid,
              userimages: that.data.userinfo.avatarUrl,
              e_mail: 'wu',
              phone: 'wu',
              nicesign: 'wu'
            },

            method: 'POST',
            header: {
              'content-type': 'application/x-www-form-urlencoded'

            },
            success: function (res) {
              console.log(res.data)

              try {
                console.log(res.data)
                wx.setStorageSync('key', res.data)

              }
              catch (e) {

              }


              var pages = getCurrentPages();
              var prevPage = pages[pages.length - 2];

              prevPage.setData({
                logs: res.data,
                bol: true
              })


              wx.navigateBack({
                delta: 1
              })


            },

          })

          }
        })

     
      } else {

        console.log('获取用户登录态失败！' + res.errMsg)
      }
    }
  });
}



})
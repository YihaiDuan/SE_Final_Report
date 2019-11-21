var app = getApp()

Page({  
    data: {  
        // tab切换    
        currentTab: 0,  
   
    checkbol:'0',
    time:'120',
    // checknum:'',
    userid:'',
    password:'',
    nickname:'',
    e_mail:'',
    six:'',
    phonebol:false,
    passwordbol:false,
    basebol:false,
    nicknamebol:false,
    e_mailbol:false,
    },  
    swichNav: function (e) {  
        console.log(e);  
        var that = this;  
      
 console.log(e.target.dataset.current)


     var cur1=0;
     var cur2=0;
     var cur3=0;

 if(e.target.dataset.current==1)
 {

    if(that.data.phonebol)
    {
   wx.showToast({
    title: '请先验证手机号!',
    icon: 'success',
    duration: 2000
  })

     }
    else
    {


   cur1=1;



    }

 }

 

   if(e.target.dataset.current==2)
   {
     console.log("我是来确认两次密码是否正确")

     if(that.data.password!=that.data.confirepassword)
{
console.log(that.data.password)
console.log(that.data.confirepassword)

    wx.showToast({
    title: '两次密码不一致!',
    icon: 'success',
    duration: 2000
  })



}

else
{
if(that.data.password==''||that.data.confirepassword=='')
{

   wx.showToast({
    title: '密码不能为空!',
    icon: 'success',
    duration: 2000
  })



}
else{


that.setData({
  password:that.data.password,
  passwordbol:true
})

cur2=1;
}

}


   }



if(cur1=='1'||cur2=='1')
{
 console.log("我进来了")
    if (this.data.currentTab === e.target.dataset.current) {  
            return false;  
        } else {  
            that.setData({  
                currentTab: e.target.dataset.current,  
            })  
        }  

}


   
    },  
    swiperChange: function (e) {  
        console.log(e);  


        this.setData({  
            currentTab: e.detail.current,  
        })  



    },  






    onLoad: function (options) 
    {  
      
    },



//获取手机号
getPhone:function(e)
{

  var that=this
console.log(e)
console.log(e.detail.value)
that.setData({
  userid:e.detail.value
})
},



//获取验证码
    getCheckNum:function()
    {

var that=this
//判断用户是否已经存在
console.log(that.data.userid)



  function matchNum(mobPhnNum) {
    var flag;
    if (/^1\d{10}$/g.test(mobPhnNum)) {
      flag = 1;
    }
    else {
      flag = 0;
    }
    return flag;
  }


  var phonenumber = matchNum(that.data.userid);

   if(phonenumber==0)
   {

wx.showToast({
  title: '手机号格式错误!',
  icon: 'success',
  duration: 2000
})



   }
   else
{



wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/UserInBol',
  data: {

    userid:that.data.userid
  },

  success: function(res)
  {
   
   console.log(res.data)
if(res.data=='1')
{

console.log("0")
wx.showToast({
  title: '该用户已经存在!',
  icon: 'success',
  duration: 2000
})

}else
{
console.log("可以获取验证码")

that.setData({
  checkbol:'1',
  time:'120'
})

//倒计时重发验证码
      that.data.a = setInterval(
        function () {
          that.setData({
            time: that.data.time - 1
          })


          if (that.data.time == 0) {


that.setData({

      checknum:'panhuanghan509.'

})

            clearInterval(that.data.a)
            //重新发送
            that.setData({

              checkbol: 2
            })
          }
        }, 1000)



//获取验证码
// wx.request({
//   url: 'http://localhost/BorrowBook/getCheckNum',
//   data:{
// userid:that.data.userid
//   },
//   success:function(res)
//   {
// console.log(res.data)
// console.log("获取验证码成功")

// that.setData({
// checknum:res.data.checknum
// })
//   }


// })






}


  },
  
})


}
 },


//验证码判断是否正确
PhoneCheck:function(e)
{

  var that=this
console.log(e)
console.log(e.detail.value.check)
if(e.detail.value.check==that.data.checknum)
{
  wx.showToast({
    title: '手机号确认成功!',
    icon: 'success',
    duration: 2000
  })
  

that.setData({
  userid:e.detail.value.userid,
  phonebol:true
})

}

},


//密码确认
  confirepassword: function (e) {
  var that = this

  that.setData({
    password: e.detail.value
  })
  console.log(e.detail.value);
  console.log("我被触发了password");
},

confirenewpassword: function (e) {
  var that = this
  console.log(e.detail.value);
  console.log("我被触发了newpassword");
that.setData({

  confirepassword:e.detail.value
})

},




//基本信息确认

//昵称确认
connickname:function(e)
{
var that=this

console.log(e)
 console.log(e.detail.value);


that.setData({

  nickname:e.detail.value
})



if(e.detail.value=='')
{

 
  wx.showToast({
    title: '昵称不能为空！',
    duration: 2000
  })

}
else
{

  that.setData({
    nicknamebol:true
  })

}


},




//邮箱格式
cone_main:function(e)
{
  var that = this
  console.log(e.detail.value);
  console.log("e_mail");

  that.setData({

e_mail:e.detail.value

  })

if(e.detail.value=='')
{
  wx.showToast({
    title: '邮箱不能为空',
    image: '../../images/321.png',
    duration: 2000
  })

}

 var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
    var isok = reg.test(e.detail.value);
    if (!isok) {
   wx.showToast({
    title: '邮箱格式错误!',
  
    duration: 2000
  })

    }







},



    


//所有步骤全部完成后提交
  formSubmit: function (e) {

  var that = this;
  var values = e.detail.value;

/*
  console.log(e.detail.value)

  console.log(values.userid)
  console.log(values.nickname)
  console.log(values.sex)
  console.log(app.data.openid)
  console.log(values.password)
*/


   if(that.data.nickname=='')
   {
  wx.showToast({
    title: '昵称不能为空！',
    duration: 2000
  })

   }
   else
   {
that.setData({

nicknamebol:true
})
     
   }

 var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
    var isok = reg.test(that.data.e_mail);
    if (!isok) {
   wx.showToast({
    title: '邮箱格式错误!',
    duration: 2000
  })

    }
    else
    {
      
      that.setData({

e_mailbol:true
      })
    }

console.log(that.data.e_mailbol)
console.log(that.data.nicknamebol)


if (that.data.nicknamebol&&that.data.e_mailbol)
{

  console.log("所有信息都正确")




    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/AddUserServlet',
      data:
      {

        userid: that.data.userid,
        nickname: that.data.nickname,
        sex: values.sex,
        openid: app.data.openid,
        e_mail: that.data.e_mail,
        password: that.data.password,
        nicesign: 'wu'

      },

      method: 'POST',

      header: {
        'content-type': 'application/x-www-form-urlencoded'

      },


      success: function (res) {
        console.log(res.data)

        if (res.data == 'success') {

          wx.navigateTo({
            url: '../register/register?action=success'
          })
        }

        else {
          wx.showToast({
            title: '注册失败!',
            icon: 'success',
            duration: 4000
          })

        }

      },

    })





}



},



})  
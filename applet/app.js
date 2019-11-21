
App({

  data: {

    openid: "",
    latitude: '',
    longitude: '',
    userinfo: '',
    nickname: '',
    sex: '',

  },

  onLaunch: function () {
    var that = this;

    wx.login({

      success: function (res) {
        if (res.code) {
          console.log(res.code)
          //发起网络请求
          wx.request({
             url: 'https://www.titwdj.cn/BorrowBook/getOpenid',
          
            data: {
              code: res.code

            },

            header:
            {
              
              'content-type': 'application/json'
            },

            success: function (res) {
              console.log(res)



              that.data.openid = res.data.openid

              console.log(that.data.openid)


              console.log(res)
              that.data.openid = res.data.openid
              console.log("openid:" + that.data.openid)

            }
          })
        }
        else {

          console.log('获取用户登录态失败！' + res.errMsg)
        }
      }
    });



    /*   wx.getLocation({
        type: 'wgs84',
        success: function(res)
         {
        
  that.data.latitude=res.latitude
  that.data.longitude=res.longitude
  
  try {  
  console.log(res)
   wx.setStorageSync('locatoin',res)  
        
          } 
          catch (e) 
          {  }  
  
  console.log(that.data.latitude)
  
         }
       })*/

  },
})

//多张图片上传
function uploadimg(data) {
  var that = this,
    i = data.i ? data.i : 0,
    success = data.success ? data.success : 0,
    fail = data.fail ? data.fail : 0;
  wx.uploadFile({
    url: data.url,
    filePath: data.path[i],
    name: 'fileData',
    formData: null,
    success: (resp) => {
      success++;
      console.log(resp)
      console.log(i);
      //这里可能有BUG，失败也会执行这里
    },
    fail: (res) => {
      fail++;
      console.log('fail:' + i + "fail:" + fail);
    },
    complete: () => {
      console.log(i);
      i++;
      if (i == data.path.length) {   //当图片传完时，停止调用          
        console.log('执行完毕');
        console.log('成功：' + success + " 失败：" + fail);
      } else {//若图片还没有传完，则继续调用函数
        console.log(i);
        data.i = i;
        data.success = success;
        data.fail = fail;
        that.uploadimg(data);
      }

    }
  });
}
// pages/mynews/mynews.js
Page({
  data:{
    
     logs:[],
    latitude:'',
    longitude:''

  },

 
  onShow:function(){
    // 页面渲染完成
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
  },


sharelocation:function()
{

  var that=this

 wx.getLocation({
      type: 'wgs84',

      success: function(res)
       {
      
      that.setData({

latitude:res.latitude,
longitude:res.longitude

      })

try {  
 console.log(res)
 wx.setStorageSync('locatoin',res)  
      
        } 
        catch (e) 
        {  }  

console.log(that.data.latitude)

wx.request({

  url: 'https://www.titwdj.cn/BorrowBook/UpPersonLocation',
  data: {

userid:that.data.logs.userid,
latitude:res.latitude,
longitude:res.longitude

  },
  

  success: function(res)
  {

    console.log(res.data)

wx.setStorageSync('key',res.data) 

that.setData({

  logs:res.data
})
    wx.showToast({
  title: '上传个人位置成功!',
  icon: 'success',
  duration: 3000
})
    
  },
 
})



       }

     })


},

//头像上传
  changeimage:function(){
    var that=this;
     wx.chooseImage({
   count: 1, // 默认9
   sizeType: ['original', 'compressed'], 
   sourceType: ['album', 'camera'], 
   
  success: function (res) {
 var tempFilePaths= res.tempFilePaths;
     console.log(tempFilePaths);
 
       //上传图片
       wx.uploadFile({
         url: 'https://www.titwdj.cn/BorrowBook/UpMyUserImages',
      filePath: tempFilePaths[0],
      name: 'fileup',

       formData:{
        userid: that.data.logs.userid
        
      },
      success: function(res)
      {
        console.log("头像上传成功!")
       console.log(res.data)

    
    wx.showToast({
  title: '上传头像成功!',
  icon: 'success',
  duration: 2000
})


  wx.request({
    url: 'https://www.titwdj.cn/BorrowBook/getUserInformationServlet',
    data: {

      userid:that.data.logs.userid
    
    },
   
    success: function(res)
    {

console.log(res.data)

  wx.setStorageSync('key',res.data)
     that.setData({

      logs:res.data

     })

    },
   
  })

      },


      fail:function()
      {
console.log("失败")

      }
      
    })
      
  }
})

  },



  //背景上传
  changebackimage: function () {
    var that = this;
    wx.chooseImage({
      count: 1, // 默认9
      sizeType: ['original', 'compressed'],
      sourceType: ['album', 'camera'],

      success: function (res) {
        var tempFilePaths = res.tempFilePaths;
        console.log(tempFilePaths);

        //上传图片
        wx.uploadFile({
          url: 'https://www.titwdj.cn/BorrowBook/UpMyBackImages',
          filePath: tempFilePaths[0],
          name: 'fileup',

          formData: {
            userid: that.data.logs.userid

          },
          success: function (res) {
            console.log("头像上传成功!")
            console.log(res.data)


            wx.showToast({
              title: '上传背景成功!',
              icon: 'success',
              duration: 2000
            })


            wx.request({
              url: 'https://www.titwdj.cn/BorrowBook/getUserInformationServlet',
              data: {

                userid: that.data.logs.userid

              },

              success: function (res) {

                console.log(res.data)

                wx.setStorageSync('key', res.data)
                that.setData({

                  logs: res.data

                })

              },

            })

          },


          fail: function () {
            console.log("失败")

          }

        })

      }
    })

  },

previewuserimages:function(e)
{


var current=e.currentTarget.dataset

var that=this




 wx.previewImage({
     current: current.url,
    
     urls: [
      current.url,
        
        ],

     
        success: function(res) {
          console.log(res);
        },
        //也根本不走
        fail: function() {
          console.log('fail')
        }
   })

 
},


previewbackimages: function (e) {


  var current = e.currentTarget.dataset

  var that = this




  wx.previewImage({
    current: current.url,

    urls: [
      current.url,

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

previewpersonQR:function(e)
{


var current=e.currentTarget.dataset

var that=this




 wx.previewImage({
   current: 'https://www.titwdj.cn/BorrowBook/images/'+current.url,
    
     urls: [
       'https://www.titwdj.cn/BorrowBook/images/'+current.url,
        
        ],

     
        success: function(res) {
          console.log(res);
        },
        //也根本不走
        fail: function() {
          console.log('fail')
        }
   })

 
},



  scanpersonQR:function()
  {

    var that=this

wx.scanCode({
  success: (res) => 
  {
    console.log(res.result)


 if("http://weixin.qq.com/r"==res.result.substring(0,22))
 {

wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/UpPersonQR',
  data: 
  {

userid:that.data.logs.userid,
url:res.result
  },
 

  success: function(res)
  {

console.log(res.data)

wx.setStorageSync('key',res.data) 

that.setData({

  logs:res.data
})
    wx.showToast({
  title: '上传个人二维码成功!',
  icon: 'success',
  duration: 2000
})
    
  },
  
})

 } 
 else
 {
console.log("请上传个人二维码")
wx.showToast({
  title: '上传失败,请上传个人二维码!',
  icon: 'success',
  duration: 2000
})

 }  


  }
})

  }

})
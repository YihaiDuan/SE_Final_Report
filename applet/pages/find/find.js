// pages/myself/myself.js
Page({
  data:{

   logs:[],
    bol:false,
    display1: false,
    logs: [],
    bol: false,
    resdata: '',
    Type: '',
    outprint: "",

    referstatus: false,
    bolstatus: '',
    readstatus:false,
    dostatus:false
 
  },


  onShow:function()
  {
  
    var that=this;

 try {

  var value = wx.getStorageSync('key')
  console.log(value)

  if (value) 
  {
   
that.setData({
bol:true,
logs:value

})

  }
} 
catch (e) 
{
  
}
 
if(value)
{

wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/getStatus',
  data:{
userid:that.data.logs.userid

  },

  success:function(res)
  {
console.log(res.data)

that.setData({

dostatus:res.data.dostatus,
readstatus:res.data.readstatus

})

  }


})



}//if

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




noshare:function()
{

wx.showModal({
  title: '提示',
  content: '请先上传个人二维码!',
  success: function(res) {
    if (res.confirm) {
      console.log('用户点击确定')

wx.navigateTo({

  url: '../mynews/mynews',
  
 
})

 } 
    else if (res.cancel) {
      console.log('用户点击取消')
    }
  }
})

},



sharelocation: function () {

  var that = this


  wx.showModal({
    title: '提示',
    content: '是否上传个人位置!',
    success: function (res) {
      if (res.confirm) {
        console.log('用户点击确定')


        wx.getLocation({
          type: 'wgs84',

          success: function (res) {

            that.setData({

              latitude: res.latitude,
              longitude: res.longitude

            })

            try {
              console.log(res)
              wx.setStorageSync('locatoin', res)

            }
            catch (e)
            { }

            console.log(that.data.latitude)

            wx.request({

              url: 'https://www.titwdj.cn/BorrowBook/UpPersonLocation',
              data: {

                userid: that.data.logs.userid,
                latitude: res.latitude,
                longitude: res.longitude

              },


              success: function (res) {

                console.log(res.data)

                wx.setStorageSync('key', res.data)

                that.setData({

                  logs: res.data
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


      } else if (res.cancel) {
        console.log('用户点击取消')
      }
    }
  })
  
},



noshare: function () {

  wx.showModal({
    title: '提示',
    content: '请先上传个人二维码!',
    success: function (res) {
      if (res.confirm) {
        console.log('用户点击确定')

        wx.navigateTo({

          url: '../mynews/mynews',


        })


      }
      else if (res.cancel) {
        console.log('用户点击取消')
      }
    }
  })

},



nosharenolocation: function () {

  wx.showModal({
    title: '提示',
    content: '请先上传个人二维码和个人位置!',
    success: function (res) {
      if (res.confirm) {
        console.log('用户点击确定')

        wx.navigateTo({

          url: '../mynews/mynews',


        })


      }
      else if (res.cancel) {
        console.log('用户点击取消')
      }
    }
  })

},

nolocation: function () {


  wx.showModal({
    title: '提示',
    content: '请先上传个人位置!',
    success: function (res) {
      if (res.confirm) {
        console.log('用户点击确定')

        wx.navigateTo({

          url: '../mynews/mynews',


        })


      }
      else if (res.cancel) {
        console.log('用户点击取消')
      }
    }
  })




},




//扫码分享
sharebookscan: function () {


  var that = this

  wx.scanCode({
    success: (res) => {
      console.log(res.scanType)

      if (res.scanType == 'EAN_13') {

        console.log(res.result)

        wx.request({
          url: 'https://api.douban.com/v2/book/isbn/' + res.result,

        method:'POST',
        header: {
          'content-type': 'application/x-www-form-urlencoded'

        },
          success: function (res)
           {

console.log(res+"11111111")
            if (res.data.code == '6000') {

              wx.showToast({
                title: '抱歉,书库暂时没有该图书',
                icon: 'success',
                duration: 5000
              })

            }
            else {

              console.log(res.data);
              console.log(res.data);
              console.log(res.data.summary);
              console.log(res.data.title);
              console.log(res.data.images.large);

              var array = res.data.author
              var arr = ''

              for (var i = 0; i < array.length; i++) {

                arr += array[i] + "/"
              }
              console.log(arr)

              wx.request({
                url: 'https://www.titwdj.cn/BorrowBook/AddShareBook',

                data: {

                  userid: that.data.logs.userid,
                  booktitle: res.data.title,
                  author: arr,
                  isbn: res.data.isbn13,
                  publish: res.data.publisher,
                  cash: res.data.price,
                  summary: res.data.summary,
                  pagenum: res.data.pages,
                  publishdate: res.data.pubdate,
                  bookimages: res.data.image

                },

                method: 'POST',


                header: {
                  'content-type': 'application/x-www-form-urlencoded'

                },
                success: function (res) {


                  if (res.data == "success") {
                    wx.showToast({
                      title: '分享书籍成功!',
                      icon: 'success',
                      duration: 3000
                    })

                  }
                  else {
                    wx.showToast({
                      title: '你已经分享过该书籍!',
                      icon: 'success',
                      duration: 3000
                    })

                  }
                },

              })






            }


          },

        })



      }
      else {
        wx.showToast({
          title: '请扫描正确的图书条形码!',
          icon: 'success',
          duration: 3000
        })

      }
    }
  })


}




})
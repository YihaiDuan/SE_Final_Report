// pages/chosen/chosen.js
Page({
  data:{

    array: ['距离', '日期'],
   booknum:'',
   booknew:'',
    latitude:'0',
    longitude:'0',
    sharelist:'',
    search:'',
    logs:'',
    action:'',
    dostatus:false,
    readstatus:false,
    index:0,
    show:false,
    markers: [
      {
      
      id: 0,
      latitude: 23.099994,
      longitude: 113.324520,
      width: 50,
      height: 50,
      callout: 
      { 
        content: '你好', 
        display: 'ALWAYS',
        color:'#fff',
        borderRadius:'20rpx',
        bgColor:'#fe5701',
        fontSize:"13rpx",
         },
      
      
    },
      {
       
        id: 0,
        latitude: 23.099994,
        longitude: 113.3554520,
        width: 50,
        height: 50,
     
      }
    ],
   
  },
  bindPickerChange: function (e) 
  {

    var that=this
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      index: e.detail.value
    })


    if (e.detail.value == '0') {

      wx.showLoading({
        title: '加载中',
      }),

        wx.getLocation({
          type: 'wgs84',
          success: function (res)
           {
            that.setData({
              latitude: res.latitude,
              longitude: res.longitude,
           
            })
            console.log("1233"+res.latitude)

            wx.request({
              url: 'https://www.titwdj.cn/BorrowBook/ShowNearBorrowBook',



              data: {

                latitude: res.latitude,
                longitude: res.longitude,
                userid: that.data.logs.userid

              },


              success: function (res) {
                console.log("12344"+res.data);

                if (res.data != 0) {
                  that.setData({

                    sharelist: res.data
                  })
                }

                setTimeout(function () {
                  wx.hideLoading()
                }, 1000)


              },

            })


          },

          fail: function (e) {


            wx.request({
              url: 'https://www.titwdj.cn/BorrowBook/ShowNearBorrowBook',



              data: {

                latitude: '0',
                longitude: '0',
                userid: that.data.logs.userid

              },


              success: function (res) {
                console.log("fail"+res.data);

                if (res.data != '0') {
                  that.setData({

                    sharelist: res.data,
                    show:true
                  })
                }
                setTimeout(function () {
                  wx.hideLoading()
                }, 1000)
              },


            })

          }
        })
    }//if

    else {
console.log("data获取")
      wx.showLoading({
        title: '加载中',
      }),

        wx.getLocation({
          type: 'wgs84',
          success: function (res) {
            that.setData({
              latitude: res.latitude,
              longitude: res.longitude
            })


            wx.request({
              url: 'https://www.titwdj.cn/BorrowBook/ShowNearBorrowBookDate',



              data: {

                latitude: res.latitude,
                longitude: res.longitude,
                userid: that.data.logs.userid,
                 show: true,
              },


              success: function (res) {

                console.log("123")
                console.log(res.data);

                if (res.data != 0) {
                  that.setData({

                    sharelist: res.data
                  })
                }

                setTimeout(function () {
                  wx.hideLoading()
                }, 1000)


              },

            })


          },

          fail: function (e) {


            wx.request({
              url: 'https://www.titwdj.cn/BorrowBook/ShowNearBorrowBookDate',



              data: {

                latitude: '0',
                longitude: '0',
                userid: that.data.logs.userid,
                show: true
              },


              success: function (res) {
                console.log(res.data);

                if (res.data != '0') {
                  that.setData({

                    sharelist: res.data
                  })
                }
                setTimeout(function () {
                  wx.hideLoading()
                }, 1000)
              },


            })

          }
        })





    }







  },



onLoad: function (options)
   {
   var that=this;

   that.setData({
     action: options.action
   })
  

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
       url: 'https://www.titwdj.cn/BorrowBook/getStatus',
       data: {
         userid: that.data.logs.userid

       },

       success: function (res) {
         console.log(res.data)

         that.setData({

           dostatus: res.data.dostatus,
           readstatus: res.data.readstatus

         })

       }


     })




     if (that.data.action == 'distance') 
     {  

     wx.showLoading({
  title: '加载中',
}),
   
   wx.getLocation({
       type: 'gcj02',
      success: function(res)
       {
        that.setData({
          latitude:res.latitude,
          longitude:res.longitude
        })


wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/ShowNearBorrowBook',
  
  

  data: {

 latitude:res.latitude,
 longitude:res.longitude,
 userid:that.data.logs.userid,
 show: true

  },
   

  success: function(res)
  {
   console.log(res.data);

   if(res.data!=0)
   {
that.setData({

sharelist:res.data
})
   }

setTimeout(function(){
   wx.hideLoading()
     },1000)
  

  },
  
})


      },

      fail:function(e)
      {


wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/ShowNearBorrowBook',
  
  

  data: {

 latitude:'0',
 longitude:'0',
 userid: that.data.logs.userid,
 show: true

  },
   

  success: function(res)
  {
   console.log(res.data);

   if(res.data!='0')
   {
   that.setData({

sharelist:res.data
})
   }
    setTimeout(function () {
      wx.hideLoading()
    }, 1000)
  },
  
  
})

      }
    })


    }//if

else
{

       wx.showLoading({
         title: '加载中',
       }),

         wx.getLocation({
           type: 'wgs84',
           success: function (res) {
             that.setData({
               latitude: res.latitude,
               longitude: res.longitude
             })


             wx.request({
               url: 'https://www.titwdj.cn/BorrowBook/ShowNearBorrowBookDate',



               data: {

                 latitude: res.latitude,
                 longitude: res.longitude,
                 userid: that.data.logs.userid,
                 show: true

               },


               success: function (res) {
                 console.log(res.data);

                 if (res.data != 0) {
                   that.setData({

                     sharelist: res.data
                   })
                 }

                 setTimeout(function () {
                   wx.hideLoading()
                 }, 1000)


               },

             })


           },

           fail: function (e) {


             wx.request({
               url: 'https://www.titwdj.cn/BorrowBook/ShowNearBorrowBookDate',



               data: {

                 latitude: '0',
                 longitude: '0',
                 userid: that.data.logs.userid,
                 show: true

               },


               success: function (res) {
                 console.log(res.data);

                 if (res.data != '0') {
                   that.setData({

                     sharelist: res.data
                   })
                 }
                 setTimeout(function () {
                   wx.hideLoading()
                 }, 1000)
               },


             })

           }
         })





}
},


formSubmit:function(e)
{

var that=this
that.setData({

   search:e.detail.value.search

  })


console.log(that.data.search)
console.log(that.data.latitude)


wx.navigateTo({
  url: '../shareresult/shareresult?search='+that.data.search+'&latitude='+that.data.latitude+'&longitude='+that.data.longitude
})

},


 map: function()
 {

wx.navigateTo({

  url: '../map/map',
  
})


 }
 


})
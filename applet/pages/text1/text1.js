// pages/text/text.js
Page({
  data:{
     

      pagemax:'0',
      //默认第一页
      pagenum:'1',
      
      //默认第一章
      chapter:'0',
      content:'',
      content1:'',
      deletebol:false,
      addbol:false,
      chapternum:'0',
      chaptertitle:'',
      chapterbol:false,
     logs:'',
     
      showtitle:'',
     
    
    


     str:'',
      rows:'',
      cols:'',
      bookcontent: '',
      pageindex:'',
      width:'',
      height:'',
      bookid:'',
//能不能免费读的判断
      readstatus:'',
      readbol:false,
      detail:'',
      typeid:'',



      lastX: 0,
      lastY: 0,
      processX: 0,
      opacity: '0',
      left: '0',
      shadow: '0',
      height1: '0',
      currentTab: 1,
      transation: '',
      min: '0',
      max: '100',
      newvalue: '0',
      showbar: false,
      action:1,
      booklist:'',
      freeindex:'',
      all:true,
      noall:false,
      warnbol:false,
      newmaxpage:''
  },
  

  onLoad:function(options)
  {
var that=this

wx.getSystemInfo({

  success: function (res) {
    console.log(res)
    that.setData({
      height:res.windowHeight-20+"px"
    });
    console.log("llllll"+res.windowHeight)
  }

})


that.setData({
bookid:options.bookid,
readstatus:options.readstatus,
typeid:options.typeid
})


    console.log("是否可以读" + options.readstatus)

try {

  var value = wx.getStorageSync('key')
  console.log(value)
  if (value) {

    that.setData({
      bol: true,
      logs: value

    })

  }
}
catch (e) {

}




wx.showLoading({
  title: '加载中',
}),

//获取目录
  //获取图书目录
  //求取免费阅读的目录树
  wx.request({
    url: 'https://www.titwdj.cn/BorrowBook/getEleCateLog',
    data: {
      bookid: that.data.bookid
    },
    success: function (res) {
      console.log(res.data)
      that.setData({
        booklist: res.data
      })
      that.setData({
  freeindex: Math.ceil(that.data.booklist.length/3)
      })

      console.log(that.data.freeindex)
    }

  })

//获取全文内容
    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/CateLogAllServlet',

      data:{
        bookid:that.data.bookid
      },
      success: function (res) {
      //console.log(res.data)
        that.setData({
          str: res.data
        })

      }
    })


  wx.getSystemInfo({
  success: function(res) {
  
    console.log(res.windowWidth)
    console.log(res.windowHeight)

    that.setData({

      width:res.windowWidth,
      height:res.windowHeight,
    })

    that.setData({
     cols:Math.floor((that.data.width-40)/18),
     rows:Math.floor((that.data.height-74)/21),
    })

        console.log(that.data.cols)
        console.log(that.data.rows)

  
        
    
  }
})



wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/CateIndexServlet',
  data: 
  {

  cols:that.data.cols,
  rows:that.data.rows,
  bookid:that.data.bookid,

  },
  success: function(res) 
  {
    console.log(res.data)
    that.setData({
      pageindex:res.data
    })



    if(options.from=='catelog')
    {
   var start=options.start
   console.log(start)
   console.log(options.from)
//寻找该章所对应的页
    for(var i=0;i<that.data.pageindex.length;i++)
    {
   
       if(start==that.data.pageindex[i].start)
       {
that.setData({
  pagenum:that.data.pageindex[i].pc,
  content: that.data.str.substring(that.data.pageindex[that.data.pageindex[i].pc - 1].start, that.data.pageindex[that.data.pageindex[i].pc - 1].end),
  pagemax: that.data.pageindex.length,
  showtitle: that.data.pageindex[that.data.pageindex[i].pc - 1].showtitle,
  newvalue: that.data.pageindex[i].pc

})
break;

       }
    }

    }
    else
    {

      //去判断该书是否有缓存
      wx.request({
        url: 'https://www.titwdj.cn/BorrowBook/ReadHistroy',
        data: {
          userid: that.data.logs.userid,
          bookid: that.data.bookid,
          allpage: that.data.pageindex.length,
          currentpage: that.data.pageindex[0].pc,
          start: that.data.pageindex[0].start,
          end: that.data.pageindex[0].end,
          maxpage: that.data.pageindex[0].pc,
          showtitle: that.data.pageindex[0].showtitle
        },
        method: 'POST',
        header: {
          'content-type': 'application/x-www-form-urlencoded'
        },

        success: function (res) {
          console.log(res.data)
          if (res.data.bol == 'true') {
            console.log("我进来了")
            that.setData({
              content: that.data.str.substring(that.data.pageindex[res.data.histroy.pc - 1].start, that.data.pageindex[res.data.histroy.pc - 1].end),
              pagemax: that.data.pageindex.length,
              showtitle: that.data.pageindex[res.data.histroy.pc - 1].showtitle,
              pagenum: res.data.histroy.pc,
              newvalue: res.data.histroy.pc
            })

          }
          else {

            console.log("我是第一次进来的" + that.data.pageindex[0].end)
            that.setData({
              content: that.data.str.substring(0, that.data.pageindex[0].end),
              pagemax: that.data.pageindex.length,
              showtitle: that.data.pageindex[0].showtitle,
              newvalue: 1,
            })
          }




        }

      })

    }



    //先判断该用户有没有购买 没有购买只能加载到总页数的30%
    //判断是否是vip免费的书
    //readstatus==0不能读
    if (that.data.readstatus == '0') {
      that.setData({
        readbol: false
      })
    }
    else {
      that.setData({
        readbol: true
      })

    }


    






console.log(that.data.readbol)
    //判断是否可以读
    if (!that.data.readbol) {
      console.log("不萌度")

      //寻找该免费章所对应的页
      for (var i = 0; i < that.data.pageindex.length; i++) {

        if (that.data.freeindex == that.data.pageindex[i].chapternum) {
          that.setData({

    pagemax:that.data.pageindex[i].pc,
    newpagemax: that.data.pageindex[i].pc,
    all:false,
    noall:true
          })
          break;

        }
      }
      console.log("免费读到的页数+|||" + that.data.pagemax)



      if (that.data.freeindex == that.data.pageindex[that.data.pagenum - 1].chapternum)
       {
that.setData({
  warnbol:true
})
       }
    }
    else{

      that.setData({
        all:true,
        noall:false,
        warnbol:false,
      })
    }



    setTimeout(function () {
      wx.hideLoading()
    }, 1000)

  },
})

},

  confire:function()
{

  var that=this
  wx.navigateTo({
    url: '../elebookpay/elebookpay?bookid=' + that.data.bookid,

  })


},

cancel:function()
{
wx.navigateBack({
  delta: 1,
})

},

onShow:function()
{

  var that = this

  try {

    var value = wx.getStorageSync('key')
    console.log(value)
    if (value) {

      that.setData({
        bol: true,
        logs: value

      })

    }
  }
  catch (e) {

  }








  wx.request({
    url: 'https://www.titwdj.cn/BorrowBook/CateIndexServlet',
    data:
    {

      cols: that.data.cols,
      rows: that.data.rows,
      bookid: that.data.bookid,

    },
    success: function (res) {
      console.log(res.data)
      that.setData({
        pageindex: res.data
      })


      if (!that.data.readbol) 
      {

        //寻找该免费章所对应的页
        for (var i = 0; i < that.data.pageindex.length; i++) {

          if (that.data.freeindex == that.data.pageindex[i].chapternum) {
            that.setData({

              pagemax: that.data.pageindex[i].pc,
            
              newpagemax: that.data.pageindex[i].pc,
              all: false,
              noall: true
            })
            break;

          }
        }
        console.log("免费读到的页数+|||" + that.data.pagemax)






        if (that.data.freeindex == that.data.pageindex[that.data.pagenum - 1].chapternum) {
         that.setData({
           warnbol:true
         })
        }
      }

    }

  })

  wx.request({
    url: 'https://www.titwdj.cn/BorrowBook/getEleBookDetailByUser',

    data: {
      userid: that.data.logs.userid,
      bookid:that.data.bookid
    },
    success: function (res) {
      console.log(res.data)
if(res.data.readbol=='true')
{

that.setData({

  all: true,
  noall: false,
  warnbol:false
})
}
    }
  })





},

 drawStart: function (e) {

var that=this
   this.data.lastX = e.touches[0].pageX;
   this.data.processX = e.touches[0].pageX;
   console.log("lastX======" + this.data.lastX);
   console.log("lastX======" + this.data.processX);
   let news = this.data.text2;
   let lastX = e.touches[0].pageX;
   this.setData({
     text: news,
     transation: '',
     opacity: 1,
     shadow: '2px 2px 5px #ccc, -2px 2px 5px #ccc;',
     content1: that.data.content,
   })
 

 },


 drawEnd: function (e) {
 
   this.setData({
     action: 1
   });
   var that = this;
   var currentX = e.changedTouches[0].clientX;

   if(that.data.addbol||that.data.deletebol)
   {

if(that.data.deletebol)
{
  console.log("第一页")
  if ((currentX - this.data.lastX) < 60) {

    that.setData({
      transation: 'animation: run 1s linear ;',
    })
    setTimeout(function () {
      that.setData({
        opacity: '0',
        left: '0',
        shadow: '0',

      })
    }, 500)

  }

}
if(that.data.addbol)
{
  console.log("最后一页")
  if (((currentX - this.data.lastX) > 60)) {

    that.setData({
      transation: 'animation:returnrun 1s linear ;',
    });
    setTimeout(function () {
      that.setData({
        opacity: '0',
        left: '0',
        shadow: '0'
      })
    }, 500)

  }



}
   }
   else
   {
     if ((currentX - this.data.lastX) < 60) {

       that.setData({
         transation: 'animation: run 1s linear ;',
       })
       setTimeout(function () {
         that.setData({
           opacity: '0',
           left: '0',
           shadow: '0',

         })
       }, 500)

     } else if (((currentX - this.data.lastX) > 60)) {

       that.setData({
         transation: 'animation:returnrun 1s linear ;',
       });
       setTimeout(function () {
         that.setData({
           opacity: '0',
           left: '0',
           shadow: '0'
         })
       }, 500)

     }

   }
   
 },

 drawMove: function (e) {
   let action = this.data.action;
   let lastX = this.data.lastX;

   let newX = e.touches[0].pageX;
   let resultX = newX - this.data.processX;
   if (action == 1)
   {
   if (parseInt(resultX)<= 0) 
   {
     console.log("加载下一页")
     var that = this


     if (!that.data.readbol) {
       if (that.data.freeindex==that.data.pageindex[that.data.pagenum-1].chapternum) {
         console.log("试读到此，需要购买")

         that.setData({
             warnbol: true
         })
       
       }
     }




//pc到最后一页了
     if (parseInt(that.data.pagenum) >= parseInt(that.data.pageindex.length)) 
     {

       wx.showToast({
         title: '已经是最后一页',
         icon: 'success',
         duration: 2000
       })
     }
     else {
//不是最后一页获取下一页数据

       that.setData({
         pagenum: parseInt(that.data.pagenum) + 1
       })
       console.log(that.data.pagenum)
       console.log(that.data.pageindex.length)

       that.setData({
         newvalue:that.data.pagenum,
       })


   that.setData({
         content: that.data.str.substring(that.data.pageindex[that.data.pagenum - 1].start, that.data.pageindex[that.data.pagenum - 1].end),

         showtitle: that.data.pageindex[that.data.pagenum - 1].showtitle,
       

      })


  }





     //加载下一页
   } else 
   {
     console.log("已经是第一页")
     //加载上一页

     var that = this
   

     that.setData({
       pagenum: parseInt(that.data.pagenum) - 1
     })
     console.log(that.data.pagenum)
     console.log(that.data.pageindex.length)
     that.setData({
       newvalue: that.data.pagenum,
     })
     //pc是第一页了
     if (parseInt(that.data.pagenum) == parseInt(0)) {
       wx.showToast({
         title: '已经是第一页',
         icon: 'success',
         duration: 2000
       })

       that.setData({
      
         pagenum: 1,
       })

     }
     else {

       that.setData({
         content: that.data.str.substring(that.data.pageindex[that.data.pagenum - 1].start, that.data.pageindex[that.data.pagenum - 1].end),
         showtitle: that.data.pageindex[that.data.pagenum - 1].showtitle,
       })

     }


   }


   }

   console.log(resultX);
   this.setData({
     left: resultX + 'rpx',
     action:0
   })

 },

 listenerSlider: function (e)
  {
  console.log(e)
  var that=this
var value=e.detail.value
console.log(value)

   that.setData({
     content: that.data.str.substring(that.data.pageindex[value- 1].start, that.data.pageindex[value - 1].end),
     showtitle: that.data.pageindex[value - 1].showtitle,
     pagenum:value
   })


 },


 progressbar: function () {
   var showbar = this.data.showbar;
   this.setData({
     showbar: !showbar

   });
 },








 onUnload: function () {
   //更新历史记录

   var that = this
   wx.request({
     url: 'https://www.titwdj.cn/BorrowBook/getEleBookDetailByUser',
     data: {
       userid: that.data.logs.userid,
       bookid: that.data.bookid
     },
     success: function (res) {
       console.log(res.data)
       that.setData({
         detail: res.data
       })
       if (that.data.detail.myelebol == "false")
         wx.showModal({
           title: '提示',
           content: '是否加了书架',
           success: function (res) {
             if (res.confirm) {
               console.log('用户点击确定')

               wx.request({
                 url: 'https://www.titwdj.cn/BorrowBook/AddMyEle',
                 data: {
                   userid: that.data.logs.userid,
                   bookid: that.data.bookid
                 },

                 success: function (res) {
                   console.log(res.data)
                   wx.navigateTo({
                     url: '../eledetail/eledetail?bookid=' + that.data.bookid + "&advice=" + that.data.typeid,

                   })
                 }
               })



             } else if (res.cancel) {
               console.log('用户点击取消')
             }
           }
         })


     },
   })







   if (that.data.pagenum != 1) {
     wx.request({
       url: 'https://www.titwdj.cn/BorrowBook/UpdateReadHistroy',
       data: {
         userid: that.data.logs.userid,
         bookid: that.data.bookid,
         allpage: that.data.pageindex.length,
         currentpage: that.data.pageindex[that.data.pagenum-1].pc ,
         start: that.data.pageindex[that.data.pagenum-1].start ,
         end: that.data.pageindex[that.data.pagenum-1].end ,
         maxpage: that.data.pageindex[that.data.pagenum-1].pc,
         showtitle: that.data.pageindex[that.data.pagenum-1].showtitle
       },

       method: 'POST',
       header: {
         'content-type': 'application/x-www-form-urlencoded'
       },

       success: function (res) {
         console.log("更新成功！")
       }
     })
   }
   else {

     wx.request({
       url: 'https://www.titwdj.cn/BorrowBook/UpdateReadHistroy',
       data: {
         userid: that.data.logs.userid,
         bookid: that.data.bookid,
         allpage: that.data.pageindex.length,
         currentpage: that.data.pageindex[that.data.pagenum-1].pc,
         start: that.data.pageindex[that.data.pagenum-1].start,
         end: that.data.pageindex[that.data.pagenum-1].end,
         maxpage: that.data.pageindex[that.data.pagenum-1].pc,
         showtitle: that.data.pageindex[that.data.pagenum-1].showtitle
       },

       method: 'POST',
       header: {
         'content-type': 'application/x-www-form-urlencoded'
       },

       success: function (res) {
         console.log("更新成功！")
       }
     })



   }
 },

 onHide: function () {
   //更新历史记录
   var that = this
   if (that.data.pagenum != 1) {
     wx.request({
       url: 'https://www.titwdj.cn/BorrowBook/UpdateReadHistroy',
       data: {
         userid: that.data.logs.userid,
         bookid: that.data.bookid,
         allpage: that.data.pageindex.length,
         currentpage: that.data.pageindex[that.data.pagenum-1].pc ,
         start: that.data.pageindex[that.data.pagenum].start,
         end: that.data.pageindex[that.data.pagenum-1].end,
         maxpage: that.data.pageindex[that.data.pagenum-1].pc ,
         showtitle: that.data.pageindex[that.data.pagenum-1].showtitle
       },

       method: 'POST',
       header: {
         'content-type': 'application/x-www-form-urlencoded'
       },

       success: function (res) {
         console.log("更新成功！")
       }
     })
   }
   else {

     wx.request({
       url: 'https://www.titwdj.cn/BorrowBook/UpdateReadHistroy',
       data: {
         userid: that.data.logs.userid,
         bookid: that.data.bookid,
         allpage: that.data.pageindex.length,
         currentpage: that.data.pageindex[that.data.pagenum-1].pc,
         start: that.data.pageindex[that.data.pagenum-1].start,
         end: that.data.pageindex[that.data.pagenum-1].end,
         maxpage: that.data.pageindex[that.data.pagenum-1].pc,
         showtitle: that.data.pageindex[that.data.pagenum-1].showtitle
       },

       method: 'POST',
       header: {
         'content-type': 'application/x-www-form-urlencoded'
       },

       success: function (res) {
         console.log("更新成功！")
       }
     })



   }

 },





})
 


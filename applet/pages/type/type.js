// pages/particulars/particulars.js
Page({
  data: {
 
typelist:'',
typeid:'',
  activeIndex: 0,
    content: '菜單一',
    tabs: [],
    contentList: []
  },


onLoad:function()
{
  wx.showLoading({
    title: '加载中',
  })
  var that=this
wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/ShowAllType',


success:function(res)
{
  console.log(res.data)
  that.setData({
    tabs:res.data
  })

  wx.request({

    url: 'https://www.titwdj.cn/BorrowBook/ShowCategoryById',
    data: {
      typeid: that.data.tabs[0].typeid,
    },
    success: function (res) {
      console.log(res.data)
      that.setData({
        contentList: res.data.list
      })
      setTimeout(function () {
        wx.hideLoading()
      }, 1000)
    }


  })
}
})


    var vm = this;
    wx.getSystemInfo({
      success: (res) => {
        console.info(res);
        vm.setData({
          deviceWidth: res.windowWidth,
          deviceHeight: res.windowHeight+50
        });
      }
    });





},
  changeTab: function (e) {

    var that=this
    this.setData({
      activeIndex: e.currentTarget.dataset.index,
      content: e.currentTarget.dataset.name
    })
    console.log(e)

    wx.request({

      url: 'https://www.titwdj.cn/BorrowBook/ShowCategoryById',
      data: {
        typeid: e.currentTarget.dataset.typeid,
      },
      success: function (res) {
        console.log(res.data)
        that.setData({
          contentList: res.data.list
        })

      }


    })

  },




  getMore: function () {
    this.setData({
      contentList: this.data.contentList.concat([
        { text: '菜单:' },
        { text: '菜单:' },
        { text: '菜单:' },
        { text: '菜单:' },
        { text: '菜单:' }
      ])
    });
  }
})
Page({
  data: {

    delBtnWidth: 180,
    borrowdetail: '',
    borrowid: '',
    logs: '',
    show: true,
    selectedAllStatus: false,
    count: '0',
    id: '',
    lists: "",
    totalserver: '0',
    totalfine: '0',
    totalcase: '0',
    totalpay: '0',
    discountlist: '',

    showdiscount: 'true',
    typestauts: '',
    nodate: true,
    grade: '',
    yuanpay: '',
    discountid: '',
    refreshbol: false,



  },
  checkboxChange: function (e) {
    var that = this
    that.setData({
      totalcase: 0,
      totalserver: 0,
      totalfine: 0,
    })
    console.log('checkbox发生change事件，携带value值为：', e)
    for (var i = 0; i < e.detail.value.length; i++) {

      var index = e.detail.value[i];
      console.log(e.detail.value[i])
      //押金
      var price = parseFloat(that.data.lists[i].price)
      //逾期费用
      var fine = parseFloat(that.data.lists[i].fine)

      //服务费
      var server = parseFloat(that.data.lists[i].server);
      var groupcount = parseFloat(that.data.lists[i].groupcount);
      var groupbol = that.data.lists[i].groupbol

      //计算总服务费
      if (groupbol == 'true') {
        that.setData({
          totalserver: parseFloat(that.data.totalserver) + groupcount
        })
      }
      else {
        that.setData({
          totalserver: parseFloat(that.data.totalserver) + server
        })
      }

      //计算总押金
      this.setData({
        totalcase: parseFloat(this.data.totalcase) + price,

      });
      //计算总逾期费用
      that.setData({
        totalfine: parseFloat(that.data.totalfine) + fine
      })

      //计算总消费

      if (that.data.user.grade == 0) {
        that.setData({
          totalpay: parseFloat(that.data.totalfine) + parseFloat(that.data.totalserver),
          yuanpay: parseFloat(that.data.totalfine) + parseFloat(that.data.totalserver)
        })
      }
      //vip
      else {
        that.setData({
          totalpay: ((parseFloat(that.data.totalfine) + parseFloat(that.data.totalserver)) * 0.8).toFixed(2),
          yuanpay: ((parseFloat(that.data.totalfine) + parseFloat(that.data.totalserver)) * 0.8).toFixed(2)
        })

      }

    }

    console.log(that.data.count)
    console.log(that.data.lists.length)
    if (e.detail.value.length == that.data.lists.length) {
      console.log("长度相等")
      that.setData({
        selectedAllStatus: true

      })

    }
    else {
      that.setData({
        selectedAllStatus: false

      })

    }
    //if(e.detail.value.length==that.)
  },




  onLoad: function (options) {


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





    console.log("userid+你好" + that.data.logs.userid)



    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/getReturnlanInfo',

      data:
      {
        userid: that.data.logs.userid
      },

      method: 'GET',

      success: function (res) {
        console.log(res.data)

        if (res.data != 610) {
          that.setData({
            lists: res.data,
          })


        }
        else {

          that.setData({

            nodate: false
          })
        }

      },




    })




  },


  onShow: function () {
    var that = this


    wx.request({
      url: 'https://www.titwdj.cn/BorrowBook/getUserInformationServlet',
      data: {
        userid: that.data.logs.userid
      },
      success: function (res) {
        console.log(res.data)
        that.setData({
          user: res.data
        })
      }
    })


    console.log("更新后的标志")
    console.log(that.data.refreshbol)
    if (that.data.refreshbol) {

      console.log("我进来了" + that.data.logs.userid)
      wx.request({
        url: 'https://www.titwdj.cn/BorrowBook/getReturnlanInfo',

        data:
        {
          userid: that.data.logs.userid
        },

        method: 'GET',

        success: function (res) {
          console.log(res.data)

          if (res.data != 610) {
            that.setData({
              lists: res.data,
            })


          }
          else {

            that.setData({

              nodate: false
            })
          }

        },
      })


    }
  },


  //多选的时候
  bindSelectAll: function () {
    var selectedAllStatus = this.data.selectedAllStatus;
    this.setData({
      selectedAllStatus: !selectedAllStatus
    }),
      this.data.selectedAllStatus = !selectedAllStatus;
    var lists = this.data.lists;
    console.log(lists)
    var that = this
    if (!selectedAllStatus) {
      that.setData({
        totalcase: 0,
        totalfine: 0,
        totalserver: 0
      })

      for (var i = 0; i < lists.length; i++) {

        lists[i].selected = 'true';


        console.log("我来了")
        //押金
        var price = parseFloat(lists[i].price)
        console.log(price)
        //逾期费用
        var fine = parseFloat(lists[i].fine)

        //服务费
        var server = parseFloat(lists[i].server);
        var groupcount = parseFloat(lists[i].groupcount);
        var groupbol = lists[i].groupbol

        //计算总服务费
        if (groupbol == 'true') {
          that.setData({
            totalserver: parseFloat(that.data.totalserver) + groupcount
          })
        }
        else {
          that.setData({
            totalserver: parseFloat(that.data.totalserver) + server
          })
        }

        //计算总押金
        this.setData({
          totalcase: parseFloat(this.data.totalcase) + price,

        });
        //计算总逾期费用
        that.setData({
          totalfine: parseFloat(that.data.totalfine) + fine
        })



      }
    }
    else {
      for (var i = 0; i < lists.length; i++) {
        lists[i].selected = 'false';

      }
      that.setData({
        totalcase: 0,
        totalfine: 0,
        totalserver: 0
      })
    }
    that.setData({

      lists: lists

    })
  },


  radioChange: function (e) {
    var that = this

  },
  showborrow: function (e) {
    var that = this;
    that.setData({

      show: false

    })
  },
  hiddenborrow: function (e) {
    var that = this;
    that.setData({

      show: true

    })
  },

  //删除借书单
  deleteborrowlan: function (e) {

    var that = this

    var current = e.currentTarget.dataset;

    console.log(current.id)

    wx.showModal({
      title: '提示',
      content: '是否删除!',
      success: function (res) {
        if (res.confirm) {
          console.log('用户点击确定')

          wx.request({
            url: 'https://www.titwdj.cn/BorrowBook/DeleteBorrowLanInfo',
            data: {

              id: current.id,
              userid: that.data.logs.userid
            },

            success: function (res) {

              wx.showToast({
                title: '删除成功!',
                icon: 'success',
                duration: 3000
              })

              console.log(res.data)


              if (res.data != '610') {
                that.setData({

                  borrowdetail: res.data
                })
              }
              else {
                that.setData({

                  bol: false

                })
              }

            },

          })



        } else if (res.cancel) {
          console.log('用户点击取消')
        }
      }
    })




  },

  submit: function (e) {
    var that = this


    if (that.data.discount != '') {
      console.log("我是需要改变的打折id" + that.data.discountid)


      wx.request({
        url: 'https://www.titwdj.cn/BorrowBook/UpdateDiscount',

        data: {
          id: that.data.discountid
        },
        success: function (res) {
          console.log("优惠券更新标志位成功!")

        }

      })

    }






    console.log(e)
    console.log(e.detail.value.checks)
    wx.navigateTo({
      url: '../qrdata2/qrdata2?totalpay=' + that.data.totalpay + "&id=" + e.detail.value.checks + '&totalcase=' + that.data.totalcase,
    })

  },

  warn: function () {
    wx.showToast({
      title: '请先选择订单!',
      icon: 'success',
      duration: 2000
    })


  },

  onUnload: function () {


  },

  onHide: function () {

  },


})
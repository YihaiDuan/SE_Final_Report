// pages/qrdata/qrdata.js
Page({
  data:{


action:'',
orderbol:false,
gradebol:false,
wallbol:false,
scorechangebol:false,
elebol:false,
score: '',
borrowpaybol:false,
returnpaybol:false,
tranpaybol:false,
  },
onLoad:function(options)
{

  var that=this
console.log(options.action)
that.setData({
  score:options.score
})
if(options.action=='order')
{
  that.setData({
  orderbol: true,
    gradebol:false,
      wallbol:false,
      scorechangebol: false,
      borrowpaybol:false,
      returnpaybol:false,
      tranpaybol:false,
  })
}

if(options.action=='wall')
{
  that.setData({
    orderbol: false,
    gradebol: false,
    wallbol: true,
    scorechangebol: false,
    borrowpaybol: false,
    returnpaybol:false,
    tranpaybol: false,
  })

}

  if (options.action == 'grade') {
    that.setData({
      orderbol: false,
      gradebol: true,
      wallbol: false,
      scorechangebol: false,
      borrowpaybol: false,
      returnpaybol: false,
      tranpaybol: false,
    })

  }

  if (options.action == 'scorechange') {
    that.setData({
      orderbol: false,
      gradebol: false,
      wallbol: false,
      scorechangebol:true,
      borrowpaybol: false,
      returnpaybol: false,
      tranpaybol: false,
    })

  }

  if (options.action == 'ele') {
    that.setData({
      orderbol: false,
      gradebol: false,
      wallbol: false,
      scorechangebol: false,
      elebol:true,
      borrowpaybol: false,
      returnpaybol: false,
      tranpaybol: false,
    })

  }

  if (options.action == 'borrowpay') {
    that.setData({
      orderbol: false,
      gradebol: false,
      wallbol: false,
      scorechangebol: false,
      elebol: false,
      borrowpaybol: true,
      returnpaybol: false,
      tranpaybol: false,
    })

  }

  if (options.action == 'returnpay') {
    that.setData({
      orderbol: false,
      gradebol: false,
      wallbol: false,
      scorechangebol: false,
      elebol: false,
      borrowpaybol: false,
      returnpaybol: true,
      tranpaybol: false,
    })

  }

  if (options.action == 'tranpay') {
    that.setData({
      orderbol: false,
      gradebol: false,
      wallbol: false,
      scorechangebol: false,
      elebol: false,
      borrowpaybol: false,
      returnpaybol: false,
      tranpaybol: true,
    })

  }

}



})
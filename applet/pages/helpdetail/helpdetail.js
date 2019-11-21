// pages/qrdata/qrdata.js
Page({
  data:
  {

  bol1:false,
  bol2:false,
  bol3:false,
  bol4:false,
  },


onLoad:function(options)
{

  var that=this

console.log(options.id)

if(options.id=='1')
{
that.setData({
  bol1:true
})
}

  if (options.id == '2') {
    that.setData({
      bol2: true
    })
  }

  if (options.id == '3') {
    that.setData({
      bol3: true
    })
  }

  if (options.id == '4') {
    that.setData({
      bol4: true
    })
  }





}


})
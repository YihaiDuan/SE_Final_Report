Page({
data:
{
  longitude:'112.545670',
  latitude:'37.958500',
  markers: [{
   
    id: 0,
    latitude: 37.958500,
    longitude: 112.545670,
    width: 50,
    height: 50
  }],
 
},


 
  markertap(e) 
  {

    var that=this
    console.log(e.markerId)
    wx.openLocation({
      latitude: that.data.latitude,
      longitude: that.data.longitude,
      scale: 18,

    })

  
  },
  
  
})
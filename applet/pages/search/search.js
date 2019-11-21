// pages/search/search.js
Page({
  data:{

  citys:["关键字","图书标题","图书编号","作者"],
  index:0,
  array:'',
  search:'',
  id:'',
  history:true,
    inputValue: '',
    searchsynlist:'',
    histroybol:true,
    hotsearch:''
  },


bindPickerChanger:function(e){
    console.log(e.detail.value);
  this.setData({index:e.detail.value})

  },


onLoad: function(options)
{

  var that=this
 if(options.id)
 {

  console.log(options.id)


 }


 wx.request({

   url: 'https://www.titwdj.cn/BorrowBook/getHotSearch',

   success:function(res)
   {
     console.log(res.data)
     that.setData({
hotsearch:res.data
     })
   }


 })
},



formSubmit:function(e)
{

  var that=this
  var record=''

  console.log(that.data.index)

  that.setData({

   search:e.detail.value.search

  })

console.log(e.detail.value.search)




//添加搜索记录

 try {
  var value = wx.getStorageSync('record')

  if (value)
   {


  record+=value+','+that.data.search

  wx.setStorageSync('record',record)

console.log(record)
  }
  else
  {

   wx.setStorageSync('record',that.data.search)

  }
}
 catch (e) 
 {
  
}


wx.navigateTo({
  url: '../result/result?search='+that.data.search+'&action='+that.data.index
})


},






onShow:function()
{

  var that=this


 try {
  var value = wx.getStorageSync('record')

  if (value)
   {

  var str=value.split(',');
  var temp;



//倒序
  for(var i=str.length-1,j=0;i>j;i--,j++)
  {

     temp=str[i];
     str[i]=str[j];
     str[j]=temp;

  }

 //显示前6个

  var newstr=new Array();

 if(str.length-1>=5)
 {

   for(var i=0;i<6;i++)
   {

   newstr[i]=str[i];

   }


 }
 else
 {
for(var i=0;i<str.length;i++)
   {

   newstr[i]=str[i];

   }

 }
  

that.setData({


    array: newstr

   })

  }
  
}
 catch (e) 
 {
  
}

},


recordsearch:function(e)
{

console.log(e);

var current=e.currentTarget.dataset;

  console.log(current.recorddata)


wx.navigateTo({
  url: '../result/result?search='+current.recorddata+'&action='+'4'
})


},


//清除搜索记录
clearstore:function()
{
  this.setData({

   history:false
   

   })
console.log("我来了")

 wx.clearStorage({
   key: 'record',
 
 })

},

  bindKeyInput: function(e) 
  {

    var that=this


    that.setData({
      inputValue: e.detail.value,
      histroybol:false
    })


   wx.request({
     url: 'https://www.titwdj.cn/BorrowBook/SearchResultSyn',
     data: {

       search:e.detail.value
     },
     method: 'POST',

     header: {
       'content-type': 'application/x-www-form-urlencoded'

     },

     success: function(res)
     {

     console.log(res.data)
that.setData({

  searchsynlist:res.data

})
     },
     
   })

  },




})
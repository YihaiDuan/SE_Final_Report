var util = require('../../utils/util.js')  
Page({
  data:{
    one:"#fff",
    tow:"#EFEFF4",
    three:"#EFEFF4",
    four:"#EFEFF4",
    five:"#EFEFF4",
    six:"#EFEFF4",
    seven:"#EFEFF4",
    engit:"#EFEFF4",
     nine:"EFEFF4",
    ten:"EFEFF4",
    eleven:"EFEFF4",
    twelve:"EFEFF4",
    thirteen:"EFEFF4",
    fourteen:"EFEFF4",
    fifteen:"EFEFF4",

    searchKeyword: '',  //需要搜索的字符  
    searchSongList: [], //放置返回数据的数组  
    isFromSearch: true,   // 用于判断searchSongList数组是不是空数组，默认true，空的数组  
    searchPageNum: 1,   // 设置加载的第几次，默认是第一次  
    callbackcount: 15,      //返回数据的个数  
    searchLoading: false, //"上拉加载"的变量，默认false，隐藏  
    searchLoadingComplete: false,  //“没有数据”的变量，默认false，隐藏  

     i:'',
     typelist:'',
     alltype:''
  },


onLoad:function(options)
{

var that=this
var i=options.typeid;

//获取全部类型
  wx.request({
    url: 'https://www.titwdj.cn/BorrowBook/ShowAllType',

    success: function (res) {
      console.log(res.data)
      that.setData({
        alltype: res.data
      })
    }
  })

//获取子类型
wx.request({
  url: 'https://www.titwdj.cn/BorrowBook/ShowCategoryById',
  data:
  {
    typeid: options.typeid

  },

  success: function (res) {
    console.log(res.data)
    that.setData({
      typelist: res.data
    })
  }
})



that.setData({

  i:options.typeid
})


console.log("加载出来的"+that.data.i)
if(i=='1')
{
     
 that.setData({
    one:"#fff",
    tow:"#EFEFF4",
    three:"#EFEFF4",
    four:"#EFEFF4",
    five:"#EFEFF4",
    six:"#EFEFF4",
    seven:"#EFEFF4",
    engit:"#EFEFF4",
    nine:"EFEFF4",
    ten:"EFEFF4",
    eleven:"EFEFF4",
    twelve:"EFEFF4",
    thirteen:"EFEFF4",
    fourteen:"EFEFF4",
    fifteen:"EFEFF4"
  })

that.setData({    
      searchPageNum: 1,   //第一次加载，设置1  
      searchSongList:[],  //放置返回数据的数组,设为空  
      isFromSearch: true,  //第一次加载，设置true  
      searchLoading: true,  //把"上拉加载"的变量设为true，显示  
      searchLoadingComplete:false //把“没有数据”设为false，隐藏  
    })  

console.log(that.data.searchLoadingComplete)
    that.fetchSearchList(1);  



}


if(i=='2')
{
     
 that.setData({
    one:"#EFEFF4",
    tow:"#fff",
    three:"#EFEFF4",
    four:"#EFEFF4",
    five:"#EFEFF4",
    six:"#EFEFF4",
    seven:"#EFEFF4",
    engit:"#EFEFF4",
    nine:"EFEFF4",
    ten:"EFEFF4",
    eleven:"EFEFF4",
    twelve:"EFEFF4",
    thirteen:"EFEFF4",
    fourteen:"EFEFF4",
    fifteen:"EFEFF4"
  })


  this.setData({    
      searchPageNum: 1,   //第一次加载，设置1  
      searchSongList:[],  //放置返回数据的数组,设为空  
      isFromSearch: true,  //第一次加载，设置true  
      searchLoading: true,  //把"上拉加载"的变量设为true，显示  
      searchLoadingComplete:false //把“没有数据”设为false，隐藏  
    })  

    this.fetchSearchList(2);  

}



if(i=='3')
{
     
 that.setData({
    one:"#EFEFF4",
    tow:"#EFEFF4",
    three:"#fff",
    four:"#EFEFF4",
    five:"#EFEFF4",
    six:"#EFEFF4",
    seven:"#EFEFF4",
    engit:"#EFEFF4",
    nine:"EFEFF4",
    ten:"EFEFF4",
    eleven:"EFEFF4",
    twelve:"EFEFF4",
    thirteen:"EFEFF4",
    fourteen:"EFEFF4",
    fifteen:"EFEFF4"
  })


  this.setData({    
      searchPageNum: 1,   //第一次加载，设置1  
      searchSongList:[],  //放置返回数据的数组,设为空  
      isFromSearch: true,  //第一次加载，设置true  
      searchLoading: true,  //把"上拉加载"的变量设为true，显示  
      searchLoadingComplete:false //把“没有数据”设为false，隐藏  
    })  

    this.fetchSearchList(3);  

}



if(i=='4')
{
     
 that.setData({
    one:"#EFEFF4",
    tow:"#EFEFF4",
    three:"#EFEFF4",
    four:"#fff",
    five:"#EFEFF4",
    six:"#EFEFF4",
    seven:"#EFEFF4",
    engit:"#EFEFF4",
    nine:"EFEFF4",
    ten:"EFEFF4",
    eleven:"EFEFF4",
    twelve:"EFEFF4",
    thirteen:"EFEFF4",
    fourteen:"EFEFF4",
    fifteen:"EFEFF4"
  })


  this.setData({    
      searchPageNum: 1,   //第一次加载，设置1  
      searchSongList:[],  //放置返回数据的数组,设为空  
      isFromSearch: true,  //第一次加载，设置true  
      searchLoading: true,  //把"上拉加载"的变量设为true，显示  
      searchLoadingComplete:false //把“没有数据”设为false，隐藏  
    })  

    this.fetchSearchList(4);  

}



if(i=='5')
{
     
 that.setData({
    one:"#EFEFF4",
    tow:"#EFEFF4",
    three:"#EFEFF4",
    four:"#EFEFF4",
    five:"#fff",
    six:"#EFEFF4",
    seven:"#EFEFF4",
    engit:"#EFEFF4",
    nine:"EFEFF4",
    ten:"EFEFF4",
    eleven:"EFEFF4",
    twelve:"EFEFF4",
    thirteen:"EFEFF4",
    fourteen:"EFEFF4",
    fifteen:"EFEFF4"
  })


  this.setData({    
      searchPageNum: 1,   //第一次加载，设置1  
      searchSongList:[],  //放置返回数据的数组,设为空  
      isFromSearch: true,  //第一次加载，设置true  
      searchLoading: true,  //把"上拉加载"的变量设为true，显示  
      searchLoadingComplete:false //把“没有数据”设为false，隐藏  
    })  

    this.fetchSearchList(5);  

}



if(i=='6')
{
     
 that.setData({
    one:"#EFEFF4",
    tow:"#EFEFF4",
    three:"#EFEFF4",
    four:"#EFEFF4",
    five:"#EFEFF4",
    six:"#fff",
    seven:"#EFEFF4",
    engit:"#EFEFF4",
    nine:"EFEFF4",
    ten:"EFEFF4",
    eleven:"EFEFF4",
    twelve:"EFEFF4",
    thirteen:"EFEFF4",
    fourteen:"EFEFF4",
    fifteen:"EFEFF4"
  })


  this.setData({    
      searchPageNum: 1,   //第一次加载，设置1  
      searchSongList:[],  //放置返回数据的数组,设为空  
      isFromSearch: true,  //第一次加载，设置true  
      searchLoading: true,  //把"上拉加载"的变量设为true，显示  
      searchLoadingComplete:false //把“没有数据”设为false，隐藏  
    })  

    this.fetchSearchList(6);  

}




if(i=='7')
{
     
 that.setData({
    one:"#EFEFF4",
    tow:"#EFEFF4",
    three:"#EFEFF4",
    four:"#EFEFF4",
    five:"#EFEFF4",
    six:"#EFEFF4",
    seven:"#fff",
    engit:"#EFEFF4",
    nine:"EFEFF4",
    ten:"EFEFF4",
    eleven:"EFEFF4",
    twelve:"EFEFF4",
    thirteen:"EFEFF4",
    fourteen:"EFEFF4",
    fifteen:"EFEFF4"
  })


  this.setData({    
      searchPageNum: 1,   //第一次加载，设置1  
      searchSongList:[],  //放置返回数据的数组,设为空  
      isFromSearch: true,  //第一次加载，设置true  
      searchLoading: true,  //把"上拉加载"的变量设为true，显示  
      searchLoadingComplete:false //把“没有数据”设为false，隐藏  
    })  

    this.fetchSearchList(7);  

}




if(i=='8')
{
     
 that.setData({
    one:"#EFEFF4",
    tow:"#EFEFF4",
    three:"#EFEFF4",
    four:"#EFEFF4",
    five:"#EFEFF4",
    six:"#EFEFF4",
    seven:"#EFEFF4",
    engit:"#fff",
    nine:"EFEFF4",
    ten:"EFEFF4",
    eleven:"EFEFF4",
    twelve:"EFEFF4",
    thirteen:"EFEFF4",
    fourteen:"EFEFF4",
    fifteen:"EFEFF4"
  })


  this.setData({    
      searchPageNum: 1,   //第一次加载，设置1  
      searchSongList:[],  //放置返回数据的数组,设为空  
      isFromSearch: true,  //第一次加载，设置true  
      searchLoading: true,  //把"上拉加载"的变量设为true，显示  
      searchLoadingComplete:false //把“没有数据”设为false，隐藏  
    })  

    this.fetchSearchList(8);  

}




if(i=='9')
{
     
 that.setData({
    one:"#EFEFF4",
    tow:"#EFEFF4",
    three:"#EFEFF4",
    four:"#EFEFF4",
    five:"#EFEFF4",
    six:"#EFEFF4",
    seven:"#EFEFF4",
    engit:"#EFEFF4",
    nine:"#fff",
    ten:"EFEFF4",
    eleven:"EFEFF4",
    twelve:"EFEFF4",
    thirteen:"EFEFF4",
    fourteen:"EFEFF4",
    fifteen:"EFEFF4"
  })


  this.setData({    
      searchPageNum: 1,   //第一次加载，设置1  
      searchSongList:[],  //放置返回数据的数组,设为空  
      isFromSearch: true,  //第一次加载，设置true  
      searchLoading: true,  //把"上拉加载"的变量设为true，显示  
      searchLoadingComplete:false //把“没有数据”设为false，隐藏  
    })  

    this.fetchSearchList(9);  

}





if(i=='10')
{
     
 that.setData({
    one:"#EFEFF4",
    tow:"#EFEFF4",
    three:"#EFEFF4",
    four:"#EFEFF4",
    five:"#EFEFF4",
    six:"#EFEFF4",
    seven:"#EFEFF4",
    engit:"#EFEFF4",
    nine:"#EFEFF4",
    ten:"#fff",
    eleven:"EFEFF4",
    twelve:"EFEFF4",
    thirteen:"EFEFF4",
    fourteen:"EFEFF4",
    fifteen:"EFEFF4"
  })


  this.setData({    
      searchPageNum: 1,   //第一次加载，设置1  
      searchSongList:[],  //放置返回数据的数组,设为空  
      isFromSearch: true,  //第一次加载，设置true  
      searchLoading: true,  //把"上拉加载"的变量设为true，显示  
      searchLoadingComplete:false //把“没有数据”设为false，隐藏  
    })  

    this.fetchSearchList(10);  

}





if(i=='11')
{
     
 that.setData({
    one:"#EFEFF4",
    tow:"#EFEFF4",
    three:"#EFEFF4",
    four:"#EFEFF4",
    five:"#EFEFF4",
    six:"#EFEFF4",
    seven:"#EFEFF4",
    engit:"#EFEFF4",
    nine:"#EFEFF4",
    ten:"#EFEFF4",
    eleven:"#fff",
    twelve:"EFEFF4",
    thirteen:"EFEFF4",
    fourteen:"EFEFF4",
    fifteen:"EFEFF4"
  })


  this.setData({    
      searchPageNum: 1,   //第一次加载，设置1  
      searchSongList:[],  //放置返回数据的数组,设为空  
      isFromSearch: true,  //第一次加载，设置true  
      searchLoading: true,  //把"上拉加载"的变量设为true，显示  
      searchLoadingComplete:false //把“没有数据”设为false，隐藏  
    })  

    this.fetchSearchList(11);  

}



if(i=='12')
{
     
 that.setData({
    one:"#EFEFF4",
    tow:"#EFEFF4",
    three:"#EFEFF4",
    four:"#EFEFF4",
    five:"#EFEFF4",
    six:"#EFEFF4",
    seven:"#EFEFF4",
    engit:"#EFEFF4",
    nine:"#EFEFF4",
    ten:"#EFEFF4",
    eleven:"#EFEFF4",
    twelve:"#fff",
    thirteen:"EFEFF4",
    fourteen:"EFEFF4",
    fifteen:"EFEFF4"
  })


  this.setData({    
      searchPageNum: 1,   //第一次加载，设置1  
      searchSongList:[],  //放置返回数据的数组,设为空  
      isFromSearch: true,  //第一次加载，设置true  
      searchLoading: true,  //把"上拉加载"的变量设为true，显示  
      searchLoadingComplete:false //把“没有数据”设为false，隐藏  
    })  

    this.fetchSearchList(12);  

}



if(i=='13')
{
     
 that.setData({
    one:"#EFEFF4",
    tow:"#EFEFF4",
    three:"#EFEFF4",
    four:"#EFEFF4",
    five:"#EFEFF4",
    six:"#EFEFF4",
    seven:"#EFEFF4",
    engit:"#EFEFF4",
    nine:"#EFEFF4",
    ten:"#EFEFF4",
    eleven:"#EFEFF4",
    twelve:"#EFEFF4",
    thirteen:"#fff",
    fourteen:"EFEFF4",
    fifteen:"EFEFF4"
  })


  this.setData({    
      searchPageNum: 1,   //第一次加载，设置1  
      searchSongList:[],  //放置返回数据的数组,设为空  
      isFromSearch: true,  //第一次加载，设置true  
      searchLoading: true,  //把"上拉加载"的变量设为true，显示  
      searchLoadingComplete:false //把“没有数据”设为false，隐藏  
    })  

    this.fetchSearchList(13);  

}


if(i=='14')
{
     
 that.setData({
    one:"#EFEFF4",
    tow:"#EFEFF4",
    three:"#EFEFF4",
    four:"#EFEFF4",
    five:"#EFEFF4",
    six:"#EFEFF4",
    seven:"#EFEFF4",
    engit:"#EFEFF4",
    nine:"#EFEFF4",
    ten:"#EFEFF4",
    eleven:"#EFEFF4",
    twelve:"#EFEFF4",
    thirteen:"#EFEFF4",
    fourteen:"#fff",
    fifteen:"EFEFF4"
  })


  this.setData({    
      searchPageNum: 1,   //第一次加载，设置1  
      searchSongList:[],  //放置返回数据的数组,设为空  
      isFromSearch: true,  //第一次加载，设置true  
      searchLoading: true,  //把"上拉加载"的变量设为true，显示  
      searchLoadingComplete:false //把“没有数据”设为false，隐藏  
    })  

    this.fetchSearchList(14);  

}



if(i=='15')
{
     
 that.setData({
    one:"#EFEFF4",
    tow:"#EFEFF4",
    three:"#EFEFF4",
    four:"#EFEFF4",
    five:"#EFEFF4",
    six:"#EFEFF4",
    seven:"#EFEFF4",
    engit:"#EFEFF4",
    nine:"#EFEFF4",
    ten:"#EFEFF4",
    eleven:"#EFEFF4",
    twelve:"#EFEFF4",
    thirteen:"#EFEFF4",
    fourteen:"#EFEFF4",
    fifteen:"#fff"
  })


  this.setData({    
      searchPageNum: 1,   //第一次加载，设置1  
      searchSongList:[],  //放置返回数据的数组,设为空  
      isFromSearch: true,  //第一次加载，设置true  
      searchLoading: true,  //把"上拉加载"的变量设为true，显示  
      searchLoadingComplete:false //把“没有数据”设为false，隐藏  
    })  

    this.fetchSearchList(15);  

}


},



 //搜索，访问网络  
  fetchSearchList: function(e){

    console.log("传参过来的"+e);                                  

    let that = this;  
    let searchKeyword = e,//输入框字符串作为参数  
        searchPageNum = that.data.searchPageNum,//把第几次加载次数作为参数  
        callbackcount =that.data.callbackcount; //返回数据的个数  
    //访问网络  
    util.getSearchMusic(searchKeyword,searchPageNum,callbackcount, function(data)
    {  
      console.log(data)  
     
      console.log(data.curnum)
      //判断是否有数据，有则取数据  
      if(data.curnum!='0')
      {  
        let searchList = [];  
        //如果isFromSearch是true从data中取出数据，否则先从原来的数据继续添加  
        that.data.isFromSearch ? searchList=data.list : searchList=that.data.searchSongList.concat(data.list)  
        that.setData({  
          searchSongList: searchList, //获取数据数组  
           searchLoading: true   //把"上拉加载"的变量设为false，显示  
        });  
      //没有数据了，把“没有数据”显示，把“上拉加载”隐藏  
      }else{  
        that.setData({  
          searchLoadingComplete: true, //把“没有数据”设为true，显示  
          searchLoading: false  //把"上拉加载"的变量设为false，隐藏  
        });  
      }  
    })  
  },  

   //滚动到底部触发事件  
  searchScrollLower: function()
  {  
 console.log("我下拉了")
    let that = this;  

    if(that.data.searchLoading && !that.data.searchLoadingComplete)
    {  
      that.setData({  
        searchPageNum: that.data.searchPageNum+1,  //每次触发上拉事件，把searchPageNum+1  
        isFromSearch: false  //触发到上拉事件，把isFromSearch设为为false  
      });  
     
      that.fetchSearchList(that.data.i);  
    }  
  } ,


  list1:function(event
  )
  {

   var that=this;
   var current=event.currentTarget.dataset
     
    that.setData({

    i: current.id

    })


     that.setData({    
      searchPageNum: 1,   //第一次加载，设置1  
      searchSongList:[],  //放置返回数据的数组,设为空  
      isFromSearch: true,  //第一次加载，设置true  
      searchLoading: true,  //把"上拉加载"的变量设为true，显示  
      searchLoadingComplete:false //把“没有数据”设为false，隐藏  
    })  

    that.fetchSearchList(that.data.i); 


      that.setData({
    one:"#fff",
    tow:"#EFEFF4",
    three:"#EFEFF4",
    four:"#EFEFF4",
    five:"#EFEFF4",
    six:"#EFEFF4",
    seven:"#EFEFF4",
    engit:"#EFEFF4",
      nine:"#EFEFF4",
    ten:"#EFEFF4",
    eleven:"#EFEFF4",
    twelve:"#EFEFF4",
    thirteen:"#EFEFF4",
    fourteen:"#EFEFF4",
    fifteen:"EFEFF4"
        
     })

  },


   list2:function(event)
   {
   var that=this;

    var current=event.currentTarget.dataset
     
    that.setData({

    i: current.id

    })


     that.setData({    
      searchPageNum: 1,   //第一次加载，设置1  
      searchSongList:[],  //放置返回数据的数组,设为空  
      isFromSearch: true,  //第一次加载，设置true  
      searchLoading: true,  //把"上拉加载"的变量设为true，显示  
      searchLoadingComplete:false //把“没有数据”设为false，隐藏  
    })  

    that.fetchSearchList(that.data.i);

    that.setData({
    one:"#EFEFF4",
    tow:"#fff",
    three:"#EFEFF4",
    four:"#EFEFF4",
    five:"#EFEFF4",
    six:"#EFEFF4",
    seven:"#EFEFF4",
    engit:"#EFEFF4",
      nine:"#EFEFF4",
    ten:"#EFEFF4",
    eleven:"#EFEFF4",
    twelve:"#EFEFF4",
    thirteen:"#EFEFF4",
    fourteen:"#EFEFF4",
    fifteen:"EFEFF4"
        
     })

  },
   list3:function(event)
   {
   var that=this;

       var current=event.currentTarget.dataset
     
    that.setData({

    i: current.id

    })


     that.setData({    
      searchPageNum: 1,   //第一次加载，设置1  
      searchSongList:[],  //放置返回数据的数组,设为空  
      isFromSearch: true,  //第一次加载，设置true  
      searchLoading: true,  //把"上拉加载"的变量设为true，显示  
      searchLoadingComplete:false //把“没有数据”设为false，隐藏  
    })  

    that.fetchSearchList(that.data.i);



     that.setData({
     one:"#EFEFF4",
    tow:"#EFEFF4",
    three:"#fff",
    four:"#EFEFF4",
    five:"#EFEFF4",
    six:"#EFEFF4",
    seven:"#EFEFF4",
    engit:"#EFEFF4",
      nine:"#EFEFF4",
    ten:"#EFEFF4",
    eleven:"#EFEFF4",
    twelve:"#EFEFF4",
    thirteen:"#EFEFF4",
    fourteen:"#EFEFF4",
    fifteen:"EFEFF4"
        
     })

  },
   list4:function(event){
   var that=this;


    var current=event.currentTarget.dataset
     
    that.setData({

    i: current.id

    })


     that.setData({    
      searchPageNum: 1,   //第一次加载，设置1  
      searchSongList:[],  //放置返回数据的数组,设为空  
      isFromSearch: true,  //第一次加载，设置true  
      searchLoading: true,  //把"上拉加载"的变量设为true，显示  
      searchLoadingComplete:false //把“没有数据”设为false，隐藏  
    })  

    that.fetchSearchList(that.data.i);



    that.setData({
     one:"#EFEFF4",
    tow:"#EFEFF4",
    three:"#EFEFF4",
    four:"#fff",
    five:"#EFEFF4",
    six:"#EFEFF4",
    seven:"#EFEFF4",
     engit:"#EFEFF4",
      nine:"#EFEFF4",
    ten:"#EFEFF4",
    eleven:"#EFEFF4",
    twelve:"#EFEFF4",
    thirteen:"#EFEFF4",
    fourteen:"#EFEFF4",
    fifteen:"EFEFF4"
        
     })

  },


   list5:function(event){
   var that=this;
    var current=event.currentTarget.dataset
     
    that.setData({

    i: current.id

    })


     that.setData({    
      searchPageNum: 1,   //第一次加载，设置1  
      searchSongList:[],  //放置返回数据的数组,设为空  
      isFromSearch: true,  //第一次加载，设置true  
      searchLoading: true,  //把"上拉加载"的变量设为true，显示  
      searchLoadingComplete:false //把“没有数据”设为false，隐藏  
    })  

    that.fetchSearchList(that.data.i); 

   that.setData({
     one:"#EFEFF4",
    tow:"#EFEFF4",
    three:"#EFEFF4",
    four:"#EFEFF4",
    five:"#fff",
    six:"#EFEFF4",
    seven:"#EFEFF4",
     engit:"#EFEFF4",
      nine:"#EFEFF4",
    ten:"#EFEFF4",
    eleven:"#EFEFF4",
    twelve:"#EFEFF4",
    thirteen:"#EFEFF4",
    fourteen:"#EFEFF4",
    fifteen:"EFEFF4"
        
     })

  },
   list6:function(event){


   var that=this;

    var current=event.currentTarget.dataset
     
    that.setData({

    i: current.id

    })


     that.setData({    
      searchPageNum: 1,   //第一次加载，设置1  
      searchSongList:[],  //放置返回数据的数组,设为空  
      isFromSearch: true,  //第一次加载，设置true  
      searchLoading: true,  //把"上拉加载"的变量设为true，显示  
      searchLoadingComplete:false //把“没有数据”设为false，隐藏  
    })  

    that.fetchSearchList(that.data.i);


    that.setData({
     one:"#EFEFF4",
    tow:"#EFEFF4",
    three:"#EFEFF4",
    four:"#EFEFF4",
    five:"#EFEFF4",
    six:"#fff",
    seven:"#EFEFF4",
  engit:"#EFEFF4",
      nine:"#EFEFF4",
    ten:"#EFEFF4",
    eleven:"#EFEFF4",
    twelve:"#EFEFF4",
    thirteen:"#EFEFF4",
    fourteen:"#EFEFF4",
    fifteen:"EFEFF4"
        
     })

  },
   list7:function(event){
   var that=this;

    var current=event.currentTarget.dataset
     
    that.setData({

    i: current.id

    })


     that.setData({    
      searchPageNum: 1,   //第一次加载，设置1  
      searchSongList:[],  //放置返回数据的数组,设为空  
      isFromSearch: true,  //第一次加载，设置true  
      searchLoading: true,  //把"上拉加载"的变量设为true，显示  
      searchLoadingComplete:false //把“没有数据”设为false，隐藏  
    })  

    that.fetchSearchList(that.data.i);





   that.setData({
    one:"#EFEFF4",
    tow:"#EFEFF4",
    three:"#EFEFF4",
    four:"#EFEFF4",
    five:"#EFEFF4",
    six:"#EFEFF4",
    seven:"#fff",
     engit:"#EFEFF4",
      nine:"#EFEFF4",
    ten:"#EFEFF4",
    eleven:"#EFEFF4",
    twelve:"#EFEFF4",
    thirteen:"#EFEFF4",
    fourteen:"#EFEFF4",
    fifteen:"EFEFF4"
        
     })

  },


   list8:function(event){
   var that=this;


    var current=event.currentTarget.dataset
     
    that.setData({

    i: current.id

    })


     that.setData({    
      searchPageNum: 1,   //第一次加载，设置1  
      searchSongList:[],  //放置返回数据的数组,设为空  
      isFromSearch: true,  //第一次加载，设置true  
      searchLoading: true,  //把"上拉加载"的变量设为true，显示  
      searchLoadingComplete:false //把“没有数据”设为false，隐藏  
    })  

    that.fetchSearchList(that.data.i);




   that.setData({
     one:"#EFEFF4",
    tow:"#EFEFF4",
    three:"#EFEFF4",
    four:"#EFEFF4",
    five:"#EFEFF4",
    six:"#EFEFF4",
    seven:"#EFEFF4",
    engit:"#fff",
      nine:"#EFEFF4",
    ten:"#EFEFF4",
    eleven:"#EFEFF4",
    twelve:"#EFEFF4",
    thirteen:"#EFEFF4",
    fourteen:"#EFEFF4",
    fifteen:"EFEFF4"
        
     })

  },



     list9:function(event){
   var that=this;

    var current=event.currentTarget.dataset
     
    that.setData({

    i: current.id

    })


     that.setData({    
      searchPageNum: 1,   //第一次加载，设置1  
      searchSongList:[],  //放置返回数据的数组,设为空  
      isFromSearch: true,  //第一次加载，设置true  
      searchLoading: true,  //把"上拉加载"的变量设为true，显示  
      searchLoadingComplete:false //把“没有数据”设为false，隐藏  
    })  

    that.fetchSearchList(that.data.i);



   that.setData({
     one:"#EFEFF4",
    tow:"#EFEFF4",
    three:"#EFEFF4",
    four:"#EFEFF4",
    five:"#EFEFF4",
    six:"#EFEFF4",
    seven:"#EFEFF4",
    engit:"#EFEFF4",
      nine:"#fff",
    ten:"#EFEFF4",
    eleven:"#EFEFF4",
    twelve:"#EFEFF4",
    thirteen:"#EFEFF4",
    fourteen:"#EFEFF4",
    fifteen:"EFEFF4"
        
     })

  },


   list10:function(event){
   var that=this;

    var current=event.currentTarget.dataset
     
    that.setData({

    i: current.id

    })


     that.setData({    
      searchPageNum: 1,   //第一次加载，设置1  
      searchSongList:[],  //放置返回数据的数组,设为空  
      isFromSearch: true,  //第一次加载，设置true  
      searchLoading: true,  //把"上拉加载"的变量设为true，显示  
      searchLoadingComplete:false //把“没有数据”设为false，隐藏  
    })  

    that.fetchSearchList(that.data.i);



   that.setData({
     one:"#EFEFF4",
    tow:"#EFEFF4",
    three:"#EFEFF4",
    four:"#EFEFF4",
    five:"#EFEFF4",
    six:"#EFEFF4",
    seven:"#EFEFF4",
    engit:"#EFEFF4",
      nine:"#EFEFF4",
    ten:"#fff",
    eleven:"#EFEFF4",
    twelve:"#EFEFF4",
    thirteen:"#EFEFF4",
    fourteen:"#EFEFF4",
    fifteen:"EFEFF4"
        
     })

  },



     list11:function(event){
   var that=this;

    var current=event.currentTarget.dataset
     
    that.setData({

    i: current.id

    })


     that.setData({    
      searchPageNum: 1,   //第一次加载，设置1  
      searchSongList:[],  //放置返回数据的数组,设为空  
      isFromSearch: true,  //第一次加载，设置true  
      searchLoading: true,  //把"上拉加载"的变量设为true，显示  
      searchLoadingComplete:false //把“没有数据”设为false，隐藏  
    })  

    that.fetchSearchList(that.data.i);


   that.setData({
     one:"#EFEFF4",
    tow:"#EFEFF4",
    three:"#EFEFF4",
    four:"#EFEFF4",
    five:"#EFEFF4",
    six:"#EFEFF4",
    seven:"#EFEFF4",
    engit:"#EFEFF4",
      nine:"#EFEFF4",
     ten:"#EFEFF4",
    eleven:"#fff",
    twelve:"#EFEFF4",
    thirteen:"#EFEFF4",
    fourteen:"#EFEFF4",
    fifteen:"EFEFF4"
        
     })
  

  },


 list12:function(event){
   var that=this;

    var current=event.currentTarget.dataset
     
    that.setData({

    i: current.id

    })


     that.setData({    
      searchPageNum: 1,   //第一次加载，设置1  
      searchSongList:[],  //放置返回数据的数组,设为空  
      isFromSearch: true,  //第一次加载，设置true  
      searchLoading: true,  //把"上拉加载"的变量设为true，显示  
      searchLoadingComplete:false //把“没有数据”设为false，隐藏  
    })  

    that.fetchSearchList(that.data.i);



   that.setData({
     one:"#EFEFF4",
    tow:"#EFEFF4",
    three:"#EFEFF4",
    four:"#EFEFF4",
    five:"#EFEFF4",
    six:"#EFEFF4",
    seven:"#EFEFF4",
    engit:"#EFEFF4",
      nine:"#EFEFF4",
     ten:"#EFEFF4",
    eleven:"#EFEFF4",
    twelve:"#fff",
    thirteen:"#EFEFF4",
    fourteen:"#EFEFF4",
    fifteen:"EFEFF4"
        
     })
  
  },




list13:function(event){
   var that=this;


    var current=event.currentTarget.dataset
     
    that.setData({

    i: current.id

    })


     that.setData({    
      searchPageNum: 1,   //第一次加载，设置1  
      searchSongList:[],  //放置返回数据的数组,设为空  
      isFromSearch: true,  //第一次加载，设置true  
      searchLoading: true,  //把"上拉加载"的变量设为true，显示  
      searchLoadingComplete:false //把“没有数据”设为false，隐藏  
    })  

    that.fetchSearchList(that.data.i);




   that.setData({
     one:"#EFEFF4",
    tow:"#EFEFF4",
    three:"#EFEFF4",
    four:"#EFEFF4",
    five:"#EFEFF4",
    six:"#EFEFF4",
    seven:"#EFEFF4",
    engit:"#EFEFF4",
      nine:"#EFEFF4",
     ten:"#EFEFF4",
    eleven:"#EFEFF4",
    twelve:"#EFEFF4",
    thirteen:"#fff",
    fourteen:"#EFEFF4",
    fifteen:"EFEFF4"
        
     })
  
  },


  list14:function(event){
   var that=this;


    var current=event.currentTarget.dataset
     
    that.setData({

    i: current.id

    })


     that.setData({    
      searchPageNum: 1,   //第一次加载，设置1  
      searchSongList:[],  //放置返回数据的数组,设为空  
      isFromSearch: true,  //第一次加载，设置true  
      searchLoading: true,  //把"上拉加载"的变量设为true，显示  
      searchLoadingComplete:false //把“没有数据”设为false，隐藏  
    })  

    that.fetchSearchList(that.data.i);



   that.setData({
     one:"#EFEFF4",
    tow:"#EFEFF4",
    three:"#EFEFF4",
    four:"#EFEFF4",
    five:"#EFEFF4",
    six:"#EFEFF4",
    seven:"#EFEFF4",
    engit:"#EFEFF4",
      nine:"#EFEFF4",
     ten:"#EFEFF4",
    eleven:"#EFEFF4",
    twelve:"#EFEFF4",
    thirteen:"#EFEFF4",
    fourteen:"#fff",
    fifteen:"EFEFF4"
        
     })
  
  },


  list15:function(event){
   var that=this;

    var current=event.currentTarget.dataset
     
    that.setData({

    i: current.id

    })


     that.setData({    
      searchPageNum: 1,   //第一次加载，设置1  
      searchSongList:[],  //放置返回数据的数组,设为空  
      isFromSearch: true,  //第一次加载，设置true  
      searchLoading: true,  //把"上拉加载"的变量设为true，显示  
      searchLoadingComplete:false //把“没有数据”设为false，隐藏  
    })  

    that.fetchSearchList(that.data.i);


   that.setData({
     one:"#EFEFF4",
    tow:"#EFEFF4",
    three:"#EFEFF4",
    four:"#EFEFF4",
    five:"#EFEFF4",
    six:"#EFEFF4",
    seven:"#EFEFF4",
    engit:"#EFEFF4",
      nine:"#EFEFF4",
     ten:"#EFEFF4",
    eleven:"#EFEFF4",
    twelve:"#EFEFF4",
    thirteen:"#EFEFF4",
    fourteen:"#EFEFF4",
    fifteen:"#fff"
        
     })
  
  },

})
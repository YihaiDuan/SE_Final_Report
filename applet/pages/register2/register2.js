// pages/enter/enter.js

var app = getApp()
Page({
  data:{

    userid:'',
  
  },
   
  formSubmit:function(e)
  {

var that=this;
var values=e.detail.value;
  console.log(e.detail.value)

  console.log(values.userid)
  }
  

  




})
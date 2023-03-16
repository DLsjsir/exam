//login.js
const util = require('../../utils/util.js')
Page({
  data: { //这个页面的全局变量
    username:'',
    password:'',
    name:'',
    hidden: true,
  },
  onLoad(options) { //这个页面的主函数
    if(wx.getStorageSync('uid')){
      wx.switchTab({
        url: '../index/index',
    })
  }
  },
  onInput(e){ //e当前事件
    //console.log(e.currentTarget.dataset.name)  dataset 等同于 data-name
    // console.log(e.detail.value)
    this.data[e.currentTarget.dataset.name] = e.detail.value
    //js中,对象变量可以.属性 等同于 对象变量[属性]
    //console.log(this.data.username + '------' + this.data.password)
  },
  submit(){
    console.log(this.data.username + '------' + this.data.password)
    //http协议 应用层协议 
    //2个参数get 3个人参数post
    //resp回调参数是Java给小程序返回回来的参数
    //箭头函数 简写形式
    util.http('/wx/login', this.data,resp=>{
      if(resp.code == 1){
        console.log(resp.uid + '------' + resp.name)
        wx.setStorageSync('uid', resp.uid)
        wx.setStorageSync('name', resp.name)
        wx.switchTab({
          url: '../index/index',
        })
      }else{
        util.alert(resp.msg)
        if(resp.msg == '请注册该学号'){
          this.data.hidden = false
          this.setData(this.data)//一旦全局变量发生改变 加这句话更新页面
        }
      }
    })

  }
})
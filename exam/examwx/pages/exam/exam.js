// pages/exam/exam.js
const util = require('../../utils/util.js')
const app = getApp()//获取app.js 全局js文件
Page({
  data: {
    api:app.globalData.api,
    exam:'',
    question:[],//空数组
    currentQ:{},//空对象
    index: 0,   //数组下标
    json: '',
  },
  onLoad(options) {
    if(options){
    this.data.exam = options.exam
    util.http('/wx/random?exam='+this.data.exam, resp=>{
      this.data.question = resp
      this.data.currentQ = this.data.question[0]//此时为第一道题
      console.log(resp)
      this.setData(this.data)
    })
    }
  },
  answer(e){
   var result = e.currentTarget.dataset.answer
   this.data.currentQ.result = result
   this.setData(this.data)
   //使用全局变量一定要加前缀
   this.data.question[this.data.index].result = result
   setTimeout(()=>{ //简写形式可以扩大作用域 this作用域指向当前页面
    this.switch()
   },500)//等待0.5s
  },
  switch() {
    if(this.data.index<this.data.question.length-1){
    this.data.index++
    this.data.currentQ = this.data.question[this.data.index]
  }else{
    this.data.currentQ = {}
    this.data.currentQ.question = '已全部答完，请确认交卷'
    console.log(this.data.currentQ)
  }
    this.setData(this.data)
  },
  submit(){
    console.log(this.data.question)
    var objList = []
    for(var bean of this.data.question){
      var obj = {}
      obj.ret = bean.answer == bean.result?1:0
      obj.qid = bean.id
      obj.uid = wx.getStorageSync('uid')
      objList.push(obj)
    }
    console.log(objList)
    this.data.json = JSON.stringify(objList)
    util.http('/wx/submit',this.data,resp=>{
      
      util.alert('交卷成功')
     this.data.json = JSON.stringify()
      setTimeout(()=>{
        wx.navigateBack()
      },1000)//1s后退出该页面
    })
  }
})
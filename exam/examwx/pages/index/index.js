// pages/index/index.js
const util = require('../../utils/util.js')
Page({
  data: {
    exam:[],
  },
  onLoad(options) {
    util.http('/wx/exam',resp=>{
      this.data.exam = resp.exam
      this.setData(this.data)//更新页面
    })
  },
  exam(e){
    var exam = e.currentTarget.dataset.item
    util.http('/wx/check?exam='+exam+'&uid='+wx.getStorageSync('uid'),resp=>{
      if(resp.code==1){
        util.redirect({
          url:'exam',
          exam:exam
        })
      }else{
        util.alert(resp.msg)
      }
    })
  },
  onPullDownRefresh(){
    util.http('/wx/exam',resp=>{
      this.data.exam = resp.exam
      util.stopPullSetData(this)//数据更新出来了，停止刷新动作
    })
  }
})
// app.js
App({ //APP({})是一个函数，在js中 {}1.函数体2.对象
  //整个小程序的主函数 点击编译或者保存时第一个执行的函数
  onLaunch(){//函数简写形式  onLaunch()是函数 {}是函数体
    // 展示本地存储能力
    const logs = wx.getStorageSync('logs') || []//存了日志，什么时间登录的
    logs.unshift(Date.now())//logs数组内部 开头添加 Date.now()当前时间
    wx.setStorageSync('logs', logs)//把logs数组存入小程序内部

    // 微信账号登录
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
      }
    })
  },
  globalData: {//整个小程序的全局变量 值是一个对象
    api:'http://localhost:8080',
    userInfo: null//用户信息
  }
})

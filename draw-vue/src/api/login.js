import request from '@/utils/request'

// 登录方法
export function login(username, password, code) {
  return request({
    url: '/login',
    method: 'post',
    data: {
      'account': username,
      'password': password,
      'verify': code
    }
  })
}

export  function sso(code) {
  return request({
    url: '/sso/login',
    method: 'get',
    params: {
      code: code
    }
  })
}
// 获取用户详细信息
export function getInfo() {
  return request({
    url: '/getInfo',
    method: 'get'
  })
}

// 退出方法
export function logout() {
  return request({
    url: '/logout',
    method: 'post'
  })
}

// 获取验证码
export function getCodeImg(account) {
  return request({
    url: '/captchaImage?account='+ account,
    method: 'get'
  })
}

// 获取验证码
export function getCodeNeed(account) {
  return request({
    url: '/captchaNeed?account='+ account,
    method: 'get'
  })
}

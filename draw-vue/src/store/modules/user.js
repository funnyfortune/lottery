import {
  login,
  sso,
  logout,
  getInfo
} from '@/api/login'
import {
  getToken,
  setToken,
  removeToken
} from '@/utils/auth'

const user = {
  state: {
    user: {},
    noticeCount: 0,
    msgCount: 0,
    token: getToken(),
    name: '',
    avatar: '',
    roles: [],
    permissions: [],
    userMsg: {},
    userNotice: {}
  },

  mutations: {
    SET_USER: (state, user) => {
      state.user = user
    },
    SET_TOKEN: (state, token) => {
      state.token = token
    },
    SET_NAME: (state, name) => {
      state.name = name
    },
    SET_AVATAR: (state, avatar) => {
      state.avatar = avatar
    },
    SET_ROLES: (state, roles) => {
      state.roles = roles
    },
    SET_PERMISSIONS: (state, permissions) => {
      state.permissions = permissions
    },
    SET_NOTICE_COUNT: (state, count) => {
      state.noticeCount = count
    },
    SET_MSG_COUNT: (state, count) => {
      state.msgCount = count
    },
    SET_USER_MSG: (state, msg) => {
      state.userMsg = msg
    },
    SET_USER_NOTICE: (state, msg) => {
      state.userNotice = msg
    }
  },

  actions: {
    // 登录
    Login({
            commit
          }, userInfo) {
      const username = userInfo.account.trim()
      const password = userInfo.password
      const code = userInfo.captcha
      return new Promise((resolve, reject) => {
        login(username, password, code).then(res => {
          debugger
          setToken(res.data.token)
          commit('SET_TOKEN', res.data.token)
          resolve(res)
        }).catch(error => {
          reject(error)
        })
      })
    },
    sso({commit}, code) {
      return new Promise((resolve, reject) => {
        sso(code).then (res => {
          if (res.code == 200) {
            setToken(res.data.token)
            commit('SET_TOKEN', res.data.token)
            resolve(res)
          } else {
            reject(res.msg)
          }
        }).catch(error => {
          reject(error)
        })
      })
    },
    // 获取用户信息
    GetInfo({
              commit,
              state
            }) {
      return new Promise((resolve, reject) => {
        getInfo(state.token).then(res => {
          const user = res.data.user
          const avatar = user.avatar == "" ? '' : process.env.VUE_APP_BASE_API + user.avatar;
          if (res.data.roles && res.data.roles.length > 0) { // 验证返回的roles是否是一个非空数组
            commit('SET_ROLES', res.data.roles)
            commit('SET_PERMISSIONS', res.data.permissions)
          } else {
            commit('SET_ROLES', ['ROLE_DEFAULT'])
          }
          commit('SET_NAME', user.name)
          commit('SET_AVATAR', avatar)
          commit('SET_USER', user)
          commit('SET_NOTICE_COUNT', res.data.noticeCount)
          commit('SET_MSG_COUNT', res.data.msgCount)
          resolve(res)
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 退出系统
    LogOut({
             commit,
             state
           }) {
      return new Promise((resolve, reject) => {
        logout(state.token).then(() => {
          commit('SET_TOKEN', '')
          commit('SET_ROLES', [])
          commit('SET_PERMISSIONS', [])
          removeToken()
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 前端 登出
    FedLogOut({
                commit
              }) {
      return new Promise(resolve => {
        commit('SET_TOKEN', '')
        removeToken()
        resolve()
      })
    },
    computeMsgCount({commit, state}, n) {
      let val = state.msgCount + n
      commit('SET_MSG_COUNT', val > 0 ? val : 0)
    },
    computeNoticeCount({commit, state}, n) {
      let val = state.noticeCount + n
      commit('SET_NOTICE_COUNT', val > 0 ? val : 0)
    },
    showUserMsg({commit, state}, res) {
      if (res.code == 200) {
        commit('SET_MSG_COUNT', state.msgCount + 1)
        commit('SET_USER_MSG', res.data)
      }
    },
    showUserNotice({commit, state}, res) {
      if (res.code == 200) {
        commit('SET_NOTICE_COUNT', state.noticeCount + 1)
        commit('SET_USER_NOTICE', res.data)
      }
    }
  }
}

export default user

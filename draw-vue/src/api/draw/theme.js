import request from '@/utils/request'

// 查询抽奖主题列表
export function listTheme(query) {
  return request({
    url: '/draw/theme/list',
    method: 'get',
    params: query
  })
}

export function selectTheme() {
  return request({
    url: '/draw/theme/select',
    method: 'get'
  })
}

// 查询抽奖主题详细
export function getTheme(id) {
  return request({
    url: '/draw/theme/' + id,
    method: 'get'
  })
}

// 新增抽奖主题
export function addTheme(data) {
  return request({
    url: '/draw/theme/save',
    method: 'post',
    data: data
  })
}

// 修改抽奖主题
export function updateTheme(data) {
  return request({
    url: '/draw/theme/update',
    method: 'post',
    data: data
  })
}

// 删除抽奖主题
export function delTheme(ids) {
  return request({
    url: '/draw/theme/delete',
    method: 'post',
      data: ids
  })
}

// 导出抽奖主题
export function exportTheme(query) {
  return request({
    url: '/draw/theme/export',
    method: 'get',
    params: query
  })
}

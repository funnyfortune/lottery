import request from '@/utils/request'

// 查询抽奖行为列表
export function listAction(query) {
  return request({
    url: '/draw/action/list',
    method: 'get',
    params: query
  })
}

// 查询抽奖行为详细
export function getAction(id) {
  return request({
    url: '/draw/action/' + id,
    method: 'get'
  })
}

// 新增抽奖行为
export function addAction(data) {
  return request({
    url: '/draw/action/save',
    method: 'post',
    data: data
  })
}

// 修改抽奖行为
export function updateAction(data) {
  return request({
    url: '/draw/action/update',
    method: 'post',
    data: data
  })
}

// 删除抽奖行为
export function delAction(ids) {
  return request({
    url: '/draw/action/delete',
    method: 'post',
      data: ids
  })
}

// 导出抽奖行为
export function exportAction(query) {
  return request({
    url: '/draw/action/export',
    method: 'get',
    params: query
  })
}

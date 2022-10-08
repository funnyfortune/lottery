import request from '@/utils/request'

// 查询抽奖客户列表
export function listCustomer(query) {
  return request({
    url: '/draw/customer/list',
    method: 'get',
    params: query
  })
}

// 查询抽奖客户详细
export function getCustomer(id) {
  return request({
    url: '/draw/customer/' + id,
    method: 'get'
  })
}

// 新增抽奖客户
export function addCustomer(data) {
  return request({
    url: '/draw/customer/save',
    method: 'post',
    data: data
  })
}

// 修改抽奖客户
export function updateCustomer(data) {
  return request({
    url: '/draw/customer/update',
    method: 'post',
    data: data
  })
}

// 删除抽奖客户
export function delCustomer(ids) {
  return request({
    url: '/draw/customer/delete',
    method: 'post',
      data: ids
  })
}

// 导出抽奖客户
export function exportCustomer(query) {
  return request({
    url: '/draw/customer/export',
    method: 'get',
    params: query
  })
}

export function importTemplate() {
  return request({
    url: '/draw/customer/importTemplate',
    method: 'get'
  })
}

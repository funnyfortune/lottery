import request from '@/utils/request'

// 查询抽奖礼品列表
export function listGift(query) {
  return request({
    url: '/draw/gift/list',
    method: 'get',
    params: query
  })
}

// 查询抽奖礼品详细
export function getGift(id) {
  return request({
    url: '/draw/gift/' + id,
    method: 'get'
  })
}

// 新增抽奖礼品
export function addGift(data) {
  return request({
    url: '/draw/gift/save',
    method: 'post',
    data: data
  })
}

// 修改抽奖礼品
export function updateGift(data) {
  return request({
    url: '/draw/gift/update',
    method: 'post',
    data: data
  })
}

// 删除抽奖礼品
export function delGift(ids) {
  return request({
    url: '/draw/gift/delete',
    method: 'post',
      data: ids
  })
}

// 导出抽奖礼品
export function exportGift(query) {
  return request({
    url: '/draw/gift/export',
    method: 'get',
    params: query
  })
}

import request from '@/utils/request'

export function getInfo(query) {
  return request({
    url: '/draw/lottery/info',
    method: 'get',
    params: query
  })
}

export function getResult(query) {
  return request({
    url: '/draw/lottery/result',
    method: 'get',
    params: query
  })
}

export function updateResult(query) {
  return request({
    url: '/draw/lottery/update',
    method: 'get',
    params: query
  })
}

import request from '@/utils/request'

// 查询抽奖中奖列表
export function listResult(query) {
  return request({
    url: '/draw/result/list',
    method: 'get',
    params: query
  })
}

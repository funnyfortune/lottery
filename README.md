

# 后端技术
## 系统环境
| 框架 | 说明 | 版本 |
| --- | --- | --- |
| JDK | Java 开发工具包 | >= 1.8.0(推荐1.8版本) |
| Maven | Java 管理与构建工具 | >= 3.5.0(推荐3.6.3) |

## 主框架
| 框架 | 说明 | 版本 | 学习指南 |
| --- | --- | --- | --- |
| [Spring Boot ](https://spring.io/projects/spring-boot) | 应用开发框架 | 2.3.12.RELEASE | [文档 ](https://docs.spring.io/spring-boot/docs/2.3.12.RELEASE/reference/html/) |

## 存储层
| 框架 | 说明 | 版本 | 学习指南 |
| --- | --- | --- | --- |
| [MySQL ](https://www.mysql.com/cn/) | 数据库服务器 | >= 5.7 | 文档  |
| [MyBatis Plus ](https://mp.baomidou.com/) | MyBatis 增强工具包 | 3.5.2 | [文档 ](https://baomidou.com/pages/24112f/) |
| [Dynamic Datasource ](https://gitee.com/baomidou/dynamic-datasource-spring-boot-starter) | 动态数据源 | 3.5.0 | [文档 ](https://www.kancloud.cn/tracy5546/dynamic-datasource/2264611) |
| [Redis ](https://redis.io/) | redis 数据库 | >= 5.0 | [文档 ](http://www.redis.cn/documentation.html) |


### 其它工具
| 框架 | 说明 | 版本 | 学习指南 |
| --- | --- | --- | --- |
| [Knife4j ](https://gitee.com/xiaoym/knife4j) | Swagger 增强 UI 实现 | 3.0.3 | [文档 ](https://doc.xiaominfo.com/knife4j/documentation/) |
| [hutool ](https://www.hutool.cn/) | 工具类库 | 5.7.20 | [文档 ](https://www.hutool.cn/docs) |


# 前端技术

-  [vue](https://v2.cn.vuejs.org/)是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。 
-  [npm：node.js](https://nodejs.org/zh-cn/docs/)的包管理工具，用于统一管理我们前端项目中需要用到的包、插件、工具、命令等，便于开发和维护。 
-  [ES6](/note/es6/)：Javascript的新版本，ECMAScript6的简称。利用ES6我们可以简化我们的JS代码，同时利用其提供的强大功能来快速实现JS逻辑。 
-  [vue-cli](https://cli.vuejs.org/)：Vue的脚手架工具，用于自动生成Vue项目的目录及文件。 
-  [vue-router](https://router.vuejs.org/)： Vue提供的前端路由工具，利用其我们实现页面的路由控制，局部刷新及按需加载，构建单页应用，实现前后端分离。 
-  [vuex](https://vuex.vuejs.org/zh/guide/)：Vue提供的状态管理工具，用于统一管理我们项目中各种数据的交互和重用，存储我们需要用到数据对象。 
-  [element-ui](https://element.eleme.cn/2.15/#/zh-CN/component)：基于MVVM框架Vue开源出来的一套前端ui组件。 
```vue
├── build                      # 构建相关  
├── bin                        # 执行脚本
├── public                     # 公共文件
│   ├── favicon.ico            # favicon图标
│   └── index.html             # html模板
├── src                        # 源代码
│   ├── api                    # 所有请求
│   ├── assets                 # 主题 字体等静态资源
│   ├── components             # 全局公用组件
│   ├── directive              # 全局指令
│   ├── layout                 # 布局
│   ├── plugins                # 通用方法
│   ├── router                 # 路由
│   ├── store                  # 全局 store管理
│   ├── utils                  # 全局公用方法
│   ├── views                  # view
│   ├── App.vue                # 入口页面
│   ├── main.js                # 入口 加载组件 初始化等
│   ├── permission.js          # 权限管理
│   └── settings.js            # 系统配置
├── .editorconfig              # 编码格式
├── .env.development           # 开发环境配置
├── .env.production            # 生产环境配置
├── .env.staging               # 测试环境配置
├── .eslintignore              # 忽略语法检查
├── .eslintrc.js               # eslint 配置项
├── .gitignore                 # git 忽略项
├── babel.config.js            # babel.config.js
├── package.json               # package.json
└── vue.config.js              # vue.config.js
```

# 抽奖流程
![](https://cdn.nlark.com/yuque/0/2022/jpeg/25625205/1661136108224-6858f12e-26cb-494a-af11-2479b801d74c.jpeg)
# 抽奖关系
![](https://cdn.nlark.com/yuque/0/2022/jpeg/25625205/1661136571636-726e31f2-6881-4a4a-ab91-3204d04fd3ab.jpeg)
# 抽奖算法
## 随机
:::info
如果主题创建时，没选择概率类型，则默认为**随机模式，**在随机下，系统会根据奖项的数量，该主题下**未中奖客户数量**进行随机抽奖。

- 如果奖项的数量大于客户数量，则客户一定中奖。
- 如果如果客户数量已经被使用完，则新奖项无法进行抽奖。
- 如果奖项的数量小于客户数量，则会以可抽奖客户总数量为随机总数进行奖项数量随机。
:::
## 优先级
:::info
若为**优先级**概率类型的抽奖主题，系统会根据该主题下**未中奖客户数量，客户概率**进行抽奖。
抽奖规则如下：

- 如果奖项的数量大于客户数量，则客户一定中奖。
- 如果如果客户数量已经被使用完，则新奖项无法进行抽奖。
- 如果奖项的数量小于客户数量，则会根据客户概率从大到小进行排列，依次进行概率区间抽奖，知道奖项数量被消耗完，抽奖结束。

***注意：概率为0的客户不参与抽奖。**
:::
# 抽奖主题
## 操作步骤
列表界面拥有**[奖项管理，客户管理，中奖名单，新增，修改，删除]**按钮，需要具有该按钮的权限则可使用。
点击** [抽奖管理-->抽奖主题]** 菜单，查看列表。如下图所示：
![image.png](https://cdn.nlark.com/yuque/0/2022/png/25625205/1665218094708-bd7e73d7-1143-4260-b2cb-17d2271fe852.png#clientId=u6ade8ec5-da80-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=737&id=u5302f4f7&name=image.png&originHeight=737&originWidth=1909&originalType=binary&ratio=1&rotation=0&showTitle=false&size=67950&status=done&style=none&taskId=u3f92bff6-ca23-4738-85b0-ff06e565713&title=&width=1909)

## 1.新增/编辑

点击**[新增**]或**[修改]**按钮进入到新增或编辑页面，填写相关表单信息,其中带*号信息为必填信息，如下图所示：

![image.png](https://cdn.nlark.com/yuque/0/2022/png/25625205/1661136971141-0c090c84-b716-48d2-b42a-80dc50d69de4.png#clientId=u4d421289-3eea-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=297&id=ua2413216&name=image.png&originHeight=297&originWidth=503&originalType=binary&ratio=1&rotation=0&showTitle=false&size=11716&status=done&style=none&taskId=u3db56f3d-5f59-4685-96f1-2eaf7416847&title=&width=503)
**概率类型**： 

- 随机： 系统会进行公平的随机数原则抽奖。
- 优先级： 系统根据用户对客户设置的概率进行抽奖。具体详情，请查看([客户管理](#mO5Og))
## 2.删除

> 若该抽奖主题被删除，则相应的业务信息无法查看

## 3.导出

根据选择的条件进行导出列表。
# 客户管理
## 操作步骤
列表界面拥有**[导入，导出，新增，修改，删除]**按钮，需要具有该按钮的权限则可使用。
![image.png](https://cdn.nlark.com/yuque/0/2022/png/25625205/1665218122247-76b6290a-a473-475e-9367-5c766845eab4.png#clientId=u6ade8ec5-da80-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=827&id=u55732de9&name=image.png&originHeight=827&originWidth=1912&originalType=binary&ratio=1&rotation=0&showTitle=false&size=86646&status=done&style=none&taskId=uc63056b8-17d7-4603-9e76-0ec89382318&title=&width=1912)
点击** [抽奖管理-->客户管理]** 菜单，查看列表。如下图所示：
## 1.新增/编辑
点击**[新增**]或**[修改]**按钮进入到新增或编辑页面，填写相关表单信息,其中带*号信息为必填信息，如下图所示：
![image.png](https://cdn.nlark.com/yuque/0/2022/png/25625205/1661137195491-d5e9e9ca-b296-4740-8599-b571d7b5f39b.png#clientId=u4d421289-3eea-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=546&id=u3e9b2654&name=image.png&originHeight=546&originWidth=502&originalType=binary&ratio=1&rotation=0&showTitle=false&size=19550&status=done&style=none&taskId=u7e12eab2-a0f1-44d6-8398-c0aa08ae04d&title=&width=502)
选择主题：如果该主题为优先级，则需要填写客户的概率。如下图所示：
![image.png](https://cdn.nlark.com/yuque/0/2022/png/25625205/1665218168509-bd7ff3ce-5b82-4bff-b146-d9ce4a5e776a.png#clientId=u6ade8ec5-da80-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=573&id=u1a93c501&name=image.png&originHeight=573&originWidth=536&originalType=binary&ratio=1&rotation=0&showTitle=false&size=19956&status=done&style=none&taskId=ud3d0218a-3cb2-4966-91ae-cd23dca6e57&title=&width=536)
## 客户导入
点击**[导入]**按钮进入到导入页面，如下图所示：
> 请导入前，先下载客户导入模板，根据模板信息进行导入。如下图所示：

**抽奖主题ID： 请从抽奖主题-》列表获取。**
![image.png](https://cdn.nlark.com/yuque/0/2022/png/25625205/1661137630062-798e85c2-5c8c-4971-8d05-010f256f44ec.png#clientId=u4d421289-3eea-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=408&id=uc1c547e4&name=image.png&originHeight=408&originWidth=411&originalType=binary&ratio=1&rotation=0&showTitle=false&size=14150&status=done&style=none&taskId=ue1d044a0-5bd3-4244-85f0-5625d34cd80&title=&width=411)
![image.png](https://cdn.nlark.com/yuque/0/2022/png/25625205/1661137651695-7108cc31-9bd7-4b5b-b7bc-fd7bf4a7fe99.png#clientId=u4d421289-3eea-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=132&id=u87205a04&name=image.png&originHeight=132&originWidth=711&originalType=binary&ratio=1&rotation=0&showTitle=false&size=3890&status=done&style=none&taskId=ua5212166-f5af-4fb9-9f37-bdd27fbdb95&title=&width=711)

# 奖项管理
## 操作步骤
列表界面拥有**新增，修改，删除，抽奖]**按钮，需要具有该按钮的权限则可使用。
点击** [抽奖管理-->奖项管理]** 菜单，查看列表。如下图所示：
![image.png](https://cdn.nlark.com/yuque/0/2022/png/25625205/1665218201497-35e8b19c-dfa9-474d-bf80-64f14236ec3c.png#clientId=u6ade8ec5-da80-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=590&id=uc7e13063&name=image.png&originHeight=590&originWidth=1920&originalType=binary&ratio=1&rotation=0&showTitle=false&size=50118&status=done&style=none&taskId=u80b4c6ba-093d-4560-bd1a-f556b61f7a0&title=&width=1920)
## 1.新增/编辑
点击**[新增**]或**[修改]**按钮进入到新增或编辑页面，填写相关表单信息,其中带*号信息为必填信息，如下图所示：
![image.png](https://cdn.nlark.com/yuque/0/2022/png/25625205/1665218221643-151cad36-327b-4c45-b5d0-d781ab93c6ea.png#clientId=u6ade8ec5-da80-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=379&id=u99de19c8&name=image.png&originHeight=379&originWidth=533&originalType=binary&ratio=1&rotation=0&showTitle=false&size=12888&status=done&style=none&taskId=u750c53d4-77bb-4243-833d-14fd71414a1&title=&width=533)
## 2.抽奖
:::info
在点击**开始抽奖**前，请保证该抽奖主题下已经创建了客户，否则抽奖无法正常进行。
:::
### 开始抽奖
点击**[抽**]或**[停]**按钮进行抽奖，如下图所示：
![image.png](https://cdn.nlark.com/yuque/0/2022/png/25625205/1665220054255-e57697c0-ed79-443a-bd21-7abb7dd431c3.png#clientId=u6ade8ec5-da80-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=928&id=u01aeef73&name=image.png&originHeight=928&originWidth=1869&originalType=binary&ratio=1&rotation=0&showTitle=false&size=1103867&status=done&style=none&taskId=u105597d2-29d8-4e89-b7d6-c1019c4c1e4&title=&width=1869)
### 中奖名单效果
![image.png](https://cdn.nlark.com/yuque/0/2022/png/25625205/1665220069737-9962875f-9f61-4905-a898-7da0083e9ae0.png#clientId=u6ade8ec5-da80-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=909&id=u0a0a5a3b&name=image.png&originHeight=909&originWidth=1484&originalType=binary&ratio=1&rotation=0&showTitle=false&size=797372&status=done&style=none&taskId=u53c1984a-e082-493a-9799-96b9139c971&title=&width=1484)
## 操作步骤
列表界面拥有**[导出]**按钮，需要具有该按钮的权限则可使用。
点击** [抽奖管理-->中奖名单]** 菜单，查看列表。如下图所示：
![image.png](https://cdn.nlark.com/yuque/0/2022/png/25625205/1665220087484-625013e3-cbd9-4e03-acf3-71f3755e494c.png#clientId=u6ade8ec5-da80-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=553&id=uf39abf89&name=image.png&originHeight=553&originWidth=1915&originalType=binary&ratio=1&rotation=0&showTitle=false&size=50210&status=done&style=none&taskId=u7645ea5a-270d-4372-a8c5-a7a521b8ad6&title=&width=1915)
## 1.导出

根据选择的条件进行导出列表。

# 启动

1. 启动后端服务
2. 启动前端服务  `npm run dev`

输入：http://localhost/draw 如下图所示：
![image.png](https://cdn.nlark.com/yuque/0/2022/png/25625205/1665219527682-d403c0ff-dc8e-4e91-b18b-90285b9bb60c.png#clientId=u6ade8ec5-da80-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=923&id=u6c5e51d2&name=image.png&originHeight=923&originWidth=1891&originalType=binary&ratio=1&rotation=0&showTitle=false&size=335937&status=done&style=none&taskId=u7437fc1f-5ebd-4e5b-8dcd-0c937ddb116&title=&width=1891)
账号： admin
密码 ：admin123

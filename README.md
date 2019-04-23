# ☁️ myblog
SSM框架独立搭建的个人博客系统

项目链接：https://www.seaguller.cn （备案中，暂不可用）

# 🌞 开发项目
请先看项目目录中：`关于应用本项目您需要知道的一些事`。

数据库文件未 `myblog.sql`，请自行建库并引入。

# 🌞 项目介绍
## 项目简介
本项目为 `Spring boot + Mybatis` 搭建的个人博客项目，旨在通过这个项目熟悉前后端的交互与`Spring boot`进行整体项目的构造，解决从零开始建站中要考虑的问题和困难，对于初学Spring boot的人来说是个不错的练手机会。

## 🍪 主要技术
* 用`SSM框架`进行搭建。

* 前后端交互采用`Ajax`技术，前端模板使用了`Thymeleaf`。

* 对于文章阅读量使用`redis`进行个人IP与文章ID的缓存。

* 安全方面使用 `Spring security` 安全框架进行登录注册的处理与权限的管理。

* 数据库连接池使用`druid`配置，数据库方面选择的是`mysql`。

## 🍪 环境设置
| 工具 | 名称 |
|:-------------:|:-------------:|
| 开发工具 | IDEA |
| 语言 | JDK1.8、HTML、css、js |
| 数据库 | Mysql 5.7 |
| 项目框架 | SSM |
| ORM | Mybatis |
| 安全框架 | Spring security |
| 缓存 | Redis |
| 项目构建 | Maven |
| 运行环境 | 阿里云Centos7 |

## 🍪 页面展示
### ☕ 首页
![首页](http://seaguller.oss-cn-beijing.aliyuncs.com/myblog/index.jpg)
![首页](http://seaguller.oss-cn-beijing.aliyuncs.com/myblog/index.png)

### ☕ 个人中心
![个人中心](http://seaguller.oss-cn-beijing.aliyuncs.com/myblog/user.jpg)

### ☕ 后台管理
![后台管理](http://seaguller.oss-cn-beijing.aliyuncs.com/myblog/admin.jpg)

## 🍊 项目待优化
* live2D模型加载时间过长（要10几秒，太恐怖嘞！）
* 字体文件加载时间略长（压缩过后的字体文件加载时间还是要1-3秒）
* 文章目录不能直接分级待修复（必须一级一级标题来，不能在没有一级标题的情况下直接写二级标题）
* 手机适配（目前只做了首页）
* 不同浏览器的适配（目前只做了谷歌和火狐浏览器的适配）

## 🍊 功能待增添
* 网站访客量的统计
* 个人中心“悄悄话”页面加入评论与回复显示
* 加入QQ直接登录功能
* 后台加入对“更新”与“音乐”界面的管理
* 写作暂时保存（草稿箱）
* 用户手机号码更换功能
* 对文章分类的管理与友链的增添

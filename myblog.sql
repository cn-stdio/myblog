/*
Navicat MySQL Data Transfer

Source Server         : 101.132.74.152
Source Server Version : 50643
Source Host           : 101.132.74.152:3306
Source Database       : myblog

Target Server Type    : MYSQL
Target Server Version : 50643
File Encoding         : 65001

Date: 2019-04-21 21:49:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '文章id',
  `title` varchar(120) NOT NULL COMMENT '文章标题',
  `author` varchar(120) NOT NULL COMMENT '文章作者',
  `content` longtext NOT NULL COMMENT '文章内容',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `attribute_label` varchar(120) DEFAULT NULL COMMENT '文章属性（热门）标签',
  `summary` longtext COMMENT '文章摘要',
  `article_id` bigint(20) DEFAULT NULL,
  `type` varchar(120) NOT NULL COMMENT '文章分类',
  `classify` varchar(120) NOT NULL COMMENT '文章类别（原创）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COMMENT='文章列表';

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('45', '咦？这就是传说中的开篇介绍吗！', 'cc5d76e15a0144f4ba911ca27889e88c', '# 一个普通的程序猿\n啊？开…开始了吗……？！\n\n咳咳，如你们所见，我就是这个小破站的维护人，一个普通的大二在读生，高考后立志要做一个程序猿，结果就是在离学习资源八百里外的薯片海洋里喝着阔乐躺尸，一个暑假过去，程序还没入门，身体上倒是更像猿人嘞…（嗝~）\n\n大学刚入学的时候心态总是最放松的，想12年的义务教育过来，终于脱离了高考的魔障，上了大学岂不是海阔凭鱼跃，天高任我游？（不要擅自改诗啊魂淡！）\n我呢，想了想自己的高中生活，emm……，惨不忍睹，走到迎新点，那时让我们新生每个人对自己写一句话填入时光邮筒里。我记得我写的是：\n> 我没有让你失望。\n\n以前总是听人说，如果我当初如何如何，那么我现在肯定会厉害很多，我没有灌输鸡汤的欲望，只不过现在想想这种说法未免太过可笑了些，鸡蛋焦了怨锅，球飞了怨拍子，人啊，总是喜欢找一些自以为是的理由来摆脱自己的无能，在我眼里，以前的你是个废物，现在的你必然同样是个废物，未来会怎么样我不确定，但是现在说出这话的你，想必已经为未来的废物人生打好基础了叭？\n\n人，总是要成长的。以前的我是跟他们一样的废物，每天找各种理由来推脱自己的无能，安心的躺在床上混吃等死，所以直到上了大学，我依然是一个彻头彻尾的废物。为什么不改变一下？这是我进入大学校门想的第一件事，既然到了新的环境，那么就做一个全新的自己叭，跟别人较劲我或许不擅长，但是要说跟自己较劲，我可是个一等一的好手呢。\n\n于是乎，自己给自己定目标，自己给自己施压，终于从一个废物，进化成了一名…………普通的程序猿~\n其实敲代码真的是一件很有趣的事情呢，可能正是因为我喜欢我正在做的事，才有动力让我去探索它，大一下学期的时候一头扎入了web后端的海洋，从此无法自拔，昏天黑夜（睡的那叫一个死，叫都叫不醒雾）\n\n# 关于此博客\n啊……貌似扯的有点远嘞，这个博客的搭建过程其实蛮有趣的，因为不懂前端，所以搭页面只能一点一点自己摸索，有的时候会出一些千奇百怪的错误，甚至于一些组件乱飞，好玩的叭行~\n\n很感谢我们翼灵工作室前端组的所有人，不厌其烦的为我解答一个个问题，还有我们后端组的老大，给我提出了很多宝贵的意见，阿里嘎多！\n\n本来计划1-2个月就把它写出来的，结果因为要做另一个项目，还有各种原因（1.我懒，2.我懒，3.我懒...）一拖再拖，直到现在才基本完工。\n\n有段时间真的很迷茫，BUG频出，有的时候突然想加功能就要把以前的几乎推翻重改，一度想过要不就这样叭，还好中途没有放弃坚持了下来，我果然是最厉害的，嘻嘻嘻~\n> 终有一天，你会感谢现在拼命的自己。\n\n那么就说介么多叭，目前博客还有几个小问题（比如live2D的模型加载速度太慢），哦对了，因为中文字体文件太过庞大，网页加载过于缓慢，我又想在评论区用所以静态压缩字体库是叭行的，网上的诸多方法没一个能用的，只能忍痛把漂亮的字体改成了呆板的自带字体，如果有带佬有解决方法的话，欢迎扣我~\n\n欢迎大嘎对本博客提出宝贵的意见，我一定会认（bù）真（wén）整（bù）改（gù）的！嘻嘻嘻~\n\n就这样叭，要加油鸭！', '2019-04-21 17:03:19', '2019-04-21 19:16:27', '随笔,生活', '一个普通的程序猿 啊？开…开始了吗……？！ 咳咳，如你们所见，我就是这个小破站的维护人，一个普通的大二在读生，高考后立志要做一个程序猿，结果就是在离学习资源八百...', '1555837399574', '心情窗', '原创');

-- ----------------------------
-- Table structure for attribute_article
-- ----------------------------
DROP TABLE IF EXISTS `attribute_article`;
CREATE TABLE `attribute_article` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '文章属性ID',
  `like` int(11) NOT NULL DEFAULT '0' COMMENT '喜欢数（点赞数）',
  `comment` int(11) NOT NULL DEFAULT '0' COMMENT '评论数',
  `read` int(11) NOT NULL DEFAULT '0' COMMENT '阅读量',
  `article_id` mediumtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1044 DEFAULT CHARSET=utf8 COMMENT='文章属性';

-- ----------------------------
-- Records of attribute_article
-- ----------------------------
INSERT INTO `attribute_article` VALUES ('1043', '0', '0', '2', '1555837399574');

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `msg` varchar(255) COLLATE utf8_bin NOT NULL,
  `contact` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `state` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of feedback
-- ----------------------------

-- ----------------------------
-- Table structure for reply_information
-- ----------------------------
DROP TABLE IF EXISTS `reply_information`;
CREATE TABLE `reply_information` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` bigint(20) NOT NULL,
  `respondent_id` varchar(120) COLLATE utf8_bin NOT NULL,
  `answer_id` varchar(120) COLLATE utf8_bin NOT NULL,
  `content` varchar(255) COLLATE utf8_bin NOT NULL,
  `reply_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `state` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of reply_information
-- ----------------------------

-- ----------------------------
-- Table structure for reviews_article
-- ----------------------------
DROP TABLE IF EXISTS `reviews_article`;
CREATE TABLE `reviews_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `self_id` varchar(120) NOT NULL DEFAULT '1' COMMENT '层级ID',
  `article_id` mediumtext NOT NULL COMMENT '文章ID',
  `original_author` varchar(120) NOT NULL COMMENT '文章作者',
  `answer_name` varchar(120) NOT NULL COMMENT '被回复者ID',
  `respondent_name` varchar(120) NOT NULL COMMENT '回复者ID',
  `comment_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评论时间',
  `likes` int(11) NOT NULL DEFAULT '0' COMMENT '楼层点赞数',
  `comment_content` text NOT NULL COMMENT '评论内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=232 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reviews_article
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(20) NOT NULL,
  `name` varchar(120) COLLATE utf8_bin NOT NULL DEFAULT 'ROLE_USER'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'ROLE_ADMIN');
INSERT INTO `role` VALUES ('2', 'ROLE_USER');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(255) NOT NULL,
  `gender` tinyint(4) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `birthday` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `recent_login_date` timestamp NULL DEFAULT NULL,
  `phone` varchar(255) NOT NULL COMMENT '手机号',
  `password` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `introduce` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('cc5d76e15a0144f4ba911ca27889e88c', '1', 'Seaguller', null, null, 'https://seaguller.oss-cn-beijing.aliyuncs.com/headImg/cc5d76e15a0144f4ba911ca27889e88c.jpeg', '2019-04-21 20:44:42', '17360075515', '$2a$10$owgandnaGUegBVjy9Rb3M..zgOcDycrGfpLgd1VXrITYAD7nnohHe', null, null);

-- ----------------------------
-- Table structure for user_article_like
-- ----------------------------
DROP TABLE IF EXISTS `user_article_like`;
CREATE TABLE `user_article_like` (
  `user_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `article_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user_article_like
-- ----------------------------

-- ----------------------------
-- Table structure for user_comment_like
-- ----------------------------
DROP TABLE IF EXISTS `user_comment_like`;
CREATE TABLE `user_comment_like` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` varchar(120) NOT NULL COMMENT '用户id',
  `article_id` mediumtext COMMENT '文章id',
  `self_id` varchar(20) NOT NULL COMMENT '评论层级',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1069 DEFAULT CHARSET=utf8 COMMENT='用户点赞过的评论';

-- ----------------------------
-- Records of user_comment_like
-- ----------------------------

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `role_id` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('cc5d76e15a0144f4ba911ca27889e88c', '1');

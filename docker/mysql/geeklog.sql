/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : geeklog

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-09-10 15:05:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `article_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `modified_at` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `content` text,
  `category_id` int(11) DEFAULT NULL,
  `tags` varchar(255) DEFAULT NULL,
  `display` bit(1) DEFAULT b'1',
  PRIMARY KEY (`article_id`),
  KEY `fk_article_user` (`user_id`),
  KEY `fk_article_category` (`category_id`),
  CONSTRAINT `fk_article_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_article_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('1', 'Java开发技巧', '2018-09-10 11:56:04', '2018-09-10 11:56:04', '1', '这是文章主体内容3', '2', 'Java', '');
INSERT INTO `article` VALUES ('2', 'Vue总结', '2018-09-08 11:30:48', '2018-09-08 11:30:48', '2', '这是文章主体内容2', '1', 'Vue', '');
INSERT INTO `article` VALUES ('3', 'JavaScript技巧', '2018-09-08 11:30:48', '2018-09-08 11:30:48', '3', '这是文章主体内容', '1', 'JavaScript', '');
INSERT INTO `article` VALUES ('4', 'Docker使用经验', '2018-09-08 11:30:49', '2018-09-08 11:30:49', '4', '这是文章主体内容', '3', 'Docker', '');
INSERT INTO `article` VALUES ('5', 'Python开发技巧', '2018-09-08 11:30:49', '2018-09-08 11:30:49', '5', '这是文章主体内容', '2', 'Python', '');
INSERT INTO `article` VALUES ('6', 'Jmeter压测总结', '2018-09-08 11:30:50', '2018-09-08 11:30:50', '6', '这是文章主体内容', '4', 'Jmeter', '');
INSERT INTO `article` VALUES ('7', 'Junit使用技巧', '2018-09-08 11:30:50', '2018-09-08 11:30:50', '6', '这是文章主体内容', '4', 'Junit', '');
INSERT INTO `article` VALUES ('8', 'Hadoop使用', '2018-09-08 11:30:51', '2018-09-08 11:30:51', '7', '这是文章主体内容', '6', 'Hadoop', '');
INSERT INTO `article` VALUES ('9', 'KNN算法的改进', '2018-09-08 11:30:51', '2018-09-08 11:30:51', '8', '这是文章主体内容', '5', '机器学习', '');
INSERT INTO `article` VALUES ('10', '逻辑回归的另一角度解析', '2018-09-08 11:30:53', '2018-09-08 11:30:53', '9', '这是文章主体内容', '5', '机器学习', '');

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `authority_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`authority_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authority
-- ----------------------------
INSERT INTO `authority` VALUES ('1', 'can_write_article');
INSERT INTO `authority` VALUES ('2', 'can_comment');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '前端开发', '这是前端');
INSERT INTO `category` VALUES ('2', '后端开发', '这是后端');
INSERT INTO `category` VALUES ('3', '运维', '这是运维');
INSERT INTO `category` VALUES ('4', '测试', '这是测试');
INSERT INTO `category` VALUES ('5', '机器学习', '这是机器学习');
INSERT INTO `category` VALUES ('6', '大数据', '这是大数据');

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect` (
  `collect_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `article_id` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`collect_id`),
  KEY `fk_collect_user` (`user_id`),
  KEY `fk_collect_article` (`article_id`),
  CONSTRAINT `fk_collect_article` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_collect_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of collect
-- ----------------------------
INSERT INTO `collect` VALUES ('1', '1', '1', '2018-09-06 10:08:51');
INSERT INTO `collect` VALUES ('2', '2', '1', '2018-09-06 10:09:00');
INSERT INTO `collect` VALUES ('3', '4', '2', '2018-09-06 10:09:09');
INSERT INTO `collect` VALUES ('4', '7', '3', '2018-09-06 10:09:23');
INSERT INTO `collect` VALUES ('5', '9', '1', '2018-09-06 10:09:37');
INSERT INTO `collect` VALUES ('6', '8', '2', '2018-09-06 10:09:50');
INSERT INTO `collect` VALUES ('7', '8', '1', '2018-09-06 10:09:57');
INSERT INTO `collect` VALUES ('8', '8', '8', '2018-09-06 10:10:06');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `article_id` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `root_id` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `fk_comment_user` (`user_id`),
  KEY `fk_comment_parent_id` (`parent_id`),
  KEY `fk_comment_root_id` (`root_id`),
  KEY `fk_comment_article` (`article_id`),
  CONSTRAINT `fk_comment_article` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_comment_parent_id` FOREIGN KEY (`parent_id`) REFERENCES `comment` (`comment_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_comment_root_id` FOREIGN KEY (`root_id`) REFERENCES `comment` (`comment_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_comment_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '1', '1', '这是我写的文章，欢迎大家讨论并改进', null, '1', '2018-09-06 09:50:53');
INSERT INTO `comment` VALUES ('2', '2', '1', '楼主写的不错，有自己的见解', '1', '1', '2018-09-06 09:51:16');
INSERT INTO `comment` VALUES ('3', '3', '1', '其实在文章XXX这里，还可以这样改进...', null, '3', '2018-09-06 09:53:13');
INSERT INTO `comment` VALUES ('4', '1', '1', '我觉得你说的有一些道理，我去测试跑跑快', '3', '3', '2018-09-06 09:53:43');
INSERT INTO `comment` VALUES ('5', '6', '2', '学习前端Vue中，写的不错', null, '5', '2018-09-06 09:59:48');
INSERT INTO `comment` VALUES ('6', '2', '2', '感谢感谢，一点自己的心得', '5', '5', '2018-09-06 09:59:55');
INSERT INTO `comment` VALUES ('7', '7', '2', '写的不错', '5', '5', '2018-09-06 10:00:23');
INSERT INTO `comment` VALUES ('8', '9', '2', '我也觉得确实写的不错', '7', '5', '2018-09-06 10:03:06');
INSERT INTO `comment` VALUES ('9', '1', '3', '学习了，学习了', null, '9', '2018-09-06 10:06:01');
INSERT INTO `comment` VALUES ('10', '10', '4', '文章在这里XXX，我觉得写得很不错', null, '10', '2018-09-06 10:06:09');

-- ----------------------------
-- Table structure for forbidden
-- ----------------------------
DROP TABLE IF EXISTS `forbidden`;
CREATE TABLE `forbidden` (
  `forbidden_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `authority_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`forbidden_id`),
  KEY `fk_forbidden_user` (`user_id`),
  KEY `fk_forbidden_authority` (`authority_id`),
  CONSTRAINT `fk_forbidden_authority` FOREIGN KEY (`authority_id`) REFERENCES `authority` (`authority_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_forbidden_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of forbidden
-- ----------------------------
INSERT INTO `forbidden` VALUES ('1', '4', '1');
INSERT INTO `forbidden` VALUES ('2', '5', '2');
INSERT INTO `forbidden` VALUES ('3', '6', '1');
INSERT INTO `forbidden` VALUES ('4', '7', '2');

-- ----------------------------
-- Table structure for star
-- ----------------------------
DROP TABLE IF EXISTS `star`;
CREATE TABLE `star` (
  `star_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `article_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`star_id`),
  KEY `fk_star_user` (`user_id`),
  KEY `fk_star_article` (`article_id`),
  CONSTRAINT `fk_star_article` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_star_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of star
-- ----------------------------
INSERT INTO `star` VALUES ('1', '1', '1');
INSERT INTO `star` VALUES ('2', '2', '1');
INSERT INTO `star` VALUES ('3', '3', '1');
INSERT INTO `star` VALUES ('4', '1', '2');
INSERT INTO `star` VALUES ('5', '6', '2');
INSERT INTO `star` VALUES ('6', '8', '1');
INSERT INTO `star` VALUES ('7', '8', '2');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `nickname` varchar(20) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `bio` varchar(255) DEFAULT NULL,
  `is_admin` bit(1) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `ui_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'a123456', '123456', '小啊', null, null, '');
INSERT INTO `user` VALUES ('2', 'b1234567', '123123', '测试Nickname', '测试路径222', '测试Bio', '');
INSERT INTO `user` VALUES ('3', 'c123456', '123456', '小菜1', null, null, '\0');
INSERT INTO `user` VALUES ('4', 'd123456', '123456', '小代1', null, null, '\0');
INSERT INTO `user` VALUES ('5', 'e123456', '123456', '小哦', null, null, '\0');
INSERT INTO `user` VALUES ('6', 'f123456', '123456', '小付', null, null, '\0');
INSERT INTO `user` VALUES ('7', 'g123456', '123456', '小高', null, null, '\0');
INSERT INTO `user` VALUES ('8', 'h123456', '123456', '小黄', null, null, '\0');
INSERT INTO `user` VALUES ('9', 'i123456', '123456', '小爱', null, null, '\0');
INSERT INTO `user` VALUES ('10', 'j123456', '123456', '小建', null, null, '\0');

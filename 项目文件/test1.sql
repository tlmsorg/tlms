/*
Navicat MySQL Data Transfer

Source Server         : mysql用户连接
Source Server Version : 50551
Source Host           : localhost:3306
Source Database       : testdb

Target Server Type    : MYSQL
Target Server Version : 50551
File Encoding         : 65001

Date: 2016-09-23 10:43:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `test1`
-- ----------------------------
DROP TABLE IF EXISTS `test1`;
CREATE TABLE `test1` (
  `userId` varchar(20) COLLATE utf8_romanian_ci DEFAULT NULL,
  `couse` varchar(20) COLLATE utf8_romanian_ci DEFAULT NULL,
  `point` varchar(20) COLLATE utf8_romanian_ci DEFAULT NULL,
  `id` varchar(20) COLLATE utf8_romanian_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_romanian_ci;

-- ----------------------------
-- Records of test1
-- ----------------------------
INSERT INTO `test1` VALUES ('1', '1', '1', '1');

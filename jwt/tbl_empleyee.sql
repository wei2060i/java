/*
Navicat MySQL Data Transfer

Source Server         : admin
Source Server Version : 50553
Source Host           : localhost:3306
Source Database       : mybatis

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2019-10-16 22:58:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tbl_empleyee
-- ----------------------------
DROP TABLE IF EXISTS `tbl_empleyee`;
CREATE TABLE `tbl_empleyee` (
  `id` char(19) NOT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  `dept_id` char(19) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_empleyee
-- ----------------------------
INSERT INTO `tbl_empleyee` VALUES ('1184124732035825665', '唐昊', '110@qq.com', '1', '1184132521349087234');
INSERT INTO `tbl_empleyee` VALUES ('1184122608560373762', '小舞', '112@qq.com', '2', '1184132521349087234');
INSERT INTO `tbl_empleyee` VALUES ('1184125389715275777', '无天', '119@qq.com', '1', '1184132521349087234');

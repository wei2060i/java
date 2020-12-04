/*
Navicat MySQL Data Transfer

Source Server         : admin
Source Server Version : 50553
Source Host           : localhost:3306
Source Database       : mybatis

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2019-10-16 22:58:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tbl_department
-- ----------------------------
DROP TABLE IF EXISTS `tbl_department`;
CREATE TABLE `tbl_department` (
  `id` char(19) NOT NULL,
  `dept_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_department
-- ----------------------------
INSERT INTO `tbl_department` VALUES ('1184132810600873986', '市场部');
INSERT INTO `tbl_department` VALUES ('1184132521349087234', '开发部');

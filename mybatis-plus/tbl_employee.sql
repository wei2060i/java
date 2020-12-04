/*
Navicat MySQL Data Transfer

Source Server         : admin
Source Server Version : 50553
Source Host           : localhost:3306
Source Database       : chen

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2019-10-22 23:12:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tbl_employee
-- ----------------------------
DROP TABLE IF EXISTS `tbl_employee`;
CREATE TABLE `tbl_employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `last_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_employee
-- ----------------------------
INSERT INTO `tbl_employee` VALUES ('1', '小舞', '22222@23', '1', '12');
INSERT INTO `tbl_employee` VALUES ('2', '小舞', '22222@23', '11', '11');
INSERT INTO `tbl_employee` VALUES ('3', '小舞', '22222@23', '12', '22');

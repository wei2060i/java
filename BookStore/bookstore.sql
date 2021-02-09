/*
Navicat MySQL Data Transfer

Source Server         : admin
Source Server Version : 50553
Source Host           : localhost:3306
Source Database       : bookstore

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2019-03-21 22:26:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for orderitem
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem` (
  `order_id` varchar(100) NOT NULL DEFAULT '',
  `product_id` varchar(100) NOT NULL DEFAULT '',
  `buynum` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_id`,`product_id`),
  KEY `product_id` (`product_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderitem
-- ----------------------------
INSERT INTO `orderitem` VALUES ('f850c7cc-b44c-4acd-ae64-4a30231ca426', '1', '2');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` varchar(100) NOT NULL DEFAULT '',
  `money` double DEFAULT NULL,
  `receiverAddress` varchar(255) DEFAULT NULL,
  `receiverName` varchar(20) DEFAULT NULL,
  `receiverPhone` varchar(20) DEFAULT NULL,
  `paystate` int(11) DEFAULT NULL,
  `ordertime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('f850c7cc-b44c-4acd-ae64-4a30231ca426', '200', 'xxx', 'xxx', 'xxx', '0', '2019-03-21 19:52:52', '5');

-- ----------------------------
-- Table structure for products
-- ----------------------------
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products` (
  `id` varchar(100) NOT NULL DEFAULT '',
  `name` varchar(40) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `category` varchar(40) DEFAULT NULL,
  `pnum` int(11) DEFAULT NULL,
  `img_url` varchar(100) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of products
-- ----------------------------
INSERT INTO `products` VALUES ('1', '领家有女初长成', '100', '文学', '3', 'TS9.jpg', '言情');
INSERT INTO `products` VALUES ('2', '三国演义', '79', '文学', '-12', 'TS13.jpg', '经典');
INSERT INTO `products` VALUES ('3', 'Think int java', '78', '文学', '4', 'TS9.jpg', '故事');
INSERT INTO `products` VALUES ('4', 'java Web', '65', '文学', '7', 'TS13.jsp', '不是');
INSERT INTO `products` VALUES ('5', 'html入门', '78', '文学', '66', 'TS7.jpg', '故事');
INSERT INTO `products` VALUES ('6', 'css精通', '56', '文学', '77', 'TS8.jpg', '故事');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `PASSWORD` varchar(20) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `introduce` varchar(100) DEFAULT NULL,
  `activeCode` varchar(50) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `role` varchar(10) DEFAULT '普通用户',
  `registTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '123456', '女', '2532975042@qq.com', '12344432423', '漂亮', 'ff6e6d56-d7bd-43c5-a4ae-16ca927228f0', '1', 'admin', '2019-03-19 20:26:26');
INSERT INTO `user` VALUES ('5', 'adc', '123456', '女', '18272637169@163.com', '12344432422', '1333', '0dd6d7df-2084-459f-b7c3-6bdcdefdcec3', '1', '普通用户', '2019-03-19 23:06:29');
INSERT INTO `user` VALUES ('6', 'adc', '123456', '女', '18272637169@163.com', '12344432423', '1333', 'b075f0e0-02bd-4860-be33-cf245eb28337', '0', '普通用户', '2019-03-19 17:36:09');

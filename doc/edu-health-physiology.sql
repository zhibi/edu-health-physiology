/*
 Navicat Premium Data Transfer

 Source Server         : zhibi
 Source Server Type    : MySQL
 Source Server Version : 50173
 Source Host           : 120.27.24.193:3306
 Source Schema         : edu-health-physiology

 Target Server Type    : MySQL
 Target Server Version : 50173
 File Encoding         : 65001

 Date: 29/05/2019 10:43:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for info
-- ----------------------------
DROP TABLE IF EXISTS `info`;
CREATE TABLE `info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NULL DEFAULT 0,
  `addtime` datetime NULL DEFAULT NULL,
  `tiwen` int(11) NULL DEFAULT 0,
  `maibo` int(11) NULL DEFAULT 0,
  `xuetang` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1000004 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of info
-- ----------------------------
INSERT INTO `info` VALUES (1000000, 1000001, '2019-05-29 10:41:51', 361, 651, 1451);
INSERT INTO `info` VALUES (1000001, 1000001, '2019-05-29 10:42:05', 11, 11, 11);
INSERT INTO `info` VALUES (1000002, 1000001, '2019-05-29 10:42:09', 22, 22, 22);
INSERT INTO `info` VALUES (1000003, 1000001, '2019-05-29 10:42:13', 33, 33, 33);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1000002 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1000000, 'admin', '1234', '超级管理员', 'admin@admin.com', '123');
INSERT INTO `user` VALUES (1000001, '666', '666', NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;

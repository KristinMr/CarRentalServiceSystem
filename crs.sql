/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : crs

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 13/07/2019 16:53:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for brand
-- ----------------------------
DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand`  (
  `brand_id` int(10) NOT NULL AUTO_INCREMENT,
  `brand_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `brand_info` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `brand_recycle_bin` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`brand_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of brand
-- ----------------------------
INSERT INTO `brand` VALUES (1, '劳斯莱斯', '劳斯莱斯', 0);
INSERT INTO `brand` VALUES (2, '玛莎拉蒂', NULL, 0);
INSERT INTO `brand` VALUES (3, '桑塔纳', '桑塔纳', 0);

-- ----------------------------
-- Table structure for car
-- ----------------------------
DROP TABLE IF EXISTS `car`;
CREATE TABLE `car`  (
  `car_id` int(10) NOT NULL AUTO_INCREMENT,
  `car_model` int(10) NULL DEFAULT NULL,
  `car_state` int(10) NULL DEFAULT NULL,
  `car_city` int(10) NULL DEFAULT NULL,
  `car_picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `car_info` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `car_recycle_bin` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`car_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of car
-- ----------------------------
INSERT INTO `car` VALUES (1, 4, 3, 3, NULL, '第一辆', 0);
INSERT INTO `car` VALUES (2, 2, 1, 4, NULL, '第二辆', 0);
INSERT INTO `car` VALUES (3, 4, 4, 4, NULL, '第三辆', 0);
INSERT INTO `car` VALUES (4, 4, 1, 3, NULL, '第四辆', 0);
INSERT INTO `car` VALUES (5, 3, 2, 2, NULL, '第五辆', 0);

-- ----------------------------
-- Table structure for city
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city`  (
  `city_id` int(10) NOT NULL AUTO_INCREMENT,
  `city_province` int(10) NULL DEFAULT NULL,
  `city_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `city_info` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `city_recycle_bin` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`city_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of city
-- ----------------------------
INSERT INTO `city` VALUES (1, 1, '北京市', NULL, 0);
INSERT INTO `city` VALUES (2, 2, '天津市', NULL, 0);
INSERT INTO `city` VALUES (3, 3, '上海市', NULL, 0);
INSERT INTO `city` VALUES (4, 4, '重庆市', '', 0);

-- ----------------------------
-- Table structure for model
-- ----------------------------
DROP TABLE IF EXISTS `model`;
CREATE TABLE `model`  (
  `model_id` int(10) NOT NULL AUTO_INCREMENT,
  `model_brand` int(10) NOT NULL,
  `model_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `model_color` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `model_rent` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `model_Info` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `model_recycle_bin` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`model_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of model
-- ----------------------------
INSERT INTO `model` VALUES (1, 1, 'YY', '白色', '1000', NULL, 0);
INSERT INTO `model` VALUES (2, 1, 'XX', '黑色', '1200', NULL, 0);
INSERT INTO `model` VALUES (3, 2, 'LM', '红色', '800', NULL, 0);
INSERT INTO `model` VALUES (4, 2, 'KK', '紫色', '700', NULL, 0);

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `order_id` int(10) NOT NULL AUTO_INCREMENT,
  `order_user` int(10) NOT NULL,
  `order_car` int(10) NOT NULL,
  `order_time` date NOT NULL,
  `order_stime` date NOT NULL,
  `order_etime` date NOT NULL,
  `order_state` int(10) NOT NULL,
  `order_info` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for province
-- ----------------------------
DROP TABLE IF EXISTS `province`;
CREATE TABLE `province`  (
  `province_id` int(10) NOT NULL AUTO_INCREMENT,
  `province_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `province_info` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `province_recycle_bin` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`province_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of province
-- ----------------------------
INSERT INTO `province` VALUES (1, '北京', NULL, 0);
INSERT INTO `province` VALUES (2, '天津', NULL, 0);
INSERT INTO `province` VALUES (3, '上海', NULL, 0);
INSERT INTO `province` VALUES (4, '重庆', NULL, 0);
INSERT INTO `province` VALUES (5, '河北省', NULL, 0);
INSERT INTO `province` VALUES (6, '山西省', NULL, 0);
INSERT INTO `province` VALUES (7, '辽宁省', NULL, 0);

-- ----------------------------
-- Table structure for rank
-- ----------------------------
DROP TABLE IF EXISTS `rank`;
CREATE TABLE `rank`  (
  `rank_id` int(10) NOT NULL AUTO_INCREMENT,
  `rank_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `rank_info` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `rank_recycle_bin` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`rank_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rank
-- ----------------------------
INSERT INTO `rank` VALUES (1, '超级管理员', '拥有管理所有用户的权限和所有系统资源', 0);
INSERT INTO `rank` VALUES (2, '管理员2', '管理普通用户资源', 1);
INSERT INTO `rank` VALUES (3, '普通用户', '一般', 0);
INSERT INTO `rank` VALUES (4, '普通', '二班', 0);
INSERT INTO `rank` VALUES (5, '普通', '二班', 0);

-- ----------------------------
-- Table structure for state
-- ----------------------------
DROP TABLE IF EXISTS `state`;
CREATE TABLE `state`  (
  `state_id` int(11) NOT NULL AUTO_INCREMENT,
  `state_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state_info` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `state_recycle_bin` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`state_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of state
-- ----------------------------
INSERT INTO `state` VALUES (1, '空闲', NULL, 0);
INSERT INTO `state` VALUES (2, '被预约', NULL, 0);
INSERT INTO `state` VALUES (3, '租赁中', NULL, 0);
INSERT INTO `state` VALUES (4, '维修', NULL, 0);
INSERT INTO `state` VALUES (5, '维修', '维修', 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_rank` int(10) NOT NULL,
  `user_sex` int(10) NULL DEFAULT NULL,
  `user_idn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_dln` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_dage` int(10) NULL DEFAULT NULL,
  `user_age` int(10) NULL DEFAULT NULL,
  `user_adderss` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_info` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `user_recycle_bin` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '123456', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);

SET FOREIGN_KEY_CHECKS = 1;

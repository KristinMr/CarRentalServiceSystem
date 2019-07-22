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

 Date: 22/07/2019 14:50:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `admin_id` int(10) NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `admin_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `admin_sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `admin_idn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `admin_age` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `admin_tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `admin_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `admin_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `admin_info` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `admin_rank` int(10) NULL DEFAULT NULL,
  `admin_recycle_bin` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`admin_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'Kristin', '123456', '男', NULL, NULL, NULL, NULL, NULL, NULL, 1, 0);
INSERT INTO `admin` VALUES (2, 'Admin', '123456', '女', NULL, NULL, NULL, NULL, NULL, NULL, 2, 0);

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
  `car_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `car_model` int(10) NULL DEFAULT NULL,
  `car_state` int(10) NULL DEFAULT NULL,
  `car_info` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `car_recycle_bin` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`car_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of car
-- ----------------------------
INSERT INTO `car` VALUES (1, '123456', 4, 3, '第一辆', 0);
INSERT INTO `car` VALUES (2, '215363', 2, 2, '第二辆', 0);
INSERT INTO `car` VALUES (3, '1518513', 4, 4, '第三辆', 0);
INSERT INTO `car` VALUES (4, '156156', 4, 2, '第四辆', 0);
INSERT INTO `car` VALUES (5, '325', 3, 2, '第五辆', 0);
INSERT INTO `car` VALUES (6, '13213214144', 4, 2, 'dafdsa', 0);
INSERT INTO `car` VALUES (7, '12313', 1, 3, '', 0);
INSERT INTO `car` VALUES (8, '111212', 1, 2, '', 0);
INSERT INTO `car` VALUES (9, '3333333', 1, 2, '', 0);
INSERT INTO `car` VALUES (10, '3333333332', 1, 3, '', 0);
INSERT INTO `car` VALUES (11, '00000989', 1, 1, '', 0);
INSERT INTO `car` VALUES (12, '5555554', 1, 1, '', 0);

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
-- Table structure for oorder
-- ----------------------------
DROP TABLE IF EXISTS `oorder`;
CREATE TABLE `oorder`  (
  `order_id` int(10) NOT NULL AUTO_INCREMENT,
  `order_admin` int(10) NOT NULL,
  `order_user` int(10) NOT NULL,
  `order_car` int(10) NOT NULL,
  `order_time` date NOT NULL,
  `order_stime` date NOT NULL,
  `order_etime` date NULL DEFAULT NULL,
  `order_state` int(10) NULL DEFAULT NULL,
  `order_info` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `order_recycle_bin` int(10) NOT NULL,
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oorder
-- ----------------------------
INSERT INTO `oorder` VALUES (1, 1, 1, 1, '2019-07-16', '2019-07-16', NULL, 6, NULL, 0);
INSERT INTO `oorder` VALUES (2, 1, 2, 4, '2019-07-19', '2019-07-19', NULL, 7, '第一次下单测试', 1);
INSERT INTO `oorder` VALUES (3, 1, 1, 6, '2019-07-20', '2019-07-20', NULL, 7, '第二次下单测试', 0);
INSERT INTO `oorder` VALUES (4, 1, 1, 7, '2019-07-20', '2019-07-20', NULL, 6, '', 0);
INSERT INTO `oorder` VALUES (5, 1, 1, 8, '2019-07-20', '2019-07-20', NULL, 7, '', 0);
INSERT INTO `oorder` VALUES (6, 1, 2, 10, '2019-07-20', '2019-07-20', NULL, 6, '', 0);
INSERT INTO `oorder` VALUES (7, 1, 1, 9, '2019-07-20', '2019-07-20', NULL, 6, '', 0);

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
INSERT INTO `rank` VALUES (2, '管理员', '管理普通用户资源', 0);
INSERT INTO `rank` VALUES (3, '普通用户', '一般', 0);
INSERT INTO `rank` VALUES (4, '普通', '二班', 0);
INSERT INTO `rank` VALUES (5, '普通', '二班', 0);

-- ----------------------------
-- Table structure for recharge
-- ----------------------------
DROP TABLE IF EXISTS `recharge`;
CREATE TABLE `recharge`  (
  `recharge_id` int(10) NOT NULL AUTO_INCREMENT,
  `recharge_admin` int(10) NULL DEFAULT NULL,
  `recharge_user` int(10) NULL DEFAULT NULL,
  `recharge_num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `recharge_date` datetime(0) NULL DEFAULT NULL,
  `recharge_info` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `recharge_recycle_bin` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`recharge_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of recharge
-- ----------------------------
INSERT INTO `recharge` VALUES (1, 1, 1, '2000', '2019-07-16 19:20:07', NULL, 0);
INSERT INTO `recharge` VALUES (2, 1, 2, '3000', '2019-07-17 19:20:10', NULL, 0);
INSERT INTO `recharge` VALUES (3, 1, 2, '1000', '2019-07-18 19:24:05', '第一次充值测试', 0);
INSERT INTO `recharge` VALUES (4, 1, 2, '2000', '2019-07-18 19:41:59', '第二次充值测试', 0);

-- ----------------------------
-- Table structure for state
-- ----------------------------
DROP TABLE IF EXISTS `state`;
CREATE TABLE `state`  (
  `state_id` int(11) NOT NULL AUTO_INCREMENT,
  `state_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state_type` int(10) NULL DEFAULT NULL,
  `state_info` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `state_recycle_bin` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`state_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of state
-- ----------------------------
INSERT INTO `state` VALUES (1, '空闲', 1, NULL, 0);
INSERT INTO `state` VALUES (2, '被预约', 1, NULL, 0);
INSERT INTO `state` VALUES (3, '租赁中', 1, NULL, 0);
INSERT INTO `state` VALUES (4, '维修', 1, NULL, 0);
INSERT INTO `state` VALUES (5, '维修', 1, '维修', 0);
INSERT INTO `state` VALUES (6, '正常', 2, NULL, 0);
INSERT INTO `state` VALUES (7, '预约', 2, '', 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_rank` int(10) NULL DEFAULT NULL,
  `user_sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_idn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_dln` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_dage` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_age` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_money` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_info` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `user_recycle_bin` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '张三', '123456', 3, '男', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', NULL, 0);
INSERT INTO `user` VALUES (2, '李四', '123456', 3, '女', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '3000.0', NULL, 0);

SET FOREIGN_KEY_CHECKS = 1;

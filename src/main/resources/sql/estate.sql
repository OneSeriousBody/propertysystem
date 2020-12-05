/*
 Navicat Premium Data Transfer

 Source Server         : 47.103.223.46
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : 47.103.223.46:3306
 Source Schema         : estate

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 05/12/2020 17:06:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for broadband
-- ----------------------------
DROP TABLE IF EXISTS `broadband`;
CREATE TABLE `broadband`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `owner_id` int(0) NULL DEFAULT NULL,
  `type_id` int(0) NULL DEFAULT NULL,
  `name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `price` double(10, 2) NULL DEFAULT NULL,
  `create_time` date NULL DEFAULT NULL,
  `over_time` date NULL DEFAULT NULL,
  `flag` int(0) NULL DEFAULT NULL COMMENT '是否自动续费',
  `status` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of broadband
-- ----------------------------
INSERT INTO `broadband` VALUES (1, 13, NULL, '20M宽带', 160.00, '2020-11-28', '2021-03-28', 1, 1);
INSERT INTO `broadband` VALUES (2, 12, NULL, '50M宽带', 120.00, '2020-11-28', '2021-01-28', 1, 1);
INSERT INTO `broadband` VALUES (3, 14, 5, '20M宽带', 240.00, '2020-12-01', '2021-06-01', 1, 0);
INSERT INTO `broadband` VALUES (4, 16, 7, '50M宽带', 300.00, '2020-12-05', '2021-05-05', 1, 0);

-- ----------------------------
-- Table structure for building
-- ----------------------------
DROP TABLE IF EXISTS `building`;
CREATE TABLE `building`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `numbers` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `uints` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remarks` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of building
-- ----------------------------
INSERT INTO `building` VALUES (1, '1号楼', 'A单元', '无');
INSERT INTO `building` VALUES (7, '2号楼', 'A单元', '无');
INSERT INTO `building` VALUES (11, '3号楼', 'B单元', '无');

-- ----------------------------
-- Table structure for carcharge
-- ----------------------------
DROP TABLE IF EXISTS `carcharge`;
CREATE TABLE `carcharge`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `pay_date` datetime(0) NULL DEFAULT NULL,
  `end_date` date NULL DEFAULT NULL,
  `money` double(10, 2) NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  `owner_id` int(0) NULL DEFAULT NULL,
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收费类型',
  `park_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of carcharge
-- ----------------------------
INSERT INTO `carcharge` VALUES (14, '2020-11-28 11:18:19', '2020-12-28', 260.00, 1, 12, '停车费', NULL, 4);
INSERT INTO `carcharge` VALUES (15, '2020-12-01 11:50:32', '2021-01-01', 260.00, 0, 14, '无', '停车费', 5);
INSERT INTO `carcharge` VALUES (16, '2020-12-01 14:13:39', '2021-01-01', 260.00, 0, 16, '无', '停车费', 6);
INSERT INTO `carcharge` VALUES (17, '2020-12-05 07:42:21', '2021-03-05', 780.00, 0, 18, '无', '停车费', 7);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `owner_id` int(0) NULL DEFAULT NULL COMMENT '业主id',
  `comment` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评论内容',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '评论时间',
  `score` int(0) NULL DEFAULT NULL COMMENT '评分',
  `repair_id` int(0) NULL DEFAULT NULL,
  `repair_personnel_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, 12, '师傅手艺很好', '2020-11-30 12:47:07', 5, 20, 1);
INSERT INTO `comment` VALUES (2, 12, '好评', '2020-12-01 08:38:23', 5, 21, 4);
INSERT INTO `comment` VALUES (4, 12, '好好好', '2020-12-01 12:35:29', 5, 22, 1);
INSERT INTO `comment` VALUES (5, 12, '速度很快', '2020-12-05 05:11:13', 5, 23, 2);

-- ----------------------------
-- Table structure for complaint
-- ----------------------------
DROP TABLE IF EXISTS `complaint`;
CREATE TABLE `complaint`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `com_id` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `com_date` datetime(0) NULL DEFAULT NULL,
  `handle_date` datetime(0) NULL DEFAULT NULL,
  `owner_id` int(0) NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  `clr` int(0) NULL DEFAULT NULL COMMENT '处理人',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of complaint
-- ----------------------------
INSERT INTO `complaint` VALUES (5, '1', '2020-11-28 14:02:31', '2020-12-01 20:08:19', 12, 1, 1, '隔壁一直乱扔垃圾');
INSERT INTO `complaint` VALUES (6, '1', '2020-12-01 12:09:57', '2020-12-01 12:10:44', 12, 1, 1, '垃圾太多了');
INSERT INTO `complaint` VALUES (7, '3', '2020-12-01 12:13:06', '2020-12-05 04:53:56', 12, 1, 1, '差评');
INSERT INTO `complaint` VALUES (8, '3', '2020-12-05 03:28:37', NULL, 12, 0, NULL, '垃圾太多了');
INSERT INTO `complaint` VALUES (9, '3', '2020-12-05 03:28:53', NULL, 12, 0, NULL, '突然想投诉一下');

-- ----------------------------
-- Table structure for complaint_type
-- ----------------------------
DROP TABLE IF EXISTS `complaint_type`;
CREATE TABLE `complaint_type`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remarks` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of complaint_type
-- ----------------------------
INSERT INTO `complaint_type` VALUES (1, '垃圾乱放', NULL, NULL);
INSERT INTO `complaint_type` VALUES (2, '绿植太差', NULL, NULL);
INSERT INTO `complaint_type` VALUES (3, '其他', NULL, NULL);

-- ----------------------------
-- Table structure for house
-- ----------------------------
DROP TABLE IF EXISTS `house`;
CREATE TABLE `house`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `storey` int(0) NULL DEFAULT NULL,
  `numbers` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  `into_date` datetime(0) NULL DEFAULT NULL,
  `building_id` int(0) NULL DEFAULT NULL,
  `remarks` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `area` double(10, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of house
-- ----------------------------
INSERT INTO `house` VALUES (6, NULL, '1-402', 1, '2020-11-27 16:00:00', 1, '无', 100.00);
INSERT INTO `house` VALUES (7, NULL, '1-401', 1, '2020-11-27 16:00:00', 1, '无', 100.00);
INSERT INTO `house` VALUES (8, NULL, '1-403', 1, '2020-11-28 16:00:00', 1, '无', 100.00);
INSERT INTO `house` VALUES (9, NULL, '1-404', 1, '2020-12-05 15:36:28', 1, '无', 100.00);
INSERT INTO `house` VALUES (10, NULL, '1-405', 1, '2020-12-04 16:00:00', 1, '无', 120.00);

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `content` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fbdate` datetime(0) NULL DEFAULT NULL,
  `title` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公告标题',
  `remarks` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (1, '测试内容', '2020-11-27 11:33:30', '测试', '备注11', NULL);
INSERT INTO `notice` VALUES (4, '测试的公告内容', '2020-12-01 11:08:52', '测试公告', '备注111111', NULL);

-- ----------------------------
-- Table structure for owner
-- ----------------------------
DROP TABLE IF EXISTS `owner`;
CREATE TABLE `owner`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tel` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `identity` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `house_id` int(0) NULL DEFAULT NULL,
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'email',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `broadband_id` int(0) NULL DEFAULT NULL COMMENT '宽带套餐id',
  `password` varchar(28) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `I_name`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of owner
-- ----------------------------
INSERT INTO `owner` VALUES (12, 'xhh', '13824567809', '男', '41123119801122', 6, '714617106@qq.com', '无', NULL, '123456');
INSERT INTO `owner` VALUES (13, 'ltg', '13824567809', '男', '441581199801234', 7, '714617106@qq.com', '无', NULL, '123456');
INSERT INTO `owner` VALUES (14, 'zjs', '15920307514', '男', '441581199801234', 8, '714617106@qq.com', '无', NULL, '123456');
INSERT INTO `owner` VALUES (16, 'cxs', '15920307514', '男', '441581199801235', 9, '714617106@qq.com', '无', NULL, '123456');
INSERT INTO `owner` VALUES (18, 'xxy', '13824567809', '男', '441581199801234', 10, '714617106@qq.com', '无', NULL, '123456');

-- ----------------------------
-- Table structure for parking
-- ----------------------------
DROP TABLE IF EXISTS `parking`;
CREATE TABLE `parking`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `numbers` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  `license` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车牌号',
  `owner_id` int(0) NULL DEFAULT NULL,
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of parking
-- ----------------------------
INSERT INTO `parking` VALUES (4, 'A-1001', 1, NULL, 12, '无');
INSERT INTO `parking` VALUES (5, '1-1002', 1, NULL, 14, '无');
INSERT INTO `parking` VALUES (6, '1-1003', 1, NULL, 16, '无无无');
INSERT INTO `parking` VALUES (7, '1-1005', 1, NULL, 18, '无');

-- ----------------------------
-- Table structure for property_info
-- ----------------------------
DROP TABLE IF EXISTS `property_info`;
CREATE TABLE `property_info`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `type_id` int(0) NULL DEFAULT NULL,
  `money` double(10, 2) NULL DEFAULT NULL,
  `start_date` datetime(0) NULL DEFAULT NULL,
  `end_date` datetime(0) NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  `house_id` int(0) NULL DEFAULT NULL,
  `remarks` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of property_info
-- ----------------------------
INSERT INTO `property_info` VALUES (1, 2, 900.00, '2020-10-01 09:28:11', '2020-12-29 16:00:00', 1, 1, '啊水水');
INSERT INTO `property_info` VALUES (7, 2, 450.00, '2020-11-29 16:00:00', '2020-11-19 16:00:00', 0, 3, 'ceshi');
INSERT INTO `property_info` VALUES (24, 1, 234.00, '2020-09-30 16:00:00', '2020-12-30 16:00:00', 0, 2, 'ceshi');
INSERT INTO `property_info` VALUES (25, 1, 260.00, '2020-09-30 16:00:00', '2020-12-30 16:00:00', 1, 3, 'ceshi');

-- ----------------------------
-- Table structure for property_type
-- ----------------------------
DROP TABLE IF EXISTS `property_type`;
CREATE TABLE `property_type`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` double(10, 2) NULL DEFAULT NULL,
  `unit` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` int(0) NULL DEFAULT NULL COMMENT '1是宽带类型的数据，0是费宽带类型的数据',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of property_type
-- ----------------------------
INSERT INTO `property_type` VALUES (1, '物业费', 2.60, '月', 3, NULL);
INSERT INTO `property_type` VALUES (2, '水费', 4.50, '吨', 0, NULL);
INSERT INTO `property_type` VALUES (3, '电费', 0.50, '度', 0, NULL);
INSERT INTO `property_type` VALUES (4, '车位费', 260.00, '个', 2, NULL);
INSERT INTO `property_type` VALUES (5, '20M宽带', 40.00, '月', 1, NULL);
INSERT INTO `property_type` VALUES (7, '50M宽带', 60.00, '月', 1, NULL);
INSERT INTO `property_type` VALUES (9, '100M宽带', 88.00, '月', 1, NULL);

-- ----------------------------
-- Table structure for records
-- ----------------------------
DROP TABLE IF EXISTS `records`;
CREATE TABLE `records`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `type_id` int(0) NULL DEFAULT NULL,
  `num` double(5, 2) NULL DEFAULT NULL,
  `num2` double(5, 2) NULL DEFAULT NULL,
  `house_id` int(0) NULL DEFAULT NULL,
  `up_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `on_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `status` int(0) NULL DEFAULT NULL,
  `price` double(10, 2) NULL DEFAULT NULL COMMENT '费用',
  `check_time` datetime(0) NULL DEFAULT NULL COMMENT '记表时间',
  `meter` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of records
-- ----------------------------
INSERT INTO `records` VALUES (25, 2, 0.00, 300.00, 6, '2020-12-28 12:28:47', '2020-11-28 12:28:47', 1, 1350.00, '2020-11-28 16:00:00', '张三', '无');
INSERT INTO `records` VALUES (28, 2, 0.00, 20.00, 7, '2020-12-28 13:59:18', '2020-11-28 13:59:18', 1, 90.00, '2020-11-27 16:00:00', '李四', '无');

-- ----------------------------
-- Table structure for repair
-- ----------------------------
DROP TABLE IF EXISTS `repair`;
CREATE TABLE `repair`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `com_id` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `com_date` datetime(0) NULL DEFAULT NULL,
  `hope_date` datetime(0) NULL DEFAULT NULL,
  `handle_date` datetime(0) NULL DEFAULT NULL,
  `phone` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` double(10, 2) NULL DEFAULT NULL,
  `price_detail` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '维修费用详情',
  `owner_id` int(0) NULL DEFAULT NULL,
  `personnel_id` int(0) NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  `clr` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of repair
-- ----------------------------
INSERT INTO `repair` VALUES (20, '2', '2020-11-28 15:40:42', '2020-11-28 16:00:00', NULL, '15920307511', 100.00, '人工费50,器材50', 12, 1, 3, NULL, '热水器坏了');
INSERT INTO `repair` VALUES (21, '1', '2020-12-01 08:35:26', '2020-12-01 16:00:00', NULL, '15920307511', 100.00, '人工50，材料50', 12, 4, 2, NULL, '无');
INSERT INTO `repair` VALUES (22, '1', '2020-12-01 12:15:24', '2020-11-30 16:00:00', NULL, '15920307511', 120.00, '人工费50，材料70', 12, 1, 2, NULL, '无');
INSERT INTO `repair` VALUES (23, '3', '2020-12-05 03:29:11', '2020-12-05 16:00:00', NULL, '15920307511', 100.00, '人工50，材料50', 12, 2, 2, NULL, '无');
INSERT INTO `repair` VALUES (24, '5', '2020-12-05 04:14:24', '2020-12-04 16:00:00', NULL, '1740128366', NULL, NULL, 12, 1, 1, NULL, '无');
INSERT INTO `repair` VALUES (25, '6', '2020-12-05 04:14:39', '2020-12-04 16:00:00', NULL, '1740128366', NULL, NULL, 12, NULL, 0, NULL, '无');
INSERT INTO `repair` VALUES (26, '6', '2020-12-05 04:14:53', '2020-12-04 16:00:00', NULL, '1740128366', NULL, NULL, 12, NULL, 0, NULL, '无');
INSERT INTO `repair` VALUES (27, '7', '2020-12-05 04:15:18', '2020-12-04 16:00:00', NULL, '1740128366', NULL, NULL, 12, NULL, 0, NULL, '无');
INSERT INTO `repair` VALUES (28, '4', '2020-12-05 04:15:38', '2020-12-04 16:00:00', NULL, '1740128366', NULL, NULL, 12, NULL, 0, NULL, '无');

-- ----------------------------
-- Table structure for repair_personnel
-- ----------------------------
DROP TABLE IF EXISTS `repair_personnel`;
CREATE TABLE `repair_personnel`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `no` int(0) NULL DEFAULT NULL COMMENT '员工工号',
  `phone` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of repair_personnel
-- ----------------------------
INSERT INTO `repair_personnel` VALUES (1, 'ltg', NULL, '15920307516');
INSERT INTO `repair_personnel` VALUES (2, 'zjs', NULL, '15920307512');
INSERT INTO `repair_personnel` VALUES (3, 'cxz', NULL, '15920307513');
INSERT INTO `repair_personnel` VALUES (4, 'xhh', NULL, '15920307514');
INSERT INTO `repair_personnel` VALUES (5, 'sentinel', NULL, '15920307511');

-- ----------------------------
-- Table structure for repairtype
-- ----------------------------
DROP TABLE IF EXISTS `repairtype`;
CREATE TABLE `repairtype`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remarks` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of repairtype
-- ----------------------------
INSERT INTO `repairtype` VALUES (1, '公共部位维修', NULL, 1);
INSERT INTO `repairtype` VALUES (2, '共用设施设备 ', NULL, 1);
INSERT INTO `repairtype` VALUES (3, '电梯', NULL, 1);
INSERT INTO `repairtype` VALUES (4, '自用部位维修', NULL, 1);
INSERT INTO `repairtype` VALUES (5, '门窗维修', NULL, 1);
INSERT INTO `repairtype` VALUES (6, '电话维修', NULL, 1);
INSERT INTO `repairtype` VALUES (7, '电路维修', NULL, 1);
INSERT INTO `repairtype` VALUES (8, '其他', NULL, 1);

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` int(0) NULL DEFAULT NULL,
  `remarks` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES (1, 'admin', '1', 1, NULL);
INSERT INTO `userinfo` VALUES (5, 'xhh', '123456', 0, '无');
INSERT INTO `userinfo` VALUES (6, 'ltg', '123456', 0, '无');
INSERT INTO `userinfo` VALUES (7, 'zjs', '123456', 0, '无');
INSERT INTO `userinfo` VALUES (10, 'cxs', '123456', 0, '无');
INSERT INTO `userinfo` VALUES (11, 'xxy', '123456', 0, '无');

SET FOREIGN_KEY_CHECKS = 1;

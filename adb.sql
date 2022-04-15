/*
 Navicat Premium Data Transfer

 Source Server         : 本地mysql
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : adb

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 15/04/2022 19:26:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_activity
-- ----------------------------
DROP TABLE IF EXISTS `t_activity`;
CREATE TABLE `t_activity`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '活动名',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '举办人',
  `starttime` timestamp(0) NULL DEFAULT NULL COMMENT '开始时间',
  `endtime` timestamp(0) NULL DEFAULT NULL COMMENT '结束时间',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `number` int(0) NULL DEFAULT NULL COMMENT '人数总量',
  `surplus` int(0) NULL DEFAULT NULL COMMENT '剩余活动人数',
  `version` int(0) NULL DEFAULT 1 COMMENT '乐观锁',
  `state` tinyint(1) NULL DEFAULT 1 COMMENT '状态 0关闭 1开启',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_activity
-- ----------------------------
INSERT INTO `t_activity` VALUES (2, '\"可复制的领导力”线上讲座（院级）', '张三', '2022-03-24 13:51:00', '2022-04-13 13:52:00', '活动目的\r\n1.提高同学们的领导意识；\r\n2.通过讲座丰富同学们的课余生活；\r\n3.推进学院的发展\r\n五、活动内容\r\n1、同学们通过讲座来拓宽自己的知识面；\r\n2、通过讲座来获得启示和感悟；', 100, 100, 1, 1, '/files/1649669764120');
INSERT INTO `t_activity` VALUES (3, '活动2', '关羽', '2022-04-11 14:27:00', '2022-04-11 14:27:00', '活动描述', 150, 150, 11, 1, '/files/1649669724077');
INSERT INTO `t_activity` VALUES (4, '活动名2', '关羽', '2022-04-14 00:00:00', '2022-04-11 14:00:00', '内容5', 55, 55, 1, 1, '/files/1649669666126');
INSERT INTO `t_activity` VALUES (5, '\"可复制的领导力”线上讲座（院级）', '张三', '2022-03-24 13:51:00', '2022-04-13 13:52:00', NULL, 100, 100, 1, 1, '/files/1649669764120');
INSERT INTO `t_activity` VALUES (6, '活动2', '关羽', '2022-04-11 14:27:00', '2022-04-11 14:27:00', NULL, 150, 150, 1, 1, '/files/1649669724077');
INSERT INTO `t_activity` VALUES (7, '活动名2', '关羽', '2022-04-14 00:00:00', '2022-04-11 14:00:00', NULL, 55, 55, 1, 1, '/files/1649669666126');
INSERT INTO `t_activity` VALUES (8, '\"可复制的领导力”线上讲座（院级）', '张三', '2022-03-24 13:51:00', '2022-04-13 13:52:00', NULL, 100, 100, 3, 1, '/files/1649669764120');
INSERT INTO `t_activity` VALUES (9, '活动2', '关羽', '2022-04-11 14:27:00', '2022-04-11 14:27:00', NULL, 150, 150, 1, 0, '/files/1649669724077');
INSERT INTO `t_activity` VALUES (10, '活动名2', '关羽', '2022-04-14 00:00:00', '2022-04-11 14:00:00', NULL, 55, 55, 1, 0, '/files/1649669666126');
INSERT INTO `t_activity` VALUES (11, '\"可复制的领导力”线上讲座（院级）', '张三', '2022-03-24 13:51:00', '2022-04-13 13:52:00', NULL, 100, 100, 1, 1, '/files/1649669764120');
INSERT INTO `t_activity` VALUES (12, '活动2', '关羽', '2022-04-11 14:27:00', '2022-04-11 14:27:00', '456', 150, 150, 1, 1, '/files/1649669724077');
INSERT INTO `t_activity` VALUES (13, '活动名2', '关羽', '2022-04-14 00:00:00', '2022-04-11 14:00:00', '123', 55, 55, 7, 1, '/files/1649669666126');
INSERT INTO `t_activity` VALUES (14, '\"可复制的领导力”线上讲座（院级）', '张三', '2022-03-24 13:51:00', '2022-04-13 13:52:00', '活动目的\r\n1.提高同学们的领导意识；\r\n2.通过讲座丰富同学们的课余生活；\r\n3.推进学院的发展\r\n五、活动内容\r\n1、同学们通过讲座来拓宽自己的知识面；\r\n2、通过讲座来获得启示和感悟；', 100, 99, 1, 1, '/files/1649669764120');

-- ----------------------------
-- Table structure for t_log
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '操作内容',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '操作时间',
  `user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '操作人',
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'ip',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 266 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_log
-- ----------------------------
INSERT INTO `t_log` VALUES (80, '用户 admin 登录系统', '2021-05-25 16:42:07', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (81, '更新用户：admin ', '2021-05-25 16:42:19', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (82, '用户 admin 退出系统', '2021-05-25 16:42:29', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (83, '用户 admin 登录系统', '2021-05-25 16:42:31', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (84, '更新用户：jerry ', '2021-05-25 16:49:14', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (85, '用户 admin 登录系统', '2021-05-25 16:49:30', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (86, '用户 admin 登录系统', '2021-05-25 16:50:07', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (87, '更新用户：jerry ', '2021-05-25 16:50:21', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (88, '用户 admin 登录系统', '2021-05-26 16:35:40', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (89, '更新用户：jerry ', '2021-05-26 16:36:16', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (90, '更新用户：jack ', '2021-05-26 16:36:18', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (91, '删除用户 tom ', '2021-05-26 16:36:29', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (92, '删除用户 hello ', '2021-05-26 16:36:30', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (93, '删除用户 jack ', '2021-05-26 16:36:31', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (94, '删除用户 jerry ', '2021-05-26 16:36:32', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (95, '用户 admin 登录系统', '2021-05-31 12:18:16', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (96, '更新用户：admin ', '2021-05-31 12:19:48', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (97, '更新用户：admin ', '2021-05-31 12:19:57', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (98, '用户 admin 登录系统', '2021-06-01 16:46:09', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (99, '新增用户：zhang ', '2021-06-01 16:46:46', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (100, '新增用户：li ', '2021-06-01 16:47:12', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (101, '更新用户：admin ', '2021-06-01 16:47:19', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (102, '用户 admin 退出系统', '2021-06-01 16:47:22', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (103, '用户 admin 登录系统', '2021-06-01 16:47:24', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (104, '更新用户：li ', '2021-06-01 16:47:34', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (105, '更新用户：zhang ', '2021-06-01 16:47:35', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (106, '用户 admin 登录系统', '2021-06-07 12:32:17', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (107, '用户 admin 登录系统', '2021-06-07 14:16:00', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (108, '用户 admin 登录系统', '2021-06-07 14:34:26', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (109, '更新用户：li ', '2021-06-07 14:35:21', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (110, '更新用户：li ', '2021-06-07 14:35:23', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (111, '更新角色：超级管理员', '2021-06-07 14:35:41', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (112, '更新角色：超级管理员', '2021-06-07 14:35:45', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (113, '更新权限菜单：用户管理', '2021-06-07 14:37:58', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (114, '更新权限菜单：用户管理', '2021-06-07 14:38:08', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (115, '更新权限菜单：用户管理', '2021-06-07 14:38:13', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (116, '更新权限菜单：用户管理', '2021-06-07 14:38:21', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (117, '用户 admin 登录系统', '2021-06-07 14:57:29', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (118, '更新角色：超级管理员', '2021-06-07 14:57:43', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (119, '用户 admin 登录系统', '2021-06-09 23:19:38', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (120, '删除权限菜单：学生管理', '2021-06-09 23:19:50', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (121, '用户 admin 登录系统', '2021-06-12 10:18:54', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (122, '更新角色：超级管理员', '2021-06-12 10:30:15', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (123, '用户 admin 登录系统', '2021-06-12 13:55:01', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (124, '用户 admin 登录系统', '2021-06-27 10:44:12', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (125, '更新角色：超级管理员', '2021-06-27 10:44:26', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (126, '用户 admin 登录系统', '2021-06-27 11:15:11', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (127, '更新角色：超级管理员', '2021-06-27 11:15:17', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (128, '用户 admin 登录系统', '2021-06-27 11:18:36', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (129, '用户 admin 登录系统', '2021-06-27 11:41:59', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (130, '更新角色：超级管理员', '2021-06-27 11:42:04', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (131, '用户 admin 登录系统', '2021-06-27 11:49:27', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (132, '用户 admin 登录系统', '2022-04-09 14:21:31', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (133, '更新用户：li ', '2022-04-09 14:21:54', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (134, '更新用户：li ', '2022-04-09 14:21:55', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (135, '用户 admin 退出系统', '2022-04-09 14:23:38', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (136, '用户 li 登录系统', '2022-04-09 14:24:09', 'li', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (137, '用户 li 退出系统', '2022-04-09 14:24:48', 'li', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (138, '用户 zhang 登录系统', '2022-04-09 14:25:03', 'zhang', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (139, '用户 zhang 退出系统', '2022-04-09 14:26:30', 'zhang', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (140, '用户 admin 登录系统', '2022-04-09 14:26:37', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (141, '更新角色：超级管理员', '2022-04-09 14:28:31', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (142, '更新角色：普通用户', '2022-04-09 14:40:40', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (143, '用户 admin 登录系统', '2022-04-09 15:05:37', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (144, '用户 admin 退出系统', '2022-04-09 15:08:28', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (145, '用户 admin 登录系统', '2022-04-09 15:08:30', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (146, '用户 admin 登录系统', '2022-04-09 15:23:36', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (147, '用户 admin 登录系统', '2022-04-09 15:34:31', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (148, '用户 admin 登录系统', '2022-04-09 15:40:11', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (149, '更新角色：超级管理员', '2022-04-09 15:46:48', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (150, '更新角色：超级管理员', '2022-04-09 15:47:13', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (151, '删除权限菜单：图书管理', '2022-04-09 15:47:18', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (152, '用户 admin 登录系统', '2022-04-09 15:50:17', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (153, '用户 admin 登录系统', '2022-04-09 16:03:46', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (154, '删除权限菜单：活动管理', '2022-04-09 16:04:09', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (155, '用户 admin 登录系统', '2022-04-09 16:07:48', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (156, '更新角色：超级管理员', '2022-04-09 16:07:57', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (157, '用户 admin 登录系统', '2022-04-09 16:09:11', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (158, '用户 admin 登录系统', '2022-04-09 16:34:34', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (159, '更新角色：超级管理员', '2022-04-09 16:34:47', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (160, '用户 admin 登录系统', '2022-04-09 16:44:45', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (161, '删除权限菜单：活动管理', '2022-04-09 16:45:16', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (162, '更新角色：普通用户', '2022-04-09 16:45:35', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (163, '更新角色：普通用户', '2022-04-09 16:45:40', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (164, '更新用户：li ', '2022-04-09 16:46:21', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (165, '更新用户：zhang ', '2022-04-09 16:46:30', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (166, '用户 admin 登录系统', '2022-04-11 13:35:06', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (167, '更新角色：超级管理员', '2022-04-11 13:35:19', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (168, '用户 admin 登录系统', '2022-04-11 13:45:30', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (169, '用户 admin 登录系统', '2022-04-11 13:58:18', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (170, '更新角色：超级管理员', '2022-04-11 13:58:28', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (171, '用户 admin 登录系统', '2022-04-11 14:29:05', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (172, '用户 admin 登录系统', '2022-04-11 14:40:24', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (173, '更新角色：超级管理员', '2022-04-11 14:53:13', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (174, '更新角色：超级管理员', '2022-04-11 14:53:15', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (175, '用户 admin 登录系统', '2022-04-11 14:53:30', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (176, '用户 admin 登录系统', '2022-04-11 15:12:54', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (177, '更新用户：admin ', '2022-04-11 15:17:23', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (178, '用户 admin 登录系统', '2022-04-11 15:20:38', 'admin', '127.0.0.1');
INSERT INTO `t_log` VALUES (179, '用户 admin 登录系统', '2022-04-11 15:23:13', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (180, '用户 admin 登录系统', '2022-04-11 15:54:18', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (181, '用户 admin 登录系统', '2022-04-11 15:59:45', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (182, '更新角色：超级管理员', '2022-04-11 15:59:57', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (183, '用户 admin 登录系统', '2022-04-11 16:45:16', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (184, '用户 admin 登录系统', '2022-04-11 17:34:14', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (185, '用户 admin 登录系统', '2022-04-11 21:50:48', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (186, '更新角色：超级管理员', '2022-04-11 21:50:56', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (187, '用户 admin 登录系统', '2022-04-12 18:10:07', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (188, '用户 admin 登录系统', '2022-04-12 21:47:47', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (189, '用户 admin 登录系统', '2022-04-13 13:17:07', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (190, '用户 admin 登录系统', '2022-04-13 14:21:30', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (191, '用户 admin 登录系统', '2022-04-13 14:52:19', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (192, '用户 admin 登录系统', '2022-04-13 16:40:44', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (208, '用户 admin 登录系统', '2022-04-13 21:10:52', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (209, '用户 admin 登录系统', '2022-04-13 21:12:33', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (210, '用户 admin 登录系统', '2022-04-13 22:33:59', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (211, '用户 zhang 登录系统', '2022-04-13 22:34:06', 'zhang', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (212, '用户 zhang 登录系统', '2022-04-13 22:43:14', 'zhang', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (213, '用户 zhang 登录系统', '2022-04-13 22:46:42', 'zhang', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (214, '用户 zhang 登录系统', '2022-04-14 11:36:28', 'zhang', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (215, '用户 zhang 登录系统', '2022-04-14 13:56:39', 'zhang', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (216, '用户 admin 登录系统', '2022-04-14 13:57:00', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (217, '用户 admin 登录系统', '2022-04-14 14:13:46', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (218, '用户 admin 退出系统', '2022-04-14 14:29:31', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (219, '用户 admin 登录系统', '2022-04-14 14:29:39', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (220, '更新角色：超级管理员', '2022-04-14 14:30:44', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (221, '用户 admin 登录系统', '2022-04-14 14:35:44', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (222, '更新角色：超级管理员', '2022-04-14 14:38:37', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (223, '更新角色：超级管理员', '2022-04-14 14:38:49', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (224, '更新角色：超级管理员', '2022-04-14 14:38:52', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (225, '用户 admin 登录系统', '2022-04-14 14:39:44', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (226, '用户 admin 登录系统', '2022-04-14 14:41:08', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (227, '用户 admin 登录系统', '2022-04-14 14:57:24', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (228, '用户 admin 登录系统', '2022-04-14 14:57:44', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (229, '用户 admin 登录系统', '2022-04-14 15:01:18', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (230, '用户 admin 登录系统', '2022-04-14 15:19:15', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (231, '用户 admin 登录系统', '2022-04-14 15:22:28', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (232, '用户 admin 登录系统', '2022-04-14 15:23:47', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (233, '用户 admin 登录系统', '2022-04-14 15:25:45', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (234, '用户 admin 退出系统', '2022-04-14 15:26:20', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (235, '用户 zhang 登录系统', '2022-04-14 15:26:22', 'zhang', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (236, '用户 zhang 登录系统', '2022-04-14 15:44:11', 'zhang', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (237, '用户 zhang 退出系统', '2022-04-14 15:44:23', 'zhang', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (238, '用户 admin 登录系统', '2022-04-14 15:44:25', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (239, '用户 admin 登录系统', '2022-04-14 17:55:43', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (240, '用户 admin 登录系统', '2022-04-14 18:15:29', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (241, '用户 admin 登录系统', '2022-04-14 18:50:42', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (242, '用户 admin 登录系统', '2022-04-14 19:05:35', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (243, '用户 admin 登录系统', '2022-04-14 19:48:28', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (244, '用户 admin 登录系统', '2022-04-14 21:10:06', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (245, '用户 admin 登录系统', '2022-04-14 21:33:51', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (246, '用户 admin 登录系统', '2022-04-14 21:36:02', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (247, '用户 admin 登录系统', '2022-04-14 21:55:08', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (248, '用户 admin 登录系统', '2022-04-15 13:47:42', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (249, '用户 admin 登录系统', '2022-04-15 13:54:35', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (250, '用户 admin 登录系统', '2022-04-15 14:57:40', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (251, '用户 admin 退出系统', '2022-04-15 15:09:24', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (252, '用户 li 登录系统', '2022-04-15 15:09:37', 'li', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (253, '用户 admin 登录系统', '2022-04-15 15:17:18', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (254, '用户 admin 退出系统', '2022-04-15 15:25:26', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (255, '用户 li 登录系统', '2022-04-15 15:25:28', 'li', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (256, '更新用户：li ', '2022-04-15 15:26:03', 'li', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (257, '用户 admin 登录系统', '2022-04-15 16:21:42', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (258, '用户 li 登录系统', '2022-04-15 16:27:44', 'li', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (259, '用户 admin 登录系统', '2022-04-15 16:29:43', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (260, '用户 admin 登录系统', '2022-04-15 16:32:33', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (261, '用户 admin 退出系统', '2022-04-15 16:37:41', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (262, '用户 admin 登录系统', '2022-04-15 16:37:42', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (263, '用户 admin 登录系统', '2022-04-15 16:44:17', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (264, '用户 admin 登录系统', '2022-04-15 18:54:10', 'admin', '0:0:0:0:0:0:0:1');
INSERT INTO `t_log` VALUES (265, '用户 admin 登录系统', '2022-04-15 19:20:48', 'admin', '0:0:0:0:0:0:0:1');

-- ----------------------------
-- Table structure for t_message
-- ----------------------------
DROP TABLE IF EXISTS `t_message`;
CREATE TABLE `t_message`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '内容',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '评论人',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评论时间',
  `parent_id` bigint(0) NULL DEFAULT NULL COMMENT '父ID',
  `foreign_id` bigint(0) NULL DEFAULT 0 COMMENT '关联id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '留言表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_message
-- ----------------------------
INSERT INTO `t_message` VALUES (20, '11111111111111111111111111', 'admin', '2022-04-09 14:41:00', NULL, 0);
INSERT INTO `t_message` VALUES (21, '222222222222', 'admin', '2022-04-09 14:41:05', 20, 0);
INSERT INTO `t_message` VALUES (22, 'hello', 'admin', '2022-04-11 16:27:18', NULL, 0);
INSERT INTO `t_message` VALUES (23, '你好111111111111111111111111111111111111111111111111111111', 'admin', '2022-04-15 15:24:34', NULL, 0);

-- ----------------------------
-- Table structure for t_notice
-- ----------------------------
DROP TABLE IF EXISTS `t_notice`;
CREATE TABLE `t_notice`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '内容',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_notice
-- ----------------------------
INSERT INTO `t_notice` VALUES (4, '标题1', '标题1内容标题1内容标题1内容标题1内容标题1内容', '2021-05-17 15:29:29');
INSERT INTO `t_notice` VALUES (5, '标题2', '标题2内容标题2内容标题2内容标题2内容标题2内容标题2内容标题2内容', '2021-05-17 15:30:08');
INSERT INTO `t_notice` VALUES (6, '公告标题', '公告标题的内容公告标题的内容公告标题的内容公告标题的内容', '2021-05-17 15:30:42');
INSERT INTO `t_notice` VALUES (7, '3', '4', '2022-04-14 18:54:59');

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单路径',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 's-data' COMMENT '图标',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES (1, '用户管理', '用户管理', 'user', 'user-solid');
INSERT INTO `t_permission` VALUES (2, '角色管理', '角色管理', 'role', 's-cooperation');
INSERT INTO `t_permission` VALUES (3, '权限管理', '权限管理', 'permission', 'menu');
INSERT INTO `t_permission` VALUES (19, '公告管理', '公告管理', 'notice', 'data-board');
INSERT INTO `t_permission` VALUES (24, '日志管理', '日志管理', 'log', 'notebook-2');
INSERT INTO `t_permission` VALUES (25, '在线留言', '在线留言', 'message', 'message');
INSERT INTO `t_permission` VALUES (38, '活动管理', '活动管理', 'activity', 's-data');
INSERT INTO `t_permission` VALUES (39, '活动报名管理', '活动报名管理', 'registration', 's-data');

-- ----------------------------
-- Table structure for t_registration
-- ----------------------------
DROP TABLE IF EXISTS `t_registration`;
CREATE TABLE `t_registration`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '流水号',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户id',
  `activity_id` bigint(0) NULL DEFAULT NULL COMMENT '活动id',
  `time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请时间(用户申请参加活动的时间)',
  `state` tinyint(1) NULL DEFAULT 0 COMMENT '状态0不通过 1通过',
  `context` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '响应内容',
  `version` int(0) NULL DEFAULT 1 COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_registration
-- ----------------------------
INSERT INTO `t_registration` VALUES (2, 2, 11, '2022-04-14 21:11:55', 1, NULL, 1);
INSERT INTO `t_registration` VALUES (4, 1, 11, '2022-04-13 21:11:55', 0, NULL, 1);
INSERT INTO `t_registration` VALUES (5, 2, 6, '2022-04-13 22:47:07', 1, NULL, 1);
INSERT INTO `t_registration` VALUES (6, 2, 7, '2022-04-13 22:47:35', 1, NULL, 1);
INSERT INTO `t_registration` VALUES (7, 2, 4, '2022-04-13 22:47:57', 1, NULL, 1);
INSERT INTO `t_registration` VALUES (8, 2, 3, '2022-04-13 22:47:57', 1, NULL, 1);
INSERT INTO `t_registration` VALUES (9, 2, 2, '2022-04-13 22:47:58', 1, NULL, 1);
INSERT INTO `t_registration` VALUES (10, 3, 12, '2022-04-15 15:09:59', 0, NULL, 1);
INSERT INTO `t_registration` VALUES (11, 3, 11, '2022-04-15 15:10:01', 0, NULL, 1);
INSERT INTO `t_registration` VALUES (12, 3, 8, '2022-04-15 15:10:19', 0, NULL, 1);
INSERT INTO `t_registration` VALUES (13, 3, 6, '2022-04-15 15:10:21', 0, NULL, 1);
INSERT INTO `t_registration` VALUES (14, 3, 5, '2022-04-15 15:10:22', 0, NULL, 1);
INSERT INTO `t_registration` VALUES (15, 3, 7, '2022-04-15 15:10:24', 0, NULL, 1);
INSERT INTO `t_registration` VALUES (16, 3, 13, '2022-04-15 15:10:27', 0, NULL, 1);
INSERT INTO `t_registration` VALUES (17, 3, 2, '2022-04-15 16:27:57', 0, NULL, 1);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `permission` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限列表',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, '超级管理员', '所有权限', '[1,2,3,27,30,31,24,25,34,36,37,38,39,19]');
INSERT INTO `t_role` VALUES (2, '普通用户', '部分权限', '[]');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '昵称',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '手机号',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '地址',
  `age` int(0) NULL DEFAULT NULL COMMENT '年龄',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'admin', 'admin', '管理员', '111124444', '13978786565', '1622537239707', '[1]', '成都', 20);
INSERT INTO `t_user` VALUES (2, 'zhang', '123456', '张三', 'zhang@qq.com', '13345457878', NULL, '[2]', '北京', 24);
INSERT INTO `t_user` VALUES (3, 'li', '123456', '李四', 'li@qq.com', '13989898898', '1650007563001', '[2]', '南京', 22);

SET FOREIGN_KEY_CHECKS = 1;

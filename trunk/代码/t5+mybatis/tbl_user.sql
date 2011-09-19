/*
MySQL Data Transfer
Source Host: localhost
Source Database: yiidemo
Target Host: localhost
Target Database: yiidemo
Date: 2011-9-19 1:21:42
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for tbl_user
-- ----------------------------
CREATE TABLE `tbl_user` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL,
  `email` varchar(128) NOT NULL,
  `birthday` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `tbl_user` VALUES ('1', 'test1', 'pass1', 'test1@example.com', '2011-09-01 21:31:42');
INSERT INTO `tbl_user` VALUES ('2', 'test2', 'pass2', 'test2@example.com', '2011-09-04 21:31:47');
INSERT INTO `tbl_user` VALUES ('3', 'test3', 'pass3', 'test3@example.com', '2011-09-05 21:31:51');
INSERT INTO `tbl_user` VALUES ('4', 'test4', 'pass4', 'test4@example.com', '2011-09-06 21:31:56');
INSERT INTO `tbl_user` VALUES ('5', 'test5', 'pass5', 'test5@example.com', '2011-09-07 21:32:03');
INSERT INTO `tbl_user` VALUES ('6', 'test6', 'pass6', 'test6@example.com', '2011-09-08 21:32:07');
INSERT INTO `tbl_user` VALUES ('7', 'test7', 'pass7', 'test7@example.com', '2011-09-11 21:32:11');
INSERT INTO `tbl_user` VALUES ('8', 'test8', 'pass8', 'test8@example.com', '2011-09-12 21:32:15');
INSERT INTO `tbl_user` VALUES ('9', 'test9', 'pass9', 'test9@example.com', '2011-09-13 21:32:20');
INSERT INTO `tbl_user` VALUES ('10', 'test10', 'pass10', 'test10@example.com', '2011-09-14 21:32:23');
INSERT INTO `tbl_user` VALUES ('11', 'test11', 'pass11', 'test11@example.com', '2011-09-15 21:32:27');
INSERT INTO `tbl_user` VALUES ('12', 'test12', 'pass12', 'test12@example.com', '2011-09-18 21:32:31');
INSERT INTO `tbl_user` VALUES ('13', 'test13', 'pass13', 'test13@example.com', '2011-09-19 21:32:33');
INSERT INTO `tbl_user` VALUES ('14', 'test14', 'pass14', 'test14@example.com', '2011-09-20 21:32:36');
INSERT INTO `tbl_user` VALUES ('15', 'test15', 'pass15', 'test15@example.com', '2011-09-21 21:32:40');
INSERT INTO `tbl_user` VALUES ('16', 'test16', 'pass16', 'test16@example.com', '2011-09-22 21:32:43');
INSERT INTO `tbl_user` VALUES ('17', 'test17', 'pass17', 'test17@example.com', '2011-09-25 21:32:46');
INSERT INTO `tbl_user` VALUES ('18', 'test18', 'pass18', 'test18example.com', '2011-09-26 21:32:51');
INSERT INTO `tbl_user` VALUES ('19', 'test19', 'pass19', 'test19example.com', '2011-09-27 21:32:55');
INSERT INTO `tbl_user` VALUES ('20', 'test20', 'pass20', 'test20@example.com', '2011-09-28 21:32:59');
INSERT INTO `tbl_user` VALUES ('21', 'test21', 'pass21', 'test21@example.com', '2011-09-29 21:33:13');

/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.5.13 : Database - j2ee
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`j2ee` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `j2ee`;

/*Table structure for table `classify` */

DROP TABLE IF EXISTS `classify`;

CREATE TABLE `classify` (
  `classifyID` int(11) NOT NULL AUTO_INCREMENT,
  `classifyName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`classifyID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `classify` */

insert  into `classify`(`classifyID`,`classifyName`) values (1,'计算机'),(2,'化工'),(3,'历史'),(4,'人文'),(6,'天文'),(8,'建筑'),(9,'交通'),(10,'杂类');

/*Table structure for table `file` */

DROP TABLE IF EXISTS `file`;

CREATE TABLE `file` (
  `fileID` int(11) NOT NULL AUTO_INCREMENT,
  `fileName` varchar(80) DEFAULT NULL,
  `fileName_random` varchar(80) DEFAULT NULL,
  `author` varchar(50) DEFAULT NULL COMMENT '作者',
  `date` datetime DEFAULT NULL COMMENT '上传日期',
  `looknum` int(11) DEFAULT NULL COMMENT '查看次数',
  `check_1` int(11) DEFAULT NULL COMMENT '0/1',
  `classifyID` int(11) DEFAULT NULL COMMENT '分类ID',
  `location` varchar(255) DEFAULT NULL COMMENT '绝对路径',
  `type` varchar(20) DEFAULT NULL COMMENT '类型',
  PRIMARY KEY (`fileID`),
  KEY `file_ibfk_1` (`classifyID`),
  CONSTRAINT `file_ibfk_1` FOREIGN KEY (`classifyID`) REFERENCES `classify` (`classifyID`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

/*Data for the table `file` */

insert  into `file`(`fileID`,`fileName`,`fileName_random`,`author`,`date`,`looknum`,`check_1`,`classifyID`,`location`,`type`) values (16,'中国与中东地区物流线路规划.pdf','中国与中东地区物流线路规划202005062103.pdf','李杰','2020-05-06 00:00:00',0,1,9,'E:\\j2ee_finalProject\\file\\upload\\20200506\\中国与中东地区物流线路规划202005062103.pdf','pdf'),(17,'中国与中东地区物流线路规划.pdf','中国与中东地区物流线路规划202005062103.pdf','李杰','2020-05-06 00:00:00',0,1,9,'E:\\j2ee_finalProject\\file\\upload\\20200506\\中国与中东地区物流线路规划202005062103.pdf','pdf'),(18,'计算机网络习题讲解笔记.pdf','计算机网络习题讲解笔记202005082130.pdf','blackjie','2020-05-08 00:00:00',0,1,1,'E:\\j2ee_finalProject\\file\\upload\\20200508\\计算机网络习题讲解笔记202005082130.pdf','pdf'),(21,'成绩2.xlsx','成绩2202005081320.xlsx','blackjie','2020-05-08 00:00:00',0,1,10,'E:\\j2ee_finalProject\\file\\upload\\20200508\\成绩2202005081320.xlsx','xlsx'),(22,'5软件工程培养方案0729-ZH_2_.docx','5软件工程培养方案0729-ZH_2_202005082103.docx','blackjie','2020-05-08 00:00:00',0,1,10,'E:\\j2ee_finalProject\\file\\upload\\20200508\\5软件工程培养方案0729-ZH _2_202005082103.docx','docx'),(23,'软件1171李杰-团员自我评价表.doc','软件1171李杰-团员自我评价表202005082310.doc','blackjie','2020-05-08 00:00:00',0,0,10,'E:\\j2ee_finalProject\\file\\upload\\20200508\\软件1171李杰-团员自我评价表202005082310.doc','doc'),(24,'软件1171李杰1172502523UML实验报告.docx','软件1171李杰1172502523UML实验报告202005092103.docx','李杰_管理员','2020-05-09 00:00:00',0,0,1,'E:\\j2ee_finalProject\\file\\upload\\20200509\\软件1171李杰1172502523UML实验报告202005092103.docx','docx'),(25,'操作手册维修v3.0.docx','操作手册维修v3.0202005092013.docx','李杰_管理员','2020-05-09 00:00:00',0,0,10,'E:\\j2ee_finalProject\\file\\upload\\20200509\\操作手册维修v3.0202005092013.docx','docx'),(26,'第7章  JSP标准标记库.ppt','第7章  JSP标准标记库202005093201.ppt','TEST','2020-05-09 00:00:00',0,1,1,'E:\\j2ee_finalProject\\file\\upload\\20200509\\第7章  JSP标准标记库202005093201.ppt','ppt'),(27,'多元微分部分答案.pdf','多元微分部分答案202005091302.pdf','TEST','2020-05-09 00:00:00',0,0,10,'E:\\j2ee_finalProject\\file\\upload\\20200509\\多元微分部分答案202005091302.pdf','pdf'),(28,'班新学号.xls','班新学号202005100312.xls','李杰_管理员','2020-05-10 11:38:26',1,0,10,'E:\\j2ee_finalProject\\file\\upload\\20200510\\班新学号202005100312.xls','xls');

/*Table structure for table `file_user` */

DROP TABLE IF EXISTS `file_user`;

CREATE TABLE `file_user` (
  `fileID` int(11) NOT NULL,
  `fileName` varchar(50) DEFAULT NULL,
  `userName` varchar(50) DEFAULT NULL,
  `date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `file_user` */

insert  into `file_user`(`fileID`,`fileName`,`userName`,`date`) values (28,'班新学号.xls','李杰_管理员','2020-05-10 11:38:35');

/*Table structure for table `manage` */

DROP TABLE IF EXISTS `manage`;

CREATE TABLE `manage` (
  `manageID` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员ID',
  `manageName` varchar(10) DEFAULT NULL COMMENT '管理员名称',
  `password` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`manageID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `manage` */

insert  into `manage`(`manageID`,`manageName`,`password`) values (1,'李杰_管理员','123456');

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `roleID` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`roleID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`roleID`,`roleName`) values (1,'党员'),(2,'辅导员'),(3,'班主任'),(6,'混子');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(10) DEFAULT NULL,
  `password` varchar(16) DEFAULT NULL,
  `name` varchar(11) DEFAULT NULL,
  `department` varchar(10) DEFAULT NULL COMMENT '部门',
  `sex` varchar(5) DEFAULT NULL COMMENT '性别',
  `birthdate` date DEFAULT NULL,
  `education` varchar(10) DEFAULT NULL COMMENT '学历',
  `degree` varchar(10) DEFAULT NULL COMMENT '学位',
  `image` varchar(255) DEFAULT NULL COMMENT '头像地址',
  `job` varchar(10) DEFAULT NULL COMMENT '职称',
  `email` varchar(30) DEFAULT NULL,
  `QQ` varchar(11) DEFAULT NULL,
  `Introduction` varchar(255) DEFAULT NULL COMMENT '简介',
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`userID`,`username`,`password`,`name`,`department`,`sex`,`birthdate`,`education`,`degree`,`image`,`job`,`email`,`QQ`,`Introduction`) values (1,'blackjie','123','李杰','计算机','男','2010-05-18','大学','学士','heartImage/qq_pic_merged_1568653403706.jpg','共青团员','2416856684@qq.com','241685668','本人阳光大方，为人和善，思维能力强。'),(2,'TEST','123','周豪','计算机','女','2020-04-30','大学','学士','heartImage/v2-bad47108bf89e807cf1a6123e3155ca8_r.jpg','共青团员','232323@qq.com','432423432','可爱'),(3,'OK','123','阿郎','交通','男','2020-05-04','大学','学士','heartImage/QQ截图20191007212111.png','发展党员','4234244@qq.com','2342423','乐观开朗');

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `userID` int(11) NOT NULL,
  `roleID` int(11) NOT NULL,
  PRIMARY KEY (`roleID`,`userID`),
  KEY `userID` (`userID`),
  CONSTRAINT `roleID` FOREIGN KEY (`roleID`) REFERENCES `role` (`roleID`) ON DELETE CASCADE,
  CONSTRAINT `userID` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_role` */

insert  into `user_role`(`userID`,`roleID`) values (1,3),(1,6),(2,1),(2,6),(3,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

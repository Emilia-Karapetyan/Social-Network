/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 8.0.20 : Database - socialbase
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`socialbase` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `socialbase`;

/*Table structure for table `commentpost` */

DROP TABLE IF EXISTS `commentpost`;

CREATE TABLE `commentpost` (
  `id` int NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) DEFAULT NULL,
  `postId` int NOT NULL,
  `userId` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `postId` (`postId`),
  KEY `userId` (`userId`),
  CONSTRAINT `commentpost_ibfk_1` FOREIGN KEY (`postId`) REFERENCES `post` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `commentpost_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=181 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `commentpost` */

insert  into `commentpost`(`id`,`comment`,`postId`,`userId`) values 
(179,'s',28,2),
(180,'ggg',28,2);

/*Table structure for table `friend` */

DROP TABLE IF EXISTS `friend`;

CREATE TABLE `friend` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fromId` int NOT NULL,
  `toId` int NOT NULL,
  `bool` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fromId` (`fromId`),
  KEY `toId` (`toId`),
  CONSTRAINT `friend_ibfk_1` FOREIGN KEY (`fromId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `friend_ibfk_2` FOREIGN KEY (`toId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `friend` */

/*Table structure for table `like` */

DROP TABLE IF EXISTS `like`;

CREATE TABLE `like` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userId` int NOT NULL,
  `postId` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `like_ibfk_3` (`postId`)
) ENGINE=InnoDB AUTO_INCREMENT=191 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `like` */

insert  into `like`(`id`,`userId`,`postId`) values 
(98,2,13),
(99,2,15),
(100,1,19),
(127,1,20),
(128,4,26),
(131,2,26),
(133,1,27),
(153,2,31),
(158,4,25),
(181,2,27),
(185,2,20),
(187,2,28),
(188,2,32),
(189,2,33),
(190,2,34);

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fromId` int NOT NULL,
  `toId` int NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=224 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `message` */

insert  into `message`(`id`,`fromId`,`toId`,`message`,`date`,`time`) values 
(223,2,1,'hi','2020-07-31','12:45:03');

/*Table structure for table `post` */

DROP TABLE IF EXISTS `post`;

CREATE TABLE `post` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `picURL` varchar(255) DEFAULT NULL,
  `userId` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  CONSTRAINT `post_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `post` */

insert  into `post`(`id`,`description`,`picURL`,`userId`) values 
(28,'d','1593283188385_index1.jpg',4);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `age` int NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `picURL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user` */

insert  into `user`(`id`,`name`,`surname`,`age`,`email`,`password`,`picURL`) values 
(2,'Anna','Anyan',21,'an@mail.ru','3636',''),
(3,'Gevor','Darbinyan',19,'gev@mail.ru','111',''),
(4,'Ashot','Garslyan',25,'ashot@mail.ru','5555',''),
(5,'Sveta','Bdoyan',16,'sveta@mail.ru','5454',''),
(6,'Emili','Karapetyan',20,'emul@mail.ru','2222',''),
(7,'Artak','Nazaryan',25,'artak@mail.ru','8585',NULL),
(9,'Anahit','Simonyan',99,'anahit@mail.ru','222',''),
(10,'Admin','Adminyan',100000,'admin@mail.ru','222','');

/*Table structure for table `usersphoto` */

DROP TABLE IF EXISTS `usersphoto`;

CREATE TABLE `usersphoto` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userId` int NOT NULL,
  `picURL` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `usersphoto_ibfk_1` (`userId`),
  CONSTRAINT `usersphoto_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `usersphoto` */

insert  into `usersphoto`(`id`,`userId`,`picURL`) values 
(102,2,'1594311725041_d.jpg'),
(103,2,'1594312120238_d.jpg'),
(104,2,'1594312125330_d.jpg'),
(105,2,'1594312129809_d.jpg'),
(106,2,'1594312138455_d.jpg'),
(107,2,'1594312143364_d.jpg');

/* Procedure structure for procedure `addLike` */

/*!50003 DROP PROCEDURE IF EXISTS  `addLike` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `addLike`(a int(11) , s Int(11))
begin
Declare x int(11);
set x=(Select count(*) from `like` where userId=a and postId=s);
if x=0 then	
	insert into `like`(userId,postId) values (a,s);
else
	delete from `like` where userId = a and postId=s;
end if;
end */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

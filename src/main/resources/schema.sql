DROP TABLE IF EXISTS `part`;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `part` (
  `id` bigint(20) NOT NULL,
  `amount` int(11) DEFAULT NULL,
  `must` bit(1) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
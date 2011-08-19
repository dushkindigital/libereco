
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

# Dumping database structure for libereco
DROP DATABASE IF EXISTS `libereco`;
CREATE DATABASE IF NOT EXISTS `libereco` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `libereco`;


# Dumping structure for table libereco.liberecouser
DROP TABLE IF EXISTS `liberecouser`;
CREATE TABLE IF NOT EXISTS `liberecouser` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `userName` varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  `created` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `lastUpdated` timestamp NOT NULL default '0000-00-00 00:00:00',
  `role` int(10) unsigned default NULL,
  `status` int(10) unsigned default '0',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `userName` (`userName`),
  KEY `userName_2` (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


# Dumping structure for table libereco.marketplace
DROP TABLE IF EXISTS `marketplace`;
CREATE TABLE IF NOT EXISTS `marketplace` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `marketplaceName` varchar(64) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `marketplaceName` (`marketplaceName`),
  KEY `marketplaceName_2` (`marketplaceName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dumping data for table libereco.marketplace: ~2 rows (approximately)
/*!40000 ALTER TABLE `marketplace` DISABLE KEYS */;
REPLACE INTO `marketplace` (`id`, `marketplaceName`) VALUES
	(1, 'ebay'),
	(2, 'etsy');
/*!40000 ALTER TABLE `marketplace` ENABLE KEYS */;


# Dumping structure for table libereco.marketplaceauthorizations
DROP TABLE IF EXISTS `marketplaceauthorizations`;
CREATE TABLE IF NOT EXISTS `marketplaceauthorizations` (
  `marketplaceId` int(10) unsigned NOT NULL,
  `userId` int(10) unsigned NOT NULL,
  `token` text,
  `tokenSecret` varchar(255) default NULL,
  `expirationTime` timestamp NULL default NULL,
  KEY `marketplaceAuthorizations_FKIndex1` (`userId`),
  KEY `marketplaceAuthorizations_FKIndex2` (`marketplaceId`),
  CONSTRAINT `marketplaceauthorizations_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `liberecouser` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `marketplaceauthorizations_ibfk_2` FOREIGN KEY (`marketplaceId`) REFERENCES `marketplace` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


# Dumping structure for table libereco.pendingmarketplaceauthorizations
DROP TABLE IF EXISTS `pendingmarketplaceauthorizations`;
CREATE TABLE IF NOT EXISTS `pendingmarketplaceauthorizations` (
  `marketplaceId` int(10) unsigned NOT NULL,
  `userId` int(10) unsigned NOT NULL,
  `requestToken` varchar(128) default NULL,
  `requestTokenSecret` varchar(128) default NULL,
  KEY `marketplaceAuthorizations_FKIndex1` (`userId`),
  KEY `marketplaceAuthorizations_FKIndex2` (`marketplaceId`),
  CONSTRAINT `pendingmarketplaceauthorizations_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `liberecouser` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `pendingmarketplaceauthorizations_ibfk_2` FOREIGN KEY (`marketplaceId`) REFERENCES `marketplace` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40000 ALTER TABLE `pendingmarketplaceauthorizations` DISABLE KEYS */;
/*!40000 ALTER TABLE `pendingmarketplaceauthorizations` ENABLE KEYS */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

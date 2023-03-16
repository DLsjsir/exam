-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.5.53 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win32
-- HeidiSQL 版本:                  9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 exam 的数据库结构
CREATE DATABASE IF NOT EXISTS `exam` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `exam`;

-- 导出  表 exam.tbl_exam 结构
CREATE TABLE IF NOT EXISTS `tbl_exam` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `qid` int(11) NOT NULL,
  `ret` int(11) NOT NULL COMMENT '1为对0为错',
  PRIMARY KEY (`id`),
  KEY `FK_tbl_exam_tbl_user` (`uid`),
  KEY `FK_tbl_exam_tbl_question` (`qid`),
  CONSTRAINT `FK_tbl_exam_tbl_question` FOREIGN KEY (`qid`) REFERENCES `tbl_question` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_tbl_exam_tbl_user` FOREIGN KEY (`uid`) REFERENCES `tbl_user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8 COMMENT='卷子 关系表';

-- 正在导出表  exam.tbl_exam 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `tbl_exam` DISABLE KEYS */;
REPLACE INTO `tbl_exam` (`id`, `uid`, `qid`, `ret`) VALUES
	(23, 6, 3, 1),
	(24, 6, 18, 1),
	(25, 6, 20, 0),
	(26, 6, 21, 1),
	(27, 6, 22, 0),
	(28, 7, 3, 1),
	(29, 7, 19, 0),
	(30, 7, 20, 0),
	(31, 7, 21, 1),
	(32, 7, 23, 0),
	(33, 18, 3, 0),
	(34, 18, 18, 1),
	(35, 18, 20, 1),
	(36, 18, 21, 1),
	(37, 18, 22, 0),
	(38, 16, 18, 1),
	(39, 16, 20, 1),
	(40, 16, 21, 1),
	(41, 16, 22, 1),
	(42, 16, 23, 1),
	(43, 16, 31, 1),
	(44, 16, 33, 1),
	(45, 16, 34, 1),
	(46, 16, 35, 0),
	(47, 16, 38, 0),
	(48, 6, 30, 1),
	(49, 6, 31, 1),
	(50, 6, 32, 1),
	(51, 6, 34, 1),
	(52, 6, 37, 1);
/*!40000 ALTER TABLE `tbl_exam` ENABLE KEYS */;

-- 导出  表 exam.tbl_question 结构
CREATE TABLE IF NOT EXISTS `tbl_question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question` varchar(50) NOT NULL,
  `answer` varchar(50) NOT NULL,
  `uid` int(11) NOT NULL,
  `logo` varchar(255) DEFAULT NULL,
  `ctime` datetime NOT NULL,
  `quit` varchar(50) NOT NULL DEFAULT '正常',
  PRIMARY KEY (`id`),
  KEY `FK_tbl_question_tbl_user` (`uid`),
  CONSTRAINT `FK_tbl_question_tbl_user` FOREIGN KEY (`uid`) REFERENCES `tbl_user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- 正在导出表  exam.tbl_question 的数据：~27 rows (大约)
/*!40000 ALTER TABLE `tbl_question` DISABLE KEYS */;
REPLACE INTO `tbl_question` (`id`, `question`, `answer`, `uid`, `logo`, `ctime`, `quit`) VALUES
	(3, 'Java是为面向对象而生的', '对', 2, '', '2022-08-31 20:57:42', '正常'),
	(4, 'bs4是python爬虫库', '对', 3, '', '2022-08-31 20:58:12', '正常'),
	(10, 'Python集合元素不允许重复', '对', 3, '', '2022-09-01 00:12:26', '已弃用'),
	(11, 'Python集合元素可以是元组', '对', 3, '', '2022-09-01 00:14:04', '已弃用'),
	(12, 'Python中没有else if', '对', 3, '', '2022-09-01 21:13:59', '已弃用'),
	(13, 'split()实参可以写空字符串', '错', 3, '', '2022-09-01 21:14:06', '已弃用'),
	(17, 'xpath可用来解析html结构', '对', 3, '', '2022-09-01 21:14:31', '已弃用'),
	(18, '继承关系中可以重写', '对', 2, '', '2022-09-01 21:14:53', '正常'),
	(19, '继承关系中不可以重载', '错', 2, '', '2022-09-01 21:15:00', '正常'),
	(20, 'Servlet生命周期没有init()', '错', 2, '', '2022-09-01 21:15:52', '正常'),
	(21, 'Mysql是关系型数据库', '对', 2, '', '2022-09-01 21:15:59', '正常'),
	(22, 'Map键可以是int类型', '错', 2, '', '2022-09-01 21:16:04', '正常'),
	(23, 'Map值允许重复', '对', 2, '', '2022-09-01 21:16:10', '正常'),
	(24, 'SSM是SpringBoot框架基础', '对', 2, '', '2022-09-04 08:26:43', '已弃用'),
	(25, 'MyBatis用法中没有$符号', '错', 2, '', '2022-10-03 21:05:59', '已弃用'),
	(26, 'Struts2底层是基于Servlet', '错', 2, '', '2022-10-03 21:07:25', '已弃用'),
	(30, '超链接标签只能打开新页面', '错', 1, '', '2022-10-03 21:12:29', '正常'),
	(31, 'form表单可以执行POST请求', '对', 1, '/exam/logo/apic27858.jpg', '2022-10-03 21:12:37', '正常'),
	(32, 'js有浏览器兼容性问题', '对', 1, '', '2022-10-03 21:12:54', '正常'),
	(33, 'jQuery以后会越来越火', '错', 1, '', '2022-10-03 21:13:25', '正常'),
	(34, 'vuejs的制作者是中国人', '对', 1, '', '2022-10-03 21:14:01', '正常'),
	(35, 'css3动画性能比jQ动画更好', '对', 1, '', '2022-10-03 21:14:28', '正常'),
	(36, 'elementUI是基于react.js', '错', 1, '', '2022-10-03 21:15:11', '正常'),
	(37, 'Ajax请求类型只有GET和POST', '错', 1, '', '2022-10-03 21:15:47', '正常'),
	(38, '{{}}目前只在前端语法中有出现', '错', 1, '', '2022-10-03 21:16:29', '正常'),
	(39, '用JS定时器可以做JS游戏开发', '对', 1, '', '2022-10-03 21:17:42', '正常'),
	(40, 'C语言真有趣', '对', 19, '/exam/logo/apic27858.jpg', '2023-02-16 19:52:54', '正常');
/*!40000 ALTER TABLE `tbl_question` ENABLE KEYS */;

-- 导出  表 exam.tbl_user 结构
CREATE TABLE IF NOT EXISTS `tbl_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `status` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- 正在导出表  exam.tbl_user 的数据：~9 rows (大约)
/*!40000 ALTER TABLE `tbl_user` DISABLE KEYS */;
REPLACE INTO `tbl_user` (`id`, `username`, `password`, `name`, `status`) VALUES
	(1, 'html5', '123456', 'html5老师', '老师'),
	(2, 'java', '123456', 'java老师', '老师'),
	(3, 'python', '123456', 'python老师', '老师'),
	(6, '333', '123456', '张三', '学生'),
	(7, '444', '123456', '李四', '学生'),
	(15, '555', '123456', '王五', '学生'),
	(16, '888', '123456', '唐八', '学生'),
	(18, '777', '123456', '钱七', '学生'),
	(19, 'C', '123456', 'C老师', '老师');
/*!40000 ALTER TABLE `tbl_user` ENABLE KEYS */;

-- 导出  视图 exam.v_exam 结构
-- 创建临时表以解决视图依赖性错误
CREATE TABLE `v_exam` (
	`id` INT(11) NOT NULL,
	`uid` INT(11) NOT NULL,
	`qid` INT(11) NOT NULL,
	`ret` INT(11) NOT NULL COMMENT '1为对0为错',
	`username` VARCHAR(50) NULL COLLATE 'utf8_general_ci'
) ENGINE=MyISAM;

-- 导出  视图 exam.v_question 结构
-- 创建临时表以解决视图依赖性错误
CREATE TABLE `v_question` (
	`id` INT(11) NOT NULL,
	`question` VARCHAR(50) NOT NULL COLLATE 'utf8_general_ci',
	`answer` VARCHAR(50) NOT NULL COLLATE 'utf8_general_ci',
	`uid` INT(11) NOT NULL,
	`logo` VARCHAR(255) NULL COLLATE 'utf8_general_ci',
	`ctime` DATETIME NOT NULL,
	`quit` VARCHAR(50) NOT NULL COLLATE 'utf8_general_ci',
	`username` VARCHAR(50) NULL COLLATE 'utf8_general_ci'
) ENGINE=MyISAM;

-- 导出  视图 exam.v_exam 结构
-- 移除临时表并创建最终视图结构
DROP TABLE IF EXISTS `v_exam`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_exam` AS select `tbl_exam`.`id` AS `id`,`tbl_exam`.`uid` AS `uid`,`tbl_exam`.`qid` AS `qid`,`tbl_exam`.`ret` AS `ret`,`v_question`.`username` AS `username` from (`tbl_exam` left join `v_question` on((`tbl_exam`.`qid` = `v_question`.`id`)));

-- 导出  视图 exam.v_question 结构
-- 移除临时表并创建最终视图结构
DROP TABLE IF EXISTS `v_question`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_question` AS select `tbl_question`.`id` AS `id`,`tbl_question`.`question` AS `question`,`tbl_question`.`answer` AS `answer`,`tbl_question`.`uid` AS `uid`,`tbl_question`.`logo` AS `logo`,`tbl_question`.`ctime` AS `ctime`,`tbl_question`.`quit` AS `quit`,`tbl_user`.`username` AS `username` from (`tbl_question` left join `tbl_user` on((`tbl_question`.`uid` = `tbl_user`.`id`)));

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : geeklog

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-09-06 10:14:22
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS=0;

DROP SCHEMA IF EXISTS geeklog;
CREATE SCHEMA geeklog DEFAULT CHARACTER SET utf8;
USE geeklog;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `article_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `modified_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `user_id` int(11) DEFAULT NULL,
  `content` text,
  `category_id` int(11) DEFAULT NULL,
  `tags` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`article_id`),
  KEY `fk_article_user` (`user_id`),
  KEY `fk_article_category` (`category_id`),
  CONSTRAINT `fk_article_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_article_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('1', 'Java开发技巧', '2018-09-06 09:45:27', '2018-09-06 09:45:27', '1', 'Java是目前最流行的编程语言之一——它可以用来编写Windows程序或者是Web应用，移动应用，网络程序，消费电子产品，机顶盒设备，它无处不在。

有超过30亿的设备是运行在Java之上的。根据Oracle的统计数据，光是使用中的Java Card就有有50亿。

超过900万程序员选择使用Java进行开发，它是最受开发人员欢迎的语言，同时也是最流行的开发平台。

本文为那些准Java程序员们准备了一系列广为流传的Java最佳编程实践：

优先返回空集合而非null
如果程序要返回一个不包含任何值的集合，确保返回的是空集合而不是null。这能节省大量的”if else”检查。
```
public class getLocationName {
    return (null==cityName ? "": cityName);
}
```
', '2', 'Java');
INSERT INTO `article` VALUES ('2', 'Vue总结', '2018-09-06 09:45:34', '2018-09-06 09:45:34', '2', '从代码来看，貌似finally块中的println语句应该会被执行5次。但当程序运行后，你会发现finally块只执行了4次。第5次迭代的时候会触发exit函数的调用，于是这第5次的finally便永远也触发不到了。原因便是——System.exit会挂起所有线程的执行，包括当前线程。即便是try语句后的finally块，只要是执行了exit，便也无力回天了。

在调用System.exit时，JVM会在关闭前执行两个结束任务：

首先，它会执行完所有通过Runtime.addShutdownHook注册进来的终止的钩子程序。这一点**很关键**，因为它会释放JVM外部的资源。

接下来的便是Finalizer了。可能是`System.runFinalizersOnExit`也可能是Runtime.runFinalizersOnExit。finalizer的使用已经被废弃有很长一段时间了。finalizer可以在存活对象上进行调用，即便是这些对象仍在被其它线程所使用。而这会导致不可预期的结果甚至是死锁。', '1', 'Vue');
INSERT INTO `article` VALUES ('3', 'JavaScript技巧', '2018-09-06 09:46:04', '2018-09-06 09:46:04', '3', 'Xms = 最小内存分配
Xmx = 最大内存分配
XX:PermSize = JVM启动时的初始大小
XX:MaxPermSize = JVM启动后可分配的最大空间
如何计算Java中操作的耗时
在Java中进行操作计时有两个标准的方法：System.currentTimeMillis()和System.nanoTime()。问题就在于，什么情况下该用哪个。从本质上来讲，他们的作用都是一样的，但有以下几点不同：

System.currentTimeMillis()的精度在千分之一秒到千分之15秒之间（取决于系统）而System.nanoTime()则能到纳秒级。
System.currentTimeMillis读操作耗时在数个CPU时钟左右。而System.nanoTime()则需要上百个。
System.currentTimeMillis*对应的是绝对时间（1970年1 月1日所经历的毫秒数），而System.nanoTime()则不与任何时间点相关*。

Float还是double

数据类型 所用字节 有效位数
float 4 7
double 8 15
在对精度要求高的场景下，double类型相对float要更流行一些，理由如下：

大多数处理器在处理float和double上所需的时间都是差不多的。而计算时间一样的前提下，double类型却能提供更高的精度。

幂运算
Java是通过异或操作来进行幂运算的。Java对于幂运算有两种处理方式：

乘积：', '1', 'JavaScript');
INSERT INTO `article` VALUES ('4', 'Docker使用经验', '2018-09-06 09:46:40', '2018-09-06 09:46:40', '4', '## 使用 Maven 阿里云国内镜像
修改maven根目录下的conf文件夹中的setting.xml文件，在mirrorms内添加如下内容：
```
<mirror>
   <id>alimaven</id>
   <name>aliyun maven</name>
   <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
   <mirrorOf>central</mirrorOf>
</mirror>
```', '3', 'Docker');
INSERT INTO `article` VALUES ('5', 'Python开发技巧', '2018-09-06 09:46:52', '2018-09-06 09:46:54', '5', '根据Java的接口规范：

FileOutputStream是用于写入原始字节流比如图片流数据。如果是要写入字符流，则应该考虑使用FileWriter。

- 这样就很清楚了，写图片应该使用FileOutputStream而写文本则应该选择FileWriter。

- 附加建议
集合的使用
Java提供了许多集合类——比如，Vector，Stack，Hashtable等。所以鼓励开发人员尽可能地使用这些集合类有如下原因：

使用集合使得代码的可重用度更高。
集合类使得代码的结构更良好，更易于理解与维护。
最重要的是这些集合类都经过充分的测试，代码质量很高。
1-50-500规则
在大型软件系统中，代码的可维护性是件很有挑战的工作。新加入的开发人员经常会抱怨这些情况：单片代码（Monolithic Code）,意大利面式代码（spaghetti code, 常用于描述捆绑在一起并且低内聚的类和方法）。保持代码的整洁与可维护有一条很简单的规则：

10：包内的类不超过10个
50：方法的代码行数不超过50
500：类的代码行数不超过500
SOLID设计准则
SOLID是Robert Martin提出的一套设计准则的简称。根据他的准则：
一个类应当有仅只有一个任务/职责。执行多个任务的类会让人觉得困惑。
单一职责原则
开闭原则 开发人员应当优先考虑扩展现有的软件功能，而不是是修改它。
里氏替换原则 子类必须能够替换掉他们的父类型
接口隔离原则 和单一职责原则类似，但它特指的是接口层。每个接口都应当只负责一项任务。
依赖反转原则 依赖抽象而不是具体实现。也就是说每个模块都应当通过一个抽象层与其它模块进行解耦。
设计模式的使用
设计模式能帮助开发人员更好地在软件中应用软件的设计准则。它还为开发人员提供了跨语言的通用平台。设计模式中的标准术语能让开发人员更容易进行沟通。

关于文档
不要上来就开始写代码。制定计划，准备，编写文档，检查然后再去实现。首先，先把需求记下来。然后去准备设计文档。合理地去假设举证。互相review方案然后进行确认。

使用equals而非==
==是用来比较对象引用的，它会检查两个操作数指向的是不是同一个对象（不是相同的对象，而是同一个对象）。而”equals”则比较的是两个字符串是不是相同（假设是字符串对象）。

避免使用浮点数
只有当确实有必要的时候才使用浮点数。比方说，使用浮点数来表示卢比或者派萨就很容易产生问题——这种情况应当使用BigDecimal。而浮点数更多地是用于测量。
学习Java的一些资源
最后我想给大家推荐一下学习Java的一些相关资料。', '2', 'Python');
INSERT INTO `article` VALUES ('6', 'Jmeter压测总结', '2018-09-06 09:47:18', '2018-09-06 09:47:20', '6', '3.在try catch中要加finally，释放一些特殊的操作
(1)文件流操作，不释放的话容易导致流溢出。

(2)解锁问题

4. HashMap在创建时，最好预估一下大小。因为HashMap会在放不下时做刷新，这时，会将小的haspMap拷贝一份给更大的haspMap.

预估大小，这样可以满足大部分场景，如果大小仍然超出了该怎么办？

5. 字符串的不可变性，不用再说了，前面总结过

6. 单引号和双引号是有区别的

7. java中创建对象的确很昂贵。不要随便乱建对象。

从效率上讲，越少越好。可是总是有那么多的大包，如果碰到性能瓶颈，那么这个方法就是一个出路。

从面向对象的角度看，我觉得，如果性能不是瓶颈，领域模型的设计，还有逻辑清晰、容易理解也许更重要。需要视情况而定。

', '4', 'Jmeter');
INSERT INTO `article` VALUES ('7', 'Junit使用技巧', '2018-09-06 09:47:42', '2018-09-06 09:47:43', '6', 'SQL语言的发展

SQL分有（关系型数据库，非关系型数据库）

## 常见的sql语句类型：

DDL数据定义语言

TPL事务处理语言

DCL数据控制语言

DML数据操作语言

SQL开发技巧着重于DML语句：

DML（SELECT,INSERT , UPDATE , DELETE）



## 正确使用SQL：

增加数据库处理效率，减少应用响应时间

减少数据库服务器负载，增加服务器稳定

减少服务器间通讯的网络流量

正确的使用join从句：

SQL标准中join的类型：

Join:

内连接（INNER）

全外连接（FULL OUTER）

左外连接（LEFT OUTER）

右外连接（RIGHT OUTER）

交叉连接（CROSS）

1. join操作的类型--innerjoin

Select from tableA A inner join tableB B on A.key=B.key', '4', 'Junit');
INSERT INTO `article` VALUES ('8', 'Hadoop使用', '2018-09-06 09:48:27', '2018-09-06 09:48:27', '7', '1.尽量在合适的场合使用单例

使用单例可以减轻加载的负担，缩短加载的时间，提高加载的效率，但并不是所有地方都适用于单例，简单来说，单例主要适用于以下三个方面：

第一，控制资源的使用，通过线程同步来控制资源的并发访问；

第二，控制实例的产生，以达到节约资源的目的；

第三，控制数据共享，在不建立直接关联的条件下，让多个不相关的进程或线程之间实现通信。

2. 尽量避免随意使用静态变量

要知道，当某个对象被定义为static变量所引用，那么GC通常是不会回收这个对象所占有的内存，如：

此时静态变量b的生命周期与A类同步，如果A类不会卸载，那么b对象会常驻内存，直到程序终止。

3. 尽量避免过多过常的创建Java对象

尽量避免在经常调用的方法，循环中new对象，由于系统不仅要花费时间来创建对象，而且还要花时间对这些对象进行垃圾回收和处理，在我们可以控制的范围内，最大限度的重用对象，最好能用基本的数据类型或数组来替代对象。', '6', 'Hadoop');
INSERT INTO `article` VALUES ('9', 'KNN算法的改进', '2018-09-06 09:48:54', '2018-09-06 09:48:58', '8', 'k近邻算法是一种基于实例的算法，即学习过程只是简单的存储已知的训练数据，遇到新的查询实例时，从训练集中取出相似的实例，因此它是一种懒惰(lazy)学习方法。可以为不同的待分类查询实例建立不同的目标函数进行逼近。

k近邻算法原理：

        令D为训练数据集，当测试集d出现时，将d与D中所有的样本进行比较，计算他们之间的相似度（或者距离）。从D中选出前k个最相似的样本，则d的类别由k个最近邻的样本中出现最多的类别决定。

        k近邻算法关键部分是距离（相似度）函数，对于关系型数据，经常使用欧氏距离，对于文本数据，经常采用余弦相似度。k的选择是通过在训练集上交叉检验，交叉验证一般分为三类：double-fold CV即经常所说的2折交叉；10-fold交叉和LOO（leave one out）CV即留一法交叉。

参考http://blog.163.com/leo666@126/blog/static/1194684712011113085410814/

     2折：将原始数据集DataSet均分为两份：一份作为训练集，即trainingSet，一份作为测试集，即testingSet，然后用训练集去做训练，用测试集去验证；之后再将训练集作为测试集，测试集作为训练集进行迭代一次，将两次所得的误差经行处理作为总体数据的预测误差。（注：这里强调一点，就是数据集一定要均分为两份，理由是：作为训练集，数据量一定要不小于测试集，所以在迭代的过程中，使得数据不出现错误情况，必须均分。）

       K-折：（在这里说下K-折）是在将数据集分成K个子集，K个子集中得一个作为测试集，而其余的K-1个数据集作为训练集，最后对K个数据子集的错误计算均值，K次迭代验证是对监督学习算法的结果进行评估的方法，数据集的划分一般采用等均分或者随机划分。【来自邵峰晶等编著《数据挖掘原理与算法》中国水利水电出版社】

      LOO：这个方法是K折的一种特列，就是把数据分为N份，其实每一份都是一个样本，这样迭代N次，计算最后的误差来作为预测误差。

k近邻的问题：

       k近邻简单直接，有效，健壮，在很多情况下可以和复杂的算法性能相同。但是k近邻有三个缺点：

（1）需要更精确的距离函数代替欧氏距离

（2）搜索一个最优的近邻大小代替k

（3）找出更精确的类别概率估计代替简单的投票方法。

针对上述三种问题，提出了三中改进思路：

1.改进距离函数

     由于它基于假设测试实例在欧式空间中最相似于近邻的实例的类别。由于实例间距离计算基于实例的所有属性，然而我们搜索的是实例包括不相关属性，标准的欧氏距离将会变得不准确。当出现许多不相关属性时称为维数灾难，kNN对此特别敏感。

      解决方法：（1）消除不相关属性即特征选择。Kohavietal提出了一种缠绕法(wrapper)除此外还有贪婪搜索和遗传搜索。

                        （2）属性加权。w是属性a的权重

‘

当所有的属性不均衡时，属性加权距离函数定义为



Ip (Ai;C)是属性A和类别C的互信息

除此之外，还有一种基于频率的距离函数，称之为相异性度量。与卡方距离相似



值差分度量（VDM）是标称属性的距离函数



C是输出的类别数量，P是输入属性A时输出C的条件概率，VDM在度量连续属性时需要将连续属性映射为标称属性

2.改进近邻距离大小

     KNN分类准确率对K值敏感，通过交叉验证方法确定最优的K值。一旦在训练时学习了最优的K值，可以在分类时对所有的测试集使用。DKNN即动态确定K值，所有的算法都需要确定K近邻，为此，在KDTree和NBTree中，实例存储在叶节点上，邻近实例存储在相同或者相近的叶节点上。树的内部节点通过测试选择属性的相关性对测试实例进行排序

3.改进类别概率估计

      KNN的实例邻近的类别被认为相同。所以改进算法需要根据他们到测试实例的距离进行加权。



另外一种非常有效的方法是基于概率的局部分类模型，即结合NB算法，这种算法在数据较小的时候表现很好。有研究者发现保持紧邻k很小将减少对于NB强依赖的机会，然而NB的类别估计概率不可信。

', '5', '机器学习');
INSERT INTO `article` VALUES ('10', '逻辑回归的另一角度解析', '2018-09-06 09:49:25', '2018-09-06 09:49:27', '9', '1. 什么是逻辑回归？
许多人对线性回归都比较熟悉，但知道逻辑回归的人可能就要少的多。从大的类别上来说，逻辑回归是一种有监督的统计学习方法，主要用于对样本进行分类。

在线性回归模型中，输出一般是连续的，例如

，对于每一个输入的x，都有一个对应的y输出。**模型的定义域和值域都可以**是[-∞, +∞]。但是对于逻辑回归，输入可以是连续的[-∞, +∞]，但输出一般是离散的，即只有有限多个输出值。例如，其值域可以只有两个值{0, 1}，这两个值可以表示对样本的某种分类，高/低、患病/健康、阴性/阳性等，这就是最常见的二分类逻辑回归。因此，从整体上来说，通过逻辑回归模型，我们将在整个实数范围上的x映射到了有限个点上，这样就实现了对x的分类。因为每次拿过来一个x，经过逻辑回归分析，就可以将它归入某一类y中。


逻辑回归与线性回归的关系
逻辑回归也被称为广义线性回归模型，它与线性回归模型的形式基本上相同，都具有 ax+b，其中a和b是待求参数，其区别在于他们的因变量不同，多重线性回归直接将ax+b作为因变量，即y = ax+b，而logistic回归则通过函数S将ax+b对应到一个隐状态p，p = S(ax+b)，然后根据p与1-p的大小决定因变量的值。这里的函数S就是Sigmoid函数

将t换成ax+b，可以得到逻辑回归模型的参数形式：', '5', '机器学习');

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `autnority_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`autnority_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authority
-- ----------------------------
INSERT INTO `authority` VALUES ('1', '发文章');
INSERT INTO `authority` VALUES ('2', '写评论');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '前端开发', '这是前端');
INSERT INTO `category` VALUES ('2', '后端开发', '这是后端');
INSERT INTO `category` VALUES ('3', '运维', '这是运维');
INSERT INTO `category` VALUES ('4', '测试', '这是测试');
INSERT INTO `category` VALUES ('5', '机器学习', '这是机器学习');
INSERT INTO `category` VALUES ('6', '大数据', '这是大数据');

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect` (
  `collect_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `article_id` int(11) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`collect_id`),
  KEY `fk_collect_user` (`user_id`),
  KEY `fk_collect_article` (`article_id`),
  CONSTRAINT `fk_collect_article` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_collect_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of collect
-- ----------------------------
INSERT INTO `collect` VALUES ('1', '1', '1', '2018-09-06 10:08:51');
INSERT INTO `collect` VALUES ('2', '2', '1', '2018-09-06 10:09:00');
INSERT INTO `collect` VALUES ('3', '4', '2', '2018-09-06 10:09:09');
INSERT INTO `collect` VALUES ('4', '7', '3', '2018-09-06 10:09:23');
INSERT INTO `collect` VALUES ('5', '9', '1', '2018-09-06 10:09:37');
INSERT INTO `collect` VALUES ('6', '8', '2', '2018-09-06 10:09:50');
INSERT INTO `collect` VALUES ('7', '8', '1', '2018-09-06 10:09:57');
INSERT INTO `collect` VALUES ('8', '8', '8', '2018-09-06 10:10:06');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `article_id` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `root_id` int(11) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`comment_id`),
  KEY `fk_comment_user` (`user_id`),
  KEY `fk_comment_parent_id` (`parent_id`),
  KEY `fk_comment_root_id` (`root_id`),
  KEY `fk_comment_article` (`article_id`),
  CONSTRAINT `fk_comment_article` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_comment_parent_id` FOREIGN KEY (`parent_id`) REFERENCES `comment` (`comment_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_comment_root_id` FOREIGN KEY (`root_id`) REFERENCES `comment` (`comment_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_comment_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '1', '1', '这是我写的文章，欢迎大家讨论并改进', null, '1', '2018-09-06 09:50:53');
INSERT INTO `comment` VALUES ('2', '2', '1', '楼主写的不错，有自己的见解', '1', '1', '2018-09-06 09:51:16');
INSERT INTO `comment` VALUES ('3', '3', '1', '其实在文章XXX这里，还可以这样改进...', null, '3', '2018-09-06 09:53:13');
INSERT INTO `comment` VALUES ('4', '1', '1', '我觉得你说的有一些道理，我去测试跑跑快', '3', '3', '2018-09-06 09:53:43');
INSERT INTO `comment` VALUES ('5', '6', '2', '学习前端Vue中，写的不错', null, '5', '2018-09-06 09:59:48');
INSERT INTO `comment` VALUES ('6', '2', '2', '感谢感谢，一点自己的心得', '5', '5', '2018-09-06 09:59:55');
INSERT INTO `comment` VALUES ('7', '7', '2', '写的不错', '5', '5', '2018-09-06 10:00:23');
INSERT INTO `comment` VALUES ('8', '9', '2', '我也觉得确实写的不错', '7', '5', '2018-09-06 10:03:06');
INSERT INTO `comment` VALUES ('9', '1', '3', '学习了，学习了', null, '9', '2018-09-06 10:06:01');
INSERT INTO `comment` VALUES ('10', '10', '4', '文章在这里XXX，我觉得写得很不错', null, '10', '2018-09-06 10:06:09');

-- ----------------------------
-- Table structure for forbidden
-- ----------------------------
DROP TABLE IF EXISTS `forbidden`;
CREATE TABLE `forbidden` (
  `forbidden_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `authority_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`forbidden_id`),
  KEY `fk_forbidden_user` (`user_id`),
  KEY `fk_forbidden_authority` (`authority_id`),
  CONSTRAINT `fk_forbidden_authority` FOREIGN KEY (`authority_id`) REFERENCES `authority` (`autnority_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_forbidden_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of forbidden
-- ----------------------------
INSERT INTO `forbidden` VALUES ('1', '4', '1');
INSERT INTO `forbidden` VALUES ('2', '5', '2');
INSERT INTO `forbidden` VALUES ('3', '6', '1');
INSERT INTO `forbidden` VALUES ('4', '7', '2');

-- ----------------------------
-- Table structure for star
-- ----------------------------
DROP TABLE IF EXISTS `star`;
CREATE TABLE `star` (
  `star_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `article_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`star_id`),
  KEY `fk_star_user` (`user_id`),
  KEY `fk_star_article` (`article_id`),
  CONSTRAINT `fk_star_article` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_star_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of star
-- ----------------------------
INSERT INTO `star` VALUES ('1', '1', '1');
INSERT INTO `star` VALUES ('2', '2', '1');
INSERT INTO `star` VALUES ('3', '3', '1');
INSERT INTO `star` VALUES ('4', '1', '2');
INSERT INTO `star` VALUES ('5', '6', '2');
INSERT INTO `star` VALUES ('6', '8', '1');
INSERT INTO `star` VALUES ('7', '8', '2');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `nickname` varchar(20) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `bio` varchar(255) DEFAULT NULL,
  `is_admin` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `ui_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'a123456', '123456', '小啊', null, null, '1');
INSERT INTO `user` VALUES ('2', 'b123456', '123456', '小波', null, null, '0');
INSERT INTO `user` VALUES ('3', 'c123456', '123456', '小菜', null, null, '0');
INSERT INTO `user` VALUES ('4', 'd123456', '123456', '小代', null, null, '0');
INSERT INTO `user` VALUES ('5', 'e123456', '123456', '小哦', null, null, '0');
INSERT INTO `user` VALUES ('6', 'f123456', '123456', '小付', null, null, '0');
INSERT INTO `user` VALUES ('7', 'g123456', '123456', '小高', null, null, '0');
INSERT INTO `user` VALUES ('8', 'h123456', '123456', '小黄', null, null, '0');
INSERT INTO `user` VALUES ('9', 'i123456', '123456', '小爱', null, null, '0');
INSERT INTO `user` VALUES ('10', 'j123456', '123456', '小建', null, null, '0');

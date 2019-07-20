create database myweb1;

use myweb1;

create table t1(
	id int,
	name varchar(30)
);

create table s1(
	id int,
	rst int
);

insert into t1(id, name) values(1001, '张三');
insert into t1(id, name) values(1002, '李四');

insert into s1(id, rst) value(1001, 86);
insert into s1(id, rst) value(1002, 98);

commit;

drop table if exists web_group;
drop table if exists web_item;

create table web_group(
	id int,
	gname varchar(100),
	order_id int
);

create table web_item(
	id	int,
	gid int,
	title varchar(100),
	order_id int,
	url varchar(200)
);

insert into web_group values(5, '我常用的网站', 1);
insert into web_group values(1, '数据库——MySQL', 2);
insert into web_group values(2, 'Web前端开发', 3);
insert into web_group values(3, 'JavaWeb开发', 4);
insert into web_group values(4, '操作系统——Linux', 5);

insert into web_item values(13, 5, '百度', 1, 'http://www.baidu.com/');
insert into web_item values(14, 5, '菜鸟教程', 2, 'https://www.runoob.com/');
insert into web_item values(15, 5, '等级考试', 3, 'http://ncre.neea.edu.cn/');
insert into web_item values(16, 5, '计算机软考', 4, 'http://www.ruankao.org.cn/');
insert into web_item values(1, 1, '安装MySQL', 1, '');
insert into web_item values(2, 1, '建库', 2, '');
insert into web_item values(3, 1, '表', 3, '');
insert into web_item values(4, 1, '索引', 4, '');
insert into web_item values(5, 1, '视图', 5, '');
insert into web_item values(6, 1, 'SQL', 6, '');
insert into web_item values(7, 2, 'HTML5', 1, '');
insert into web_item values(8, 2, '盒子模型', 2, '');
insert into web_item values(9, 2, 'CSS', 3, '');
insert into web_item values(10, 2, 'JS', 4, '');
insert into web_item values(11, 2, 'JQuery', 5, '');
insert into web_item values(12, 3, '', 1, '');

commit;

select gid, gname, i.id, title, url
from web_group g
left join web_item i on g.id=i.gid
order by g.order_id, i.order_id;

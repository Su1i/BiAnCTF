use bianctf;
-- ----------------------------
-- 1、用户信息表
-- ----------------------------
drop table if exists lab_user;
create table lab_user (
                          user_id           bigint(20)      not null     comment '用户ID',
                          email             varchar(50)     default ''                 comment '用户邮箱',
                          password          varchar(100)    default ''                 comment '密码',
                          user_name         varchar(30)     not null                   comment '用户昵称',
                          real_name         varchar(30)     default ''                 comment '真实姓名',
                          student_id        varchar(50)     default ''                 comment '学号/工号',
                          college           varchar(30)     default ''                 comment '学院',
                          speciality        varchar(50)     default ''                 comment '专业',
                          grade             varchar(10)     default ''                 comment '年级',
                          identity_card     varchar(50)     default ''                 comment '身份证号',
                          phone_number       varchar(11)    default ''                 comment '手机号码',
                          sex               char(1)         default '0'                comment '用户性别（0男 1女 2未知）',
                          avatar            varchar(100)    default ''                 comment '头像地址',
                          user_type         varchar(10)     default 'sys_user'         comment '用户类型（sys_user系统用户）',
                          status            char(1)         default '0'                comment '帐号状态（0正常 1停用）',
                          del_flag          char(1)         default '0'                comment '删除标志（0代表存在 2代表删除）',
                          login_ip          varchar(128)    default ''                 comment '最后登录IP',
                          login_date        datetime                                   comment '最后登录时间',
                          create_by         varchar(64)     default ''                 comment '创建者',
                          create_time       datetime                                   comment '创建时间',
                          update_by         varchar(64)     default ''                 comment '更新者',
                          update_time       datetime                                   comment '更新时间',
                          remark            varchar(500)    default null               comment '备注',
                          primary key (user_id)
) engine=innodb comment = '实验室用户信息表';

-- ----------------------------
-- 初始化-用户信息表数据
-- ----------------------------
insert into lab_user values(1,  'crazyLionLi@163.com', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '疯狂的狮子Li', '狮子','201914890221','理学院', '物理学', '2019', '130123456789456132', '15666666666', '0', '', 'sys_user','0', '0','127.0.0.1', sysdate(), 'admin', sysdate(), '', null, '测试');
insert into lab_user values(2,  'test@163.com', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '疯狂的狮子', '狮子','201914890221','理学院', '物理学', '2019', '130123456789456132', '15666666666', '0', '', 'sys_user','0', '0','127.0.0.1', sysdate(), 'admin', sysdate(), '', null, '测试');



-- ----------------------------
-- 3、实验室方向表
-- ----------------------------
create table lab_direction (
                          direction_id      bigint(20)      not null     comment '方向ID',
                          direction_name    varchar(30)     not null                   comment '方向名称',
                          direction_describe    varchar(30)     default ''                   comment '方向描述',
                          del_flag          char(1)         default '0'                comment '删除标志（0代表存在 2代表删除）',
                          create_by         varchar(64)     default ''                 comment '创建者',
                          create_time       datetime                                   comment '创建时间',
                          update_by         varchar(64)     default ''                 comment '更新者',
                          update_time       datetime                                   comment '更新时间',
                          remark            varchar(500)    default null               comment '备注',
                          primary key (direction_id)
) engine=innodb comment = '实验室方向表';


-- ----------------------------
-- 初始化-实验室方向表数据
-- ----------------------------
insert into lab_direction values(1,  'web', 'web安全', '0', 'admin', sysdate(), '', null, '测试');
insert into lab_direction values(2,  'pwn', '二进制安全', '0', 'admin', sysdate(), '', null, '测试');

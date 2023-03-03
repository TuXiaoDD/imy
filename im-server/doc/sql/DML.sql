
create table imy.user
(
    id          bigint auto_increment
        primary key,
    nickname    varchar(128)                        not null comment '用户名称',
    mobile      char(12)                            null comment '手机号',
    email       varchar(128)                        null comment '邮箱',
    gender      tinyint                             null comment '性别',
    birthday    timestamp                           null comment '生日',
    motto       varchar(512)                        null comment '个性签名',
    avatar      varchar(256)                        null comment '头像',
    add_time    timestamp default CURRENT_TIMESTAMP null,
    update_time timestamp                           null,
    is_delete   tinyint   default 0                 null
)
    comment '用户表';



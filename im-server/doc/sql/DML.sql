
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

-- 暂定
create table imy.friend
(
    id            bigint auto_increment
        primary key,
    add_time      timestamp default CURRENT_TIMESTAMP null,
    update_time   timestamp                           null,
    is_delete     int       default 0                 null,
    friend_uid    bigint                              not null,
    uid           bigint                              not null,
    remark        varchar(256)                        null comment '好友备注',
    relation_type int                                 null comment '好友类型',
    status        int                                 null comment '状态'
)
    comment '好友表';



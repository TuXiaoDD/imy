
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
    latest_msg    text                                null comment '最新的一条消息',
    direction     int       default 0                 null comment '0 表示uid给friend_uid发 1 表示friend_uid给uid发',
    unread        int       default 0                 null comment '0 未读；1 已读'
)
    comment '好友表';

create table imy.`group`
(
    id          bigint auto_increment
        primary key,
    add_time    timestamp default CURRENT_TIMESTAMP null,
    update_time timestamp                           null,
    is_delete   int       default 0                 null,
    group_name  varchar(256)                        not null comment '群名称',
    announce    text                                null comment '群公告',
    master_uid  bigint                              null comment '群主',
    introduce   text                                null comment '群简介',
    avatar      varchar(512)                        null comment '群头像'
)
    comment '群';

create table imy.group_member
(
    id           bigint auto_increment
        primary key,
    group_id     bigint                              not null,
    uid          bigint                              not null,
    group_remark varchar(256)                        null comment '群备注',
    add_time     timestamp default CURRENT_TIMESTAMP null,
    update_time  timestamp                           null,
    is_delete    int       default 0                 null,
    constraint group_member_group_id_uindex
        unique (group_id)
)
    comment '群成员';

create table imy.group_message
(
    id          bigint auto_increment
        primary key,
    add_time    timestamp default CURRENT_TIMESTAMP null,
    update_time timestamp                           null,
    is_delete   int       default 0                 null,
    group_id    bigint                              not null,
    content     text                                not null comment '消息内容',
    msg_hash    varchar(256)                        not null comment '消息唯一标识',
    msg_type    int       default 0                 not null comment '消息类型',
    src_uid     bigint                              not null comment '发消息的',
    dst_uid     bigint                              not null comment '收消息的',
    direction   int       default 0                 null comment '0 src发给dst
1 dst发给src',
    unread      int       default 0                 null comment '0 未读；1 已读'
)
    comment '群聊消息表';

create table imy.single_message
(
    id          bigint auto_increment
        primary key,
    add_time    timestamp default CURRENT_TIMESTAMP null,
    update_time timestamp                           null,
    is_delete   int       default 0                 null,
    uid         bigint                              not null comment '发送方uid',
    to_uid      bigint                              not null comment '接受方uid',
    msg_type    varchar(256)                        not null comment '消息类型',
    content     text                                not null comment '消息内容',
    direction   int       default 0                 null comment '0 表示uid发给to_uid
1 表示to_uid发给uid',
    unread      int       default 0                 null comment '0 未读；1 已读',
    msg_hash    varchar(256)                        not null
)
    comment '消息表';



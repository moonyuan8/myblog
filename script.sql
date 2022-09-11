create table t_comment
(
    id                bigint auto_increment
        primary key,
    nickname          varchar(255) null,
    email             varchar(255) null,
    content           varchar(255) null,
    avatar            varchar(255) null,
    create_time       datetime     null,
    blog_id           bigint       null,
    parent_comment_id bigint       null,
    admin_comment     bit          not null
)
    charset = utf8;

create table t_friend
(
    id             bigint auto_increment
        primary key,
    blogname       varchar(255) null,
    blogaddress    longtext     null,
    pictureaddress longtext     null,
    createtime     datetime     null,
    status         int          null,
    content        longtext     null
);

create table t_message
(
    id                bigint auto_increment
        primary key,
    nickname          varchar(255) null,
    email             varchar(255) null,
    content           varchar(255) null,
    avatar            varchar(255) null,
    create_time       datetime     null,
    parent_message_id bigint       null,
    admin_message     bit          not null
)
    charset = utf8;

create table t_microblog
(
    id          bigint auto_increment
        primary key,
    content     longtext     null,
    create_time datetime     null,
    title       varchar(255) null
);

create table t_person
(
    email  varchar(255) null,
    name   varchar(255) null,
    status int          null,
    code   varchar(100) null,
    id     bigint auto_increment
        primary key
);

create table t_picture
(
    id                 bigint auto_increment
        primary key,
    pictureaddress     varchar(255) null,
    picturedescription varchar(255) null,
    picturename        varchar(255) null,
    picturetime        varchar(255) null
)
    charset = utf8;

create table t_type
(
    id   bigint auto_increment
        primary key,
    name varchar(255) not null
)
    charset = utf8;

create table t_user
(
    id          bigint auto_increment
        primary key,
    avatar      varchar(255) null,
    create_time datetime     null,
    email       varchar(255) null,
    nickname    varchar(255) null,
    password    varchar(255) null,
    type        int          null,
    update_time datetime     null,
    username    varchar(255) null
)
    charset = utf8;

create table t_blog
(
    id              bigint auto_increment
        primary key,
    appreciation    bit          not null,
    content         longtext     null,
    create_time     datetime     null,
    description     varchar(255) null,
    first_picture   varchar(255) null,
    flag            varchar(255) null,
    published       bit          not null,
    share_statement bit          not null,
    title           varchar(255) null,
    update_time     datetime     null,
    views           int          null,
    type_id         bigint       null,
    user_id         bigint       null,
    comment_count   int          null,
    commentabled    bit          null,
    recommend       bit          null,
    constraint FK292449gwg5yf7ocdlmswv9w4j
        foreign key (type_id) references t_type (id),
    constraint FK8ky5rrsxh01nkhctmo7d48p82
        foreign key (user_id) references t_user (id)
)
    charset = utf8;



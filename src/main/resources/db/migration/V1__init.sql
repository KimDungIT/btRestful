create table account
(
    id                 int          not null
        primary key,
    enable             bit          not null,
    password           varchar(255) null,
    username           varchar(255) null,
    role               varchar(255) null,
    created_by         varchar(255) null,
    created_date       datetime     null,
    last_modified_by   varchar(255) null,
    last_modified_date datetime     null
)
    engine = MyISAM;

create table composer
(
    id                 int auto_increment
        primary key,
    name               varchar(50)  null,
    age                int          null,
    hometown           varchar(50)  null,
    created_by         varchar(255) null,
    created_date       datetime     null,
    last_modified_by   varchar(255) null,
    last_modified_date datetime     null
);

create table hibernate_sequence
(
    next_val bigint null
)
    engine = MyISAM;

create table music
(
    id          int auto_increment
        primary key,
    title       varchar(50) null,
    year         varchar(50) null,
    composer_id int         not null,
    constraint FKb0l1he3w9glcychtykd87gica
        foreign key (composer_id) references composer (id)
);


# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table capabilities (
  id                            bigint auto_increment not null,
  title                         varchar(255),
  title2                        varchar(255),
  classli                       varchar(255),
  description                   varchar(255),
  image_url                     varchar(255),
  constraint pk_capabilities primary key (id)
);

create table gallery (
  id                            bigint auto_increment not null,
  description                   varchar(255),
  image_url                     varchar(255),
  constraint pk_gallery primary key (id)
);

create table school (
  id                            bigint auto_increment not null,
  title                         varchar(255),
  description                   varchar(255),
  image_url                     varchar(255),
  constraint pk_school primary key (id)
);

create table team (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  description                   varchar(255),
  image_url                     varchar(255),
  constraint pk_team primary key (id)
);

create table user (
  email                         varchar(255) not null,
  password_hash                 varchar(255),
  name                          varchar(255),
  phone                         varchar(255),
  salt                          varchar(255),
  constraint pk_user primary key (email)
);


# --- !Downs

drop table if exists capabilities;

drop table if exists gallery;

drop table if exists school;

drop table if exists team;

drop table if exists user;


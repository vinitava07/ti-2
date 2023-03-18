create database powerchat;
create schema powerchat;

create table if not exists powerchat.user (
 NAME varchar(255),
 EMAIL varchar(255),
 PHONE_NUMBER varchar(50) primary key,
);
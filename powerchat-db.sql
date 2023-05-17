create database powerchat;
create schema powerchat;

create table if not exists powerchat.user (
name varchar(255),
email varchar(255),
phone_number varchar(50) primary key
);

create table if not exists powerchat.plan (
id varchar(255) primary key,
name varchar(255),
monthly_prompt_limit numeric
);

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

create table if not exists powerchat.subscription (
id uuid default uuid_generate_v4() primary key,
subscription_user varchar(50) references powerchat.user(phone_number),
plan varchar(255) references powerchat.plan(id),
created_at timestamp,
expiration_date timestamp,
is_active boolean
);

create table if not exists powerchat.question (
 question_subscription uuid references powerchat.subscription(id),
 question text,
 reply text,
 created_at timestamp,
 id uuid default uuid_generate_v4() primary key
);

create table if not exists powerchat.admin (
 id uuid default uuid_generate_v4() primary key,
 email varchar(255),
 password varchar(255)
);

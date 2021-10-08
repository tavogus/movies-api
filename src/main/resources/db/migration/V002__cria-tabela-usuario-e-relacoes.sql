create table tb_user(
    id bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null,
    password varchar(255) not null,
    primary key (id)
);

create table tb_role(
    id bigint not null auto_increment,
    authority varchar(30),
    primary key (id)
);

create table tb_user_role(
    user_id bigint not null,
    role_id bigint not null
);

INSERT INTO tb_user (name, email, password) VALUES ('Bob Brown', 'bob@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (name, email, password) VALUES ('Ana Grey', 'ana@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

INSERT INTO tb_role (authority) VALUES ('ROLE_USER');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);
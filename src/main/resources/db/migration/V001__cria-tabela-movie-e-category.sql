create table category(
   id bigint not null auto_increment,
   name varchar(50) not null,
   primary key (id)
);

create table movie(
   id bigint not null auto_increment,
   title varchar(60) not null,
   category_id bigint not null,
   insertion_date datetime not null,
   author varchar(60) not null,
   synopsis text not null,
   primary key (id)
);

create table tv_show(
   id bigint not null auto_increment,
   title varchar(60) not null,
   category_id bigint not null,
   insertion_date datetime not null,
   author varchar(60) not null,
   synopsis text not null,
   seasons int not null,
   primary key (id)
);

create table actor(
    id bigint not null auto_increment,
    name varchar(60) not null,
    primary key (id)
);

create table movie_actor(
    movie_id bigint not null,
    actor_id bigint not null
);

create table tvshow_actor(
    tvshow_id bigint not null,
    actor_id bigint not null
);

alter table movie add constraint fk_movie_category
foreign key (category_id) references category (id);

alter table tv_show add constraint fk_tv_show_category
foreign key (category_id) references category (id);
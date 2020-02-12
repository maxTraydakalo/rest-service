create table authority
(
    id        bigint not null auto_increment,
    authority varchar(255),
    primary key (id)
) engine = InnoDB;

create table claim
(
    id          bigint not null auto_increment,
    claimed_at  datetime(6),
    description varchar(255),
    is_done     bit,
    name        varchar(255),
    employee_id bigint,
    primary key (id)
) engine = InnoDB;

create table employee
(
    id      bigint not null auto_increment,
    name    varchar(255),
    surname varchar(255),
    primary key (id)
) engine = InnoDB;

create table user
(
    id       bigint not null auto_increment,
    username    varchar(255),
    password varchar(255),
    primary key (id)
) engine = InnoDB;

create table user_authorities
(
    user_id        bigint not null,
    authorities_id bigint not null
) engine = InnoDB;

alter table claim
    add constraint FKoi7uxmi4i56j4e9lwkgf4757m
        foreign key (employee_id)
            references employee (id);

alter table user_authorities
    add constraint FKdd8lhvujos470g40gikxj22mb
        foreign key (authorities_id)
            references authority (id);

alter table user_authorities
    add constraint FKmj13d0mnuj4cd8b6htotbf9mm
        foreign key (user_id)
            references user (id);
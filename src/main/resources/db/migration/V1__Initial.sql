create sequence hibernate_sequence start with 1 increment by 1;

create table claim
(
    id          bigint not null,
    description varchar(255),
    is_done     boolean,
    name        varchar(255),
    employee_id bigint,
    primary key (id)
);

create table employee
(
    id      bigint not null,
    name    varchar(255),
    surname varchar(255),
    primary key (id)
);

alter table claim
    add constraint FKoi7uxmi4i56j4e9lwkgf4757m foreign key (employee_id) references employee;

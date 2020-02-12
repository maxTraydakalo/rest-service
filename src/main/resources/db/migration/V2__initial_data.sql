insert into employee (id, name, surname)
values (1, 'max', 'traydakalo');

insert into claim (description, is_done, name, employee_id, claimed_at)
values ('desc', false, 'claim ', 1, '1000-01-01 00:00:00');
insert into claim ( description, is_done, name, employee_id, claimed_at)
values ('desc', false, 'claim ', 1, '1000-01-01 00:00:00');
insert into claim (description, is_done, name, employee_id, claimed_at)
values ('desc', false, 'claim ', 1, '1000-01-01 00:00:00');
insert into claim (description, is_done, name, claimed_at)
values ('desc', false, 'claim',  '2020-03-26 00:00:00');

insert into user(username, password) values ('externaluser', '$2a$10$.wi5WlH5.1bS/sLeyTt3/.w8tF1m1LDcVZCzKg2ecn7zjSJTCtfDa');
insert into user(username, password) values ('1', '$2a$10$bRELpDqfHsEfKR3Orj2FbOb/d6tAGQbREXShQt4XO06C9mBIhjeIy');
insert into test.sys_user(id, username, password)
values (1, 'wyf', '{bcrypt}$2a$10$a0G0XIrqPHtFtIVNRhSKruJ0ljQjB0t7fT.VhWufom7XmQbYO1Nfu');

insert into test.sys_user(id, username, password)
values (2, 'wisely', '{bcrypt}$2a$10$o3eruWqpL1Iz.0LaLV.CZOX8nl.vsbhHMxWoBG1Su7u85EDR42HXm');

insert into test.sys_role(id, name)
values (1, 'ROLE_ADMIN');

insert into test.sys_role(id, name)
VALUES (2, 'ROLE_USER');

delete
from test.sys_user_roles
where sys_user_id = 1;
delete
from test.sys_user_roles
where sys_user_id = 2;

insert into test.sys_user_roles (sys_user_id, roles_id)
values (1, 1);
insert into test.sys_user_roles (sys_user_id, roles_id)
values (2, 2);
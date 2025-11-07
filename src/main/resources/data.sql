insert into user_details(id,birth_date,name)
values (10001,current_date(),'JongBeom');

insert into user_details(id,birth_date,name)
values (10002,current_date(),'Beams');

insert into user_details(id,birth_date,name)
values (10003,current_date(),'JJB');

insert  into post (id,description,user_id)
values (20001,'나는 빨리 웹 만들고 싶다.',10001);

insert  into post (id,description,user_id)
values (20002,'나는 빨리 포폴 만들고 싶다.',10001);

insert  into post (id,description,user_id)
values (20003,'나는 빨리 취업하고 싶다.',10002);

insert  into post (id,description,user_id)
values (20004,'나는 빨리 다시 살고 싶다.',10002);
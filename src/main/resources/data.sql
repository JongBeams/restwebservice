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

insert into todo (ID,USERNAME,DESCRIPTION,TARGET_DATE,DONE)
values (10001,'jongbeom','일정짜기ㅣ기기기기기기','2025-11-10',false);
insert into todo (ID,USERNAME,DESCRIPTION,TARGET_DATE,DONE)
values (10002,'jongbeom','놀고싶다아아아아아아아','2026-02-10',false);
insert into todo (ID,USERNAME,DESCRIPTION,TARGET_DATE,DONE)
values (10003,'jongbeom','일정이 빡빡해에에에ㅔㅇ에ㅔ','2026-01-10',false);
insert into todo (ID,USERNAME,DESCRIPTION,TARGET_DATE,DONE)
values (10004,'jongbeom','개발조아아아아아아아아',CURRENT_DATE(),false);
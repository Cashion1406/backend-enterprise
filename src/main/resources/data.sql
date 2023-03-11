insert into department_tbl(
	 id,department_info, is_deleted, depart_name)
	values ( '1','Learning about computer', 'false', 'Computer Science');

insert into department_tbl(
	 id,department_info, is_deleted, depart_name)
	values ( '2','Learning about graphic', 'false', 'Graphic Design');

insert into department_tbl(
	 id,department_info, is_deleted, depart_name)
	values ( '3','Learning about money', 'false', 'Business');

commit ;

insert into client_tbl(
	id, client_age, client_info, client_email,client_firstname, client_is_deleted, client_lastname, client_pronoun, client_role, department_id)
	values ('1', '21', 'Student', 'ipad7383@gmail.com','DOAN' ,'FALSE', 'AN', 'HE_HIM','ROLE_USER' , '2');


insert into client_tbl(
	id, client_age, client_info, client_email,client_firstname, client_is_deleted, client_lastname, client_pronoun, client_role, department_id)
	values ('2', '21', 'Student','cashion1406@gmail.com' ,'NGUYEN', 'FALSE', 'LAM', 'SHE_HER','ROLE_ADMIN' , '1');

insert into client_tbl(
	id, client_age, client_info, client_email,client_firstname, client_is_deleted, client_lastname, client_pronoun, client_role, department_id)
	values ('3', '21', 'Student', 'steven140602@gmail.com','NGUYEN', 'FALSE', 'MINH', 'HE_HIM','ROLE_MOD' , '3');


insert into client_tbl(
	id, client_age, client_info, client_email,client_firstname, client_is_deleted, client_lastname, client_pronoun, client_role, department_id)
	values ('4', '25', 'Teacher', 'andtgcs200176@fpt.edu.vn','STEVEN' ,'FALSE', 'CASHION', 'HE_HIM','ROLE_QA_DE' , '2');

insert into client_tbl(
	id, client_age, client_info, client_email,client_firstname, client_is_deleted, client_lastname, client_pronoun, client_role, department_id)
	values ('5', '25', 'Teacher','andtgcs200176@fpt.edu.vn' ,'LAMIE', 'FALSE', 'LAM', 'SHE_HER','ROLE_QA_DE' , '1');

insert into client_tbl(
	id, client_age, client_info, client_email,client_firstname, client_is_deleted, client_lastname, client_pronoun, client_role, department_id)
	values ('6', '25', 'Teacher', 'andtgcs200176@fpt.edu.vn','NGUYEN', 'FALSE', 'DAMIN', 'HE_HIM','ROLE_QA_DE' , '3');


commit;

insert into cate_tbl(
	id, cate_name)
values ('1', 'Busines');

insert into cate_tbl(
	id, cate_name)
values ('2', 'IT');

insert into cate_tbl(
	id, cate_name)
values ('3', 'ART');

commit;

insert into topic_tbl(
	id, topic_final_date, topic_closure_date,image_url ,is_deleted, modify_date, topic_name, topic_description)
	values (1, '2023-04-29 15:25:15', '2023-03-28 15:25:15','/download/imageURL' ,'FALSE', '02/20/2023', 'Create Spring Boot micro services','Spring boot architecture');

insert into topic_tbl(
	id, topic_final_date, topic_closure_date,image_url ,is_deleted, modify_date, topic_name,topic_description )
	values (2, '2023-04-29 15:25:15', '2023-03-28 15:25:15', '/download/imageURL','FALSE', '02/20/2023', 'Create cross-platform using Flutter','Discuss about Flutter');

insert into topic_tbl(
	id, topic_final_date, topic_closure_date,image_url ,is_deleted, modify_date, topic_name,topic_description)
	values (3, '2023-04-29 15:25:15', '2023-02-28 15:25:15','/download/imageURL' ,'FALSE', '02/20/2023', 'Deploy handwritten OCR', 'Handwritten OCR discussion');

commit;

insert into idea_tbl(
	id, is_anonymous ,attached_path, idea_body, date, modify_date, idea_title, client_id, topic_id)
	values ('1', 'false' ,'/download/file', 'Just fixed','2023-02-27 15:25', '2023-02-27 15:25', 'Fixing flutter', '1','2');

insert into idea_tbl(
	id, is_anonymous,attached_path, idea_body, date, modify_date, idea_title, client_id, topic_id)
	values ('2','false' ,'/download/file', 'Just fixed','2023-02-27 15:25', '2023-02-27 15:25', 'Fixing Spring boot ', '3','1');

insert into idea_tbl(
	id,is_anonymous ,attached_path, idea_body, date, modify_date, idea_title, client_id, topic_id)
	values ('3','false' ,'/download/file', 'Just fixed','2023-02-27 15:25', '2023-02-27 15:25', 'Fixing React', '2','3');

commit ;

insert into comment_tbl(
	id, is_anonymous,comment_body, modify_date, client_id, idea_id)
	values ('1', 'false','This idea is briliant', '2023-02-27 15:25','1', '1' );

insert into comment_tbl(
	id, is_anonymous,comment_body, modify_date,client_id, idea_id )
	values ('2', 'false','I honestly dont know about this','2023-02-27 15:26' ,'2', '1');

insert into comment_tbl(
	id, is_anonymous,comment_body, modify_date, client_id, idea_id)
	values ('3', 'false','Need some adjustment',  '2023-02-27 15:27','3', '1');

commit;

insert into reaction_tbl(
	id, reaction, client_id, idea_id)
	values ('1', 'true', '1', '1');
insert into reaction_tbl(
	id, reaction, client_id, idea_id)
	values ('2', 'false', '2', '1');
insert into reaction_tbl(
	id, reaction, client_id, idea_id)
	values ('3', null , '3', '1');

commit ;

insert into follow_tbl(
	topic_id, client_id)
	values ('1', '1');
insert into follow_tbl(
	topic_id, client_id)
	values ('2', '2');
insert into follow_tbl(
	topic_id, client_id)
	values ('3', '3');
commit ;

insert into idea_cate_tbl(
	cate_id, idea_id)
	values ('1', '1');

insert into idea_cate_tbl(
	cate_id, idea_id)
	values ('1', '2');

insert into idea_cate_tbl(
	cate_id, idea_id)
	values ('2', '3');

commit;
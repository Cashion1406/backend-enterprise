INSERT INTO department_tbl(
	 id,department_info, is_deleted, depart_name)
	VALUES ( '1','Learning about computer', 'false', 'Computer Science');

INSERT INTO department_tbl(
	 id,department_info, is_deleted, depart_name)
	VALUES ( '2','Learning about graphic', 'false', 'Graphic Design');

INSERT INTO department_tbl(
	 id,department_info, is_deleted, depart_name)
	VALUES ( '3','Learning about money', 'false', 'Business');

commit ;

INSERT INTO client_tbl(
	id, client_age, client_info, client_firstname, client_is_deleted, client_lastname, client_pronoun, client_role, department_id)
	VALUES ('1', '21', 'STudent', 'DOAN', 'FALSE', 'AN', 'HE_HIM','ROLE_USER' , '2');

INSERT INTO client_tbl(
	id, client_age, client_info, client_firstname, client_is_deleted, client_lastname, client_pronoun, client_role, department_id)
	VALUES ('2', '21', 'STudent', 'NGUYEN', 'FALSE', 'LAM', 'SHE_HER','ROLE_ADMIN' , '1');

INSERT INTO client_tbl(
	id, client_age, client_info, client_firstname, client_is_deleted, client_lastname, client_pronoun, client_role, department_id)
	VALUES ('3', '21', 'STudent', 'NGUYEN', 'FALSE', 'MINH', 'HE_HIM','ROLE_MOD' , '3');

commit;

INSERT INTO cate_tbl(
	id, cate_name)
VALUES ('1', 'Busines');

INSERT INTO cate_tbl(
	id, cate_name)
VALUES ('2', 'IT');

INSERT INTO cate_tbl(
	id, cate_name)
VALUES ('3', 'ART');

commit;

INSERT INTO topic_tbl(
	id, topic_final_date, topic_closure_date, is_deleted, modify_date, topic_name)
	VALUES ('1', '02/20/2023', '03/20/2023', 'FALSE', '02/20/2023', 'Create Spring Boot micro services');

INSERT INTO topic_tbl(
	id, topic_final_date, topic_closure_date, is_deleted, modify_date, topic_name)
	VALUES ('2', '04/20/2023', '07/20/2023', 'FALSE', '02/20/2023', 'Create cross-platform using Flutter');

INSERT INTO topic_tbl(
	id, topic_final_date, topic_closure_date, is_deleted, modify_date, topic_name)
	VALUES ('3', '10/20/2023', '15/20/2023', 'FALSE', '02/20/2023', 'Deploy handwritten OCR');
commit;

INSERT INTO idea_tbl(
	id, attached_path, idea_body, date, modify_date, idea_title, client_id, topic_id)
	VALUES ('1', '/download/file', 'Just fixed','02/20/2023', '02/20/2023', 'Fixing flutter', '1','2');
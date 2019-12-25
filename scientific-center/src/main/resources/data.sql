insert into magazine(issn, membership_fee_type, title)
	values ( '12345678', 'READER_FEE', 'magazine1');
insert into magazine(issn, membership_fee_type, title)
	values ( '87654321', 'AUTHOR_FEE', 'magazine2');

insert into editor(chief_editor, first_name, last_name, magazine_id)
	values (true, 'Bob', 'B', 1);
insert into editor(chief_editor, first_name, last_name, magazine_id)
	values (false, 'Alice', 'A', 1);
insert into editor(chief_editor, first_name, last_name, magazine_id)
	values (true, 'John', 'D', 2);

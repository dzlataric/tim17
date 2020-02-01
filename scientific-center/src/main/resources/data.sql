insert into magazine(issn, membership_fee_type, title)
values ('12345678', 'READER_FEE', 'magazine1');
insert into magazine(issn, membership_fee_type, title)
values ('87654321', 'AUTHOR_FEE', 'magazine2');
insert into magazine(issn, membership_fee_type, title)
values ('12341234', 'READER_FEE', 'magazine3');
insert into magazine(issn, membership_fee_type, title)
values ('56785678', 'AUTHOR_FEE', 'magazine4');

insert into area_of_science(name)
values ('area1');
insert into area_of_science(name)
values ('area2');
insert into area_of_science(name)
values ('area3');
insert into area_of_science(name)
values ('area4');
insert into area_of_science(name)
values ('area5');
insert into area_of_science(name)
values ('area6');
insert into area_of_science(name)
values ('area7');

insert into editor(chief_editor, first_name, last_name, area_of_science_id, magazine_id)
values (true, 'Bob', 'B', null, 1);
insert into editor(chief_editor, first_name, last_name, area_of_science_id, magazine_id)
values (false, 'Alice', 'A', 2, 1);
insert into editor(chief_editor, first_name, last_name, area_of_science_id, magazine_id)
values (true, 'John', 'D', 4, 2);
insert into editor(chief_editor, first_name, last_name, area_of_science_id, magazine_id)
values (true, 'Chief', 'C', null, 3);
insert into editor(chief_editor, first_name, last_name, area_of_science_id, magazine_id)
values (false, 'Dummy', 'D', null, 3);
insert into editor(chief_editor, first_name, last_name, area_of_science_id, magazine_id)
values (false, 'Editor', 'E', 5, 3);
insert into editor(chief_editor, first_name, last_name, area_of_science_id, magazine_id)
values (true, 'First', 'F', 6, 4);

insert into magazine_area_of_science(magazine_id, area_of_science_id)
values (1, 1);
insert into magazine_area_of_science(magazine_id, area_of_science_id)
values (1, 2);
insert into magazine_area_of_science(magazine_id, area_of_science_id)
values (1, 3);
insert into magazine_area_of_science(magazine_id, area_of_science_id)
values (2, 4);
insert into magazine_area_of_science(magazine_id, area_of_science_id)
values (3, 5);
insert into magazine_area_of_science(magazine_id, area_of_science_id)
values (4, 6);

insert into payment_type(name) values ('PAYPAL');
insert into payment_type(name) values ('BITCOIN');
insert into payment_type(name) values ('CARD');

insert into research_paper(abstract, title)
values ('Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ex tortor, euismod sit amet facilisis id, eleifend in justo. Phasellus luctus aliquet finibus. Maecenas ut mi rhoncus augue consectetur accumsan. In volutpat tincidunt tempor. Cras eget venenatis ex. Donec tortor velit, porttitor eget commodo eget, ultrices sed metus. Praesent maximus laoreet metus, pharetra semper dolor tincidunt et.',
        'Scientific researche paper 1');
insert into research_paper(abstract, title)
values ('Sed tincidunt elit ut arcu hendrerit euismod. Etiam porttitor risus in viverra vestibulum. Curabitur pulvinar ante eget metus maximus, at varius sem accumsan. Duis vel ultricies sapien. Duis in orci ut neque bibendum hendrerit non eget magna. Mauris condimentum efficitur quam eu sagittis. Integer sed massa volutpat, faucibus ipsum et, rutrum velit. Nullam sit amet dignissim lacus. Integer eu condimentum elit, in fringilla metus. Donec luctus placerat leo quis tristique. Sed ac augue libero.',
        'Scientific researche paper 2');
insert into research_paper(abstract, title)
values ('In hac habitasse platea dictumst. Donec non urna nec risus auctor scelerisque. Maecenas bibendum justo id viverra consequat. Nam nec mi eu est vestibulum tincidunt at in sapien. Sed scelerisque metus sit amet molestie ultricies. Integer sagittis metus nisl, vel semper dui pretium vel. Vivamus odio tellus, congue auctor vulputate at, facilisis vel orci. Integer mattis vestibulum sollicitudin. Duis bibendum pharetra urna, efficitur faucibus magna ullamcorper ultricies. Mauris semper justo pulvinar convallis consectetur.',
        'Scientific researche paper 3');

insert into magazine_research_paper(magazine_id, research_paper_id)
values (1, 1);
insert into magazine_research_paper(magazine_id, research_paper_id)
values (1, 2);
insert into magazine_research_paper(magazine_id, research_paper_id)
values (1, 3);

insert into magazine_payment_type(magazine_id, payment_type_id)
values (1, 1);
insert into magazine_payment_type(magazine_id, payment_type_id)
values (1, 2);
insert into magazine_payment_type(magazine_id, payment_type_id)
values (1, 3);

insert into author(city, country, email_address, first_name, last_name, main_author, research_paper_id)
values ('Novi Sad', 'Serbia', 'karol@nonexisting.com', 'Karol', 'Fischer', true, 1);
insert into author(city, country, email_address, first_name, last_name, main_author, research_paper_id)
values ('Novi Sad', 'Serbia', 'emily@nonexisting.com', 'Emily', 'Arnold', true, 2);
insert into author(city, country, email_address, first_name, last_name, main_author, research_paper_id)
values ('Novi Sad', 'Serbia', 'joey@nonexisting.com', 'Joey', 'Robertson', true, 3);


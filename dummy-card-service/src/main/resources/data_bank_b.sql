insert into client(id, name)
values ('3b0d80ab-e2b7-43de-b84f-7bd0ecac5566', 'Tamara Topalski');
insert into client(id, name)
values ('3b0d80ab-e2b7-43de-b84f-7bd0ecac5577', 'Nikolina Nikolic');
insert into client(id, name)
values ('3b0d80ab-e2b7-43de-b84f-7bd0ecac6655', 'Jelena Milanovic');
insert into client(id, name)
values ('3b0d80ab-e2b7-43de-b84f-7bd0ecac6644', 'Dragana Draganovic');

insert into account(accountNumber, cardPAN, chn, cvv, cardValidThru, balance, client_id)
values ('4343-3535-3535-3535-5', '4355-3535-3535-3535', 'Tamara Topalski', '123', '2020-6-26', 2000, '3b0d80ab-e2b7-43de-b84f-7bd0ecac5566');
insert into account(accountNumber, cardPAN, chn, cvv, cardValidThru, balance, client_id)
values ('4343-3636-3636-3636-5', '4355-3636-3636-3636', 'Nikolina Nikolic', '123', '2020-6-26', 1000, '3b0d80ab-e2b7-43de-b84f-7bd0ecac5577');
insert into account(accountNumber, cardPAN, chn, cvv, cardValidThru, balance, client_id)
values ('4343-3737-3737-3737-5', '4355-3737-3737-3737', 'Jelena Milanovic', '123', '2020-6-26', 500, '3b0d80ab-e2b7-43de-b84f-7bd0ecac6655');
insert into account(accountNumber, cardPAN, chn, cvv, cardValidThru, balance, client_id)
values ('4343-3838-3838-3838-5', '4355-3838-3838-3838', 'Dragana Draganovic', '123', '2020-6-26', 0, '3b0d80ab-e2b7-43de-b84f-7bd0ecac6644');
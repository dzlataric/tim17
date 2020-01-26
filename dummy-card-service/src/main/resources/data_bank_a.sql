insert into client(id, name)
values ('3b0d80ab-e2b7-43de-b84f-7bd0ecac4231', 'Petar Petrovic');
insert into client(id, name)
values ('3b0d80ab-e2b7-43de-b84f-7bd0ecac4321', 'Zika Zivanovic');
insert into client(id, name)
values ('3b0d80ab-e2b7-43de-b84f-7bd0ecac4341', 'Djura Djurdjev');
insert into client(id, name)
values ('3b0d80ab-e2b7-43de-b84f-7bd0ecac4111', 'Marko Markovic');

insert into account(accountNumber, cardPAN, chn, cvv, cardValidThru, balance, client_id)
values ('3535-3535-3535-3535-1', '3736-3535-3535-3535', 'Petar Petrovic', '123', '12/12/2020', 2000, '3b0d80ab-e2b7-43de-b84f-7bd0ecac4231');
insert into account(accountNumber, cardPAN, chn, cvv, cardValidThru, balance, client_id)
values ('3636-3636-3636-3636-1', '3736-3636-3636-3636', 'Zika Zivanovic', '123', '12/12/2020', 1000, '3b0d80ab-e2b7-43de-b84f-7bd0ecac4321');
insert into account(accountNumber, cardPAN, chn, cvv, cardValidThru, balance, client_id)
values ('3737-3737-3737-3737-1', '3736-3737-3737-3737', 'Djura Djurdjev', '123', '12/12/2020', 500, '3b0d80ab-e2b7-43de-b84f-7bd0ecac4341');
insert into account(accountNumber, cardPAN, chn, cvv, cardValidThru, balance, client_id)
values ('3838-3838-3838-3838-1', '3736-3838-3838-3838', 'Marko Markovic', '123', '12/12/2020', 0, '3b0d80ab-e2b7-43de-b84f-7bd0ecac4111');
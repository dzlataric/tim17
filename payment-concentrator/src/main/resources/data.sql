-- drop and create need to be done automatically
-- otherwise payment_types won't be dropped
-- and then merchants won't be dropped this will
-- result with exception primary key violation h2

DROP TABLE payment_types;
CREATE TABLE PUBLIC.PAYMENT_TYPES
(MERCHANT_ID VARCHAR NOT NULL, PAYMENT_TYPE VARCHAR NOT NULL);

insert into merchant(id, bank_id, name, password)
values('3b0d80ab-e2b7-43de-b84f-7bd0ecac4231', '12345', 'Petar Petrovic', 'p123' );


insert into payment_types(merchant_id, payment_type)
values('3b0d80ab-e2b7-43de-b84f-7bd0ecac4231', 'PAYPAL');
insert into payment_types(merchant_id, payment_type)
values('3b0d80ab-e2b7-43de-b84f-7bd0ecac4231', 'CARD');
insert into payment_types(merchant_id, payment_type)
values('3b0d80ab-e2b7-43de-b84f-7bd0ecac4231', 'CRYPTO');

insert into bank(id, name, payment_url)
values('12345', 'BANK_A', 'https://localhost:8080');
insert into bank(id, name, payment_url)
values('54321', 'BANK_B', 'https://localhost:8099');


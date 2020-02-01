
BANK_A
-Dserver.port=8080 -Dspring.application.name=BANK_A -Dspring.datasource.data=classpath:data_bank_a.sql -Dspring.datasource.url=jdbc:h2:~/bank-A-db -Dbank.iin=3736

BANK_B
-Dserver.port=8099 -Dspring.application.name=BANK_B -Dspring.datasource.data=classpath:data_bank_b.sql -Dspring.datasource.url=jdbc:h2:~/bank-B-db -Dbank.iin=4355
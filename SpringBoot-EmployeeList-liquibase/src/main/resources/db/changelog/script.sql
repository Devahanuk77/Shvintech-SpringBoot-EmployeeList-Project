--liquibase formatted sql
--DO NOT DELETE ABOVE LINE. IT IS NEEDED BY LIQUIBASE.
--changeset devendra.pasagodgula:001 failOnError:true splitStatements:false logicalFilePath:script.sql


CREATE TABLE employee_data
(
    id SERIAL,
    name VARCHAR(50) NOT NULL,
    role VARCHAR(50) NOT NULL,
    age INTEGER NOT NULL,
    salary DOUBLE NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    CONSTRAINT employee_data_pkey PRIMARY KEY (id)
)
	--changeset devendra.pasagodgula:002 addColumn failOnError:true splitStatements:false logicalFilePath:script.sql
ALTER TABLE demo_liquibase.employee_data ADD  address VARCHAR(50) NOT NULL; 


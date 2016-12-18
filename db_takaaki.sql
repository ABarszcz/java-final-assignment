USE gc200292749;
/* DROP TABLES */
DROP TABLE IF EXISTS SALES;
DROP TABLE IF EXISTS EMPLOYEE;
DROP TABLE IF EXISTS CUSTOMER;
DROP TABLE IF EXISTS PRODUCT;
DROP TABLE IF EXISTS MANUFACTURER;
DROP TABLE IF EXISTS LOGIN;

/* Employee Table */
CREATE TABLE EMPLOYEE(EMPID INT NOT NULL auto_increment,
					  FNAME VARCHAR(100) NOT NULL,
                      LNAME VARCHAR(100) NOT NULL,
                      GENDER VARCHAR(10) NOT NULL,
                      ADDRESS VARCHAR(100) NOT NULL,
                      CITY VARCHAR(100) NOT NULL,
                      PROVINCE VARCHAR(50) NOT NULL,
                      PHONENUM VARCHAR(25) NOT NULL,
                      DEPT VARCHAR(100) NOT NULL,
                      DEPTPOSITION VARCHAR(100) NOT NULL,
                      SSN VARCHAR(100) NOT NULL,
                      BIRTHYEAR int not null,
                      BIRTHMONTH INT NOT NULL,
                      BIRTHDATE INT NOT NULL,
                      HOURLY DEC(10,2),
                      SALARY DEC(10,2),
                      COMM DEC(50,2),                      
                      PRIMARY KEY(EMPID));
                      select * from MANUFACTURER;
/* Customer Table */
CREATE TABLE CUSTOMER(CUSID int NOT NULL auto_increment,
					   FNAME VARCHAR(100) NOT NULL,
                       LNAME VARCHAR(100) NOT NULL,
                       GENDER VARCHAR(10) NOT NULL,
                       ADDRESS VARCHAR(100) NOT NULL,
                       CITY VARCHAR(100) NOT NULL,
                       PROVINCE VARCHAR(50) NOT NULL,
                       PHONENUM VARCHAR(25) NOT NULL,
                       BIRTHDATE int NOT NULL,
                       BIRTHMONTH INT NOT NULL,
                       BIRTHYEAR INT NOT NULL,
                       PRIMARY KEY(CUSID));
                       
/* Manufacturer Table */
CREATE TABLE MANUFACTURER(
							MFACTID INT NOT NULL auto_increment,
						 MFACTNAME VARCHAR(100) NOT NULL,
						 ADDRESS VARCHAR(100) NOT NULL,
                         CITY VARCHAR(100) NOT NULL,
                         PROVINCE VARCHAR(50),
                         PHONENUM VARCHAR(50),
                         PRIMARY KEY(MFACTID));
                        
                      
/*Product Table */
CREATE TABLE PRODUCT(
					 PRODID INT NOT NULL auto_increment,
					 PRODUCTNAME VARCHAR(100) NOT NULL,
                     PRICE DOUBLE NOT NULL,
                     DISCOUNT DOUBLE NOT NULL,
                     MFACTNAME VARCHAR(100),
                     PRIMARY KEY(PRODID));
                     
/* Sales Table */
CREATE TABLE SALES(SALEID INT NOT NULL auto_increment,
				   PRODUCT VARCHAR(100) NOT NULL,
                   CUSTOMER VARCHAR(100) NOT NULL,
                   EMPLOYEE VARCHAR(100) NOT NULL,
                   COMM DECIMAL(10,2) NOT NULL,
                   PRIMARY KEY(SALEID)
);
                        
/* Login Table */
CREATE TABLE LOGIN(ID INT NOT NULL AUTO_INCREMENT,
				   USERNAME VARCHAR(100),
				   PASSWORD char(128),
                   ADMIN BOOLEAN,
                   PRIMARY KEY(ID));
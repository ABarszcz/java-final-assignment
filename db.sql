USE WORLD
/* DROP TABLES */
DROP TABLE SALES;
DROP TABLE EMPLOYEE;
DROP TABLE CUSTOMER;
DROP TABLE PRODUCT;
DROP TABLE MANUFACTURER;
DROP TABLE LOGIN;

/* Employee Table */
CREATE TABLE EMPLOYEE(EMPID INT NOT NULL,
					  FNAME VARCHAR(50) NOT NULL,
                      LNAME VARCHAR(50) NOT NULL,
                      GENDER VARCHAR(6) NOT NULL,
                      ADDRESS VARCHAR(100) NOT NULL,
                      CITY VARCHAR(50) NOT NULL,
                      PROVINCE VARCHAR(10) NOT NULL,
                      PHONENUM VARCHAR(25) NOT NULL,
                      DEPT VARCHAR(50) NOT NULL,
                      DEPTPOSITION VARCHAR(50) NOT NULL,
                      SSN DOUBLE NOT NULL,
                      BIRTHDATE DATE NOT NULL,
                      HOURLY DEC(10,2),
                      SALARY DEC(10,2),
                      SALES DEC(50,2),
                      COMM DEC(50,2),                      
                      PRIMARY KEY(EMPID));
                      
/* Customer Table */
CREATE TABLE CUSTOMER(CUSID INT NOT NULL auto_increment,
					   FNAME VARCHAR(50) NOT NULL,
                       LNAME VARCHAR(50) NOT NULL,
                       GENDER VARCHAR(6) NOT NULL,
                       ADDRESS VARCHAR(100) NOT NULL,
                       CITY VARCHAR(50) NOT NULL,
                       PROVINCE VARCHAR(10) NOT NULL,
                       PHONENUM VARCHAR(25) NOT NULL,
                       BIRTHDATE DATE NOT NULL,
                       PRIMARY KEY(CUSID));
                       
/* Manufacturer Table */
CREATE TABLE MANUFACTURER(
							MFACTID INT NOT NULL auto_increment
						 MFACRNAME VARCHAR(100) NOT NULL,
						 ADDRESS VARCHAR(100) NOT NULL,
                         CITY VARCHAR(100) NOT NULL,
                         PROVINCE VARCHAR(50),
                         PHONENUM VARCHAR(50),
                         PRIMARY KEY(MFACTID));
                        
                      
/*Product Table */
CREATE TABLE PRODUCT(
					 PRODUCTNAME VARCHAR(100) NOT NULL,
                     PRICE DOUBLE NOT NULL,
                     DISCOUNT DOUBLE NOT NULL,
                     MFACTNAME VARCHAR(100),
                     PRIMARY KEY(PRODUCTNAME),
                     FOREIGN KEY(MFACTNAME)
						REFERENCES MANUFACTURER(PRODUCTNAME));
                     
/* Sales Table */
CREATE TABLE SALES(SALEID INT NOT NULL,
				   PRODID INT NOT NULL,
                   CUSID INT NOT NULL,
                   EMPID INT NOT NULL,
                   SALEDATE DATE NOT NULL,
                   PRIMARY KEY(SALEID),
                   FOREIGN KEY(PRODID)
						REFERENCES PRODUCT(PRODID),
				   FOREIGN KEY(CUSID) 
						REFERENCES CUSTOMER(CUSID),
                   FOREIGN KEY(EMPID)
						REFERENCES EMPLOYEE(EMPID));
                        
/* Login Table */
CREATE TABLE LOGIN(ID INT NOT NULL AUTO_INCREMENT,
				   USERNAME VARCHAR(100),
				   PASSWORD char(128),
                   ADMIN BOOLEAN,
                   PRIMARY KEY(ID));
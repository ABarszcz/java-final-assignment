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
CREATE TABLE CUSTOMER(CUSID INT NOT NULL,
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
CREATE TABLE MANUFACTURER(MANID INT NOT NULL,
						 NAME VARCHAR(100) NOT NULL,
						 ADDRESS VARCHAR(100) NOT NULL,
                         CITY VARCHAR(50) NOT NULL,
                         PROVINCE VARCHAR(10),
                         PHONENUM VARCHAR(10),
                         PRIMARY KEY(MANID));
                      
/*Product Table */
CREATE TABLE PRODUCT(PRODID INT NOT NULL,
					 NAME VARCHAR(50) NOT NULL,
                     PRICE DEC(30,2) NOT NULL,
                     DISCOUNT DEC(30,2) NOT NULL,
                     MANID INT,
                     PRIMARY KEY(PRODID),
                     FOREIGN KEY(MANID)
						REFERENCES MANUFACTURER(MANID));
                     
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
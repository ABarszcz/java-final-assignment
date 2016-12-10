USE WORLD;

DROP TABLE EMPLOYEE;
DROP TABLE CUSTOMER;
DROP TABLE PRODUCT;
DROP TABLE MANUFACTURER;
DROP TABLE SALES;
DROP TABLE LOGIN;


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
                      PRIMARY KEY(EMPID));
                      
-- INSERT INTO EMPLOYEE VALUES(0001, 'Kayla', 'Wiest', 'FEMALE', '123 Help me stree', 'city', 'ont','906-838-9990', 'IT', 'Tech', 9808776665, '1990-11-19');
-- SELECT * FROM EMPLOYEE

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
                       
-- INSERT INTO CUSTOMER VALUES(001, 'k','w','male','123 adsad', 'newmarket', 'ont', '905-444-4444', '19901119');                    
-- SELECT * FROM CUSTOMER;     

CREATE TABLE MANUFACTURER(MANID INT NOT NULL,
						 NAME VARCHAR(100) NOT NULL,
						 ADDRESS VARCHAR(100) NOT NULL,
                         CITY VARCHAR(50) NOT NULL,
                         PROVINCE VARCHAR(10),
                         PHONENUM VARCHAR(10),
                         PRIMARY KEY(MANID));
                      
-- INSERT INTO MANUFACTURER VALUES(1,'help me inc', '123 addsres', 'barrie', 'ont', '9084443344');
-- SELECT * FROM MANUFACTURER;  

CREATE TABLE PRODUCT(PRODID INT NOT NULL,
					 NAME VARCHAR(50) NOT NULL,
                     PRICE DEC(30,2) NOT NULL,
                     DISCOUNT DEC(30,2) NOT NULL,
                     MANID INT,
                     PRIMARY KEY(PRODID),
                     FOREIGN KEY(MANID)
						REFERENCES MANUFACTURER(MANID));
                     
-- INSERT INTO PRODUCT VALUES(001, 'name', 33.99, 2.99, 1);
-- SELECT * FROM PRODUCT

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
                        
-- INSERT INTO SALES VALUES(101, 1, 1, 1, '20161119');                    
-- SELECT * FROM SALES;    

CREATE TABLE LOGIN(ID INT NOT NULL AUTO_INCREMENT,
				   USERNAME VARCHAR(100),
				   PASSWORD char(128),
                   ADMIN BOOLEAN,
                   PRIMARY KEY(ID));
   
-- INSERT INTO LOGIN(username, password, admin) VALUES('kayla', 'asdasda', false);   

                   
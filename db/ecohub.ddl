-- DROP

DROP TABLE overtime;
DROP TABLE employee_job_history;
DROP TABLE day_off;
DROP TABLE employee;
DROP TABLE manager;
DROP TABLE job;
DROP TABLE charity_event;
DROP TABLE preventive_measure;
DROP TABLE head_leader;
DROP TABLE department_disaster;
DROP TABLE disaster;
DROP TABLE department;
DROP TABLE location;



-- CREATE
CREATE TABLE location (
    location_id NUMBER PRIMARY KEY,
    address VARCHAR2(128),
    postal_code VARCHAR2(32),
    city VARCHAR2(64),
    country VARCHAR2(2)
);

CREATE TABLE department (
    department_id NUMBER PRIMARY KEY,
    department_name VARCHAR2(100),
    phone_number VARCHAR2(32),
    location_id NUMBER,
    FOREIGN KEY (location_id) REFERENCES location(location_id)
);

CREATE TABLE disaster (
    disaster_id NUMBER PRIMARY KEY,
    disaster_type VARCHAR2(128),
    casualties NUMBER,
    total_cost NUMBER(10,2)
);

CREATE TABLE department_disaster (
    department_id NUMBER, 
    disaster_id NUMBER,
    common_description VARCHAR2(200),
    CONSTRAINT department_disaster_pk PRIMARY KEY (department_id, disaster_id),
    FOREIGN KEY (department_id) REFERENCES department(department_id),
    FOREIGN KEY (disaster_id) REFERENCES disaster(disaster_id)
);

CREATE TABLE head_leader (
    leader_id NUMBER PRIMARY KEY,
    first_name VARCHAR(128),
    last_name VARCHAR(128)
);

CREATE TABLE preventive_measure (
    measure_id NUMBER PRIMARY KEY,
    head_leader_id NUMBER,
    department_id NUMBER,
    FOREIGN KEY (head_leader_id) REFERENCES head_leader(leader_id),
    FOREIGN KEY (department_id) REFERENCES department(department_id) 
);

CREATE TABLE charity_event (
    event_id NUMBER PRIMARY KEY,
    head_leader_id NUMBER,
    department_id NUMBER,
    funds_raised NUMBER(10,2),
    FOREIGN KEY (head_leader_id) REFERENCES head_leader(leader_id),
    FOREIGN KEY (department_id) REFERENCES department(department_id) 
);

CREATE TABLE job (
    job_id NUMBER PRIMARY KEY,
    job_type varchar(32),
    min_salary NUMBER(10,2),
    max_salary NUMBER(10,2)
);

CREATE TABLE manager (
    manager_id NUMBER PRIMARY KEY,
    first_name VARCHAR(64),
    last_name VARCHAR(64)
);

CREATE TABLE employee (
    employee_id NUMBER PRIMARY KEY,
    department_id NUMBER,
    manager_id NUMBER,
    first_name VARCHAR2(64),
    last_name VARCHAR2(64),
    gender VARCHAR2(64),
    age NUMBER,
    email VARCHAR2(64),
    hire_date VARCHAR2(64),
    phone_number VARCHAR2(64),
    FOREIGN KEY (department_id) REFERENCES department(department_id),
    FOREIGN KEY (manager_id) REFERENCES manager(manager_id)
);

CREATE TABLE day_off (
    day_off_id NUMBER PRIMARY KEY,
    employee_id NUMBER,
    number_of_days NUMBER,
    reason VARCHAR2(64),
    start_date VARCHAR2(64),
    end_date VARCHAR2(64),
    FOREIGN KEY (employee_id) REFERENCES employee(employee_id)
);

CREATE TABLE employee_job_history (
    job_history_id NUMBER PRIMARY KEY,
    job_id NUMBER,
    employee_id NUMBER,
    salary NUMBER(10,2),
    bonus NUMBER(10,2),
    start_date VARCHAR2(64),
    end_date VARCHAR2(64),
    FOREIGN KEY (employee_id) REFERENCES employee(employee_id),
    FOREIGN KEY (job_id) REFERENCES job(job_id)
);

CREATE TABLE overtime (
    overtime_id NUMBER PRIMARY KEY,
    job_history_id NUMBER,
    start_date VARCHAR2(64),
    end_date VARCHAR2(64),
    FOREIGN KEY (job_history_id) REFERENCES employee_job_history(job_history_id)
);



-- INSERT
INSERT INTO location(location_id, address, postal_code, city, country) VALUES (1, 'Krasnova', '65059', 'Odessa', 'UA');

INSERT INTO department(department_id, department_name, phone_number, location_id) VALUES (1, 'Mnistry of Defense', '725-160-37', 1);

INSERT INTO disaster(disaster_id,disaster_type,casualties,total_cost) VALUES (12, 'Earthquake', '65059', '1500.60');

INSERT INTO department_disaster(department_id,disaster_id,common_description) VALUES (1, 12, 'some common description');

INSERT INTO head_leader( leader_id,first_name,last_name) VALUES (1,'Max', 'Pax');

INSERT INTO preventive_measure(measure_id,head_leader_id,department_id) VALUES (1,1,1);

INSERT INTO charity_event(event_id,head_leader_id,department_id,funds_raised) VALUES (1,1,1,6500.50);

INSERT INTO job(job_id,job_type,min_salary,max_salary) VALUES (1,'Java programmer',760.3,880.6);

INSERT INTO manager (manager_id,first_name,last_name) VALUES (1,'John','Doe');

INSERT INTO employee(employee_id,department_id,manager_id,first_name,last_name,gender,age,email,hire_date,phone_number) 
VALUES (1,1,1,'Tomas','Richardson','male',25,'TomasR@gmail.com','12.12.2023','+380 457 754');

INSERT INTO day_off (day_off_id,employee_id,number_of_days,reason,start_date,end_date) VALUES(1,1,7,'Car Accident','20.05.2005','27.05.2005');

INSERT INTO employee_job_history(job_history_id,job_id,employee_id,salary,bonus,start_date,end_date) VALUES (1,1,1,4000.20,500.1,'06.09.2022','07.04.2024');

INSERT INTO overtime(overtime_id,job_history_id,start_date,end_date) VALUES (1,1,'05.06.2007',' 08.06.2007');


-- SELECT

SELECT e.first_name, e.last_name, e.age, e.email, dep.phone_number, loc.city, loc.country, jh.salary, j.job_type FROM employee e 
JOIN department dep on dep.department_id = e.department_id
JOIN location loc on loc.location_id = dep.location_id
JOIN employee_job_history jh on jh.employee_id = e.employee_id
JOIN job j on j.job_id = jh.job_id;


SELECT d.department_name, d.phone_number, l.city, l.country FROM department d
JOIN location l ON l.location_id = d.location_id;

SELECT * FROM disaster;

SELECT * FROM job;

SELECT * FROM manager;

SELECT * FROM employee;

SELECT * FROM overtime;

SELECT * FROM employee_job_history;

SELECT * FROM day_off;




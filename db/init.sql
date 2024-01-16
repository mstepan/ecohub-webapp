-- create database
CREATE DATABASE ecohub;

-- connect to DB
\c ecohub;

-- drop all tables
DROP TABLE IF EXISTS overtime;
DROP TABLE IF EXISTS employee_job_history;
DROP TABLE IF EXISTS day_off;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS manager;
DROP TABLE IF EXISTS job;
DROP TABLE IF EXISTS charity_event;
DROP TABLE IF EXISTS preventive_measure;
DROP TABLE IF EXISTS head_leader;
DROP TABLE IF EXISTS department_disaster;
DROP TABLE IF EXISTS disaster;
DROP TABLE IF EXISTS department;
DROP TABLE IF EXISTS location;


-- create all tables
CREATE TABLE location (
    location_id SERIAL PRIMARY KEY,
    address VARCHAR(128),
    postal_code VARCHAR(32),
    city VARCHAR(64),
    country VARCHAR(2)
);

CREATE TABLE department (
    department_id SERIAL PRIMARY KEY,
    department_name VARCHAR(100),
    phone_number VARCHAR(32),
    location_id INTEGER,
    FOREIGN KEY (location_id) REFERENCES location(location_id)
);

CREATE TABLE disaster (
    disaster_id SERIAL PRIMARY KEY,
    disaster_type VARCHAR(128),
    casualties INTEGER,
    total_cost NUMERIC(10,2)
);

CREATE TABLE department_disaster (
    dep_dis_id SERIAL PRIMARY KEY,
    department_id INTEGER,
    disaster_id INTEGER,
    common_description VARCHAR(200),
    CONSTRAINT department_disaster_unique UNIQUE (department_id, disaster_id),
    FOREIGN KEY (department_id) REFERENCES department(department_id),
    FOREIGN KEY (disaster_id) REFERENCES disaster(disaster_id)
);

CREATE TABLE head_leader (
    leader_id SERIAL PRIMARY KEY,
    first_name VARCHAR(128),
    last_name VARCHAR(128)
);

CREATE TABLE preventive_measure (
    measure_id SERIAL PRIMARY KEY,
    head_leader_id INTEGER,
    department_id INTEGER,
    FOREIGN KEY (head_leader_id) REFERENCES head_leader(leader_id),
    FOREIGN KEY (department_id) REFERENCES department(department_id)
);

CREATE TABLE charity_event (
    event_id SERIAL PRIMARY KEY,
    head_leader_id INTEGER,
    department_id INTEGER,
    funds_raised NUMERIC(10,2),
    FOREIGN KEY (head_leader_id) REFERENCES head_leader(leader_id),
    FOREIGN KEY (department_id) REFERENCES department(department_id)
);

CREATE TABLE job (
    job_id SERIAL PRIMARY KEY,
    job_type VARCHAR(32),
    min_salary NUMERIC(10,2),
    max_salary NUMERIC(10,2)
);

CREATE TABLE manager (
    manager_id SERIAL PRIMARY KEY,
    first_name VARCHAR(64),
    last_name VARCHAR(64)
);

CREATE TABLE employee (
    employee_id SERIAL PRIMARY KEY,
    department_id INTEGER,
    manager_id INTEGER,
    first_name VARCHAR(64),
    last_name VARCHAR(64),
    gender VARCHAR(64),
    age INTEGER,
    email VARCHAR(64),
    hire_date VARCHAR(64),
    phone_number VARCHAR(64),
    FOREIGN KEY (department_id) REFERENCES department(department_id),
    FOREIGN KEY (manager_id) REFERENCES manager(manager_id)
);

CREATE TABLE day_off (
    day_off_id SERIAL PRIMARY KEY,
    employee_id INTEGER,
    number_of_days INTEGER,
    reason VARCHAR(64),
    start_date VARCHAR(64),
    end_date VARCHAR(64),
    FOREIGN KEY (employee_id) REFERENCES employee(employee_id)
);

CREATE TABLE employee_job_history (
    job_history_id SERIAL PRIMARY KEY,
    job_id INTEGER,
    employee_id INTEGER,
    salary NUMERIC(10,2),
    bonus NUMERIC(10,2),
    start_date VARCHAR(64),
    end_date VARCHAR(64),
    FOREIGN KEY (employee_id) REFERENCES employee(employee_id),
    FOREIGN KEY (job_id) REFERENCES job(job_id)
);

CREATE TABLE overtime (
    overtime_id SERIAL PRIMARY KEY,
    job_history_id INTEGER,
    start_date VARCHAR(64),
    end_date VARCHAR(64),
    FOREIGN KEY (job_history_id) REFERENCES employee_job_history(job_history_id)
);

-- Insert some values

--INSERT INTO location(location_id, address, postal_code, city, country) VALUES (1, 'Krasnova', '65059', 'Odessa', 'UA');
--
--INSERT INTO department(department_id, department_name, phone_number, location_id) VALUES (1, 'Mnistry of Defense', '725-160-37', 1);
--
--INSERT INTO disaster(disaster_id,disaster_type,casualties,total_cost) VALUES (12, 'Earthquake', '65059', '1500.60');
--
--INSERT INTO department_disaster(department_id,disaster_id,common_description) VALUES (1, 12, 'some common description');
--
--INSERT INTO head_leader( leader_id,first_name,last_name) VALUES (1,'Max', 'Pax');
--
--INSERT INTO preventive_measure(measure_id,head_leader_id,department_id) VALUES (1,1,1);
--
--INSERT INTO charity_event(event_id,head_leader_id,department_id,funds_raised) VALUES (1,1,1,6500.50);
--
--INSERT INTO job(job_id,job_type,min_salary,max_salary) VALUES (1,'Java programmer',760.3,880.6);
--
--INSERT INTO manager (manager_id,first_name,last_name) VALUES (1,'John','Doe');
--
--INSERT INTO employee(employee_id,department_id,manager_id,first_name,last_name,gender,age,email,hire_date,phone_number)
--VALUES (1,1,1,'Tomas','Richardson','male',25,'TomasR@gmail.com','12.12.2023','+380 457 754');
--
--INSERT INTO day_off (day_off_id,employee_id,number_of_days,reason,start_date,end_date) VALUES(1,1,7,'Car Accident','20.05.2005','27.05.2005');
--
--INSERT INTO employee_job_history(job_history_id,job_id,employee_id,salary,bonus,start_date,end_date) VALUES (1,1,1,4000.20,500.1,'06.09.2022','07.04.2024');
--
--INSERT INTO overtime(overtime_id,job_history_id,start_date,end_date) VALUES (1,1,'05.06.2007',' 08.06.2007');

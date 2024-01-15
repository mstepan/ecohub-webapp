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




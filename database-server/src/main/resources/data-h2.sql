------------------ USER Data ------------------

-- Dummy data for User Table
-- Default password: 123456
INSERT INTO user (user_id, password, username, role) VALUES (100001,'$2a$10$B4FvjVc2oXqDTFdGGZjT5urzHS6TSdzrQ7p/vFi1rUuZgjXR4.m5K', 'gagan', 'Admin');
INSERT INTO user (user_id, password, username, role) VALUES (100002,'$2a$10$B4FvjVc2oXqDTFdGGZjT5urzHS6TSdzrQ7p/vFi1rUuZgjXR4.m5K', 'admin', 'Admin');
INSERT INTO user (user_id, password, username, role) VALUES (100003,'$2a$10$B4FvjVc2oXqDTFdGGZjT5urzHS6TSdzrQ7p/vFi1rUuZgjXR4.m5K', 'rhoda', 'User');
INSERT INTO user (user_id, password, username, role) VALUES (100004,'$2a$10$B4FvjVc2oXqDTFdGGZjT5urzHS6TSdzrQ7p/vFi1rUuZgjXR4.m5K', 'olive', 'User');
INSERT INTO user (user_id, password, username, role) VALUES (100005,'$2a$10$B4FvjVc2oXqDTFdGGZjT5urzHS6TSdzrQ7p/vFi1rUuZgjXR4.m5K', 'aida', 'User');
INSERT INTO user (user_id, password, username, role) VALUES (100006,'$2a$10$B4FvjVc2oXqDTFdGGZjT5urzHS6TSdzrQ7p/vFi1rUuZgjXR4.m5K', 'john', 'User');
-- MYSQL sequence
-- UPDATE user_id_sequence SET next_val = 100021;
-- H2 sequence
ALTER sequence user_id_sequence restart with 100007;

-- Dummy data for Address
INSERT INTO address (address_id, area, city, pincode, state) VALUES (100001,'Union Street','Mumbai','198106','Maharashtra');
INSERT INTO address (address_id, area, city, pincode, state) VALUES (100002,'Stonepot Road','Pune','307102','Maharashtra');
INSERT INTO address (address_id, area, city, pincode, state) VALUES (100003,'Adams Drive','Banglore','477002','Karnataka');
INSERT INTO address (address_id, area, city, pincode, state) VALUES (100004,'Illinois Avenue','Mumbai','597223','Maharashtra');
INSERT INTO address (address_id, area, city, pincode, state) VALUES (100005,'Hog Camp Road','Mumbai','660525','Maharashtra');
INSERT INTO address (address_id, area, city, pincode, state) VALUES (100006,'West Fork Drive','Pune','744113','Maharashtra');

-- Dummy data for User detail
INSERT INTO user_details (user_details_id, first_name, last_name, email_id, phone_no, security_question, security_answer, address_id) VALUES (100001, 'Gagandeep', 'Singh', 'singh.gagandeep3911@gmail.com', '8419969059', 'Default Question', 'answer', 100001);
INSERT INTO user_details (user_details_id, first_name, last_name, email_id, phone_no, security_question, security_answer, address_id) VALUES (100002, 'Stefani', 'Germanotta', 'stefani@mail.com', '9876543210', 'Default Question', 'answer', 100002);
INSERT INTO user_details (user_details_id, first_name, last_name, email_id, phone_no, security_question, security_answer, address_id) VALUES (100003, 'Rhoda', 'Report', 'rhoda@mail.com', '8877669059', 'Default Question', 'answer', 100003);
INSERT INTO user_details (user_details_id, first_name, last_name, email_id, phone_no, security_question, security_answer, address_id) VALUES (100004, 'Olive', 'Yew', 'olive@mail.com', '3443312345', 'Default Question', 'answer', 100004);
INSERT INTO user_details (user_details_id, first_name, last_name, email_id, phone_no, security_question, security_answer, address_id) VALUES (100005, 'Aida', 'Bugg', 'aida@mail.com', '7576788679', 'Default Question', 'answer', 100005);
INSERT INTO user_details (user_details_id, first_name, last_name, email_id, phone_no, security_question, security_answer, address_id) VALUES (100006, 'John', 'Doe', 'john@mail.com', '9966553322', 'Default Question', 'answer', 100006);

ALTER sequence address_id_sequence restart with 100007;

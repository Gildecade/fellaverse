-- admin
insert into fellaverse.admin(username, password, email, phone_number) values ('SuperAdmin', 'MY3PD7DfunexGNpnLSmFOw==', 'superadmin@admin.com', 1234567890);
insert into fellaverse.admin(username, password, email, phone_number) values ('ShopAdmin', 'MY3PD7DfunexGNpnLSmFOw==', 'shopadmin@admin.com', 1234567890);
insert into fellaverse.admin(username, password, email, phone_number) values ('WorkoutAdmin', 'MY3PD7DfunexGNpnLSmFOw==', 'workoutadmin@admin.com', 1234567890);
insert into fellaverse.admin(username, password, email, phone_number) values ('TweetAdmin', 'MY3PD7DfunexGNpnLSmFOw==', 'tweetadmin@admin.com', 1234567890);


-- role
insert into role (role_name, description) values ('SuperAdmin', 'Super administrators for all modules.');
insert into role (role_name, description) values ('ShopAdmin', 'Administrators for shop module. Able to crud all products.');
insert into role (role_name, description) values ('WorkoutAdmin', 'Administrators for schedule and record module. Able to crud all schedules and records.');
insert into role (role_name, description) values ('TweetAdmin', 'Administrators for tweet module. Able to crud all tweets and comments.');

-- admin role

-- functionality
insert into functionality (function_name, description) values ('select record', 'View workout records');
insert into functionality (function_name, description) values ('add record', 'Add workout records');
insert into functionality (function_name, description) values ('delete record', 'Delete workout records');
insert into functionality (function_name, description) values ('select exercise', 'View workout exercises');
insert into functionality (function_name, description) values ('select schedule', 'View workout schedules');
insert into functionality (function_name, description) values ('add schedule', 'Add workout schedules');
insert into functionality (function_name, description) values ('update schedule', 'Modify workout schedules');
insert into functionality (function_name, description) values ('delete schedule', 'Delete workout schedules');
insert into functionality (function_name, description) values ('select program', 'View workout programs');
insert into functionality (function_name, description) values ('add program', 'Add workout programs');
insert into functionality (function_name, description) values ('update program', 'Modify workout programs');
insert into functionality (function_name, description) values ('delete program', 'Delete workout programs');
insert into functionality (function_name, description) values ('select checkIn', 'View workout check in data');
insert into functionality (function_name, description) values ('add checkIn', 'Add workout check in data');
insert into functionality (function_name, description) values ('update checkIn', 'Modify workout check in data');
insert into functionality (function_name, description) values ('delete checkIn', 'Delete workout check in data');
insert into functionality (function_name, description) values ('add course', 'Add shop courses');
insert into functionality (function_name, description) values ('update course', 'Modify shop courses');
insert into functionality (function_name, description) values ('delete course', 'Delete shop courses');
insert into functionality (function_name, description) values ('buy', 'Buy something, at first only course');

-- user
insert into fellaverse.user(username, password, email, phone_number) values ('User01', 'MY3PD7DfunexGNpnLSmFOw==', 'user01@user.com', 1234567890);
insert into fellaverse.user(username, password, email, phone_number) values ('User02', 'MY3PD7DfunexGNpnLSmFOw==', 'user02@user.com', 1234567890);
insert into fellaverse.user(username, password, email, phone_number) values ('User03', 'MY3PD7DfunexGNpnLSmFOw==', 'user03@user.com', 1234567890);
insert into fellaverse.user(username, password, email, phone_number) values ('User04', 'MY3PD7DfunexGNpnLSmFOw==', 'user04@user.com', 1234567890);
insert into fellaverse.user(username, password, email, phone_number) values ('User05', 'MY3PD7DfunexGNpnLSmFOw==', 'user05@user.com', 1234567890);
insert into fellaverse.user(username, password, email, phone_number) values ('User06', 'MY3PD7DfunexGNpnLSmFOw==', 'user06@user.com', 1234567890);

-- user functions
insert into user_function (function_id, user_id) values (1, 1);
insert into user_function (function_id, user_id) values (2, 1);
insert into user_function (function_id, user_id) values (3, 1);
insert into user_function (function_id, user_id) values (4, 1);
insert into user_function (function_id, user_id) values (5, 1);
insert into user_function (function_id, user_id) values (6, 1);
insert into user_function (function_id, user_id) values (7, 1);
insert into user_function (function_id, user_id) values (8, 1);
insert into user_function (function_id, user_id) values (9, 1);
insert into user_function (function_id, user_id) values (10, 1);
insert into user_function (function_id, user_id) values (11, 1);
insert into user_function (function_id, user_id) values (12, 1);
insert into user_function (function_id, user_id) values (13, 1);
insert into user_function (function_id, user_id) values (14, 1);
insert into user_function (function_id, user_id) values (15, 1);
insert into user_function (function_id, user_id) values (16, 1);
insert into user_function (function_id, user_id) values (17, 1);
insert into user_function (function_id, user_id) values (18, 1);
insert into user_function (function_id, user_id) values (19, 1);
insert into user_function (function_id, user_id) values (20, 1);

insert into user_function (function_id, user_id) values (1, 2);
insert into user_function (function_id, user_id) values (2, 2);
insert into user_function (function_id, user_id) values (3, 2);
insert into user_function (function_id, user_id) values (4, 2);
insert into user_function (function_id, user_id) values (5, 2);
insert into user_function (function_id, user_id) values (6, 2);
insert into user_function (function_id, user_id) values (7, 2);
insert into user_function (function_id, user_id) values (8, 2);
insert into user_function (function_id, user_id) values (9, 2);
insert into user_function (function_id, user_id) values (10, 2);
insert into user_function (function_id, user_id) values (11, 2);
insert into user_function (function_id, user_id) values (12, 2);
insert into user_function (function_id, user_id) values (13, 2);
insert into user_function (function_id, user_id) values (14, 2);
insert into user_function (function_id, user_id) values (15, 2);
insert into user_function (function_id, user_id) values (16, 2);
insert into user_function (function_id, user_id) values (17, 2);
insert into user_function (function_id, user_id) values (18, 2);
insert into user_function (function_id, user_id) values (19, 2);
insert into user_function (function_id, user_id) values (20, 2);

insert into user_function (function_id, user_id) values (1, 3);
insert into user_function (function_id, user_id) values (2, 3);
insert into user_function (function_id, user_id) values (3, 3);
insert into user_function (function_id, user_id) values (4, 3);
insert into user_function (function_id, user_id) values (5, 3);
insert into user_function (function_id, user_id) values (6, 3);
insert into user_function (function_id, user_id) values (7, 3);
insert into user_function (function_id, user_id) values (8, 3);
insert into user_function (function_id, user_id) values (9, 3);
insert into user_function (function_id, user_id) values (10, 3);
insert into user_function (function_id, user_id) values (11, 3);
insert into user_function (function_id, user_id) values (12, 3);
insert into user_function (function_id, user_id) values (13, 3);
insert into user_function (function_id, user_id) values (14, 3);
insert into user_function (function_id, user_id) values (15, 3);
insert into user_function (function_id, user_id) values (16, 3);
insert into user_function (function_id, user_id) values (17, 3);
insert into user_function (function_id, user_id) values (18, 3);
insert into user_function (function_id, user_id) values (19, 3);
insert into user_function (function_id, user_id) values (20, 3);

insert into user_function (function_id, user_id) values (1, 4);
insert into user_function (function_id, user_id) values (2, 4);
insert into user_function (function_id, user_id) values (3, 4);
insert into user_function (function_id, user_id) values (4, 4);
insert into user_function (function_id, user_id) values (5, 4);
insert into user_function (function_id, user_id) values (6, 4);
insert into user_function (function_id, user_id) values (7, 4);
insert into user_function (function_id, user_id) values (8, 4);
insert into user_function (function_id, user_id) values (9, 4);
insert into user_function (function_id, user_id) values (10, 4);
insert into user_function (function_id, user_id) values (11, 4);
insert into user_function (function_id, user_id) values (12, 4);
insert into user_function (function_id, user_id) values (13, 4);
insert into user_function (function_id, user_id) values (14, 4);
insert into user_function (function_id, user_id) values (15, 4);
insert into user_function (function_id, user_id) values (16, 4);
insert into user_function (function_id, user_id) values (17, 4);
insert into user_function (function_id, user_id) values (18, 4);
insert into user_function (function_id, user_id) values (19, 4);
insert into user_function (function_id, user_id) values (20, 4);

insert into user_function (function_id, user_id) values (1, 5);
insert into user_function (function_id, user_id) values (2, 5);
insert into user_function (function_id, user_id) values (3, 5);
insert into user_function (function_id, user_id) values (4, 5);
insert into user_function (function_id, user_id) values (5, 5);
insert into user_function (function_id, user_id) values (6, 5);
insert into user_function (function_id, user_id) values (7, 5);
insert into user_function (function_id, user_id) values (8, 5);
insert into user_function (function_id, user_id) values (9, 5);
insert into user_function (function_id, user_id) values (10, 5);
insert into user_function (function_id, user_id) values (11, 5);
insert into user_function (function_id, user_id) values (12, 5);
insert into user_function (function_id, user_id) values (13, 5);
insert into user_function (function_id, user_id) values (14, 5);
insert into user_function (function_id, user_id) values (15, 5);
insert into user_function (function_id, user_id) values (16, 5);
insert into user_function (function_id, user_id) values (17, 5);
insert into user_function (function_id, user_id) values (18, 5);
insert into user_function (function_id, user_id) values (19, 5);
insert into user_function (function_id, user_id) values (20, 5);


-- product
insert into product (product_name, description, image_url, price, created_date_time, product_status) values ('p1', 'd1', 'https://storage.com', 4.2, curdate(), 1);
insert into product (product_name, description, image_url, price, created_date_time, product_status) values ('p2', 'd2', 'https://storage.com', 14.2, curdate(), 1);
insert into product (product_name, description, image_url, price, created_date_time, product_status) values ('p3', 'd3', 'https://storage.com', 42.2, curdate(), 1);

insert into course (video_url, id, user_id) values ('https://video.com', 1, 1);
insert into course (video_url, id, user_id) values ('https://video.com', 2, 1);

-- exercise
insert into exercise (exercise_name) values ('push up');
insert into exercise (exercise_name) values ('push down');
insert into exercise (exercise_name) values ('push left');
insert into exercise (exercise_name) values ('push right');

-- schedule
insert into schedule (schedule_name, workout_days, start_time, end_time, user_id) values ('s1',134,'2023-01-01', '2023-04-30', 1);
insert into schedule (schedule_name, workout_days, start_time, end_time, user_id) values ('s2',157,'2023-01-01', '2023-04-30', 1);
insert into schedule (schedule_name, workout_days, start_time, end_time, user_id) values ('s3',1234567,'2023-01-01', '2023-04-30', 1);

-- program
insert into program (program_name, schedule_id) values ('p1', 1);
insert into program (program_name, schedule_id) values ('p2', 2);
insert into program (program_name, schedule_id) values ('p3', 3);

-- program exercise

insert into program_exercise (exercise_id, program_id) values (1, 1);
insert into program_exercise (exercise_id, program_id) values (1, 2);
insert into program_exercise (exercise_id, program_id) values (2, 3);

-- record
# insert into record (create_tate_time, weights, quantity, num_of_sets, user_id, exercise_id) values ('2023-02-01',12,20,2,2,2);
# insert into record (create_tate_time, weights, quantity, num_of_sets, user_id, exercise_id) values ('2023-02-02',13,24,2,3,2);
# insert into record (create_tate_time, weights, quantity, num_of_sets, user_id, exercise_id) values ('2023-02-03',14,21,2,2,3);
# insert into record (create_tate_time, weights, quantity, num_of_sets, user_id, exercise_id) values ('2023-02-04',15,22,2,2,4);
# insert into record (create_tate_time, weights, quantity, num_of_sets, user_id, exercise_id) values ('2023-02-05',16,23,2,1,1);


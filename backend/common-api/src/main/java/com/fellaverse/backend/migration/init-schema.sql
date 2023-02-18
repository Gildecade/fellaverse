CREATE TABLE `admin`
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    username     VARCHAR(60)           NOT NULL,
    password     VARCHAR(60)           NOT NULL,
    email        VARCHAR(255)          NOT NULL,
    phone_number VARCHAR(60)           NOT NULL,
    CONSTRAINT pk_admin PRIMARY KEY (id)
);

CREATE TABLE admin_role
(
    admin_id BIGINT NOT NULL,
    role_id  BIGINT NOT NULL,
    CONSTRAINT pk_admin_role PRIMARY KEY (admin_id, role_id)
);

CREATE TABLE course
(
    id                BIGINT AUTO_INCREMENT NOT NULL,
    product_name      VARCHAR(60)           NOT NULL,
    `description`     VARCHAR(255)          NULL,
    image_url         VARCHAR(255)          NULL,
    price             FLOAT                 NOT NULL,
    created_date_time datetime              NOT NULL,
    video_url         VARCHAR(255)          NOT NULL,
    product_status    INT                   NOT NULL,
    user_id           BIGINT                NULL,
    CONSTRAINT pk_course PRIMARY KEY (id)
);

CREATE TABLE exercise
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    exercise_name VARCHAR(60)           NOT NULL,
    CONSTRAINT pk_exercise PRIMARY KEY (id)
);

CREATE TABLE program
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    program_name VARCHAR(60)           NOT NULL,
    CONSTRAINT pk_program PRIMARY KEY (id)
);

CREATE TABLE program_exercise
(
    exercise_id BIGINT NOT NULL,
    program_id  BIGINT NOT NULL,
    CONSTRAINT pk_program_exercise PRIMARY KEY (exercise_id, program_id)
);

CREATE TABLE `role`
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    role_name     VARCHAR(60)           NOT NULL,
    `description` VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE schedule
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    schedule_name VARCHAR(60)           NOT NULL,
    workout_days  INT                   NOT NULL,
    start_time    datetime              NOT NULL,
    end_time      datetime              NOT NULL,
    user_id       BIGINT                NULL,
    CONSTRAINT pk_schedule PRIMARY KEY (id)
);

CREATE TABLE user
(
    id           BIGINT AUTO_INCREMENT                                 NOT NULL,
    username     VARCHAR(60)                                           NOT NULL,
    password     VARCHAR(60)                                           NOT NULL,
    email        VARCHAR(255)                                          NOT NULL,
    phone_number VARCHAR(60)                                           NOT NULL,
    wallet       BIGINT                               DEFAULT 1000     NOT NULL,
    status       ENUM ('normal', 'locked', 'unknown') DEFAULT 'normal' NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE `admin`
    ADD CONSTRAINT uc_admin_username UNIQUE (username);

ALTER TABLE `role`
    ADD CONSTRAINT uc_role_description UNIQUE (`description`);

ALTER TABLE `role`
    ADD CONSTRAINT uc_role_role_name UNIQUE (role_name);

ALTER TABLE user
    ADD CONSTRAINT uc_user_username UNIQUE (username);

ALTER TABLE course
    ADD CONSTRAINT FK_COURSE_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE schedule
    ADD CONSTRAINT FK_SCHEDULE_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE admin_role
    ADD CONSTRAINT fk_admrol_on_admin FOREIGN KEY (admin_id) REFERENCES `admin` (id);

ALTER TABLE admin_role
    ADD CONSTRAINT fk_admrol_on_role FOREIGN KEY (role_id) REFERENCES `role` (id);

ALTER TABLE program_exercise
    ADD CONSTRAINT fk_proexe_on_exercise FOREIGN KEY (exercise_id) REFERENCES exercise (id);

ALTER TABLE program_exercise
    ADD CONSTRAINT fk_proexe_on_program FOREIGN KEY (program_id) REFERENCES program (id);
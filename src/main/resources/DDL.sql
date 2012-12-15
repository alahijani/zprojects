CREATE TABLE SEQUENCE
(
    SEQ_NAME varchar(50) PRIMARY KEY NOT NULL,
    SEQ_COUNT decimal(15)
)
CREATE TABLE PROJECTS
(
    ID varchar(255) PRIMARY KEY NOT NULL,
    CODE varchar(255),
    DESCRIPTION varchar(255),
    TITLE varchar(255)
)
CREATE TABLE TASKS
(
    ID varchar(255) PRIMARY KEY NOT NULL,
    DESCRIPTION varchar(255),
    TITLE varchar(255),
    PROJECT_ID varchar(255),
    FOREIGN KEY ( PROJECT_ID ) REFERENCES PROJECTS ( ID )
)
CREATE TABLE USERS
(
    ID varchar(255) PRIMARY KEY NOT NULL,
    FULLNAME varchar(255),
    USERNAME varchar(255) NOT NULL,
    PASSWORD varchar(255) NOT NULL,
    ADMIN smallint DEFAULT 0,
    ENABLED smallint DEFAULT 0
)

INSERT INTO USERS(ID, FULLNAME, USERNAME, PASSWORD, ADMIN, ENABLED) VALUES (0, 'Administrator', 'admin', 'admin', 1, 1);
INSERT INTO PROJECTS(ID, TITLE, CODE, DESCRIPTION) VALUES (0, 'Urban Velociraptor Training Project', 'uvelo',
  'Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.'
);
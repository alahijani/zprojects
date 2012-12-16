CREATE TABLE users 
  ( 
     id       VARCHAR(255) NOT NULL, 
     admin    SMALLINT DEFAULT 0, 
     enabled  SMALLINT DEFAULT 0, 
     fullname VARCHAR(255), 
     password VARCHAR(255) NOT NULL, 
     username VARCHAR(255) NOT NULL UNIQUE, 
     PRIMARY KEY (id) 
  ) 

CREATE TABLE projects 
  ( 
     id          VARCHAR(255) NOT NULL, 
     code        VARCHAR(255) NOT NULL UNIQUE, 
     description VARCHAR(255) NOT NULL, 
     title       VARCHAR(255) NOT NULL, 
     PRIMARY KEY (id) 
  ) 

CREATE TABLE tasks 
  ( 
     id          VARCHAR(255) NOT NULL, 
     description VARCHAR(255) NOT NULL, 
     title       VARCHAR(255) NOT NULL, 
     assignee_id VARCHAR(255), 
     project_id  VARCHAR(255) NOT NULL, 
     PRIMARY KEY (id) 
  ) 

ALTER TABLE tasks 
  ADD CONSTRAINT tasks_project_id FOREIGN KEY (project_id) REFERENCES projects ( 
  id) 

ALTER TABLE tasks 
  ADD CONSTRAINT tasks_assignee_id FOREIGN KEY (assignee_id) REFERENCES users ( 
  id) 

CREATE TABLE sequence 
  ( 
     seq_name  VARCHAR(50) NOT NULL, 
     seq_count DECIMAL(15), 
     PRIMARY KEY (seq_name) 
  ) 

INSERT INTO sequence 
            (seq_name, 
             seq_count) 
VALUES      ('SEQ_GEN', 
             10)

INSERT INTO users 
            (id, 
             fullname, 
             username, 
             password, 
             admin, 
             enabled) 
VALUES      (0, 
             'Administrator', 
             'admin', 
             'admin', 
             1, 
             1)

INSERT INTO projects 
            (id, 
             title, 
             code, 
             description) 
VALUES      (1, 
             'Urban Velociraptor Training Project', 
             'uvelo', 
             'Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.')
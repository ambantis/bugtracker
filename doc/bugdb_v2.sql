CREATE DATABASE bugs;
CREATE USER roach WITH PASSWORD 'motel';
ALTER DATABASE bugs OWNER TO roach;

CREATE TABLE person(
  id               VARCHAR (15) PRIMARY KEY ,
  name             VARCHAR (100) NOT NULL ,
  email            VARCHAR (30) NOT NULL ,
  url              VARCHAR (50) ,
  location         VARCHAR (50)
);

CREATE TABLE project(
  id               SERIAL PRIMARY KEY ,
  name             VARCHAR (50) NOT NULL ,
  owner            VARCHAR (15) REFERENCES person (id)
);

CREATE TABLE build(
  project_id       INTEGER REFERENCES project(id),
  major            INTEGER NOT NULL ,
  minor            INTEGER NOT NULL ,
  PRIMARY KEY (project_id, major, minor)
);

CREATE TABLE role(
  id               VARCHAR (3) PRIMARY KEY ,
  name             VARCHAR (30) NOT NULL
);

CREATE TABLE team(
  project_id       INTEGER REFERENCES project(id) ,
  person_id        VARCHAR (15) REFERENCES person(id) ,
  role_id          VARCHAR (3) REFERENCES role(id) ,
  PRIMARY KEY (project_id, person_id)
);

CREATE TABLE status(
  id               SERIAL PRIMARY KEY ,
  name             VARCHAR (6) NOT NULL
);

CREATE TABLE resolve(
  id               SERIAL PRIMARY KEY ,
  name             VARCHAR (10) NOT NULL
);

CREATE TABLE severity(
  id               SERIAL PRIMARY KEY ,
  name             VARCHAR (6) NOT NULL
);

CREATE TABLE priority(
  id               SERIAL PRIMARY KEY ,
  name             VARCHAR (10) NOT NULL
);

CREATE TABLE bug(
  id               SERIAL PRIMARY KEY ,
  project_id       INTEGER ,
  major            INTEGER REFERENCES build(major) ,
  minor            INTEGER REFERENCES build(minor) ,
  status_id        INTEGER REFERENCES status(id) ,
  resolve_id       INTEGER REFERENCES resolve(id) ,
  priority_id      INTEGER REFERENCES priority(id) ,
  owner            VARCHAR (15) NOT NULL ,
  assignee         VARCHAR (15) NOT NULL ,
  summary          VARCHAR (100) NOT NULL ,
  behavior         VARCHAR (1000) NOT NULL ,
  steps_reproduce  VARCHAR (1000) NOT NULL ,
  created          TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now() ,
  created_by       VARCHAR (15) NOT NULL REFERENCES person(id) ,
  due_date         DATE NOT NULL DEFAULT current_date,
  close_date       DATE NOT NULL DEFAULT '1970-01-01',
  FOREIGN KEY (project_id, owner) REFERENCES team(project_id, person_id),
  FOREIGN KEY (project_id, assignee) REFERENCES team(project_id, person_id)
);

CREATE TABLE row(
  id               SERIAL PRIMARY KEY ,
  bug_id           INTEGER REFERENCES bug(id) ,
  created          TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now() ,
  comment          VARCHAR (1000) ,
  comment_by       VARCHAR (15) REFERENCES person(id) -- should reference team(project_id, person_id)
);

CREATE TABLE archive(
  id               SERIAL PRIMARY KEY ,
  project_id       INTEGER ,
  major            INTEGER REFERENCES build(major) ,
  minor            INTEGER REFERENCES build(minor) ,
  status_id        INTEGER REFERENCES status(id) ,
  resolve_id       INTEGER REFERENCES resolve(id) ,
  priority_id      INTEGER REFERENCES priority(id) ,
  owner            VARCHAR (15) NOT NULL ,
  assignee         VARCHAR (15) NOT NULL ,
  summary          VARCHAR (100) NOT NULL ,
  behavior         VARCHAR (1000) NOT NULL ,
  steps_reproduce  VARCHAR (1000) NOT NULL ,
  created          TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now() ,
  created_by       VARCHAR (15) NOT NULL REFERENCES person(id) ,
  due_date         DATE NOT NULL DEFAULT current_date,
  close_date       DATE NOT NULL DEFAULT '1970-01-01'
);

INSERT INTO person (id, name, email) VALUES ('ambantis', 'Alexandros Bantis', 'ambantis@gmail.com');
INSERT INTO person (id, name, email) VALUES ('acbantis', 'Ana Cecilia Bantis', 'acbantis@gmail.com');

INSERT INTO project (name, owner) VALUES ('Bugtracker', 'ambantis');

INSERT INTO build (project_id, major, minor) VALUES (1, 0, 8);

INSERT INTO role (id, name) VALUES ('qat', 'tester');
INSERT INTO role (id, name) VALUES ('dev', 'developer');
INSERT INTO role (id, name) VALUES ('mgr', 'manager');

INSERT INTO team (project_id, person_id, role_id) VALUES (1, 'ambantis', 'mgr');
INSERT INTO team (project_id, person_id, role_id) VALUES (1, 'acbantis', 'qat');

INSERT INTO status(id, name) VALUES (1, 'opened');
INSERT INTO status(id, name) VALUES (2, 'closed');

INSERT INTO resolve(id, name) VALUES (1, $$fixed$$);
INSERT INTO resolve(id, name) VALUES (2, $$won't fix$$);
INSERT INTO resolve(id, name) VALUES (3, $$postponed$$);
INSERT INTO resolve(id, name) VALUES (4, $$no repro$$);
INSERT INTO resolve(id, name) VALUES (5, $$duplicate$$);
INSERT INTO resolve(id, name) VALUES (6, $$by design$$);

INSERT INTO severity(id, name) VALUES (1, 'DEBUG');
INSERT INTO severity(id, name) VALUES (2, 'ERROR');
INSERT INTO severity(id, name) VALUES (3, 'FATAL');

INSERT INTO priority(id, name) VALUES (1, 'MUST FIX');
INSERT INTO priority(id, name) VALUES (2, 'MUST FIX');
INSERT INTO priority(id, name) VALUES (3, 'SO SO');
INSERT INTO priority(id, name) VALUES (4, 'LOW PRIORITY');
INSERT INTO priority(id, name) VALUES (5, 'VERY LOW PRIORITY');
CREATE DATABASE bugdb;
CREATE USER roach WITH PASSWORD 'motel';
ALTER DATABASE bugdb OWNER TO roach;

CREATE TABLE bug (
  bug_id        serial PRIMARY KEY,
  due_date      DATE DEFAULT current_date,
  close_date    DATE,
  assignee      CHARACTER VARYING(30) DEFAULT 'unk',
  priority      INTEGER DEFAULT 0,
  summary       CHARACTER VARYING(100) NOT NULL,
  history       CHARACTER VARYING(4000),
  final_result  CHARACTER VARYING(4000) DEFAULT 'n/a',
  created       TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
  created_by    CHARACTER VARYING(30) NOT NULL,
  modified      TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
  modified_by   CHARACTER VARYING(30) NOT NULL,
  CHECK (priority > -1 AND priority < 11),
  CHECK ((due_date IS NULL AND close_date IS NOT NULL) OR (due_date IS NOT NULL AND close_date IS NULL))
);

CREATE TABLE archive(
  bug_id        INTEGER,
  due_date      DATE,
  close_date    DATE,
  assignee      CHARACTER VARYING(30),
  priority      INTEGER,
  summary       CHARACTER VARYING(100),
  history       CHARACTER VARYING(4000),
  final_result  CHARACTER VARYING(4000),
  created       TIMESTAMP WITH TIME ZONE,
  created_by    CHARACTER VARYING(30),
  modified      TIMESTAMP WITH TIME ZONE,
  modified_by   CHARACTER VARYING(30)
);

CREATE TABLE contact(
  bug_id        INTEGER,
  user_name     CHARACTER VARYING(15),
  created       TIMESTAMP WITH TIME ZONE DEFAULT now(),
  created_by    CHARACTER VARYING(30) NOT NULL,
  UNIQUE (bug_id, email)
);

CREATE TABLE users(
  user_name     CHARACTER VARYING(15) NOT NULL PRIMARY KEY,
  user_pass     CHARACTER VARYING(15) NOT NULL,
  user_email    CHARACTER VARYING(30) NOT NULL
);

CREATE TABLE user_roles (
  user_name     CHARACTER VARYING(15) NOT NULL,
  role_name     CHARACTER VARYING(15) NOT NULL,
  UNIQUE (user_name, role_name)
);



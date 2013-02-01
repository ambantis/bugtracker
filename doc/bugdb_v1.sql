CREATE DATABASE bugdb;
CREATE USER roach WITH PASSWORD 'motel';
ALTER DATABASE bugdb OWNER TO roach;


CREATE TABLE roles(
  role_id       VARCHAR (3) PRIMARY KEY,
  role_name     VARCHAR (50) NOT NULL
);

CREATE TABLE users(
  user_id       VARCHAR(15) PRIMARY KEY,
  password      VARCHAR (15) NOT NULL,
  full_name     VARCHAR (30) NOT NULL,
  role_id       VARCHAR (3) NOT NULL REFERENCES roles(role_id),
  email         VARCHAR(30) NOT NULL
);

CREATE TABLE bug (
  bug_id        SERIAL PRIMARY KEY,
  due_date      DATE DEFAULT current_date,
  close_date    DATE,
  assignee      VARCHAR(30) DEFAULT 'unk' REFERENCES users(user_id),
  priority      INTEGER DEFAULT 0,
  summary       VARCHAR(100) NOT NULL,
  history       VARCHAR(4000),
  final_result  VARCHAR(4000) DEFAULT 'n/a',
  created       TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
  created_by    VARCHAR(30) NOT
                            NULL,
  modified      TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
  modified_by   VARCHAR(30) NOT NULL,
  CHECK (priority > -1 AND priority < 11),
  CHECK ((due_date IS NULL AND close_date IS NOT NULL) OR (due_date IS NOT NULL AND close_date IS NULL))
);

CREATE TABLE archive(
  bug_id        INTEGER,
  due_date      DATE,
  close_date    DATE,
  assignee      VARCHAR(30),
  priority      INTEGER,
  summary       VARCHAR(100),
  history       VARCHAR(4000),
  final_result  VARCHAR(4000),
  created       TIMESTAMP WITH TIME ZONE,
  created_by    VARCHAR(30),
  modified      TIMESTAMP WITH TIME ZONE,
  modified_by   VARCHAR(30)
);

INSERT INTO roles (role_id, role_name) VALUES ('adm', 'Administrator');
INSERT INTO roles (role_id, role_name) VALUES ('mgr', 'Manager');
INSERT INTO roles (role_id, role_name) VALUES ('dev', 'Developer');
INSERT INTO roles (role_id, role_name) VALUES ('qat', 'Quality Assurance');

INSERT INTO users (user_id, password, full_name, role_id, email)
  VALUES('marge', 'kiss', 'Marge Simpson', 'adm', 'marge@simpsons.com');
INSERT INTO users (user_id, password, full_name, role_id, email)
  VALUES('monty', 'money', 'Monty Burns', 'mgr', 'monty@simpsons.com');
INSERT INTO users (user_id, password, full_name, role_id, email)
  VALUES('bart', 'cowabunga', 'Bart Simpson', 'dev', 'bart@simpsons.com');
INSERT INTO users (user_id, password, full_name, role_id, email)
  VALUES('lisa', 'flute', 'Lisa Simpson', 'dev', 'lisa@simpsons.com');
INSERT INTO users (user_id, password, full_name, role_id, email)
  VALUES('barney', 'burp', 'Barney Grumble', 'qat', 'barney@simpsons.com');
INSERT INTO users (user_id, password, full_name, role_id, email)
  VALUES('homer', 'donuts', 'Homer Simpson', 'qat', 'homer@simpsons.com');
INSERT INTO users (user_id, password, full_name, role_id, email)
  VALUES('unk', 'unk', 'Unknown', 'dev', 'unknown@simpsons.com');


CREATE DATABASE bugdb;

CREATE TABLE bug (
  bug_id        serial PRIMARY KEY,
  due_date      DATE DEFAULT '1970-01-01',
  assignee      VARCHAR(30) DEFAULT 'unk',
  priority      INTEGER DEFAULT -1,
  summary       VARCHAR(100) NOT NULL,
  description   VARCHAR(4000),
  finalResult  VARCHAR(4000) DEFAULT 'n/a'
);
 
CREATE USER roach WITH PASSWORD 'motel';
GRANT SELECT ON bug TO roach;
GRANT INSERT ON bug TO roach;
GRANT UPDATE ON bug TO roach;
GRANT DELETE ON bug TO roach;
GRANT USAGE, SELECT ON SEQUENCE bug_bug_id_seq TO roach;

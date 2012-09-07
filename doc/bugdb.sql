CREATE DATABASE bugdb;
CREATE USER roach WITH PASSWORD 'motel';
ALTER DATABASE bugdb OWNER TO roach;

CREATE TABLE bug (
  bug_id        serial PRIMARY KEY,
  due_date      DATE DEFAULT current_date,
  assignee      VARCHAR(30) DEFAULT 'unk',
  priority      INTEGER DEFAULT 0,
  summary       VARCHAR(100) NOT NULL,
  history       VARCHAR(4000),
  final_result  VARCHAR(4000) DEFAULT 'n/a',
  is_open       BOOLEAN DEFAULT TRUE,
  CHECK (priority > -1 AND priority < 11)
);


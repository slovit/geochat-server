-- Create User table
DROP TABLE IF EXISTS User;

CREATE TABLE User (
  user_id VARCHAR(36) NOT NULL PRIMARY KEY,
  username VARCHAR(20) NOT NULL,
  email VARCHAR(20) NOT NULL,
  password VARCHAR(20) NOT NULL,
  first_name VARCHAR(20),
  last_name VARCHAR(20)
 );

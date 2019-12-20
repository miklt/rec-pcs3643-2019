create database db1;
create database db2;
create database db3;
create database db4;
create database db5;
create database db6;
create database db7;

CREATE USER 'u1'@'%' IDENTIFIED BY '7HMUgV';
CREATE USER 'u2'@'%' IDENTIFIED BY '6wShMY';
CREATE USER 'u3'@'%' IDENTIFIED BY '7RPPgV';
CREATE USER 'u4'@'%' IDENTIFIED BY '4Fhgkt';
CREATE USER 'u5'@'%' IDENTIFIED BY '2VnzHB';
CREATE USER 'u6'@'%' IDENTIFIED BY '3X3Ffw';
CREATE USER 'u7'@'%' IDENTIFIED BY '4xH33X';

GRANT ALL PRIVILEGES ON db1.* TO  'u1'@'%';
GRANT ALL PRIVILEGES ON db2.* TO  'u2'@'%';
GRANT ALL PRIVILEGES ON db3.* TO  'u3'@'%';
GRANT ALL PRIVILEGES ON db4.* TO  'u4'@'%';
GRANT ALL PRIVILEGES ON db5.* TO  'u5'@'%';
GRANT ALL PRIVILEGES ON db6.* TO  'u6'@'%';
GRANT ALL PRIVILEGES ON db7.* TO  'u7'@'%';

GRANT ALL PRIVILEGES ON db1.* TO  'professor'@'%';
GRANT ALL PRIVILEGES ON db2.* TO  'professor'@'%';
GRANT ALL PRIVILEGES ON db3.* TO  'professor'@'%';
GRANT ALL PRIVILEGES ON db4.* TO  'professor'@'%';
GRANT ALL PRIVILEGES ON db5.* TO  'professor'@'%';
GRANT ALL PRIVILEGES ON db6.* TO  'professor'@'%';
GRANT ALL PRIVILEGES ON db7.* TO  'professor'@'%';


FLUSH PRIVILEGES;
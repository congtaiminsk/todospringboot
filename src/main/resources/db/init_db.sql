create database todomanager;
use todomanager;

create table works (
  ID int AUTO_INCREMENT PRIMARY KEY ,
  NAME varchar(255) NOT NULL ,
  STARTING_DATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  ENDING_DATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  STATUS varchar(255) NOT NULL
);

ALTER TABLE todomanager.works
ADD UNIQUE (NAME);

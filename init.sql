CREATE TABLE IF NOT EXISTS users ( 
    id SERIAL NOT NULL PRIMARY KEY, 
	auth_type varchar(256) NOT NULL, 
	username varchar(32) NOT NULL UNIQUE, 
	email varchar(256) NOT NULL UNIQUE, 
	password varchar(32), 
	google_id varchar(256) 
); 


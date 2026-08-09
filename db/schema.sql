CREATE TABLE users(
	 id int AUTO_INCREMENT,
	 email varchar(255) NOT NULL UNIQUE, 
	 name VARCHAR(100) NOT NULL, 
	 nickname VARCHAR(40) NOT NULL, 
	 PRIMARY KEY (id))
	 
CREATE TABLE tasks(
	id INT AUTO_INCREMENT, 
	user_id int NOT NULL,
	name VARCHAR(100) NOT NULL, 
	content text, 
	created_at datetime not null,
	start_at datetime not null,
	completed_at datetime,
	primary key (id),
	
	CONSTRAINT fk_user_id
	FOREIGN KEY (user_id) REFERENCES users(id)
	ON DELETE CASCADE)
	ENGINE=InnoDB;
	
CREATE TABLE notes(
	id INT AUTO_INCREMENT, 
	user_id int NOT NULL,
	name VARCHAR(60) NOT NULL , 
	content text, 
	created_at datetime not null,
	primary key (id),
	
	CONSTRAINT fk_user_id
	FOREIGN KEY (user_id) REFERENCES users(id)
	ON DELETE CASCADE)
	ENGINE=InnoDB;
	
CREATE TABLE xp(
	id INT AUTO_INCREMENT, 
	user_id int NOT NULL UNIQUE,
	current_xp INT NOT NULL,
	current_level INT NOT NULL,
	primary key (id),
	
	CONSTRAINT fk_ex_user_id
	FOREIGN KEY (user_id) REFERENCES users(id)
	ON DELETE CASCADE)
	ENGINE=InnoDB;
CREATE TABLE IF NOT EXISTS artist (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    genre VARCHAR(255) NOT NULL,
    nationality VARCHAR(255) NOT NULL,
    birth_date DATE,
    death_date DATE,
    instrument VARCHAR(255),
    biography TEXT
    );

CREATE TABLE IF NOT EXISTS disk (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    release_date DATE,
    genre VARCHAR(255),
    artist_id INT,
    FOREIGN KEY (artist_id) REFERENCES artist(id)
    );
CREATE TABLE IF NOT EXISTS song (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    genre VARCHAR(255) NOT NULL,
    lyrics TEXT,
    disk_id INT,
    FOREIGN KEY (disk_id) REFERENCES disk(id)
    );



CREATE TABLE artist_disk (
     id INT AUTO_INCREMENT PRIMARY KEY,
     id_artist INT,
     id_disk INT,
     FOREIGN KEY (id_artist) REFERENCES artist(id),
     FOREIGN KEY (id_disk) REFERENCES disk(id)
);

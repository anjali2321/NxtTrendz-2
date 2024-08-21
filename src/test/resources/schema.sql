create table if not exists category(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name varchar(255),
    description varchar(255)
);

create table if not exists product(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name varchar(255),
    description varchar(255),
    price DOUBLE,
    categoryId INT,
    FOREIGN KEY (categoryId) REFERENCES category(id)
);
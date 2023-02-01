CREATE Database project;
use project;
CREATE Table userAccount(
    id INT PRIMARY KEY,
    userName VARCHAR(50),
    password VARCHAR(50),
    roll VARCHAR(30)
)

Insert into userAccount values (1,'admin','pass','admin'),(2,'eyob','1234','librerian');

DROP TABLE IF EXISTS SiteUser;

DROP TABLE IF EXISTS SiteUserInfo;

DROP TABLE IF EXISTS SiteUserAddress;

DROP TABLE IF EXISTS item;

DROP TABLE IF EXISTS customer_order;


CREATE TABLE IF NOT EXISTS SiteUser(
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    password VARCHAR(100) NOT NULL,
    nickName VARCHAR(100) NOT NULL,
    adminFlag BOOLEAN NOT NULL COMMENT '0 or 1',
    deleteFlag BOOLEAN NOT NULL COMMENT '0 or 1',
    email VARCHAR(100),
    created_at TIMESTAMP,
    login_at TIMESTAMP,
    phone_number VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS SiteUserInfo(
	ID INT NOT NULL,
	gender VARCHAR(10) NOT NULL,
	creditNumber VARCHAR(100),
	birthday DATE,
	firstName1 VARCHAR(100) NOT NULL,
	lastName1 VARCHAR(100) NOT NULL,
	firstName2 VARCHAR(100) NOT NULL,
	lastName2 VARCHAR(100) NOT NULL,
	PRIMARY KEY(ID)
);

CREATE TABLE IF NOT EXISTS SiteUserAddress(
    ID INT NOT NULL,
    addressID INT NOT NULL,
    postNumber INTEGER NOT NULL,
    address1 VARCHAR(100) NOT NULL,
    address2 VARCHAR(100) NOT NULL,
    address3 VARCHAR(100) NOT NULL,
    address4 VARCHAR(100)
);

-- 商品テーブル
CREATE TABLE IF NOT EXISTS item (
  item_id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255),
  price INT,
  stock INT,
  image VARCHAR(255),
  sizes VARCHAR(255)
);



-- 注文テーブル
CREATE TABLE IF NOT EXISTS customer_order (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL,
    creditNumber VARCHAR(20) NOT NULL,
    phone_number VARCHAR(20),
    address1 VARCHAR(100),
    address2 VARCHAR(100),
    address3 VARCHAR(100),
    address4 VARCHAR(100),
    order_id INT,
    quantity INT,
    total_price INT
);



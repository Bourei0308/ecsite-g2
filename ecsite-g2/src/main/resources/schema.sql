CREATE TABLE IF NOT EXISTS SiteUser(
    id INT(10) PRIMARY KEY AUTO_INCREMENT,
    password VARCHAR(100) NOT NULL,
    nickName VARCHAR(100) NOT NULL,
    adminFlag TINYINT(1) NOT NULL COMMENT '0 or 1',
    deleteFlag TINYINT(1) NOT NULL COMMENT '0 or 1',
    email VARCHAR(100),
    created_at TIMESTAMP,
    login_at TIMESTAMP,
    phone_number VARCHAR(20)
);
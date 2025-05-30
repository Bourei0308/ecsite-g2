INSERT INTO SiteUser (password,nickName,adminFlag,deleteFlag,email,phone_number) VALUES ('1234','Taro',0,0,'a@a.com','123456');
INSERT INTO SiteUser (password,nickName,adminFlag,deleteFlag,email,phone_number) VALUES ('1234', 'admin', 1, 0, 'aa@a.com', '000');

INSERT INTO SiteUserInfo (
  ID, gender,
  creditNumber, birthday, firstName1, lastName1, firstName2, lastName2
) VALUES (
  1, 'male',
  1234567812345678, '1990-01-01', 'Taro', 'Yamada', 'タロウ', 'ヤマダ'
);


INSERT INTO SiteUserAddress (
  ID,addressID ,postNumber, address1, address2, address3, address4
) VALUES (
  1, 1, 1900013, '1', '1', '1', '1'
);

INSERT INTO item (name, price, stock, image, sizes) VALUES 
('うたかたの月下美人（グレー）', 15000, 10, 'gray.png', 'S, M'),
('水彩絢爛（ピンク）', 15000, 5, 'pink.png', 'S, M'),
('はんなり夕涼み（黄色）', 15000, 8, 'yellow.png', 'S, M'),
('涼風の燦々しらべ（水色）', 18000, 3, 'blue.png', 'S, M');

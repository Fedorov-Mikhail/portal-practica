-- Вставка данных
-- INSERT INTO user_ (id, name, email, password, post, telegram, phone, project, birth_date, city)
-- VALUES (1, 'Федоров', 'Mikey_fed', '1234', 'Intern', '@Surrounded_by_beasts', '89370771345','Корпоративный портал', '2004-09-03','Samara');
INSERT INTO user_ (name, birthday, start_work, telegram, city, email, phone, login, password, is_active, role, photo, post, project)
VALUES ('Иван Иванов', '1990-05-15', '2023-01-01', '@ivan', 'Москва', 'ivan@example.com',
        '+79991234567', 'ivan_login', 'securepassword123', true, 'ADMIN', NULL,NULL,NULL);
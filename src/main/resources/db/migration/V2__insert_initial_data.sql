
-- Categories
INSERT INTO categories (name) VALUES ('Бургеры');
INSERT INTO categories (name) VALUES ('Пицца');
INSERT INTO categories (name) VALUES ('Суши');
INSERT INTO categories (name) VALUES ('Напитки');
INSERT INTO categories (name) VALUES ('Десерты');

-- Extras
INSERT INTO extras (name, price) VALUES ('Сыр', 200);
INSERT INTO extras (name, price) VALUES ('Бекон', 300);
INSERT INTO extras (name, price) VALUES ('Соус', 100);
INSERT INTO extras (name, price) VALUES ('Сырный соус', 150);
INSERT INTO extras (name, price) VALUES ('Авокадо', 400);

-- Foods
INSERT INTO foods (name, description, price, category_id) VALUES ('Чизбургер', 'Классический чизбургер с говядиной', 1500, 1);
INSERT INTO foods (name, description, price, category_id) VALUES ('Двойной бургер', 'Бургер с двумя котлетами', 2500, 1);
INSERT INTO foods (name, description, price, category_id) VALUES ('Маргарита', 'Пицца с томатами и моцареллой', 3000, 2);
INSERT INTO foods (name, description, price, category_id) VALUES ('Пепперони', 'Пицца с пепперони', 3500, 2);
INSERT INTO foods (name, description, price, category_id) VALUES ('Калифорния', 'Ролл с крабом и авокадо', 2000, 3);

-- Food - Extra
INSERT INTO food_extras (food_id, extra_id) VALUES (1, 1);
INSERT INTO food_extras (food_id, extra_id) VALUES (1, 2);
INSERT INTO food_extras (food_id, extra_id) VALUES (1, 3);
INSERT INTO food_extras (food_id, extra_id) VALUES (2, 2);
INSERT INTO food_extras (food_id, extra_id) VALUES (3, 4);
INSERT INTO food_extras (food_id, extra_id) VALUES (3, 5);

-- Permissions(role)
INSERT INTO permission (name) VALUES ('ROLE_USER');
INSERT INTO permission (name) VALUES ('ROLE_ADMIN');
INSERT INTO permission (name) VALUES ('ROLE_MANAGER');

-- Users
INSERT INTO users (username, email, password) VALUES ('admin', 'admin@gmail.com', '$2a$12$6fB0Xsid3V1uKb6JzZqLHeAmw2J/Dey4L996ra0yRBJtrIlCpEFpW');
INSERT INTO users (username, email, password) VALUES ('user1', 'user1@gmail.com', '$2y$10$E31TCwXQgdu36ULNwx3gdeS6KLpvLyaFNi0yF9VhD.o0x9KRYnvvC');
INSERT INTO users (username, email, password) VALUES ('manager', 'manager@gmail.com', '$2a$12$hKvAVXzx/IZxNS2M//VDY.OCpZWFDqr9kx3j.MJkTBxovxUKGFaX2');

-- Users - Roles
INSERT INTO users_roles (user_id, roles_id) VALUES (1, 2);
INSERT INTO users_roles (user_id, roles_id) VALUES (2, 1);
INSERT INTO users_roles (user_id, roles_id) VALUES (3, 3);

-- Orders
INSERT INTO orders (user_id, status, total_price, created_at) VALUES (1, 'NEW', 4500, '2025-12-17 12:00:00');
INSERT INTO orders (user_id, status, total_price, created_at) VALUES (2, 'IN_PROGRESS', 2400, '2025-12-17 13:30:00');

-- Order Items
INSERT INTO order_items (order_id, food_id, quantity) VALUES (1, 1, 2);
INSERT INTO order_items (order_id, food_id, quantity) VALUES (1, 3, 3);
INSERT INTO order_items (order_id, food_id, quantity) VALUES (2, 5, 1);

-- Order Item Extras
INSERT INTO order_item_extras (order_item_id, extra_id) VALUES (1, 1);
INSERT INTO order_item_extras (order_item_id, extra_id) VALUES (1, 2);
INSERT INTO order_item_extras (order_item_id, extra_id) VALUES (2, 4);
-- ===============================
-- PRODUCTS
-- ===============================
INSERT INTO products (name, description, price, stock_quantity)
VALUES
    ('MacBook Air M1', 'Apple 13-inch laptop with M1 chip', 999.99, 15),
    ('Logitech MX Master 3', 'Wireless mouse for productivity', 79.99, 50),
    ('Keychron K6', 'Mechanical keyboard with Gateron switches', 89.99, 30),
    ('Sony WH-1000XM5', 'Noise-cancelling over-ear headphones', 349.99, 25),
    ('Samsung Galaxy S23', 'Flagship Android smartphone', 899.00, 20),
    ('Apple Watch SE', 'Smartwatch with fitness tracking', 279.99, 40),
    ('Anker PowerCore 20000', 'Portable USB-C power bank', 49.99, 100),
    ('Dell XPS 13', 'Compact Windows ultrabook', 1099.00, 10),
    ('Razer DeathAdder V3', 'Gaming mouse with ergonomic design', 69.99, 60),
    ('Amazon Echo Dot', 'Smart speaker with Alexa', 49.99, 80);

-- ===============================
-- USERS
-- ===============================
INSERT INTO users (name, email, password)
VALUES
    ('Ayush Baghel', 'ayush@example.com', 'password123'),
    ('John Doe', 'john.doe@example.com', 'pass123'),
    ('Jane Smith', 'jane.smith@example.com', 'securepass'),
    ('Arjun Mehta', 'arjun.mehta@example.com', 'arjunpass'),
    ('Sara Khan', 'sara.khan@example.com', 'sarapass');

-- ===============================
-- CARTS
-- ===============================
INSERT INTO carts (user_id)
VALUES
    (1),
    (2),
    (3),
    (4),
    (5);

-- ===============================
-- CART_ITEMS
-- ===============================
INSERT INTO cart_items (cart_id, product_id, quantity)
VALUES
    (1, 1, 1),
    (1, 2, 2),
    (2, 4, 1),
    (3, 3, 1),
    (3, 5, 1),
    (4, 6, 2),
    (5, 7, 1);

-- ===============================
-- ORDERS
-- ===============================
INSERT INTO orders (user_id, total_amount, status)
VALUES
    (1, 1159.97, 'PLACED'),
    (2, 349.99, 'PLACED'),
    (3, 178.98, 'PLACED'),
    (4, 559.98, 'SHIPPED'),
    (5, 49.99, 'CANCELLED');

-- ===============================
-- ORDER_ITEMS
-- ===============================
INSERT INTO order_items (order_id, product_id, quantity, price_at_purchase)
VALUES
    (1, 1, 1, 999.99),
    (1, 2, 2, 79.99),
    (2, 4, 1, 349.99),
    (3, 3, 2, 89.49),
    (4, 6, 2, 279.99),
    (5, 7, 1, 49.99);
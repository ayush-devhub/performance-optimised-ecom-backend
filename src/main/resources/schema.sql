-- Drop existing tables (for clean dev re-runs)
DROP TABLE IF EXISTS products;

-- ===============================
-- PRODUCTS TABLE
-- ===============================
CREATE TABLE products
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    name           VARCHAR(100)   NOT NULL,
    description    TEXT,
    price          DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    stock_quantity INT            NOT NULL DEFAULT 0,
    created_at     TIMESTAMP               DEFAULT CURRENT_TIMESTAMP,
    updated_at     TIMESTAMP               DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Add index for faster product lookups (will help during optimization phase)
CREATE INDEX idx_product_name ON products (name);

-- ===============================
-- USERS TABLE
-- ===============================
DROP TABLE IF EXISTS users;
CREATE TABLE users
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(100) NOT NULL,
    email      VARCHAR(100) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- ===============================
-- CART TABLE
-- ===============================
DROP TABLE IF EXISTS carts;
CREATE TABLE carts
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id    BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

-- ===============================
-- CART_ITEMS TABLE
-- ===============================
DROP TABLE IF EXISTS cart_items;
CREATE TABLE cart_items
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    cart_id    BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity   INT    NOT NULL DEFAULT 1,
    added_at   TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cart_id) REFERENCES carts (id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE
);

-- Add indexes for performance
CREATE INDEX idx_cart_user ON carts (user_id);
CREATE INDEX idx_cart_item_cart ON cart_items (cart_id);
CREATE INDEX idx_cart_item_product ON cart_items (product_id);

-- ===============================
-- ORDERS TABLE
-- ===============================
DROP TABLE IF EXISTS orders;
CREATE TABLE orders
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id      BIGINT         NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    status       VARCHAR(50) DEFAULT 'PLACED',
    created_at   TIMESTAMP   DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

-- ===============================
-- ORDER_ITEMS TABLE
-- ===============================
DROP TABLE IF EXISTS order_items;
CREATE TABLE order_items
(
    id                BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id          BIGINT         NOT NULL,
    product_id        BIGINT         NOT NULL,
    quantity          INT            NOT NULL DEFAULT 1,
    price_at_purchase DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE
);

-- Add useful indexes
CREATE INDEX idx_order_user ON orders (user_id);
CREATE INDEX idx_order_item_order ON order_items (order_id);
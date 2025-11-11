
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
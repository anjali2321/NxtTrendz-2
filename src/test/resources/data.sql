INSERT INTO category (name, description)
SELECT 'Electronics', 'Gadgets and devices for everyday use.'
WHERE NOT EXISTS (SELECT 1 FROM category WHERE id = 1);

INSERT INTO category (name, description)
SELECT 'Books', 'Novels, textbooks, and other reading materials.'
WHERE NOT EXISTS (SELECT 2 FROM category WHERE id = 2);

INSERT INTO category (name, description)
SELECT 'Fashion', 'Clothing, footwear, and accessories.'
WHERE NOT EXISTS (SELECT 3 FROM category WHERE id = 3);

INSERT INTO product (name, description, price, categoryId)
SELECT 'Laptop', 'A high-performance laptop suitable for gaming and professional tasks.', 50000.00, 1
WHERE NOT EXISTS (SELECT 1 FROM product WHERE id = 1);

INSERT INTO product (name, description, price, categoryId)
SELECT 'Bluetooth Speaker', 'A portable speaker with excellent sound quality.', 2500.00, 1
WHERE NOT EXISTS (SELECT 2 FROM product WHERE id = 2);

INSERT INTO product (name, description, price, categoryId)
SELECT 'Mystery Novel', 'A thrilling novel full of twists and turns.', 500.00, 2
WHERE NOT EXISTS (SELECT 3 FROM product WHERE id = 3);

INSERT INTO product (name, description, price, categoryId)
SELECT 'History Textbook', 'A comprehensive guide to world history.', 400.00, 2
WHERE NOT EXISTS (SELECT 4 FROM product WHERE id = 4);

INSERT INTO product (name, description, price, categoryId)
SELECT 'Leather Jacket', 'A stylish jacket made of genuine leather.', 2200.00, 3
WHERE NOT EXISTS (SELECT 5 FROM product WHERE id = 5);

INSERT INTO product (name, description, price, categoryId)
SELECT 'Running Shoes', 'Comfortable shoes perfect for jogging.', 700.00, 3
WHERE NOT EXISTS (SELECT 6 FROM product WHERE id = 6);
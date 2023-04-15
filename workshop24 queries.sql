use northwind;

CREATE TABLE purchase_order (
    order_id INT AUTO_INCREMENT NOT NULL,
    order_date DATE NOT NULL,
    customer_name VARCHAR(128) NOT NULL,
    ship_address VARCHAR(128),
    notes LONGTEXT,
    tax DECIMAL(2,2) DEFAULT 0.05,
    PRIMARY KEY (order_id)
);

--Decimal(3,2): 3 means it can hold 3 digits in total, including 
--the digit right after the decimal point.
--2 means it can hold 2 digits after the decimal point. 
--so max number it can store is +99.99, min num it can store is -99.99

CREATE TABLE purchase_order_details (
  id INT AUTO_INCREMENT NOT NULL,
  product VARCHAR(64),
  unit_price DECIMAL(5,2),
  discount DECIMAL(3,2) DEFAULT 0.95,
  quantity INT,
  order_id INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_order_id
    FOREIGN KEY (order_id) REFERENCES purchase_order(order_id)
);



CREATE TABLE fruits_products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    standard_price DECIMAL(10, 2) NOT NULL,
    discount DECIMAL(3, 2) DEFAULT 1.0
);

INSERT INTO fruits_products (name, standard_price, discount) VALUES
   ('Apple', 5.0, 0.9),
   ('Banana', 5.99, 0.85),
   ('Orange', 4.49, 0.85),
   ('Pear', 6.79, 0.85),
   ('Pineapple', 8.99, 0.9),
   ('Grapes', 8.99, 0.7),
   ('Watermelon', 7.99, 0.8),
   ('Strawberry', 7.99, 1.0),
   ('Blueberry', 8.99, 1.0),
   ('Kiwi', 4.99, 0.95);
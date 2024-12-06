CREATE TABLE Customer (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100),
    phone VARCHAR(15)
);

CREATE TABLE Food (
    food_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    price DECIMAL(10, 2)
);

CREATE TABLE `Order` (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT,
    order_date DATETIME,
    total_price DECIMAL(10, 2),
    status VARCHAR(50),
    driver_id INT,
    FOREIGN KEY (customer_id) REFERENCES Customer(customer_id),
    FOREIGN KEY (driver_id) REFERENCES Driver(driver_id)
);

CREATE TABLE OrderDetail (
    order_detail_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT,
    food_id INT,
    quantity INT,
    price DECIMAL(10, 2),
    FOREIGN KEY (order_id) REFERENCES `Order`(order_id),
    FOREIGN KEY (food_id) REFERENCES Food(food_id)
);

CREATE TABLE Driver (
    driver_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    phone VARCHAR(15)
);

CREATE TABLE Payment (
    payment_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT,
    payment_date DATETIME,
    amount DECIMAL(10, 2),
    method VARCHAR(50),
    FOREIGN KEY (order_id) REFERENCES `Order`(order_id)
);

INSERT INTO Customer (name, email, phone)
VALUES
('John Doe', 'john@example.com', '123456789'),
('Jane Smith', 'jane@example.com', '987654321'),
('Alice Brown', 'alice@example.com', '555444333'),
('Bob Green', 'bob@example.com', '666777888'),
('Eve White', 'eve@example.com', '111222333');


INSERT INTO Food (name, price)
VALUES
('Pizza', 8.99),
('Burger', 5.49),
('Pasta', 7.99),
('Salad', 4.99),
('Sushi', 12.49);


INSERT INTO Driver (name, phone)
VALUES
('Tom Driver', '444555666'),
('Jerry Courier', '333222111'),
('Anna Delivery', '999888777'),
('Luke Express', '777666555'),
('Emma Speed', '888999000');

INSERT INTO `Order` (customer_id, order_date, total_price, status, driver_id)
VALUES
(1, '2024-12-05 10:00:00', 25.47, 'Delivered', 1),
(2, '2024-12-05 11:00:00', 15.48, 'Delivered', 2),
(3, '2024-12-05 12:00:00', 8.99, 'Pending', 3),
(4, '2024-12-05 13:00:00', 7.99, 'Cancelled', 4),
(5, '2024-12-05 14:00:00', 16.48, 'Processing', 5);

INSERT INTO OrderDetail (order_id, food_id, quantity, price)
VALUES
(1, 1, 2, 17.98),
(1, 2, 1, 5.49),
(2, 3, 1, 7.99),
(2, 4, 1, 4.99),
(3, 5, 1, 12.49);

INSERT INTO Payment (order_id, payment_date, amount, method)
VALUES
(1, '2024-12-05 10:15:00', 25.47, 'Credit Card'),
(2, '2024-12-05 11:15:00', 15.48, 'PayPal'),
(3, '2024-12-05 12:30:00', 8.99, 'Cash'),
(4, '2024-12-05 13:10:00', 0.00, 'None'),
(5, '2024-12-05 14:20:00', 16.48, 'Credit Card');

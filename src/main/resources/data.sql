INSERT INTO roles (code, value)
VALUES (2, 'GENERAL'),
       (1, 'ADMIN');

INSERT INTO users (role_code, name, password, email)
VALUES (1, '宇津宮', 'password', 'hoge@example.com'),
       (2, '金子', 'password2', 'kaneko@example.com');

INSERT INTO physical_details (created_at, weight, recorded_date)
VALUES ('2023-08-01 12:12:12', 70.0, '2023-08-10'),
       ('2023-08-02 11:11:11', 65.0, '2023-08-12');

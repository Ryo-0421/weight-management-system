CREATE TABLE IF NOT EXISTS roles (
    code INT NOT NULL CHECK (code >= 0 AND code <= 999),
    value VARCHAR(255) NOT NULL,
    PRIMARY KEY(code)
);

CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    role_code INT NOT NULL CHECK (role_code >= 0 AND role_code <= 999),
    name VARCHAR(255) NOT NULL,
    password VARCHAR(60) NOT NULL,
    email VARCHAR(255) NOT NULL,
    FOREIGN KEY(role_code) REFERENCES roles(code)
);

CREATE TABLE IF NOT EXISTS physical_details (
    user_id INT AUTO_INCREMENT,
    created_at TIMESTAMP,
    weight DECIMAL NOT NULL,
    recorded_date DATE NOT NULL,
    PRIMARY KEY(user_id, created_at),
    FOREIGN KEY(user_id) REFERENCES users(id)
);
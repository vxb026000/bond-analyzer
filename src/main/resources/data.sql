-- Admin user for testing (username: admin, password: password)
INSERT INTO users (username, password, role)
VALUES ('admin', '{bcrypt}$2a$10$POy8KIwEnxmFwo2auo9e3ehPwGdeUR3vYMNuSEzw/goe77EWf/.BK', 'ADMIN');

-- Example normal user for testing stocks
INSERT INTO users (username, password, role)
VALUES ('user', '{bcrypt}$2a$10$POy8KIwEnxmFwo2auo9e3ehPwGdeUR3vYMNuSEzw/goe77EWf/.BK', 'USER');

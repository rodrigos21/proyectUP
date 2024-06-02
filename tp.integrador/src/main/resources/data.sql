
INSERT INTO USERS (name, surname, email, password, dni, phone) VALUES ('test', 'test', 'test@test.com', 'asd', '12345678A', '123456789');
INSERT INTO USERS (name, surname, email, password, dni, phone) VALUES ('admin', 'admin', 'admin@admin.com', '1234', '12345678A', '123456789');
INSERT INTO USERS (name, surname, email, password, dni, phone) VALUES ('John', 'Doe', 'john@example.com', '123', '12345678A', '123456789');
INSERT INTO USERS (name, surname, email, password, dni, phone) VALUES ('Jane', 'Doe', 'jane@example.com', '456', '87654321B', '987654321');


INSERT INTO ROLES (name, description) VALUES ('ROLE_USER', 'Regular user role');
INSERT INTO ROLES (name, description) VALUES ('ROLE_ADMIN', 'Admin user role');

INSERT INTO USER_ROLES (user_id, role_id) VALUES (1, 1);
INSERT INTO USER_ROLES (user_id, role_id) VALUES (2, 1);
INSERT INTO USER_ROLES (user_id, role_id) VALUES (2, 2);

INSERT INTO USER_SECURITY (username, password, role) VALUES ('admin@admin.com', '1234', 'ROLE_ADMIN');
INSERT INTO USER_SECURITY (username, password, role) VALUES ('test@test.com', 'asd', 'ROLE_ADMIN');
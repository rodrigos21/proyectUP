
INSERT INTO USERS (name, surname, email, password, dni, phone) VALUES ('admin', 'admin', 'admin@admin.com', '1234', '12345678A', '123456789');
INSERT INTO USERS (name, surname, email, password, dni, phone) VALUES ('Jane', 'Doe', 'jane@example.com', '456', '87654321B', '987654321');
INSERT INTO USERS (name, surname, email, password, dni, phone) VALUES ('John', 'Smith', 'john.smith@example.com', '123', '12345678A', '123456789');
INSERT INTO USERS (name, surname, email, password, dni, phone) VALUES ('Alice', 'Johnson', 'alice.johnson@example.com', '789', '87654322C', '987654322');
INSERT INTO USERS (name, surname, email, password, dni, phone) VALUES ('Bob', 'Brown', 'bob.brown@example.com', '101', '87654323D', '987654323');
INSERT INTO USERS (name, surname, email, password, dni, phone) VALUES ('Carol', 'Davis', 'carol.davis@example.com', '202', '87654324E', '987654324');
INSERT INTO USERS (name, surname, email, password, dni, phone) VALUES ('David', 'Miller', 'david.miller@example.com', '303', '87654325F', '987654325');
INSERT INTO USERS (name, surname, email, password, dni, phone) VALUES ('Eve', 'Wilson', 'eve.wilson@example.com', '404', '87654326G', '987654326');
INSERT INTO USERS (name, surname, email, password, dni, phone) VALUES ('Frank', 'Moore', 'frank.moore@example.com', '505', '87654327H', '987654327');
INSERT INTO USERS (name, surname, email, password, dni, phone) VALUES ('Grace', 'Taylor', 'grace.taylor@example.com', '606', '87654328I', '987654328');
INSERT INTO USERS (name, surname, email, password, dni, phone) VALUES ('Hank', 'Anderson', 'hank.anderson@example.com', '707', '87654329J', '987654329');

INSERT INTO ROLES (name, description) VALUES ('ROLE_USER', 'Regular user role');
INSERT INTO ROLES (name, description) VALUES ('ROLE_ADMIN', 'Admin user role');
INSERT INTO ROLES (name, description) VALUES ('ROLE_MANAGER', 'Manager user role');
INSERT INTO ROLES (name, description) VALUES ('ROLE_EDITOR', 'Editor user role');
INSERT INTO ROLES (name, description) VALUES ('ROLE_VIEWER', 'Viewer user role');

INSERT INTO USER_ROLES (user_id, role_id) VALUES (1, 1);
INSERT INTO USER_ROLES (user_id, role_id) VALUES (2, 1);
INSERT INTO USER_ROLES (user_id, role_id) VALUES (3, 2);
INSERT INTO USER_ROLES (user_id, role_id) VALUES (4, 3);
INSERT INTO USER_ROLES (user_id, role_id) VALUES (5, 4);
INSERT INTO USER_ROLES (user_id, role_id) VALUES (6, 5);
INSERT INTO USER_ROLES (user_id, role_id) VALUES (7, 3);
INSERT INTO USER_ROLES (user_id, role_id) VALUES (8, 2);
INSERT INTO USER_ROLES (user_id, role_id) VALUES (9, 5);

INSERT INTO USER_SECURITY (username, password, role) VALUES ('admin@admin.com', '1234', 'ROLE_ADMIN');

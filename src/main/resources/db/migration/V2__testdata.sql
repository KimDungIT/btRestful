INSERT INTO account(id, username, password, enable, role) VALUES(1, 'Rajeev', 'abc', true, 'USER');
INSERT INTO account(id, username, password, enable, role) VALUES(2, 'Flyway', 'test', true, 'USER');

UPDATE hibernate_sequence set next_val = 3;
CREATE SCHEMA controlServer;

INSERT INTO server.block_list (block_type, description)
VALUES ('finBlock', '
Финансовая блокировка клиента');

INSERT INTO  server.users_block (block_type, description)
VALUES ('admBlock', '
Административная блокировка клиента');

SELECT * FROM  controlServer.blockList;
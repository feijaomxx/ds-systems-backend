
INSERT INTO categoria (nome, tipo) VALUES ('Salário', 'RECEITA');
INSERT INTO categoria (nome, tipo) VALUES ('Alimentação', 'DESPESA');
INSERT INTO categoria (nome, tipo) VALUES ('Lazer', 'DESPESA');
INSERT INTO categoria (nome, tipo) VALUES ('Moradia', 'DESPESA');


INSERT INTO usuario (nome, email, senha, renda_mensal)
VALUES ('Lucas Solo', 'lucas@email.com', 'senha123', 3500.00);


INSERT INTO usuario (nome, email, senha, renda_mensal)
VALUES ('Roberto Pai', 'roberto@email.com', 'senha123', 8000.00);


INSERT INTO usuario (nome, email, senha, renda_mensal)
VALUES ('Mateus Filho', 'mateus@email.com', 'senha123', 1500.00);


INSERT INTO compartilhamento_conta (id_dono_conta, id_usuario_convidado, nivel_permissao)
VALUES (2, 3, 'LEITURA');


INSERT INTO transacao (id_usuario, id_categoria, valor, data_transacao, descricao)
VALUES (1, 1, 3500.00, CURRENT_DATE, 'Salário do mês');

INSERT INTO transacao (id_usuario, id_categoria, valor, data_transacao, descricao)
VALUES (2, 4, 1500.00, CURRENT_DATE, 'Aluguel pago');

INSERT INTO transacao (id_usuario, id_categoria, valor, data_transacao, descricao)
VALUES (3, 3, 200.00, CURRENT_DATE, 'Cinema e lanche');
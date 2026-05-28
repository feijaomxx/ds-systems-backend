
ALTER TABLE usuario ADD COLUMN cpf VARCHAR(14) UNIQUE;
ALTER TABLE usuario ADD COLUMN rg VARCHAR(20);
ALTER TABLE usuario ADD COLUMN telefone VARCHAR(20);


CREATE TABLE endereco (
                          id SERIAL PRIMARY KEY,
                          id_usuario INT UNIQUE NOT NULL,
                          cep VARCHAR(10) NOT NULL,
                          logradouro VARCHAR(150) NOT NULL,
                          numero VARCHAR(20) NOT NULL,
                          bairro VARCHAR(100) NOT NULL,
                          municipio VARCHAR(100) NOT NULL,
                          estado VARCHAR(2) NOT NULL,
                          complemento VARCHAR(100),


                          CONSTRAINT fk_endereco_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id) ON DELETE CASCADE
);


UPDATE usuario SET cpf = '111.111.111-11', telefone = '(11) 99999-1111' WHERE id = 1;
UPDATE usuario SET cpf = '222.222.222-22', telefone = '(22) 99999-2222' WHERE id = 2;
UPDATE usuario SET cpf = '333.333.333-33', telefone = '(33) 99999-3333' WHERE id = 3;

ALTER TABLE usuario ALTER COLUMN cpf SET NOT NULL
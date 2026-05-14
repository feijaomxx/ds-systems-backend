CREATE TABLE usuario
(
    id            SERIAL PRIMARY KEY,
    nome          VARCHAR(100)        NOT NULL,
    email         VARCHAR(150) UNIQUE NOT NULL,
    senha         VARCHAR(255)        NOT NULL,
    renda_mensal  DECIMAL(12, 2)      NOT NULL CHECK (renda_mensal >= 0),
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE categoria
(
    id   SERIAL PRIMARY KEY,
    nome VARCHAR(50) UNIQUE NOT NULL,
    tipo VARCHAR(15)        NOT NULL CHECK (tipo IN ('RECEITA', 'DESPESA'))
);

CREATE TABLE transacao
(
    id             SERIAL PRIMARY KEY,
    id_usuario     INT            NOT NULL,
    id_categoria   INT            NOT NULL,
    valor          DECIMAL(12, 2) NOT NULL CHECK (valor > 0),
    data_transacao DATE           NOT NULL,
    descricao      VARCHAR(255),

    CONSTRAINT fk_usuario FOREIGN KEY (id_usuario) REFERENCES usuario (id) ON DELETE CASCADE,
    CONSTRAINT fk_categoria FOREIGN KEY (id_categoria) REFERENCES categoria (id) ON DELETE RESTRICT
);

CREATE INDEX idx_transacao_data ON transacao (data_transacao);

CREATE TABLE compartilhamento_conta
(
    id                    SERIAL PRIMARY KEY,
    id_dono_conta         INT         NOT NULL,
    id_usuario_convidado  INT         NOT NULL,
    nivel_permissao       VARCHAR(20) NOT NULL CHECK (nivel_permissao IN ('LEITURA', 'ESCRITA')),
    data_compartilhamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_dono FOREIGN KEY (id_dono_conta) REFERENCES usuario (id) ON DELETE CASCADE,
    CONSTRAINT fk_convidado FOREIGN KEY (id_usuario_convidado) REFERENCES usuario (id) ON DELETE CASCADE,
    CONSTRAINT uk_compartilhamento UNIQUE (id_dono_conta, id_usuario_convidado),
    CONSTRAINT chk_nao_compartilhar_consigo_mesmo CHECK (id_dono_conta <> id_usuario_convidado)
);

CREATE VIEW vw_resumo_mensal AS
SELECT u.nome                               AS usuario,
       c.tipo                               AS tipo_movimentacao,
       SUM(t.valor)                         AS total_acumulado,
       TO_CHAR(t.data_transacao, 'YYYY-MM') AS mes_referencia
FROM transacao t
         JOIN usuario u ON t.id_usuario = u.id
         JOIN categoria c ON t.id_categoria = c.id
GROUP BY u.nome, c.tipo, mes_referencia;


CREATE TABLE alerta_financeiro
(
    id          SERIAL PRIMARY KEY,
    id_usuario  INT REFERENCES usuario (id),
    mensagem    TEXT,
    data_alerta TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE OR REPLACE FUNCTION fn_checar_gastos()
RETURNS TRIGGER AS $$
DECLARE
renda_atual DECIMAL;
    total_gasto DECIMAL;
BEGIN

SELECT renda_mensal INTO renda_atual FROM usuario WHERE id = NEW.id_usuario;


SELECT COALESCE(SUM(t.valor), 0)
INTO total_gasto
FROM transacao t
         JOIN categoria c ON t.id_categoria = c.id
WHERE t.id_usuario = NEW.id_usuario
  AND c.tipo = 'DESPESA'
  AND TO_CHAR(t.data_transacao, 'MM-YYYY') = TO_CHAR(NEW.data_transacao, 'MM-YYYY');


IF (total_gasto > (renda_atual * 0.80)) THEN
        INSERT INTO alerta_financeiro (id_usuario, mensagem)
        VALUES (NEW.id_usuario, 'ALERTA: Seus gastos ultrapassaram 80% da sua renda mensal!');
END IF;

RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER trg_alerta_gastos
    AFTER INSERT
    ON transacao
    FOR EACH ROW
    EXECUTE FUNCTION fn_checar_gastos();
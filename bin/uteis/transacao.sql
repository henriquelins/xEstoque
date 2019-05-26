-- Table: transacao

-- DROP TABLE transacao;

CREATE TABLE transacao
(
  id_transacao serial NOT NULL,
  tipo integer,
  data_transacao date,
  hora_transacao time without time zone,
  id_produto integer,
  valorunitario double precision,
  valorvenda double precision,
  estoqueanterior integer,
  ajuste integer,
  estoqueatual integer,
  id_operador integer,
  CONSTRAINT pk_transacao PRIMARY KEY (id_transacao)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE transacao
  OWNER TO postgres;

-- Index: transacao_pk

-- DROP INDEX transacao_pk;

CREATE UNIQUE INDEX transacao_pk
  ON transacao
  USING btree
  (id_transacao);


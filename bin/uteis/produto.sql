-- Table: produto

-- DROP TABLE produto;

CREATE TABLE produto
(
  id_produto serial NOT NULL,
  categoria character varying(100),
  codigo character varying(20),
  nomedescricao character varying(100),
  valorunitario double precision,
  valorvenda double precision,
  estoqueatual integer,
  unidademedida character varying(50),
  estoqueminimo integer,
  observacoes text,
  foto bytea,
  CONSTRAINT pk_produto PRIMARY KEY (id_produto)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE produto
  OWNER TO postgres;

-- Index: cliente_pk

-- DROP INDEX cliente_pk;

CREATE UNIQUE INDEX cliente_pk
  ON produto
  USING btree
  (id_produto);


-- Table: operador

-- DROP TABLE operador;

CREATE TABLE operador
(
  id_operador serial NOT NULL,
  nome character varying(100),
  login character varying(50),
  senha character varying(10),
  CONSTRAINT pk_operador PRIMARY KEY (id_operador)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE operador
  OWNER TO postgres;

-- Index: operador_pk

-- DROP INDEX operador_pk;

CREATE UNIQUE INDEX operador_pk
  ON operador
  USING btree
  (id_operador);


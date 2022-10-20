-- Table: public.currencies

-- DROP TABLE IF EXISTS public.currencies;

CREATE TABLE IF NOT EXISTS public.currencies
(
    id bigint NOT NULL,
    char_code character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    nominal integer,
    num_code character varying(255) COLLATE pg_catalog."default",
    valuteid character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT currencies_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.currencies
    OWNER to root;
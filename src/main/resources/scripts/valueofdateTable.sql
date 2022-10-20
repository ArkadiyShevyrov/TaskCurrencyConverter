-- Table: public.valueofdate

-- DROP TABLE IF EXISTS public.valueofdate;

CREATE TABLE IF NOT EXISTS public.valueofdate
(
    id bigint NOT NULL,
    date date,
    value double precision,
    currency_id bigint,
    CONSTRAINT valueofdate_pkey PRIMARY KEY (id),
    CONSTRAINT fkjwjdbicfgcjytahwuvqv3ee5p FOREIGN KEY (currency_id)
        REFERENCES public.currencies (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.valueofdate
    OWNER to root;
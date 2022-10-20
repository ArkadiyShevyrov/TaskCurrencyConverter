-- Table: public.conversion

-- DROP TABLE IF EXISTS public.conversion;

CREATE TABLE IF NOT EXISTS public.conversion
(
    id bigint NOT NULL,
    user_id bigint,
    date date,
    value_from double precision,
    value_to double precision,
    value_of_date_from_id bigint,
    value_of_date_to_id bigint,
    CONSTRAINT conversion_pkey PRIMARY KEY (id),
    CONSTRAINT fko2lv5mvbtjwvcicqenfcnktig FOREIGN KEY (value_of_date_to_id)
        REFERENCES public.valueofdate (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkq9wys1lk2kc5tbkovdbem0orq FOREIGN KEY (value_of_date_from_id)
        REFERENCES public.valueofdate (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkqr49py0lo8hdrnqw3h9hhkxey FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.conversion
    OWNER to root;
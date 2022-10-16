SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';




CREATE TABLE public.equipo_usuario (
                                       fk_usuario bigint NOT NULL,
                                       fk_equipo bigint NOT NULL
);


ALTER TABLE public.equipo_usuario OWNER TO mads;


CREATE TABLE public.equipos (
                                id bigint NOT NULL,
                                descripcion character varying(255) NOT NULL,
                                nombre character varying(255) NOT NULL
);


ALTER TABLE public.equipos OWNER TO mads;



CREATE SEQUENCE public.equipos_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE;


ALTER TABLE public.equipos_id_seq OWNER TO mads;


ALTER SEQUENCE public.equipos_id_seq OWNED BY public.equipos.id;



CREATE TABLE public.tareas (
                               id bigint NOT NULL,
                               titulo character varying(255) NOT NULL,
                               usuario_id bigint NOT NULL
);


ALTER TABLE public.tareas OWNER TO mads;


CREATE SEQUENCE public.tareas_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE;


ALTER TABLE public.tareas_id_seq OWNER TO mads;

ALTER SEQUENCE public.tareas_id_seq OWNED BY public.tareas.id;


CREATE TABLE public.usuarios (
                                 id bigint NOT NULL,
                                 nombre character varying(255),
                                 password character varying(255),
                                 email character varying(255) NOT NULL,
                                 fecha_de_nacimiento date
);


ALTER TABLE public.usuarios OWNER TO mads;


CREATE SEQUENCE public.usuarios_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE;


ALTER TABLE public.usuarios_id_seq OWNER TO mads;


ALTER SEQUENCE public.usuarios_id_seq OWNED BY public.usuarios.id;



ALTER TABLE ONLY public.equipos ALTER COLUMN id SET DEFAULT nextval('public.equipos_id_seq'::regclass);



ALTER TABLE ONLY public.tareas ALTER COLUMN id SET DEFAULT nextval('public.tareas_id_seq'::regclass);



ALTER TABLE ONLY public.usuarios ALTER COLUMN id SET DEFAULT nextval('public.usuarios_id_seq'::regclass);




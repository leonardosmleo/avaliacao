################ CRIAÇÃO DO DATABASE #######################
-- Database: avaliacao
-- DROP DATABASE avaliacao;

CREATE DATABASE avaliacao
    WITH 
    OWNER = avaliacao
    ENCODING = 'UTF8'
    LC_COLLATE = 'Portuguese_Brazil.1252'
    LC_CTYPE = 'Portuguese_Brazil.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;


################ CRIAÇÃO DO USUARIO #######################
-- Role: avaliacao
-- DROP ROLE avaliacao;

CREATE ROLE avaliacao WITH
  LOGIN
  SUPERUSER
  INHERIT
  CREATEDB
  CREATEROLE
  REPLICATION
  ENCRYPTED PASSWORD 'SCRAM-SHA-256$4096:QtgWTvir90oZuDEQgMCp9Q==$bgFzKSDCwXP5o7LWfKLl8zFoMtZaL/eFK9+Tvt0Gt04=:4u9u6zZCcdms7iGKKYyV6s0eRcF4NaCn02mAeOAvjf8=';


################ CRIAÇÃO DAS TABELAS #######################
--
-- PostgreSQL database dump
--

-- Dumped from database version 13.2
-- Dumped by pg_dump version 13.2

-- Started on 2021-08-15 04:48:51

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 201 (class 1259 OID 16714)
-- Name: categoria; Type: TABLE; Schema: public; Owner: avaliacao
--

CREATE TABLE public.categoria (
    id_categoria bigint NOT NULL,
    ds_categoria character varying(100) NOT NULL,
    fl_ativo boolean NOT NULL
);


ALTER TABLE public.categoria OWNER TO avaliacao;

--
-- TOC entry 202 (class 1259 OID 16729)
-- Name: curso; Type: TABLE; Schema: public; Owner: avaliacao
--

CREATE TABLE public.curso (
    id_curso bigint NOT NULL,
    ds_assunto character varying(100) NOT NULL,
    dt_inicio date NOT NULL,
    dt_termino date NOT NULL,
    fl_ativo boolean NOT NULL,
    qtde_alunos integer,
    id_categoria bigint NOT NULL
);


ALTER TABLE public.curso OWNER TO avaliacao;

--
-- TOC entry 200 (class 1259 OID 16664)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: avaliacao
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO avaliacao;

--
-- TOC entry 2990 (class 0 OID 16714)
-- Dependencies: 201
-- Data for Name: categoria; Type: TABLE DATA; Schema: public; Owner: avaliacao
--

COPY public.categoria (id_categoria, ds_categoria, fl_ativo) FROM stdin;
14	Comportamental	t
15	Programação	t
16	Qualidade	t
17	Processos	t
18	teste	t
19	teste 2	t
20	teste 44444	t
\.


--
-- TOC entry 2991 (class 0 OID 16729)
-- Dependencies: 202
-- Data for Name: curso; Type: TABLE DATA; Schema: public; Owner: avaliacao
--

COPY public.curso (id_curso, ds_assunto, dt_inicio, dt_termino, fl_ativo, qtde_alunos, id_categoria) FROM stdin;
35	Lógica	2022-01-08	2022-01-09	t	1	16
34	Estrutura de dados	2022-01-02	2022-01-08	t	1	15
\.


--
-- TOC entry 2997 (class 0 OID 0)
-- Dependencies: 200
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: avaliacao
--

SELECT pg_catalog.setval('public.hibernate_sequence', 35, true);


--
-- TOC entry 2855 (class 2606 OID 16718)
-- Name: categoria categoria_pkey; Type: CONSTRAINT; Schema: public; Owner: avaliacao
--

ALTER TABLE ONLY public.categoria
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (id_categoria);


--
-- TOC entry 2857 (class 2606 OID 16733)
-- Name: curso curso_pkey; Type: CONSTRAINT; Schema: public; Owner: avaliacao
--

ALTER TABLE ONLY public.curso
    ADD CONSTRAINT curso_pkey PRIMARY KEY (id_curso);


--
-- TOC entry 2858 (class 2606 OID 16734)
-- Name: curso fk4yftnjuuemdgwgfqp3tw1g9us; Type: FK CONSTRAINT; Schema: public; Owner: avaliacao
--

ALTER TABLE ONLY public.curso
    ADD CONSTRAINT fk4yftnjuuemdgwgfqp3tw1g9us FOREIGN KEY (id_categoria) REFERENCES public.categoria(id_categoria);


-- Completed on 2021-08-15 04:48:53

--
-- PostgreSQL database dump complete
--


--
-- PostgreSQL database dump
--

-- Dumped from database version 10.14
-- Dumped by pg_dump version 10.14

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

ALTER TABLE IF EXISTS ONLY public.disk_film DROP CONSTRAINT IF EXISTS fknagak3u43018f37a38y10wy9f;
ALTER TABLE IF EXISTS ONLY public.ordr_film DROP CONSTRAINT IF EXISTS fkjugisj6s0kkx0jo8kb5d4n5eb;
ALTER TABLE IF EXISTS ONLY public.ordr_film DROP CONSTRAINT IF EXISTS fkh0xtilsdq68ftn6txhvwtq7lr;
ALTER TABLE IF EXISTS ONLY public.disk_film DROP CONSTRAINT IF EXISTS fkdfrpmd2clk44sm5sc5791ftyb;
ALTER TABLE IF EXISTS ONLY public.ordr DROP CONSTRAINT IF EXISTS fkde2tf0h8d6kelck62befxwhb2;
ALTER TABLE IF EXISTS ONLY public.ordr DROP CONSTRAINT IF EXISTS fk99w9gikmwaanreiww5rp2o5cm;
ALTER TABLE IF EXISTS ONLY public.disk DROP CONSTRAINT IF EXISTS uk_g7wroiob50m5gqtabyb9tyvm3;
ALTER TABLE IF EXISTS ONLY public.ordr DROP CONSTRAINT IF EXISTS ordr_pkey;
ALTER TABLE IF EXISTS ONLY public.film DROP CONSTRAINT IF EXISTS film_pkey;
ALTER TABLE IF EXISTS ONLY public.disk DROP CONSTRAINT IF EXISTS disk_pkey;
ALTER TABLE IF EXISTS ONLY public.disk_film DROP CONSTRAINT IF EXISTS disk_film_pkey;
ALTER TABLE IF EXISTS ONLY public.client DROP CONSTRAINT IF EXISTS client_pkey;
DROP TABLE IF EXISTS public.ordr_film;
DROP TABLE IF EXISTS public.ordr;
DROP TABLE IF EXISTS public.film;
DROP TABLE IF EXISTS public.disk_film;
DROP TABLE IF EXISTS public.disk;
DROP TABLE IF EXISTS public.client;
DROP EXTENSION IF EXISTS adminpack;
DROP EXTENSION IF EXISTS plpgsql;
DROP SCHEMA IF EXISTS public;
--
-- Name: DATABASE postgres; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE postgres IS 'default administrative connection database';


--
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- Name: adminpack; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION adminpack; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: client; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.client (
    client_id integer NOT NULL,
    email character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    phone character varying(20) NOT NULL
);


ALTER TABLE public.client OWNER TO postgres;

--
-- Name: disk; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.disk (
    disk_id integer NOT NULL,
    name character varying(255) NOT NULL,
    price integer DEFAULT 0 NOT NULL,
    type character varying(255) NOT NULL
);


ALTER TABLE public.disk OWNER TO postgres;

--
-- Name: disk_film; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.disk_film (
    disk_id integer NOT NULL,
    film_id integer NOT NULL
);


ALTER TABLE public.disk_film OWNER TO postgres;

--
-- Name: film; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.film (
    film_id integer NOT NULL,
    info character varying(255) NOT NULL,
    name character varying(255)
);


ALTER TABLE public.film OWNER TO postgres;

--
-- Name: ordr; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ordr (
    ordr_id integer NOT NULL,
    is_returned boolean NOT NULL,
    request_time date NOT NULL,
    return_time date NOT NULL,
    client_id integer,
    disk_id integer
);


ALTER TABLE public.ordr OWNER TO postgres;

--
-- Name: ordr_film; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ordr_film (
    film_id integer NOT NULL,
    ordr_id integer NOT NULL
);


ALTER TABLE public.ordr_film OWNER TO postgres;

--
-- Data for Name: client; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.client (client_id, email, name, phone) FROM stdin;
\.


--
-- Data for Name: disk; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.disk (disk_id, name, price, type) FROM stdin;
\.


--
-- Data for Name: disk_film; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.disk_film (disk_id, film_id) FROM stdin;
\.


--
-- Data for Name: film; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.film (film_id, info, name) FROM stdin;
\.


--
-- Data for Name: ordr; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ordr (ordr_id, is_returned, request_time, return_time, client_id, disk_id) FROM stdin;
\.


--
-- Data for Name: ordr_film; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ordr_film (film_id, ordr_id) FROM stdin;
\.


--
-- Name: client client_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_pkey PRIMARY KEY (client_id);


--
-- Name: disk_film disk_film_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.disk_film
    ADD CONSTRAINT disk_film_pkey PRIMARY KEY (film_id, disk_id);


--
-- Name: disk disk_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.disk
    ADD CONSTRAINT disk_pkey PRIMARY KEY (disk_id);


--
-- Name: film film_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.film
    ADD CONSTRAINT film_pkey PRIMARY KEY (film_id);


--
-- Name: ordr ordr_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ordr
    ADD CONSTRAINT ordr_pkey PRIMARY KEY (ordr_id);


--
-- Name: disk uk_g7wroiob50m5gqtabyb9tyvm3; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.disk
    ADD CONSTRAINT uk_g7wroiob50m5gqtabyb9tyvm3 UNIQUE (name);


--
-- Name: ordr fk99w9gikmwaanreiww5rp2o5cm; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ordr
    ADD CONSTRAINT fk99w9gikmwaanreiww5rp2o5cm FOREIGN KEY (disk_id) REFERENCES public.disk(disk_id);


--
-- Name: ordr fkde2tf0h8d6kelck62befxwhb2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ordr
    ADD CONSTRAINT fkde2tf0h8d6kelck62befxwhb2 FOREIGN KEY (client_id) REFERENCES public.client(client_id);


--
-- Name: disk_film fkdfrpmd2clk44sm5sc5791ftyb; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.disk_film
    ADD CONSTRAINT fkdfrpmd2clk44sm5sc5791ftyb FOREIGN KEY (film_id) REFERENCES public.film(film_id);


--
-- Name: ordr_film fkh0xtilsdq68ftn6txhvwtq7lr; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ordr_film
    ADD CONSTRAINT fkh0xtilsdq68ftn6txhvwtq7lr FOREIGN KEY (film_id) REFERENCES public.ordr(ordr_id);


--
-- Name: ordr_film fkjugisj6s0kkx0jo8kb5d4n5eb; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ordr_film
    ADD CONSTRAINT fkjugisj6s0kkx0jo8kb5d4n5eb FOREIGN KEY (ordr_id) REFERENCES public.film(film_id);


--
-- Name: disk_film fknagak3u43018f37a38y10wy9f; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.disk_film
    ADD CONSTRAINT fknagak3u43018f37a38y10wy9f FOREIGN KEY (disk_id) REFERENCES public.disk(disk_id);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--


--V0__Create_database.sql

--CREATE ROLE cityapiuser WITH
--	LOGIN
--	NOSUPERUSER
--	CREATEDB
--	CREATEROLE
--	INHERIT
--	NOREPLICATION
--	CONNECTION LIMIT -1
--	PASSWORD 'cityapipassword';
--
--CREATE DATABASE cityapi
--    WITH
--    OWNER = cityapi
--    ENCODING = 'UTF8'
--    CONNECTION LIMIT = -1;
--
--COMMENT ON DATABASE cityapi
--    IS 'Database for information about Brazilian cities';
--
--REVOKE CREATE ON SCHEMA public FROM PUBLIC;
--CREATE SCHEMA IF NOT EXIST city AUTHORIZATION cityapiuser;

CREATE TABLE IF NOT EXISTS city.city
(
    ibge_id bigint NOT NULL,
    uf character varying(2) NOT NULL,
    name character varying(50) NOT NULL,
    is_capital boolean NOT NULL,
    longitude double precision NOT NULL,
    latitude double precision NOT NULL,
    alternative_names character varying(50),
    microregion character varying(50),
    mesoregion character varying(50),
    CONSTRAINT "PRIMARY_KEY" PRIMARY KEY (ibge_id),
    CONSTRAINT "UNIQUE_KEY" UNIQUE (uf, name)
        USING INDEX TABLESPACE city_api_ts
)
WITH (
    OIDS = FALSE
)
TABLESPACE city_api_ts;

ALTER TABLE city.city
    OWNER to cityapiuser;
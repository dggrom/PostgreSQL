PGDMP     9            	        w         
   HellowPost    10.8    10.8     
           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false                       1262    24878 
   HellowPost    DATABASE     �   CREATE DATABASE "HellowPost" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Russian_Russia.1251' LC_CTYPE = 'Russian_Russia.1251';
    DROP DATABASE "HellowPost";
             postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false                       0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    3                        3079    12924    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false                       0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            �            1259    24879    kontragent_id_seq    SEQUENCE     z   CREATE SEQUENCE public.kontragent_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.kontragent_id_seq;
       public       postgres    false    3            �            1259    24881 
   Kontragent    TABLE     �   CREATE TABLE public."Kontragent" (
    id_kont regclass DEFAULT nextval('public.kontragent_id_seq'::regclass) NOT NULL,
    name_kont text NOT NULL,
    deleted_kont boolean DEFAULT false NOT NULL
);
     DROP TABLE public."Kontragent";
       public         postgres    false    196    3            �            1259    24917    nomen_id_sequences    SEQUENCE     {   CREATE SEQUENCE public.nomen_id_sequences
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.nomen_id_sequences;
       public       postgres    false    3            �            1259    24909    Nomenclature    TABLE     �   CREATE TABLE public."Nomenclature" (
    id_nomen integer DEFAULT nextval('public.nomen_id_sequences'::regclass) NOT NULL,
    name_nomen text,
    deleted_nomen boolean DEFAULT false
);
 "   DROP TABLE public."Nomenclature";
       public         postgres    false    201    3            �            1259    24889    viewcomcon_id    SEQUENCE     v   CREATE SEQUENCE public.viewcomcon_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.viewcomcon_id;
       public       postgres    false    3            �            1259    24891    ViewComingConsumption    TABLE     �   CREATE TABLE public."ViewComingConsumption" (
    id_viewcc integer DEFAULT nextval('public.viewcomcon_id'::regclass) NOT NULL,
    name_viewcc text NOT NULL,
    deleted_viewcc boolean DEFAULT false NOT NULL
);
 +   DROP TABLE public."ViewComingConsumption";
       public         postgres    false    198    3                      0    24881 
   Kontragent 
   TABLE DATA               H   COPY public."Kontragent" (id_kont, name_kont, deleted_kont) FROM stdin;
    public       postgres    false    197   6                 0    24909    Nomenclature 
   TABLE DATA               M   COPY public."Nomenclature" (id_nomen, name_nomen, deleted_nomen) FROM stdin;
    public       postgres    false    200   �                 0    24891    ViewComingConsumption 
   TABLE DATA               Y   COPY public."ViewComingConsumption" (id_viewcc, name_viewcc, deleted_viewcc) FROM stdin;
    public       postgres    false    199   �                  0    0    kontragent_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.kontragent_id_seq', 7, true);
            public       postgres    false    196                       0    0    nomen_id_sequences    SEQUENCE SET     @   SELECT pg_catalog.setval('public.nomen_id_sequences', 1, true);
            public       postgres    false    201                       0    0    viewcomcon_id    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.viewcomcon_id', 3, true);
            public       postgres    false    198            �
           2606    24900    Kontragent Kontragent_pkey 
   CONSTRAINT     a   ALTER TABLE ONLY public."Kontragent"
    ADD CONSTRAINT "Kontragent_pkey" PRIMARY KEY (id_kont);
 H   ALTER TABLE ONLY public."Kontragent" DROP CONSTRAINT "Kontragent_pkey";
       public         postgres    false    197            �
           2606    24916    Nomenclature Nomenclature_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public."Nomenclature"
    ADD CONSTRAINT "Nomenclature_pkey" PRIMARY KEY (id_nomen);
 L   ALTER TABLE ONLY public."Nomenclature" DROP CONSTRAINT "Nomenclature_pkey";
       public         postgres    false    200            �
           2606    24902 1   ViewComingConsumption ViewComingСonsumption_pkey 
   CONSTRAINT     z   ALTER TABLE ONLY public."ViewComingConsumption"
    ADD CONSTRAINT "ViewComingСonsumption_pkey" PRIMARY KEY (id_viewcc);
 _   ALTER TABLE ONLY public."ViewComingConsumption" DROP CONSTRAINT "ViewComingСonsumption_pkey";
       public         postgres    false    199               V   x�3�0�¾���.l���¾�8Ӹ9��	gqJJJ1�e�YRYY	d�q^��[@(`�ya1��(��b����=... Ya)1         #   x�3估��/l��pa�Ŧ�ݜi\1z\\\ �[�            x�3�tK-*.�L�2���@N� �a	j     
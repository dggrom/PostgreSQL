PGDMP     0    0                w         
   HellowPost    10.7    11.2     -           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            .           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            /           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            0           1262    16393 
   HellowPost    DATABASE     �   CREATE DATABASE "HellowPost" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Russian_Russia.1251' LC_CTYPE = 'Russian_Russia.1251';
    DROP DATABASE "HellowPost";
             postgres    false            �            1259    16430    kontragent_id_seq    SEQUENCE     z   CREATE SEQUENCE public.kontragent_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.kontragent_id_seq;
       public       postgres    false            �            1259    16414 
   Kontragent    TABLE     �   CREATE TABLE public."Kontragent" (
    id_kont regclass DEFAULT nextval('public.kontragent_id_seq'::regclass) NOT NULL,
    name_kont text NOT NULL,
    deleted_kont boolean DEFAULT false NOT NULL
);
     DROP TABLE public."Kontragent";
       public         postgres    false    197            �            1259    16454    viewcomcon_id    SEQUENCE     v   CREATE SEQUENCE public.viewcomcon_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.viewcomcon_id;
       public       postgres    false            �            1259    16435    ViewComingСonsumption    TABLE     �   CREATE TABLE public."ViewComingСonsumption" (
    id_viewcc integer DEFAULT nextval('public.viewcomcon_id'::regclass) NOT NULL,
    name_viewcc text NOT NULL,
    deleted_viewcc boolean DEFAULT false NOT NULL
);
 ,   DROP TABLE public."ViewComingСonsumption";
       public         postgres    false    199            '          0    16414 
   Kontragent 
   TABLE DATA               H   COPY public."Kontragent" (id_kont, name_kont, deleted_kont) FROM stdin;
    public       postgres    false    196   �       )          0    16435    ViewComingСonsumption 
   TABLE DATA               Z   COPY public."ViewComingСonsumption" (id_viewcc, name_viewcc, deleted_viewcc) FROM stdin;
    public       postgres    false    198          1           0    0    kontragent_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.kontragent_id_seq', 6, true);
            public       postgres    false    197            2           0    0    viewcomcon_id    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.viewcomcon_id', 2, true);
            public       postgres    false    199            �
           2606    16433    Kontragent Kontragent_pkey 
   CONSTRAINT     a   ALTER TABLE ONLY public."Kontragent"
    ADD CONSTRAINT "Kontragent_pkey" PRIMARY KEY (id_kont);
 H   ALTER TABLE ONLY public."Kontragent" DROP CONSTRAINT "Kontragent_pkey";
       public         postgres    false    196            �
           2606    16449 2   ViewComingСonsumption ViewComingСonsumption_pkey 
   CONSTRAINT     {   ALTER TABLE ONLY public."ViewComingСonsumption"
    ADD CONSTRAINT "ViewComingСonsumption_pkey" PRIMARY KEY (id_viewcc);
 `   ALTER TABLE ONLY public."ViewComingСonsumption" DROP CONSTRAINT "ViewComingСonsumption_pkey";
       public         postgres    false    198            '   G   x�3�0�¾���.l���¾�8Ӹ9��	gqJJJ1�e�YRYY	d�q������y1z\\\ ��      )      x�3�tK-*.�L����� Ka     
PGDMP         5                w         
   HellowPost    10.7    11.2                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false                       1262    16393 
   HellowPost    DATABASE     �   CREATE DATABASE "HellowPost" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Russian_Russia.1251' LC_CTYPE = 'Russian_Russia.1251';
    DROP DATABASE "HellowPost";
             postgres    false            �            1259    16414 
   Kontragent    TABLE     �   CREATE TABLE public."Kontragent" (
    id_kont integer NOT NULL,
    name_kont text NOT NULL,
    deleted_kont boolean DEFAULT false NOT NULL
);
     DROP TABLE public."Kontragent";
       public         postgres    false                      0    16414 
   Kontragent 
   TABLE DATA               H   COPY public."Kontragent" (id_kont, name_kont, deleted_kont) FROM stdin;
    public       postgres    false    196   �       �
           2606    16421    Kontragent Kontragent_pkey 
   CONSTRAINT     a   ALTER TABLE ONLY public."Kontragent"
    ADD CONSTRAINT "Kontragent_pkey" PRIMARY KEY (id_kont);
 H   ALTER TABLE ONLY public."Kontragent" DROP CONSTRAINT "Kontragent_pkey";
       public         postgres    false    196               ?   x�3�0�¾��^�ě.6^�uaǅ��i\���\쿰�b�I6\�{�(���� ���     
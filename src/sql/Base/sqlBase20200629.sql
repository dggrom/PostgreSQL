PGDMP     $    .                x         
   HellowPost     12.3 (Ubuntu 12.3-1.pgdg18.04+1)     12.3 (Ubuntu 12.3-1.pgdg18.04+1) /    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16490 
   HellowPost    DATABASE     ~   CREATE DATABASE "HellowPost" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'ru_RU.UTF-8' LC_CTYPE = 'ru_RU.UTF-8';
    DROP DATABASE "HellowPost";
                postgres    false            �            1259    16491    dokcoming_id    SEQUENCE     u   CREATE SEQUENCE public.dokcoming_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.dokcoming_id;
       public          postgres    false            �            1259    16493 	   DokComing    TABLE     F  CREATE TABLE public."DokComing" (
    id_dcom regclass DEFAULT nextval('public.dokcoming_id'::regclass) NOT NULL,
    "SumMoney_dcom" real,
    "Komment_dcom" text,
    id_kont regclass NOT NULL,
    id_viewcc integer NOT NULL,
    deleted_dcom boolean DEFAULT false NOT NULL,
    date_dcom date DEFAULT '2000-01-01'::date
);
    DROP TABLE public."DokComing";
       public         heap    postgres    false    202            �            1259    16501    dokcomingtablemoney_id    SEQUENCE        CREATE SEQUENCE public.dokcomingtablemoney_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.dokcomingtablemoney_id;
       public          postgres    false            �            1259    16503    DokComingTableMoney    TABLE     
  CREATE TABLE public."DokComingTableMoney" (
    id_dcomtm regclass DEFAULT nextval('public.dokcomingtablemoney_id'::regclass) NOT NULL,
    id_dcom regclass NOT NULL,
    id_nomen regclass NOT NULL,
    kol_dcomtm real,
    sum_docmtm real,
    price_docmtm real
);
 )   DROP TABLE public."DokComingTableMoney";
       public         heap    postgres    false    204            �            1259    16507    dokconsumption_id    SEQUENCE     z   CREATE SEQUENCE public.dokconsumption_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.dokconsumption_id;
       public          postgres    false            �            1259    16509    DokConsumption    TABLE     <  CREATE TABLE public."DokConsumption" (
    id_dcon regclass DEFAULT nextval('public.dokconsumption_id'::regclass) NOT NULL,
    "SumMoney_dcon" integer,
    "Komment_dcon" text,
    id_kont regclass NOT NULL,
    id_viewcc integer NOT NULL,
    deleted_dcon boolean,
    date_dcon date DEFAULT '2000-01-01'::date
);
 $   DROP TABLE public."DokConsumption";
       public         heap    postgres    false    206            �            1259    16516    DokConsumptionTableMoney    TABLE     
  CREATE TABLE public."DokConsumptionTableMoney" (
    id_dcontm regclass DEFAULT nextval('public.dokconsumption_id'::regclass) NOT NULL,
    id_dcon regclass NOT NULL,
    id_nomen regclass NOT NULL,
    kol_dcontm real,
    sum_dcontm real,
    price_dcontm real
);
 .   DROP TABLE public."DokConsumptionTableMoney";
       public         heap    postgres    false    206            �            1259    16520    kontragent_id_seq    SEQUENCE     z   CREATE SEQUENCE public.kontragent_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.kontragent_id_seq;
       public          postgres    false            �            1259    16522 
   Kontragent    TABLE     �   CREATE TABLE public."Kontragent" (
    id_kont regclass DEFAULT nextval('public.kontragent_id_seq'::regclass) NOT NULL,
    name_kont text NOT NULL,
    deleted_kont boolean DEFAULT false NOT NULL
);
     DROP TABLE public."Kontragent";
       public         heap    postgres    false    209            �            1259    16530    nomen_id_sequences    SEQUENCE     {   CREATE SEQUENCE public.nomen_id_sequences
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.nomen_id_sequences;
       public          postgres    false            �            1259    16532    Nomenclature    TABLE     �   CREATE TABLE public."Nomenclature" (
    id_nomen regclass DEFAULT nextval('public.nomen_id_sequences'::regclass) NOT NULL,
    name_nomen text,
    deleted_nomen boolean DEFAULT false
);
 "   DROP TABLE public."Nomenclature";
       public         heap    postgres    false    211            �            1259    16540    viewcomcon_id    SEQUENCE     v   CREATE SEQUENCE public.viewcomcon_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.viewcomcon_id;
       public          postgres    false            �            1259    16542    ViewComingConsumption    TABLE     �   CREATE TABLE public."ViewComingConsumption" (
    id_viewcc integer DEFAULT nextval('public.viewcomcon_id'::regclass) NOT NULL,
    name_viewcc text NOT NULL,
    deleted_viewcc boolean DEFAULT false NOT NULL
);
 +   DROP TABLE public."ViewComingConsumption";
       public         heap    postgres    false    213            �            1259    16550    dokconsumptiontablemoney_id    SEQUENCE     �   CREATE SEQUENCE public.dokconsumptiontablemoney_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public.dokconsumptiontablemoney_id;
       public          postgres    false            �          0    16493 	   DokComing 
   TABLE DATA           |   COPY public."DokComing" (id_dcom, "SumMoney_dcom", "Komment_dcom", id_kont, id_viewcc, deleted_dcom, date_dcom) FROM stdin;
    public          postgres    false    203   b;       �          0    16503    DokComingTableMoney 
   TABLE DATA           s   COPY public."DokComingTableMoney" (id_dcomtm, id_dcom, id_nomen, kol_dcomtm, sum_docmtm, price_docmtm) FROM stdin;
    public          postgres    false    205   M<       �          0    16509    DokConsumption 
   TABLE DATA           �   COPY public."DokConsumption" (id_dcon, "SumMoney_dcon", "Komment_dcon", id_kont, id_viewcc, deleted_dcon, date_dcon) FROM stdin;
    public          postgres    false    207   {<       �          0    16516    DokConsumptionTableMoney 
   TABLE DATA           x   COPY public."DokConsumptionTableMoney" (id_dcontm, id_dcon, id_nomen, kol_dcontm, sum_dcontm, price_dcontm) FROM stdin;
    public          postgres    false    208   �<       �          0    16522 
   Kontragent 
   TABLE DATA           H   COPY public."Kontragent" (id_kont, name_kont, deleted_kont) FROM stdin;
    public          postgres    false    210   
=       �          0    16532    Nomenclature 
   TABLE DATA           M   COPY public."Nomenclature" (id_nomen, name_nomen, deleted_nomen) FROM stdin;
    public          postgres    false    212   �=       �          0    16542    ViewComingConsumption 
   TABLE DATA           Y   COPY public."ViewComingConsumption" (id_viewcc, name_viewcc, deleted_viewcc) FROM stdin;
    public          postgres    false    214   >       �           0    0    dokcoming_id    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.dokcoming_id', 19, true);
          public          postgres    false    202            �           0    0    dokcomingtablemoney_id    SEQUENCE SET     E   SELECT pg_catalog.setval('public.dokcomingtablemoney_id', 55, true);
          public          postgres    false    204            �           0    0    dokconsumption_id    SEQUENCE SET     @   SELECT pg_catalog.setval('public.dokconsumption_id', 30, true);
          public          postgres    false    206            �           0    0    dokconsumptiontablemoney_id    SEQUENCE SET     J   SELECT pg_catalog.setval('public.dokconsumptiontablemoney_id', 1, false);
          public          postgres    false    215            �           0    0    kontragent_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.kontragent_id_seq', 12, true);
          public          postgres    false    209            �           0    0    nomen_id_sequences    SEQUENCE SET     @   SELECT pg_catalog.setval('public.nomen_id_sequences', 5, true);
          public          postgres    false    211            �           0    0    viewcomcon_id    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.viewcomcon_id', 6, true);
          public          postgres    false    213            .           2606    16553 ,   DokComingTableMoney DokComingTableMoney_pkey 
   CONSTRAINT     u   ALTER TABLE ONLY public."DokComingTableMoney"
    ADD CONSTRAINT "DokComingTableMoney_pkey" PRIMARY KEY (id_dcomtm);
 Z   ALTER TABLE ONLY public."DokComingTableMoney" DROP CONSTRAINT "DokComingTableMoney_pkey";
       public            postgres    false    205            ,           2606    16555    DokComing DokComing_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY public."DokComing"
    ADD CONSTRAINT "DokComing_pkey" PRIMARY KEY (id_dcom);
 F   ALTER TABLE ONLY public."DokComing" DROP CONSTRAINT "DokComing_pkey";
       public            postgres    false    203            2           2606    16557 6   DokConsumptionTableMoney DokConsumptionTableMoney_pkey 
   CONSTRAINT        ALTER TABLE ONLY public."DokConsumptionTableMoney"
    ADD CONSTRAINT "DokConsumptionTableMoney_pkey" PRIMARY KEY (id_dcontm);
 d   ALTER TABLE ONLY public."DokConsumptionTableMoney" DROP CONSTRAINT "DokConsumptionTableMoney_pkey";
       public            postgres    false    208            0           2606    16559 "   DokConsumption DokConsumption_pkey 
   CONSTRAINT     i   ALTER TABLE ONLY public."DokConsumption"
    ADD CONSTRAINT "DokConsumption_pkey" PRIMARY KEY (id_dcon);
 P   ALTER TABLE ONLY public."DokConsumption" DROP CONSTRAINT "DokConsumption_pkey";
       public            postgres    false    207            4           2606    16561    Kontragent Kontragent_pkey 
   CONSTRAINT     a   ALTER TABLE ONLY public."Kontragent"
    ADD CONSTRAINT "Kontragent_pkey" PRIMARY KEY (id_kont);
 H   ALTER TABLE ONLY public."Kontragent" DROP CONSTRAINT "Kontragent_pkey";
       public            postgres    false    210            6           2606    16563    Nomenclature Nomenclature_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public."Nomenclature"
    ADD CONSTRAINT "Nomenclature_pkey" PRIMARY KEY (id_nomen);
 L   ALTER TABLE ONLY public."Nomenclature" DROP CONSTRAINT "Nomenclature_pkey";
       public            postgres    false    212            8           2606    16565 1   ViewComingConsumption ViewComingСonsumption_pkey 
   CONSTRAINT     z   ALTER TABLE ONLY public."ViewComingConsumption"
    ADD CONSTRAINT "ViewComingСonsumption_pkey" PRIMARY KEY (id_viewcc);
 _   ALTER TABLE ONLY public."ViewComingConsumption" DROP CONSTRAINT "ViewComingСonsumption_pkey";
       public            postgres    false    214            ;           2606    16566    DokComingTableMoney id_dcom_FK    FK CONSTRAINT     �   ALTER TABLE ONLY public."DokComingTableMoney"
    ADD CONSTRAINT "id_dcom_FK" FOREIGN KEY (id_dcom) REFERENCES public."DokComing"(id_dcom) NOT VALID;
 L   ALTER TABLE ONLY public."DokComingTableMoney" DROP CONSTRAINT "id_dcom_FK";
       public          postgres    false    2860    203    205            ?           2606    16571 #   DokConsumptionTableMoney id_dcom_FK    FK CONSTRAINT     �   ALTER TABLE ONLY public."DokConsumptionTableMoney"
    ADD CONSTRAINT "id_dcom_FK" FOREIGN KEY (id_dcon) REFERENCES public."DokConsumption"(id_dcon);
 Q   ALTER TABLE ONLY public."DokConsumptionTableMoney" DROP CONSTRAINT "id_dcom_FK";
       public          postgres    false    2864    207    208            9           2606    16576    DokComing id_kont_FK    FK CONSTRAINT     �   ALTER TABLE ONLY public."DokComing"
    ADD CONSTRAINT "id_kont_FK" FOREIGN KEY (id_kont) REFERENCES public."Kontragent"(id_kont) NOT VALID;
 B   ALTER TABLE ONLY public."DokComing" DROP CONSTRAINT "id_kont_FK";
       public          postgres    false    210    2868    203            =           2606    16581    DokConsumption id_kont_FK    FK CONSTRAINT     �   ALTER TABLE ONLY public."DokConsumption"
    ADD CONSTRAINT "id_kont_FK" FOREIGN KEY (id_kont) REFERENCES public."Kontragent"(id_kont) NOT VALID;
 G   ALTER TABLE ONLY public."DokConsumption" DROP CONSTRAINT "id_kont_FK";
       public          postgres    false    210    207    2868            <           2606    16586    DokComingTableMoney id_nomen_FK    FK CONSTRAINT     �   ALTER TABLE ONLY public."DokComingTableMoney"
    ADD CONSTRAINT "id_nomen_FK" FOREIGN KEY (id_nomen) REFERENCES public."Nomenclature"(id_nomen) NOT VALID;
 M   ALTER TABLE ONLY public."DokComingTableMoney" DROP CONSTRAINT "id_nomen_FK";
       public          postgres    false    212    205    2870            @           2606    16591 $   DokConsumptionTableMoney id_nomen_FK    FK CONSTRAINT     �   ALTER TABLE ONLY public."DokConsumptionTableMoney"
    ADD CONSTRAINT "id_nomen_FK" FOREIGN KEY (id_nomen) REFERENCES public."Nomenclature"(id_nomen);
 R   ALTER TABLE ONLY public."DokConsumptionTableMoney" DROP CONSTRAINT "id_nomen_FK";
       public          postgres    false    212    2870    208            :           2606    16596    DokComing id_view_FK    FK CONSTRAINT     �   ALTER TABLE ONLY public."DokComing"
    ADD CONSTRAINT "id_view_FK" FOREIGN KEY (id_viewcc) REFERENCES public."ViewComingConsumption"(id_viewcc) NOT VALID;
 B   ALTER TABLE ONLY public."DokComing" DROP CONSTRAINT "id_view_FK";
       public          postgres    false    214    2872    203            >           2606    16601    DokConsumption id_viewcc_FK    FK CONSTRAINT     �   ALTER TABLE ONLY public."DokConsumption"
    ADD CONSTRAINT "id_viewcc_FK" FOREIGN KEY (id_viewcc) REFERENCES public."ViewComingConsumption"(id_viewcc) NOT VALID;
 I   ALTER TABLE ONLY public."DokConsumption" DROP CONSTRAINT "id_viewcc_FK";
       public          postgres    false    207    214    2872            �   �   x�u�=
�@��w��n�Lm%v�6�D�������G �@D�W������ͼ�1��b)h)�'?:�ȡ��������P�Ȗ�OC��v�f��&cD7�l�7����L�(@h�K}����
�xP��H��9��1^�ۖob"5�����q�����-�k��bD&��Jzѝ��ҌD�N���C]�W��J�폓B%6D���'�� g]z�      �      x�36�4��4�4�450�������� 'IT      �   5   x�3�414�LLL�4�4�L�4202�50�52�2��8�2��2�\1z\\\ *�	�      �   :   x���	 0�u��&}����	(x���1�n}g�T6���"ǰ4C�U�[�^#�q�
      �   �   x�5N;�0��� R�='`A<�*C�$3�9k/�P��=�ˍp��=ؖ���S�r/��t�Y�pf�u`1�t�l��9�O�`�B�D���Ce]#�?=���Kn�c��/!��[B�8�q�H���v��ia����Af      �   a   x�3�0�®��/����3�ː���;.l���bÅ��.v�8/L���®���.��4�0��LS��Ĕ�����=... �b,�      �   I   x�3�0���_���.l�L�2�0��>��f ǈ�-����($�����˄3�3��k ���� ���     
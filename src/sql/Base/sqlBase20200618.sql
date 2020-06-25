PGDMP     -                    x         
   HellowPost "   10.13 (Ubuntu 10.13-1.pgdg18.04+1)     12.3 (Ubuntu 12.3-1.pgdg18.04+1) /    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16385 
   HellowPost    DATABASE     ~   CREATE DATABASE "HellowPost" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'ru_RU.UTF-8' LC_CTYPE = 'ru_RU.UTF-8';
    DROP DATABASE "HellowPost";
                postgres    false            �            1259    16430    dokcoming_id    SEQUENCE     u   CREATE SEQUENCE public.dokcoming_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.dokcoming_id;
       public          postgres    false            �            1259    16422 	   DokComing    TABLE       CREATE TABLE public."DokComing" (
    id_dcom regclass DEFAULT nextval('public.dokcoming_id'::regclass) NOT NULL,
    "SumMoney_dcom" real,
    "Komment_dcom" text,
    id_kont regclass NOT NULL,
    id_viewcc integer NOT NULL,
    deleted_dcom boolean DEFAULT false NOT NULL
);
    DROP TABLE public."DokComing";
       public            postgres    false    203            �            1259    16444    dokcomingtablemoney_id    SEQUENCE        CREATE SEQUENCE public.dokcomingtablemoney_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.dokcomingtablemoney_id;
       public          postgres    false            �            1259    16446    DokComingTableMoney    TABLE     �   CREATE TABLE public."DokComingTableMoney" (
    id_dcomtm regclass DEFAULT nextval('public.dokcomingtablemoney_id'::regclass) NOT NULL,
    id_dcom regclass NOT NULL,
    id_nomen regclass NOT NULL,
    kol_dcomtm real,
    sum_docmtm real
);
 )   DROP TABLE public."DokComingTableMoney";
       public            postgres    false    206            �            1259    16433    dokconsumption_id    SEQUENCE     z   CREATE SEQUENCE public.dokconsumption_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.dokconsumption_id;
       public          postgres    false            �            1259    16435    DokConsumption    TABLE       CREATE TABLE public."DokConsumption" (
    id_dcon regclass DEFAULT nextval('public.dokconsumption_id'::regclass) NOT NULL,
    "SumMoney_dcon" integer,
    "Komment_dcon" text,
    id_kont regclass NOT NULL,
    id_viewcc integer NOT NULL,
    deleted_dcon boolean
);
 $   DROP TABLE public."DokConsumption";
       public            postgres    false    204            �            1259    16467    DokConsumptionTableMoney    TABLE     �   CREATE TABLE public."DokConsumptionTableMoney" (
    id_dcontm regclass DEFAULT nextval('public.dokconsumption_id'::regclass) NOT NULL,
    id_dcon regclass NOT NULL,
    id_nomen regclass NOT NULL,
    kol_dcontm real,
    sum_dcontm real
);
 .   DROP TABLE public."DokConsumptionTableMoney";
       public            postgres    false    204            �            1259    16386    kontragent_id_seq    SEQUENCE     z   CREATE SEQUENCE public.kontragent_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.kontragent_id_seq;
       public          postgres    false            �            1259    16388 
   Kontragent    TABLE     �   CREATE TABLE public."Kontragent" (
    id_kont regclass DEFAULT nextval('public.kontragent_id_seq'::regclass) NOT NULL,
    name_kont text NOT NULL,
    deleted_kont boolean DEFAULT false NOT NULL
);
     DROP TABLE public."Kontragent";
       public            postgres    false    196            �            1259    16396    nomen_id_sequences    SEQUENCE     {   CREATE SEQUENCE public.nomen_id_sequences
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.nomen_id_sequences;
       public          postgres    false            �            1259    16398    Nomenclature    TABLE     �   CREATE TABLE public."Nomenclature" (
    id_nomen regclass DEFAULT nextval('public.nomen_id_sequences'::regclass) NOT NULL,
    name_nomen text,
    deleted_nomen boolean DEFAULT false
);
 "   DROP TABLE public."Nomenclature";
       public            postgres    false    198            �            1259    16406    viewcomcon_id    SEQUENCE     v   CREATE SEQUENCE public.viewcomcon_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.viewcomcon_id;
       public          postgres    false            �            1259    16408    ViewComingConsumption    TABLE     �   CREATE TABLE public."ViewComingConsumption" (
    id_viewcc integer DEFAULT nextval('public.viewcomcon_id'::regclass) NOT NULL,
    name_viewcc text NOT NULL,
    deleted_viewcc boolean DEFAULT false NOT NULL
);
 +   DROP TABLE public."ViewComingConsumption";
       public            postgres    false    200            �            1259    16465    dokconsumptiontablemoney_id    SEQUENCE     �   CREATE SEQUENCE public.dokconsumptiontablemoney_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public.dokconsumptiontablemoney_id;
       public          postgres    false            �          0    16422 	   DokComing 
   TABLE DATA           q   COPY public."DokComing" (id_dcom, "SumMoney_dcom", "Komment_dcom", id_kont, id_viewcc, deleted_dcom) FROM stdin;
    public          postgres    false    202   �:       �          0    16446    DokComingTableMoney 
   TABLE DATA           e   COPY public."DokComingTableMoney" (id_dcomtm, id_dcom, id_nomen, kol_dcomtm, sum_docmtm) FROM stdin;
    public          postgres    false    207   V;       �          0    16435    DokConsumption 
   TABLE DATA           v   COPY public."DokConsumption" (id_dcon, "SumMoney_dcon", "Komment_dcon", id_kont, id_viewcc, deleted_dcon) FROM stdin;
    public          postgres    false    205   �;       �          0    16467    DokConsumptionTableMoney 
   TABLE DATA           j   COPY public."DokConsumptionTableMoney" (id_dcontm, id_dcon, id_nomen, kol_dcontm, sum_dcontm) FROM stdin;
    public          postgres    false    209   �;       �          0    16388 
   Kontragent 
   TABLE DATA           H   COPY public."Kontragent" (id_kont, name_kont, deleted_kont) FROM stdin;
    public          postgres    false    197   �;       �          0    16398    Nomenclature 
   TABLE DATA           M   COPY public."Nomenclature" (id_nomen, name_nomen, deleted_nomen) FROM stdin;
    public          postgres    false    199   �<       �          0    16408    ViewComingConsumption 
   TABLE DATA           Y   COPY public."ViewComingConsumption" (id_viewcc, name_viewcc, deleted_viewcc) FROM stdin;
    public          postgres    false    201   �<       �           0    0    dokcoming_id    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.dokcoming_id', 18, true);
          public          postgres    false    203            �           0    0    dokcomingtablemoney_id    SEQUENCE SET     E   SELECT pg_catalog.setval('public.dokcomingtablemoney_id', 36, true);
          public          postgres    false    206            �           0    0    dokconsumption_id    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.dokconsumption_id', 2, true);
          public          postgres    false    204            �           0    0    dokconsumptiontablemoney_id    SEQUENCE SET     J   SELECT pg_catalog.setval('public.dokconsumptiontablemoney_id', 1, false);
          public          postgres    false    208            �           0    0    kontragent_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.kontragent_id_seq', 11, true);
          public          postgres    false    196            �           0    0    nomen_id_sequences    SEQUENCE SET     @   SELECT pg_catalog.setval('public.nomen_id_sequences', 5, true);
          public          postgres    false    198            �           0    0    viewcomcon_id    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.viewcomcon_id', 5, true);
          public          postgres    false    200            #           2606    16451 ,   DokComingTableMoney DokComingTableMoney_pkey 
   CONSTRAINT     u   ALTER TABLE ONLY public."DokComingTableMoney"
    ADD CONSTRAINT "DokComingTableMoney_pkey" PRIMARY KEY (id_dcomtm);
 Z   ALTER TABLE ONLY public."DokComingTableMoney" DROP CONSTRAINT "DokComingTableMoney_pkey";
       public            postgres    false    207                       2606    16429    DokComing DokComing_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY public."DokComing"
    ADD CONSTRAINT "DokComing_pkey" PRIMARY KEY (id_dcom);
 F   ALTER TABLE ONLY public."DokComing" DROP CONSTRAINT "DokComing_pkey";
       public            postgres    false    202            %           2606    16472 6   DokConsumptionTableMoney DokConsumptionTableMoney_pkey 
   CONSTRAINT        ALTER TABLE ONLY public."DokConsumptionTableMoney"
    ADD CONSTRAINT "DokConsumptionTableMoney_pkey" PRIMARY KEY (id_dcontm);
 d   ALTER TABLE ONLY public."DokConsumptionTableMoney" DROP CONSTRAINT "DokConsumptionTableMoney_pkey";
       public            postgres    false    209            !           2606    16443 "   DokConsumption DokConsumption_pkey 
   CONSTRAINT     i   ALTER TABLE ONLY public."DokConsumption"
    ADD CONSTRAINT "DokConsumption_pkey" PRIMARY KEY (id_dcon);
 P   ALTER TABLE ONLY public."DokConsumption" DROP CONSTRAINT "DokConsumption_pkey";
       public            postgres    false    205                       2606    16417    Kontragent Kontragent_pkey 
   CONSTRAINT     a   ALTER TABLE ONLY public."Kontragent"
    ADD CONSTRAINT "Kontragent_pkey" PRIMARY KEY (id_kont);
 H   ALTER TABLE ONLY public."Kontragent" DROP CONSTRAINT "Kontragent_pkey";
       public            postgres    false    197                       2606    16459    Nomenclature Nomenclature_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public."Nomenclature"
    ADD CONSTRAINT "Nomenclature_pkey" PRIMARY KEY (id_nomen);
 L   ALTER TABLE ONLY public."Nomenclature" DROP CONSTRAINT "Nomenclature_pkey";
       public            postgres    false    199                       2606    16421 1   ViewComingConsumption ViewComingСonsumption_pkey 
   CONSTRAINT     z   ALTER TABLE ONLY public."ViewComingConsumption"
    ADD CONSTRAINT "ViewComingСonsumption_pkey" PRIMARY KEY (id_viewcc);
 _   ALTER TABLE ONLY public."ViewComingConsumption" DROP CONSTRAINT "ViewComingСonsumption_pkey";
       public            postgres    false    201            *           2606    16452    DokComingTableMoney id_dcom_FK    FK CONSTRAINT     �   ALTER TABLE ONLY public."DokComingTableMoney"
    ADD CONSTRAINT "id_dcom_FK" FOREIGN KEY (id_dcom) REFERENCES public."DokComing"(id_dcom) NOT VALID;
 L   ALTER TABLE ONLY public."DokComingTableMoney" DROP CONSTRAINT "id_dcom_FK";
       public          postgres    false    202    2847    207            ,           2606    16473 #   DokConsumptionTableMoney id_dcom_FK    FK CONSTRAINT     �   ALTER TABLE ONLY public."DokConsumptionTableMoney"
    ADD CONSTRAINT "id_dcom_FK" FOREIGN KEY (id_dcon) REFERENCES public."DokConsumption"(id_dcon);
 Q   ALTER TABLE ONLY public."DokConsumptionTableMoney" DROP CONSTRAINT "id_dcom_FK";
       public          postgres    false    2849    205    209            &           2606    16498    DokComing id_kont_FK    FK CONSTRAINT     �   ALTER TABLE ONLY public."DokComing"
    ADD CONSTRAINT "id_kont_FK" FOREIGN KEY (id_kont) REFERENCES public."Kontragent"(id_kont) NOT VALID;
 B   ALTER TABLE ONLY public."DokComing" DROP CONSTRAINT "id_kont_FK";
       public          postgres    false    2841    202    197            (           2606    16508    DokConsumption id_kont_FK    FK CONSTRAINT     �   ALTER TABLE ONLY public."DokConsumption"
    ADD CONSTRAINT "id_kont_FK" FOREIGN KEY (id_kont) REFERENCES public."Kontragent"(id_kont) NOT VALID;
 G   ALTER TABLE ONLY public."DokConsumption" DROP CONSTRAINT "id_kont_FK";
       public          postgres    false    205    2841    197            +           2606    16460    DokComingTableMoney id_nomen_FK    FK CONSTRAINT     �   ALTER TABLE ONLY public."DokComingTableMoney"
    ADD CONSTRAINT "id_nomen_FK" FOREIGN KEY (id_nomen) REFERENCES public."Nomenclature"(id_nomen) NOT VALID;
 M   ALTER TABLE ONLY public."DokComingTableMoney" DROP CONSTRAINT "id_nomen_FK";
       public          postgres    false    207    199    2843            -           2606    16478 $   DokConsumptionTableMoney id_nomen_FK    FK CONSTRAINT     �   ALTER TABLE ONLY public."DokConsumptionTableMoney"
    ADD CONSTRAINT "id_nomen_FK" FOREIGN KEY (id_nomen) REFERENCES public."Nomenclature"(id_nomen);
 R   ALTER TABLE ONLY public."DokConsumptionTableMoney" DROP CONSTRAINT "id_nomen_FK";
       public          postgres    false    209    2843    199            '           2606    16503    DokComing id_view_FK    FK CONSTRAINT     �   ALTER TABLE ONLY public."DokComing"
    ADD CONSTRAINT "id_view_FK" FOREIGN KEY (id_viewcc) REFERENCES public."ViewComingConsumption"(id_viewcc) NOT VALID;
 B   ALTER TABLE ONLY public."DokComing" DROP CONSTRAINT "id_view_FK";
       public          postgres    false    2845    202    201            )           2606    16513    DokConsumption id_viewcc_FK    FK CONSTRAINT     �   ALTER TABLE ONLY public."DokConsumption"
    ADD CONSTRAINT "id_viewcc_FK" FOREIGN KEY (id_viewcc) REFERENCES public."ViewComingConsumption"(id_viewcc) NOT VALID;
 I   ALTER TABLE ONLY public."DokConsumption" DROP CONSTRAINT "id_viewcc_FK";
       public          postgres    false    205    2845    201            �   �   x�U�=
�@F�o#��dcm%v�6AAk;;� @E�Wؽ����u���ٝ��s@B!gqI:�4�~���h�E'$'3X�33��y齂$m_�r;[���wI��''��5e�/�!���^v2��
�L�=�}�e��+�	2_d6L4;Z���}�_��j��i{�(�.IE�&=��GS�      �   7   x���  �sR��oV`�u�7��0	���<�M�H�T䶫���H~��      �      x�3�420�LLL�4�4�L����� *?�      �      x�3�4AN#�=... N�      �   �   x�3�0�¾���.l���¾�8Ӹ�9/,��(���M{8K�L9ˁ �04�L,N� C  
�p���Yf�@- �A�9��`0ȷ�t��J)�(Jr,8�J����@�8SҀ������� �P>      �   a   x�3�0�®��/����3�ː���;.l���bÅ��.vM9�S��S8K��8/L���®����]�}�������[.l 2c���� h,�      �   B   x�3�0���_���.l�L�2�0��>��f Ǆ38K��8�R��K�BR�K��=... ���     
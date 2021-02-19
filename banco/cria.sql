CREATE TABLE t_rbw_audio_video (
    cd_audio_video  NUMBER(5) NOT NULL,
    fl_video        VARCHAR2(200),
    fl_audio        VARCHAR2(200)
);

ALTER TABLE t_rbw_audio_video ADD CONSTRAINT t_rbw_video_pk PRIMARY KEY ( cd_audio_video );

CREATE SEQUENCE SQ_RBW_AUDIO_VIDEO
START WITH 1 
INCREMENT BY 1;

CREATE TABLE t_rbw_candidato (
    cd_candidato    NUMBER(11) NOT NULL,
    nr_cpf          VARCHAR2(11) NOT NULL,
    nr_vaga         NUMBER(4),
    cd_audio_video  NUMBER(5),
    cd_chatbot      NUMBER(5),
    cd_recrutador   NUMBER(11),
    dt_nascimento   DATE NOT NULL,
    nr_medalha      NUMBER(1) NOT NULL,
    nm_candidato    VARCHAR2(100) NOT NULL,
    ds_email        VARCHAR2(100) NOT NULL,
    nm_senha        VARCHAR2(16) NOT NULL
);
--Duas Pessoas não pode concorrer a mesma vaga ?
--CREATE UNIQUE INDEX t_rbw_cand__idx ON
--    t_rbw_candidato (
--        nr_vaga
--    ASC );

CREATE UNIQUE INDEX t_rbw_cand__idxv0 ON
    t_rbw_candidato (
        cd_audio_video
    ASC );

CREATE UNIQUE INDEX t_rbw_cand__idxv1 ON
    t_rbw_candidato (
        cd_chatbot
    ASC );

ALTER TABLE t_rbw_candidato ADD constraint ck_candidato_medalha CHECK ( nr_medalha >= 0 AND nr_medalha <= 2 );
ALTER TABLE t_rbw_candidato ADD CONSTRAINT t_rbw_cand_pk PRIMARY KEY ( cd_candidato );

ALTER TABLE t_rbw_candidato ADD CONSTRAINT un_cand_cpf UNIQUE ( nr_cpf );

ALTER TABLE t_rbw_candidato ADD CONSTRAINT un_cand_email UNIQUE ( ds_email );

CREATE SEQUENCE SQ_RBW_CANDIDATO
START WITH 1 
INCREMENT BY 1;

CREATE TABLE t_rbw_chatbot (
    cd_chatbot   NUMBER(5) NOT NULL,
    ds_resposta  VARCHAR2(100)
);

ALTER TABLE t_rbw_chatbot ADD CONSTRAINT t_rbw_chatbot_pk PRIMARY KEY ( cd_chatbot );

CREATE SEQUENCE SQ_RBW_CHATBOT
START WITH 1 
INCREMENT BY 1;

CREATE TABLE t_rbw_recrutador (
    cd_recrutador  NUMBER(11) NOT NULL,
    nm_recrutador  VARCHAR2(100) NOT NULL,
    ds_email       VARCHAR2(100) NOT NULL,
    nm_senha       VARCHAR2(16) NOT NULL
);

ALTER TABLE t_rbw_recrutador ADD CONSTRAINT t_brw_recrut_pk PRIMARY KEY ( cd_recrutador );

ALTER TABLE t_rbw_recrutador ADD CONSTRAINT un_recrutador_email UNIQUE ( ds_email );

CREATE SEQUENCE SQ_RBW_RECRUTADOR
START WITH 1 
INCREMENT BY 1;

CREATE TABLE t_rbw_vaga (
    nr_vaga     NUMBER(4) NOT NULL,
    nm_vaga     VARCHAR2(50) NOT NULL,
    ds_vaga     VARCHAR2(400) NOT NULL,
    vl_salario  NUMBER(7, 2)
);

ALTER TABLE t_rbw_vaga ADD CONSTRAINT t_rbw_vaga_pk PRIMARY KEY ( nr_vaga );

ALTER TABLE t_rbw_candidato
    ADD CONSTRAINT fk_rbw_audio_cand FOREIGN KEY ( cd_audio_video )
        REFERENCES t_rbw_audio_video ( cd_audio_video );

ALTER TABLE t_rbw_candidato
    ADD CONSTRAINT fk_rbw_chatbot_cand FOREIGN KEY ( cd_chatbot )
        REFERENCES t_rbw_chatbot ( cd_chatbot );

ALTER TABLE t_rbw_candidato
    ADD CONSTRAINT fk_rbw_recrutador_candidato FOREIGN KEY ( cd_recrutador )
        REFERENCES t_rbw_recrutador ( cd_recrutador );

ALTER TABLE t_rbw_candidato
    ADD CONSTRAINT t_rbw_vaga_cand FOREIGN KEY ( nr_vaga )
        REFERENCES t_rbw_vaga ( nr_vaga );

CREATE SEQUENCE SQ_RBW_VAGA
START WITH 1 
INCREMENT BY 1;

CREATE TABLE springReplyBoard(
   NO NUMBER,
   name VARCHAR2(51) CONSTRAINT sr_name_nn NOT NULL,
   subject VARCHAR2(2000) CONSTRAINT sr_subject_nn NOT NULL,
   content CLOB CONSTRAINT sr_content_nn NOT NULL,
   pwd VARCHAR2(10) CONSTRAINT sr_pwd_nn NOT NULL,
   regdate DATE DEFAULT SYSDATE,
   hit NUMBER DEFAULT 0,
   group_id NUMBER,
   group_step NUMBER DEFAULT 0,
   group_tab NUMBER DEFAULT 0,
   root NUMBER DEFAULT 0,
   DEPTH NUMBER DEFAULT 0,
   CONSTRAINT sr_no_pk PRIMARY KEY(no)
);

CREATE SEQUENCE sr_no_seq
    START WITH 1
    INCREMENT BY 1
    NOCYCLE
    NOCACHE;
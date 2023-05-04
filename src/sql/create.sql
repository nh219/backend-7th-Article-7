set linesize 200
set pagesize 50

DROP SEQUENCE post_view_seq;
DROP SEQUENCE reply_reply_id_seq;
DROP SEQUENCE post_post_id_seq;
DROP SEQUENCE member_member_id_seq;
DROP TABLE crawl_post;
DROP TABLE reply_recommend;
DROP TABLE reply_report;
DROP TABLE post_recommend;
DROP TABLE post_report;
DROP TABLE reply;
DROP TABLE post;
DROP TABLE member;

CREATE TABLE member (
    member_id       NUMBER(6)       CONSTRAINT member_member_id_PK PRIMARY KEY CONSTRAINT member_member_id_nn NOT NULL,
    email           VARCHAR2(255)   CONSTRAINT member_email_uq UNIQUE CONSTRAINT member_email_nn NOT NULL,
    sign_up_date    DATE            DEFAULT    sysdate,
    nickname        VARCHAR2(30)    CONSTRAINT member_nickname_uq UNIQUE CONSTRAINT member_nickname_nn NOT NULL,
    password        VARCHAR2(45)    CONSTRAINT member_password_nn NOT NULL,
    admin           NUMBER(1)       DEFAULT 0
);

CREATE TABLE post (
    post_id         NUMBER(6)       CONSTRAINT post_post_id_pk PRIMARY KEY CONSTRAINT post_post_id_nn NOT NULL,
    category        VARCHAR2(8)     CONSTRAINT post_catagory_nn NOT NULL 
                                    CONSTRAINT post_category_ck CHECK(category IN('free','party','feedback','scrim')),
    title           VARCHAR2(150)   CONSTRAINT post_title_nn NOT NULL,
    member_id       NUMBER(6)       CONSTRAINT post_member_id_nn NOT NULL 
                                    CONSTRAINT post_member_id_fk REFERENCES member(member_id),
    post_date       DATE            DEFAULT sysdate,
    post_content    VARCHAR2(4000)  CONSTRAINT post_post_content_nn NOT NULL,
    views           NUMBER(6)       DEFAULT 0,
    report_num      NUMBER(3)       DEFAULT 0,
    like_num        NUMBER(5)       DEFAULT 0,
    dislike_num     NUMBER(5)       DEFAULT 0,
    post_reply_num  NUMBER(5)       DEFAULT 0,
    post_notice     NUMBER(1)       DEFAULT 0
);

CREATE TABLE reply (
    reply_id        NUMBER(6)       CONSTRAINT reply_reply_it_pk PRIMARY KEY CONSTRAINT reply_reply_id_nn NOT NULL,
    member_id       NUMBER(6)       CONSTRAINT reply_member_id_nn NOT NULL 
                                    CONSTRAINT reply_member_id_fk REFERENCES member(member_id),
    reply_date      DATE            DEFAULT sysdate,
    reply_content   VARCHAR2(1000)  CONSTRAINT reply_reply_content_nn NOT NULL,
    parents_reply_id NUMBER(6)      CONSTRAINT reply_parents_reply_id_nn NOT NULL
                                    CONSTRAINT reply_parents_reply_id_fk REFERENCES reply(reply_id),
    post_id         NUMBER(6)       CONSTRAINT reply_post_id_nn NOT NULL
                                    CONSTRAINT reply_post_id_fk REFERENCES post(post_id),
    report_num      NUMBER(3)       DEFAULT 0,
    like_num        NUMBER(5)       DEFAULT 0,
    dislike_num     NUMBER(5)       DEFAULT 0
);

CREATE TABLE post_report (
    member_id       NUMBER(6)       CONSTRAINT post_report_member_id_fk REFERENCES member(member_id),
    post_id         NUMBER(6)       CONSTRAINT post_report_post_id_fk REFERENCES post(post_id),
    report_category NUMBER(1)       DEFAULT 0 CONSTRAINT post_report_report_category_nn NOT NULL 
                                    CONSTRAINT post_report_report_category_ck CHECK(report_category IN(0,1,2,3)),
    CONSTRAINT post_report_pk       PRIMARY KEY(member_id, post_id)
);

CREATE TABLE post_recommend(
    member_id       NUMBER(6)       CONSTRAINT post_recommend_member_id_fk REFERENCES member(member_id),
    post_id         NUMBER(6)       CONSTRAINT post_recommend_post_id_fk REFERENCES post(post_id),
    like_check      NUMBER(1)       DEFAULT 0 CONSTRAINT post_recommend_like_check_ck CHECK(like_check IN(0,1,2)),
    CONSTRAINT post_recommend_pk    PRIMARY KEY(member_id, post_id)
);

CREATE TABLE reply_report(
    member_id       NUMBER(6)       CONSTRAINT reply_report_member_id_nn NOT NULL
                                    CONSTRAINT reply_report_member_id_fk REFERENCES member(member_id),
    reply           NUMBER(6)       CONSTRAINT reply_report_reply_nn NOT NULL
                                    CONSTRAINT reply_report_reply_fk REFERENCES reply(reply_id),
    CONSTRAINT reply_report_pk      PRIMARY KEY(member_id, reply)
);

CREATE TABLE reply_recommend(
    member_id       NUMBER(6)       CONSTRAINT reply_recommend_member_id_fk REFERENCES member(member_id),
    post_id         NUMBER(6)       CONSTRAINT reply_recommend_post_id_fk REFERENCES post(post_id),
    like_check      NUMBER(1)       DEFAULT 0 CONSTRAINT reply_recommend_like_check_ck CHECK(like_check IN(0,1,2)),
    CONSTRAINT reply_recommend_pk   PRIMARY KEY(member_id, post_id)
);

CREATE TABLE crawl_post(
    crawl_id        NUMBER(6)       CONSTRAINT crawl_post_crawl_id_pk PRIMARY KEY CONSTRAINT crawl_post_crawl_id_nn NOT NULL,
    sitename        VARCHAR2(30)    CONSTRAINT crawl_post_sitename_nn NOT NULL,
    title           VARCHAR2(150)   CONSTRAINT crawl_post_title_nn NOT NULL,
    reply_date      DATE            DEFAULT sysdate,
    views           NUMBER(6)       DEFAULT 0,
    url             VARCHAR2(300)   CONSTRAINT crawl_post_url_nn NOT NULL
);

CREATE SEQUENCE member_member_id_seq
	START WITH 1
	INCREMENT BY 1
	MAXVALUE 999999
	NOCYCLE;
    
CREATE SEQUENCE post_post_id_seq
	START WITH 1
	INCREMENT BY 1
	MAXVALUE 999999
	NOCYCLE;

CREATE SEQUENCE reply_reply_id_seq
	START WITH 1
	INCREMENT BY 1
	MAXVALUE 999999
	NOCYCLE;

CREATE SEQUENCE post_view_seq
	START WITH 1
	INCREMENT BY 1
	MAXVALUE 999999
	NOCYCLE;
	
commit;

insert into member (member_id, email, nickname, password)
values (member_id_seq.nextval, 'longlee@daum.net', '이성구', '6789');

insert into member (member_id, email, nickname, password)
values (member_id_seq.nextval, 'son@naver.com', '손흥민', '1234');

insert into member (member_id, email, nickname, password, admin)
values (member_id_seq.nextval, 'admin@naver.com', '관리자1', '9999', 1);

commit;
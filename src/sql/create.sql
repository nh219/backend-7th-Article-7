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
    nickname        VARCHAR2(60)    CONSTRAINT member_nickname_uq UNIQUE CONSTRAINT member_nickname_nn NOT NULL,
    password        VARCHAR2(60)    CONSTRAINT member_password_nn NOT NULL,
    admin           NUMBER(1)       DEFAULT 0
);

CREATE TABLE post (
    post_id         NUMBER(6)       CONSTRAINT post_post_id_pk PRIMARY KEY CONSTRAINT post_post_id_nn NOT NULL,
    category        VARCHAR2(60)    CONSTRAINT post_category_nn NOT NULL 
                                    CONSTRAINT post_category_ck CHECK(category IN('자유','투표 피드백','파티 구함','통합 인기 게시판')),
    title           VARCHAR2(150)   CONSTRAINT post_title_nn NOT NULL,
    nickname        VARCHAR2(60)    CONSTRAINT post_nickname_nn NOT NULL 
                                    CONSTRAINT post_nickname_fk REFERENCES member(nickname),
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
    reply_id        NUMBER(6)       CONSTRAINT reply_reply_id_pk PRIMARY KEY CONSTRAINT reply_reply_id_nn NOT NULL,
    nickname       VARCHAR2(60)     CONSTRAINT reply_nickname_nn NOT NULL 
                                    CONSTRAINT reply_nickname_fk REFERENCES member(nickname),
    reply_date      DATE            DEFAULT sysdate,
    reply_content   VARCHAR2(1000)  CONSTRAINT reply_reply_content_nn NOT NULL,
    parents_reply_id NUMBER(6)      CONSTRAINT reply_parents_reply_id_fk REFERENCES reply(reply_id),
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
    sitename        VARCHAR2(60)    CONSTRAINT crawl_post_sitename_nn NOT NULL,
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



--회원 더미

INSERT INTO member (member_id, email, nickname, password, admin)
VALUES (member_member_id_seq.nextval, 'meditation@naver.com', '조명상', 'a0fj39f9we', 1);

INSERT INTO member (member_id, email, nickname, password, admin)
VALUES (member_member_id_seq.nextval, 'namhyukjjang@daum.net', '조남혁', 'ab5j8k9q1r', 1);

INSERT INTO member (member_id, email, nickname, password)
VALUES (member_member_id_seq.nextval, 'clapHyun@hanmail.net', '박수현', '0g93jj39du');

INSERT INTO member (member_id, email, nickname, password)
VALUES (member_member_id_seq.nextval, 'singasong@naver.com', '송준석', 'iop07n45hw');

INSERT INTO member (member_id, email, nickname, password)
VALUES (member_member_id_seq.nextval, 'thislifeHo@gmail.com', '이승호', 'qpmdf093j5');

INSERT INTO member (member_id, email, nickname, password)
VALUES (member_member_id_seq.nextval, 'jondae@gmail.com', '존댓말을쓸거에요', '6v2g1y8m');

INSERT INTO member (member_id, email, nickname, password)
VALUES (member_member_id_seq.nextval, 'jimajima@hanmail.net', '사에지마', '1f2d3q4w5e');

INSERT INTO member (member_id, email, nickname, password)
VALUES (member_member_id_seq.nextval, 'incheon22@naver.com', '인천광역시연수구송도동', '4g8t9b1p6m');

INSERT INTO member (member_id, email, nickname, password)
VALUES (member_member_id_seq.nextval, 'weezOne@naver.com', '위즈원', '2d6f8g9h1t');

INSERT INTO member (member_id, email, nickname, password)
VALUES (member_member_id_seq.nextval, 'field1004@gmail.com', '홀리필드', '5v2b1a8k6f');

INSERT INTO member (member_id, email, nickname, password)
VALUES (member_member_id_seq.nextval, '1885gogo@naver.com', 'jyxxg1885', '4y2a6f7c8g');

INSERT INTO member (member_id, email, nickname, password) 
VALUES (member_member_id_seq.nextval, 'whiteHandTT@naver.com', '방구석백수탈출', '2sQfT7nE9m');

INSERT INTO member (member_id, email, nickname, password) 
VALUES (member_member_id_seq.nextval, 'chuchu123@gmail.com', 'Datupchu', 'bG4fV2cJ6s');

INSERT INTO member (member_id, email, nickname, password) 
VALUES (member_member_id_seq.nextval, 'uadokjon@naver.com', '반박시니말틀림', '5nFtJ8zEwR');

INSERT INTO member (member_id, email, nickname, password) 
VALUES (member_member_id_seq.nextval, 'melon@naver.com', '초코멜론', '9dA3jK5tRq');

INSERT INTO member (member_id, email, nickname, password) 
VALUES (member_member_id_seq.nextval, 'steal777@naver.com', '빼앗긴궁극기', '6yRfE3sT9x');

INSERT INTO member (member_id, email, nickname, password) 
VALUES (member_member_id_seq.nextval, 'ilikepain@gmail.com', '매를번다', '8jV6wN5zSx');

INSERT INTO member (member_id, email, nickname, password) 
VALUES (member_member_id_seq.nextval, 'lolchobo@gmail.com', '롤겁나초보오', '4dG6cR8nWf');

INSERT INTO member (member_id, email, nickname, password) 
VALUES (member_member_id_seq.nextval, 'iloveyou@gmail.com', '너의모든순간', '7yH5nK4fJd');

INSERT INTO member (member_id, email, nickname, password) 
VALUES (member_member_id_seq.nextval, 'topjungle@naver.com', '탑정글미드원딜차이', '5sW9fJ3vRt');



--게시글 더미 (자유)

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '자유', '수세미같은 영국의 어느 고급케이크', '빼앗긴궁극기', '아무리 봐도 수세미 + 퐁퐁 조합 같음');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '자유', '유럽에서도 롤이 1등겜임?', '존댓말을쓸거에요', 'ㅇ?');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '자유', '챔프폭은 문제가 없어요~ 그냥 피지컬챔을 전부 못하는거 ㅋ', 'Datupchu', 'ㅋㅋㅋ');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '자유', '도란 그라가스 할려나?', '반박시니말틀림', 'ㅇ??');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '자유', '이거 패패승승승하면 이번 msi안봄', '방구석백수탈출', '는구라고 t1응원함ㅋㅋ');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '자유', '진짜 사람들이 지능형 안티슼팬인지 진짜 슼팬들인지 모르겠네', '홀리필드', '롤 좀 봤다는 사람들이 이거 3셋 하나 따였다고 무슨 개념글을 저렇게 가고 추천수를 저렇게 받음..? 뭐 3대2면 유럽한테도 따일뻔한 병신들 하면서 놀리는게 이해는됨ㅇㅇ 근데 1 2세트 보고도, 3세트 밴픽 보고도 방빼라느니 하는 글들이 써지는게 아무리 봐도 티원 욕먹이려는 다른팀팬들이 쓴거같음 진짜 티원팬들이면 뭐.. 어쩔수없지 ㅋㅋ');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '자유', '실버스크랩스 듣고 싶다', '매를번다', '빠바밤 빠밤');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '자유', '피넛 챔프폭이 아니라 실력이 없어서 마오카이하는거야', '빼앗긴궁극기', '니달리 리신 비에고는 실력있는 피지컬 정글러가 쓰는거고 마오카이 뽀삐는 쓰레기 퇴물들이 쓰는픽이야 정신차려 챔프폭이 좁은게 아니라 실력이 없어서 칼챔을 못쓰는거야 정신차려');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '자유', '징크스는 드븐한테 뚝배기 깨지지 않냐', '탑정글미드원딜차이', '페이즈는 다른가');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '자유', '젠지는 걍 피넛만 막으면 이김 ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ', '사에지마', '3년차 슼갈 즙독인데 ㅇㄱㄹㅇ임 ㅋㅋㅋㅋㅋㅋㅋㅋ');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '자유', '괜히 쵸비팬들이 우승하고도 제오쵸구케 말하는 게 아님', '초코멜론', '페이즈는 신인이라 불안한 정도지 도피딜 << 이 새끼들은 그냥 짐덩어리들임');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '자유', '젠첩이니 피넛의 혐차는 경기력이 더 잘보이지', '인천광역시연수구송도동', 'ㅇㅇ');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '자유', '쵸 페딜 도 번갈아가며 캐리하는데 ㅋㅋㅋ', '위즈원', '1세트 레전드아리 2세트 레전드아펠블츠 4세트 레전드까진아니지만 상대가 할거 다막아버린 도란 등등 번갈아가며 캐리하는데 피넛은 ㄹㅇ로 모든세트 다못함ㅋㅋㅋ 정신차리자 제발');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '자유', '티원은 이름이 좀 ㅅㅌㅊ로 보이는게있음', 'jyxxg1885', 'ㄹㅇ');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '자유', '피넛은 96듀오보다 피지컬 현저히 떨어짐', '존댓말을쓸거에요', '솔직히 은퇴한 스맵 프레이 스코어도 걔보단 피지컬 좋아');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '자유', '케리아 최애의 아이 보고 각성할듯', '너의모든순간', '베릴수련법 ㄷㄷ');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '자유', '오늘 경기는 하겐다즈먹으면서 볼껀데 불만업제', '반박시니말틀림', 'ㅇ');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '자유', 'Fact) 스프링 결승도 제오케가 도피딜보다 잘했음', '빼앗긴궁극기', '그냥 미드차이 하나때문에 진거임 피넛이 오너보다 잘해서 우승한 게 아니다');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '자유', 'LCK 1시드가 LEC 2시드한테 고전ㅋㅋㅋㅋㅋ', '탑정글미드원딜차이', '부끄러운줄 아세요 lck스프링 플레이오프 방식 마음에 안든다고 까다가 지들이 수혜보니까 입 싹 닫기, 이번시즌 상대전적 1대3이면서 입털기 등등 업보 쌓은거 대가는 치뤄야죠');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '자유', 'lck 가 월즈는 7번이나 처먹고 므시 2번인건 왜임??', '사에지마', '메타 분석이 느려서?');

--게시글 더미 (투표 피드백)

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '투표 피드백', '누가 더 잘했나요?', '존댓말을쓸거에요', 'ㅇ?');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '투표 피드백', '내가 더 잘함', '방구석백수탈출', 'ㅇㅈ?');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '투표 피드백', '투표 부탁드려요', '위즈원', '투표용');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '투표 피드백', '한문철 가즈아!!!', '빼앗긴궁극기', 'ㄱㄱㄱ');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '투표 피드백', '이건 누가 봐도......', '사에지마', '내가 이김');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '투표 피드백', '애매한데 어떠냐?', '존댓말을쓸거에요', '어떤것같음');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '투표 피드백', '억울하다 투표해라', '빼앗긴궁극기', '제발');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '투표 피드백', '이리보고 저리봐도', '인천광역시연수구송도동', '둘리');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '투표 피드백', '이겼다. 내가', '매를번다', '훗');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '투표 피드백', '투표 ㄱㄱ요', '반박시니말틀림', '투표!');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '투표 피드백', '투표합시다', '너의모든순간', '내가 이김');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '투표 피드백', '이건 좀 그렇지 않냐?', '초코멜론', '솔직히');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '투표 피드백', '찢었다.', '홀리필드', '내가');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '투표 피드백', '찢겼다.', '탑정글미드원딜차이', '내가');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '투표 피드백', '애매하다 진짜', 'Datupchu', '안그러냐');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '투표 피드백', '쌍방과실 ㅇㅈ?', '빼앗긴궁극기', 'ㅇㅈ');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '투표 피드백', '흠 모르겠네 투표 ㄱ', 'jyxxg1885', '투표해줘');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '투표 피드백', '친구랑 싸울뻔함', '탑정글미드원딜차이', '누가 잘못한거에여');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '투표 피드백', '솔직히 이건 쟤 잘못', '반박시니말틀림', '아니냐');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '투표 피드백', '투표 좀 해줘', 'Datupchu', '억울행');

--게시글 더미 (파티 구함)

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '파티 구함', '잘하시는 분 ㄱㄱ요', '빼앗긴궁극기', 'ㅍㅌㄱㅎ');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '파티 구함', '나 잘하는데 같이할사람~', '매를번다', 'ㅍㅌㄱㅎ');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '파티 구함', '겜 ㄱ', '반박시니말틀림', 'ㅍㅌㄱㅎ');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '파티 구함', '같이 하실 분', '방구석백수탈출', 'ㅍㅌㄱㅎ');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '파티 구함', '겜 같이 해여', '존댓말을쓸거에요', 'ㅍㅌㄱㅎ');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '파티 구함', '고고고', '반박시니말틀림', 'ㅍㅌㄱㅎ');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '파티 구함', '파티구함요..', '위즈원', 'ㅍㅌㄱㅎ');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '파티 구함', '롤하실분~~', '인천광역시연수구송도동', 'ㅍㅌㄱㅎ');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '파티 구함', '롤 ㄱ 파티 ㄱ', '빼앗긴궁극기', 'ㅍㅌㄱㅎ');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '파티 구함', '겜이나합시다', '탑정글미드원딜차이', 'ㅍㅌㄱㅎ');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '파티 구함', '롤롤롤', 'jyxxg1885', 'ㅍㅌㄱㅎ');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '파티 구함', '파티하실분!?', '반박시니말틀림', 'ㅍㅌㄱㅎ');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '파티 구함', '지치고 힘들땐 롤', '너의모든순간', 'ㅍㅌㄱㅎ');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '파티 구함', '롤이나 하자고', '홀리필드', 'ㅍㅌㄱㅎ');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '파티 구함', '전쟁은 이제 시작이다...', '위즈원', 'ㅍㅌㄱㅎ');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '파티 구함', '이 몸과 팀하실 분!', '탑정글미드원딜차이', 'ㅍㅌㄱㅎ');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '파티 구함', '가즈아!!!', '빼앗긴궁극기', 'ㅍㅌㄱㅎ');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '파티 구함', '없냐 같이 할 사람', 'Datupchu', 'ㅍㅌㄱㅎ');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '파티 구함', '나랑 팀하면 이득', '초코멜론', 'ㅍㅌㄱㅎ');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '파티 구함', '파티 구함요~', '사에지마', 'ㅍㅌㄱㅎ');

--게시글 더미 (통합 인기 게시판)

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '통합 인기 게시판', '쵸비 전 세트 그렇게 못하진않은게', '존댓말을쓸거에요', '그 4강전 사일러스 라이즈 임팩트땜에 별것아닌것처럼보임');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '통합 인기 게시판', '근데 피넛도 마오 바이 밴하면 막막해지긴 하네', '인천광역시연수구송도동', '뽀삐 가져가면 ㄹㅇ');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '통합 인기 게시판', '이번경기 생각나는 장면 두개', '빼앗긴궁극기', '라칸 납치했다고 지옥까지 쫒아가다가 죽어서 라인 주도권 날린 쵸비 야이크가 페이커 빙의해서 스킬 다피하고 빠져나가는게 꼴받아서 냅다 던진 3인방');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '통합 인기 게시판', '근데 피넛 챔프폭이 좀 적긴하네 ㅇㅇ', '위즈원', '오너 보다 더 좁은 느낌이긴 함 ㅇㅇ');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '통합 인기 게시판', '팩트) 만에 하나 젠지가 탈락한다고 해도 티원은 우승 못한다', '방구석백수탈출', '제발 꿈깨라');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '통합 인기 게시판', '미래에서 왔다', '반박시니말틀림', '젠지 오늘 진다..');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '통합 인기 게시판', '젠지는 걍 죽더라도 클래식 돌려야겠다', 'jyxxg1885', '위로 갈수록 끠넛 안통하겠네');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '통합 인기 게시판', '젠지 약점 아우솔', '홀리필드', 'ㅇㅇ');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '통합 인기 게시판', '젠지, DRX수련법을 익히는거냐', '반박시니말틀림', '페이즈를 위해 다전제 경험을 쌓는거냐');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '통합 인기 게시판', '도란 진짜 잘하네', '매를번다', 'ㄴ');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '통합 인기 게시판', '포카칩 먹으면서 계산중', '너의모든순간', 'ㅇ');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '통합 인기 게시판', '젠지 지투 누가이김?', '빼앗긴궁극기', '몰라');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '통합 인기 게시판', '진짜 쵸비묻으면 팬덤 개더러워지네', 'Datupchu', '젠지정도 팬층있는 팀도 저렇게 오염되냐..얘네가 걍 최악의 팬덤이네');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '통합 인기 게시판', '쵸비 : "첫 경기가 빨리 진행되고 준비가 덜 된 상태라..."', '매를번다', '(경직된 모습)');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '통합 인기 게시판', '젠첩들 캐니언 vs 피넛 하면 캐니언 몰표함 ㅋ', '반박시니말틀림', '오너 vs 피넛만해도 오너 표가 더 많을듯 ㅋ');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '통합 인기 게시판', '어제 피넛 못했어?', '사에지마', '딴겜하면서 중간중간 대충 볼때는 미드정글만 이기거나 미드정글탑이 잘하고 바텀은 꾸준히 터지던데? 뭔가 큰 실수 있었나?');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '통합 인기 게시판', '오늘 경기에선 무슨 권법 나오냐?', '초코멜론', '솔직히 T1이 질거라곤 하나도 생각 안드는데 우리혁이 새로운 권법 창시하는 것만 기대됨');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '통합 인기 게시판', '피넛이 아무리 못해도 얘보단 잘함', '매를번다', '대 상 혁 ㅋㅋㅋㅋㅋㅋㅋ');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '통합 인기 게시판', '오늘부터 1패 하면 그 날 솔랭은 끝인걸로', '빼앗긴궁극기', 'ㅇ');

INSERT INTO post (post_id, category, title, nickname, post_content)
VALUES (post_post_id_seq.NEXTVAL, '통합 인기 게시판', '음 결국 겜 던져버렸네에', 'jyxxg1885', '요즘 도구들은 아구창이 왤케 열려있을까나');



commit;



SELECT * FROM member;
SELECT * FROM post;
SELECT * FROM user_sequences;



--test 조회수, 추천수 테스트
--UPDATE post SET views = views + 1 WHERE post_id = 1;
--UPDATE post SET like_num = like_num + 1 WHERE post_id = 1;
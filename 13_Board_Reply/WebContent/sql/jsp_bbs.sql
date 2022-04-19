-- jsp_bbs 테이블 생성
-- BBS(Bulletin Board System : 전자게시판)

-- jsp_bbs 게시판 테이블 구성

CREATE TABLE JSP_BBS(
	BOARD_NO NUMBER(5) PRIMARY KEY,			-- 글번호
	BOARD_WRITER VARCHAR2(30) NOT NULL,		-- 작성자
	BOARD_TITLE VARCHAR2(100) NOT NULL,		-- 제목
	BOARD_CONTENT VARCHAR2(1000) NOT NULL,	-- 내용
	BOARD_PWD VARCHAR2(30) NOT NULL,		-- 비밀번호
	BOARD_HIT NUMBER(5) DEFAULT 0,			-- 조회수
	BOARD_DATE DATE,						-- 작성일
	BOARD_UPDATE DATE,						-- 수정일
	BOARD_GROUP NUMBER(5),					-- 그룹
	BOARD_STEP NUMBER(5),					-- 답변 단계
	BOARD_INDENT NUMBER(5)					-- 답변글 들여쓰기
);

INSERT INTO JSP_BBS VALUES(1, 'Client1', '제목1', '내용1', '1111', DEFAULT, SYSDATE, '', 1, 0, 0);
INSERT INTO JSP_BBS VALUES(2, 'Client2', '제목2', '내용2', '2222', DEFAULT, SYSDATE, '', 2, 0, 0);
INSERT INTO JSP_BBS VALUES(3, 'Client3', '제목3', '내용3', '3333', DEFAULT, SYSDATE, '', 3, 0, 0);
INSERT INTO JSP_BBS VALUES(4, 'Client4', '제목4', '내용4', '4444', DEFAULT, SYSDATE, '', 4, 0, 0);
INSERT INTO JSP_BBS VALUES(5, 'Client5', '제목5', '내용5', '5555', DEFAULT, SYSDATE, '', 5, 0, 0);
INSERT INTO JSP_BBS VALUES(6, 'Client6', '제목6', '내용6', '6666', DEFAULT, SYSDATE, '', 6, 0, 0);
INSERT INTO JSP_BBS VALUES(7, 'Client7', '제목7', '내용7', '7777', DEFAULT, SYSDATE, '', 7, 0, 0);
INSERT INTO JSP_BBS VALUES(8, 'Client8', '제목8', '내용8', '8888', DEFAULT, SYSDATE, '', 8, 0, 0);
INSERT INTO JSP_BBS VALUES(9, 'Client9', '제목9', '내용9', '9999', DEFAULT, SYSDATE, '', 9, 0, 0);
INSERT INTO JSP_BBS VALUES(10, 'Client10', '제목10', '내용10', '1010', DEFAULT, SYSDATE, '', 10, 0, 0);
INSERT INTO JSP_BBS VALUES(11, 'Client11', '제목11', '내용11', '1111', DEFAULT, SYSDATE, '', 11, 0, 0);
INSERT INTO JSP_BBS VALUES(12, 'Client12', '제목12', '내용12', '1212', DEFAULT, SYSDATE, '', 12, 0, 0);
INSERT INTO JSP_BBS VALUES(13, 'Client13', '제목13', '내용13', '1313', DEFAULT, SYSDATE, '', 13, 0, 0);
INSERT INTO JSP_BBS VALUES(14, 'Client14', '제목14', '내용14', '1414', DEFAULT, SYSDATE, '', 14, 0, 0);
INSERT INTO JSP_BBS VALUES(15, 'Client15', '제목15', '내용15', '1515', DEFAULT, SYSDATE, '', 15, 0, 0);
INSERT INTO JSP_BBS VALUES(16, 'Client16', '제목16', '내용16', '1616', DEFAULT, SYSDATE, '', 16, 0, 0);
INSERT INTO JSP_BBS VALUES(17, 'Client17', '제목17', '내용17', '1717', DEFAULT, SYSDATE, '', 17, 0, 0);
INSERT INTO JSP_BBS VALUES(18, 'Client18', '제목18', '내용18', '1818', DEFAULT, SYSDATE, '', 18, 0, 0);
INSERT INTO JSP_BBS VALUES(19, 'Client19', '제목19', '내용19', '1919', DEFAULT, SYSDATE, '', 19, 0, 0);
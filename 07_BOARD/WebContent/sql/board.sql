-- BOARD 테이블 생성
CREATE TABLE BOARD (
	BOARD_NO NUMBER(5) PRIMARY KEY,		// 번호
	BOARD_WRITER VARCHAR2(30) NOT NULL, // 작성자
	BOARD_TITLE VARCHAR2(200) NOT NULL, // 제목
	BOARD_CONTENT VARCHAR2(1000),		// 내용
	BOARD_PWD VARCHAR2(30),				// 비밀번호
	BOARD_HIT NUMBER(5) DEFAULT 0,		// 조회수
	BOARD_DATE DATE,					// 작성일
	BOARD_UPDATE DATE					// 수정일
);

-- BOARD 테이블에 게시글 추가
INSERT INTO BOARD VALUES(1, '홍길동', '제목1', '길동이 글', '1111', default, sysdate, '');
INSERT INTO BOARD VALUES(2, '이순신', '장군님 글', '이순신 장군님 글', '2222', default, sysdate, '');
INSERT INTO BOARD VALUES(3, '유관순', '제목3', '유관순 열사 글', '3333', default, sysdate, '');
INSERT INTO BOARD VALUES(4, '김유신', '유신님 글', '화랑도 글', '4444', default, sysdate, '');
INSERT INTO BOARD VALUES(5, '김연아', '제목5', '연아님 글', '5555', default, sysdate, '');

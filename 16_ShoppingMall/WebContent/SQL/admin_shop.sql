-- admin_shop 테이블 생성

CREATE TABLE ADMIN_SHOP(
	ADMIN_ID VARCHAR2(30) PRIMARY KEY,	-- 관리자 아이디
	ADMIN_PWD VARCHAR2(30) NOT NULL,	-- 관리자 비밀번호
	ADMIN_NAME VARCHAR2(50) NOT NULL,	-- 관리자 이름
	ADMIN_EMAIL VARCHAR2(200),			-- 관리자 이메일
	ADMIN_DATE DATE						-- 관리자 등록일
);

-- 관리자 등록
INSERT INTO ADMIN_SHOP VALUES('admin', 'admin1234', '관리자', 'admin@naver.com', SYSDATE);
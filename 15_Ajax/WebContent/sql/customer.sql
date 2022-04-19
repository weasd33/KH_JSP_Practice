-- CUSTOMER 테이블 생성

CREATE TABLE CUSTOMER(
	NO NUMBER(5) UNIQUE,			-- 번호
	ID VARCHAR2(30) PRIMARY KEY,	-- 아이디
	NAME VARCHAR2(30) NOT NULL,		-- 이름
	AGE NUMBER(3),					-- 나이
	PHONE VARCHAR2(20) NOT NULL,	-- 전화번호
	ADDR VARCHAR2(500)				-- 주소
);

-- 고객 정보 저장
INSERT INTO CUSTOMER VALUES(1, 'hong', '홍길동', 27, '010-1111-1111', '서울시 중구 남대문로');
INSERT INTO CUSTOMER VALUES(2, 'leess', '이순신', 34, '010-2222-2222', '전라남도 신안군');
INSERT INTO CUSTOMER VALUES(3, 'yooks', '유관순', 19, '010-3333-3333', '충청남도 천안시');

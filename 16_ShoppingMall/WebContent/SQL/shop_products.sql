-- shop_products 테이블 생성

CREATE TABLE SHOP_PRODUCTS(
	PNUM NUMBER(5) PRIMARY KEY,			-- 상품 번호
	PNAME VARCHAR2(50) NOT NULL,		-- 상품명
	PCATEGORY_FK VARCHAR2(10) NOT NULL,	-- 카테고리 코드
	PCOMPANY VARCHAR2(100),				-- 상품 제조사
	PIMAGE VARCHAR2(100),				-- 상품 이미지(상품 파일명)
	PQTY NUMBER(5) DEFAULT 0,			-- 상품 수량
	PRICE NUMBER(10) DEFAULT 0,			-- 상품 가격
	PSPEC VARCHAR2(30),					-- 상품 스펙
	PCONTENTS VARCHAR2(1000),			-- 상품 설명
	POINT NUMBER(6) DEFAULT 0,			-- 상품 포인트
	PINPUTDATE DATE						-- 상품 입고일
);
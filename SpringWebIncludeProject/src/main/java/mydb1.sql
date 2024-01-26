/*
    입문 
     = 자바
       = 변수 / 연산자 / 제어문(******) 
       = 클래스 / 객체 
         = 추상클래스 / 인터페이스 
       = 객체 지향 
         = 오버로딩 , 오버라이딩 , 캡슐화 , 상속 , 포함 
       = 예외처리 
       = Collection / IO
     = 오라클 
       = DML (INSERT,DELETE,UPDATE,SELECT)
         = JOIN / SUBQUERY 
       = DDL (CREATE , DROP , ALTER)
         = 제외조건 
       = TCL (COMMIT,ROLLBACK)
       = PL/SQL (ERP)
         FUNCTION / PROCEDURE / TRIGGER
     = HTML / CSS / JavaScript
       CSS => 약간 수정 
       JavaScript 
         = ES6 => 변수 / 함수 / 이벤트 처리 
         = Jquery 
         = VueJs
         = ReactJS (******)
     = JSP
         = 지시자 , include , JSTL , EL , MVC , GET/POST
     = Spring 
         = Container / DI / AOP / Interceptor / Transaction
         = validate / MVC  
         = MyBatis연결 
     
*/
CREATE OR REPLACE PROCEDURE seoulLocationData(
   pStart NUMBER,
   pEnd NUMBER,
   pResult OUT SYS_REFCURSOR
)
IS
BEGIN 
   OPEN pResult FOR
    SELECT no,title,poster,msg,address,hit,num
    FROM (SELECT no,title,poster,msg,address,hit,rownum as num
    FROM (SELECT no,title,poster,msg,address,hit
    FROM seoul_location ORDER BY no ASC))
    WHERE num BETWEEN pStart AND pEnd;
END;
/

CREATE OR REPLACE PROCEDURE seoulLocatioTotalPage(
   pTotal OUT NUMBER
)
IS
BEGIN
  SELECT CEIL(COUNT(*)/12.0) INTO pTotal
  FROM seoul_location;
END;
/

CREATE OR REPLACE PROCEDURE seoulLocationDetailData(
  pNo seoul_location.no%TYPE,
  pResult OUT SYS_REFCURSOR
)
IS
BEGIN
  OPEN pResult FOR
    SELECT no,title,poster,msg,address,hit
    FROM seoul_location
    WHERE no=pNo;
END;
/
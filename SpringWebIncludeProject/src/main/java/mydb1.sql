/*
    �Թ� 
     = �ڹ�
       = ���� / ������ / ���(******) 
       = Ŭ���� / ��ü 
         = �߻�Ŭ���� / �������̽� 
       = ��ü ���� 
         = �����ε� , �������̵� , ĸ��ȭ , ��� , ���� 
       = ����ó�� 
       = Collection / IO
     = ����Ŭ 
       = DML (INSERT,DELETE,UPDATE,SELECT)
         = JOIN / SUBQUERY 
       = DDL (CREATE , DROP , ALTER)
         = �������� 
       = TCL (COMMIT,ROLLBACK)
       = PL/SQL (ERP)
         FUNCTION / PROCEDURE / TRIGGER
     = HTML / CSS / JavaScript
       CSS => �ణ ���� 
       JavaScript 
         = ES6 => ���� / �Լ� / �̺�Ʈ ó�� 
         = Jquery 
         = VueJs
         = ReactJS (******)
     = JSP
         = ������ , include , JSTL , EL , MVC , GET/POST
     = Spring 
         = Container / DI / AOP / Interceptor / Transaction
         = validate / MVC  
         = MyBatis���� 
     
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
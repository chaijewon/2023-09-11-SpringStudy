package com.sist.dao;

public class MyDAO {
  public void getConnection()
  {
	 System.out.println("오라클 연결...");  
  }
  public void disConnection()
  {
	 System.out.println("오라클 해제...");  
  }
  /*
   *   공통모듈 : getConnection(),disConnection()
   *            => 스프링에 의해 자동 호출 
   *   핵심모듈 : 프로그래머는 핵심만 코딩
   *   ** 반복을 제거하는 역할 (트랜잭션 , 로그파일 , 보안)
   *      => 사용자 정의 AOP는 극히 드물다 
   *      => Footer
   *      
   *   ** 디자인 패턴 : Proxy패턴 => 대신 호출 
   */
  public void select()
  {
	  getConnection();
	  System.out.println("SELECT문장 수행 요청");
	  disConnection();
  }
  public void insert()
  {
	  getConnection();
	  System.out.println("INSERT문장 수행 요청");
	  disConnection();
  }
  public void update()
  {
	  getConnection();
	  System.out.println("UPDATE문장 수행 요청");
	  disConnection();
  }
  public void delete()
  {
	  getConnection();
	  System.out.println("DELETE문장 수행 요청");
	  disConnection();
  }
}

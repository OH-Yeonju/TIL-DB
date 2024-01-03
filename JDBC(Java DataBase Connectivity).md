###### JDBC(Java DataBase Connectivity)

- 자바 프로그래밍 언어로 만들어진 클래스와 인터페이스로 이루어진 API로서 ANSI SQL(1999)를 지원

- SQL문을 실행할 수 있는 함수 호출 인터페이스이다.

- 특징
  
  - DBMS 종류에 독립적인 자바 프로그래밍 가능
  
  - 데이터베이스가 달라지더라도 동일한 API를 사용하게 해준다(드라이버 및 URL만 수정하면 가능)
  
  - 자바가 가지는 플랫폼에 독립적이라는 특성과 DBMS에 독립적이라는 특성을 가진다

- 기능
  
  - 데이터베이스에 연결 설정한다
  
  - SQL 문장을 DBMS에 전송한다
  
  - SQL 문장 전송 후 결과를 처리할 수 있게 해준다

- JDBC Interface
  
  - 데이터베이스를 만드는 업체에게 제공되는 인터페이스
    
    - 업체에게 제공되는 인터페이스를 각각의 DBMS 엊체들이 구현해 놓은 것으로서 이것이 바로 드라이버이다.
  
  - 프로그래머에게 제공되는 인터페이스
    
    - SQL 패키지가 제공하고 있는 라이브러리로서 프로그래머는 이 라이브러리를 기반으로 DB 프로그램을 작성할 수 있다.

- JDBC API : java.sql package
  
  - Driver(interface)
    
    - 드라이버에 대한 정보를 가지고 있다.
    
    - 모든 드라이버가 반드시 구현해야 하는 인터페이스
    
    - 드라이버의 버전이나 연결에 대한 정보를 알아볼 수 있는 메소드를 가지고 있다.
  
  - Connection(interface)
    
    - 데이터베이스에 대한 하나의 세션을 포현한다
      
      세션은 하나의 클라이언트가 서버에 요청을 하기 위해 연결을 맺은 상태를 의미한다.
    
    - drivemanager 클래스의 getCommection() 메소드를 이용하여 얻어올 수 있다.
    
    - 디폴트로 setAutoCommit(true)로 설정된다
    
    - 개발자가 원하는 경우에 commit을 해주고 싶거나 트랜잭션이 아주 중요한 부분에 있어서 Rollback 처리를 하고자 할 경우에는 setAutoCommit(false)로 설정한다.
      
      단, 이 경우에는 SQL문을 수행할때마다 명시적으로 commit 을 호출해야 한다.
  
  - Statement(interface)
    
    - SQL 문장을 실행하고 그것에 대한 결과값을 가져오기 위해서 사용한다
  
  - PreparedStatement(interface)
    
    - 동일한 SQL 문장이 여러번 반복적으로 수행될 때 사용하는 객체
    
    - 다용량의 문자나 바이너리 타입의 데이터(이미지나 사운드 등)를 저장하기 위해서도 사용될 수 있다.
    
    - SQL 문장이 미리 컴파일 되어 PreparedStatement 객체에 저장된다
    
    - 여러번 반복 수행 시 clearParameters() 메소드를 이용해 Statement에 남겨진 값을 초기화 한다.
    
    - public ResultSet executeQuery() throws SQLException
      
      Select를 처리할 때 사용한다
    
    - public int executeUpdate() throws SQLException
      
      주로 DML등의 SQL을 수행할 때 사용한다.
  
  - CallableStatement(interface)
    
    - 데이터베이스에 대하여 실제 SQL문을 실행하는 거싱 아니라 Stored Procedures를 호출한다.
    
    - Stored Procedures란 연속되는 SQL문으로 데이터베이스에 저장해두고 마치 함수의 호출처럼 사용한다
    
    - 데이터베이스에 Stored Procedures를 만들어 두고 자바에서 호출하여 사용할 수 있게 한다.
    
    - Stored Procedures 사용 시 속도의 향상을 기대할 수 있고 자바 코드에 SQL 문장이 들어가지 않으므로 자바 코드가 SQL에 독립적이 된다.
  
  - ResultSet(interface)
    
    - 쿼리에 대한 결과값 처리
    
    - ResultSet 객체의 커서는 첫번째 레코드보다 바로 이전을 가리킨다
    
    - next()
      
      - ResultSet 객체의 커서를 이동
    
    - getXXX(index or name) 메소드를 이용하여 데이터를 얻을 수 있다.
      
      - getString(index or name)
      
      - getInt(index or name)
      
      - getDate(index or name)
      
      - ...



##### JDBC Programming 개발 순서

1. JDBC Driver Loading
   
   - 데이터베이스와의 접속을 오픈하기 위해 애플리케이션의 JVM 안으로 특정 드라이버 클래스를 적재
   
   - Class.forName( Driver ClassName);
   
   - 각 데이터베이스별 드라이버 클래스
     
     - MySQL : com.mysql.cj.jdbc.Driver
     
     - Oracle : oracle.jdbc.driver.OracleDriver
     
     - MSSQL : com.microsoft.sqlserver.jdbc.SQLServerDriver

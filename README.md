**Spring MVC 게시판 프로젝트**
===================

이 프로젝트는 Spring framework를 기반으로 한 MVC 게시판입니다.
DB와 연동하는 데 사용한 라이브러리는 MyBatis입니다.

----------

개발환경
-------------

> - **IDE** :  Eclipse Java EE 4.7.2 (Oxygen.2 Release)
> - **JDK**: Java 1.8
> - **Tomcat** : Tomcat 7.0.85
> - **Maven** : Maven 3.3.9 (Embedded in Eclipse)
> - **Spring Tools** : STS 3.9.2. RELEASE
> -  **DBMS** : Mysql 5.7.19
> - **Bootstrap** :  Bootstrap 4.0.0

라이브러리
---------------

> - springframework 4.3.4. RELEASE
> - mybatis 3.4.1
> - mybatis-spring 1.3.1
> - spring-jdbc 4.3.4
> - spring-test 4.3.4
> - log4jdbc-log4j2-jdbc4 1.16
> - junit 4.12
> - jackson-databind 2.8.6

DB 테이블 구조
-------------------
gongji 테이블은 글 목록 테이블이고,
gongji_comment 테이블은 댓글 테이블입니다.

먼저 이 구조로 테이블을 생성하셔야 합니다.

![table](https://github.com/thdus56/SpringProject/blob/master/%EA%B3%B5%EC%A7%80,%20%EB%8C%93%EA%B8%80%20%ED%85%8C%EC%9D%B4%EB%B8%94.PNG?raw=true)

gongji_comment 테이블에서 중요한 점은 외래키와 제약사항 설정입니다.
gongi 테이블의 id를 참조하고 ON DELETE CASCADE 제약사항을 설정해서 
게시글이 지워지면 댓글까지 지워지도록 했습니다.

![enter image description here](https://github.com/thdus56/SpringProject/blob/master/%EB%8C%93%EA%B8%80%ED%85%8C%EC%9D%B4%EB%B8%94%20%EB%A7%8C%EB%93%A4%EA%B8%B0.PNG?raw=true)

게시판 기능
----------

>  - 게시판 리스트 보기
>  - 새로운 글 쓰기
>  - 글 상세 보기
>  - 글 수정하기
>  - 글 삭제하기
>  - 댓글 목록 보기
>  - 댓글 입력하기
>  - 댓글 수정하기
>  - 댓글 삭제하기

유의사항
-------------
기본 기능만 설계된 상태이고, 예외 처리를 하지 않아 오류가 많을 것이라고 생각합니다.

제작자 : thdus56@hanmail.net

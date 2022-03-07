<div align="center">

<h1> [ HTTP Web Server ] </h1>

<b>바닥부터 작성해 본 HTTP 스펙을 구현한 WAS</b>

</div>

<br>

- 웹 서버의 동작을 이해하기 위해 HTTP 스펙을 구현하고, 이를 이용해 세션 방식의 로그인 API 구현
- 구현에 필요한 최소한의 내용: https://github.com/conagreen/webserver-from-scratch/blob/main/STUDIED.md
- 개발 기간: 2021.07.06 ~ 현재 (웹 서버 구현은 끝났으나 테스트 코드 추가 작성 중)
- JDK 1.8 / Junit 5

<br>

## 개발 내용
- RFC 2616 스펙을 만족하는 HTTP request parser
  - Java ServerSocket을 활용하여 클라이언트의 요청을 받음
  - HTTP Request Message 스펙에 맞게 파싱 및 객체로 변환
  
  <br>
  
- RFC 2616 스펙을 만족하는 HTTP response builder
  - Java ServerSocket을 활용하여 클라이언트에게 응답할 수 있는 `HttpResponse` 객체 생성
  - HTTP Response Message 스펙에 맞게 빌딩 후 응답

<br>

- 자바 서버 소켓을 활용한 서버 통신
  - 클라이언트와 서버 간의 통신을 위한 ServerSocket 생성
  - 클라이언트의 요청이 오면 연결을 맺고, 반환받은 Socket으로 통신을 함

<br>

- 세션 처리를 위한 쿠키 구현
  - stateless한 HTTP 통신을 보완하기 위한 `Cookie`와 `HttpSession` 구현
  - `HttpSession`은 상태를 기억하기 위한 session id와 속성을 가짐
  - 싱글톤의 `SessionManager`로 `HttpSession`객체 관리
  - 로그인 성공 시 session id 정보를 담은 쿠키를 응답 헤더로 응답

<br>

- 서버 사이드 세션 구현
  - ThreadLocal을 활용한 request 스코프 구현
  - `RequestContext` - 요청과 응답에 대한 context를 가지고 있는 모듈
  - `RequestContextHolder` - `RequestContext`의 스레드 로컬에 저장하는 모듈

<br>

- 웹 서버를 사용한 미니 프로젝트 구현
  - 직접 만든 Web Server를 사용하여 세션 방식의 로그인 구현
    - 로그인 성공 - session id 발급 후 해당 경로로 리다이렉트 -> `/hello`
    - 로그인 실패 - 해당 경로로 리다이렉트 -> `/index.html`

<br>

- 스레드 풀을 활용한 다중 요청 처리
  - `ThreadPoolExecutor` 클래스를 사용하여 스레드 풀 객체 생성 및 설정

<br>

- 자바 서블릿의 스펙을 간소화하여 구현
  - Java EE의 HttpServletRequest와 HttpServletResponse를 간소화한 `HttpRequest`, `HttpResponse` 구현

<br>

- 웹 서버 사용 편의성을 위한 부가 기능 제공
  - 사용자 정의 컨트롤러
  - 스태틱 파일 서빙을 위한 컨트롤러
  - 잘못된 경로에 대한 Not Found 컨트롤러
  - 프론트 컨트롤러 패턴을 활용한 DispatchServlet(Spring-Like)
  
<br>

- 테스트 코드 작성
  - junit5를 이용한 단위 테스트 (진행 중)
    - Cookie 생성 및 요청 라인 객체 테스트
    - DispatchResolver 메서드 테스트
    
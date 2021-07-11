# 1. stream

- InputStream: 프로그램이 받는 입력
    - 키 입력
    - 파일 읽기
    - 소켓 (클라 -> 서버)
- OutputStream: 프로그램이 하는 출력
    - 화면 출력
    - 파일 쓰기
    - 소켓 (서버 -> 클라)
    
# 2. 자바에서 읽는 방법

- 바이트 (raw)
    - InputStream
    - OutputStream
- 문자열 (string)
    - Reader
- Writer

# 3. HTTP 프로토콜

```
GET / HTTP/1.1\r\n
Host: localhost:8080\r\n
Connection: keep-alive\r\n
User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36\r\n
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\r\n
Accept-Encoding: gzip, deflate, br\r\n
Accept-Language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7\r\n
```

## 3.1. 요청라인의 구조

- **아래 주소로 브라우저에 입력**
- http://localhost:8080/


- **요청 라인은 다음과 같이 구성됨**
- GET / HTTP/1.1\r\n
    * GET - request method 
    * / - request target (path, uri)
    * HTTP/1.1 - protocol version

## 3.2. 요청 헤더 필드
- 헤더와 값
- 콜론으로 구분

## 3.3. 요청 바디

```
POST / HTTP/1.1\r\n
Host: localhost:8080\r\n
User-Agent: python-requests/2.25.1\r\n
Accept-Encoding: gzip, deflate\r\n
Accept: */*\r\n
Connection: keep-alive\r\n
Content-Length: 46\r\n
Content-Type: application/json\r\n
\r\n
{"username": "username1", "pssword": "123456"}
```

## 3.4. 요청 구조 정리

1. 요청 라인 (요청메서드 요청타겟(경로) 프로토콜버전)
2. 요청 헤더 필드
3. \r\n
4. 요청 바디 (optional)

### [ 요청 문법 ]

**A client sends request messages to the server**

- a request line, consisting of the case-sensitive request method, a space, the request target, another space, the protocol version, a carriage return, and a line feed (e.g. GET /images/logo.png HTTP/1.1);
- zero or more request header fields, each consisting of the case-insensitive field name, a colon, optional leading whitespace, the field value, and optional trailing whitespace (e.g. Accept-Language: en), and ending with a carriage return and a line feed;
- an empty line, consisting of a carriage return and a line feed;
- an optional message body.

> 참고 - [위키피디아](https://en.wikipedia.org/wiki/Hypertext_Transfer_Protocol#cite_note-rfc7230-3-21)


# 4. 응답

1. 상태 라인
 - HTTP/1.1 200 OK
2. 응답 헤더 필드
3. \r\n
4. 메세지 바디(optional)

### [ 응답 문법 ]

**A server sends response messages to the client**

- a status line, consisting of the protocol version, a space, the response status code, another space, a possibly empty reason phrase, a carriage return, and a line feed (e.g. HTTP/1.1 200 OK);
- zero or more response header fields, each consisting of the case-insensitive field name, a colon, optional leading whitespace, the field value, and optional trailing whitespace (e.g. Content-Type: text/html), and ending with a carriage return and a line feed;
- an empty line, consisting of a carriage return and a line feed;
- an optional message body.                 Z

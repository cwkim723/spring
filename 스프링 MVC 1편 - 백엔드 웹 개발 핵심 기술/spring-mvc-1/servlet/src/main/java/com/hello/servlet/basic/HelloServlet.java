package com.hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello") // name: 서블릿 이름, urlPatterns: URL 매핑
public class HelloServlet extends HttpServlet {

    // control + o
    // http 요청을 통해 매핑된 url이 호출되면 서블릿 컨테이너는 다음 메서드 실행
    /* HttpServletRequest
       http 요청 메시지를 개발자가 직접 파싱해서 사용하는 대신 서블릿이 개발자 대신 http 요청 메시지를 파싱하고
       그 결과를 HttpServletRequest 객체에 담아서 제공
       1. 임시 저장소 기능
        저장: request.setAttribute(name, value)
        조회: request.getAttribute(name)
       2. 세션 관리 기능
        request.getSession(create: true)

       HttpServletRequest, HttpServletResponse 객체는 http 요청, 응답 메시지를 편리하게 사용해주는 객체일 뿐임


    */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // http 요청이 오면 was(servlet container)가 request, response 객체를 만들어서 servlet에 던져줌

        System.out.println("HelloServlet.service");
        System.out.println("request = " + request); // request = org.apache.catalina.connector.RequestFacade@77ae0561
        System.out.println("response = " + response); // response = org.apache.catalina.connector.ResponseFacade@62b35e9d
        // HttpServletRequest, HttpServletResponse는 인터페이스 -> WAS 서버들이 이 인터페이스들을 구현한 구현체들이 나타남

        // http://localhost:8090/hello?username=kim 으로 쿼리스트링 작성 후 실행
        String username = request.getParameter("username");
        System.out.println("username = " + username); // username = kim

        response.setContentType("text/plain"); // header 정보
        response.setCharacterEncoding("utf-8"); // header 정보
        response.getWriter().write("hello " + username); // write(): http body에 데이터가 들어감
        /*
            [ http response ]
            [ 헤더 ]
            Content-Type: text/plain;charset=utf-8
            [ 응답 ]
            hello kim
        */

        /*
            Received [GET /hello?username=kim HTTP/1.1

            Host: localhost:8090
            Connection: keep-alive
            Cache-Control: max-age=0
            sec-ch-ua: "Google Chrome";v="107", "Chromium";v="107", "Not=A?Brand";v="24"
            sec-ch-ua-mobile: ?0
            sec-ch-ua-platform: "macOS"
            Upgrade-Insecure-Requests: 1
            User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36
            ... 등의 내용을 콘솔에서 보고 싶으면 properties 파일에서 logging.level.org.apache.coyote.http11=debug 설정
            운영 단계에서는 지양, 개발 단계에서만 적용하기
        */

    }
}

package com.hello.servlet.basic.request;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        printStartLine(request);
        printHeaders(request);
        printHeaderUtils(request);
    }

    private static void printStartLine(HttpServletRequest request) {
        System.out.println("--- REQUEST-LINE - start ---");
        System.out.println("request.getMethod() = " + request.getMethod()); // GET
        System.out.println("request.getProtocol() = " + request.getProtocol()); // HTTP/1.1
        System.out.println("request.getScheme() = " + request.getScheme()); // http
        System.out.println("request.getRequestURL() = " + request.getRequestURL()); // // http://localhost:8080/request-header
        System.out.println("request.getRequestURI() = " + request.getRequestURI()); // /request-header
        System.out.println("request.getQueryString() = " + request.getQueryString()); // null
        System.out.println("request.isSecure() = " + request.isSecure()); // false, https 사용 유무
        System.out.println("--- REQUEST-LINE - end ---");
        System.out.println();
    }

    // Header 모든 정보
    private void printHeaders(HttpServletRequest request) {
        System.out.println("--- Headers - start ---");

        // header 출력 방법 1
        Enumeration<String> headerNames = request.getHeaderNames(); // 모든 헤더 정보를 다 출력 가능
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            System.out.println(headerName + ": " + request.getHeader(headerName));
        }

        /*
        --- Headers - start ---
        host: localhost:8080
        connection: keep-alive
        cache-control: max-age=0
        sec-ch-ua: "Google Chrome";v="107", "Chromium";v="107", "Not=A?Brand";v="24"
        sec-ch-ua-mobile: ?0
        sec-ch-ua-platform: "macOS"
        upgrade-insecure-requests: 1
        user-agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36
        accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*//*;q=0.8,application/signed-exchange;v=b3;q=0.9
        sec-fetch-site: none
        sec-fetch-mode: navigate
        sec-fetch-user: ?1
        sec-fetch-dest: document
        accept-encoding: gzip, deflate, br
        accept-language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7
                --- Headers - end ---
        */

        // header 출력 방법 2
        request.getHeaderNames().asIterator()
                .forEachRemaining(headerName -> System.out.println("headerName = " + headerName));

        System.out.println("--- Headers - end ---");
        System.out.println();
    }

    //Header 편리한 조회
    private void printHeaderUtils(HttpServletRequest request) {
        System.out.println("--- Header 편의 조회 start ---");
        System.out.println("[Host 편의 조회]");
        System.out.println("request.getServerName() = " + request.getServerName()); // localhost
        System.out.println("request.getServerPort() = " + request.getServerPort()); // 8080
        System.out.println();
        System.out.println("[Accept-Language 편의 조회]");
        request.getLocales().asIterator()
                .forEachRemaining(locale -> System.out.println("locale = " + locale));
        /*
            locale = ko_KR
            locale = ko
            locale = en_US
            locale = en
        */
        System.out.println("request.getLocale() = " + request.getLocale()); // ko_KR, 언어 선호가 제일 높게 설정된 언어를 출력
        System.out.println();
        System.out.println("[cookie 편의 조회]");
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                System.out.println(cookie.getName() + ": " + cookie.getValue());
            }
        }
        System.out.println();
        System.out.println("[Content 편의 조회]");
        System.out.println("request.getContentType() = " + request.getContentType()); // null
        System.out.println("request.getContentLength() = " + request.getContentLength()); // -1
        System.out.println("request.getCharacterEncoding() = " + request.getCharacterEncoding()); // UTF-8
        System.out.println("--- Header 편의 조회 end ---");
        System.out.println();
    }

}

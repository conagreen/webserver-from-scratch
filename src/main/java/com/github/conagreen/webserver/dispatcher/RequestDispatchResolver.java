package com.github.conagreen.webserver.dispatcher;

import com.github.conagreen.controller.Controller;
import com.github.conagreen.http.request.HttpRequest;

public interface RequestDispatchResolver {

    /**
    * 이 요청을 처리할 수 있는 컨트롤러인지 검사
    * @param request HTTP 요청
    * @return 요청 가능하면 true
    * */
    boolean supports(HttpRequest request);

    /**
     * @param request HTTP 요청
     * @return 요청을 처리할 컨트롤러
     * */
    Controller dispatch(HttpRequest request);
}

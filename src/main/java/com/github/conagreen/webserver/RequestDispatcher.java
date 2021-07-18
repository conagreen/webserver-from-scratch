package com.github.conagreen.webserver;

import com.github.conagreen.controller.Controller;
import com.github.conagreen.http.request.HttpRequest;
import com.github.conagreen.webserver.dispatcher.RequestDispatchResolver;
import com.github.conagreen.webserver.dispatcher.RequestDispatchResolverComposite;

/*
* 책임: request에 맞는 controller를 내려줌
* 1. 사용자 정의 컨트롤러
* 2. 스태틱 파일 컨트롤러
* 3. 낫파운드
* 4. 템플릿 컨트롤러
* */
public class RequestDispatcher {

    private final RequestDispatchResolver composite = new RequestDispatchResolverComposite();

    public Controller dispatch(HttpRequest request) {
        return composite.dispatch(request);
    }
}



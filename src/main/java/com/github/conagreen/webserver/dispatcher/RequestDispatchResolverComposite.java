package com.github.conagreen.webserver.dispatcher;

import com.github.conagreen.controller.Controller;
import com.github.conagreen.http.request.HttpRequest;
import com.github.conagreen.webserver.NotFoundController;

import java.util.ArrayList;
import java.util.List;

public class RequestDispatchResolverComposite implements RequestDispatchResolver{

    private final List<RequestDispatchResolver> resolvers = new ArrayList<>();
    private static final Controller notFoundController = new NotFoundController();

    public RequestDispatchResolverComposite() {
        resolvers.add(new UserDefindRequestDispatchResolver());
        resolvers.add(new StaticFileRequestDispatchResolver());
    }

    @Override
    public boolean supports(HttpRequest request) {
        for (RequestDispatchResolver resolver : resolvers) {
            if (resolver.supports(request)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Controller dispatch(HttpRequest request) {
        for (RequestDispatchResolver resolver : resolvers) {
            if (resolver.supports(request)) {
                return resolver.dispatch(request);
            }
        }
        return notFoundController;
    }
}

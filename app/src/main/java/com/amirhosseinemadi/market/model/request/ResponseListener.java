package com.amirhosseinemadi.market.model.request;

public interface ResponseListener<T> {

    void onResponse(T response);

    void onFailed(String error);

}

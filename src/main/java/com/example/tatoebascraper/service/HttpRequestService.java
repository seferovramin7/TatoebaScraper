package com.example.tatoebascraper.service;

public interface HttpRequestService {

    <T> T sendGetRequest(String url, Class<T> className);

    <T, V> T sendPostRequest(String url, V v, Class<T> className);

}

package com.serheev.datainfoservice.service;

public interface CDCEventConsumer {

    void handle(String message);

}

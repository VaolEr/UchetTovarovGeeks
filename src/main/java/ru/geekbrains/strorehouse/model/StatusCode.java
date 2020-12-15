package ru.geekbrains.strorehouse.model;

public class StatusCode {
    StatusCodeType status;

    public StatusCode(StatusCodeType status) {
        this.status = status;
    }

    public StatusCodeType getStatus() {
        return status;
    }

    public void setStatus(StatusCodeType status) {
        this.status = status;
    }
}



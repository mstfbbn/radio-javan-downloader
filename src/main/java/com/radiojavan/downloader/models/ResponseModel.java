package com.radiojavan.downloader.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({
        "isOk",
        "message",
        "data",
})
public class ResponseModel<T> {

    @JsonProperty("is_ok")
    private Boolean isOk;

    private String message;

    private T data;

    /*
    * NOTE: we cannot use a constructor with only 'message' attribute.
    * Because, it conflicts with 'data' attribute's constructor, when generic type is 'String.class'.
    */

    public ResponseModel(Boolean isOk, String message) {
        this.isOk = isOk;
        this.message = message;
    }

    public ResponseModel(T data) {
        this.isOk = true;
        this.message = null;
        this.data = data;
    }
}

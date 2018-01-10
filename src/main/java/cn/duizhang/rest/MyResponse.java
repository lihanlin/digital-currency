package cn.duizhang.rest;

/**
 * Created by hanlinli on 2018/1/10.
 */
import com.fasterxml.jackson.annotation.JsonProperty;

public class MyResponse {
    private long id;

    private String content;

    public MyResponse() {
        // Jackson deserialization
    }

    public MyResponse(long id, String content) {
        this.id = id;
        this.content = content;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getContent() {
        return content;
    }
}

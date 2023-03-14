package org.example;

/**
 * 상태 코드.
 */
public enum Status {
    NO_CONTENT("200 No Content"), FORBIDDEN("403 Forbidden"),
    OK("200 OK"), CONFLICT("409 Conflict"), METHOD_NOT_ALLOWED("405 Method Not Allowed"),
    NOT_FOUND("404 Not Found");
    String code;

    Status(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}

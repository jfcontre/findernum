package com.finder.adapters.in.http.finder;

public class RequestDto {
    private Integer x;
    private Integer y;
    private Integer n;

    public RequestDto() {
    }

    public RequestDto(Integer x, Integer y, Integer n) {
        this.x = x;
        this.y = y;
        this.n = n;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n = n;
    }
}

package com.ohgiraffers.understand.dto;

public class MenuDTO {
    private int code;
    private String name;
    private int price;
    private int categoryCode;
    private String status;

    public MenuDTO() {
    }

    public MenuDTO(String status, int categoryCode, int price, String name, int code) {
        this.status = status;
        this.categoryCode = categoryCode;
        this.price = price;
        this.name = name;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MenuDTO{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", categoryCode=" + categoryCode +
                ", status='" + status + '\'' +
                '}';
    }
}

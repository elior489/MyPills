package com.lior.eliyao.mypills;

public class Pill {
    private String name;
    private String code;
    private String batch;
    private String company;
    private int exp;
    private double price;

    public Pill() {
    }

    public Pill(String name, String code, String batch, String company, int exp, double price) {
        this.name = name;
        this.code = code;
        this.batch = batch;
        this.company = company;
        this.exp = exp;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Pill[" +
                "name='" + name + '\'' +
//                ",      code='" + code + '\'' +
//                ",      batch='" + batch + '\'' +
                ",      company='" + company + '\'' +
                ",      exp=" + exp +
//                ",      price=" + price +
                ']';
    }
}
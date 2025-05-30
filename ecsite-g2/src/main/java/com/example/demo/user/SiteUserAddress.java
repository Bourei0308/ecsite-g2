package com.example.demo.user;

public class SiteUserAddress {
    private int ID;                // ユーザーID
    private int addressID;        // 住所ID（AUTO_INCREMENT）
    private Integer postNumber;       // 郵便番号
    private String address1;      // 都道府県など
    private String address2;      // 市区町村など
    private String address3;      // 番地など
    private String address4;      // 建物名など（任意）

    // --- Getters and Setters ---
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public Integer getPostNumber() {
        return postNumber;
    }

    public void setPostNumber(Integer postNumber) {
        this.postNumber = postNumber;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getAddress4() {
        return address4;
    }

    public void setAddress4(String address4) {
        this.address4 = address4;
    }
}

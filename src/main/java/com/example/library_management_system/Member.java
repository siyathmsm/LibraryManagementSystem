package com.example.library_management_system;
public class Member {
    // Private member variables for book properties
    private String cardNo;
    private String memberName;
    private String address;
    private String contactNo;

    // Getter methods for the member properties
    public String getCardNo() {
        return cardNo;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getAddress() {
        return address;
    }

    public String getContactNo() {
        return contactNo;
    }

    // Setter methods for the book properties
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
}


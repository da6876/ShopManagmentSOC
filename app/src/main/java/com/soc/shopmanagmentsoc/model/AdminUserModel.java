package com.soc.shopmanagmentsoc.model;

public class AdminUserModel {
    String admin_id,admin_user_name,password,full_name,mobile_no,
    email_address,address,latitude,longitude,admin_user_status,
    user_type_id,user_type_name ;
    String userType_user_type_id,userType_user_type_name,userType_user_type_status,userType_create_info;
    String shop_id, admin_user_id,  shop_name,contact_person_name, contact_person_phone,  contact_person_email,
     shop_address,  td_no,  est_date,  shop_status,  create_info,update_info, extra;
    public AdminUserModel(String admin_id, String admin_user_name, String password, String full_name, String mobile_no, String email_address, String address, String latitude, String longitude, String admin_user_status, String user_type_id, String user_type_name) {
        this.admin_id = admin_id;
        this.admin_user_name = admin_user_name;
        this.password = password;
        this.full_name = full_name;
        this.mobile_no = mobile_no;
        this.email_address = email_address;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.admin_user_status = admin_user_status;
        this.user_type_id = user_type_id;
        this.user_type_name = user_type_name;
    }
    public AdminUserModel(String userType_user_type_id, String userType_user_type_name, String userType_user_type_status,
                          String userType_create_info) {
        this.userType_user_type_id = userType_user_type_id;
        this.userType_user_type_name = userType_user_type_name;
        this.userType_user_type_status = userType_user_type_status;
        this.userType_create_info = userType_create_info;
    }
    public AdminUserModel(String shop_id, String admin_user_id, String shop_name,
                          String contact_person_name, String contact_person_phone, String contact_person_email,
                          String shop_address, String td_no, String est_date, String shop_status, String create_info,
                          String update_info,String extra) {
        this.shop_id = shop_id;
        this.admin_user_id = admin_user_id;
        this.shop_name = shop_name;
        this.contact_person_name = contact_person_name;
        this.contact_person_phone = contact_person_phone;
        this.contact_person_email = contact_person_email;
        this.shop_address = shop_address;
        this.td_no = td_no;
        this.est_date = est_date;
        this.shop_status = shop_status;
        this.create_info = create_info;
        this.update_info = update_info;
        this.extra = extra;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getAdmin_user_id() {
        return admin_user_id;
    }

    public void setAdmin_user_id(String admin_user_id) {
        this.admin_user_id = admin_user_id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getContact_person_name() {
        return contact_person_name;
    }

    public void setContact_person_name(String contact_person_name) {
        this.contact_person_name = contact_person_name;
    }

    public String getContact_person_phone() {
        return contact_person_phone;
    }

    public void setContact_person_phone(String contact_person_phone) {
        this.contact_person_phone = contact_person_phone;
    }

    public String getContact_person_email() {
        return contact_person_email;
    }

    public void setContact_person_email(String contact_person_email) {
        this.contact_person_email = contact_person_email;
    }

    public String getShop_address() {
        return shop_address;
    }

    public void setShop_address(String shop_address) {
        this.shop_address = shop_address;
    }

    public String getTd_no() {
        return td_no;
    }

    public void setTd_no(String td_no) {
        this.td_no = td_no;
    }

    public String getEst_date() {
        return est_date;
    }

    public void setEst_date(String est_date) {
        this.est_date = est_date;
    }

    public String getShop_status() {
        return shop_status;
    }

    public void setShop_status(String shop_status) {
        this.shop_status = shop_status;
    }

    public String getCreate_info() {
        return create_info;
    }

    public void setCreate_info(String create_info) {
        this.create_info = create_info;
    }

    public String getUpdate_info() {
        return update_info;
    }

    public void setUpdate_info(String update_info) {
        this.update_info = update_info;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getUserType_user_type_id() {
        return userType_user_type_id;
    }

    public void setUserType_user_type_id(String userType_user_type_id) {
        this.userType_user_type_id = userType_user_type_id;
    }

    public String getUserType_user_type_name() {
        return userType_user_type_name;
    }

    public void setUserType_user_type_name(String userType_user_type_name) {
        this.userType_user_type_name = userType_user_type_name;
    }

    public String getUserType_user_type_status() {
        return userType_user_type_status;
    }

    public void setUserType_user_type_status(String userType_user_type_status) {
        this.userType_user_type_status = userType_user_type_status;
    }

    public String getUserType_create_info() {
        return userType_create_info;
    }

    public void setUserType_create_info(String userType_create_info) {
        this.userType_create_info = userType_create_info;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_user_name() {
        return admin_user_name;
    }

    public void setAdmin_user_name(String admin_user_name) {
        this.admin_user_name = admin_user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAdmin_user_status() {
        return admin_user_status;
    }

    public void setAdmin_user_status(String admin_user_status) {
        this.admin_user_status = admin_user_status;
    }

    public String getUser_type_id() {
        return user_type_id;
    }

    public void setUser_type_id(String user_type_id) {
        this.user_type_id = user_type_id;
    }

    public String getUser_type_name() {
        return user_type_name;
    }

    public void setUser_type_name(String user_type_name) {
        this.user_type_name = user_type_name;
    }
}

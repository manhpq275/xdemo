package vn.mvv.xconnect.models;

import java.util.Date;

import vn.mvv.xconnect.models.enums.Gender;
import vn.mvv.xconnect.models.enums.Region;

/**
 * Created by phuc.nguyen on 5/26/2016.
 */
public class ConsumerView extends BaseView {
    public String getCode() {
        return code;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    private String json;

    public void setCode(String code) {
        this.code = code;
    }

    private String code;

    private String email;

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    private String address;
    private Region region;
    private String phone;
    private Gender gender;
    private Date dateOfBirth;
    private String avatarUrl;
    private String access_token;

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public Region getRegion() {
        return region;
    }

    public String getPhone() {
        return phone;
    }

    public Gender getGender() {
        return gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getAccess_token() {
        return access_token;
    }
}

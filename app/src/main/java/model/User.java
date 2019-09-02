package model;

import com.google.gson.annotations.SerializedName;

/**
 * Dibuat oleh petersam
 */
public class User {
    @SerializedName("id")
    private Integer id;

    @SerializedName("uname")
    private String uname;

    @SerializedName("password")
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

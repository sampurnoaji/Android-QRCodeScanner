package id.io.barcodescanner.main.response;

/**
 * Dibuat oleh petersam
 */
public class UserDetails {
    private String username;
    private String alias;
    private String role;
    private String memberCode;
    private String email;
    private String image;
    private String level;
    private String department;

    public UserDetails(String username, String alias, String role, String memberCode, String email, String image, String level, String department) {
        this.username = username;
        this.alias = alias;
        this.role = role;
        this.memberCode = memberCode;
        this.email = email;
        this.image = image;
        this.level = level;
        this.department = department;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}

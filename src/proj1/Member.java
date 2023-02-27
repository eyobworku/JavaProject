package proj1;

public class Member {
    private int memId;
    private String fullName;
    private String phoneNo;
    private String email;

    public Member(int memId, String fullName, String phoneNo, String email) {
        this.memId = memId;
        this.fullName = fullName;
        this.phoneNo = phoneNo;
        this.email = email;
    }

    public int getMemId() {
        return this.memId;
    }

    public void setMemId(int memId) {
        this.memId = memId;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNo() {
        return this.phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

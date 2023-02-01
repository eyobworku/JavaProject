package proj1;

public class Account {
    private int id;
    private String userName;
    private String password;
    private String roll;

    public Account(int id, String userName, String password, String roll) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.roll = roll;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoll() {
        return this.roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    @Override
    public String toString(){
        return (getId()+", "+getUserName());
    }
}

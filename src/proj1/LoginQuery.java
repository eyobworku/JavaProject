package proj1;
import java.sql.*;

public class LoginQuery {
    private static final String URL="jdbc:mysql://localhost/project";
    private static final String User="root";
    private static final String Password="pass";
    private Connection connection;
    private PreparedStatement loginUserQuery;

    LoginQuery()  {
        try{
            connection= DriverManager.getConnection(URL,User,Password);
            loginUserQuery=connection.prepareStatement("SELECT * "+
                    "FROM userAccount WHERE userName=? AND password=?");
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    public Account loginUser(String userName,String passWord){
        try {
            loginUserQuery.setString(1, userName);
            loginUserQuery.setString(2, passWord);  
            ResultSet resultSet=loginUserQuery.executeQuery();
            if (resultSet.next()){
                Account logedUser = new Account(
                    Integer.parseInt(resultSet.getString("id")),
                    resultSet.getString("userName"),
                    resultSet.getString("password"),
                    resultSet.getString("roll")
                );
                return logedUser;
            }else{
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void close(){
        try{
            connection.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}

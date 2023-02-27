package proj1;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Queries {
    private static final String URL="jdbc:mysql://localhost/project";
    private static final String User="root";
    private static final String Password="pass";
    private Connection connection;
    private PreparedStatement loginUserQuery;
    private PreparedStatement selectAllUser;
    private PreparedStatement createAccountQuery;
    private PreparedStatement deleteAccountQuery;
    private PreparedStatement editAccountQuery;
    private PreparedStatement createBookQuery;
    private PreparedStatement updateBookQuery;
    private PreparedStatement getAllBookQuery;
    private PreparedStatement addMemberQuery;
    private PreparedStatement editMemberQuery;
    private PreparedStatement getAllMemberQuery;
    private CallableStatement borrowBookQuery;
    private CallableStatement returnBookQuery;
    private PreparedStatement getAllBorrowedQuery;
    Queries()  {
        try{
            connection= DriverManager.getConnection(URL,User,Password);
            loginUserQuery=connection.prepareStatement("SELECT * "+
                    "FROM userAccount WHERE userName=? AND password=?");
            selectAllUser=connection.prepareStatement("SELECT * FROM userAccount");
            createAccountQuery=connection.prepareStatement("INSERT INTO useraccount(userName,password,roll,phoneNo) values(?,?,?,?);");
            deleteAccountQuery=connection.prepareStatement("DELETE FROM useraccount WHERE id=?;");
            editAccountQuery=connection.prepareStatement("update useraccount set username=?, password=?, roll=?, phoneNo=? where id = ?;");
            createBookQuery=connection.prepareStatement("INSERT INTO books(bookName,authorName,typeOf,noBook,image) values(?,?,?,?,?);");
            updateBookQuery=connection.prepareStatement("update books set bookName=?, authorName=?, typeOf=?, noBook=?, image=? where bookId = ?;");
            getAllBookQuery=connection.prepareStatement("SELECT * FROM books");
            addMemberQuery=connection.prepareStatement("INSERT INTO members(fullName,phoneNo,email) VALUES (?,?,?);");
            editMemberQuery=connection.prepareStatement("update members set fullName=?, phoneNo=?, email=? where mbId = ?;");
            getAllMemberQuery=connection.prepareStatement("SELECT * FROM members");
            borrowBookQuery=connection.prepareCall("call borrow_book(?,?);");
            returnBookQuery=connection.prepareCall("call return_book(?);");
            getAllBorrowedQuery=connection.prepareStatement("SELECT * FROM borrows where statusOf='pending';");

        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    public int borrowBook(int bookid,int mebid){
        try {
            borrowBookQuery.setInt(1, bookid);
            borrowBookQuery.setInt(2, mebid);
            int result = borrowBookQuery.executeUpdate();
            return result;
        } catch (SQLException ex){
                // ex.printStackTrace();
                return 0;
        }
    }

    public int returnBook(int borrowId){
        try {
            returnBookQuery.setInt(1, borrowId);
            int result = returnBookQuery.executeUpdate();
            return result;
        } catch (SQLException ex){
                // ex.printStackTrace();
                return 0;
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
                    resultSet.getString("phoneNo"),
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

    public List<Account> getAllUser(){
        try(ResultSet resultSet= selectAllUser.executeQuery("SELECT * FROM userAccount")){
            List<Account> results=new ArrayList<>();
            while (resultSet.next()){
                results.add(new Account (
                    Integer.parseInt(resultSet.getString("id")),
                    resultSet.getString("userName"),
                    resultSet.getString("password"),
                    resultSet.getString("phoneNo"),
                    resultSet.getString("roll")
                ));
            }
            return results;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Borrow> getAllBorrow(){
        try(ResultSet resultSet= getAllBorrowedQuery.executeQuery("SELECT * FROM borrows where statusOf='pending';")){
            List<Borrow> results=new ArrayList<>();
            while (resultSet.next()){
                results.add(new Borrow (
                    Integer.parseInt(resultSet.getString("borrowId")),
                    Integer.parseInt(resultSet.getString("bookId")),
                    Integer.parseInt(resultSet.getString("mbId")),
                    resultSet.getString("statusOf")
                ));
            }
            return results;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Member> getAllMember(){
        try(ResultSet resultSet= getAllMemberQuery.executeQuery("SELECT * FROM members")){
            List<Member> results=new ArrayList<>();
            while (resultSet.next()){
                results.add(new Member (
                    Integer.parseInt(resultSet.getString("mbId")),
                    resultSet.getString("fullName"),
                    resultSet.getString("phoneNo"),
                    resultSet.getString("email")
                ));
            }
            return results;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
        public int createUserAccount(String username,String password,String roll,String phoneno){
            try {
            createAccountQuery.setString(1, username);
            createAccountQuery.setString(2, password);
            createAccountQuery.setString(3, roll);
            createAccountQuery.setString(4, phoneno);

            int result = createAccountQuery.executeUpdate();
            return result;
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            }
        }
        public int createMember(Member member){
            try {
                addMemberQuery.setString(1, member.getFullName());
                addMemberQuery.setString(2, member.getPhoneNo());
                addMemberQuery.setString(3, member.getEmail());
            int result = addMemberQuery.executeUpdate();
            return result;
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            }
        }
        public int deleteAccount(int id){
            try {
                deleteAccountQuery.setInt(1, id);
                int result = deleteAccountQuery.executeUpdate();
                return result;
            } catch (SQLException e) {
                System.out.println("User does not deleted;");
                e.printStackTrace();
                return 0;
            }
        }
        
        public int ediAccount(Account user){
            try {
            editAccountQuery.setString(1, user.getUserName());
            editAccountQuery.setString(2, user.getPassword());
            editAccountQuery.setString(3, user.getRoll());
            editAccountQuery.setString(4, user.getPhoneNo());
            editAccountQuery.setInt(5, user.getId());
            int result;
                result = editAccountQuery.executeUpdate();
                return result;
            } catch (SQLException e) {
                System.out.println("user does not edited");
                e.printStackTrace();
                return 0;
            }
        }
        public int editMember(Member member){
            try {
                editMemberQuery.setString(1, member.getFullName());
                editMemberQuery.setString(2, member.getPhoneNo());
                editMemberQuery.setString(3, member.getEmail());
                editMemberQuery.setInt(4, member.getMemId());
            int result;
                result = editMemberQuery.executeUpdate();
                return result;
            } catch (SQLException e) {
                System.out.println("member does not edited");
                e.printStackTrace();
                return 0;
            }
        }

        public int createBook(Book book){
            try {
            createBookQuery.setString(1, book.getBookName());
            createBookQuery.setString(2, book.getAuthor());
            createBookQuery.setString(3, book.getGener());
            createBookQuery.setInt(4, book.getQuantity());
            createBookQuery.setString(5, book.getImageBook());

            int result = createBookQuery.executeUpdate();
            return result;
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            }
        }
        public int editBook(Book book){
            try {
            updateBookQuery.setString(1, book.getBookName());
            updateBookQuery.setString(2, book.getAuthor());
            updateBookQuery.setString(3, book.getGener());
            updateBookQuery.setInt(5, book.getQuantity());
            updateBookQuery.setString(4, book.getImageBook());
            updateBookQuery.setInt(6, book.getBookId());
            int result;
                result = updateBookQuery.executeUpdate();
                return result;
            } catch (SQLException e) {
                System.out.println("user does not edited");
                e.printStackTrace();
                return 0;
            }
        }
        public List<Book> getAllBook(){
            try(ResultSet resultSet= getAllBookQuery.executeQuery("SELECT * FROM books")){
                List<Book> results=new ArrayList<>();
                while (resultSet.next()){
                    results.add(new Book (
                        Integer.parseInt(resultSet.getString("bookId")),
                        resultSet.getString("bookName"),
                        resultSet.getString("authorName"),
                        resultSet.getString("typeOf"),
                        Integer.parseInt(resultSet.getString("noBook")),
                        resultSet.getString("image")
                    ));
                }
                return results;
    
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
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

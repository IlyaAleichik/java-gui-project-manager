package models;

public class User {

    private int UserId;
    private String UserName;
    private String UserSurname;
    private String UserPatronymic;
    private String UserPassword;
    private String UserImg;
    private String UserPhone;
    private String UserEmail;
    private String UserNickname;
    private int UserRole;


    public User(){}
    public User(int user_id, String user_name, String user_surname, String user_patronymic, String user_password, String user_phone, String user_email, String user_nickname, int user_role){
        UserId = user_id;
        UserName = user_name;
        UserSurname = user_surname;
        UserPatronymic = user_patronymic;
        UserPassword = user_password;
        UserPhone = user_phone;
        UserEmail = user_email;
        UserNickname = user_nickname;
    }
    public User(String userName, String userSurname, String userPatronymic, String userPassword, String userPhone, String userEmail, String userNickname) {
        UserName = userName;
        UserSurname = userSurname;
        UserPatronymic = userPatronymic;
        UserPassword = userPassword;
        UserPhone = userPhone;
        UserEmail = userEmail;
        UserNickname = userNickname;
    }

    public int getUserId() { return UserId;}

    public void setUserId(int userId) { UserId = userId;}

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserSurname() {
        return UserSurname;
    }

    public void setUserSurname(String userSurname) {
        UserSurname = userSurname;
    }

    public String getUserPatronymic() {
        return UserPatronymic;
    }

    public void setUserPatronymic(String userPatronymic) {
        UserPatronymic = userPatronymic;
    }

    public String getUserImg() { return UserImg; }

    public void setUserImg(String userImg) { UserImg = userImg; }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }

    public String getUserPhone() {
        return UserPhone;
    }

    public void setUserPhone(String userPhone) {
        UserPhone = userPhone;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getUserNickname() {
        return UserNickname;
    }

    public void setUserNickname(String userNickname) {
        UserNickname = userNickname;
    }

    public int getUserRole() {
        return UserRole;
    }

    public void setUserRole(int userRole) { UserRole = userRole; }

}

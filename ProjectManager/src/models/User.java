package models;

public class User {
    private String UserName;
    private String UserSurname;
    private String UserPatronymic;
    private String UserPassword;
    private String UserPhone;
    private String UserEmail;
    private String UserNickname;
    private String UserRole;

    public User(String userName, String userSurname, String userPatronymic, String userPassword, String userPhone, String userEmail, String userNickname) {
        UserName = userName;
        UserSurname = userSurname;
        UserPatronymic = userPatronymic;
        UserPassword = userPassword;
        UserPhone = userPhone;
        UserEmail = userEmail;
        UserNickname = userNickname;
    }

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

    public String getUserRole() {
        return UserRole;
    }

    public void setUserRole(String userRole) {
        UserRole = userRole;
    }

}

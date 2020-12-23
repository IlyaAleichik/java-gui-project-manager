package models;

public class ActivityLog {
    public String getLog_time() {
        return log_time;
    }

    public void setLog_time(String log_time) {
        this.log_time = log_time;
    }

    public String getLog_date() {
        return log_date;
    }

    public void setLog_date(String log_date) {
        this.log_date = log_date;
    }

    public String getLog_user_ip() {
        return log_user_ip;
    }

    public void setLog_user_ip(String log_user_ip) {
        this.log_user_ip = log_user_ip;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    String log_time;;
    String log_date;
    String log_user_ip;
    String user_email;
    String user_nickname;
    String role_name;

    public ActivityLog(String log_time, String log_date, String log_user_ip, String user_email, String user_nickname, String role_name) {
        this.log_time = log_time;
        this.log_date = log_date;
        this.log_user_ip = log_user_ip;
        this.user_email = user_email;
        this.user_nickname = user_nickname;
        this.role_name = role_name;
    }

}

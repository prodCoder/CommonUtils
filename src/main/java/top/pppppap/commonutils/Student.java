package top.pppppap.commonutils;

/**
 * 学生实体
 *
 * @author pppppap
 * @since 2018-05-20 上午 9:49
 */

public class Student extends BaseEntity {
    private String userinfo_sex;
    private String user_id;
    private String userinfo_name;
    private String userinfo_tel;
    private String userinfo_birth;
    private String userinfo_email;

    public String getUserinfo_sex() {
        return userinfo_sex;
    }

    public void setUserinfo_sex(String userinfo_sex) {
        this.userinfo_sex = userinfo_sex;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUserinfo_name() {
        return userinfo_name;
    }

    public void setUserinfo_name(String userinfo_name) {
        this.userinfo_name = userinfo_name;
    }

    public String getUserinfo_tel() {
        return userinfo_tel;
    }

    public void setUserinfo_tel(String userinfo_tel) {
        this.userinfo_tel = userinfo_tel;
    }

    public String getUserinfo_birth() {
        return userinfo_birth;
    }

    public void setUserinfo_birth(String userinfo_birth) {
        this.userinfo_birth = userinfo_birth;
    }

    public String getUserinfo_email() {
        return userinfo_email;
    }

    public void setUserinfo_email(String userinfo_email) {
        this.userinfo_email = userinfo_email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "userinfo_sex='" + userinfo_sex + '\'' +
                ", user_id='" + user_id + '\'' +
                ", userinfo_name='" + userinfo_name + '\'' +
                ", userinfo_tel='" + userinfo_tel + '\'' +
                ", userinfo_birth='" + userinfo_birth + '\'' +
                ", userinfo_email='" + userinfo_email + '\'' +
                '}';
    }
}

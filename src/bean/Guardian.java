package bean;

public class Guardian implements java.io.Serializable {

    private String guardian_id;
    private String guardian_name;
    private String password;
    private String student_id;  // student_id フィールドを追加

    // guardian_id のゲッター
    public String getGuardianId() {
        return guardian_id;
    }

    // guardian_name のゲッター
    public String getGuardianName() {
        return guardian_name;
    }

    // password のゲッター
    public String getPassword() {
        return password;
    }

    // student_id のゲッター
    public String getStudentId() {
        return student_id;
    }

    // guardian_id のセッター
    public void setGuardianId(String guardian_id) {
        this.guardian_id = guardian_id;
    }

    // guardian_name のセッター
    public void setGuardianName(String guardian_name) {
        this.guardian_name = guardian_name;
    }

    // password のセッター
    public void setPassword(String password) {
        this.password = password;
    }

    // student_id のセッター
    public void setStudentId(String student_id) {
        this.student_id = student_id;
    }
}

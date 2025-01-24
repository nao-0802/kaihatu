
package bean;

public class Allergy {
    private int allergy_id;
    private String allergy_name;
    private boolean isChecked; // 生徒がアレルギーを持っているかどうか

    // allergyId のゲッターとセッター
    public int getAllergyId() {
        return allergy_id;
    }

    public void setAllergyId(int allergy_id) {
        this.allergy_id = allergy_id;
    }

    // allergyName のゲッターとセッター
    public String getAllergyName() {
        return allergy_name;
    }

    public void setAllergyName(String allergy_name) {
        this.allergy_name = allergy_name;
    }
    public boolean getIsChecked() {
        return isChecked;
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }
}




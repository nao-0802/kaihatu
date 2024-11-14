package admin;

import dao.GuardianDao;

public class GuardianDeleteExecuteAction {
	public static void main(String[] args) {
        // 削除するTeacherのIDを指定（例として1を指定）
        int guardianIdToDelete = 1;

        // TeacherDAOをインスタンス化して削除実行
        GuardianDao guardianDAO = new GuardianDao();
        boolean isDeleted = guardianDAO.deleteTeacherById(guardianIdToDelete);

        // 削除結果の出力
        if (isDeleted) {
            System.out.println("Teacher with ID " + guardianIdToDelete + " was successfully deleted.");
        } else {
            System.out.println("Failed to delete teacher with ID " + guardianIdToDelete);
        }
    }
}

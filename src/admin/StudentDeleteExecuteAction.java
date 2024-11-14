package admin;

import dao.StudentDao;

public class StudentDeleteExecuteAction {
	public static void main(String[] args) {
        // 削除するTeacherのIDを指定（例として1を指定）
        int studentIdToDelete = 1;

        // TeacherDAOをインスタンス化して削除実行
        StudentDao studentDAO = new StudentDao();
        boolean isDeleted = studentDAO.deleteTeacherById(studentIdToDelete);

        // 削除結果の出力
        if (isDeleted) {
            System.out.println("Student with ID " + studentIdToDelete + " was successfully deleted.");
        } else {
            System.out.println("Failed to delete teacher with ID " + studentIdToDelete);
        }
    }
}

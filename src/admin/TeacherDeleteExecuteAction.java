package admin;

import dao.TeacherDao;

public class TeacherDeleteExecuteAction {
    public static void main(String[] args) {
        // 削除するTeacherのIDを指定（例として1を指定）
        int teacherIdToDelete = 1;

        // TeacherDAOをインスタンス化して削除実行
        TeacherDao teacherDAO = new TeacherDao();
        boolean isDeleted = teacherDAO.deleteTeacherById(teacherIdToDelete);

        // 削除結果の出力
        if (isDeleted) {
            System.out.println("Teacher with ID " + teacherIdToDelete + " was successfully deleted.");
        } else {
            System.out.println("Failed to delete teacher with ID " + teacherIdToDelete);
        }
    }
}
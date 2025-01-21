package admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.ClassDao;
import dao.StudentDao;
import dao.StudentRecordDao;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {
   @Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

       StudentDao sDao = new StudentDao();
       ClassDao cDao = new ClassDao(); // ClassDaoのインスタンスを作成
       StudentRecordDao srDao = new StudentRecordDao();


       // フォームから送信されたデータを取得
       String studentId = req.getParameter("studentId");  // 教職員ID
        String studentName = req.getParameter("studentName");  // 氏名
        String className = req.getParameter("className");  // クラス名
        String flagStr = req.getParameter("flag");  // 有効/無効

        int flag = 0;
        if (flagStr != null && flagStr.equals("0")) {
            flag = 0;  // 有効
        } else if (flagStr != null && flagStr.equals("1")) {
            flag = 1;  // 無効
        }

     // クラス名からClass_idを取得
        String classId = cDao.findClassIdByClassName(className);
        if (classId == null) {
            // 該当クラスが見つからない場合のエラーハンドリング
            req.setAttribute("errorMessage", "指定されたクラス名に対応するクラスが見つかりません。");
            req.getRequestDispatcher("../admin/student_update.jsp").forward(req, res);
            return;
        }

     // デバッグ情報
        System.out.println("クラス名: " + className);
        System.out.println("取得されたクラスID: " + classId);


        // Teacherオブジェクトにデータをセット
        Student updatedStudent = new Student();
        updatedStudent.setStudentId(studentId);
        updatedStudent.setStudentName(studentName);
        updatedStudent.setClassId(classId); // クラスIDをセット
        updatedStudent.setFlag(flag);


        System.out.println("更新される生徒情報: " + updatedStudent);

        // 教職員情報の更新
        boolean success = sDao.save(updatedStudent);  // 更新メソッドを呼び出す

        srDao.updateClassId(studentId, classId);


        if (success) {
            // 更新成功時
            req.getRequestDispatcher("../admin/student_update_done.jsp").forward(req, res);
        } else {
            // 更新失敗時（任意でエラーメッセージを表示する場合）
            req.setAttribute("errorMessage", "情報の更新に失敗しました。再度試してください。");
            req.getRequestDispatcher("../admin/student_update.jsp").forward(req, res);
        }
   }
}
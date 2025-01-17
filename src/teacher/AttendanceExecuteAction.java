package teacher;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Attendance;
import dao.AttendanceDao;
import tool.Action;

public class AttendanceExecuteAction extends Action {

    private AttendanceDao dao = new AttendanceDao(); // DAOのインスタンスをフィールドで管理

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // セッションから教師ID（userId）を取得
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("userId");  // セッションから取得した教師ID

        // userIdがnullの場合、ログイン画面にリダイレクト
        if (userId == null) {
            res.sendRedirect("login.jsp");  // ログインしていない場合はリダイレクト
            return;
        }

        List<Attendance> attendanceList = null;

        try {
            // 出席情報を取得
            attendanceList = dao.getAttendancesByTeacherId(userId);  // userIdを使用して出席情報を取得
        } catch (Exception e) {
            // 出席情報の取得に失敗した場合
            req.setAttribute("errorMessage", "出席情報の取得に失敗しました。");
            req.getRequestDispatcher("error.jsp").forward(req, res);
            return;
        }

        // 出席情報が空の場合
        if (attendanceList == null || attendanceList.isEmpty()) {
            req.setAttribute("message", "現在、出席情報はありません。");
        } else {
            // 出席情報がある場合
            req.setAttribute("attendanceList", attendanceList);
        }

        // attendancelist.jspにフォワード
        req.getRequestDispatcher("attendancelist.jsp").forward(req, res);
    }
}

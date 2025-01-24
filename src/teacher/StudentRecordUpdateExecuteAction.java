package teacher;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.StudentRecord;
import dao.StudentRecordDao;
import tool.Action;

public class StudentRecordUpdateExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String studentId = request.getParameter("studentId");
        String classId = request.getParameter("classId");
        String features = request.getParameter("features");
        String[] allergyIds = request.getParameterValues("allergy_id");

        StudentRecordDao dao = new StudentRecordDao();
        StudentRecord record = new StudentRecord();
        record.setStudentId(studentId);
        record.setClassId(classId);
        record.setFeatures(features);

        dao.updateStudentRecord(record);

        List<Integer> allergyIdList = new ArrayList<>();
        if (allergyIds != null) {
            for (String id : allergyIds) {
                allergyIdList.add(Integer.parseInt(id));
            }
        }
        dao.updateAllergies(studentId, allergyIdList);

        request.setAttribute("studentId", studentId);

        request.getRequestDispatcher("../teacher/student_record_update_done.jsp").forward(request, response);
    }
}

package teacher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentRecordDao;
import tool.Action;

public  class StudentRecordUpdateExecuteAction extends Action {


  @Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
	  String studentrecordid=request.getParameter("studentrecordid");
	  String allergy=request.getParameter("allergy");
	  String features=request.getParameter("features");
	  String class_id=request.getParameter("class_id");

	  StudentRecordDao dao=new StudentRecordDao();
	  dao.updateAllergyAndFeatures(studentrecordid, allergy, features);

	  request.setAttribute("class_id", class_id);

	  request.getRequestDispatcher("/teacher/ClassSelectExecuteAction")
	    .forward(request, response);
  }
}
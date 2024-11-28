package teacher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public  class StudentRecordUpdateExecuteAction extends Action {


  @Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
	  String studentrecordid=request.getParameter("studentrecordid");
	  String allergy=request.getParameter("allergy");
	  String features=request.getParameter("features");
	}
}
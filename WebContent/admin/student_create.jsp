<%-- 教職員登録 JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">すてっぷのーと</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section class="mo-4">
            <h2 class="h3 mb-3 fw-normal bg-opacity-10 py-2 px-4" style="background-color:#f0f1f2;">生徒情報登録</h2>
            <form method="post" action="StudentSignupExecute.action" autocomplete="off">
                <div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
                    <div>
                        <label class="form-label" for="student-id-input">生徒ID</label>
                        <input class="form-control" id="student-id-input" name="student_id" value="${student_id}" maxlength="10" required placeholder="生徒IDを入力してください" autocomplete="off">
                        <div class="mt-2 text-warning">${errors.student_id}</div>
                    </div>
                    <div>
                        <label class="form-label" for="student-name-input">氏名</label>
                        <input class="form-control" id="student-name-input" name="student_name" value="${student_name}" maxlength="30" required placeholder="氏名を入力してください">
                        <div class="mt-2 text-warning">${errors.student_name}</div>
                    </div>
                    <div>
                        <label class="form-label" for="student-class-select">クラス</label>
                        <select class="form-select" id="student-class-select" name="class_id" required>
                            <option value="">--クラスを選択--</option>
                            <c:forEach var="classItem" items="${class_list}">
                                <option value="${classItem.classId}" <c:if test="${classItem.classId == class_id}">selected</c:if>>${classItem.className}</option>
                            </c:forEach>
                        </select>
                        <div class="mt-2 text-warning">${errors.class_id}</div>
                    </div>
                    <br>
                    <div>
                        <br>
                        <button class="btn btn-secondary" id="register-button" type="submit">登録</button>
                    </div>
                    <br>
                    <div><a href="../scoremanager/AccountList.action">戻る</a></div>
                </div>
            </form>
        </section>
    </c:param>
</c:import>

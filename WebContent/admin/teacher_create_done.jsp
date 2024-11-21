<%-- 教職員登録成功 JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section class="mo-4">
            <h2 class="h3 mb-3 fw-normal bg-opacity-10 py-2 px-4" style="background-color:#f0f1f2;">登録完了</h2>
            <p>教職員情報が正常に登録されました。</p>
            <div>
                <a href="./TeacherSignup.action" class="btn btn-secondary">新しい教職員を登録する</a>
            </div>
            <div>
                <a href="./AccountList.action" class="btn btn-primary mt-3">一覧に戻る</a>
            </div>
        </section>
    </c:param>
</c:import>

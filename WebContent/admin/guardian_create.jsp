<%-- 保護者登録 JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">保護者情報登録</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section class="mo-4">
            <h2 class="h3 mb-3 fw-normal bg-opacity-10 py-2 px-4" style="background-color:#f0f1f2;">保護者情報登録</h2>
            <form method="post" action="GuardianSignupExecute.action">
                <div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
                    <div>
                        <label class="form-label" for="guardian-id-input">保護者ID</label>
                        <input class="form-control" id="guardian-id-input" name="guardian_id" value="${guardian_id}" maxlength="10" required placeholder="保護者IDを入力してください">
                        <div class="mt-2 text-warning">${errors.guardian_id}</div>
                    </div>
                    <div>
                        <label class="form-label" for="guardian-name-input">氏名</label>
                        <input class="form-control" id="guardian-name-input" name="guardian_name" value="${guardian_name}" maxlength="30" required placeholder="氏名を入力してください">
                        <div class="mt-2 text-warning">${errors.guardian_name}</div>
                    </div>
                    <div>
                        <label class="form-label" for="guardian-password-input">パスワード</label>
                        <input type="password" class="form-control" id="guardian-password-input" name="password" maxlength="20" required placeholder="パスワードを入力してください">
                        <div class="mt-2 text-warning">${errors.password}</div>
                    </div>
                    <br>
                    <div>
                        <br>
                        <button class="btn btn-secondary" id="register-button" type="submit">登録</button>
                    </div>
                    <br>
                    <div><a href="./AccountList.action">戻る</a></div>
                </div>
            </form>
        </section>
    </c:param>
</c:import>

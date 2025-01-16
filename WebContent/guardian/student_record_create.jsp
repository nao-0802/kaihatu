<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="bean.Student" %>
<%
    List<Student> students = (List<Student>) request.getAttribute("students");
%>


<body>
    <header class="header">
        <div class="navtext-container">
            <p class="navtext">カルテ入力</p>
        </div>
        <%@include file="../common/G_header.jsp" %>
    </header>
<main>

<form action="../guardian/StudentRecordCreateExecute.action" method="post">
<div class="aa">
    <p>生年月日を入力してください</p>
    <input type="date" name="birthdate" required>
</div>

<div class="aa">
    <p>食物アレルギーはありますか？</p>
    <label><input type="radio" name="arerugi-" value="aru" required>ある</label>
    <label><input type="radio" name="arerugi-" value="nai" required>ない</label>
</div>

<div id="content1" style="display: none;">
    <p>アレルギーの診断がされているものにチェックを入れてください</p>

<ul class="flex_table">
    <li>
      <ul>
        <li><label><input type="checkbox" value="0">エビ</label></li>
        <li><label><input type="checkbox" value="1">卵</label></li>
      </ul>
    </li>
    <li>
      <ul>
        <li><label><input type="checkbox" value="2">カニ</label></li>
        <li><label><input type="checkbox" value="3">乳</label></li>
      </ul>
    </li>
    <li>
      <ul>
        <li><label><input type="checkbox" value="4">小麦</label></li>
        <li><label><input type="checkbox" value="5">落花生</label></li>
      </ul>
    </li>
    <li>
      <ul id="under">
        <li><label><input type="checkbox" value="6">そば</label></li>
      </ul>
    </li>
</ul>
<ul class="flex_table2">
    <li>
        <ul>
          <li><label><input type="checkbox" value="7">牛肉</label></li>
          <li><label><input type="checkbox" value="8">いか</label></li>
          <li><label><input type="checkbox" value="9">あわび</label></li>
          <li><label><input type="checkbox" value="10">カシューナッツ</label></li>
          <li><label><input type="checkbox" value="11">りんご</label></li>
          <li><label><input type="checkbox" value="12">オレンジ</label></li>
          <li><label><input type="checkbox" value="13">ゴマ</label></li>
        </ul>
    </li>
    <li>
        <ul>
          <li><label><input type="checkbox" value="14">豚肉</label></li>
          <li><label><input type="checkbox" value="15">さば</label></li>
          <li><label><input type="checkbox" value="16">いくら</label></li>
          <li><label><input type="checkbox" value="17">くるみ</label></li>
          <li><label><input type="checkbox" value="18">キウイ</label></li>
          <li><label><input type="checkbox" value="19">まつたけ</label></li>
          <li><label><input type="checkbox" value="20">大豆</label></li>
        </ul>
    </li>    <li>
        <ul>
          <li><label><input type="checkbox" value="21">鶏肉</label></li>
          <li><label><input type="checkbox" value="22">さけ</label></li>
          <li><label><input type="checkbox" value="23">アーモンド</label></li>
          <li><label><input type="checkbox" value="24">もも</label></li>
          <li><label><input type="checkbox" value="25">バナナ</label></li>
          <li><label><input type="checkbox" value="26">やまいも</label></li>
          <li><label><input type="checkbox" value="27">ゼラチン</label></li>
        </ul>
    </li>
</ul>
</div>
<div class="button">
    <button type="submit">登録</button>
</div>

</form>
</main>
</body>

<script>

    document.addEventListener('DOMContentLoaded', function() {
        const radioButtons = document.querySelectorAll('input[name="arerugi-"]');
        const content1 = document.getElementById('content1');


        radioButtons.forEach(radio => {
            radio.addEventListener('change', function() {
                if (this.value === 'aru') {
                    content1.style.display = 'block';
                } else if (this.value !== 'aru') {
                    content1.style.display = 'none';
                }
            });
        });
    });


</script>

<style>
body{
  overflow-y: scroll;
}


main{
  margin-top: 52px;
  width: 100%;
}

.aa{
  padding: 5px;
  border-bottom: 1px solid #757575;
}

#content1 p{
  margin-left: 5px;
}


.flex_table{
  padding: 0;
  display: flex;
  border: 1px solid #999;
  text-align: left;
}
.flex_table li{
  list-style: none;
  white-space: nowrap;
}
.flex_table > li{
  flex-basis: calc( 100% / 4 ); /* 3列なので( 100% / 3 )となります */
}
.flex_table > li + li{
  border-left: 1px solid #999;
}

.flex_table li ul{
  padding: 0;
}

.flex_table li ul li + li{
  border-top: 1px solid #999;
}

.flex_table #under{
  border-bottom: 1px solid #999;
}


.flex_table2{
  padding: 0;
  display: flex;
  border: 1px solid #999;
  text-align: left;
}
.flex_table2 li{
  list-style: none;
  white-space: nowrap;
}
.flex_table2 > li{
  flex-basis: calc( 100% / 3 ); /* 3列なので( 100% / 3 )となります */
}
.flex_table2 > li + li{
  border-left: 1px solid #999;
}
.flex_table2 li ul{
  padding: 0;
}
.flex_table2 li ul li + li{
  border-top: 1px solid #999;
}


.button{
  text-align: center;
}
button{
  width: 60px;
  padding: 3px;
  margin-top: 10px;
  margin-bottom: 10px;
}

</style>
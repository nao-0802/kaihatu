<%@page contentType="text/html; charset=UTF-8" %>
<!-- %@include file="../T_header.html" % -->
<%@page import="bean.StudentRecord, java.util.List"%>
list=<% List<StudentRecord> list=(List<StudentRecord>)req.getAttribute("list"); %>

<style>
summary {
	display: block;
  }

  summary::-webkit-details-marker {
	display: none;
  }

#b {
	background-color: aquamarine;
	width: 500px;
}

#c[open] {
	background-color: aqua;
	width: 500px;
}

</style>

<script>
    function autoCheck(textValue, radioId) {
        document.getElementById(radioId).checked = textValue.length > 0;
    }
</script>

<details>
    <a href="tel:${hogosyatel}">${hogosyatel}</a>
    <p>arerugi-</p>
    <p>jibyou(hossa),taiou</p>
</details>

<form action="ContactBook.action">
<button>連絡帳</button>
</form>

<details name="life" id=a>
    <summary>睡眠</summary>
    <form action="">
        <div>
            <label><input type="radio" name="sleep">寝た</label><br>
            <label><input type="radio" name="sleep">起きた</label><br>
            <button>記録</button>
        </div>
    </form>
</details>

<details name="life" id=b>
    <summary>食事</summary>
    <form action="">
        <div>
            <label><input type="radio" name="meal">全量</label><br>
            <label><input type="radio" name="meal">半量</label><br>
            <label><input type="radio" name="meal">少量</label><br>
            <button>記録</button>
        </div>
    </form>
</details>

<details name="life" id=c>
    <summary>排泄</summary>
    <form action="">
        <div>
            <label><input type="radio" name="excretion">かたい</label><br>
            <label><input type="radio" name="excretion">やわらかい</label><br>
            <label>
                <input type="radio" name="option" id="radio1">その他
            </label>
            <input type="text" oninput="autoCheck(this.value, 'radio1')"><br>
        </div>
        <button>記録</button>
    </form>
</details>

<!--  %@include file="../footer.html" % -->
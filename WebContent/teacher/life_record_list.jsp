<%@ page import="bean.MealRecord, bean.SleepRecord, bean.ExcretionRecord" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>生活記録</title>
</head>
<body>
    <h1>生活記録</h1>

    <!-- 食事記録 -->
    <h2>食事記録</h2>
    <table border="1">
        <tr>
            <th>日付</th>
            <th>時間</th>
            <th>詳細</th>
        </tr>
        <%
            List<MealRecord> mealRecords = (List<MealRecord>) request.getAttribute("mealRecords");
            if (mealRecords != null && !mealRecords.isEmpty()) {
                for (MealRecord record : mealRecords) {
                    String mealDetail;
                    switch (record.getMealAmount()) {
                        case 0:
                            mealDetail = "全量";
                            break;
                        case 1:
                            mealDetail = "半量";
                            break;
                        case 2:
                            mealDetail = "少量";
                            break;
                        default:
                            mealDetail = "不明";
                            break;
                    }
        %>
        <tr>
            <td><%= record.getDay() %></td>
            <td><%= record.getTime() %></td>
            <td><%= mealDetail %></td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="3">食事記録がありません。</td>
        </tr>
        <%
            }
        %>
    </table>

    <!-- 睡眠記録 -->
    <h2>睡眠記録</h2>
    <table border="1">
        <tr>
            <th>日付</th>
            <th>時間</th>
            <th>詳細</th>
        </tr>
        <%
            List<SleepRecord> sleepRecords = (List<SleepRecord>) request.getAttribute("sleepRecords");
            if (sleepRecords != null && !sleepRecords.isEmpty()) {
                for (SleepRecord record : sleepRecords) {
                    String sleepDetail = record.getSleep() == 0 ? "起床" : "就寝";
        %>
        <tr>
            <td><%= record.getDay() %></td>
            <td><%= record.getTime() %></td>
            <td><%= sleepDetail %></td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="3">睡眠記録がありません。</td>
        </tr>
        <%
            }
        %>
    </table>

    <!-- 排泄記録 -->
    <h2>排泄記録</h2>
    <table border="1">
        <tr>
            <th>日付</th>
            <th>時間</th>
            <th>詳細</th>
        </tr>
        <%
            List<ExcretionRecord> excretionRecords = (List<ExcretionRecord>) request.getAttribute("excretionRecords");
            if (excretionRecords != null && !excretionRecords.isEmpty()) {
                for (ExcretionRecord record : excretionRecords) {
                    String typeDetail = record.getType() == 0 ? "かたい" : "やわらかい";
                    String excretionDetail = record.getExcretionDetail() != null && !record.getExcretionDetail().isEmpty()
                            ? record.getExcretionDetail()
                            : "なし";
        %>
        <tr>
            <td><%= record.getDay() %></td>
            <td><%= record.getTime() %></td>
            <td><%= typeDetail %> - <%= excretionDetail %></td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="3">排泄記録がありません。</td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>

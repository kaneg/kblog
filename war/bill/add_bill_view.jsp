<%@ page import="gz.aws.bill.BillTypeManager" %>
<%@ page import="gz.aws.bill.WebUtil" %>
<%@ page import="gz.aws.bill.entity.BillType" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/_taglibs.jsp" %>
<html>
<head><title>Simple jsp page</title>
    <link type="text/css" href="/css/le-frog/jquery-ui-1.8.14.custom.css" rel="stylesheet"/>
    <%@include file="_js.jsp" %>
</head>
<script type="text/javascript">
    $(function() {
        $("#datepicker").datepicker({
            dateFormat:'yy-mm-dd'
        }).datepicker('setDate', new Date());
    });

    function createNewBillType(options) {
        if(options[options.selectedIndex].value!=-1){
            return false;
        }

        var prompt = window.prompt('请输入分类：','');
        if (prompt) {
            var billType = prompt;
            var callback = function(billId) {
                if (billId && $.trim(billId)) {
                    billId = $.trim(billId);
                    var o = new Option(billType, billId);
                    var select = $("#billType").get(0);
                    select.options.add(o);
                    select.selectedIndex = select.options.length - 1;
                }
            }
            $.post("add_bill_type.action", {billType:billType,isIn:${param.isIn}}, callback)
        }else{
            options.selectedIndex=0;
            return false;
        }
    }
</script>
<body>
<%
    BillTypeManager billTypeManager = WebUtil.getBillTypeManager();
    List<BillType> billTypes = billTypeManager.listAll(Boolean.valueOf(request.getParameter("isIn")));
    pageContext.setAttribute("billTypes", billTypes);
%>
<form action="add_bill.action" method="post">
    <input type="hidden" name="isIn" value="${param.isIn}">
    <table>
        <tr>
            <td>时间：</td>
            <td><input type="text" name="issueDate" id="datepicker" readonly="true"></td>
        </tr>
        <tr>
            <td>金额：</td>
            <td><input type="text" name="amount" value="111"></td>
        </tr>
        <tr>
            <td>地点：</td>
            <td><input type="text" name="location"></td>
        </tr>
        <tr>
            <td>描述：</td>
            <td><input type="text" name="desc"></td>
        </tr>
        <tr>
            <td>类型：</td>
            <td>
                <select name="billType" id="billType" onchange="return createNewBillType(this)">
                    <option value="0" selected="true">未分类</option>
                    <option value="-1">新建分类</option>
                    <c:forEach items="${billTypes}" var="billType">
                        <option value="${billType.id}">${billType.value}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td><input type="submit" value="Add"></td>
            <td>
                <button onclick="top.TINY.box.hide();return false">Cancel</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/_taglibs.jsp" %>
<%--<s:include value="admin/list_mymood.action"/>--%>
<style type="text/css">
    .mood_list {
        font-size: small;
        line-height: 125%;
        margin: 20px 0 20px 20px;
        padding-top: 10px;
        border: solid 2px #6688EE;
    }
</style>
<script type="text/javascript">
    function confirmDelete()
    {
        return  window.confirm("Do you really want to delete it?")
    }
</script>
<c:forEach var="mood" items="${moods}">
    <div class="mood_list">
        <f:formatDate value="${mood.createDate}" pattern="yyyy-MM-dd hh:mm"/>
                <br> ${mood.mood}
        <a href="admin/delete_mood.action?mId=${mood.id}" onclick="return confirmDelete()">Delete</a>
    </div>
</c:forEach>
<%
   String contextPath = request.getContextPath();
   response.setContentType("text/javascript");
%>

var contextPath = "<%=contextPath%>";

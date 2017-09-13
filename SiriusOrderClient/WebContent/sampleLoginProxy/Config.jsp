<%@page contentType="text/html;charset=UTF-8"%>
<jsp:useBean id="sampleLoginProxyid" scope="session" class="com.sirius.loginws.login.wsdl.LoginProxy"/>
<%
String submitAction = request.getParameter("submitAction");
if (session.getAttribute("__default_endpoint__") == null) {
    session.setAttribute("__default_endpoint__", sampleLoginProxyid._getDescriptor().getEndpoint());
}
if (submitAction != null && submitAction.equals("Update")) {
    sampleLoginProxyid._getDescriptor().setEndpoint(request.getParameter("endpoint"));
}
else if (submitAction != null && submitAction.equals("Reset")) {
    sampleLoginProxyid._getDescriptor().setEndpoint((String) session.getAttribute("__default_endpoint__"));
}
String endpoint = sampleLoginProxyid._getDescriptor().getEndpoint();

boolean hasAsync = com.ibm.ccl.ws.jaxws.gstc.util.AsyncUtils.hasAsync(sampleLoginProxyid.getClass());

boolean asyncChanging =
    request.getParameter("asyncChanging") != null &&
    request.getParameter("asyncChanging").toString().equals("true");
if (asyncChanging)
    session.setAttribute("__async__", request.getParameter("async"));
String async = session.getAttribute("__async__") == null ? "" : "checked";

boolean bypassChanging =
    request.getParameter("bypassChanging") != null &&
    request.getParameter("bypassChanging").toString().equals("true");
if (bypassChanging)
    session.setAttribute("__bypass__", request.getParameter("bypass"));
String bypass = session.getAttribute("__bypass__") == null ? "" : "checked";

%>
<HTML>
<HEAD>
<TITLE>Quality of Service</TITLE>
<LINK rel="stylesheet" type="text/css" href="TestClient.css"/>
<script language="JavaScript">
function submitForm(action) {
var form = document.getElementById("configForm");
if (action == "Update" || action == "Reset")
form.submitAction.value = action;
else
eval("form." + action + "Changing.value = true;");
form.submit();
}
function reloadInputs() {
window.parent.frames["inputs"].location.reload(true);
}
</script>
</HEAD>
<BODY>
<TABLE class="heading" width="100%">
<TR><TD>Quality of Service</TD></TR>
</TABLE>
<FORM id="configForm" method="post" action="Config.jsp">
<P>
Endpoint:<br/>
<INPUT type="text" name="endpoint" SIZE="50" value="<%= endpoint %>"/>
<INPUT type="hidden" name="submitAction"/>
<INPUT type="button" value="Update" onclick="javascript:submitForm('Update');"/>
<INPUT type="button" value="Reset" onclick="javascript:submitForm('Reset');"/>
<br/>
<br/>
<% if (hasAsync) { %>
<INPUT type="hidden" name="asyncChanging" value="false"/>
<INPUT type="checkbox" name="async" id="async" <%= async %> onclick="javascript:submitForm('async');"/>
<LABEL for="async">Enable asynchronous invocation</LABEL>
<br/>
<% } %>
<INPUT type="hidden" name="bypassChanging" value="false"/>
<INPUT type="checkbox" name="bypass" id="bypass" <%= bypass %> onclick="javascript:submitForm('bypass');"/>
<LABEL for="bypass">Bypass JAXB and use raw SOAP messages</LABEL>
</P>
</FORM>
<% if (bypassChanging) { %>
<script language="JavaScript">reloadInputs();</script>
<% } %>
</BODY>
</HTML>

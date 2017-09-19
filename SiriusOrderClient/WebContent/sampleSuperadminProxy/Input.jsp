<%@page contentType="text/html;charset=UTF-8"%>
<%!
private static final String ENVELOPE_TEMPLATE =
    "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
    "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
    "<soapenv:Header>\n" +
    "</soapenv:Header>\n" +
    "<soapenv:Body>\n\n" +
    "</soapenv:Body>\n" +
    "</soapenv:Envelope>";
%>
<%
boolean bypass = session.getAttribute("__bypass__") == null ? false : true;
request.setCharacterEncoding("UTF-8");
String methodName = request.getParameter("methodName");
if (methodName == null || methodName.trim().length() == 0) methodName = "Inputs";
%>
<HTML>
<HEAD>
<TITLE>Inputs</TITLE>
<LINK rel="stylesheet" type="text/css" href="TestClient.css"/>
<script language="JavaScript">
function saveFullPath(fileInput, hiddenInput) {
    hiddenInput.value = fileInput.value;
}

function noBinding() {
    var doc  = window.parent.frames["config"].document;
    var form = doc.getElementById("configForm");
    form.bypass.click();
}

function triggerSoapAction() {
    var chk = document.getElementById("__use_soapaction__");
    var txt = document.getElementById("__soapaction__");
    txt.disabled = !chk.checked;
}
</script>
</HEAD>
<BODY>
<TABLE class="heading" width="100%">
<TR><TD><%= org.eclipse.jst.ws.util.JspUtils.markup(methodName) %></TD></TR>
</TABLE>
<BR/>
<%
String method = request.getParameter("method");
int methodID = 0;
if (method == null) methodID = -1;

boolean valid = true;

if(methodID != -1) methodID = Integer.parseInt(method);
switch (methodID){ 
case 2:
valid = false;
%>
<FORM METHOD="POST" ACTION="Result.jsp" TARGET="result">
<INPUT TYPE="HIDDEN" NAME="method" VALUE="<%=method%>">
[This method takes no parameters]
<BR/>
<BR/>
<INPUT TYPE="SUBMIT" VALUE="Invoke">
<INPUT TYPE="RESET" VALUE="Clear">
</FORM>
<%
break;
case 15:
valid = false;
%>
<FORM METHOD="POST" ACTION="Result.jsp" TARGET="result">
<INPUT TYPE="HIDDEN" NAME="method" VALUE="<%=method%>">
<% if (bypass) { %>
<INPUT TYPE="checkbox" ID="__use_soapaction__" NAME="__use_soapaction__" onclick="triggerSoapAction();"/>
<label for="__use_soapaction__">Use SOAPAction HTTP header</label>
<BR/>
<INPUT TYPE="text" ID="__soapaction__" NAME="__soapaction__" SIZE="70" DISABLED/>
<BR/>
<BR/>
<TEXTAREA NAME="__rawxml__" ROWS="8" COLs="45"><%= ENVELOPE_TEMPLATE %></TEXTAREA>
<BR/>
<% } else { %>
<TABLE CLASS="tableform">
<TR><TD ALIGN="LEFT" CLASS="headingcol">null?</TD></TR>
<TR>
<TD ALIGN="left" CLASS="nullcol"><input type="checkbox" name="budget18null" value="budget18null"></TD>
<TD COLSPAN="1" ALIGN="LEFT" CLASS="headingcol">budget:</TD>
<TD ALIGN="left"><INPUT value="0" TYPE="TEXT" NAME="budget18" SIZE=20></TD>
</TR>
</TABLE>
<TABLE CLASS="tableform">
<TR>
<TD ALIGN="left" CLASS="nullcol"></TD>
<TD COLSPAN="1" ALIGN="LEFT" CLASS="headingcol">locationId:</TD>
<TD ALIGN="left"><INPUT value="0" TYPE="TEXT" NAME="locationId20" SIZE=20></TD>
</TR>
</TABLE>
<% } %>
<BR/>
<INPUT TYPE="SUBMIT" VALUE="Invoke">
<INPUT TYPE="RESET" VALUE="Clear">
</FORM>
<%
break;
case 22:
valid = false;
%>
<FORM METHOD="POST" ACTION="Result.jsp" TARGET="result">
<INPUT TYPE="HIDDEN" NAME="method" VALUE="<%=method%>">
<% if (bypass) { %>
<INPUT TYPE="checkbox" ID="__use_soapaction__" NAME="__use_soapaction__" onclick="triggerSoapAction();"/>
<label for="__use_soapaction__">Use SOAPAction HTTP header</label>
<BR/>
<INPUT TYPE="text" ID="__soapaction__" NAME="__soapaction__" SIZE="70" DISABLED/>
<BR/>
<BR/>
<TEXTAREA NAME="__rawxml__" ROWS="8" COLs="45"><%= ENVELOPE_TEMPLATE %></TEXTAREA>
<BR/>
<% } else { %>
<TABLE CLASS="tableform">
<TR>
<TD ALIGN="left" CLASS="nullcol"></TD>
<TD COLSPAN="1" ALIGN="LEFT" CLASS="headingcol">locationId:</TD>
<TD ALIGN="left"><INPUT value="0" TYPE="TEXT" NAME="locationId25" SIZE=20></TD>
</TR>
</TABLE>
<% } %>
<BR/>
<INPUT TYPE="SUBMIT" VALUE="Invoke">
<INPUT TYPE="RESET" VALUE="Clear">
</FORM>
<%
break;
case 27:
valid = false;
%>
<FORM METHOD="POST" ACTION="Result.jsp" TARGET="result">
<INPUT TYPE="HIDDEN" NAME="method" VALUE="<%=method%>">
<% if (bypass) { %>
<INPUT TYPE="checkbox" ID="__use_soapaction__" NAME="__use_soapaction__" onclick="triggerSoapAction();"/>
<label for="__use_soapaction__">Use SOAPAction HTTP header</label>
<BR/>
<INPUT TYPE="text" ID="__soapaction__" NAME="__soapaction__" SIZE="70" DISABLED/>
<BR/>
<BR/>
<TEXTAREA NAME="__rawxml__" ROWS="8" COLs="45"><%= ENVELOPE_TEMPLATE %></TEXTAREA>
<BR/>
<% } else { %>
<TABLE CLASS="tableform">
<TR><TD ALIGN="LEFT" CLASS="headingcol">null?</TD></TR>
<TR>
<TD ALIGN="left" CLASS="nullcol"><input type="checkbox" name="city30null" value="city30null"></TD>
<TD COLSPAN="1" ALIGN="LEFT" CLASS="headingcol">city:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="city30" SIZE=20></TD>
</TR>
</TABLE>
<TABLE CLASS="tableform">
<TR><TD ALIGN="LEFT" CLASS="headingcol">null?</TD></TR>
<TR>
<TD ALIGN="left" CLASS="nullcol"><input type="checkbox" name="state32null" value="state32null"></TD>
<TD COLSPAN="1" ALIGN="LEFT" CLASS="headingcol">state:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="state32" SIZE=20></TD>
</TR>
</TABLE>
<TABLE CLASS="tableform">
<TR>
<TD ALIGN="left" CLASS="nullcol"></TD>
<TD COLSPAN="1" ALIGN="LEFT" CLASS="headingcol">creatorId:</TD>
<TD ALIGN="left"><INPUT value="0" TYPE="TEXT" NAME="creatorId34" SIZE=20></TD>
</TR>
</TABLE>
<% } %>
<BR/>
<INPUT TYPE="SUBMIT" VALUE="Invoke">
<INPUT TYPE="RESET" VALUE="Clear">
</FORM>
<%
break;
case 36:
valid = false;
%>
<FORM METHOD="POST" ACTION="Result.jsp" TARGET="result">
<INPUT TYPE="HIDDEN" NAME="method" VALUE="<%=method%>">
<% if (bypass) { %>
<INPUT TYPE="checkbox" ID="__use_soapaction__" NAME="__use_soapaction__" onclick="triggerSoapAction();"/>
<label for="__use_soapaction__">Use SOAPAction HTTP header</label>
<BR/>
<INPUT TYPE="text" ID="__soapaction__" NAME="__soapaction__" SIZE="70" DISABLED/>
<BR/>
<BR/>
<TEXTAREA NAME="__rawxml__" ROWS="8" COLs="45"><%= ENVELOPE_TEMPLATE %></TEXTAREA>
<BR/>
<% } else { %>
<TABLE CLASS="tableform">
<TR>
<TD ALIGN="left" CLASS="nullcol"></TD>
<TD COLSPAN="1" ALIGN="LEFT" CLASS="headingcol">locationId:</TD>
<TD ALIGN="left"><INPUT value="0" TYPE="TEXT" NAME="locationId39" SIZE=20></TD>
</TR>
</TABLE>
<TABLE CLASS="tableform">
<TR>
<TD ALIGN="left" CLASS="nullcol"></TD>
<TD COLSPAN="1" ALIGN="LEFT" CLASS="headingcol">adminId:</TD>
<TD ALIGN="left"><INPUT value="0" TYPE="TEXT" NAME="adminId41" SIZE=20></TD>
</TR>
</TABLE>
<TABLE CLASS="tableform">
<TR>
<TD ALIGN="left" CLASS="nullcol"></TD>
<TD COLSPAN="1" ALIGN="LEFT" CLASS="headingcol">updaterId:</TD>
<TD ALIGN="left"><INPUT value="0" TYPE="TEXT" NAME="updaterId43" SIZE=20></TD>
</TR>
</TABLE>
<% } %>
<BR/>
<INPUT TYPE="SUBMIT" VALUE="Invoke">
<INPUT TYPE="RESET" VALUE="Clear">
</FORM>
<%
break;
case 45:
valid = false;
%>
<FORM METHOD="POST" ACTION="Result.jsp" TARGET="result">
<INPUT TYPE="HIDDEN" NAME="method" VALUE="<%=method%>">
[This method takes no parameters]
<BR/>
<BR/>
<INPUT TYPE="SUBMIT" VALUE="Invoke">
<INPUT TYPE="RESET" VALUE="Clear">
</FORM>
<%
break;
}
if (valid) {
%>
Select a method to test
<%
}
%>

</BODY>
</HTML>

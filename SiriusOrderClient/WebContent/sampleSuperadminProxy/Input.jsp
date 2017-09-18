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
case 19:
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
<TD ALIGN="left" CLASS="nullcol"><input type="checkbox" name="parameters24null" value="parameters24null"></TD>
<TD COLSPAN="2" ALIGN="LEFT" CLASS="headingcol">parameters:</TD>
<TR>
<TD ALIGN="left" CLASS="nullcol"><input type="checkbox" name="budget26null" value="budget26null"></TD>
<TD CLASS="spacercol"></TD>
<TD COLSPAN="1" ALIGN="LEFT" CLASS="headingcol">budget:</TD>
<TD ALIGN="left"><INPUT value="0" TYPE="TEXT" NAME="budget26" SIZE=20></TD>
</TR>
<TR>
<TD ALIGN="left" CLASS="nullcol"></TD>
<TD CLASS="spacercol"></TD>
<TD COLSPAN="1" ALIGN="LEFT" CLASS="headingcol">locationId:</TD>
<TD ALIGN="left"><INPUT value="0" TYPE="TEXT" NAME="locationId28" SIZE=20></TD>
</TR>
</TABLE>
<% } %>
<BR/>
<INPUT TYPE="SUBMIT" VALUE="Invoke">
<INPUT TYPE="RESET" VALUE="Clear">
</FORM>
<%
break;
case 30:
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
<TD ALIGN="left" CLASS="nullcol"><input type="checkbox" name="parameters35null" value="parameters35null"></TD>
<TD COLSPAN="2" ALIGN="LEFT" CLASS="headingcol">parameters:</TD>
<TR>
<TD ALIGN="left" CLASS="nullcol"></TD>
<TD CLASS="spacercol"></TD>
<TD COLSPAN="1" ALIGN="LEFT" CLASS="headingcol">locationId:</TD>
<TD ALIGN="left"><INPUT value="0" TYPE="TEXT" NAME="locationId37" SIZE=20></TD>
</TR>
</TABLE>
<% } %>
<BR/>
<INPUT TYPE="SUBMIT" VALUE="Invoke">
<INPUT TYPE="RESET" VALUE="Clear">
</FORM>
<%
break;
case 39:
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
<TD ALIGN="left" CLASS="nullcol"><input type="checkbox" name="parameters44null" value="parameters44null"></TD>
<TD COLSPAN="2" ALIGN="LEFT" CLASS="headingcol">parameters:</TD>
<TR>
<TD ALIGN="left" CLASS="nullcol"><input type="checkbox" name="city46null" value="city46null"></TD>
<TD CLASS="spacercol"></TD>
<TD COLSPAN="1" ALIGN="LEFT" CLASS="headingcol">city:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="city46" SIZE=20></TD>
</TR>
<TR>
<TD ALIGN="left" CLASS="nullcol"><input type="checkbox" name="state48null" value="state48null"></TD>
<TD CLASS="spacercol"></TD>
<TD COLSPAN="1" ALIGN="LEFT" CLASS="headingcol">state:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="state48" SIZE=20></TD>
</TR>
<TR>
<TD ALIGN="left" CLASS="nullcol"></TD>
<TD CLASS="spacercol"></TD>
<TD COLSPAN="1" ALIGN="LEFT" CLASS="headingcol">createrId:</TD>
<TD ALIGN="left"><INPUT value="0" TYPE="TEXT" NAME="createrId50" SIZE=20></TD>
</TR>
</TABLE>
<% } %>
<BR/>
<INPUT TYPE="SUBMIT" VALUE="Invoke">
<INPUT TYPE="RESET" VALUE="Clear">
</FORM>
<%
break;
case 52:
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
<TD ALIGN="left" CLASS="nullcol"><input type="checkbox" name="parameters57null" value="parameters57null"></TD>
<TD COLSPAN="2" ALIGN="LEFT" CLASS="headingcol">parameters:</TD>
<TR>
<TD ALIGN="left" CLASS="nullcol"></TD>
<TD CLASS="spacercol"></TD>
<TD COLSPAN="1" ALIGN="LEFT" CLASS="headingcol">locationId:</TD>
<TD ALIGN="left"><INPUT value="0" TYPE="TEXT" NAME="locationId59" SIZE=20></TD>
</TR>
<TR>
<TD ALIGN="left" CLASS="nullcol"></TD>
<TD CLASS="spacercol"></TD>
<TD COLSPAN="1" ALIGN="LEFT" CLASS="headingcol">adminId:</TD>
<TD ALIGN="left"><INPUT value="0" TYPE="TEXT" NAME="adminId61" SIZE=20></TD>
</TR>
<TR>
<TD ALIGN="left" CLASS="nullcol"></TD>
<TD CLASS="spacercol"></TD>
<TD COLSPAN="1" ALIGN="LEFT" CLASS="headingcol">updaterId:</TD>
<TD ALIGN="left"><INPUT value="0" TYPE="TEXT" NAME="updaterId63" SIZE=20></TD>
</TR>
</TABLE>
<% } %>
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

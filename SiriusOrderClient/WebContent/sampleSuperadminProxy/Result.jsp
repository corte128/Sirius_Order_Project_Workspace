<%@page contentType="text/html;charset=UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
com.ibm.ccl.ws.jaxws.gstc.util.OutputUtils.init(session);

boolean async       = session.getAttribute("__async__") == null ? false : true;
String methodKey    = request.getParameter("key");
String resultSuffix = methodKey != null && methodKey.length() > 0 ? " - " + methodKey : "";
%>
<HTML>
<HEAD>
<TITLE>Result</TITLE>
<LINK rel="stylesheet" type="text/css" href="TestClient.css"/>
<script language="JavaScript">

function reloadMethods() {
    window.parent.frames["methods"].location.reload(true);
}
</script>
</HEAD>
<BODY>
<TABLE class="heading" width="100%">
<TR><TD>Result<%= org.eclipse.jst.ws.util.JspUtils.markup(resultSuffix) %></TD></TR>
</TABLE>
<P>
<jsp:useBean id="sampleSuperadminProxyid" scope="session" class="com.sirius.service.superadmin.superadmin.wsdl.SuperadminProxy" />

<%
String method = request.getParameter("method");
int methodID = 0;
if (method == null) methodID = -1;

if(methodID != -1) methodID = Integer.parseInt(method);
boolean gotMethod = false;

boolean isDone = true;
try {
    String sourceInTemp = request.getParameter("__rawxml__");
        javax.xml.transform.Source sourceIn  = sourceInTemp != null ?
            new javax.xml.transform.stream.StreamSource(new java.io.ByteArrayInputStream(sourceInTemp.getBytes())) : null;
    javax.xml.transform.Source sourceOut = null;

    boolean bypass = (sourceIn != null);

switch (methodID){ 
case 2:
    gotMethod = true;
    com.sirius.service.superadmin.superadmin.wsdl.SuperadminProxy.Descriptor returnp3mtemp = null;
    if (methodKey != null) {
        javax.xml.ws.Response resp = com.ibm.ccl.ws.jaxws.gstc.util.AsyncUtils.getResponse(session, methodKey);
        if (resp != null) {
            isDone = resp.isDone();
            if (!isDone)
                break;
            if (resp.get() != null) {
                Object o = resp.get();
                if (o instanceof javax.xml.transform.Source)
                    sourceOut = (javax.xml.transform.Source) o;
                else if (o instanceof com.sirius.service.superadmin.superadmin.wsdl.SuperadminProxy.Descriptor)
                    returnp3mtemp = (com.sirius.service.superadmin.superadmin.wsdl.SuperadminProxy.Descriptor) o;
            }
        }
    }
    else if (bypass) {
        javax.xml.ws.Dispatch dispatch = sampleSuperadminProxyid._getDescriptor().getDispatch();

        if (request.getParameter("__use_soapaction__") != null)
            com.ibm.ccl.ws.jaxws.gstc.util.DispatchUtils.useSoapAction(dispatch, request.getParameter("__soapaction__"));
        else
            com.ibm.ccl.ws.jaxws.gstc.util.DispatchUtils.clearSoapAction(dispatch);

        if (!async)
            sourceOut = (javax.xml.transform.Source) dispatch.invoke(sourceIn);
        else {
            // async code omitted
            break;
        }
    } else {
        if (!async) {
        try {
            returnp3mtemp = sampleSuperadminProxyid._getDescriptor();
            }catch(Exception exc){
                %>
                Exception: <%= org.eclipse.jst.ws.util.JspUtils.markup(exc.toString()) %>
                Message: <%= org.eclipse.jst.ws.util.JspUtils.markup(exc.getMessage()) %>
                <%
break;
            }
        }
        else {
            // async code omitted
            break;
        }
    }
if (sourceOut != null) {
%>
    <TEXTAREA ROWS="8" COLs="45"><%= org.eclipse.jst.ws.util.JspUtils.markup(com.ibm.ccl.ws.jaxws.gstc.util.SourceUtils.transform(sourceOut)) %></TEXTAREA>
<%
}
else {
if (returnp3mtemp == null) {
%>
    null
<%
} else {
    %>
    <TABLE CLASS="tableform">
<TR>
<TD COLSPAN="4" VALIGN="TOP" ALIGN="LEFT" CLASS="headingcol">returnp:</TD>
<TR>
<TD CLASS="spacercol">&nbsp;</TD>
<TD COLSPAN="3" VALIGN="TOP" ALIGN="LEFT" CLASS="headingcol">proxy:</TD>
<TR>
<TD CLASS="spacercol">&nbsp;</TD>
<TD COLSPAN="3" VALIGN="TOP" ALIGN="LEFT" CLASS="headingcol">dispatch:</TD>
<TD>
<%
if(returnp3mtemp != null){
javax.xml.ws.Dispatch typedispatch13 = returnp3mtemp.getDispatch();
if(typedispatch13 != null){
        if(typedispatch13!= null){
        String tempdispatch13 = typedispatch13.toString();
        %>
        <%=tempdispatch13%>
        <%
        }}
else{
        %>
        <%= typedispatch13%>
        <%
}
}
%>
</TD>
<TR>
<TD CLASS="spacercol">&nbsp;</TD>
<TD COLSPAN="3" VALIGN="TOP" ALIGN="LEFT" CLASS="headingcol">endpoint:</TD>
<TD>
<%
if(returnp3mtemp != null){
java.lang.String typeendpoint15 = returnp3mtemp.getEndpoint();
if(typeendpoint15 != null){
        String tempResultendpoint15 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typeendpoint15));
        %>
        <%= tempResultendpoint15 %>
        <%
}
else{
        %>
        <%= typeendpoint15%>
        <%
}
}
%>
</TD>
    </TABLE>
    <%
}
%>
<HR/><BR/>
<%
}
break;
case 19:
    gotMethod = true;
    com.sirius.service.superadmin.superadmin.wsdl.SetBudgetByLocationResponse returnp20mtemp = null;
    if (methodKey != null) {
        javax.xml.ws.Response resp = com.ibm.ccl.ws.jaxws.gstc.util.AsyncUtils.getResponse(session, methodKey);
        if (resp != null) {
            isDone = resp.isDone();
            if (!isDone)
                break;
            if (resp.get() != null) {
                Object o = resp.get();
                if (o instanceof javax.xml.transform.Source)
                    sourceOut = (javax.xml.transform.Source) o;
                else if (o instanceof com.sirius.service.superadmin.superadmin.wsdl.SetBudgetByLocationResponse)
                    returnp20mtemp = (com.sirius.service.superadmin.superadmin.wsdl.SetBudgetByLocationResponse) o;
            }
        }
    }
    else if (bypass) {
        javax.xml.ws.Dispatch dispatch = sampleSuperadminProxyid._getDescriptor().getDispatch();

        if (request.getParameter("__use_soapaction__") != null)
            com.ibm.ccl.ws.jaxws.gstc.util.DispatchUtils.useSoapAction(dispatch, request.getParameter("__soapaction__"));
        else
            com.ibm.ccl.ws.jaxws.gstc.util.DispatchUtils.clearSoapAction(dispatch);

        if (!async)
            sourceOut = (javax.xml.transform.Source) dispatch.invoke(sourceIn);
        else {
            // async code omitted
            break;
        }
    } else {
        %>
        <jsp:useBean id="com1sirius1service1superadmin1superadmin1wsdl1SetBudgetByLocation_0id" scope="session" class="com.sirius.service.superadmin.superadmin.wsdl.SetBudgetByLocation" />
        <%
        String parameters24null = request.getParameter("parameters24null");
        if (parameters24null != null)
            com1sirius1service1superadmin1superadmin1wsdl1SetBudgetByLocation_0id = null;
        else {
        String budget_1id=  request.getParameter("budget26");
        String budget26null = request.getParameter("budget26null");
        java.math.BigDecimal budget_1idTemp;
        if (budget26null != null)
            budget_1idTemp = null;
        else {
         budget_1idTemp  = new java.math.BigDecimal(budget_1id);
        }
        String locationId_3id=  request.getParameter("locationId28");
        int locationId_3idTemp  = Integer.parseInt(locationId_3id);
        com1sirius1service1superadmin1superadmin1wsdl1SetBudgetByLocation_0id.setBudget(budget_1idTemp);
        com1sirius1service1superadmin1superadmin1wsdl1SetBudgetByLocation_0id.setLocationId(locationId_3idTemp);
        }

        if (!async) {
        try {
            returnp20mtemp = sampleSuperadminProxyid.setBudgetByLocation(com1sirius1service1superadmin1superadmin1wsdl1SetBudgetByLocation_0id);
            }catch(Exception exc){
                %>
                Exception: <%= org.eclipse.jst.ws.util.JspUtils.markup(exc.toString()) %>
                Message: <%= org.eclipse.jst.ws.util.JspUtils.markup(exc.getMessage()) %>
                <%
break;
            }
        }
        else {
            // async code omitted
            break;
        }
    }
if (sourceOut != null) {
%>
    <TEXTAREA ROWS="8" COLs="45"><%= org.eclipse.jst.ws.util.JspUtils.markup(com.ibm.ccl.ws.jaxws.gstc.util.SourceUtils.transform(sourceOut)) %></TEXTAREA>
<%
}
else {
if (returnp20mtemp == null) {
%>
    null
<%
} else {
    %>
    <TABLE CLASS="tableform">
<TR>
<TD COLSPAN="2" VALIGN="TOP" ALIGN="LEFT" CLASS="headingcol">returnp:</TD>
<TR>
<TD CLASS="spacercol">&nbsp;</TD>
<TD COLSPAN="1" VALIGN="TOP" ALIGN="LEFT" CLASS="headingcol">setBudgetByLocationReturn:</TD>
<TD>
<%
if(returnp20mtemp != null){
boolean typesetBudgetByLocationReturn22 = returnp20mtemp.isSetBudgetByLocationReturn();
        String tempResultsetBudgetByLocationReturn22 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typesetBudgetByLocationReturn22));
        %>
        <%= tempResultsetBudgetByLocationReturn22 %>
        <%
}
%>
</TD>
    </TABLE>
    <%
}
%>
<HR/><BR/>
<%
}
break;
case 30:
    gotMethod = true;
    com.sirius.service.superadmin.superadmin.wsdl.GetBudgetByLocationResponse returnp31mtemp = null;
    if (methodKey != null) {
        javax.xml.ws.Response resp = com.ibm.ccl.ws.jaxws.gstc.util.AsyncUtils.getResponse(session, methodKey);
        if (resp != null) {
            isDone = resp.isDone();
            if (!isDone)
                break;
            if (resp.get() != null) {
                Object o = resp.get();
                if (o instanceof javax.xml.transform.Source)
                    sourceOut = (javax.xml.transform.Source) o;
                else if (o instanceof com.sirius.service.superadmin.superadmin.wsdl.GetBudgetByLocationResponse)
                    returnp31mtemp = (com.sirius.service.superadmin.superadmin.wsdl.GetBudgetByLocationResponse) o;
            }
        }
    }
    else if (bypass) {
        javax.xml.ws.Dispatch dispatch = sampleSuperadminProxyid._getDescriptor().getDispatch();

        if (request.getParameter("__use_soapaction__") != null)
            com.ibm.ccl.ws.jaxws.gstc.util.DispatchUtils.useSoapAction(dispatch, request.getParameter("__soapaction__"));
        else
            com.ibm.ccl.ws.jaxws.gstc.util.DispatchUtils.clearSoapAction(dispatch);

        if (!async)
            sourceOut = (javax.xml.transform.Source) dispatch.invoke(sourceIn);
        else {
            // async code omitted
            break;
        }
    } else {
        %>
        <jsp:useBean id="com1sirius1service1superadmin1superadmin1wsdl1GetBudgetByLocation_5id" scope="session" class="com.sirius.service.superadmin.superadmin.wsdl.GetBudgetByLocation" />
        <%
        String parameters35null = request.getParameter("parameters35null");
        if (parameters35null != null)
            com1sirius1service1superadmin1superadmin1wsdl1GetBudgetByLocation_5id = null;
        else {
        String locationId_6id=  request.getParameter("locationId37");
        int locationId_6idTemp  = Integer.parseInt(locationId_6id);
        com1sirius1service1superadmin1superadmin1wsdl1GetBudgetByLocation_5id.setLocationId(locationId_6idTemp);
        }

        if (!async) {
        try {
            returnp31mtemp = sampleSuperadminProxyid.getBudgetByLocation(com1sirius1service1superadmin1superadmin1wsdl1GetBudgetByLocation_5id);
            }catch(Exception exc){
                %>
                Exception: <%= org.eclipse.jst.ws.util.JspUtils.markup(exc.toString()) %>
                Message: <%= org.eclipse.jst.ws.util.JspUtils.markup(exc.getMessage()) %>
                <%
break;
            }
        }
        else {
            // async code omitted
            break;
        }
    }
if (sourceOut != null) {
%>
    <TEXTAREA ROWS="8" COLs="45"><%= org.eclipse.jst.ws.util.JspUtils.markup(com.ibm.ccl.ws.jaxws.gstc.util.SourceUtils.transform(sourceOut)) %></TEXTAREA>
<%
}
else {
if (returnp31mtemp == null) {
%>
    null
<%
} else {
    %>
    <TABLE CLASS="tableform">
<TR>
<TD COLSPAN="2" VALIGN="TOP" ALIGN="LEFT" CLASS="headingcol">returnp:</TD>
<TR>
<TD CLASS="spacercol">&nbsp;</TD>
<TD COLSPAN="1" VALIGN="TOP" ALIGN="LEFT" CLASS="headingcol">getBudgetByLocationReturn:</TD>
<TD>
<%
if(returnp31mtemp != null){
java.math.BigDecimal typegetBudgetByLocationReturn33 = returnp31mtemp.getGetBudgetByLocationReturn();
if(typegetBudgetByLocationReturn33 != null){
        String tempResultgetBudgetByLocationReturn33 = org.eclipse.jst.ws.util.JspUtils.markup(typegetBudgetByLocationReturn33.toString());
        %>
        <%= tempResultgetBudgetByLocationReturn33 %>
        <%
}
else{
        %>
        <%= typegetBudgetByLocationReturn33%>
        <%
}
}
%>
</TD>
    </TABLE>
    <%
}
%>
<HR/><BR/>
<%
}
break;
case 39:
    gotMethod = true;
    com.sirius.service.superadmin.superadmin.wsdl.AddLocationResponse returnp40mtemp = null;
    if (methodKey != null) {
        javax.xml.ws.Response resp = com.ibm.ccl.ws.jaxws.gstc.util.AsyncUtils.getResponse(session, methodKey);
        if (resp != null) {
            isDone = resp.isDone();
            if (!isDone)
                break;
            if (resp.get() != null) {
                Object o = resp.get();
                if (o instanceof javax.xml.transform.Source)
                    sourceOut = (javax.xml.transform.Source) o;
                else if (o instanceof com.sirius.service.superadmin.superadmin.wsdl.AddLocationResponse)
                    returnp40mtemp = (com.sirius.service.superadmin.superadmin.wsdl.AddLocationResponse) o;
            }
        }
    }
    else if (bypass) {
        javax.xml.ws.Dispatch dispatch = sampleSuperadminProxyid._getDescriptor().getDispatch();

        if (request.getParameter("__use_soapaction__") != null)
            com.ibm.ccl.ws.jaxws.gstc.util.DispatchUtils.useSoapAction(dispatch, request.getParameter("__soapaction__"));
        else
            com.ibm.ccl.ws.jaxws.gstc.util.DispatchUtils.clearSoapAction(dispatch);

        if (!async)
            sourceOut = (javax.xml.transform.Source) dispatch.invoke(sourceIn);
        else {
            // async code omitted
            break;
        }
    } else {
        %>
        <jsp:useBean id="com1sirius1service1superadmin1superadmin1wsdl1AddLocation_8id" scope="session" class="com.sirius.service.superadmin.superadmin.wsdl.AddLocation" />
        <%
        String parameters44null = request.getParameter("parameters44null");
        if (parameters44null != null)
            com1sirius1service1superadmin1superadmin1wsdl1AddLocation_8id = null;
        else {
        String city_9id=  request.getParameter("city46");
        String city46null = request.getParameter("city46null");
        java.lang.String city_9idTemp;
        if (city46null != null)
            city_9idTemp = null;
        else {
         city_9idTemp  = city_9id;
        }
        String state_11id=  request.getParameter("state48");
        String state48null = request.getParameter("state48null");
        java.lang.String state_11idTemp;
        if (state48null != null)
            state_11idTemp = null;
        else {
         state_11idTemp  = state_11id;
        }
        String createrId_13id=  request.getParameter("createrId50");
        int createrId_13idTemp  = Integer.parseInt(createrId_13id);
        com1sirius1service1superadmin1superadmin1wsdl1AddLocation_8id.setCity(city_9idTemp);
        com1sirius1service1superadmin1superadmin1wsdl1AddLocation_8id.setState(state_11idTemp);
        com1sirius1service1superadmin1superadmin1wsdl1AddLocation_8id.setCreaterId(createrId_13idTemp);
        }

        if (!async) {
        try {
            returnp40mtemp = sampleSuperadminProxyid.addLocation(com1sirius1service1superadmin1superadmin1wsdl1AddLocation_8id);
            }catch(Exception exc){
                %>
                Exception: <%= org.eclipse.jst.ws.util.JspUtils.markup(exc.toString()) %>
                Message: <%= org.eclipse.jst.ws.util.JspUtils.markup(exc.getMessage()) %>
                <%
break;
            }
        }
        else {
            // async code omitted
            break;
        }
    }
if (sourceOut != null) {
%>
    <TEXTAREA ROWS="8" COLs="45"><%= org.eclipse.jst.ws.util.JspUtils.markup(com.ibm.ccl.ws.jaxws.gstc.util.SourceUtils.transform(sourceOut)) %></TEXTAREA>
<%
}
else {
if (returnp40mtemp == null) {
%>
    null
<%
} else {
    %>
    <TABLE CLASS="tableform">
<TR>
<TD COLSPAN="2" VALIGN="TOP" ALIGN="LEFT" CLASS="headingcol">returnp:</TD>
<TR>
<TD CLASS="spacercol">&nbsp;</TD>
<TD COLSPAN="1" VALIGN="TOP" ALIGN="LEFT" CLASS="headingcol">addLocationReturn:</TD>
<TD>
<%
if(returnp40mtemp != null){
boolean typeaddLocationReturn42 = returnp40mtemp.isAddLocationReturn();
        String tempResultaddLocationReturn42 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typeaddLocationReturn42));
        %>
        <%= tempResultaddLocationReturn42 %>
        <%
}
%>
</TD>
    </TABLE>
    <%
}
%>
<HR/><BR/>
<%
}
break;
case 52:
    gotMethod = true;
    com.sirius.service.superadmin.superadmin.wsdl.AssignAdminResponse returnp53mtemp = null;
    if (methodKey != null) {
        javax.xml.ws.Response resp = com.ibm.ccl.ws.jaxws.gstc.util.AsyncUtils.getResponse(session, methodKey);
        if (resp != null) {
            isDone = resp.isDone();
            if (!isDone)
                break;
            if (resp.get() != null) {
                Object o = resp.get();
                if (o instanceof javax.xml.transform.Source)
                    sourceOut = (javax.xml.transform.Source) o;
                else if (o instanceof com.sirius.service.superadmin.superadmin.wsdl.AssignAdminResponse)
                    returnp53mtemp = (com.sirius.service.superadmin.superadmin.wsdl.AssignAdminResponse) o;
            }
        }
    }
    else if (bypass) {
        javax.xml.ws.Dispatch dispatch = sampleSuperadminProxyid._getDescriptor().getDispatch();

        if (request.getParameter("__use_soapaction__") != null)
            com.ibm.ccl.ws.jaxws.gstc.util.DispatchUtils.useSoapAction(dispatch, request.getParameter("__soapaction__"));
        else
            com.ibm.ccl.ws.jaxws.gstc.util.DispatchUtils.clearSoapAction(dispatch);

        if (!async)
            sourceOut = (javax.xml.transform.Source) dispatch.invoke(sourceIn);
        else {
            // async code omitted
            break;
        }
    } else {
        %>
        <jsp:useBean id="com1sirius1service1superadmin1superadmin1wsdl1AssignAdmin_15id" scope="session" class="com.sirius.service.superadmin.superadmin.wsdl.AssignAdmin" />
        <%
        String parameters57null = request.getParameter("parameters57null");
        if (parameters57null != null)
            com1sirius1service1superadmin1superadmin1wsdl1AssignAdmin_15id = null;
        else {
        String locationId_16id=  request.getParameter("locationId59");
        int locationId_16idTemp  = Integer.parseInt(locationId_16id);
        String adminId_18id=  request.getParameter("adminId61");
        int adminId_18idTemp  = Integer.parseInt(adminId_18id);
        String updaterId_20id=  request.getParameter("updaterId63");
        int updaterId_20idTemp  = Integer.parseInt(updaterId_20id);
        com1sirius1service1superadmin1superadmin1wsdl1AssignAdmin_15id.setLocationId(locationId_16idTemp);
        com1sirius1service1superadmin1superadmin1wsdl1AssignAdmin_15id.setAdminId(adminId_18idTemp);
        com1sirius1service1superadmin1superadmin1wsdl1AssignAdmin_15id.setUpdaterId(updaterId_20idTemp);
        }

        if (!async) {
        try {
            returnp53mtemp = sampleSuperadminProxyid.assignAdmin(com1sirius1service1superadmin1superadmin1wsdl1AssignAdmin_15id);
            }catch(Exception exc){
                %>
                Exception: <%= org.eclipse.jst.ws.util.JspUtils.markup(exc.toString()) %>
                Message: <%= org.eclipse.jst.ws.util.JspUtils.markup(exc.getMessage()) %>
                <%
break;
            }
        }
        else {
            // async code omitted
            break;
        }
    }
if (sourceOut != null) {
%>
    <TEXTAREA ROWS="8" COLs="45"><%= org.eclipse.jst.ws.util.JspUtils.markup(com.ibm.ccl.ws.jaxws.gstc.util.SourceUtils.transform(sourceOut)) %></TEXTAREA>
<%
}
else {
if (returnp53mtemp == null) {
%>
    null
<%
} else {
    %>
    <TABLE CLASS="tableform">
<TR>
<TD COLSPAN="2" VALIGN="TOP" ALIGN="LEFT" CLASS="headingcol">returnp:</TD>
<TR>
<TD CLASS="spacercol">&nbsp;</TD>
<TD COLSPAN="1" VALIGN="TOP" ALIGN="LEFT" CLASS="headingcol">assignAdminReturn:</TD>
<TD>
<%
if(returnp53mtemp != null){
boolean typeassignAdminReturn55 = returnp53mtemp.isAssignAdminReturn();
        String tempResultassignAdminReturn55 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typeassignAdminReturn55));
        %>
        <%= tempResultassignAdminReturn55 %>
        <%
}
%>
</TD>
    </TABLE>
    <%
}
%>
<HR/><BR/>
<%
}
break;
}
} catch (Exception e) { 
%>
exception: <%=org.eclipse.jst.ws.util.JspUtils.markup(e.toString())%>
<%
return;
}
if(!gotMethod){
%>
Result: N/A
<%
} else if (!isDone) {
%>
No results available yet.
<%
} else if (async && methodKey == null) {
%>
The service has been invoked.
<script language="JavaScript">reloadMethods();</script>
<%
}
%>
</BODY>
</HTML>
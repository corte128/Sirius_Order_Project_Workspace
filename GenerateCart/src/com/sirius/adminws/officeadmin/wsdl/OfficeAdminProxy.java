package com.sirius.adminws.officeadmin.wsdl;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;
import java.util.List;
import javax.xml.ws.Action;

public class OfficeAdminProxy{

    protected Descriptor _descriptor;

    public class Descriptor {
        private com.sirius.adminws.officeadmin.wsdl.JaxwsOfficeAdmin _service = null;
        private com.sirius.adminws.officeadmin.wsdl.OfficeAdmin _proxy = null;
        private Dispatch<Source> _dispatch = null;
        private boolean _useJNDIOnly = false;

        public Descriptor() {
            init();
        }

        public Descriptor(URL wsdlLocation, QName serviceName) {
            _service = new com.sirius.adminws.officeadmin.wsdl.JaxwsOfficeAdmin(wsdlLocation, serviceName);
            initCommon();
        }

        public void init() {
            _service = null;
            _proxy = null;
            _dispatch = null;
            try
            {
                InitialContext ctx = new InitialContext();
                _service = (com.sirius.adminws.officeadmin.wsdl.JaxwsOfficeAdmin)ctx.lookup("java:comp/env/service/JaxwsOfficeAdmin");
            }
            catch (NamingException e)
            {
                if ("true".equalsIgnoreCase(System.getProperty("DEBUG_PROXY"))) {
                    System.out.println("JNDI lookup failure: javax.naming.NamingException: " + e.getMessage());
                    e.printStackTrace(System.out);
                }
            }

            if (_service == null && !_useJNDIOnly)
                _service = new com.sirius.adminws.officeadmin.wsdl.JaxwsOfficeAdmin();
            initCommon();
        }

        private void initCommon() {
            _proxy = _service.getOfficeAdmin();
        }

        public com.sirius.adminws.officeadmin.wsdl.OfficeAdmin getProxy() {
            return _proxy;
        }

        public void useJNDIOnly(boolean useJNDIOnly) {
            _useJNDIOnly = useJNDIOnly;
            init();
        }

        public Dispatch<Source> getDispatch() {
            if (_dispatch == null ) {
                QName portQName = new QName("http://adminws.sirius.com/officeAdmin/wsdl", "officeAdmin");
                _dispatch = _service.createDispatch(portQName, Source.class, Service.Mode.MESSAGE);

                String proxyEndpointUrl = getEndpoint();
                BindingProvider bp = (BindingProvider) _dispatch;
                String dispatchEndpointUrl = (String) bp.getRequestContext().get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY);
                if (!dispatchEndpointUrl.equals(proxyEndpointUrl))
                    bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, proxyEndpointUrl);
            }
            return _dispatch;
        }

        public String getEndpoint() {
            BindingProvider bp = (BindingProvider) _proxy;
            return (String) bp.getRequestContext().get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY);
        }

        public void setEndpoint(String endpointUrl) {
            BindingProvider bp = (BindingProvider) _proxy;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointUrl);

            if (_dispatch != null ) {
                bp = (BindingProvider) _dispatch;
                bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointUrl);
            }
        }

        public void setMTOMEnabled(boolean enable) {
            SOAPBinding binding = (SOAPBinding) ((BindingProvider) _proxy).getBinding();
            binding.setMTOMEnabled(enable);
        }
    }

    public OfficeAdminProxy() {
        _descriptor = new Descriptor();
        _descriptor.setMTOMEnabled(false);
    }

    public OfficeAdminProxy(URL wsdlLocation, QName serviceName) {
        _descriptor = new Descriptor(wsdlLocation, serviceName);
        _descriptor.setMTOMEnabled(false);
    }

    public Descriptor _getDescriptor() {
        return _descriptor;
    }

    public boolean addVisitors(String startDate, String endDate, int count, String comment, int userID, int locationID) {
        return _getDescriptor().getProxy().addVisitors(startDate,endDate,count,comment,userID,locationID);
    }

    public boolean addHoliday(String holidayName, String date, int userID, int locationID) {
        return _getDescriptor().getProxy().addHoliday(holidayName,date,userID,locationID);
    }

    public boolean deleteHoliday(int holidayID, int userID) {
        return _getDescriptor().getProxy().deleteHoliday(holidayID,userID);
    }

    public List<Holiday> getAllHolidays(int locationID) {
        return _getDescriptor().getProxy().getAllHolidays(locationID);
    }

    public List<EmployeeBean> getUnapprovedEmployees(int locationID) {
        return _getDescriptor().getProxy().getUnapprovedEmployees(locationID);
    }

    public EmployeeBean getOfficeAdmin(int locationID) {
        return _getDescriptor().getProxy().getOfficeAdmin(locationID);
    }

}
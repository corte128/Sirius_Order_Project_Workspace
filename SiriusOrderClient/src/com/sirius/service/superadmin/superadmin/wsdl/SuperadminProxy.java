package com.sirius.service.superadmin.superadmin.wsdl;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;
import java.math.BigDecimal;
import java.util.List;
import javax.xml.ws.Action;

public class SuperadminProxy{

    protected Descriptor _descriptor;

    public class Descriptor {
        private com.sirius.service.superadmin.superadmin.wsdl.SuperAdminService _service = null;
        private com.sirius.service.superadmin.superadmin.wsdl.SuperAdmin _proxy = null;
        private Dispatch<Source> _dispatch = null;
        private boolean _useJNDIOnly = false;

        public Descriptor() {
            init();
        }

        public Descriptor(URL wsdlLocation, QName serviceName) {
            _service = new com.sirius.service.superadmin.superadmin.wsdl.SuperAdminService(wsdlLocation, serviceName);
            initCommon();
        }

        public void init() {
            _service = null;
            _proxy = null;
            _dispatch = null;
            try
            {
                InitialContext ctx = new InitialContext();
                _service = (com.sirius.service.superadmin.superadmin.wsdl.SuperAdminService)ctx.lookup("java:comp/env/service/SuperAdminService");
            }
            catch (NamingException e)
            {
                if ("true".equalsIgnoreCase(System.getProperty("DEBUG_PROXY"))) {
                    System.out.println("JNDI lookup failure: javax.naming.NamingException: " + e.getMessage());
                    e.printStackTrace(System.out);
                }
            }

            if (_service == null && !_useJNDIOnly)
                _service = new com.sirius.service.superadmin.superadmin.wsdl.SuperAdminService();
            initCommon();
        }

        private void initCommon() {
            _proxy = _service.getSuperadmin();
        }

        public com.sirius.service.superadmin.superadmin.wsdl.SuperAdmin getProxy() {
            return _proxy;
        }

        public void useJNDIOnly(boolean useJNDIOnly) {
            _useJNDIOnly = useJNDIOnly;
            init();
        }

        public Dispatch<Source> getDispatch() {
            if (_dispatch == null ) {
                QName portQName = new QName("http://superadmin.service.sirius.com/superadmin/wsdl", "superadmin");
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

    public SuperadminProxy() {
        _descriptor = new Descriptor();
        _descriptor.setMTOMEnabled(false);
    }

    public SuperadminProxy(URL wsdlLocation, QName serviceName) {
        _descriptor = new Descriptor(wsdlLocation, serviceName);
        _descriptor.setMTOMEnabled(false);
    }

    public Descriptor _getDescriptor() {
        return _descriptor;
    }

    public boolean setBudgetByLocation(BigDecimal budget, int locationId) {
        return _getDescriptor().getProxy().setBudgetByLocation(budget,locationId);
    }

    public BigDecimal getBudgetByLocation(int locationId) {
        return _getDescriptor().getProxy().getBudgetByLocation(locationId);
    }

    public boolean addLocation(String city, String state, int creatorId) {
        return _getDescriptor().getProxy().addLocation(city,state,creatorId);
    }

    public boolean assignAdmin(int locationId, int adminId, int updaterId) {
        return _getDescriptor().getProxy().assignAdmin(locationId,adminId,updaterId);
    }

    public List<OfficeBean> getOffices() {
        return _getDescriptor().getProxy().getOffices();
    }

    public int getEmployeeIdByName(String name) {
        return _getDescriptor().getProxy().getEmployeeIdByName(name);
    }

    public List<String> getOfficeAdminNames() {
        return _getDescriptor().getProxy().getOfficeAdminNames();
    }

}
package com.sirius.employeews.employee.wsdl;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;
import javax.xml.ws.Action;

public class EmployeeProxy{

    protected Descriptor _descriptor;

    public class Descriptor {
        private com.sirius.employeews.employee.wsdl.JaxwsEmployee _service = null;
        private com.sirius.employeews.employee.wsdl.Employee _proxy = null;
        private Dispatch<Source> _dispatch = null;
        private boolean _useJNDIOnly = false;

        public Descriptor() {
            init();
        }

        public Descriptor(URL wsdlLocation, QName serviceName) {
            _service = new com.sirius.employeews.employee.wsdl.JaxwsEmployee(wsdlLocation, serviceName);
            initCommon();
        }

        public void init() {
            _service = null;
            _proxy = null;
            _dispatch = null;
            try
            {
                InitialContext ctx = new InitialContext();
                _service = (com.sirius.employeews.employee.wsdl.JaxwsEmployee)ctx.lookup("java:comp/env/service/JaxwsEmployee");
            }
            catch (NamingException e)
            {
                if ("true".equalsIgnoreCase(System.getProperty("DEBUG_PROXY"))) {
                    System.out.println("JNDI lookup failure: javax.naming.NamingException: " + e.getMessage());
                    e.printStackTrace(System.out);
                }
            }

            if (_service == null && !_useJNDIOnly)
                _service = new com.sirius.employeews.employee.wsdl.JaxwsEmployee();
            initCommon();
        }

        private void initCommon() {
            _proxy = _service.getEmployee();
        }

        public com.sirius.employeews.employee.wsdl.Employee getProxy() {
            return _proxy;
        }

        public void useJNDIOnly(boolean useJNDIOnly) {
            _useJNDIOnly = useJNDIOnly;
            init();
        }

        public Dispatch<Source> getDispatch() {
            if (_dispatch == null ) {
                QName portQName = new QName("http://employeews.sirius.com/employee/wsdl", "employee");
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

    public EmployeeProxy() {
        _descriptor = new Descriptor();
        _descriptor.setMTOMEnabled(false);
    }

    public EmployeeProxy(URL wsdlLocation, QName serviceName) {
        _descriptor = new Descriptor(wsdlLocation, serviceName);
        _descriptor.setMTOMEnabled(false);
    }

    public Descriptor _getDescriptor() {
        return _descriptor;
    }

    public EmployeeBean getEmployeeByEmail(String email) {
        return _getDescriptor().getProxy().getEmployeeByEmail(email);
    }

    public boolean addEmployee(String name, String password, int role, String email, byte[] picture, int location) {
        return _getDescriptor().getProxy().addEmployee(name,password,role,email,picture,location);
    }

    public boolean updateEmployee(int id, String isValid, int updaterId) {
        return _getDescriptor().getProxy().updateEmployee(id,isValid,updaterId);
    }

}
package com.sirius.service.cart.cart.wsdl;

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

public class CartProxy{

    protected Descriptor _descriptor;

    public class Descriptor {
        private com.sirius.service.cart.cart.wsdl.CartService_Service _service = null;
        private com.sirius.service.cart.cart.wsdl.CartService _proxy = null;
        private Dispatch<Source> _dispatch = null;
        private boolean _useJNDIOnly = false;

        public Descriptor() {
            init();
        }

        public Descriptor(URL wsdlLocation, QName serviceName) {
            _service = new com.sirius.service.cart.cart.wsdl.CartService_Service(wsdlLocation, serviceName);
            initCommon();
        }

        public void init() {
            _service = null;
            _proxy = null;
            _dispatch = null;
            try
            {
                InitialContext ctx = new InitialContext();
                _service = (com.sirius.service.cart.cart.wsdl.CartService_Service)ctx.lookup("java:comp/env/service/CartService");
            }
            catch (NamingException e)
            {
                if ("true".equalsIgnoreCase(System.getProperty("DEBUG_PROXY"))) {
                    System.out.println("JNDI lookup failure: javax.naming.NamingException: " + e.getMessage());
                    e.printStackTrace(System.out);
                }
            }

            if (_service == null && !_useJNDIOnly)
                _service = new com.sirius.service.cart.cart.wsdl.CartService_Service();
            initCommon();
        }

        private void initCommon() {
            _proxy = _service.getCart();
        }

        public com.sirius.service.cart.cart.wsdl.CartService getProxy() {
            return _proxy;
        }

        public void useJNDIOnly(boolean useJNDIOnly) {
            _useJNDIOnly = useJNDIOnly;
            init();
        }

        public Dispatch<Source> getDispatch() {
            if (_dispatch == null ) {
                QName portQName = new QName("http://cart.service.sirius.com/cart/wsdl", "cart");
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

    public CartProxy() {
        _descriptor = new Descriptor();
        _descriptor.setMTOMEnabled(false);
    }

    public CartProxy(URL wsdlLocation, QName serviceName) {
        _descriptor = new Descriptor(wsdlLocation, serviceName);
        _descriptor.setMTOMEnabled(false);
    }

    public Descriptor _getDescriptor() {
        return _descriptor;
    }

    public boolean addProductToCart(OrderBean order, BudgetBean budget, int createdBy) {
        return _getDescriptor().getProxy().addProductToCart(order,budget,createdBy);
    }

    public List<OrderBean> getAllProductsInCart(int locationId) {
        return _getDescriptor().getProxy().getAllProductsInCart(locationId);
    }

    public boolean updateProductQuantityInCart(int locationId, int quantity, int productId, int updatedBy) {
        return _getDescriptor().getProxy().updateProductQuantityInCart(locationId,quantity,productId,updatedBy);
    }

    public boolean removeProductFromCart(int orderId, int updatedBy) {
        return _getDescriptor().getProxy().removeProductFromCart(orderId,updatedBy);
    }

    public boolean saveOrder(String orderName, BudgetBean budget, int locationId, int createdBy) {
        return _getDescriptor().getProxy().saveOrder(orderName,budget,locationId,createdBy);
    }

    public List<OrderBean> getOrderByOrderName(String orderName, int locationId) {
        return _getDescriptor().getProxy().getOrderByOrderName(orderName,locationId);
    }

    public List<OrderBean> getAllProductsInCartByProductType(int locationId, String productType) {
        return _getDescriptor().getProxy().getAllProductsInCartByProductType(locationId,productType);
    }

}
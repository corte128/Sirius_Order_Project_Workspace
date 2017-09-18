//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package com.sirius.product.service.main.product.wsdl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.sirius.product.service.main.product.wsdl package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetProductByID_QNAME = new QName("http://main.service.product.sirius.com/product/wsdl", "getProductByID");
    private final static QName _GetProductByIDResponse_QNAME = new QName("http://main.service.product.sirius.com/product/wsdl", "getProductByIDResponse");
    private final static QName _GetAllProductsByNameResponse_QNAME = new QName("http://main.service.product.sirius.com/product/wsdl", "getAllProductsByNameResponse");
    private final static QName _GetAllProductsByNameAndType_QNAME = new QName("http://main.service.product.sirius.com/product/wsdl", "getAllProductsByNameAndType");
    private final static QName _GetAllProductsByType_QNAME = new QName("http://main.service.product.sirius.com/product/wsdl", "getAllProductsByType");
    private final static QName _GetAllProductsByName_QNAME = new QName("http://main.service.product.sirius.com/product/wsdl", "getAllProductsByName");
    private final static QName _GetAllProductsByNameAndTypeResponse_QNAME = new QName("http://main.service.product.sirius.com/product/wsdl", "getAllProductsByNameAndTypeResponse");
    private final static QName _GetAllProductByTypeResponse_QNAME = new QName("http://main.service.product.sirius.com/product/wsdl", "getAllProductByTypeResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.sirius.product.service.main.product.wsdl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAllProductsByType }
     * 
     */
    public GetAllProductsByType createGetAllProductsByType() {
        return new GetAllProductsByType();
    }

    /**
     * Create an instance of {@link GetAllProductsByNameAndType }
     * 
     */
    public GetAllProductsByNameAndType createGetAllProductsByNameAndType() {
        return new GetAllProductsByNameAndType();
    }

    /**
     * Create an instance of {@link GetAllProductsByNameAndTypeResponse }
     * 
     */
    public GetAllProductsByNameAndTypeResponse createGetAllProductsByNameAndTypeResponse() {
        return new GetAllProductsByNameAndTypeResponse();
    }

    /**
     * Create an instance of {@link GetAllProductsByName }
     * 
     */
    public GetAllProductsByName createGetAllProductsByName() {
        return new GetAllProductsByName();
    }

    /**
     * Create an instance of {@link GetAllProductsByNameResponse }
     * 
     */
    public GetAllProductsByNameResponse createGetAllProductsByNameResponse() {
        return new GetAllProductsByNameResponse();
    }

    /**
     * Create an instance of {@link GetAllProductByTypeResponse }
     * 
     */
    public GetAllProductByTypeResponse createGetAllProductByTypeResponse() {
        return new GetAllProductByTypeResponse();
    }

    /**
     * Create an instance of {@link GetProductByID }
     * 
     */
    public GetProductByID createGetProductByID() {
        return new GetProductByID();
    }

    /**
     * Create an instance of {@link GetProductByIDResponse }
     * 
     */
    public GetProductByIDResponse createGetProductByIDResponse() {
        return new GetProductByIDResponse();
    }

    /**
     * Create an instance of {@link ProductBean }
     * 
     */
    public ProductBean createProductBean() {
        return new ProductBean();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductByID }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://main.service.product.sirius.com/product/wsdl", name = "getProductByID")
    public JAXBElement<GetProductByID> createGetProductByID(GetProductByID value) {
        return new JAXBElement<GetProductByID>(_GetProductByID_QNAME, GetProductByID.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductByIDResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://main.service.product.sirius.com/product/wsdl", name = "getProductByIDResponse")
    public JAXBElement<GetProductByIDResponse> createGetProductByIDResponse(GetProductByIDResponse value) {
        return new JAXBElement<GetProductByIDResponse>(_GetProductByIDResponse_QNAME, GetProductByIDResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllProductsByNameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://main.service.product.sirius.com/product/wsdl", name = "getAllProductsByNameResponse")
    public JAXBElement<GetAllProductsByNameResponse> createGetAllProductsByNameResponse(GetAllProductsByNameResponse value) {
        return new JAXBElement<GetAllProductsByNameResponse>(_GetAllProductsByNameResponse_QNAME, GetAllProductsByNameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllProductsByNameAndType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://main.service.product.sirius.com/product/wsdl", name = "getAllProductsByNameAndType")
    public JAXBElement<GetAllProductsByNameAndType> createGetAllProductsByNameAndType(GetAllProductsByNameAndType value) {
        return new JAXBElement<GetAllProductsByNameAndType>(_GetAllProductsByNameAndType_QNAME, GetAllProductsByNameAndType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllProductsByType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://main.service.product.sirius.com/product/wsdl", name = "getAllProductsByType")
    public JAXBElement<GetAllProductsByType> createGetAllProductsByType(GetAllProductsByType value) {
        return new JAXBElement<GetAllProductsByType>(_GetAllProductsByType_QNAME, GetAllProductsByType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllProductsByName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://main.service.product.sirius.com/product/wsdl", name = "getAllProductsByName")
    public JAXBElement<GetAllProductsByName> createGetAllProductsByName(GetAllProductsByName value) {
        return new JAXBElement<GetAllProductsByName>(_GetAllProductsByName_QNAME, GetAllProductsByName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllProductsByNameAndTypeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://main.service.product.sirius.com/product/wsdl", name = "getAllProductsByNameAndTypeResponse")
    public JAXBElement<GetAllProductsByNameAndTypeResponse> createGetAllProductsByNameAndTypeResponse(GetAllProductsByNameAndTypeResponse value) {
        return new JAXBElement<GetAllProductsByNameAndTypeResponse>(_GetAllProductsByNameAndTypeResponse_QNAME, GetAllProductsByNameAndTypeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllProductByTypeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://main.service.product.sirius.com/product/wsdl", name = "getAllProductByTypeResponse")
    public JAXBElement<GetAllProductByTypeResponse> createGetAllProductByTypeResponse(GetAllProductByTypeResponse value) {
        return new JAXBElement<GetAllProductByTypeResponse>(_GetAllProductByTypeResponse_QNAME, GetAllProductByTypeResponse.class, null, value);
    }

}
//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package com.sirius.service.cart.cart.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for removeProductFromCartResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="removeProductFromCartResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="removeProductFromCartReturn" type="{http://www.w3.org/2001/XMLSchema}boolean" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "removeProductFromCartResponse", propOrder = {
    "removeProductFromCartReturn"
})
public class RemoveProductFromCartResponse {

    protected boolean removeProductFromCartReturn;

    /**
     * Gets the value of the removeProductFromCartReturn property.
     * 
     */
    public boolean isRemoveProductFromCartReturn() {
        return removeProductFromCartReturn;
    }

    /**
     * Sets the value of the removeProductFromCartReturn property.
     * 
     */
    public void setRemoveProductFromCartReturn(boolean value) {
        this.removeProductFromCartReturn = value;
    }

}
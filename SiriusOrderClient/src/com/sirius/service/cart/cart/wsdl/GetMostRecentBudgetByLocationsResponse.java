//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package com.sirius.service.cart.cart.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getMostRecentBudgetByLocationsResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getMostRecentBudgetByLocationsResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getMostRecentBudgetByLocationReturn" type="{http://cart.service.sirius.com/cart/wsdl}budgetBean" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getMostRecentBudgetByLocationsResponse", propOrder = {
    "getMostRecentBudgetByLocationReturn"
})
public class GetMostRecentBudgetByLocationsResponse {

    protected BudgetBean getMostRecentBudgetByLocationReturn;

    /**
     * Gets the value of the getMostRecentBudgetByLocationReturn property.
     * 
     * @return
     *     possible object is
     *     {@link BudgetBean }
     *     
     */
    public BudgetBean getGetMostRecentBudgetByLocationReturn() {
        return getMostRecentBudgetByLocationReturn;
    }

    /**
     * Sets the value of the getMostRecentBudgetByLocationReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link BudgetBean }
     *     
     */
    public void setGetMostRecentBudgetByLocationReturn(BudgetBean value) {
        this.getMostRecentBudgetByLocationReturn = value;
    }

}

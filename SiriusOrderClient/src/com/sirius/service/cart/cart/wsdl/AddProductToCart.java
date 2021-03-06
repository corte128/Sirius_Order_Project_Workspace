//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package com.sirius.service.cart.cart.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for addProductToCart complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addProductToCart">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="order" type="{http://cart.service.sirius.com/cart/wsdl}orderBean" minOccurs="0" form="qualified"/>
 *         &lt;element name="budget" type="{http://cart.service.sirius.com/cart/wsdl}budgetBean" minOccurs="0" form="qualified"/>
 *         &lt;element name="createdBy" type="{http://www.w3.org/2001/XMLSchema}int" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addProductToCart", propOrder = {
    "order",
    "budget",
    "createdBy"
})
public class AddProductToCart {

    protected OrderBean order;
    protected BudgetBean budget;
    protected int createdBy;

    /**
     * Gets the value of the order property.
     * 
     * @return
     *     possible object is
     *     {@link OrderBean }
     *     
     */
    public OrderBean getOrder() {
        return order;
    }

    /**
     * Sets the value of the order property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderBean }
     *     
     */
    public void setOrder(OrderBean value) {
        this.order = value;
    }

    /**
     * Gets the value of the budget property.
     * 
     * @return
     *     possible object is
     *     {@link BudgetBean }
     *     
     */
    public BudgetBean getBudget() {
        return budget;
    }

    /**
     * Sets the value of the budget property.
     * 
     * @param value
     *     allowed object is
     *     {@link BudgetBean }
     *     
     */
    public void setBudget(BudgetBean value) {
        this.budget = value;
    }

    /**
     * Gets the value of the createdBy property.
     * 
     */
    public int getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the value of the createdBy property.
     * 
     */
    public void setCreatedBy(int value) {
        this.createdBy = value;
    }

}

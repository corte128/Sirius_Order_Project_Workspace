//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package com.sirius.adminws.officeadmin.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for deleteHolidayResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="deleteHolidayResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="deleteHolidayReturn" type="{http://www.w3.org/2001/XMLSchema}boolean" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deleteHolidayResponse", propOrder = {
    "deleteHolidayReturn"
})
public class DeleteHolidayResponse {

    protected boolean deleteHolidayReturn;

    /**
     * Gets the value of the deleteHolidayReturn property.
     * 
     */
    public boolean isDeleteHolidayReturn() {
        return deleteHolidayReturn;
    }

    /**
     * Sets the value of the deleteHolidayReturn property.
     * 
     */
    public void setDeleteHolidayReturn(boolean value) {
        this.deleteHolidayReturn = value;
    }

}

package model;

import java.util.Date;

public class serviceInvoice {
    private String serviceInvoiceID;
    private String guestID;
    private String staffID;
    private Date serviceInvoiceDate;
    private String totalserviceInvoice;

    public serviceInvoice(String serviceInvoiceID, String guestID, String staffID, Date serviceInvoiceDate, String totalserviceInvoice) {
        this.serviceInvoiceID = serviceInvoiceID;
        this.guestID = guestID;
        this.staffID = staffID;
        this.serviceInvoiceDate = serviceInvoiceDate;
        this.totalserviceInvoice = totalserviceInvoice;
    }

   
    public String getserviceInvoiceID() {
        return serviceInvoiceID;
    }

    public String getguestID() {
        return guestID;
    }

    public String getStaffID() {
        return staffID;
    }

    public Date getserviceInvoiceDate() {
        return serviceInvoiceDate;
    }

    public String gettotalserviceInvoice() {
        return totalserviceInvoice;
    }

    public void setserviceInvoiceDate(Date serviceInvoiceDate) {
        this.serviceInvoiceDate = serviceInvoiceDate;
    }
}



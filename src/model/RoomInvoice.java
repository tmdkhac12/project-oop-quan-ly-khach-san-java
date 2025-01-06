package model;
import java.util.Date;
public class RoomInvoice {
    

    private String roomInvoiceID;
    private String confirmationFormID;
    private String staffID;
    private Date invoiceDate;
    private String roomInvoiceTotal;

    public RoomInvoice(String roomInvoiceID, String confirmationFormID, String staffID, Date invoiceDate, String roomInvoiceTotal) {
        this.roomInvoiceID = roomInvoiceID;
        this.confirmationFormID = confirmationFormID;
        this.staffID = staffID;
        this.invoiceDate = invoiceDate;
        this.roomInvoiceTotal = roomInvoiceTotal;
    }

    // Getter và Setter
    public String getRoomInvoiceID() {
        return roomInvoiceID;
    }

    public String getConfirmationFormID() {
        return confirmationFormID;
    }

    public String getStaffID() {
        return staffID;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public String getRoomInvoiceTotal() {
        return roomInvoiceTotal;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }
}



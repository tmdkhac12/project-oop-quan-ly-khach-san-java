package model;
public class servicebilldetails {
	    private String serviceBillDetailsID;
	    private String serviceInvoiceID;
	    private String serviceID;
	    private String quatity;
	    private String subtotal;

	    public servicebilldetails(String serviceBillDetailsID, String serviceInvoiceID, String serviceID, String quatity, String subtotal) {
	        this.serviceBillDetailsID = serviceBillDetailsID;
	        this.serviceInvoiceID = serviceInvoiceID;
	        this.serviceID = serviceID;
	        this.quatity = quatity;
	        this.subtotal = subtotal;
	    }  
	    public String getserviceBillDetailsID() {
	        return serviceBillDetailsID;
	    }

	    public String getserviceInvoiceID() {
	        return serviceInvoiceID;
	    }

	    public String getserviceID() {
	        return serviceID;
	    }

	    public String getquatity() {
	        return quatity;
	    }

	    public String getsubtotal() {
	        return subtotal;
	    }
	}


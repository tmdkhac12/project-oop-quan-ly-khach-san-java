package model;
import java.io.Serializable;
public class service  implements Serializable{
    private String serviceID;
    private String serviceName;
    private String servicePrice;

    public service(String serviceID, String serviceName, String servicePrice) {
        this.serviceID = serviceID;
        this.serviceName = serviceName;
        this.servicePrice = servicePrice;
    }
    public service() {
    	this.serviceID = "";
    	this.serviceName = "";
    	this.servicePrice = "";
    }
    public void displayserviceInfo() {};
    

    public String getserviceID() {
        return serviceID;
    }

    public String getserviceName() {
        return serviceName;
    }
    public String getservicePrice() {
        return servicePrice;
    }
    public void setserviceID(String serviceID) {
        this.serviceID = serviceID;
    }
    public void setserviceName(String serviceName) {
        this.serviceName = serviceName;
    }
    public void setservicePrice(String servicePrice) {
        this.servicePrice = servicePrice;
    }
}


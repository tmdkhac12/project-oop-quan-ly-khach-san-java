package model;
public class anotherservice extends service {
    public anotherservice(String serviceID, String serviceName, String servicePrice) {
        super(serviceID, serviceName, servicePrice);
    }
 public anotherservice() {
	 super();
 }
    @Override
    public void displayserviceInfo() {
        System.out.println("Service ID: " + getserviceID());
        System.out.println("Service Name: " + getserviceName());
        System.out.println("Service Price: " + getservicePrice());
        System.out.println("---------------");
    }
}

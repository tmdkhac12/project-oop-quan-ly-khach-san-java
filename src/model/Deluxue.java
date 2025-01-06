package model;
public class Deluxue extends Room {
    private int numberOfBeds;
    private String roomsize;
    private String amenities;
    public Deluxue(String roomID, String roomName, String availability, String roomPrice, int numberOfBeds, String roomsize,String amenities) {
            super(roomID, roomName, availability, roomPrice);
            this.numberOfBeds = numberOfBeds;
            this.roomsize = roomsize;
            this.amenities =amenities;
        }
    public Deluxue() {
        super();
        this.numberOfBeds = 0;
        this.roomsize = " ";
        this.amenities = " ";
    }    
        public int getNumberOfBeds() {
            return numberOfBeds;
        }
        
        public void setNumberOfBeds(int numberOfBeds) {
            this.numberOfBeds = numberOfBeds;
        }
        
        public String getroomsize() {
            return roomsize;
        }
        
        public void setroomsize(String roomsize) {
            this.roomsize = roomsize;
        }

        public String getamenities() {
            return amenities;
        }
        
        public void setamenities(String amenities) {
            this.amenities = amenities;
        }
        @Override
        public void displayRoomInfo() {
            System.out.println("Room ID: " + getRoomID());
            System.out.println("Room Name: " + getRoomName());
            System.out.println("Availability: " + getAvailability());
            System.out.println("Room Price: " + getRoomPrice());
            System.out.println("numberofbed: " + getNumberOfBeds());
            System.out.println("roomsize: " + getroomsize());
            System.out.println("amnetic: " +getamenities());
            System.out.println("Type: Deluxue");
            System.out.println("---------------");
        
}
}

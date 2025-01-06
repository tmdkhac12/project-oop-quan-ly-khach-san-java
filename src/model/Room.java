package model;

public class Room {
    private String roomID;
    private String roomName;
    private String availability;
    private String roomPrice;

    // Constructors
    public Room(String roomID, String roomName, String availability, String roomPrice) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.availability = availability;
        this.roomPrice = roomPrice;
    }
    
    public Room() {
       
    }

    // Getter và Setter cho các thuộc tính
    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(String roomPrice) {
        this.roomPrice = roomPrice;
    }

    // Phương thức hiển thị thông tin phòng
    public void displayRoomInfo() {
        System.out.println("Room ID: " + roomID);
        System.out.println("Room Name: " + roomName);
        System.out.println("Availability: " + availability);
        System.out.println("Room Price: " + roomPrice);
    }
}
package model;
public class RoomType extends Room {
    private String numberOfBeds;
    private String roomArea;

    // Constructor
    public RoomType(String roomID, String roomName, String availability, String roomPrice, String numberOfBeds, String roomArea) {
        super(roomID, roomName, availability, roomPrice);
        this.numberOfBeds = numberOfBeds;
        this.roomArea = roomArea;
    }

    // Override phương thức hiển thị thông tin phòng
    @Override
    public void displayRoomInfo() {
        System.out.println("Room ID: " + getRoomID());
        System.out.println("Room Name: " + getRoomName());
        System.out.println("Availability: " + getAvailability());
        System.out.println("Room Price: " + getRoomPrice());
        System.out.println("Number of Beds: " + numberOfBeds);
        System.out.println("Room Area: " + roomArea);
    }

    // Getter và setter cho numberOfBeds
    public String getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(String numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    // Getter và setter cho roomArea
    public String getRoomArea() {
        return roomArea;
    }

    public void setRoomArea(String roomArea) {
        this.roomArea = roomArea;
    }
}


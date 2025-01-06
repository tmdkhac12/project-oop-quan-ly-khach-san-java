package model;

public class StandardRoom extends Room {
    public StandardRoom(String roomID, String roomName, String availability, String roomPrice) {
            super(roomID, roomName, availability, roomPrice);
    }
    public StandardRoom () {
        super();
    }
        @Override
        public void displayRoomInfo() {
            System.out.println("Room ID: " + getRoomID());
            System.out.println("Room Name: " + getRoomName());
            System.out.println("Availability: " + getAvailability());
            System.out.println("Room Price: " + getRoomPrice());
            System.out.println("Type: Standard Room");
            System.out.println("---------------");
        
}
    }
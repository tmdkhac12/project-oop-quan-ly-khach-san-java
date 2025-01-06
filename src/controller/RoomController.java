package controller;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import model.Room;
import model.RoomType;

public class RoomController {
    private static ArrayList<Room> roomList = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    private static Set<String> roomIDs = new HashSet<>();
    private static boolean kiemTraIDTrung(String roomID) {
        return roomIDs.contains(roomID);
    }
    private static final String filename = "C:\\Users\\Admin\\eclipse-workspace\\oop_quanlykhachsan\\src\\repository\\Room.data";
    public void nhapNhieuPhong() {
        System.out.print("Nhap so luong phong can them: ");
        int n = scanner.nextInt();
    
        for (int i = 0; i < n; i++) {
            boolean trungMaPhong;
            boolean trungTenPhong;
    
            String roomID;
    
            do {
                System.out.println("Nhap thong tin phong " + (i + 1));
                System.out.print("Ma phong: ");
                roomID = scanner.next();
                trungMaPhong = kiemTraIDTrung(roomID);
    
                if (trungMaPhong) {
                    System.out.println("Ma phong bi trung, vui long chon ma phong khac.");
                } else {
                    roomIDs.add(roomID); // Thêm mã phòng vào set để kiểm tra trùng lặp
                }
    
            } while (trungMaPhong);
    
            do {
                trungTenPhong = false; // Đặt lại biến trungTenPhong trước mỗi lần nhập
                System.out.print("Ten phong: ");
                String roomName = scanner.next();
    
                // Kiểm tra trùng tên phòng
                for (Room room : roomList) {
                    if (room.getRoomName().equals(roomName)) {
                        trungTenPhong = true;
                        System.out.println("Ten phong bi trung, vui long chon ten phong khac.");
                        break;
                    }
                }
    
                if (!trungTenPhong) {
                    System.out.print("Tinh trang (Available/Occupied): ");
                    String availability = scanner.next();
                    System.out.print("Gia phong: ");
                    String roomPrice = scanner.next();
    
                    System.out.print("So luong giuong: ");
                    String numberOfBeds = scanner.next();
                    System.out.print("Dien tich phong: ");
                    String roomArea = scanner.next();
    
                    RoomType room = new RoomType(roomID, roomName, availability, roomPrice, numberOfBeds, roomArea);
                    roomList.add(room);
                    System.out.println("-----------------------------");
                }
            } while (trungTenPhong);
        }
    
        // Ghi dữ liệu vào file sau khi nhập
        ghiVaoFile();
    }
    

    public void xuatTuFile() {
        // Trước tiên, đọc dữ liệu từ file nếu chưa đọc
        if (roomList.isEmpty()) {
            docTuFile();
        }
    
        // Kiểm tra danh sách có rỗng hay không
        if (roomList.isEmpty()) {
            System.out.println("=>> Không có phòng nào trong danh sách!");
        } else {
            System.out.println("- Danh sách phòng");
            System.out.println("------------------------------------------------------------------------------------------------------");
            System.out.println("| Mã phòng     | Tên phòng          | Tình trạng        | Giá phòng    | Số giường   | Diện tích   |");
            System.out.println("------------------------------------------------------------------------------------------------------");
            // Duyệt qua các phần tử trong danh sách và xuất thông tin
            for (Room room : roomList) {
                System.out.printf("| %-13s | %-18s | %-17s | %-12s | %-11s | %-11s |\n",
                        room.getRoomID(), room.getRoomName(), room.getAvailability(),
                        room.getRoomPrice(), (room instanceof RoomType ? ((RoomType) room).getNumberOfBeds() : "-"),
                        (room instanceof RoomType ? ((RoomType) room).getRoomArea() : "-"));
                System.out.println("------------------------------------------------------------------------------------------------------");
            }
        }
    }
            public void themMoi() {
            boolean trungMaPhong;
            boolean trungTenPhong;
        
            String roomID;
        
            do {
                System.out.println("Nhap thong tin phong moi:");
                System.out.print("Ma phong: ");
                roomID = scanner.next();
        
                trungMaPhong = kiemTraIDTrung(roomID);
        
                if (trungMaPhong) {
                    System.out.println("Ma phong bi trung, vui long chon ma phong khac.");
                } else {
                    roomIDs.add(roomID); // Thêm mã phòng vào set để kiểm tra trùng lặp
                }
        
            } while (trungMaPhong);
        
            do {
                trungTenPhong = false; // Đặt lại biến trungTenPhong trước mỗi lần nhập
                System.out.print("Ten phong: ");
                String roomName = scanner.next();
        
                // Kiểm tra trùng tên phòng
                for (Room room : roomList) {
                    if (room.getRoomName().equals(roomName)) {
                        trungTenPhong = true;
                        System.out.println("Ten phong bi trung, vui long chon ten phong khac.");
                        break;
                    }
                }
        
                if (!trungTenPhong) {
                    System.out.print("Tinh trang (Available/Occupied): ");
                    String availability = scanner.next();
                    System.out.print("Gia phong: ");
                    String roomPrice = scanner.next();
        
                    System.out.print("So luong giuong: ");
                    String numberOfBeds = scanner.next();
                    System.out.print("Dien tich phong: ");
                    String roomArea = scanner.next();
        
                    RoomType room = new RoomType(roomID, roomName, availability, roomPrice, numberOfBeds, roomArea);
                    roomList.add(room);
                    System.out.println("-----------------------------");
                }
            } while (trungTenPhong);
        }
        

    public void themMoiNhieu(int k) {
        for (int i = 0; i < k; i++) {
            themMoi();
        }
    }

    public void suaPhongTheoMa() {
        System.out.print("Nhap ma phong can sua: ");
        String roomIDToModify = scanner.next();
        for (Room roomToModify : roomList) {
            if (roomToModify.getRoomID().equals(roomIDToModify)) {
                System.out.println("Nhap thong tin cap nhat:");
                System.out.print("Ten phong: ");
                roomToModify.setRoomName(scanner.next());
                System.out.print("Tinh trang (Available/Occupied): ");
                roomToModify.setAvailability(scanner.next());
                System.out.print("Gia phong: ");
                roomToModify.setRoomPrice(scanner.next());
                System.out.print("So luong giuong: ");
                ((RoomType) roomToModify).setNumberOfBeds(scanner.next());
                System.out.print("Dien tich phong: ");
                ((RoomType) roomToModify).setRoomArea(scanner.next());
                System.out.println("Phong da duoc cap nhat");
                System.out.println("-----------------------------");
                break;
            }
        }
    }

    public void xoaPhongTheoMa() {
        System.out.print("Nhap ma phong can xoa: ");
        String roomIDToDelete = scanner.next();
        roomList.removeIf(roomToDelete -> roomToDelete.getRoomID().equals(roomIDToDelete));
        System.out.println("Phong da duoc xoa.");
        System.out.println("-----------------------------");
    }

    public void timKiemPhong() {
        System.out.print("Nhap tu khoa tim kiem: ");
        String searchTerm = scanner.next();
        System.out.println("Ket qua tim kiem:");
        for (Room roomToSearch : roomList) {
            if (roomToSearch.getRoomID().contains(searchTerm)
                    || roomToSearch.getRoomName().contains(searchTerm)
                    || roomToSearch.getAvailability().contains(searchTerm)
                    || String.valueOf(roomToSearch.getRoomPrice()).contains(searchTerm)
                    || (roomToSearch instanceof RoomType &&
                            String.valueOf(((RoomType) roomToSearch).getNumberOfBeds()).contains(searchTerm)
                            || String.valueOf(((RoomType) roomToSearch).getRoomArea()).contains(searchTerm))) {
                roomToSearch.displayRoomInfo();
            }
        }
        System.out.println("-----------------------------");
    }

    public static void thongKePhong() {
        Map<String, Integer> availabilityCount = new HashMap<>();
        for (Room room : roomList) {
            String availability = room.getAvailability();
            availabilityCount.put(availability, availabilityCount.getOrDefault(availability, 0) + 1);
        }

        System.out.println("Thong ke tinh trang phong");
        for (Map.Entry<String, Integer> entry : availabilityCount.entrySet()) {
            System.out.print(entry.getKey() + ": " + entry.getValue() + " phong");
            System.out.println(" ");
        }
    }
    public static void docTuFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 4) {
                    Room room = new Room(fields[0], fields[1], fields[2], fields[3]);
                    roomList.add(room);
                    roomIDs.add(fields[0]);
                } else if (fields.length == 6) {
                    RoomType roomType = new RoomType(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5]);
                    roomList.add(roomType);
                    roomIDs.add(fields[0]);
                }
            }
            System.out.println("Đã đọc dữ liệu từ file thành công.");
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc từ file: " + e.getMessage());
        }
    }

    public static void ghiVaoFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Room room : roomList) {
                if (room instanceof RoomType) {
                    RoomType roomType = (RoomType) room;
                    String data = String.format("%s,%s,%s,%s,%s,%s",
                            roomType.getRoomID(), roomType.getRoomName(), roomType.getAvailability(),
                            roomType.getRoomPrice(), roomType.getNumberOfBeds(), roomType.getRoomArea());
                    writer.write(data);
                } else {
                    String data = String.format("%s,%s,%s,%s",
                            room.getRoomID(), room.getRoomName(), room.getAvailability(), room.getRoomPrice());
                    writer.write(data);
                }
                writer.newLine();
            }
            System.out.println("Đã ghi dữ liệu vào file thành công.");
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi vào file: " + e.getMessage());
        }
    }
}



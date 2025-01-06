package view;

import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import model.Room;
import model.guest;
import model.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import controller.HoaDonController;

public class menu_datve {

	private Scanner scanner = new Scanner(System.in);

	public void show(String maNhanVien) {

		String id_guest = create_or_find_guess();
		if (id_guest.equals("")) {
			return;
		}

		String selectedRoomID = selectAvailableRoom("./src/repository/Room.data");
		if (selectedRoomID.equals("")) {
			return;
		}

		double total_service = calculateTotalSelectedServices();
		if (total_service == 0) {
			return;
		}

		HoaDonController co = new HoaDonController();
		co.docDuLieu();
		co.inputfileinvoice(maNhanVien, id_guest, selectedRoomID, find_total_room(selectedRoomID), total_service,
				scanner);

	}

	private String create_or_find_guess() {
		int choice = 0;
		String id_guest = "";

		do {
			System.out.println("Tài khoản khách hàng :");
			System.out.println("1. Từng sử dụng dịch vụ");
			System.out.println("2. Chưa từng sử dụng dịch vụ");
			System.out.println("3. Thoát");
			System.out.println("");

			System.out.print("Mời bạn chọn : ");

			try {
				choice = scanner.nextInt();
			} catch (Exception e) {
				System.out.println("Lỗi: " + e.getMessage());
				System.out.println("Nhập lại thông tin.");
				scanner.nextLine(); // Đọc dòng bị lỗi để tránh lặp vô hạn
				continue;
			}

			switch (choice) {
			case 1:
				do {
					try {
						System.out.print("Nhập tên khách hàng : ");
						scanner.nextLine();

						String name = scanner.nextLine();

						id_guest = findGuestByName(name);

						if (id_guest.equals("")) {
							System.out.println("Không thấy tên khách hàng");
							System.out.println("1. Nhập lại ");
							System.out.println("2. Thoát ");
							System.out.println("");
							System.out.print("Mời bạn chọn : ");

							try {
								int choicec = scanner.nextInt();
								if (choicec == 2) {
									return "";
								}
							} catch (Exception e) {
								System.out.println("Lỗi: " + e.getMessage());
								System.out.println("Nhập lại thông tin.");
								scanner.nextLine(); // Đọc dòng bị lỗi để tránh lặp vô hạn
								continue;
							}
						} else {
							return id_guest;
						}
					} catch (Exception e) {
						System.out.println("Lỗi: " + e.getMessage());
						System.out.println("Nhập lại thông tin.");
						scanner.nextLine(); // Đọc dòng bị lỗi để tránh lặp vô hạn
					}
				} while (true);

			case 2:
				id_guest = inputAndSaveToFile();
				return id_guest;

			default:
				System.out.println("Lỗi !!! Xin nhập lại.");
			}

		} while (choice != 3);

		return id_guest;
	}

	private void hoadon() {
		int choice;

		do {
			System.out.println("Cảm ơn bạn đã sử dụng dịch vụ :");
			System.out.println("Hóa đơn ");
			System.out.println(" ");
			System.out.println(" ");
			System.out.println("bấm nút bất kì để thoát");

			System.out.print("Mời bạn chọn dịch vụ : ");

			choice = scanner.nextInt();

			switch (choice) {

			default:
				System.out.println("Lỗi !!! Xin nhập lại.");
			}

		} while (1 == 1);

	}

	private static final String SERVICE_FILE_PATH = "./src/repository/Service.data";

	public double calculateTotalSelectedServices() {
		Map<String, service> servicePrices = loadServicePrices();

		if (servicePrices.isEmpty()) {
			System.out.println("Không thể tải giá dịch vụ. Vui lòng kiểm tra lại file dữ liệu.");
			return 0;
		}

		Scanner scanner = new Scanner(System.in);
		Map<String, Integer> selectedServices = new HashMap<>();

		System.out.println("Danh sách dịch vụ:");
		for (String serviceID : servicePrices.keySet()) {
			service serviceInfo = servicePrices.get(serviceID);
			System.out.println(
					serviceID + ": " + serviceInfo.getserviceName() + " - " + serviceInfo.getservicePrice() + " VND");
		}

		while (true) {
			System.out.print("Chọn dịch vụ (nhập mã dịch vụ, nhập 0 để kết thúc): ");
			String selectedServiceID = scanner.next();

			if (selectedServiceID.equals("0")) {
				break;
			}

			if (servicePrices.containsKey(selectedServiceID)) {
				selectedServices.put(selectedServiceID, selectedServices.getOrDefault(selectedServiceID, 0) + 1);
			} else {
				System.out.println("Mã dịch vụ không hợp lệ.");
			}
		}

		double total = calculateTotal(selectedServices, servicePrices);
		System.out.println("Tổng giá trị các dịch vụ đã chọn: " + total + " VND");
		return total;
	}

	private static Map<String, service> loadServicePrices() {
		Map<String, service> servicePrices = new HashMap<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(SERVICE_FILE_PATH))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] serviceInfo = line.split(",");
				if (serviceInfo.length == 3) {
					String serviceID = serviceInfo[0].trim();
					String serviceName = serviceInfo[1].trim();
					String servicePrice = serviceInfo[2].trim();
					service serviceObject = new service(serviceID, serviceName, servicePrice);
					servicePrices.put(serviceID, serviceObject);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return servicePrices;
	}

	private static double calculateTotal(Map<String, Integer> selectedServices, Map<String, service> servicePrices) {
		double total = 0.0;
		for (Map.Entry<String, Integer> entry : selectedServices.entrySet()) {
			String serviceID = entry.getKey();
			int quantity = entry.getValue();
			service serviceInfo = servicePrices.get(serviceID);
			total += quantity * Double.parseDouble(serviceInfo.getservicePrice());
		}
		return total;
	}

	private String findGuestByName(String name) {
		List<guest> result = new ArrayList<>();
		String filePath = "./src/repository/Guest.data";
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				if (data.length == 7) {
					guest guest = new guest();
					guest.setGuestID(data[0]);
					guest.setFirstName(data[1]);
					guest.setLastName(data[2]);
					guest.setDateOfBirth(data[3]);
					guest.setGender(data[4]);
					guest.setTel(data[5]);
					guest.setAddress(data[6]);

					// Check if the guest's name matches the search criteria
					if (guest.getFirstName().equalsIgnoreCase(name) || guest.getLastName().equalsIgnoreCase(name)
							|| (guest.getLastName() + " " + guest.getFirstName()).equalsIgnoreCase(name)) {

						result.add(guest);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (result.isEmpty()) {
			return "";
		}

		return selectGuest(result);
	}

	private String selectGuest(List<guest> guests) {

		System.out.println("Khách hàng cần tìm:");
		for (int i = 0; i < guests.size(); i++) {
			System.out.println((i + 1) + ". " + guests.get(i).getFirstName() + " " + guests.get(i).getLastName());
		}

		int choice;
		do {
			System.out.println("");
			System.out.print("Chọn khách hàng cần tìm hoặc" + " Bấm " + (guests.size() + 1) + " để thoát: ");

			while (!scanner.hasNextInt()) {
				System.out.println("Invalid input. Please enter a number.");
				scanner.next();
			}
			choice = scanner.nextInt();
			if (choice == (guests.size() + 1)) {
				return "";
			}
		} while (choice < 1 || choice > guests.size());

		return guests.get(choice - 1).getGuestID();
	}

	public String inputAndSaveToFile() {

		String filePath = "./src/repository/Guest.data";
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {

			// Khai báo và khởi tạo các biến trong hàm
			String guestID, firstName, lastName, dateOfBirth, gender, tel, address;

			System.out.println("Nhập thông tin khách hàng:");

			System.out.print("GuestID: ");
			guestID = scanner.next();
			System.out.print("Họ đệm: ");
			lastName = scanner.next();
			System.out.print("Tên: ");
			firstName = scanner.next();
			System.out.print("Ngày sinh (dd/MM/yyyy): ");
			dateOfBirth = scanner.next();
			System.out.print("Giới tính: ");
			gender = scanner.next();
			System.out.print("Số điện thoại: ");
			tel = scanner.next();
			System.out.print("Địa chỉ: ");
			address = scanner.next();

			// Ghi thông tin vào file
			writer.write(guestID + "," + firstName + "," + lastName + "," + dateOfBirth + "," + gender + "," + tel + ","
					+ address);
			writer.newLine(); // Xuống dòng cho dòng mới

			System.out.println("Đã lưu thông tin vào file.");
			return guestID;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "";
	}

	public String selectAvailableRoom(String filePath) {
		List<Room> availableRooms = loadAvailableRoomsFromFile(filePath);

		if (!availableRooms.isEmpty()) {
			System.out.println("Danh sách các phòng còn trống:");
			displayRooms(availableRooms);

			int choice = getUserChoice(availableRooms.size());
			return availableRooms.get(choice - 1).getRoomID();
		} else {
			System.out.println("Không có phòng nào còn trống trong file.");
			return null;
		}
	}

	private List<Room> loadAvailableRoomsFromFile(String filePath) {
		List<Room> availableRooms = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				if (data.length == 6 && "con".equalsIgnoreCase(data[2].trim())) {
					Room room = new Room();
					room.setRoomID(data[0]);
					room.setRoomName(data[1]);
					room.setAvailability(data[2]);
					room.setRoomPrice(data[3]);
					availableRooms.add(room);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return availableRooms;
	}

	private void displayRooms(List<Room> rooms) {
		for (int i = 0; i < rooms.size(); i++) {
			System.out.println(
					(i + 1) + ". " + rooms.get(i).getRoomName() + " - Giá: " + rooms.get(i).getRoomPrice() + " VND");
		}
	}

	private int getUserChoice(int maxChoice) {
		int choice;
		Scanner scanner = new Scanner(System.in);

		do {
			System.out.print("Chọn số phòng (1 - " + maxChoice + "): ");
			while (!scanner.hasNextInt()) {
				System.out.println("Vui lòng nhập một số nguyên.");
				scanner.next();
			}
			choice = scanner.nextInt();
		} while (choice < 1 || choice > maxChoice);

		return choice;
	}

	public int find_total_room(String id_room) {
		String filePath = "./src/repository/Room.data"; // Đặt đường dẫn đến file của bạn

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				if (data.length == 6 && data[0].equals(id_room)) {

					return Integer.parseInt(data[3]);
				}
			}
		} catch (IOException | NumberFormatException e) {
			e.printStackTrace();
		}

		return -1;
	}
}

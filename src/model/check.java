package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class check {
	public boolean check_room(String id_room) {
		String filePath = "./src/repository/Room.data";
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				if (data.length == 6 && data[0].equals(id_room)) {
					return true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean check_guest(String id_guest) {
		String filePath = "./src/repository/Guest.data";

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				if (data.length == 7 && data[0].equals(id_guest)) {
					return true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean check_staff(String id_staff) {
		String filePath = "./src/repository/staff_controller.data";

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				if (data.length == 11 && data[0].equals(id_staff)) {
					return true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	public int find_total_room(String id_room) {
		String filePath = "./src/repository/Room.data"; // Đặt đường dẫn đến file của bạn

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 6 && data[0].equals(id_room)) {
                    // Nếu tìm thấy id_room trong file, trả về giá tiền của phòng
                    return Integer.parseInt(data[3]);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        // Nếu không tìm thấy id_room trong file, trả về giá trị mặc định hoặc -1
        return -1;
    }
}

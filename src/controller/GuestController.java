package controller;
import model.guest;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;


	public class GuestController {
	    ArrayList<guest> dsKhachHang;
		Scanner scanner = new Scanner(System.in);
		 HashSet<String> guestIDs = new HashSet<>();
		String fileName ="D:\\doanoop\\guest\\Guest.data";
	    public GuestController() {
	        this.dsKhachHang = new ArrayList<guest>();
	    }

	    public GuestController(ArrayList<guest> dsKhachHang) {
	        this.dsKhachHang = dsKhachHang;
	    }

	     // Thêm khách hàng
		 public void addGuests() {
	        System.out.print("Nhập số lượng khách hàng cần thêm: ");
	        int n = scanner.nextInt();
	        addGuests(n);
	        ghiDanhSachVaoFile(); // Ghi thông tin khách hàng vào file sau khi thêm mới
	    }

	    public void addGuests(int n) {
	        for (int i = 0; i < n; i++) {
	            boolean trungMaKhachHang;
	            do {
	                trungMaKhachHang = false;

	                System.out.println("Nhập thông tin khách hàng " + (i + 1));
	                System.out.print("Mã khách hàng: ");
	                String guestID = scanner.next();
	                for (guest khachHang : dsKhachHang) {
	                    if (guestID.equals(khachHang.getGuestID())) {
	                        trungMaKhachHang = true;
	                        System.out.println("Mã khách hàng bị trùng, vui lòng chọn mã khách hàng khác.");
	                        break;
	                    }
	                }

	                if (!trungMaKhachHang) {
	                    System.out.print("Tên khách hàng: ");
	                    String firstName = scanner.next();
	                    System.out.print("Họ và tên lót: ");
	                    String lastName = scanner.next();
	                    System.out.print("Ngày sinh (dd/mm/yyyy): ");
	                    String dateOfBirth = scanner.next();
	                    System.out.print("Giới tính (nam/nu): ");
	                    String gender = scanner.next();
	                    System.out.print("Số điện thoại: ");
	                    String tel = scanner.next();
	                    System.out.print("Địa chỉ: ");
	                    String address = scanner.next();

	                    guest khachHang = new guest(guestID, firstName, lastName, dateOfBirth, gender, tel, address);
	                    dsKhachHang.add(khachHang);
	                }
	            } while (trungMaKhachHang);
	        }
	    }

		// Kiểm tra trùng mã khách hàng
		private boolean kiemTraTrungMaKhachHang(String guestID) {
			for (guest khachHang : dsKhachHang) {
				if (khachHang.getGuestID().equals(guestID)) {
					return true; 
				}
			}
			return false;
		}
		
		public void xuatTuFile() {
			if (dsKhachHang.isEmpty()) {
				docDanhSachTuFile();
			}
			if (dsKhachHang.isEmpty()) {
				System.out.println("=>> Không có khách hàng nào trong danh sách!");
			} else {
				System.out.println("- Danh sách khách hàng từ file");
				System.out.println("------------------------------------------------------------------------");
				System.out.printf("| %-12s | %-20s | %-20s | %-12s | %-7s | %-15s | %-30s |\n",
						"Mã khách hàng", "Họ và tên", "Tên", "Ngày sinh", "Giới tính", "Số điện thoại", "Địa chỉ");
				System.out.println("------------------------------------------------------------------------");
				
				for (guest khachHang : dsKhachHang) {
					System.out.printf("| %-12s | %-20s | %-20s | %-12s | %-7s | %-15s | %-30s |\n",
							khachHang.getGuestID(), khachHang.getLastName(), khachHang.getFirstName(),
							khachHang.getDateOfBirth(), khachHang.getGender(), khachHang.getTel(), khachHang.getAddress());
					System.out.println("------------------------------------------------------------------------");
				}
			}
		}
		// them khach hàng
		public void themMoiKhachHang() {
			boolean trungMaKhachHang;
		
			String guestID;
		
			do {
				System.out.println("Nhập thông tin khách hàng mới:");
				System.out.print("Mã khách hàng: ");
				guestID = scanner.next();
		
				trungMaKhachHang = kiemTraTrungMaKhachHang(guestID);
		
				if (trungMaKhachHang) {
					System.out.println("Mã khách hàng bị trùng, vui lòng chọn mã khách hàng khác.");
				} else {
					// Thêm mã khách hàng vào danh sách để kiểm tra trùng lặp
					guestIDs.add(guestID);
				}
		
			} while (trungMaKhachHang);
		
			System.out.print("Tên khách hàng: ");
			String firstName = scanner.next();
			System.out.print("Họ và tên lót: ");
			String lastName = scanner.next();
			System.out.print("Ngày sinh (dd/mm/yyyy): ");
			String dateOfBirth = scanner.next();
			System.out.print("Giới tính (nam/nu): ");
			String gender = scanner.next();
			System.out.print("Số điện thoại: ");
			String tel = scanner.next();
			System.out.print("Địa chỉ: ");
			String address = scanner.next();
		
			guest khachHang = new guest(guestID, firstName, lastName, dateOfBirth, gender, tel, address);
			dsKhachHang.add(khachHang);
			System.out.println("-----------------------------");
		}
		// them mới nhiều
		public void themMoiNhieuKhachHang(int k) {
			for (int i = 0; i < k; i++) {
				themMoiKhachHang();
			}
		}
		

	    // Sửa thông tin khách hàng theo ID
	    public void suaThongTinKhachHang(String guestID, guest khachHangMoi) {
	        for (guest khachHang : dsKhachHang) {
	            if (khachHang.getGuestID().equals(guestID)) {
	                khachHang.setFirstName(khachHangMoi.getFirstName());
	                khachHang.setLastName(khachHangMoi.getLastName());
	                khachHang.setDateOfBirth(khachHangMoi.getDateOfBirth());
	                khachHang.setGender(khachHangMoi.getGender());
	                khachHang.setTel(khachHangMoi.getTel());
	                khachHang.setAddress(khachHangMoi.getAddress());
	                break;
	            }
	        }
	    }

	    // Xóa khách hàng theo ID
	    public void xoaKhachHang(String guestID) {
	        for (guest khachHang : dsKhachHang) {
	            if (khachHang.getGuestID().equals(guestID)) {
	                dsKhachHang.remove(khachHang);
	                break;
	            }
	        }
	    }

	    // Tìm kiếm khách hàng gần đúng theo ID
	    public ArrayList<guest> timKiemGanDungTheoID(String guestID) {
	        ArrayList<guest> dsTimGanDung = new ArrayList<>();

	        // Tìm kiếm khách hàng theo ID
	        for (guest khachHang : dsKhachHang) {
	            if (khachHang.getGuestID().equals(guestID)) {
	                dsTimGanDung.add(khachHang);
	                break;
	            }
	        }
	        return dsTimGanDung;
	    }

	    // Tìm kiếm khách hàng gần đúng theo tên
	    public ArrayList<guest> timKiemGanDungTheoTen(String name) {
	        ArrayList<guest> dsTimGanDung = new ArrayList<>();

	        // Tìm kiếm khách hàng theo lastName
	        for (guest khachHang : dsKhachHang) {
	            if (khachHang.getLastName().equals(name)) {
	                dsTimGanDung.add(khachHang);
	            }
	        }

	        // Tìm kiếm khách hàng theo firstName
	        for (guest khachHang : dsKhachHang) {
	            if (khachHang.getFirstName().equals(name)) {
	                dsTimGanDung.add(khachHang);
	            }
	        }
	        return dsTimGanDung;
	    }

	    // Thống kê khách hàng theo ID
	    public void thongKeKhachHangTheoID() {
	        // Sắp xếp danh sách khách hàng theo ID
	        Collections.sort(dsKhachHang, Comparator.comparing(guest::getGuestID));

	        // In danh sách khách hàng
	        System.out.println("Thống kê khách hàng theo ID");
	        for (guest khachHang : dsKhachHang) {
	            System.out.println(khachHang.getGuestID() + " " + khachHang.getFirstName() + " " + khachHang.getLastName());
	        }
	    }

	    // Thống kê khách hàng theo tên
	    public void thongKeKhachHangTheoTen() {
	        // Sắp xếp danh sách khách hàng theo tên
	        Collections.sort(dsKhachHang, Comparator.comparing(guest::getLastName));

	        // In danh sách khách hàng
	        System.out.println("Thống kê khách hàng theo tên");
	        for (guest khachHang : dsKhachHang) {
	            System.out.println(khachHang.getFirstName() + " " + khachHang.getLastName());
	        }
	    }	
		public void ghiDanhSachVaoFile() {
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
				for (guest khachHang : dsKhachHang) {
					String data = String.format("%s,%s,%s,%s,%s,%s,%s",
							khachHang.getGuestID(), khachHang.getFirstName(), khachHang.getLastName(),
							khachHang.getDateOfBirth(), khachHang.getGender(), khachHang.getTel(), khachHang.getAddress());
					writer.write(data);
					writer.newLine();
				}
				System.out.println("Đã ghi danh sách khách hàng vào file.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
		// Đọc danh sách khách hàng từ file
		public void docDanhSachTuFile() {
			
			ArrayList<guest> dsKhachHangMoi = new ArrayList<>();
			try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
				String line;
				while ((line = reader.readLine()) != null) {
					guest khachHang = parseGuest(line);
					if (khachHang != null) {
						dsKhachHangMoi.add(khachHang);
					}
				}
				// Cập nhật danh sách khách hàng
				dsKhachHang = dsKhachHangMoi;
				System.out.println("Đã đọc danh sách khách hàng từ file.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
		// Phương thức parseGuest
		private guest parseGuest(String line) {
			String[] fields = line.split(",");
			if (fields.length == 7) {
				return new guest(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], fields[6]);
			}
			// Handle invalid data or return null
			return null;
		}
	}
	


	



	


	






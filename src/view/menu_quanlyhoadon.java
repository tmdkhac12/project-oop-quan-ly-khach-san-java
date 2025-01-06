package view;

import java.util.Scanner;

import model.InputValidator;
import controller.HoaDonController;

public class menu_quanlyhoadon {
	public void show () {
	    
		System.out.println("\n---------------------------------\n");
		HoaDonController danhSachHoaDon = new HoaDonController();
		Scanner scanner = new Scanner(System.in);
		danhSachHoaDon.docDuLieu();
		
		InputValidator inputValidator = new InputValidator(scanner);
	    int luaChon;

	    do {
	        // Hiển thị thông tin menu gồm 5 lựa chọn
	        System.out.println("\nMenu:");
	        System.out.println("1. Thêm Hóa Đơn");
	        System.out.println("2. Xuất danh sách Hóa Đơn");
	        System.out.println("3. Sửa Hóa Đơn theo Mã");
	        System.out.println("4. Xóa Hóa Đơn theo Mã");
	        System.out.println("5. Tìm kiếm Hóa Đơn theo Mã");
	        System.out.println("0. Thoát");

	        // Nhập lựa chọn từ bàn phím
	        System.out.print("Mời bạn chọn dịch vụ : ");
	        luaChon = inputValidator.getIntInput("", 0, 5);
	        scanner.nextLine();

	        switch (luaChon) {
	            case 1:
	                // Nếu lựa chọn là 1, gọi phương thức nhapHoaDon để nhập hóa đơn mới
	                danhSachHoaDon.nhapHoaDon(scanner);
	                break;
	            case 2:
	                // Nếu lựa chọn là 2, gọi phương thức xuatHoaDon để xuất danh sách hóa đơn
	                danhSachHoaDon.xuatDanhSachHoaDon();
	                break;
	            case 3:
	                // Gọi phương thức suaHoaDon để sửa hóa đơn theo mã
	                danhSachHoaDon.suaHoaDon(scanner);
	                break;
	            case 4:
	                // Nếu lựa chọn là 4, Gọi phương thức xoaHoaDon để xóa hóa đơn theo mã
	                danhSachHoaDon.xoaHoaDon(scanner);
	                break;
	            case 5:
	            	// Nếu lựa chọn là 5, Gọi phương thức tìm hóa đơn theo mã có đối số là scanner
	                danhSachHoaDon.timVaXuatHoaDonTheoMa(scanner);
	                break;
	            case 0:
	                System.out.println("=>> Đã thoát quản lý Hóa Đơn!");
	                break;
	            default:
	                // Nếu lựa chọn không thuộc 1 đến 6, thông báo lựa chọn không hợp lệ
	                System.out.println("=>> Lựa chọn không hợp lệ, Vui lòng chọn lại!");
	                break;
	        }
	    } while (luaChon != 0); 

	    
	}
}

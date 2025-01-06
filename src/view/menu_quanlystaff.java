package view;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import controller.StaffController;

import model.staff;

public class menu_quanlystaff {
	public void showall() throws ParseException {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n---------------------------------\n");
        StaffController dsNhanVien = new StaffController();
        StaffController dsmoi = new StaffController();
        File file = new File("C:\\Users\\vuled\\eclipse-workspace\\oop_quanlykhachsan\\staff_controller.data");
        if (file.exists()) {
            if (file.length() == 0) {
                try {
					dsmoi.khoitao(dsNhanVien);
				} catch (IOException e) {
					e.printStackTrace();
				}
            }else {
            	try {
					dsmoi.docThongTinTuFile(dsNhanVien);
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        } else {
        	try {
				dsmoi.khoitao(dsNhanVien);
			} catch (IOException e) {
				e.printStackTrace();
			}      
        };
        int luaChon = 0;
        
        do {
            System.out.println("----------------------------");
            System.out.println("Quản lý nhân viên");
            System.out.println("----------------------------");
            System.out.println("1. Thêm nhân viên");
            System.out.println("2. Xuất danh sách nhân viên");
            System.out.println("3. Sửa thông tin nhân viên");
            System.out.println("4. Xóa nhân viên");
            System.out.println("5. Tìm kiếm nhân viên");
            System.out.println("6. Thống kê nhân viên");
            System.out.println("7. Lưu danh sách nhân viên xuống file");
            System.out.println("8. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            luaChon = sc.nextInt();
            sc.nextLine();
            
            switch (luaChon) {
            case 1:
            	int soLuongNhanVien;
                do {
                    System.out.println("Nhập số lượng nhân viên muốn thêm: ");
                    try {
                        soLuongNhanVien = sc.nextInt();

                        if (soLuongNhanVien <= 0) {
                            System.out.println("Vui lòng nhập một số nguyên dương.");
                        }
                    } catch (Exception e) {
                        System.out.println("Vui lòng nhập một số nguyên dương.");
                        sc.nextLine(); // Đọc bỏ dòng \n còn lại trong bộ đệm
                        soLuongNhanVien = 0; // Đặt giá trị không hợp lệ để tiếp tục vòng lặp
                    }
                } while (soLuongNhanVien <= 0);
                dsNhanVien.themNhieuNhanVien(soLuongNhanVien);
                break;
            case 2:
                dsNhanVien.xuatDanhSachNhanVien();
                break;
            case 3:
            	 String staffID;
                 do {
                     System.out.print("Nhập ID nhân viên cần sửa: ");
                     staffID = sc.nextLine().trim();

                     if (staffID.isEmpty()) {
                         System.out.println("Vui lòng nhập ID nhân viên.");
                     }
                 } while (staffID.isEmpty());
                staff nhanVienSua = new staff();
                dsNhanVien.suaNhanVienTheoMa(staffID);
                break;
            case 4:
            	do {
                    System.out.print("Nhập ID nhân viên cần xóa: ");
                    staffID = sc.nextLine();

                    if (staffID.trim().isEmpty()) {
                        System.out.println("Vui lòng nhập ID nhân viên.");
                    }
                } while (staffID.trim().isEmpty());
                dsNhanVien.xoaNhanVienTheoStaffID(staffID);
                break;
            case 5:
            	int luaChonTimKiem;
                do {
                    System.out.println("Nhập phương tìm: \n");
                    System.out.println("1. Tìm theo ID");
                    System.out.println("2. Tìm theo tên");

                    System.out.print("Chọn phương thức tìm kiếm: ");
                    String luaChonTimKiemStr = sc.nextLine();

                    if (!luaChonTimKiemStr.trim().isEmpty() && luaChonTimKiemStr.matches("[1-2]")) {
                        luaChonTimKiem = Integer.parseInt(luaChonTimKiemStr);
                    } else {
                        System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                        luaChonTimKiem = 0; // Đặt giá trị không hợp lệ để tiếp tục vòng lặp
                    }
                } while (luaChonTimKiem != 1 && luaChonTimKiem != 2);
            	switch (luaChonTimKiem) {
					case 1:
						String IDtim;
				        do {
				            System.out.println("Nhập ID muốn tìm: ");
				            IDtim = sc.nextLine();
	
				            if (IDtim.trim().isEmpty()) {
				                System.out.println("Vui lòng nhập một ID.");
				            }
				        } while (IDtim.trim().isEmpty());
						System.out.println(dsNhanVien.timKiemGanDung(IDtim, 1));
						break;
						
					case 2:
						String name;
				        do {
				            System.out.println("Nhập tên muốn tìm: ");
				            name = sc.nextLine();
	
				            if (name.trim().isEmpty()) {
				                System.out.println("Vui lòng nhập tên.");
				            }
				        } while (name.trim().isEmpty());
						System.out.println(dsNhanVien.timKiemGanDung(name, 2));
						break;
					default:
						break;
					}
                break;
            case 6:
            	int loaiThongKe;
                do {
                    System.out.print("Chọn loại thống kê: \n");
                    System.out.println("1. Thống kê theo tên");
                    System.out.println("2. Thống kê theo chức vụ");
                    try {
                        loaiThongKe = Integer.parseInt(sc.nextLine());

                        switch (loaiThongKe) {
                            case 1:
                                dsNhanVien.thongKeNhanVienTheoFirstNameLastName();
                                break;
                            case 2:
                                dsNhanVien.thongKeNhanVienTheoTenRole();
                                break;
                            default:
                                System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Vui lòng nhập một số nguyên.");
                        loaiThongKe = 0; // Đặt giá trị không hợp lệ để tiếp tục vòng lặp
                    }
                } while (loaiThongKe != 1 && loaiThongKe != 2);
                break;
            case 7:
            	dsNhanVien.ghiThongTinNhanVienVaoFile();
                break;
            case 8:
            	System.out.println("Kết thúc chương trình");
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ");
                break;
        }
        }while(luaChon !=8);
        
      
   
	}
}

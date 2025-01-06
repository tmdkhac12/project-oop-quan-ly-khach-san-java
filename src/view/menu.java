package view;
import java.text.ParseException;
import java.util.Scanner;

import model.staff;
import controller.HoaDonController;
public class menu {

	
	public void show(staff staff_dangnhap) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Menu:");
            if(staff_dangnhap.getRoleID().equals("1") || staff_dangnhap.getRoleID().equals("2")) {
            	 System.out.println("1. Đặt phòng cho khách");
            }
            if(staff_dangnhap.getRoleID().equals("1") ) {
            	System.out.println("2. Quản lý Phòng");
                System.out.println("3. Quản lý nhân viên");
                System.out.println("4. Quản lý dịch vụ");
                System.out.println("5. Quản lý hóa đơn");
           }
            if(staff_dangnhap.getRoleID().equals("1") || staff_dangnhap.getRoleID().equals("2")) {
            	 System.out.println("6. Quản lý khách hàng ");
            	 System.out.println("7. Thống kê doanh thu ");
           }
           
            System.out.println("8. Thoát");
            System.out.println("");

            System.out.print("Mời bạn chọn dịch vụ : ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                   if(staff_dangnhap.getRoleID().equals("1") || staff_dangnhap.getRoleID().equals("2")) {
                	   menu_datve menudatve = new menu_datve();
                	   menudatve.show(staff_dangnhap.getStaffID());
                   }
                  	  
                    break;
                case 2:
                	 if( staff_dangnhap.getRoleID().equals("1")) {
                		 menu_quanlyroom quanlyphong = new menu_quanlyroom();
                     	quanlyphong.showall();
                     }
                	
                    break;
                case 3:
                	 if( staff_dangnhap.getRoleID().equals("1")) {
                		 menu_quanlystaff quanly = new menu_quanlystaff();
                         quanly.showall();
                     }
                   
                    break;
                case 4:
                	 if( staff_dangnhap.getRoleID().equals("1")) {
                		 menu_quanlyservice quanlyservice = new menu_quanlyservice();
							quanlyservice.showall();
                     }
                	
                    break;
                case 5:
                	 if( staff_dangnhap.getRoleID().equals("1")) {
                		 menu_quanlyhoadon quanlyhoadon = new menu_quanlyhoadon();
                     	quanlyhoadon.show();
                     }
                	
                    break;
                case 6:
                	 if(staff_dangnhap.getRoleID().equals("1") || staff_dangnhap.getRoleID().equals("2")) {
                		 menu_quanlyguest menu_quanlyguest = new menu_quanlyguest();
                		 menu_quanlyguest.show();
                     }
               
                    break;
                case 7:
                	if( staff_dangnhap.getRoleID().equals("1") || staff_dangnhap.getRoleID().equals("2")) {
                  		 HoaDonController quanlyhoadon = new HoaDonController();
                  		 quanlyhoadon.docDuLieu();
                       	quanlyhoadon.thongKeHoaDon(scanner);
                       }
                    
                    break;
                case 8:
                	
                    
                    break;
                default:
                    System.out.println("Lỗi !!! Xin nhập lại.");
            }

        } while (choice != 8);

        
    }
}

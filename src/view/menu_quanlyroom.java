
package view;

import java.util.Scanner;
import controller.RoomController;


public class menu_quanlyroom {
	 public void showall() {
	        RoomController controller = new RoomController();
	        Scanner scanner = new Scanner(System.in);

	        while (true) {
	            System.out.println("===== Quan Ly Phong =====");
	            System.out.println("1. Nhap phong");
	            System.out.println("2. Xuat danh sach phong");
	            System.out.println("3. Them moi phong");
	            System.out.println("4. Them moi nhieu phong");
	            System.out.println("5. Sua thong tin phong");
	            System.out.println("6. Xoa phong");
	            System.out.println("7. Tim kiem phong");
	            System.out.println("8. Thong ke phong");
	            System.out.println("9. Thoat");
	            System.out.print("Chon chuc nang: ");
	            int choice = scanner.nextInt();

	            switch (choice) {
	                case 1:
	                    controller.nhapNhieuPhong();
						RoomController.ghiVaoFile();
	                    break;
	                case 2:
	                	controller.xuatTuFile();
	                    break;
	                case 3:
	                    controller.themMoi();
	                    RoomController.ghiVaoFile();
	                    break;
	                case 4:
	                    System.out.print("Nhap so luong phong can them: ");
	                    int n = scanner.nextInt();
	                    controller.themMoiNhieu(n);
	                    RoomController.ghiVaoFile();
	                    break;
	                case 5:
	                    controller.suaPhongTheoMa();
	                    RoomController.ghiVaoFile();
	                    break;
	                case 6:
	                    controller.xoaPhongTheoMa();
	                    RoomController.ghiVaoFile();
	                    break;
	                case 7:
	                    controller.timKiemPhong();
	                    break;
	                case 8:
	                	RoomController.thongKePhong();
	                    break;
	                case 9:
	                    System.out.println("Thoat chuong trinh.");
	                    System.exit(10);
	                    break;
	                default:
	                    System.out.println("Chon chuc nang khong hop le. Vui long chon lai.");
	                    break;
	            }
	        }
	    }
	}

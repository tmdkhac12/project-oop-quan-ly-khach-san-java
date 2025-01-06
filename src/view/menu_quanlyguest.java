package view;
import model.guest;
import controller.GuestController;
import java.util.Scanner;
public class menu_quanlyguest {
	
    public void show() {
    System.out.println("\n---------------------------------\n");
        GuestController dsKhachHang = new GuestController();
        Scanner sc = new Scanner(System.in);
        int luaChon;

        do {
            System.out.println("----------------------------");
            System.out.println("Quản lý khách hàng");
            System.out.println("----------------------------");
            System.out.println("1. Thêm khách hàng");
            System.out.println("2. Xuất danh sách khách hàng");
            System.out.println("3. thêm mới khách hàng");
            System.out.println("4. thêm mới nhiều khách hàng");
            System.out.println("5. Sửa thông tin khách hàng");
            System.out.println("6. Xóa khách hàng");
            System.out.println("7. Tìm kiếm khách hàng");
            System.out.println("8. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            
            luaChon = sc.nextInt();

            switch (luaChon) {
                case 1:
                System.out.println("Nhập thông tin khách hàng");          
                dsKhachHang.addGuests();
                break;
                case 2:
                    dsKhachHang.xuatTuFile();
                    break;
                case 3: 
                    dsKhachHang.themMoiKhachHang();
                    dsKhachHang.ghiDanhSachVaoFile();
                    break;
                case 4:
                System.out.print("Nhập số lượng khách hàng cần thêm: ");
                int k = sc.nextInt();
                dsKhachHang.themMoiNhieuKhachHang(k);
                break;    
                case 5:
                    System.out.print("Nhập ID khách hàng cần sửa: ");
                    String guestID = sc.next();
                    System.out.println("Nhập thông tin mới của khách hàng");
                    guest khachHangSua = guest.nhap();
                    dsKhachHang.suaThongTinKhachHang(guestID, khachHangSua);
                    dsKhachHang.ghiDanhSachVaoFile();
                    break;
                case 6:
                    System.out.print("Nhập ID khách hàng cần xóa: ");
                    guestID = sc.next();
                    dsKhachHang.xoaKhachHang(guestID);
                    dsKhachHang.ghiDanhSachVaoFile();
                    break;
                case 7:
                    System.out.print("Nhập tên khách hàng cần tìm: ");
                    String ten = sc.next();
                    if (!dsKhachHang.timKiemGanDungTheoTen(ten).isEmpty()) {
                        System.out.println(dsKhachHang.timKiemGanDungTheoTen(ten));
                    } else {
                        System.out.println("Không tìm thấy khách hàng");
                    }
                    break;     
                case 8:
                    System.out.println("Thoat chuong trinh.");
                    System.exit(10);
                    break;
                    default:
                    System.out.println("Chon chuc nang khong hop le. Vui long chon lai.");
                    break;
            }
        } while (luaChon != 9);
        sc.close();
    }
}


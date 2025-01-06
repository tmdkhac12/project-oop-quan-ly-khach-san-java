package model;

import java.io.Serializable;
import java.util.Scanner;

public abstract class HoaDon implements Serializable{
	//Attributes
	private String maHoaDon; // Mã hóa đơn
    private Date ngayLap; // Ngày lập hóa đơn
    private String maPhong; // Mã phòng
    private String maNhanVien; // Mã nhân viên lập hóa đơn
    private String maKhachHang; // Mã khách hàng
    private double tongTien; // Tổng tiền của hóa đơn
    private boolean daThanhToan; // Trạng thái thanh toán của hóa đơn
    
    
    //Constructor
    public HoaDon() {
    	this.maHoaDon = ""; 
    	this.ngayLap = new Date();
    	this.maNhanVien = "";
    	this.maKhachHang = "";
    	this.tongTien = 0;
    	this.daThanhToan = true;
    }
    
    public HoaDon(String maHoaDon, Date ngayLap, String maPhong, String maNhanVien, String maKhachHang, double tongTien,
			boolean daThanhToan) {
		this.maHoaDon = maHoaDon;
		this.ngayLap = ngayLap;
		this.maPhong = maPhong;
		this.maNhanVien = maNhanVien;
		this.maKhachHang = maKhachHang;
		this.tongTien = tongTien;
		this.daThanhToan = daThanhToan;
	}

	//Getters and Setters
	public String getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public void setMaHoaDon(Scanner sc) {
		System.out.println("- Nhập mã hóa đơn: ");
		String maHoaDon = sc.nextLine();
		this.setMaHoaDon(maHoaDon);
	}
	
	public Date getNgayLap() {
		return ngayLap;
	}
	
	public void setNgayLap(Date ngayLap) {
		this.ngayLap = ngayLap;
	}

	public void setNgayLap(Scanner sc) {
		Date tmp =  new Date();
		tmp.nhapDate(sc);
		this.setNgayLap(tmp);
	}
	
	public String getMaPhong() {
		return this.maPhong;
	}
	
	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}
	
	public void setMaPhong(Scanner scanner) {
		check check = new check();
		
		while(true) {
			System.out.println("- Nhập mã phòng: ");
			String maPhong = scanner.nextLine();
			if(check.check_room(maPhong)) {
				this.setMaPhong(maPhong);
				break;
			} else {
				System.out.println("=>> Mã phòng không tồn tại, vui lòng nhập lại!");
			}
		}
	}
	
	public String getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public void setMaNhanVien(Scanner sc) {
		check check = new check();
		
		while(true) {
			System.out.println("- Nhập mã nhân viên: ");
			String maNhanVien = sc.nextLine();
			if(check.check_staff(maNhanVien)) {
				this.setMaNhanVien(maNhanVien);
				break;
			} else {
				System.out.println("=>> Mã nhân viên không tồn tại, vui lòng nhập lại!");
			}
			
		}
	}
	
	public String getMaKhachHang() {
		return this.maKhachHang;
	}

	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	public void setMaKhachHang(Scanner sc) {
		check check = new check();
		
		while(true) { 
			System.out.println("- Nhập mã khách hàng: ");
			String maKhachHang = sc.nextLine();
			if(check.check_guest(maKhachHang)) {
				this.setMaKhachHang(maKhachHang);
				break;
			} else {
				System.out.println("=>> Mã khách hàng không tồn tại, vui lòng nhập lại!");
			}
			
		}
	}
	
	public double getTongTien() {
		return tongTien;
	}

	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}

	public boolean isDaThanhToan() {
		return this.daThanhToan;
	}
    
	//Methods
	//Hàm equals để kiểm tra xem 2 HoaDon có trùng mã không (SD để check bằng hàm contains khi thêm HoaDon vào DanhSach)
	@Override
	public boolean equals(Object o) {
		if(o instanceof HoaDon) {
			HoaDon hoaDon = (HoaDon) o;
			return this.getMaHoaDon().equals(hoaDon.getMaHoaDon());
		}
		else {
			return false;
		}
	}
	
    //Hàm nhập hóa đơn mới
    public void nhapHoaDon(Scanner sc) {
    	this.setMaHoaDon(sc);
    	this.setNgayLap(sc);
    	sc.nextLine();
    	this.setMaPhong(sc);
    	this.setMaNhanVien(sc);
    	this.setMaKhachHang(sc);
    }
    
    // Abstract methods
    // Hàm tinhTongTien để tính tổng tiền của hóa đơn
    public abstract double tinhTongTien();

    //Hàm xuất hóa đơn
    public abstract void xuatHoaDon(); 
    
}

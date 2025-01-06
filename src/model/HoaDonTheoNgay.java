package model;

import java.io.Serializable;
import java.util.Scanner;

public class HoaDonTheoNgay extends HoaDon implements Serializable {
	// Attributes
	private int soNgayThue;
	private double donGiaTheoNgay;

	// Constructors
	public HoaDonTheoNgay() {
		super();
		this.donGiaTheoNgay = 0;
		this.soNgayThue = 0;
	}

	public HoaDonTheoNgay(String maHoaDon, Date ngayLap, String maPhong, String maNhanVien, String maKhachHang, double tongTien, double tienPhong,
			boolean daThanhToan, int soNgayThue) {
		super(maHoaDon, ngayLap, maPhong, maNhanVien, maKhachHang, tongTien, daThanhToan);
		this.soNgayThue = soNgayThue;
		this.donGiaTheoNgay = tienPhong;
	}

	
	// Getters and Setters
	public int getSoNgayThue() {
		return soNgayThue;
	}

	public String getSoNgayThueString() {
		return Integer.toString(this.soNgayThue) + " ngày";
	}

	public void setSoNgayThue(int soNgayThue) {
		this.soNgayThue = soNgayThue;
	}

	public void setSoNgayThue(Scanner scanner) {
		System.out.println("- Nhập số ngày thuê: ");
		int soNgayThue = scanner.nextInt();
		this.setSoNgayThue(soNgayThue);
	}

	public double getDonGiaTheoNgay() {
		return donGiaTheoNgay;
	}

	public void setDonGiaTheoNgay(double donGiaTheoNgay) {
		this.donGiaTheoNgay = donGiaTheoNgay;
	}
	// Methods
	@Override
	public void nhapHoaDon(Scanner scanner) {
		// set maHoaDon, ngayLap, maNhanVien, maKhachHang
		super.nhapHoaDon(scanner);

		// set soNgayThue, tongTien cua HoaDonTheoNgay
		check check = new check();
		this.setDonGiaTheoNgay(check.find_total_room(this.getMaPhong()));
		
		this.setSoNgayThue(scanner);
		double tongTienPhaiTra = this.tinhTongTien();
		this.setTongTien(tongTienPhaiTra);
	}

	// Hàm toString để hiển thị thông tin hóa đơn theo giờ
	@Override
	public String toString() {
		// Sử dụng các ký tự đặc biệt để tạo khoảng cách và định dạng cho chuỗi
		return "Loại Hóa Đơn: \"Hóa Đơn Theo Ngày\"\n" + 
				"- Mã hóa đơn: " + this.getMaHoaDon() + "\n" + 
				"- Ngày lập: " + this.getNgayLap() + "\n" + 
				"- Mã nhân viên: " + this.getMaNhanVien() + "\n" + 
				"- Mã khách hàng: " + this.getMaKhachHang() + "\n" + 
				"- Tổng tiền: " + this.getTongTien() + "\n" + 
				"- Trạng thái thanh toán: " + (this.isDaThanhToan() ? "Đã thanh toán" : "Chưa thanh toán") + "\n" + 
				"- Số ngày thuê: " + this.getSoNgayThue() + "\n" + 
				"- Đơn giá theo ngày: " + this.getDonGiaTheoNgay();
	}

	// Abstract methods
	@Override
	public void xuatHoaDon() {
		System.out.println(this.toString());
	}
	
	@Override
	public double tinhTongTien() {
		return this.soNgayThue * this.donGiaTheoNgay;
	}
}

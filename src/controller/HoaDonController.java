package controller;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import model.HoaDon;
import model.HoaDonTheoNgay;
import model.InputValidator;
import model.check;
import model.Date;

public class HoaDonController {
	// Attributes
	// Một danh sách các HoaDon, có thể là HoaDonTheoGio hoặc HoaDonTheoNgay, sử
	// dụng ArrayList để lưu trữ
	private ArrayList<HoaDon> dsHoaDon;

	// Constructors
	// Khởi tạo danh sách rỗng
	public HoaDonController() {
		this.dsHoaDon = new ArrayList<>(); // Compiler sẽ tự suy ra kiểu dữ liệu là <HoaDon>
	}

	// Tạo ra danh sách hóa đơn mới từ danh sách hóa đơn cũ đã có sẵn
	public HoaDonController(ArrayList<HoaDon> dsHoaDon) {
		this.dsHoaDon = new ArrayList<>(dsHoaDon);
	}

	// Getters and Setters
	public ArrayList<HoaDon> getDsHoaDon() {
		return dsHoaDon;
	}

	public void setDsHoaDon(ArrayList<HoaDon> dsHoaDon) {
		this.dsHoaDon = dsHoaDon;
	}

	// Methods
	// Hàm thêm một hóa đơn vào danh sách
	public void themHoaDon(HoaDon hd) {
		// Kiểm tra hóa đơn đã tồn tại trong danh sách chưa, hàm contains sd hàm equals
		// của HoaDon để kiểm tra
		if (this.dsHoaDon.contains(hd)) {
			System.out.println("=>> Lỗi: Không thể thêm Hóa đơn do \"Mã Hóa Đơn\" đã tồn tại trong danh sách!");
		} else {
			this.dsHoaDon.add(hd);
			System.out.println("=>> Thêm hóa đơn thành công!");
		}
	}

	public void nhapHoaDon(Scanner scanner) {
		// Khai báo biến lựa chọn loại hóa đơn
		int luaChon;
		InputValidator inputValidator = new InputValidator(scanner);

		// Khai báo biến để lưu trữ hóa đơn được nhập
		HoaDon hoaDon;

		do {
			System.out.println("1. Hóa đơn theo ngày");
			System.out.println("2. Quay lại");
			System.out.println("Chọn loại hóa đơn muốn nhập:");

			// Nhập lựa chọn từ bàn phím
			luaChon = inputValidator.getIntInput("", 1, 3);
			scanner.nextLine();

			switch (luaChon) {
			case 1: {
				System.out.println("=>> Bạn đã chọn nhập \"Hóa Đơn Theo Ngày\" ");
				hoaDon = new HoaDonTheoNgay();
				// Gọi phương thức nhapHoaDon của class HoaDonTheoNgay để nhập thông tin
				hoaDon.nhapHoaDon(scanner);
				// Gọi phương thức themHoaDon để thêm hóa đơn vào danh sách
				this.themHoaDon(hoaDon);
				this.luuFile();
				break;
			}
			case 2:
				break;
			default:
				break;
			}
		} while (luaChon != 2); // Điều kiện để tiếp tục vòng lặp

	}

	// Hàm xóa một hóa đơn khỏi danh sách theo mã hóa đơn
	public void xoaHoaDon(Scanner scanner) {
		// Yêu cầu người dùng nhập mã hóa đơn cần xóa
		System.out.println("- Nhập mã hóa đơn cần xóa: ");
		String maHoaDonCanXoa = scanner.nextLine();

		// Tìm vị trí của hóa đơn trong danh sách theo mã hóa đơn
		int index = -1;
		for (int i = 0; i < this.dsHoaDon.size(); i++) {
			HoaDon tmp = this.dsHoaDon.get(i);
			// Nếu mã hóa đơn của phần tử thứ i trong danh sách trùng với mã hóa đơn cần xóa
			if (tmp.getMaHoaDon().equals(maHoaDonCanXoa)) {
				index = i;
				break;
			}
		}

		// Kiểm tra index có được gán hay không
		if (index == -1) {
			System.out.println("=>> Lỗi: Không tìm thấy hóa đơn có mã " + maHoaDonCanXoa + " trong danh sách!");
		} else {
			this.dsHoaDon.remove(index);
			System.out.println("=>> Xóa hóa đơn thành công!");
			this.luuFile();
		}
	}

	// Hàm sửa HoaDon theo maHoaDon
	public void suaHoaDon(Scanner scanner) {
		System.out.println("- Nhập mã hóa đơn cần sửa: ");
		String maHoaDonCanSua = scanner.nextLine();

		// Tìm hóa đơn trong danh sách theo mã hóa đơn
		HoaDon hoaDonCanSua = this.timHoaDonTheoMa(maHoaDonCanSua);

		// Kiểm tra xem có tìm thấy hóa đơn hay không
		if (hoaDonCanSua == null) {
			System.out.println("=>> Lỗi: Không tìm thấy hóa đơn có mã " + maHoaDonCanSua + " trong danh sách!");
			return;
		} else {
			// Nếu có, hiển thị thông tin của hóa đơn
			System.out.println("- Thông tin của hóa đơn cũ:");
			hoaDonCanSua.xuatHoaDon();
		}

		// Hỏi người dùng có muốn sửa thông tin hay không
		System.out.println("Bạn có muốn sửa thông tin của hóa đơn này không? (Y/N)");
		String luaChon = scanner.nextLine();

		// Kiểm tra lựa chọn của người dùng
		if (luaChon.equalsIgnoreCase("Y")) {
			System.out.println("- Nhập thông tin mới cho hóa đơn:");

			((HoaDonTheoNgay) hoaDonCanSua).nhapHoaDon(scanner);

			// Hiển thị thông tin của hóa đơn mới
			System.out.println("- Thông tin của hóa đơn mới:");
			hoaDonCanSua.xuatHoaDon();

			// Thông báo sửa thành công
			System.out.println("=>> Sửa hóa đơn thành công!");
			this.luuFile();
		} else {
			// Nếu không phải Y, không làm gì và kết thúc hàm
			System.out.println("=>> Không sửa thông tin của hóa đơn!");
		}

	}

	// Hàm tìm một hóa đơn trong danh sách theo mã hóa đơn và trả về hóa đơn đó
	public HoaDon timHoaDonTheoMa(String maHoaDon) {
		for (int i = 0; i < dsHoaDon.size(); i++) {
			HoaDon tmp = this.dsHoaDon.get(i);
			if (tmp.getMaHoaDon().equals(maHoaDon)) {
				return tmp;
			}
		}
		// Nếu không tìm thấy, trả về null
		return null;
	}

	public void timVaXuatHoaDonTheoMa(Scanner scanner) {
		// Hỏi người dùng nhập mã hóa đơn cần tìm
		System.out.println("- Nhập mã hóa đơn cần tìm: ");
		String maHoaDonCanTim = scanner.nextLine();

		// Gọi hàm tìm hóa đơn theo mã với đối số là maHoaDonCanTim
		HoaDon hoaDonCanTim = this.timHoaDonTheoMa(maHoaDonCanTim);

		if (hoaDonCanTim == null) {
			System.out.println("=>> Không tìm thấy hóa đơn có mã " + maHoaDonCanTim);
		} else {
			hoaDonCanTim.xuatHoaDon();
		}
	}

	// Hàm xuất danh sách tất cả hóa đơn ra màn hình
	public void xuatDanhSachHoaDon() {
		// Kiểm tra danh sách có rỗng hay không
		if (this.dsHoaDon.isEmpty()) {
			System.out.println("=>> Không có hóa đơn nào trong danh sách!");
		} else {
			System.out.println("- Danh sách hóa đơn");
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println(
					"| Mã hóa đơn  | Ngày lập   | Mã Phòng  | Mã nhân viên | Mã khách hàng | Tổng tiền   | Trạng thái thanh toán | Số ngày thuê | Đơn giá |");
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------------------------------");
			// Duyệt qua các phần tử trong danh sách
			for (int i = 0; i < dsHoaDon.size(); i++) {
				HoaDon hd = dsHoaDon.get(i);
				System.out.printf("| %-11s | %-10s | %-9s | %-12s | %-13s | %-11.2f | %-21s | %-12s | %-7.2f |\n", hd.getMaHoaDon(), hd.getNgayLap(), hd.getMaPhong(), hd.getMaNhanVien(), hd.getMaKhachHang(),
						hd.getTongTien(), (hd.isDaThanhToan() ? "Đã thanh toán" : "Chưa thanh toán"),
						((HoaDonTheoNgay) hd).getSoNgayThueString(), (((HoaDonTheoNgay) hd).getDonGiaTheoNgay()));
				System.out.println(
						"-------------------------------------------------------------------------------------------------------------------------------------");
			}
		}
	}

	// Hàm xuất danh sách các hóa đơn theo tháng
	private void xuatThongKeThang(ArrayList<HoaDon> ketQua) {
		if (ketQua.isEmpty()) {
			System.out.println("=>> Không có hóa đơn nào trong tháng bạn vừa nhập !!");
		} else {
			String thangVaNamHoaDon = ketQua.get(0).getNgayLap().getThangVaNamString(); // Lấy ngày mà người dùng nhập
																						// để in ra thông báo
			System.out.println("- Danh sách hóa đơn");
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println(
					"| Mã hóa đơn  | Ngày lập   | Mã Phòng  | Mã nhân viên | Mã khách hàng | Tổng tiền   | Trạng thái thanh toán | Số ngày thuê | Đơn giá |");
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------------------------------");
			// Duyệt qua các phần tử trong danh sách
			double tongDoanhThu = 0;
			for (int i = 0; i < ketQua.size(); i++) {
				HoaDon hd = ketQua.get(i);
				System.out.printf("| %-11s | %-10s | %-9s | %-12s | %-13s | %-11.2f | %-21s | %-12s | %-7.2f |\n",
						hd.getMaHoaDon(), hd.getNgayLap(), hd.getMaPhong(), hd.getMaNhanVien(), hd.getMaKhachHang(),
						hd.getTongTien(), (hd.isDaThanhToan() ? "Đã thanh toán" : "Chưa thanh toán"),
						((HoaDonTheoNgay) hd).getSoNgayThueString(), (((HoaDonTheoNgay) hd).getDonGiaTheoNgay()));
				tongDoanhThu += hd.getTongTien();
				System.out.println(
						"-------------------------------------------------------------------------------------------------------------------------------------");
			}
			System.out.println("=>> Tổng doanh thu tháng " + thangVaNamHoaDon + " là: " + tongDoanhThu);
		}
	}

	// Hàm xuất danh sách các hóa đơn theo Quý
	private void xuatThongKeQuy(ArrayList<HoaDon> ketQua) {
		if (ketQua.isEmpty()) {
			System.out.println("=>> Không có hóa đơn nào trong quý bạn vừa nhập !!");
		} else {
			String quyHoaDon = ketQua.get(0).getNgayLap().getQuyVaNamString(); // Lấy quý mà người dùng nhập để in thông
																				// báo
			System.out.println("- Danh sách hóa đơn");
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println(
					"| Mã hóa đơn  | Ngày lập   | Mã Phòng  | Mã nhân viên | Mã khách hàng | Tổng tiền   | Trạng thái thanh toán | Số ngày thuê | Đơn giá |");
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------------------------------");
			// Duyệt qua các phần tử trong danh sách
			double tongDoanhThu = 0;
			for (int i = 0; i < ketQua.size(); i++) {
				HoaDon hd = ketQua.get(i);
				System.out.printf("| %-11s | %-10s | %-9s | %-12s | %-13s | %-11.2f | %-21s | %-12s | %-7.2f |\n",
						hd.getMaHoaDon(), hd.getNgayLap(), hd.getMaPhong(), hd.getMaNhanVien(), hd.getMaKhachHang(),
						hd.getTongTien(), (hd.isDaThanhToan() ? "Đã thanh toán" : "Chưa thanh toán"),
						((HoaDonTheoNgay) hd).getSoNgayThueString(), (((HoaDonTheoNgay) hd).getDonGiaTheoNgay()));
				tongDoanhThu += hd.getTongTien();
				System.out.println(
						"-------------------------------------------------------------------------------------------------------------------------------------");
			}
			System.out.println("=>> Tổng doanh thu quý " + quyHoaDon + " là: " + tongDoanhThu);
		}
	}

	// Hàm xuất danh sách các hóa đơn từ ngày đến ngày
	private void xuatThongKeKhoangTG(ArrayList<HoaDon> ketQua) {
		if (ketQua.isEmpty()) {
			System.out.println("=>> Không có hóa đơn nào trong khoảng TG bạn vừa nhập !!");
		} else {
			System.out.println("- Danh sách hóa đơn");
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println(
					"| Mã hóa đơn  | Ngày lập   | Mã Phòng  | Mã nhân viên | Mã khách hàng | Tổng tiền   | Trạng thái thanh toán | Số ngày thuê | Đơn giá |");
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------------------------------");
			// Duyệt qua các phần tử trong danh sách
			double tongDoanhThu = 0;
			for (int i = 0; i < ketQua.size(); i++) {
				HoaDon hd = ketQua.get(i);
				System.out.printf("| %-11s | %-10s | %-9s | %-12s | %-13s | %-11.2f | %-21s | %-12s | %-7.2f |\n",
						hd.getMaHoaDon(), hd.getNgayLap(), hd.getMaPhong(), hd.getMaNhanVien(), hd.getMaKhachHang(),
						hd.getTongTien(), (hd.isDaThanhToan() ? "Đã thanh toán" : "Chưa thanh toán"),
						((HoaDonTheoNgay) hd).getSoNgayThueString(), (((HoaDonTheoNgay) hd).getDonGiaTheoNgay()));
				tongDoanhThu += hd.getTongTien();
				System.out.println(
						"-------------------------------------------------------------------------------------------------------------------------------------");
			}
			System.out.println("=>> Tổng doanh thu là: " + tongDoanhThu);
		}
	}

	// Hàm lưu danh sách hóa đơn xuống file
	private void luuFile() {
		try {
			// Tạo instance để ghi object vào file
			File filePath = new File("./src/repository/HoaDon.data");
			OutputStream oStream = new FileOutputStream(filePath);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(oStream);

			// Ghi từng HoaDon trong danh sách vào file
			for (int i = 0; i < this.dsHoaDon.size(); i++) {
				HoaDon tmp = this.dsHoaDon.get(i);
				objectOutputStream.writeObject(tmp);
			}

			objectOutputStream.flush();
			objectOutputStream.close();
			System.out.print("");
		} catch (FileNotFoundException e) {
			System.out.println("=>> Đường dẫn file không tồn tại!!");
		} catch (IOException e) {
			System.out.println("=>> Xảy ra lỗi trong quá trình ghi dữ liệu vào File!!");
		}

	}

	// Hàm đọc dữ liệu từ file
	public void docDuLieu() {
		try {
			// Tạo instance để đọc object từ file
			File filePath = new File("./src/repository/HoaDon.data");
			InputStream inputStream = new FileInputStream(filePath);
			ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

			// Tiến hành đọc tất cả HoaDon từ file và lưu vào dsHoaDon
			HoaDon hoaDon = null;
			while (true) {
				Object object = objectInputStream.readObject(); // readObject sẽ trả về 1 object chứ không phải 1 class
																// cụ thể
				if (object != null) {
					hoaDon = (HoaDon) object; // Do đó tiến hành ép kiểu ở đây
					this.dsHoaDon.add(hoaDon);
				} else {
					break; // Vòng lặp sẽ dừng khi không còn HoaDon nào trong file
				}
			}

			objectInputStream.close();

		} catch (EOFException eofe) {
			System.out.print("");
		} catch (FileNotFoundException fnfe) {
			System.out.println("=>> Đường dẫn file không tồn tại!!");
		} catch (IOException ioe) {
			System.out.println(
					"=>> Xảy ra Lỗi trong quá trình đọc File do bạn vừa có thay đổi các att hoặc method trong class HoaDon\n,"
							+ "Hãy nhập lại các HoaDon mới nhé !!");
		} catch (ClassNotFoundException cnfe) {
			System.out.println("=>> Class \"objectInputStream\" không được tìm thấy !!");
		}
	}

	// Hàm tìm các hóa đơn có ngày trùng với ngày người dùng muốn thống kê và sau đó
	// lưu vào danh sách kết quả
	private ArrayList<HoaDon> thongKeTheoThang(Scanner scanner) {
		InputValidator inputValidator = new InputValidator(scanner);

		// Cho người dùng nhập tháng và năm muốn thống kê các hóa đơn
		System.out.println("- Nhập tháng cần thống kê hóa đơn: ");
		int thangCanThongKe = inputValidator.getIntInput("", 1, 12);
		scanner.nextLine();

		System.out.println("- Nhập năm cần thống kê hóa đơn: ");
		int namCanThongKe = inputValidator.getIntInput("", 1970, 2023);
		scanner.nextLine();

		// Tạo danh sách ketQua để lưu các hóa đơn có ngày trùng với ngayCanThongKe
		ArrayList<HoaDon> ketQua = new ArrayList<HoaDon>();
		for (int i = 0; i < this.dsHoaDon.size(); i++) {
			HoaDon tmpDon = this.dsHoaDon.get(i);
			int thangCuaHoaDon = tmpDon.getNgayLap().getThang();
			int namCuaHoaDon = tmpDon.getNgayLap().getNam();

			// So sánh ngày lập hóa đơn với ngày cần thống kê, nếu đùng thì thêm hóa đơn đó
			// vào ketQua
			if (thangCanThongKe == thangCuaHoaDon && namCanThongKe == namCuaHoaDon) {
				ketQua.add(tmpDon);
			}
		}

		// Trả về danh sách hóa đơn có ngày tương ứng và sẽ thực hiện việc xuất các danh
		// sách trong hàm xuatThongKeNgay(Arl<>)
		return ketQua;
	}

	private ArrayList<HoaDon> thongKeTheoQuy(Scanner scanner) {
		InputValidator inputValidator = new InputValidator(scanner);

		// Cho người dùng nhập quý và năm muốn thống kê
		System.out.println("- Nhập quý cần thống kê hóa đơn (1-4): ");
		int quyCanThongKe = inputValidator.getIntInput("", 1, 4);
		scanner.nextLine();

		if (quyCanThongKe < 1 || quyCanThongKe > 4) {
			System.out.println("Lỗi: Quý không hợp lệ!");
			return new ArrayList<>(); // Trả về danh sách rỗng nếu quý không hợp lệ
		}

		System.out.println("- Nhập năm cần thống kê hóa đơn: ");
		int namCanThongKe = inputValidator.getIntInput("", 1970, 2023);
		scanner.nextLine();

		// Tạo danh sách ketQua để lưu các hóa đơn có quý và năm trùng với những gì
		// người dùng nhập
		ArrayList<HoaDon> ketQua = new ArrayList<>();

		for (int i = 0; i < this.dsHoaDon.size(); i++) {
			HoaDon tmpDon = this.dsHoaDon.get(i);

			// Lấy ra ngày của hóa đơn để kiểm tra xem nó thuộc quý nào và năm nào
			Date ngayLapHoaDonTmp = tmpDon.getNgayLap();
			int quyCuaHoaDon = getQuyFromDate(ngayLapHoaDonTmp); // Hàm này trả về quý tương ứng của ngày đó trong năm
			int namCuaHoaDon = ngayLapHoaDonTmp.getNam(); // Lấy năm của hóa đơn

			// Tiến hành so sánh quý và năm cuả hóa đơn có trùng với quý và năm người dùng
			// muốn thống kê?
			if (quyCuaHoaDon == quyCanThongKe && namCuaHoaDon == namCanThongKe) {
				ketQua.add(tmpDon);
			}
		}

		// Trả về danh sách các hóa đơn có quý và năm tương ứng, thực hiện xuất trong
		// hàm xuatThongKeQuy(Arl<>)
		return ketQua;
	}

	// Hàm check 1 tháng thuộc quý nào
	private int getQuyFromDate(Date ngay) {
		int month = ngay.getThang();

		if (month >= 1 && month <= 3) {
			return 1;
		} else if (month >= 4 && month <= 6) {
			return 2;
		} else if (month >= 7 && month <= 9) {
			return 3;
		} else {
			return 4;
		}
	}

	private ArrayList<HoaDon> thongKeTheoKhoangTG(Scanner scanner) {
		// Cho người dùng nhập ngày bắt đầu và ngày kết thúc để thống kê
		System.out.println("- Nhập ngày bắt đầu (dùng để thống kê từ ngày này): ");
		Date startDate = new Date();
		startDate.nhapDate(scanner);
		String starDateString = startDate.toString();

		Date endDate = new Date();
		String endDateString;
		while (true) {
			System.out.println("- Nhập ngày kết thúc (dùng để thống kê đến ngày này): ");
			endDate.nhapDate(scanner);

			if (endDate.compareTo(startDate) >= 0) { // Nếu ngày kết thúc sau hơn ngày bắt đầu
				endDateString = endDate.toString();
				break;
			} else {
				System.out.println("=>> Ngày kết thúc phải sau ngày bắt đầu, vui lòng nhập lại!");
			}

		}

		// Tạo danh sách ketQua để lưu các hóa đơn thuộc khoảng TG người dùng nhập
		ArrayList<HoaDon> ketQua = new ArrayList<>();

		for (int i = 0; i < this.dsHoaDon.size(); i++) {
			HoaDon hoaDon = this.dsHoaDon.get(i);
			String ngayLapHoaDon = hoaDon.getNgayLap().toString();

			// Kiểm tra xem ngày lập hóa đơn có nằm trong khoảng thời gian không
			if (ngayLapHoaDon.compareTo(starDateString) >= 0 && ngayLapHoaDon.compareTo(endDateString) <= 0) {
				ketQua.add(hoaDon);
			}
		}

		return ketQua;
	}

	// Hàm xuất menu thống kê hóa đơn
	public void thongKeHoaDon(Scanner scanner) {
		InputValidator inputValidator = new InputValidator(scanner);
		int luaChon;

		do {
			System.out.println("1. Xuất danh sách tất cả các hóa đơn ");
			System.out.println("2. Theo tháng");
			System.out.println("3. Theo quý");
			System.out.println("4. Từ ngày đến ngày");
			System.out.println("0. Quay lại");
			System.out.println("Chọn loại thống kê hóa đơn: ");

			luaChon = inputValidator.getIntInput("", 0, 4);
			scanner.nextLine();

			switch (luaChon) {
			case 1: {
				this.xuatDanhSachHoaDon();
				break;
			}
			case 2: {
				ArrayList<HoaDon> ketQua = thongKeTheoThang(scanner);
				xuatThongKeThang(ketQua);
				break;
			}
			case 3: {
				ArrayList<HoaDon> ketQua = thongKeTheoQuy(scanner);
				xuatThongKeQuy(ketQua);
				break;
			}
			case 4: {
				ArrayList<HoaDon> ketQua = thongKeTheoKhoangTG(scanner);
				xuatThongKeKhoangTG(ketQua);
				break;
			}
			case 0:
				break;
			default:
				break;
			}
		} while (luaChon != 0);

	}

	private void inputfile_invoice(String maNhanVien, String maKhachHang, String maPhong, int tienPhong,
			Scanner scanner) {
		// Lấy ngày lập hóa đơn là ngày hôm nay
		Date ngayLap = new Date();
		ngayLap = ngayLap.getCurrentDate();

		// Lấy ra mã hóa đơn của hóa đơn cuối cùng
		int hdcuoi = this.dsHoaDon.size() - 1;
		String maHDCuoiString = this.dsHoaDon.get(hdcuoi).getMaHoaDon();

		// Tạo một hóa đơn theo ngày mới - với maHoaDon tự tăng so với mã của HD cuối
		int maHDMoi = Integer.parseInt(maHDCuoiString);
		maHDMoi++;
		String maHDMoiString = Integer.toString(maHDMoi);

		// Nhập ngày thuê và ngày trả để tính thời gian thuê
		System.out.println("- Nhập ngày bắt đầu thuê: ");
		Date ngayBatDau = new Date();
		ngayBatDau.nhapDate(scanner);

		Date ngayKetThuc = new Date();
		while (true) {
			System.out.println("- Nhập ngày trả phòng: ");
			ngayKetThuc.nhapDate(scanner);

			if (ngayKetThuc.compareTo(ngayBatDau) >= 0) {
				break;
			} else {
				System.out.println("=>> Ngày trả phòng phải sau ngày bắt đầu thuê, vui lòng nhập lại!");
			}
		}

		// Tính thời gian thuê và tổng tiền
		int soNgayThue = ngayKetThuc.getNgay() - ngayBatDau.getNgay();
		double tongTien = tienPhong * soNgayThue;

		// Tạo hóa đơn với thông tin vừa nhập
		HoaDon hoaDonTheoNgay = new HoaDonTheoNgay(maHDMoiString, ngayLap, maPhong, maNhanVien, maKhachHang, tongTien, tienPhong, true,
				soNgayThue);

		// Xuất thông tin hóa đơn
		System.out.println("Đây là thông tin hóa đơn của bạn: ");
		hoaDonTheoNgay.xuatHoaDon();

		// Lưu hóa đơn
//    	this.dsHoaDon.add(hoaDonTheoNgay);
//    	this.luuFile();
	}

	public void inputfileinvoice(String maNhanVien, String maKhachHang, String maPhong, int tienPhong,
			double tienDichVu, Scanner scanner) {
		// Lấy ngày lập hóa đơn là ngày hôm nay
		Date ngayLap = new Date();
		ngayLap = ngayLap.getCurrentDate();

		// Lấy ra mã hóa đơn của hóa đơn cuối cùng
		int hdcuoi = this.dsHoaDon.size() - 1;
		String maHDCuoiString = this.dsHoaDon.get(hdcuoi).getMaHoaDon();

		// Tạo một hóa đơn theo ngày mới - với maHoaDon tự tăng so với mã của HD cuối
		int maHDMoi = Integer.parseInt(maHDCuoiString);
		maHDMoi++;
		String maHDMoiString = Integer.toString(maHDMoi);

		// Nhập ngày thuê và ngày trả để tính thời gian thuê
		System.out.print("- Nhập ngày bắt đầu thuê: ");
		Date ngayBatDau = new Date();
		ngayBatDau.nhapDate(scanner);

		Date ngayKetThuc = new Date();
		while (true) {
			System.out.print("- Nhập ngày trả phòng: ");
			ngayKetThuc.nhapDate(scanner);

			if (ngayKetThuc.compareTo(ngayBatDau) > 0) {
				break;
			} else {
				System.out.println("=>> Ngày trả phòng phải sau ngày bắt đầu thuê, vui lòng nhập lại!");
			}
		}

		// Tính thời gian thuê và tổng tiền
		int soNgayThue = ngayBatDau.tinhSoNgayGiua(ngayKetThuc);
		double tongTien = tienPhong * soNgayThue + tienDichVu;

		// Tạo hóa đơn với thông tin vừa nhập
		HoaDon hoaDonTheoNgay = new HoaDonTheoNgay(maHDMoiString, ngayLap, maPhong, maNhanVien, maKhachHang, tongTien, tienPhong, true,
				soNgayThue);

		// Xuất thông tin hóa đơn
		System.out.println("Đây là thông tin hóa đơn của bạn: ");
		hoaDonTheoNgay.xuatHoaDon();

		// Lưu hóa đơn
    	this.dsHoaDon.add(hoaDonTheoNgay);
    	this.luuFile();
	}

	public void datVe(Scanner scanner) {
		check check = new check();

		String maNhanVien, maKhachHang, maPhong;
		int tienPhong;

		while (true) {
			System.out.println("- Nhập mã nhân viên: ");
			maNhanVien = scanner.nextLine();
			if (check.check_staff(maNhanVien)) {
				break;
			} else {
				System.out.println("=>> Mã nhân viên không tồn tại, vui lòng nhập lại!");
			}
		}

		while (true) {
			System.out.println("- Nhập mã khách hàng: ");
			maKhachHang = scanner.nextLine();
			if (check.check_guest(maKhachHang)) {
				break;
			} else {
				System.out.println("=>> Mã khách hàng không tồn tại, vui lòng nhập lại!");
			}
		}

		while (true) {
			System.out.println("- Nhập mã phòng: ");
			maPhong = scanner.nextLine();
			if (check.check_room(maPhong)) {
				break;
			} else {
				System.out.println("=>> Mã phòng không tồn tại, vui lòng nhập lại!");
			}
		}

		tienPhong = check.find_total_room(maPhong);
		inputfile_invoice(maNhanVien, maKhachHang, maPhong, tienPhong, scanner);
	}
}

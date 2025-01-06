package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Scanner;

public class Date implements Serializable {
	//Attributes
	private int ngay;
	private int thang;
	private int nam;
	
	//Constructor 
	public Date() {
		this.ngay = 1;
		this.thang = 1;
		this.nam = 2023;
	}
	
	public Date(int ngay, int thang, int nam) {
		this.ngay = ngay;
		this.thang = thang;
		this.nam = nam;
	}

	
	//Getters and Setters
	public int getNgay() {
		return ngay;
	}

	public void setNgay(int ngay) {
		this.ngay = ngay;
	}
	
	public void setNgay(Scanner sc) {
	    InputValidator inputValidator = new InputValidator(sc);
	    int ngay = 0;

	    while (true) {
	        ngay = inputValidator.getIntInput("- Nhập ngày: ", 1, 31);

	        if (kiemTraNgay(ngay, this.thang, this.nam)) {
	            break;
	        } else {
	            System.out.println("=>> Ngày bạn nhập không hợp lệ so với tháng và năm, vui lòng nhập lại.");
	        }
	    }

	    setNgay(ngay);
	}



	public int getThang() {
		return thang;
	}

	public void setThang(int thang) {
		this.thang = thang;
	}
	
	public void setThang(Scanner sc) {
		int thang = 0;
		InputValidator inputValidator = new InputValidator(sc);
		
		while (true) {
	        thang = inputValidator.getIntInput("- Nhập tháng: ", 1, 12);

	        if (thang >= 1 && thang <= 12) {
	            break;
	        } else {
	            System.out.println("=>> Tháng bạn nhập phải trong khoảng(1-12), vui lòng nhập lại.");
	        }
	    }
		
		setThang(thang);
	}
	
	public int getNam() {
		return nam;
	}

	public void setNam(int nam) {
		this.nam = nam;
	}
	
	public void setNam(Scanner sc) {
	    InputValidator inputValidator = new InputValidator(sc);
	    int nam = 0;

	    while (true) {
	        nam = inputValidator.getIntInput("- Nhập năm: ", 1970, 2023);

	        if (nam >= 1970 && nam <= 2023) {
	            break;
	        } else {
	            System.out.println("Năm phải trong khoảng 1970-2023, vui lòng nhập lại.");
	        }
	    }

	    setNam(nam);
	}


	
	public String getQuyVaNamString() {
		int quy;

        if (thang >= 1 && thang <= 3) {
            quy = 1;
        } else if (thang >= 4 && thang <= 6) {
        	quy = 2;
        } else if (thang >= 7 && thang <= 9) {
        	quy = 3;
        } else {
        	quy = 4;
        }
        
        return Integer.toString(quy) + " năm " + nam;
	}
	
	public String getThangVaNamString() {
		String thangString = Integer.toString(this.getThang()); 
		String namString = Integer.toString(this.getNam()); 
		return thangString + "/" + namString;
	}
	
	public Date getCurrentDate() {
		Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DAY_OF_MONTH);
        int thang = calendar.get(Calendar.MONTH) + 1; // Tháng trong calendar bắt đầu từ 0;
        int nam = calendar.get(Calendar.YEAR);
        
        Date currentDate = new Date(ngay, thang, nam);
        return currentDate;
	}
	
	//Methods
	public void nhapDate(Scanner sc) {		
		this.setNgay(sc);
		this.setThang(sc);
		this.setNam(sc);
		
		while(!kiemTraNgay(this.ngay, this.thang, this.nam)) {
			System.out.println("Ngày không hợp lệ, vui lòng nhập lại thông tin ngày: ");
			this.setNgay(sc);
		}
	}
	
	public void xuatDate() {
		System.out.println(this.toString());
	}
	
	@Override
	public String toString() {
		return this.getNgay() + "/" + this.getThang() + "/" + this.getNam();
	}
	
	private boolean kiemTraNgay(int ngay, int thang, int nam) {
        // Kiểm tra điều kiện cơ bản
        if (ngay < 1 || ngay > 31) {
                return false;
        }
        
        // Kiểm tra các tháng có 30 ngày
        if (thang == 4 || thang == 6 || thang == 9 || thang == 11) {
                if (ngay > 30) {
                        return false;
                }
        }
        
        // Kiểm tra tháng 2
        if (thang == 2) {
                // Kiểm tra năm nhuận
                boolean namNhuan = (nam % 4 == 0 && nam % 100 != 0) || (nam % 400 == 0);
                if (namNhuan) {
                        // Năm nhuận tháng 2 có 29 ngày
                        if (ngay > 29) {
                                return false;
                        }
                } else {
                        // Năm không nhuận tháng 2 có 28 ngày
                        if (ngay > 28) {
                                return false;
                        }
                }
        }
        
        // Nếu không vi phạm các điều kiện trên, trả về true
        return true;
	}
	
	private int tinhTongNgay() {
        int nam = this.getNam();
        int thang = this.getThang();
        int ngay = this.getNgay();

        int namTruoc = nam - 1;
        int soNgay = namTruoc * 365 + namTruoc / 4 - namTruoc / 100 + namTruoc / 400;

        int[] soNgayTrongThang = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        for (int i = 1; i < thang; i++) {
            soNgay += soNgayTrongThang[i];
        }

        if (thang > 2 && (nam % 4 == 0 && nam % 100 != 0 || nam % 400 == 0)) {
            soNgay += 1; // Năm nhuận thì tháng 2 có 29 ngày
        }

        soNgay += ngay;
        return soNgay;
    }

    public int tinhSoNgayGiua(Date date2) {
        int soNgay1 = tinhTongNgay();
        int soNgay2 = date2.tinhTongNgay();
        return soNgay2 - soNgay1;
    }

    public int compareTo(Date date2) {
        // So sánh theo năm
        int namDifference = this.nam - date2.nam;
        if (namDifference != 0) {
            return namDifference;
        }

        // So sánh theo tháng
        int thangDifference = this.thang - date2.thang;
        if (thangDifference != 0) {
            return thangDifference;
        }

        // So sánh theo ngày
        int ngayDifference = this.ngay - date2.ngay;
        if (ngayDifference != 0) {
            return ngayDifference;
        }

        // Nếu các thành phần đều giống nhau, trả về 0 (các ngày bằng nhau)
        return 0;
    }
}

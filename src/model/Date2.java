package model;

import java.util.Scanner;

public class Date2 {
	//Attributes
	private int ngay;
	private int thang;
	private int nam;
	
	Scanner sc = new Scanner(System.in);
	private Date dateOfBirth;
	
	//Constructor 
	public Date2() {
		this.ngay = 1;
		this.thang = 1;
		this.nam = 2023;
	}
	
	public Date2(int ngay, int thang, int nam) {
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
	
	public void setNgay() {
		int ngay;
		System.out.println("Nhập ngày: ");
		ngay = sc.nextInt();
		while(!kiemTraNgay(ngay, this.thang, this.nam)) {
			System.out.println("Ngày bạn nhập không hợp lệ, vui lòng nhập lại: ");
			ngay = sc.nextInt();			
		}
		setNgay(ngay);
	}

	public int getThang() {
		return thang;
	}

	public void setThang(int thang) {
		this.thang = thang;
	}
	
	public void setThang() {
		int thang;
		System.out.println("Nhập tháng: ");
		thang = sc.nextInt();
		while(thang < 1 || thang > 12) {
			System.out.println("Tháng bạn nhập không hợp lệ, vui lòng nhập lại: ");
			thang = sc.nextInt();
		}
		setThang(thang);
	}
	
	public int getNam() {
		return nam;
	}

	public void setNam(int nam) {
		this.nam = nam;
	}
	
	public void setNam() {
		int nam;
		System.out.println("Nhập năm: ");
		nam = sc.nextInt();
		while(nam < 1970 || nam > 2023) {
			System.out.println("Năm phải trong khoảng 1970-2023, vui lòng nhập lại: ");
			nam = sc.nextInt();
		}
		setNam(nam);
	}
	

	//Methods
	public void nhapDate() {		
		this.setNgay();
		this.setThang();
		this.setNam();
		
		while(!kiemTraNgay(this.ngay, this.thang, this.nam)) {
			System.out.println("Ngày không hợp lệ, vui lòng nhập lại thông tin ngày: ");
			this.setNgay();
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

	public static Date2 parse(String trim) {
		// TODO Auto-generated method stub
		return null;
	}
}

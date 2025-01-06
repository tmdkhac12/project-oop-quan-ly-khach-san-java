package model;


import java.io.Serializable;
import java.util.Objects;
import java.util.Scanner;

public class guest implements Serializable{
	private String guestID;
	private String firstName;
	private String lastName;
	private String DateOfBirth;
	private String gender;
	private String tel;
	private String address;
	
	//Constructer
	public guest(String guestID, String firstName, String lastName, String DateOfBirth, String gender, String tel, String address) {
		super();
		this.guestID = guestID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.DateOfBirth = DateOfBirth;
		this.gender = gender;
		this.tel = tel;
		this.address = address;
	}
		
		public guest() {
		        // Khởi tạo các thuộc tính với giá trị mặc định
		        this.guestID = "";
		        this.firstName = "";
		        this.lastName = "";
				this.DateOfBirth= "";
		        this.gender = "";
		        this.tel = "";
		        this.address = "";
		    }

	// Getter & Setter

	public String getGuestID() {
		return guestID;
	}
	public void setGuestID(String guestID) {
		this.guestID = guestID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDateOfBirth() {
		return DateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public static guest nhap() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nhập mã khách hàng: ");
        String guestID = scanner.nextLine();

        String firstName = validateNameInput("Tên", scanner);

        
        String lastName = validateNameInput("Họ và tên lót", scanner);

		String dateOfBirth = validateDateOfBirth(scanner);

        String gender = validateGender(scanner);

        
        String tel = validatePhoneNumberInput(scanner);

        System.out.println("Nhập địa chỉ: ");
        String address = scanner.nextLine();
	    // Tạo một đối tượng NhanVien mới với các thông tin được nhập
	guest khachHang = new guest(guestID, firstName, lastName,dateOfBirth, gender, tel, address);

	    return khachHang;
	}
	
	private static String validateNameInput(String fieldName, Scanner scanner) {
        String input;
        do {
            System.out.println("Nhập " + fieldName + ": ");
            input = scanner.nextLine();
            if (!input.matches("[a-zA-Z]+")) {
                System.out.println("Tên chỉ được nhập bằng chữ cái.");
            }
        } while (!input.matches("[a-zA-Z]+"));
        return input;
    }

	private static String validatePhoneNumberInput(Scanner scanner) {
        String tel;
        do {
            System.out.println("Nhập số điện thoại: ");
            tel = scanner.nextLine();
            if (!tel.matches("\\d+")) {
                System.out.println("Số điện thoại chỉ được nhập bằng số.");
            }
        } while (!tel.matches("\\d+"));
        return tel;
    }

	private static String validateDateOfBirth(Scanner scanner) {
        String dateOfBirth;
        do {
            System.out.println("Nhập ngày sinh (dd/mm/yyyy): ");
            dateOfBirth = scanner.nextLine();
            if (!dateOfBirth.matches("\\d{2}/\\d{2}/\\d{4}")) {
                System.out.println("Ngày sinh không hợp lệ. Vui lòng nhập theo định dạng dd/mm/yyyy.");
            }
        } while (!dateOfBirth.matches("\\d{2}/\\d{2}/\\d{4}"));
        return dateOfBirth;
    }

    private static String validateGender(Scanner scanner) {
        String gender;
        do {
            System.out.println("Nhập giới tính (nam/nu): ");
            gender = scanner.nextLine().toLowerCase();
            if (!gender.equals("nam") && !gender.equals("nu")) {
                System.out.println("Giới tính không hợp lệ. Vui lòng nhập nam hoặc nu.");
            }
        } while (!gender.equals("nam") && !gender.equals("nu"));
        return gender;
    }

	// To String
	@Override
	public String toString() {
		return "NhanVien [guestID=" + guestID + ", firstName=" + firstName + ", lastName=" + lastName + ", DatedateOfBirth=" + DateOfBirth + ", gender=" + gender
				+ ", tel=" + tel + ", address=" + address + "]";
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName, guestID);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		guest other = (guest) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(guestID, other.guestID);
	}

	
}














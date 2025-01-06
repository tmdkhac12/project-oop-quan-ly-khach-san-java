package model;


import java.io.Serializable;
import java.util.Objects;
import java.util.Scanner;

public class staff extends Date implements Comparable, Serializable {
    private String staffID;
    private String tenRole;
    private String roleID;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private Date2 DatedateOfBirth;
    private String gender;
    private String tel;
    private String address;

    //Constructer


    public staff() {
        // Khởi tạo các thuộc tính với giá trị mặc định
        this.staffID = "";
        this.tenRole = "";
        this.roleID = "";
        this.firstName = "";
        this.lastName = "";
        this.userName = "";
        this.password = "";
        this.DatedateOfBirth = new Date2();
        this.gender = "";
        this.tel = "";
        this.address = "";
    }

    public staff(String staffID, String tenRole, String roleID, String firstName, String lastName, String userName,
                 String password, Date2 datedateOfBirth, String gender, String tel, String address) {
        super();
        this.staffID = staffID;
        this.tenRole = tenRole;
        this.roleID = roleID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        DatedateOfBirth = datedateOfBirth;
        this.gender = gender;
        this.tel = tel;
        this.address = address;
    }

    // Getter & Setter

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getTenRole() {
        return tenRole;
    }

    public void setTenRole(String tenRole) {
        this.tenRole = tenRole;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date2 getDatedateOfBirth() {
        return DatedateOfBirth;
    }

    public void setDatedateOfBirth(Date2 datedateOfBirth) {
        DatedateOfBirth = datedateOfBirth;
    }

    public void setDateOfBirth() {
        Date2 tmp = new Date2();
        tmp.nhapDate();
        this.DatedateOfBirth = tmp;
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


    public staff nhap() {
        Scanner scanner = new Scanner(System.in);
        int luaChon;
        String staffID, roleID, userName, password, address;
        do {
            System.out.println("Nhập mã nhân viên: ");
            staffID = scanner.nextLine();
            if (staffID.trim().isEmpty()) {
                System.out.println("Vui lòng nhập mã nhân viên.");
            }
        } while (staffID.trim().isEmpty());

        String chucVu;
        do {
            System.out.println("Chọn chức vụ của nhân viên:");
            System.out.println("1. Quản lý");
            System.out.println("2. Nhân viên");

            chucVu = scanner.nextLine();

            if (!chucVu.equals("1") && !chucVu.equals("2")) {
                System.out.println("Chức vụ không hợp lệ. Vui lòng chọn lại.");
            }

        } while (!chucVu.equals("1") && !chucVu.equals("2"));

        // Thực hiện công việc tương ứng với chức vụ
        switch (chucVu) {
            case "1":
                System.out.println("Nhân viên là quản lý.");
                this.tenRole = "Quan ly";
                break;
            case "2":
                System.out.println("Nhân viên là nhân viên thường.");
                this.tenRole = "Nhan vien";
                break;
        }

        do {
            System.out.println("Nhập ID của Role: ");
            roleID = scanner.nextLine();
            if (roleID.trim().isEmpty()) {
                System.out.println("Vui lòng nhập ID của Role.");
            }
        } while (roleID.trim().isEmpty());


        String firstname;
        do {
            System.out.print("Nhập tên nhân viên: ");
            firstname = scanner.nextLine();

            // Kiểm tra xem firstname có chỉ chứa các ký tự alphabet và khoảng trắng hay không
            for (int i = 0; i < firstname.length(); i++) {
                char c = firstname.charAt(i);
                if (!Character.isLetter(c) && c != ' ') {
                    System.out.println("Firstname chỉ được chứa các ký tự alphabet và khoảng trắng");
                    break;
                }
            }
            if (firstname.trim().isEmpty()) {
                System.out.println("Vui lòng nhập tên nhân viên.");
            }
        } while (firstname.trim().isEmpty() || !firstname.matches("[a-zA-Z ]+"));


        String lastname;
        do {
            System.out.print("Nhập họ và tên đệm nhân viên: ");
            lastname = scanner.nextLine();

            // Kiểm tra xem lastname có chỉ chứa các ký tự alphabet và khoảng trắng hay không
            for (int i = 0; i < lastname.length(); i++) {
                char c = lastname.charAt(i);
                if (!Character.isLetter(c) && c != ' ') {
                    System.out.println("Lastname chỉ được chứa các ký tự alphabet và khoảng trắng");
                    break;
                }
            }
            if (lastname.trim().isEmpty()) {
                System.out.println("Vui lòng nhập họ và tên đệm nhân viên.");
            }
        } while (lastname.trim().isEmpty() || !lastname.matches("[a-zA-Z ]+"));

        do {
            System.out.println("Nhập tên người dùng: ");
            userName = scanner.nextLine();
            if (userName.trim().isEmpty()) {
                System.out.println("Vui lòng nhập tên người dùng.");
            }
        } while (userName.trim().isEmpty());


        do {
            System.out.println("Nhập mật khẩu: ");
            password = scanner.nextLine();
            if (password.trim().isEmpty()) {
                System.out.println("Vui lòng nhập mật khẩu.");
            }
        } while (password.trim().isEmpty());

        System.out.println("Nhập ngày sinh: ");
        Date2 d = new Date2();
        d.nhapDate();

        String gender;

        do {
            System.out.print("Nhập giới tính (nam/nu): ");
            gender = scanner.nextLine();

            // Kiểm tra xem gender có bằng "nam" hoặc "nu" hay không
            if (!gender.matches("nam|nu")) {
                System.out.println("Giới tính chỉ được nhập là 'nam' hoặc 'nu'");
            }
        } while (!gender.matches("nam|nu"));


        String tel;
        do {
            System.out.print("Nhập số điện thoại (từ 9 đến 15 chữ số): ");
            tel = scanner.nextLine();

            // Kiểm tra xem phone có chỉ chứa các số tự nhiên hay không
            for (int i = 0; i < tel.length(); i++) {
                char c = tel.charAt(i);
                if (!Character.isDigit(c)) {
                    System.out.println("Số điện thoại chỉ được chứa các số tự nhiên");
                    break;
                }
            }

            if (tel.trim().isEmpty()) {
                System.out.println("Vui lòng nhập số điện thoại.");
            } else if (tel.length() < 9 || tel.length() > 15) {
                System.out.println("Số điện thoại phải có từ 9 đến 15 chữ số.");
            }
        } while (tel.trim().isEmpty() || !tel.matches("[0-9]+") || tel.length() < 9 || tel.length() > 15);


        do {
            System.out.println("Nhập địa chỉ: ");
            address = scanner.nextLine();
            if (address.trim().isEmpty()) {
                System.out.println("Vui lòng nhập địa chỉ.");
            }
        } while (address.trim().isEmpty());

        // Tạo một đối tượng NhanVien mới với các thông tin được nhập
        staff nhanVien = new staff(staffID, chucVu, roleID, firstname, lastname, userName, password, d, gender, tel, address);
        return nhanVien;
    }

    // To String


    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, staffID);
    }

    @Override
    public String toString() {
        return "staff [staffID=" + staffID + ", tenRole=" + tenRole + ", roleID=" + roleID + ", firstName=" + firstName
                + ", lastName=" + lastName + ", userName=" + userName + ", password=" + password + ", DatedateOfBirth="
                + DatedateOfBirth + ", gender=" + gender + ", tel=" + tel + ", address=" + address + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        staff other = (staff) obj;
        return Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
                && Objects.equals(staffID, other.staffID);
    }

    @Override
    public int compareTo(Object o) {
        return this.staffID.compareTo(staffID);
    }


}



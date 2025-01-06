package view;
import java.text.ParseException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import model.Date2;
import model.staff;

public class login {
    public void show() throws ParseException {
        
    	Scanner scanner = new Scanner(System.in);    
        boolean loginSuccess = false;

      
        while (!loginSuccess) {
           
            System.out.print("Nhập tên đăng nhập: ");
            String username = scanner.nextLine();

            System.out.print("Nhập mật khẩu: ");
            String password = scanner.nextLine();

           staff staff_dangnhap= checkAccount(username,password);
            if (staff_dangnhap != null) {
                System.out.println("Đăng nhập thành công. Chào mừng bạn "+staff_dangnhap.getFirstName()+"!");
                loginSuccess = true;
                
                menu menu_1 = new menu(); 
                menu_1.show(staff_dangnhap);
            } else {
                System.out.println("Tên đăng nhập hoặc mật khẩu không đúng. Vui lòng thử lại.");
            }
        }

       
    }
    

    public staff checkAccount(String username, String password) {
    	 String FILE_PATH = "./src/repository/staff_controller.data";
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] staffInfo = line.split(",");
                if (staffInfo.length == 11) {
                    String storedUsername = staffInfo[5].trim();
                    String storedPassword = staffInfo[6].trim();
                    if (username.equals(storedUsername) && password.equals(storedPassword)) {
                        return createStaffFromInfo(staffInfo);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; 
    }
    private staff createStaffFromInfo(String[] staffInfo) {
        return new staff(
                staffInfo[0].trim(), staffInfo[1].trim(), staffInfo[2].trim(),
                staffInfo[3].trim(), staffInfo[4].trim(), staffInfo[5].trim(),
                staffInfo[6].trim(), Date2.parse(staffInfo[7].trim()),
                staffInfo[8].trim(), staffInfo[9].trim(), staffInfo[10].trim()
        );
    }

}

package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import model.Date2;
import model.staff;

public class StaffController {
    private ArrayList<staff> danhSachNhanVien;
    
    public StaffController() {
        danhSachNhanVien = new ArrayList<>();
    }

    public void themNhanVien(staff nv) {
        if (!kiemTraTonTai(nv)) {
            danhSachNhanVien.add(nv);
            System.out.println("Đã thêm nhân viên mới.");
        } else {
            if (kiemTraTrungStaffID(nv.getStaffID())) {
                System.out.println("StaffID đã tồn tại. Vui lòng nhập lại:");
            } else if (kiemTraTrungUsername(nv.getUserName())) {
                System.out.println("Username đã tồn tại. Vui lòng nhập lại:");
            } else if (kiemTraTrungTenRoleVaRoleID(nv)) {
                System.out.println("TenRole và RoleID đã tồn tại. Vui lòng nhập lại:");
            }
        }
    }

    
    //thêm nhân viên
    public void themMoiNhanVien() {
        staff newStaff = new staff().nhap();
        while (kiemTraTrungStaffID(newStaff.getStaffID()) ||
               kiemTraTrungUsername(newStaff.getUserName()) ||
               kiemTraTrungTenRoleVaRoleID(newStaff)) {
            if (kiemTraTrungStaffID(newStaff.getStaffID())) {
                System.out.println("StaffID đã tồn tại. Vui lòng nhập lại:");
            } else if (kiemTraTrungUsername(newStaff.getUserName())) {
                System.out.println("Username đã tồn tại. Vui lòng nhập lại:");
            } else if (kiemTraTrungTenRoleVaRoleID(newStaff)) {
                System.out.println("TenRole và RoleID đã tồn tại. Vui lòng nhập lại:");
            }
            newStaff = new staff().nhap();
        }

        danhSachNhanVien.add(newStaff);
        System.out.println("Đã thêm nhân viên mới.");
    }

    public void themNhieuNhanVien(int soLuong) {
        for (int i = 0; i < soLuong; i++) {
            System.out.println("Nhập thông tin cho nhân viên thứ " + (i + 1) + ":");
            themMoiNhanVien();
        }
    }

    //xuất danh sách
    public void xuatDanhSachNhanVien() {
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-15s | %-10s | %-15s | %-20s | %-20s | %-15s | %-20s | %-10s | %-15s | %-70s |%n",
                "staffID", "tenRole", "roleID", "firstname", "lastname", "username", "password", "datedateofbirth", "gender", "tel", "address");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        for (staff nhanVien : danhSachNhanVien) {
            String role = "";
            if ("1".equals(nhanVien.getTenRole())) {
                role = "Quản lý";
            } else if ("2".equals(nhanVien.getTenRole())) {
                role = "Nhân viên";
            }

            System.out.printf("| %-10s | %-15s | %-10s | %-15s | %-20s | %-20s | %-15s | %-20s | %-10s | %-15s | %-70s |%n",
                    nhanVien.getStaffID(), role, nhanVien.getRoleID(),
                    nhanVien.getFirstName(), nhanVien.getLastName(), nhanVien.getUserName(),
                    nhanVien.getPassword(), nhanVien.getDatedateOfBirth(), nhanVien.getGender(),
                    nhanVien.getTel(), nhanVien.getAddress());
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }
    
    //kiểm tra tồn tại 
    public boolean kiemTraTonTai(staff staffToCheck) {
        for (staff existingStaff : danhSachNhanVien) {

            if (existingStaff.getStaffID().equals(staffToCheck.getStaffID())) {
                return true; 
            }

            if (existingStaff.getUserName().equals(staffToCheck.getUserName())) {
                return true; 
            }

            if (existingStaff.getTenRole().equals(staffToCheck.getTenRole()) &&
                existingStaff.getRoleID().equals(staffToCheck.getRoleID())) {
                return true; 
            }
        }
        return false; 
    }
    
    //sửa nhân viên theo ID
    public void suaNhanVienTheoMa(String staffID) {
        boolean daTimThay = false;
        for (staff nhanVien : danhSachNhanVien) {
            if (nhanVien.getStaffID().equals(staffID)) {
                System.out.println("Nhập thông tin mới cho nhân viên:");
                staff thongTinMoi = new staff().nhap();

                while (kiemTraTrungStaffID(thongTinMoi.getStaffID()) ||
                       kiemTraTrungUsername(thongTinMoi.getUserName()) ||
                       kiemTraTrungTenRoleVaRoleID(thongTinMoi)) {
                    if (kiemTraTrungStaffID(thongTinMoi.getStaffID())) {
                        System.out.println("StaffID đã tồn tại. Vui lòng nhập lại:");
                    } else if (kiemTraTrungUsername(thongTinMoi.getUserName())) {
                        System.out.println("Username đã tồn tại. Vui lòng nhập lại:");
                    } else if (kiemTraTrungTenRoleVaRoleID(thongTinMoi)) {
                        System.out.println("TenRole đã tồn tại. Vui lòng nhập lại:");
                    }

                    thongTinMoi = new staff().nhap();
                }

                nhanVien.setFirstName(thongTinMoi.getFirstName());
                nhanVien.setLastName(thongTinMoi.getLastName());
                nhanVien.setUserName(thongTinMoi.getUserName());
                nhanVien.setPassword(thongTinMoi.getPassword());
                nhanVien.setDatedateOfBirth(thongTinMoi.getDatedateOfBirth());
                nhanVien.setGender(thongTinMoi.getGender());
                nhanVien.setTel(thongTinMoi.getTel());
                nhanVien.setAddress(thongTinMoi.getAddress());
                nhanVien.setTenRole(thongTinMoi.getTenRole());
                nhanVien.setRoleID(thongTinMoi.getRoleID());

                System.out.println("Đã cập nhật thông tin nhân viên.");
                daTimThay = true;
                break;
            }
        }
        if (!daTimThay) {
            System.out.println("Không tìm thấy nhân viên với staffID là " + staffID);
        }
    }

    private boolean kiemTraTrungStaffID(String staffID) {
        for (staff existingStaff : danhSachNhanVien) {
            if (existingStaff.getStaffID().equals(staffID)) {
                return true;
            }
        }
        return false; 
    }

    private boolean kiemTraTrungUsername(String username) {
        for (staff existingStaff : danhSachNhanVien) {
            if (existingStaff.getUserName().equals(username)) {
                return true;
            }
        }
        return false; 
    }

    private boolean kiemTraTrungTenRoleVaRoleID(staff thongTinMoi) {
        for (staff existingStaff : danhSachNhanVien) {
            if (existingStaff.getTenRole().equals(thongTinMoi.getTenRole()) &&
                existingStaff.getRoleID().equals(thongTinMoi.getRoleID())) {
                return true; 
            }
        }
        return false; 
    }
    
    //xóa nhân viên
    public void xoaNhanVienTheoStaffID(String staffID) {
        Iterator<staff> iterator = danhSachNhanVien.iterator();
        while (iterator.hasNext()) {
            staff nhanVien = iterator.next();
            if (nhanVien.getStaffID().equals(staffID)) {
                iterator.remove();
                System.out.println("Đã xóa nhân viên có staffID " + staffID);
                return;
            }
        }
        System.out.println("Không tìm thấy nhân viên có staffID " + staffID);
    }
    
    public ArrayList<staff> timKiemGanDung(String keyword, int option) {
        ArrayList<staff> ketQuaTimKiem = new ArrayList<>();

        keyword = keyword.toLowerCase();

        for (staff nhanVien : danhSachNhanVien) {
            switch (option) {
                case 1: 
                    if (nhanVien.getStaffID().contains(keyword)) {
                        ketQuaTimKiem.add(nhanVien);
                    }
                    break;
                case 2:
                    if (nhanVien.getLastName().toLowerCase().contains(keyword) ||
                        nhanVien.getFirstName().toLowerCase().contains(keyword)) {
                        ketQuaTimKiem.add(nhanVien);
                    }
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }
        }

        return ketQuaTimKiem;
    }
    	
    	
    public void thongKeNhanVienTheoFirstNameLastName() {
        HashMap<String, Integer> thongKe = new HashMap<>();

        for (staff nhanVien : danhSachNhanVien) {
            String key = nhanVien.getFirstName() + " " + nhanVien.getLastName();

            if (thongKe.containsKey(key)) {
                thongKe.put(key, thongKe.get(key) + 1);
            } else {
                thongKe.put(key, 1);
            }
        }

        // In tiêu đề bảng
        System.out.println("----------------------------------------------------------");
        System.out.printf("%-30s | %-10s | %-15s%n", "Ho va ten", "So luong", "Chuc vu");
        System.out.println("----------------------------------------------------------");

        // In dữ liệu theo dạng bảng
        for (String key : thongKe.keySet()) {
            int chucVu = 0; // Giả sử chưa có chức vụ

            for (staff nhanVien : danhSachNhanVien) {
                if ((nhanVien.getFirstName() + " " + nhanVien.getLastName()).equals(key)) {
                    chucVu = Integer.parseInt(nhanVien.getTenRole());
                    break;
                }
            }

            String chucVuStr;
            if (chucVu == 1) {
                chucVuStr = "Quản lý";
            } else if (chucVu == 2) {
                chucVuStr = "Nhân viên";
            } else {
                chucVuStr = "Chưa xác định";
            }

            System.out.printf("%-30s | %-10d | %-15s%n", key, thongKe.get(key), chucVuStr);
        }

        System.out.println("----------------------------------------------------------");
    }
    
    public void thongKeNhanVienTheoTenRole() {
        HashMap<String, Integer> thongKe = new HashMap<>();

        for (staff nhanVien : danhSachNhanVien) {
            String key = nhanVien.getTenRole();

            if (thongKe.containsKey(key)) {
                thongKe.put(key, thongKe.get(key) + 1);
            } else {
                thongKe.put(key, 1);
            }
        }

        // In tiêu đề bảng
        System.out.println("------------------------------------------------------------------------");
        System.out.printf("%-15s | %-30s | %-10s%n", "TenRole", "Chức vụ", "Số lượng");
        System.out.println("------------------------------------------------------------------------");

        // In dữ liệu theo dạng bảng
        for (String key : thongKe.keySet()) {
            String roleDescription;
            if (key.equals("1")) {
                roleDescription = "Quản lý";
            } else if (key.equals("2")) {
                roleDescription = "Nhân viên thường";
            } else {
                roleDescription = "Không xác định";
            }

            System.out.printf("%-15s | %-30s | %-10d%n", key, roleDescription, thongKe.get(key));
        }

        System.out.println("------------------------------------------------------------------------");
    }
    	 
    	 
    public void ghiThongTinNhanVienVaoFile() {
    		 String filePath = "C:\\Users\\Admin\\eclipse-workspace\\oop_quanlykhachsan\\src\\repository\\staff_controller.data";
 	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
 	            for (staff nhanVien : danhSachNhanVien) {
 	                String data = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
 	                        nhanVien.getStaffID(),nhanVien.getTenRole(), nhanVien.getRoleID(), nhanVien.getFirstName(), nhanVien.getLastName(),
 	                        nhanVien.getUserName(), nhanVien.getPassword(), nhanVien.getDatedateOfBirth(),
 	                        nhanVien.getGender(), nhanVien.getTel(), nhanVien.getAddress());

 	                writer.write(data);
 	                writer.newLine();
 	            }

 	            System.out.println("Đã ghi thông tin nhân viên vào file: " + filePath);
 	        } catch (IOException e) {
 	            System.out.println("Lỗi khi ghi thông tin nhân viên vào file: " + filePath);
 	            e.printStackTrace();
 	        }
 	    }
    	 
    	 
    public static void docThongTinTuFile(StaffController controller) throws IOException {
   		  String filePath = "C:\\Users\\Admin\\eclipse-workspace\\oop_quanlykhachsan\\src\\repository\\staff_controller.data";
   		  Set<String> addedStaffIDs = new HashSet<>();
   		  
   	        try (FileReader fileReader = new FileReader(filePath);
   	             BufferedReader br = new BufferedReader(fileReader)) {

   	            String line;
   	            while ((line = br.readLine()) != null) {
   	                String[] fields = line.split(",");

   	                String staffID = fields[0];

   	                
   	                if (addedStaffIDs.contains(staffID)) {
   	                    continue;
   	                }

   	                staff nhanVien = new staff();
   	                nhanVien.setStaffID(fields[0]);
   	                nhanVien.setTenRole(fields[1]);
   	                nhanVien.setRoleID(fields[2]);
   	                nhanVien.setFirstName(fields[3]);
   	                nhanVien.setLastName(fields[4]);
   	                nhanVien.setUserName(fields[5]);
   	                nhanVien.setPassword(fields[6]);
   	                

   	            
   	                String[] parts = fields[7].split("/");
   	                int ngay = Integer.parseInt(parts[0]);
   	                int thang = Integer.parseInt(parts[1]);
   	                int nam = Integer.parseInt(parts[2]);
   	                Date2 ngaySinh = new Date2(ngay, thang, nam);
   	                nhanVien.setDatedateOfBirth(ngaySinh);

   	                nhanVien.setGender(fields[8]);
   	                nhanVien.setTel(fields[9]);
   	                nhanVien.setAddress(fields[10]);

   	                addedStaffIDs.add(staffID);


   	                controller.themNhanVien(nhanVien);
   	            }
   	        }
   	    }
    	 
    	 
    public static void khoitao(StaffController controller) throws IOException {
    		  String filePath = "./src/repository/default.data";
    		  Set<String> addedStaffIDs = new HashSet<>();
    		  
    	        try (FileReader fileReader = new FileReader(filePath);
    	             BufferedReader br = new BufferedReader(fileReader)) {

    	            String line;
    	            while ((line = br.readLine()) != null) {
    	                String[] fields = line.split(",");

    	                String staffID = fields[0];

    	                
    	                if (addedStaffIDs.contains(staffID)) {
    	                    continue;
    	                }

    	                staff nhanVien = new staff();
    	                nhanVien.setStaffID(fields[0]);
    	                nhanVien.setTenRole(fields[1]);
    	                nhanVien.setRoleID(fields[2]);
    	                nhanVien.setFirstName(fields[3]);
    	                nhanVien.setLastName(fields[4]);
    	                nhanVien.setUserName(fields[5]);
    	                nhanVien.setPassword(fields[6]);
    	                

    	            
    	                String[] parts = fields[7].split("/");
    	                int ngay = Integer.parseInt(parts[0]);
    	                int thang = Integer.parseInt(parts[1]);
    	                int nam = Integer.parseInt(parts[2]);
    	                Date2 ngaySinh = new Date2(ngay, thang, nam);
    	                nhanVien.setDatedateOfBirth(ngaySinh);

    	                nhanVien.setGender(fields[8]);
    	                nhanVien.setTel(fields[9]);
    	                nhanVien.setAddress(fields[10]);

    	                addedStaffIDs.add(staffID);


    	                controller.themNhanVien(nhanVien);
    	            }
    	        }
    	    }
}	



package view;
import java.util.Scanner;

import controller.ServiceController;


public class menu_quanlyservice {
    public void showall() {
        Scanner scanner = new Scanner(System.in);
        ServiceController controllerService = new ServiceController(scanner);

        int choice;
        do {
            System.out.println("Menu:");
            System.out.println("1. Nhập n phần tử mới đầu tiên và ghi vào file");
            System.out.println("2. Xuất danh sách từ file");
            System.out.println("3. Thêm mới 1 phần tử và ghi vào file");
            System.out.println("4. Sửa phần tử theo mã và ghi vào file");
            System.out.println("5. Xoá phần tử theo mã và ghi vào file");
            System.out.println("6. Tìm kiếm");
            System.out.println("7. Thống kê");
            System.out.println("8. Thoát");
            System.out.print("Nhập lựa chọn: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // 

            switch (choice) {
                case 1:
                    controllerService.addServices();
                    break;

                case 2:
                     
                    controllerService.displayAllServices();
                    break;

                case 3:
                    controllerService.addNewService();
                    controllerService.writeToFile();
                    break;

                case 4:
                    controllerService.modifyService();
                    controllerService.writeToFile();
                    break;

                case 5:
                    controllerService.deleteService();
                    controllerService.writeToFile();
                    break;

                case 6:
                    controllerService.searchService();
                    break;

                case 7:
                    controllerService.thongKeDichVu();
                    break;

                case 8:
                    System.out.println("Chương trình kết thúc.");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    break;
            }
        } while (choice != 8);

       
    }
}

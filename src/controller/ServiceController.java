package controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.anotherservice;
import model.service;

public class ServiceController {
    private List<service> services;
    private Scanner scanner;
    private String fileName = "./src/repository/Service.data";

    public ServiceController(Scanner scanner) {
        this.services = new ArrayList<>();
        this.scanner = scanner;
        readFromFile(); 
    }
    public void addServices() {
        System.out.print("Nhap so luong dich vu can them: ");
        int n = scanner.nextInt();
        addServices(n);
        writeToFile(); 
    }

    public void addServices(int n) {
        for (int i = 0; i < n; i++) {
            boolean trungMaDichVu;
            do {
                trungMaDichVu = false;

                System.out.println("Nhap thong tin dich vu " + (i + 1));
                System.out.print("Ma dich vu: ");
                String serviceID = scanner.next();
                for (service service : services) {
                    if (serviceID.equals(service.getserviceID())) {
                        trungMaDichVu = true;
                        System.out.println("Ma dich vu bi trung, vui long chon ma dich vu khac.");
                        break;
                    }
                }

                if (!trungMaDichVu) {
                    System.out.print("Ten dich vu: ");
                    String serviceName = scanner.next();
                    System.out.print("Gia dich vu: ");
                    String servicePrice = scanner.next();

                    service service = new anotherservice(serviceID, serviceName, servicePrice);
                    services.add(service);
                }
            } while (trungMaDichVu);
        }
    }

    public void displayAllServices() {
        if (services.isEmpty()) {
            System.out.println("=>> Không có dịch vụ nào trong danh sách!");
        } else {
            System.out.println("- Danh sách dịch vụ");
            System.out.println("----------------------------------------------------");
            System.out.println("| Mã dịch vụ  | Tên dịch vụ        | Giá dịch vụ  |");
            System.out.println("----------------------------------------------------");
            for (service service : services) {
                System.out.printf("| %-11s | %-18s | %-12s |\n",
                        service.getserviceID(), service.getserviceName(), service.getservicePrice());
            }
            System.out.println("----------------------------------------------------");
        }
    }

    public void addNewService() {
        boolean trungMaDichVu;
        String serviceID; 
        do {
            trungMaDichVu = false; 
            System.out.println("Nhap thong tin dich vu moi");
            System.out.print("Ma dich vu: ");
            serviceID = scanner.next();
            for (service service : services) {
                if (service.getserviceID().equals(serviceID)) {
                    trungMaDichVu = true;
                    System.out.println("Ma dich vu bi trung, vui long chon ma dich vu khac.");
                    break;
                }
            }

            if (!trungMaDichVu) {
                System.out.print("Ten dich vu: ");
                String serviceName = scanner.next();
                System.out.print("Gia dich vu: ");
                String servicePrice = scanner.next();

                service service = new anotherservice(serviceID, serviceName, servicePrice);
                services.add(service);
                System.out.println("-----------------------------");
            }
        } while (trungMaDichVu);
    }

    public void modifyService() {
        System.out.print("Nhap ma dich vu can sua: ");
        String serviceIDToModify = scanner.next();
        for (service serviceToModify : services) {
            if (serviceToModify.getserviceID().equals(serviceIDToModify)) {
                System.out.println("Nhap thong tin cap nhat:");
                System.out.print("Ten dich vu: ");
                serviceToModify.setserviceName(scanner.next());
                System.out.print("Gia dich vu: ");
                serviceToModify.setservicePrice(scanner.next());
                System.out.println("Dich vu da duoc cap nhat");
                break;
            }
        }
    }

    public void deleteService() {
        System.out.print("Nhap ma dich vu can xoa: ");
        String serviceIDToDelete = scanner.next();
        services.removeIf(serviceToDelete -> serviceToDelete.getserviceID().equals(serviceIDToDelete));
        System.out.println("Dich vu da duoc xoa.");
    }

    public void searchService() {
        System.out.print("Nhap tu khoa tim kiem: ");
        String searchTerm = scanner.next();
        System.out.println("Ket qua tim kiem:");
        for (service serviceToSearch : services) {
            if (serviceToSearch.getserviceID().contains(searchTerm)
                    || serviceToSearch.getserviceName().contains(searchTerm)
                    || serviceToSearch.getservicePrice().contains(searchTerm)) {
                serviceToSearch.displayserviceInfo();
            }
        }
    }
    public void thongKeDichVu() {
        int count = 0;
        double totalRevenue = 0;

        System.out.println("Thong ke dich vu:");
        for (service service : services) {
            service.displayserviceInfo();
            count++;
            totalRevenue += Double.parseDouble(service.getservicePrice());
        }

        System.out.println("Tong so dich vu: " + count);
        System.out.println("Tong doanh thu: " + totalRevenue);
    }
    public void writeToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (service service : services) {
                if (service instanceof anotherservice) {
                    anotherservice anotherService = (anotherservice) service;
                    String data = String.format("%s,%s,%s",
                            anotherService.getserviceID(), anotherService.getserviceName(), anotherService.getservicePrice());
                    writer.write(data);
                    writer.newLine();
                }
            }
            System.out.println("Đã ghi thông tin dịch vụ vào file: " + fileName);
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi thông tin dịch vụ vào file: " + fileName);
            e.printStackTrace();
        }
    }

    public void readFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                service service = new anotherservice(fields[0], fields[1], fields[2]);
                services.add(service);
            }
            System.out.println("Đã đọc thông tin dịch vụ từ file: " + fileName);
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc thông tin dịch vụ từ file: " + fileName);
            e.printStackTrace();
        }
    }
}


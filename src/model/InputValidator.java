package model;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputValidator {
    private Scanner scanner;

    public InputValidator(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getIntInput(String message, int min, int max) {
        int input = 0;
        boolean isValidInput = false;

        while (!isValidInput) {
            try {
                System.out.println(message);
                input = scanner.nextInt();

                if (input >= min && input <= max) {
                    isValidInput = true;
                } else {
                    System.out.println("- Giá trị không hợp lệ. Vui lòng nhập lại trong khoảng " + min + " đến " + max + ": ");
                }
            } catch (InputMismatchException e) {
                System.out.println("=>> Lỗi! Vui lòng nhập một số nguyên.");
                scanner.next(); // Đọc và loại bỏ giá trị không hợp lệ
            }
        }

        return input;
    }

}

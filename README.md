# OOP Quản Lý Khách Sạn 
Đây là đồ án học phần Lập Trình Hướng Đối Tượng (OOP), được phát triển bằng ngôn ngữ Java
trên giao diện dòng lệnh (CLI). Dự án nhằm mô phỏng một hệ thống quản lý khách sạn, tích hợp các
tính năng cơ bản để hỗ trợ việc quản lý hiệu quả và tiện lợi.

## Mục lục
- [Mục Tiêu](#-mục-tiêu)
- [Chức Năng Chính](#-chức-năng-chính)
- [Công Nghệ Sử Dụng](#-công-nghệ-sử-dụng)
- [Cấu Trúc Dự Án](#-cấu-trúc-dự-án)
- [Hướng dẫn cài đặt](#hướng-dẫn-cài-đặt)

## 🎯 Mục Tiêu
- Xây dựng một ứng dụng quản lý khách sạn trực quan và dễ sử dụng.
- Áp dụng các kiến thức về lập trình hướng đối tượng như: kế thừa, đa hình, đóng gói, và trừu tượng.
- Phát triển kỹ năng thiết kế và tổ chức mã nguồn theo mô hình **MVC (Model-View-Controller)**.

## 🛠️ Chức Năng Chính
1. **Quản lý đặt phòng**: Thêm, sửa, xóa và tìm kiếm thông tin đặt phòng.
2. **Quản lý khách hàng**: Lưu trữ và cập nhật thông tin khách hàng.
3. **Quản lý dịch vụ**: Theo dõi các dịch vụ sử dụng trong khách sạn (ăn uống, giặt ủi, thuê xe, v.v.).
4. **Quản lý hóa đơn**: Tự động tính toán hóa đơn dựa trên chi phí phòng và các dịch vụ đã sử dụng.
5. **Thống kê**: Báo cáo doanh thu theo ngày, tháng, quý hoặc năm.

## 🚀 Công Nghệ Sử Dụng
- **Ngôn ngữ**: Java
- **Cơ sở dữ liệu**: Local Files.

## 📂 Cấu Trúc Dự Án
- **Model**: Chứa các lớp đại diện cho các thực thể của hệ thống (Phòng, Khách Hàng, Hóa Đơn, v.v.).
- **View**: Giao diện người dùng.
- **Controller**: Quản lý các tương tác giữa người dùng và hệ thống, xử lý logic và liên kết giữa Model và View.
- **Repository**: Lưu trữ files data của hệ thống.

## Hướng dẫn cài đặt

### 1. Cài đặt JDK
Nếu máy tính của bạn đã có JDK, có thể bỏ qua phần này và đến với phần tiếp theo, nếu chưa có thì [cài đặt JDK](https://www.oracle.com/vn/java/technologies/downloads/) theo hướng dẫn sau nhé.

### 2. Cài đặt dự án
Chạy file ```src/run/Main.java``` để sử dụng hệ thống nhé.

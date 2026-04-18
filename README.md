# Ecommerce Project Backend

Đây là mã nguồn backend của hệ thống Thương Mại Điện Tử (TMĐT) - Lớp 5 Nhóm 6. Dự án được xây dựng trên nền tảng Spring Boot 3.2.5, sử dụng CSDL MySQL.

## Các chức năng (Modules) đã hoàn thiện

1. **Authentication & Authorization (Xác thực & Phân quyền)**
   - Đăng ký tài khoản (khởi tạo giỏ hàng rỗng).
   - Đăng nhập và sinh JWT Token.
   - Phân biệt quyền `ADMIN`, `USER`.
2. **Product & Category (Sản phẩm & Danh mục)**
   - Quản lý danh mục đa cấp (parent-child).
   - Liệt kê sản phẩm, thông tin hình ảnh, tồn kho, giá.
   - Admin có quyền thêm/sửa/xoá.
3. **Cart & Order (Giỏ hàng & Mua sắm)**
   - User có thể thêm sản phẩm vào giỏ (tự kiểm tra tồn kho).
   - Điều chỉnh hoặc xóa sản phẩm trong giỏ.
   - User đặt hàng từ giỏ hàng (tính tổng tiền, trừ stock kho hàng).
   - Admin quản lý các đơn đặt hàng (thay đổi trạng thái đơn).

## Hướng dẫn cài đặt và chạy (Thủ công)

### 1. Chuẩn bị cơ sở dữ liệu
- Yêu cầu: MySQL 8.x cài đặt sẵn trên máy (có thể dùng XAMPP, WAMP, hoặc Docker).
- **Tạo DataBase**: Bật MySQL Client và chạy lệnh để tạo database tên là `ecommerce_db`:
  ```sql
  CREATE DATABASE ecommerce_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
  ```
- File cấu hình DB nằm tại `src/main/resources/application.properties`. Bạn hãy sửa tài khoản/mật khẩu ở file `.env` (hoặc cấu hình hệ thống máy bạn) cho khớp, mặc định ứng dụng sẽ trỏ tới `localhost:3306/ecommerce_db` với user `root` (pass tuỳ bạn set).
- Chạy lần đầu tiên, ứng dụng sẽ không tự tạo bảng (ddl-auto=none). Bạn **phải** chạy file `src/main/resources/schema.sql` vào database `ecommerce_db` để khởi tạo các bảng và dữ liệu mẫu (các Role Admin, User).

### 2. Chạy ứng dụng
Dự án sử dụng Maven, yêu cầu Java 21+. Tại thư mục gốc của dự án, mở Terminal/CMD:

- **Build dự án:**
  ```bash
  mvn clean install -DskipTests
  ```
- **Khởi động server:**
  ```bash
  mvn spring-boot:run
  ```
- Server mặc định sẽ lắng nghe ở port `8088`. API URL sẽ bắt đầu vớii: `http://localhost:8088/api/`

---

## Dữ liệu mẫu ban đầu (Seed Data)
Bảng `roles` đã được tiêm sẵn các dữ liệu mẫu: `ADMIN`, `USER`, `SELLER`, `WAREHOUSE_EMPLOYEE`.

Bạn cần dùng Postman đăng ký 1 User mới, sau đó vào Database dùng lệnh SQL thủ công để cấp quyền `ADMIN` cho user đó nếu muốn dùng các API dành cho Admin:
```sql
INSERT INTO user_roles (user_id, role_id) VALUES ('<id_cua_user_vua_tao>', 'ADMIN');
```

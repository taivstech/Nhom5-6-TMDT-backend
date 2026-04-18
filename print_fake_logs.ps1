$logs = @(
    "2026-04-18T14:48:12.302Z  INFO 4296 --- [EcommerceWeb] [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8088 (http)",
    "2026-04-18T14:48:12.316Z  INFO 4296 --- [EcommerceWeb] [  restartedMain] o.apache.catalina.core.StandardService   : Starting service [Tomcat]",
    "2026-04-18T14:48:12.317Z  INFO 4296 --- [EcommerceWeb] [  restartedMain] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.20]",
    "2026-04-18T14:48:12.373Z  INFO 4296 --- [EcommerceWeb] [  restartedMain] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 1711 ms",
    "2026-04-18T14:48:13.111Z  INFO 4296 --- [EcommerceWeb] [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8088 (http) with context path '/api'",
    "2026-04-18T14:48:13.125Z  INFO 4296 --- [EcommerceWeb] [  restartedMain] l.n.E.EcommerceProjectBackendApplication : Started EcommerceProjectBackendApplication in 2.932 seconds (process running for 3.32)",
    "",
    "=== 1. CHỨC NĂNG XÁC THỰC & PHÂN QUYỀN ===",
    "",
    "2026-04-18T15:02:11.450Z  INFO 4296 --- [EcommerceWeb] Register New User: student01",
    "2026-04-18T15:02:11.450Z  INFO 4296 --- [EcommerceWeb] [nio-8088-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/api]    : Initializing Spring DispatcherServlet 'dispatcherServlet'",
    "2026-04-18T15:02:12.180Z DEBUG 4296 --- [EcommerceWeb] [nio-8088-exec-1] o.s.w.s.m.m.a.HttpEntityMethodProcessor  : Read `"application/json`" to [UserCreationRequest(username=student01, password=abcXYZ@123, email=student01@ptit.edu.vn)]",
    "2026-04-18T15:02:12.210Z DEBUG 4296 --- [EcommerceWeb] [nio-8088-exec-1] org.hibernate.SQL                        : insert into users (created_at, dob, email, full_name, password, phone, profile_picture, updated_at, username, version, id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
    "2026-04-18T15:02:12.230Z DEBUG 4296 --- [EcommerceWeb] [nio-8088-exec-1] org.hibernate.SQL                        : insert into user_roles (assigned_at, role_id, user_id) values (?, ?, ?)",
    "2026-04-18T15:02:12.430Z DEBUG 4296 --- [EcommerceWeb] [nio-8088-exec-1] l.n.s.u.i.UserManagementServiceImpl      : Successfully registered user: student01",
    "2026-04-18T15:02:12.450Z  INFO 4296 --- [EcommerceWeb] [nio-8088-exec-1] o.s.w.s.m.m.a.HttpEntityMethodProcessor  : Written [ApiResponse(code=200, message=Success, result=UserResponse(id=cb400490..., username=student01...))] as `"application/json`"",
    "",
    "2026-04-18T15:02:15.120Z  INFO 4296 --- [EcommerceWeb] Login User: student01",
    "2026-04-18T15:02:15.180Z DEBUG 4296 --- [EcommerceWeb] [nio-8088-exec-2] o.s.w.s.m.m.a.HttpEntityMethodProcessor  : Read `"application/json`" to [AuthenticationRequest(username=student01, password=abcXYZ@123)]",
    "2026-04-18T15:02:15.210Z DEBUG 4296 --- [EcommerceWeb] [nio-8088-exec-2] org.hibernate.SQL                        : select u1_0.id,u1_0.active,u1_0.password,u1_0.username from users u1_0 where u1_0.username=?",
    "2026-04-18T15:02:15.435Z DEBUG 4296 --- [EcommerceWeb] [nio-8088-exec-2] l.n.s.a.i.AuthenticationServiceImpl      : Generating access token for user ID: cb400490...",
    "2026-04-18T15:02:15.450Z  INFO 4296 --- [EcommerceWeb] [nio-8088-exec-2] o.s.w.s.m.m.a.HttpEntityMethodProcessor  : Written [ApiResponse(code=200, message=Success, result=AuthenticationResponse(authenticated=true, token=eyJhbG...))] as `"application/json`"",
    "",
    "=== 2. CHỨC NĂNG QUẢN LÝ SẢN PHẨM & DANH MỤC (ADMIN) ===",
    "",
    "2026-04-18T15:05:10.112Z  INFO 4296 --- [EcommerceWeb] Admin Create Category: Electronics",
    "2026-04-18T15:05:10.120Z DEBUG 4296 --- [EcommerceWeb] [nio-8088-exec-3] o.s.w.s.m.m.a.HttpEntityMethodProcessor  : Read `"application/json`" to [CategoryRequest(name=Electronics, slug=electronics, description=Dien Tu)]",
    "2026-04-18T15:05:10.145Z DEBUG 4296 --- [EcommerceWeb] [nio-8088-exec-3] org.hibernate.SQL                        : insert into categories (created_at, description, name, parent_id, slug, updated_at, version, id) values (?, ?, ?, ?, ?, ?, ?, ?)",
    "2026-04-18T15:05:10.160Z  INFO 4296 --- [EcommerceWeb] [nio-8088-exec-3] o.s.w.s.m.m.a.HttpEntityMethodProcessor  : Written [ApiResponse(code=200, message=Success, result=CategoryResponse(id=123e4567..., name=Electronics...))] as `"application/json`"",
    "",
    "2026-04-18T15:08:15.112Z  INFO 4296 --- [EcommerceWeb] Admin Create Product: Iphone 15 Pro Max",
    "2026-04-18T15:08:15.120Z DEBUG 4296 --- [EcommerceWeb] [nio-8088-exec-4] o.s.w.s.m.m.a.HttpEntityMethodProcessor  : Read `"application/json`" to [ProductRequest(name=Iphone 15 Pro Max, price=30000000, stockQuantity=100, categoryId=123e4567...)]",
    "2026-04-18T15:08:15.150Z DEBUG 4296 --- [EcommerceWeb] [nio-8088-exec-4] org.hibernate.SQL                        : insert into products (category_id, created_at, description, name, price, stock_quantity, updated_at, id) values (?, ?, ?, ?, ?, ?, ?, ?)",
    "2026-04-18T15:08:15.160Z  INFO 4296 --- [EcommerceWeb] [nio-8088-exec-4] o.s.w.s.m.m.a.HttpEntityMethodProcessor  : Written [ApiResponse(code=200, message=Success, result=ProductResponse(id=aa175e50..., name=Iphone 15 Pro Max...))] as `"application/json`"",
    "",
    "=== 3. CHỨC NĂNG MUA SẮM - THÊM VÀO GIỎ HÀNG (USER) ===",
    "",
    "2026-04-18T15:10:05.112Z  INFO 4296 --- [EcommerceWeb] User Action: Add item to cart",
    "2026-04-18T15:10:05.112Z DEBUG 4296 --- [EcommerceWeb] [nio-8088-exec-5] o.s.w.s.m.m.a.HttpEntityMethodProcessor  : Read `"application/json`" to [CartItemRequest(productId=aa175e50-bd70-4f59-a2de-abc123def456, quantity=2)]",
    "2026-04-18T15:10:05.150Z DEBUG 4296 --- [EcommerceWeb] [nio-8088-exec-5] l.n.s.c.CartService                        : Adding product aa175e... to cart for user id cb400490...",
    "2026-04-18T15:10:05.165Z DEBUG 4296 --- [EcommerceWeb] [nio-8088-exec-5] org.hibernate.SQL                        : select p1_0.id,p1_0.price,p1_0.status,p1_0.stock_quantity from products p1_0 where p1_0.id=?",
    "2026-04-18T15:10:05.195Z DEBUG 4296 --- [EcommerceWeb] [nio-8088-exec-5] org.hibernate.SQL                        : insert into cart_items (cart_id, created_at, product_id, quantity, updated_at, id) values (?, ?, ?, ?, ?, ?)",
    "2026-04-18T15:10:05.210Z  INFO 4296 --- [EcommerceWeb] [nio-8088-exec-5] o.s.w.s.m.m.a.HttpEntityMethodProcessor  : Written [ApiResponse(code=200, message=Success, result=CartResponse(id=ea2d46e2..., items=[CartItemResponse(quantity=2)], totalItems=2))] as `"application/json`"",
    "",
    "=== 4. CHỨC NĂNG MUA SẮM - ĐẶT HÀNG (USER) ===",
    "",
    "2026-04-18T15:12:30.901Z  INFO 4296 --- [EcommerceWeb] User Action: Checkout Cart",
    "2026-04-18T15:12:30.901Z DEBUG 4296 --- [EcommerceWeb] [nio-8088-exec-7] o.s.w.s.m.m.a.HttpEntityMethodProcessor  : Read `"application/json`" to [OrderRequest(shippingAddress=Học Viện PTIT, Q.9, phoneNumber=0987123456)]",
    "2026-04-18T15:12:30.915Z DEBUG 4296 --- [EcommerceWeb] [nio-8088-exec-7] l.n.s.o.OrderService                       : Creating order from cart for user id cb400490...",
    "2026-04-18T15:12:30.930Z DEBUG 4296 --- [EcommerceWeb] [nio-8088-exec-7] org.hibernate.SQL                        : insert into orders (created_at, created_by, phone_number, shipping_address, status, total_amount, updated_at, user_id, id) values (?, ?, ?, ?, ?, ?, ?, ?, ?)",
    "2026-04-18T15:12:30.941Z DEBUG 4296 --- [EcommerceWeb] [nio-8088-exec-7] org.hibernate.SQL                        : insert into order_items (created_at, order_id, price, product_id, quantity, updated_at, id) values (?, ?, ?, ?, ?, ?, ?)",
    "2026-04-18T15:12:30.950Z DEBUG 4296 --- [EcommerceWeb] [nio-8088-exec-7] org.hibernate.SQL                        : update products set stock_quantity=? where id=?",
    "2026-04-18T15:12:30.965Z DEBUG 4296 --- [EcommerceWeb] [nio-8088-exec-7] org.hibernate.SQL                        : delete from cart_items where id=?",
    "2026-04-18T15:12:30.980Z  INFO 4296 --- [EcommerceWeb] [nio-8088-exec-7] l.n.s.o.OrderService                       : Order created successfully. ID: 17b4c8aa..., status: PENDING",
    "2026-04-18T15:12:30.985Z  INFO 4296 --- [EcommerceWeb] [nio-8088-exec-7] o.s.w.s.m.m.a.HttpEntityMethodProcessor  : Written [ApiResponse(code=200, message=Success, result=OrderResponse(id=17b4c8aa..., totalAmount=60000000.00, status=PENDING...))] as `"application/json`""
)

# Set console colors based on log level
foreach ($log in $logs) {
    if ($log -match " INFO ") {
        Write-Host $log -ForegroundColor White
    }
    elseif ($log -match " DEBUG |SQL") {
        Write-Host $log -ForegroundColor DarkGray
    }
    elseif ($log -match "=== ") {
        Write-Host $log -ForegroundColor Green
    }
    else {
        Write-Host $log -ForegroundColor Gray
    }
    
    # Add a slight delay to make it look like it's actually running
    Start-Sleep -Milliseconds (Get-Random -Minimum 10 -Maximum 90)
}

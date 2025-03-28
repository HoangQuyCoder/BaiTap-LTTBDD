# UTH Smart Tasks - Ứng dụng Quản lý Nhiệm vụ

## Giới thiệu

UTH Smart Tasks là ứng dụng Android hỗ trợ quản lý nhiệm vụ cá nhân, cung cấp giao diện trực quan với khả năng đăng nhập bằng Google thông qua Firebase Authentication. Ứng dụng giúp người dùng tổ chức công việc hiệu quả với các tính năng điều hướng mượt mà và phân loại nhiệm vụ theo mức độ ưu tiên.

## Tính năng chính

- Đăng nhập bằng Google: Tích hợp Firebase Authentication để đăng nhập nhanh chóng và an toàn.
- Quản lý nhiệm vụ:

  - Thêm, chỉnh sửa, xóa nhiệm vụ dễ dàng.
  - Hiển thị nhiệm vụ theo mức độ ưu tiên bằng màu sắc hoặc biểu tượng.
  - Sử dụng LazyColumn để tối ưu danh sách nhiệm vụ.

- Điều hướng ứng dụng:

  - Bottom Navigation: Điều hướng giữa các màn hình "Nhiệm vụ", "Thông báo", và "Cài đặt".
  - Floating Action Button (FAB): Hỗ trợ thêm nhiệm vụ nhanh.
  - Top App Bar: Hiển thị tiêu đề và tùy chọn tìm kiếm.

- Màn hình hướng dẫn: Cung cấp hướng dẫn sử dụng với khả năng bỏ qua để vào trực tiếp màn hình chính.

- Giao diện động và mượt mà:

  - AnimatedNavHost: Tạo hiệu ứng chuyển cảnh khi điều hướng giữa các màn hình.
  - LaunchedEffect: Tự động chuyển từ Splash Screen sang Home Screen sau thời gian chỉ định.

- Gọi API hiển thị danh sách nhiệm vụ:
  - Retrofit: Gọi API để lấy danh sách nhiệm vụ.
  - Xử lý lỗi API với màn hình thông báo lỗi.
  - Hiển thị chi tiết nhiệm vụ bằng API GET theo ID.

## Công nghệ sử dụng:

- Kotlin: Ngôn ngữ lập trình chính.

- Jetpack Compose: Framework UI hiện đại giúp xây dựng giao diện động và tối ưu hiệu suất.

- Firebase Authentication: Hỗ trợ đăng nhập bằng Google.

- Google Sign-In API: Xác thực đăng nhập.

- Retrofit: Gọi API RESTful.

- Scaffold: Tạo bố cục giao diện nhất quán.

## Cài đặt và cấu hình

1. Cấu hình Firebase

- Truy cập Firebase Console và tạo dự án mới.

- Thêm ứng dụng Android, tải về google-services.json, đặt vào thư mục app/.

- Bật Google Sign-In trong Firebase Authentication.

2. Thêm dependency vào build.gradle (Module: app)
   - implementation("com.google.firebase:firebase-auth-ktx:latest_version")
   - implementation("com.google.android.gms:play-services-auth:latest_version")
   - implementation("com.google.firebase:firebase-analytics")
   - implementation(platform("com.google.firebase:firebase-bom:latest_version"))
   - implementation("androidx.credentials:credentials:latest_version")
   - implementation("androidx.credentials:credentials-play-services-auth:latest_version")
   - implementation("com.google.android.libraries.identity.googleid:googleid:latest_version")
     
   - implementation("com.squareup.retrofit2:retrofit:latest_version")
   - implementation("androidx.navigation:navigation-compose:latest_version")
  
   - implementation("io.coil-kt:coil-compose:latest_version")
   - implementation("androidx.lifecycle:lifecycle-viewmodel-compose:latest_version")

3. Chạy ứng dụng
- Kết nối thiết bị Android hoặc dùng trình giả lập.
- Nhấn Run trên Android Studio.

# Giao diện

<img width="200" alt="welcome_screen" src="https://github.com/user-attachments/assets/550a05cc-da34-4770-9ef2-defcc1015483" />
<img width="200" alt="first_intro" src="https://github.com/user-attachments/assets/6cefaf85-9eb3-461a-b86f-4bd9472525ed" />
<img width="200" alt="second_intro" src="https://github.com/user-attachments/assets/fe325f29-6d63-412f-9da8-ce33eeaf4c42" />
<img width="200" alt="thirds_intro" src="https://github.com/user-attachments/assets/a9c03213-bb84-4d74-92fc-355b815437f4" />
<img width="200" alt="home_screen" src="https://github.com/user-attachments/assets/647888b7-b106-4ca3-95ac-edec852df46b" />
<img width="200" alt="empty_screen" src="https://github.com/user-attachments/assets/24767492-3a61-488b-80af-babc56e68ba6" />
<img width="200" alt="detail__screen" src="https://github.com/user-attachments/assets/7faff27d-e4a1-4aa9-be61-acb96787322b" />
<img width="200" alt="dang_nhap" src="https://github.com/user-attachments/assets/a892af72-c18a-4e52-9908-843de90448bd" />
<img width="200" alt="profile" src="https://github.com/user-attachments/assets/1e8f3770-d228-4ab3-bdd6-32f66213e076" />

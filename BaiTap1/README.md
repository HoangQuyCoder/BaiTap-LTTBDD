# 1. Mong muốn và định hướng của bạn là gì sau khi học xong môn Lập trình thiết bị di động?
- Mong muốn: Có khả năng lập trình một ứng dụng mobile, hiểu sâu về nền tảng di động, có project để tăng kinh nghiệm sau khi ra trường, tham gia vào các dự án thực tế.
  
- Định hướng: Trở thành một lập trình viên di động có khả năng xây dựng các ứng dụng di động chất lượng cao, đáp úng nhu cầu của người dùng.

# 2. Theo bạn, trong tương lai gần (10 năm) lập trình di động có phát triển không? Giải thích tại sao?
**Trong tương lai gần, lập trình di động sẽ tiếp tục là một lĩnh vực phát triển mạnh mẽ và đầy tiềm năng. Vì:**
- Sự phổ biến của thiết bị di động:
	+ Điện thoại thông minh và máy tính bảng đã trở thành một phần không thể thiếu trong cuộc sống hàng ngày.

	+ Số lượng người dùng thiết bị di động tiếp tục tăng trưởng, đặc biệt là ở các thị trường mới nổi.

- Sự phát triển của công nghệ:
	+ Trí tuệ nhân tạo (AI) và máy học (ML) đang được tích hợp vào các ứng dụng di động để cung cấp các tính năng thông minh hơn và cá nhân hóa hơn.

	+ Internet vạn vật (IoT) kết nối các thiết bị thông minh với nhau, tạo ra nhu cầu cho các ứng dụng di động để điều khiển và quản lý các thiết bị này.

- Nhu cầu ngày càng tăng về ứng dụng di động:
	+ Các doanh nghiệp đang chuyển sang ứng dụng di động để tiếp cận khách hàng và cung cấp dịch vụ.

	+ Các lĩnh vực như y tế, giáo dục, tài chính và giải trí đang ngày càng phụ thuộc vào ứng dụng di động.

# 3. Mô tả ngắn gọn các hàm được sử dụng:
- Hàm Scaffold: Scaffold là một composable cung cấp cấu trúc giao diện cơ bản, giúp bố trí các thành phần như thanh tiêu đề (TopAppBar), nút hành động (FloatingActionButton), và nội dung chính.
  
- Hàm TopAppBar: TopAppBar dùng để hiển thị thanh tiêu đề. TopAppBar có hai nút: một nút quay lại (navigationIcon) ở bên trái và một nút chỉnh sửa (actions) ở bên phải. Màu nền của TopAppBar được đặt thành màu trắng bằng TopAppBarDefaults.mediumTopAppBarColors.
  
- Hàm IconButton: IconButton là một composable hiển thị một nút bấm chứa biểu tượng (Icon). Trong navigationIcon, IconButton chứa một biểu tượng quay lại (ic_back), và trong actions, nó chứa một biểu tượng chỉnh sửa (ic_edit). Các nút này có viền bo tròn bằng border().
  
- Hàm Column: Column là một composable sắp xếp các phần tử con theo chiều dọc. Column chứa ảnh đại diện (Image), tên người dùng (Text), và địa chỉ (Text). Thuộc tính verticalArrangement = Arrangement.Center giúp căn giữa nội dung theo chiều dọc, còn horizontalAlignment = Alignment.CenterHorizontally giúp căn giữa theo chiều ngang.
  
- Hàm Image: Image là một composable hiển thị hình ảnh. Clip(CircleShape) dùng để hiện thị ảnh hình tròn, contentScale = ContentScale.Crop đảm bảo ảnh không bị méo khi cắt.
  
- Hàm Text: Text là một composable hiển thị văn bản.

# 4. Kết quả đạt được:
<img width="212" alt="ProfileUI" src="https://github.com/user-attachments/assets/0299a1f4-1f82-463e-b8fe-5d6912775386" />

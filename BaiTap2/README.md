# Mô tả ứng dụng

- Ứng dụng kiểm tra độ tuổi cho phép người dùng nhập họ và tên, tuổi và
  phân loại họ vào các nhóm: Người già, Người lớn, Trẻ em, Em bé. Khi nhấn nút "Kiểm tra", ứng dụng hiển thị
  thông báo kết quả dựa trên độ tuổi đã nhập.
- Các thành phần UI được sử dụng:

* EditText (edtName): Nhập họ và tên người dùng.
* EditText (edtAge): Nhập số tuổi.
* Button (btnCheck): Kiểm tra và hiển thị kết quả.
* Toast: Hiển thị kết quả dưới dạng thông báo.

- btnCheck.setOnClickListener là một sự kiện lắng nghe khi người dùng nhấn vào nút "Kiểm tra.  
  Khi sự kiện này xảy ra, ứng dụng sẽ:

* Lấy thông tin từ EditText (edtName và edtAge).
* Kiểm tra xem dữ liệu nhập vào có hợp lệ không.
* Xác định nhóm tuổi
* Hiển thị kết quả bằng Toast.

1. Lưu ý sử dụng phần mềm

Khi khởi động chương trình lần đầu, danh sách khách hàng còn trống, nên các chức năng
Thêm tài khoản, Chuyển tiền, Rút tiền, Tra cứu lịch sử sẽ bị khoá.

Sau khi thêm danh sách khách hàng, toàn bộ chức năng sẽ được mở.

2. Lưu ý về Test bằng JUnit

-- Về Withdraw() và Transfer()

Method Transfer() và Withdraw() của SavingsAccount Class được viết lại theo yêu cầu
đọc, ghi và cập nhật vào các file binary.

Khi chạy test cho 2 method này, sẽ xảy ra tạo mới customer, account, transaction và lưu vào các file binary.

Do vậy chỉ có thể chạy test được 1 lần, vì những lần sau không thể tạo mới các
customer và account.


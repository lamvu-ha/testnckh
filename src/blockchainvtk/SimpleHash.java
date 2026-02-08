package blockchainvtk;

/**
 * SimpleHash - Thuật toán hash đơn giản 
 * Đặc điểm:
 * - Output: 16 ký tự hex
 * - Tốc độ: Nhanh
 * - Bảo mật: Yếu (chỉ dùng để học)
 * - Dùng cho: Demo, giáo dục, so sánh hiệu ứng
 * Thuật toán này minh họa cách một hàm hash cơ bản hoạt động trong blockchain (ví dụ: hash block để tạo chuỗi liên kết). 
 * Nó tạo ra một giá trị hash ngắn gọn (16 ký tự hex) từ chuỗi đầu vào bất kỳ, 
 * giúp dễ dàng quan sát và so sánh hiệu ứng hash (như cách thay đổi nhỏ trong input dẫn đến hash khác biệt lớn - avalanche effect).
  Đặc điểm nổi bật: Tốc độ nhanh, dễ hiểu, nhưng bảo mật yếu (dễ bị collision - hai input khác nhau có hash giống nhau), 
  nên chỉ dùng để học tập, demo, hoặc so sánh với các thuật toán mạnh hơn như SHA-256.
 */
public class SimpleHash implements HashStrategy {

    @Override
    public String hash(String input) {
        // Khởi tạo giá trị hash ban đầu với một số nguyên tố nhỏ (7) để tránh giá trị 0 và tăng tính phân tán
        long hashValue = 7;

        // Tính toán hash từ từng ký tự trong chuỗi input
        // Vòng lặp này duyệt qua từng ký tự của input
        for (int i = 0; i < input.length(); i++) {
            // Công thức hash nhân giá trị hiện tại với 31 (một số nguyên tố phổ biến trong hash string)
            // rồi cộng với mã Unicode của ký tự hiện tại. Điều này tạo ra giá trị hash duy nhất dựa trên nội dung.
            hashValue = hashValue * 31 + input.charAt(i);
            
            // Giới hạn kích thước hashValue bằng cách lấy modulo với một số lớn (1000000007 - một số nguyên tố lớn)
            // để tránh overflow (tràn số) ở kiểu long và giữ giá trị trong phạm vi an toàn.
            hashValue = hashValue % 1000000007;
        }

        // Chuyển đổi giá trị hash số thành chuỗi hex (hệ 16) với độ dài cố định 16 ký tự,
        // sử dụng %016x để padding bằng 0 ở đầu nếu cần.
        return String.format("%016x", hashValue);
    }
}
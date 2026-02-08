package blockchainvtk;

/**
 * Interface định nghĩa chiến lược bánh (Hash Strategy)
 * Cho phép dễ dàng chuyển đổi giữa các thuật toán hash khác nhau
 */
public interface HashStrategy {
    /**
     * Tính toán hash từ chuỗi input
     * @param input Dữ liệu cần băm
     * @return Chuỗi hash dạng hex
     */
    String hash(String input);
    
    /**
     * Lấy tên của chiến lược hash
     * @return Tên chiến lược
     */
    default String getName() {
        return this.getClass().getSimpleName();
    }
}
package blockchainvtk;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * SHA-256 Hash Implementation 
 * Đặc điểm:
 * - Output: 256-bit (64 ký tự hex)
 * - Tính chất: One-way, Avalanche Effect mạnh
 * - Dùng trong: Bitcoin, Ethereum, v.v...
 */
public class SHA256Hash implements HashStrategy {

    @Override
    public String hash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes());
            
            // Chuyển đổi byte[] thành chuỗi hex
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }
            
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 không được hỗ trợ!", e);
        }
    }
}
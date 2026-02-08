package blockchainvtk;

import java.util.HashMap;
import java.util.Map;

/**
 * Công cụ so sánh chi tiết hiệu ứng của 2 chế độ hash
 * Hữu ích cho NCKH hoặc báo cáo
 */
public class HashComparison {

    /**
     * So sánh 2 chiến lược hash
     */
    public static void compareHashStrategies(String input) {
        HashStrategy simpleHash = new SimpleHash();
        HashStrategy sha256Hash = new SHA256Hash();

        String simple = simpleHash.hash(input);
        String sha256 = sha256Hash.hash(input);

        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║          SO SÁNH CẢ 2 CHẾ ĐỘ HASH                   ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
        
        System.out.println("\n📄 Dữ liệu input: \"" + input + "\"");
        System.out.println("\n┌─ SimpleHash ─────────────────────────────────────────┐");
        System.out.println("│ Hash:        " + simple);
        System.out.println("│ Độ dài:      " + simple.length() + " ký tự");
        System.out.println("│ Tốc độ:      ⚡ Nhanh");
        System.out.println("│ Bảo mật:     🔓 Yếu (giáo dục)");
        System.out.println("└─────────────────────────────────────────────────────────┘");

        System.out.println("\n┌─ SHA-256 Hash ────────────────────────────────────────┐");
        System.out.println("│ Hash:        " + sha256);
        System.out.println("│ Độ dài:      " + sha256.length() + " ký tự");
        System.out.println("│ Tốc độ:      ⚙️  Bình thường");
        System.out.println("│ Bảo mật:     🔒 Cao (chuẩn công nghiệp)");
        System.out.println("└─────────────────────────────────────────────────────────┘");
    }

    /**
     * Demo avalanche effect với cả 2 chế độ
     */
    public static void demonstrateAvalanche() {
        String[] texts = {
            "Sinh vien A",
            "Sinh vien B",
            "sinh vien A", // chỉ thay chữ hoa thành thường
            "Sinh vien A " // thêm 1 khoảng trắng
        };

        HashStrategy simpleHash = new SimpleHash();
        HashStrategy sha256Hash = new SHA256Hash();

        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║          DEMO HIỆU ỨNG AVALANCHE EFFECT              ║");
        System.out.println("╚═══════════════════════════════════════════��════════════╝");

        System.out.println("\n┌─ SimpleHash ─────────────────────────────────────────┐");
        for (String text : texts) {
            System.out.println("│ \"" + String.format("%-20s", text) + "\" → " 
                             + simpleHash.hash(text));
        }
        System.out.println("└─────────────────────────────────────────────────────────┘");

        System.out.println("\n┌─ SHA-256 Hash ────────────────────────────────────────┐");
        for (String text : texts) {
            System.out.println("│ \"" + String.format("%-20s", text) + "\" → " 
                             + sha256Hash.hash(text));
        }
        System.out.println("└─────────────────────────────────────────────────────────┘");
    }

    public static void main(String[] args) {
        compareHashStrategies("Sinh vien A nop bai");
        demonstrateAvalanche();
    }
}
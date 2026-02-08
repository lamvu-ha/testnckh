package blockchainvtk;

import java.util.Scanner;

/**
 * Lớp Main - Chương trình demo blockchain giáo dục
 * Hỗ trợ 2 chế độ hash: SimpleHash (giáo dục) & SHA-256 (bảo mật)
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║   BLOCKCHAIN VISUALIZATION TOOL - DEMO GIÁO DỤC      ║");
        System.out.println("║         (2 Chế độ Hash: SimpleHash & SHA-256)        ║");
        System.out.println("╚════════════════════════════════════════════════════════╝\n");

        // ===== CHỌN CHẾ ĐỘ HASH =====
        int mode = selectHashMode();
        HashStrategy hashStrategy = (mode == 1) ? new SimpleHash() : new SHA256Hash();

        System.out.println("\n✓ Đã chọn chế độ: " + hashStrategy.getName());
        System.out.println("─".repeat(60) + "\n");

        // ===== BƯỚC 1: Khởi tạo Blockchain =====
        Blockchain blockchain = new Blockchain(hashStrategy);

        // ===== BƯỚC 2: Thêm các block vào chuỗi =====
        System.out.println("\n Bước 1: Thêm dữ liệu vào blockchain");
        System.out.println("─".repeat(60));
        blockchain.addBlock("Sinh viên A nộp bài");
        blockchain.addBlock("Sinh viên B nộp bài");
        blockchain.addBlock("Sinh viên C nộp bài");

        // ===== BƯỚC 3: Hiển thị blockchain ban đầu =====
        System.out.println("\n Bước 2: Kiểm tra blockchain ban đầu");
        System.out.println("─".repeat(60));
        blockchain.printChain();
        
        System.out.println("\n Xác minh tính toàn vẹn:");
        boolean isValidBefore = blockchain.isValid();

        // ===== BƯỚC 4: Giả mạo dữ liệu =====
        System.out.println("\n\n  Bước 3: Sinh viên cố gắng giả mạo dữ liệu");
        System.out.println("─".repeat(60));
        System.out.println("→ Sửa dữ liệu Block #1 thành: 'Sinh viên A gian lận'");
        blockchain.getBlock(1).setData("Sinh viên A gian lận");

        // ===== BƯỚC 5: Hiển thị blockchain sau giả mạo =====
        System.out.println("\n Bước 4: Kiểm tra blockchain sau giả mạo");
        System.out.println("─".repeat(60));
        blockchain.printChain();

        // ===== BƯỚC 6: Xác minh lại =====
        System.out.println("\n Xác minh tính toàn vẹn:");
        boolean isValidAfter = blockchain.isValid();

        // ===== KẾT LUẬN =====
        System.out.println("\n\n" + "═".repeat(60));
        System.out.println(" KẾT LUẬN:");
        System.out.println("═".repeat(60));
        System.out.println(" Trước giả mạo: Blockchain " + (isValidBefore ? " HỢP LỆ" : " KHÔNG HỢP LỆ"));
        System.out.println(" Sau giả mạo:   Blockchain " + (isValidAfter ? " HỢP LỆ" : " KHÔNG HỢP LỆ"));
        System.out.println("\n💡 Blockchain phát hiện được sự thay đổi dữ liệu!");
        System.out.println("   Đây là đặc điểm bảo mật cơ bản của blockchain.\n");

        // Demo thêm: So sánh hiệu ứng avalanche
        System.out.println("\n" + "═".repeat(60));
        System.out.println(" DEMO: HIỆU ỨNG AVALANCHE (Avalanche Effect)");
        System.out.println("═".repeat(60));
        demonstrateAvalancheEffect(hashStrategy);
    }

    /**
     * Cho người dùng chọn chế độ hash
     */
    private static int selectHashMode() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║  Chọn chế độ Hash:                                   ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║  1️  SimpleHash     (16 ký tự, dễ hiểu, tốc độ nhanh)  ║");
        System.out.println("║  2️  SHA-256 Hash   (64 ký tự, bảo mật cao, chuẩn)     ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.print("\n👉 Nhập lựa chọn (1 hoặc 2): ");
        
        int choice = 1;
        try {
            choice = Integer.parseInt(scanner.nextLine().trim());
            if (choice < 1 || choice > 2) {
                System.out.println("⚠️  Lựa chọn không hợp lệ, sử dụng SimpleHash");
                choice = 1;
            }
        } catch (NumberFormatException e) {
            System.out.println("⚠️  Vui lòng nhập số, sử dụng SimpleHash");
            choice = 1;
        }
        
        scanner.close();
        return choice;
    }

    /**
     * Demo hiệu ứng Avalanche Effect
     * Thay đổi 1 ký tự nhỏ → Hash thay đổi hoàn toàn
     */
    private static void demonstrateAvalancheEffect(HashStrategy strategy) {
        String text1 = "Sinh vien A nop bai";
        String text2 = "Sinh vien A nop bail"; // Chỉ thay đổi 1 ký tự cuối

        String hash1 = strategy.hash(text1);
        String hash2 = strategy.hash(text2);

        System.out.println("\n📄 Dữ liệu gốc:    \"" + text1 + "\"");
        System.out.println("🔢 Hash:           " + hash1);
        System.out.println("\n📄 Dữ liệu sửa:    \"" + text2 + "\" (thay đổi 1 ký tự)");
        System.out.println("🔢 Hash:           " + hash2);

        // So sánh sự khác nhau
        int differentChars = countDifferentChars(hash1, hash2);
        double percentage = (double) differentChars / hash1.length() * 100;

        System.out.println("\n📊 Phân tích:");
        System.out.println("   - Dữ liệu thay đổi: 1 ký tự (~5%)");
        System.out.println("   - Hash thay đổi:    " + differentChars + " / " + hash1.length() 
                          + " ký tự (~" + String.format("%.1f", percentage) + "%)");
        System.out.println("\n💡 Hiệu ứng Avalanche: Thay đổi nhỏ → Kết quả hoàn toàn khác!");
        System.out.println("   Đây là đặc tính quan trọng của hàm băm mật mã.\n");
    }

    /**
     * Đếm số ký tự khác nhau giữa 2 chuỗi
     */
    private static int countDifferentChars(String s1, String s2) {
        int count = 0;
        int minLen = Math.min(s1.length(), s2.length());
        
        for (int i = 0; i < minLen; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                count++;
            }
        }
        
        count += Math.abs(s1.length() - s2.length());
        return count;
    }
}
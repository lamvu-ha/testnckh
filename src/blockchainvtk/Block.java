package blockchainvtk;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Lớp Block đại diện cho một block trong blockchain
 */
public class Block extends AbstractBlock {
    
    private static final DateTimeFormatter FORMATTER = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    private HashStrategy hashStrategy; // Chiến lược hash

    /**
     * Tạo một block mới
     * @param index Vị trí trong chuỗi
     * @param data Dữ liệu block
     * @param previousHash Hash của block trước
     * @param strategy Chiến lược hash
     */
    public Block(int index, String data, String previousHash, HashStrategy strategy) {
        this.index = index;
        this.data = data;
        this.previousHash = previousHash;
        this.timestamp = LocalDateTime.now().format(FORMATTER);
        this.hashStrategy = strategy;
        this.calculateHash();
    }

    /**
     * Tính toán hash block từ: index + timestamp + data + previousHash
     */
    @Override
    public void calculateHash() {
        String input = index + timestamp + data + previousHash;
        this.hash = hashStrategy.hash(input);
    }

    @Override
    public String toString() {
        return String.format(
            "Block #%d%n" +
            "  Timestamp: %s%n" +
            "  Data: %s%n" +
            "  Hash: %s%n" +
            "  Previous Hash: %s%n" +
            "  %s",
            index,
            timestamp,
            data,
            hash,
            previousHash,
            "─".repeat(50)
        );
    }
}
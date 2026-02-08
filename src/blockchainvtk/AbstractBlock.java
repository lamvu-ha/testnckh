package blockchainvtk;

/**
 * Lớp trừu tượng định nghĩa cấu trúc cơ bản của một Block
 */
public abstract class AbstractBlock {
    
    protected int index;           // Vị trí block trong chuỗi
    protected String timestamp;    // Thời gian tạo block
    protected String data;         // Dữ liệu block
    protected String previousHash; // Hash của block trước đó
    protected String hash;         // Hash của block hiện tại

    /**
     * Phương thức trừu tượng tính toán hash
     */
    public abstract void calculateHash();

    // ===== GETTER =====
    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public int getIndex() {
        return index;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getData() {
        return data;
    }

    // ===== SETTER =====
    public void setData(String data) {
        this.data = data;
        calculateHash(); // Tính lại hash khi dữ liệu thay đổi
    }
}
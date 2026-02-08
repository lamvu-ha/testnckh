package blockchainvtk;

import java.util.ArrayList;
import java.util.List;

/**
 * Lớp Blockchain quản lý toàn bộ chuỗi các block
 */
public class Blockchain {
    
    private List<Block> chain;
    private HashStrategy hashStrategy;

    /**
     * Khởi tạo blockchain với genesis block
     * @param strategy Chiến lược hash sử dụng
     */
    public Blockchain(HashStrategy strategy) {
        this.chain = new ArrayList<>();
        this.hashStrategy = strategy;
        createGenesisBlock();
    }

    /**
     * Tạo genesis block - block đầu tiên trong chuỗi
     */
    private void createGenesisBlock() {
        Block genesisBlock = new Block(0, "Genesis Block", "0", hashStrategy);
        chain.add(genesisBlock);
        System.out.println("✓ Genesis block được tạo");
    }

    /**
     * Thêm một block mới vào chuỗi
     * @param data Dữ liệu của block mới
     */
    public void addBlock(String data) {
        Block lastBlock = getLastBlock();
        Block newBlock = new Block(
            chain.size(),
            data,
            lastBlock.getHash(),
            hashStrategy
        );
        chain.add(newBlock);
        System.out.println("✓ Block #" + newBlock.getIndex() + " được thêm vào");
    }

    /**
     * Lấy block cuối cùng trong chuỗi
     */
    private Block getLastBlock() {
        return chain.get(chain.size() - 1);
    }

    /**
     * Kiểm tra tính toàn vẹn của blockchain
     * @return true nếu chuỗi hợp lệ, false nếu có sai lệch
     */
    public boolean isValid() {
        for (int i = 1; i < chain.size(); i++) {
            Block current = chain.get(i);
            Block previous = chain.get(i - 1);

            // Kiểm tra 1: Hash của block hiện tại có đúng không?
            String recalculatedHash = hashStrategy.hash(
                current.getIndex() + current.getTimestamp() + 
                current.getData() + current.getPreviousHash()
            );

            if (!current.getHash().equals(recalculatedHash)) {
                System.out.println("✗ Block #" + i + ": Hash không khớp!");
                return false;
            }

            // Kiểm tra 2: Previous hash có trỏ đúng đến block trước không?
            if (!current.getPreviousHash().equals(previous.getHash())) {
                System.out.println("✗ Block #" + i + ": Liên kết bị đứt!");
                return false;
            }
        }
        System.out.println("✓ Blockchain hợp lệ - toàn bộ chuỗi toàn vẹn");
        return true;
    }

    /**
     * Hiển thị toàn bộ blockchain
     */
    public void printChain() {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("BLOCKCHAIN (tổng cộng " + chain.size() + " block)");
        System.out.println("═".repeat(60) + "\n");
        
        for (Block block : chain) {
            System.out.println(block);
        }
    }

    /**
     * Lấy một block theo index
     * @param index Vị trí block
     * @return Block tại vị trí index
     */
    public Block getBlock(int index) {
        if (index < 0 || index >= chain.size()) {
            throw new IndexOutOfBoundsException("Block index không hợp lệ: " + index);
        }
        return chain.get(index);
    }

    /**
     * Lấy số lượng block trong chuỗi
     */
    public int getChainLength() {
        return chain.size();
    }

    /**
     * Xóa toàn bộ blockchain (chỉ giữ genesis block)
     */
    public void reset() {
        chain.clear();
        createGenesisBlock();
        System.out.println("✓ Blockchain đã được reset");
    }
}
package blockchainvtk;

import java.util.ArrayList;
import java.util.List;

public class Blockchain {

    private List<Block> chain;
    private HashStrategy hashStrategy;

    public Blockchain(HashStrategy strategy) {
        this.chain = new ArrayList<>();
        this.hashStrategy = strategy;
        createGenesisBlock();
    }

    private void createGenesisBlock() {
        Block genesis = new Block(0, "Genesis Block", "0", hashStrategy);
        chain.add(genesis);
    }

    public void addBlock(String data) {
        Block lastBlock = chain.get(chain.size() - 1);
        Block newBlock = new Block(
                chain.size(),
                data,
                lastBlock.getHash(),
                hashStrategy
        );
        chain.add(newBlock);
    }

    public boolean isValid() {
        for (int i = 1; i < chain.size(); i++) {
            Block current = chain.get(i);
            Block previous = chain.get(i - 1);

            String recalculatedHash = hashStrategy.hash(
                    current.index + current.timestamp + current.data + current.previousHash
            );

            if (!current.getHash().equals(recalculatedHash)) {
                return false;
            }

            if (!current.getPreviousHash().equals(previous.getHash())) {
                return false;
            }
        }
        return true;
    }

    public void printChain() {
        for (Block block : chain) {
            System.out.println(block);
        }
    }

    public Block getBlock(int index) {
        return chain.get(index);
    }
}

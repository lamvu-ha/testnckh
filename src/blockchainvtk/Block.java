package blockchainvtk;

import java.time.LocalDateTime;

public class Block extends AbstractBlock {

    private HashStrategy hashStrategy;

    public Block(int index, String data, String previousHash, HashStrategy strategy) {
        this.index = index;
        this.data = data;
        this.previousHash = previousHash;
        this.timestamp = LocalDateTime.now().toString();
        this.hashStrategy = strategy;
        calculateHash();
    }

    @Override
    public void calculateHash() {
        String input = index + timestamp + data + previousHash;
        this.hash = hashStrategy.hash(input);
    }

    @Override
    public String toString() {
        return "Block #" + index +
                "\n data: " + data +
                "\n hash: " + hash +
                "\n previousHash: " + previousHash +
                "\n--------------------------";
    }
}


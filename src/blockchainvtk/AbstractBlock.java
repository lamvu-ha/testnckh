package blockchainvtk;

public abstract class AbstractBlock {

    protected int index;
    protected String timestamp;
    protected String data;
    protected String previousHash;
    protected String hash;

    public abstract void calculateHash();

    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setData(String data) {
        this.data = data;
        calculateHash();
    }
}

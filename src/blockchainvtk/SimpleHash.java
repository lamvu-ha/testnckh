package blockchainvtk;

public class SimpleHash implements HashStrategy {

    @Override
    public String hash(String input) {
        long hash = 7;
        for (int i = 0; i < input.length(); i++) {
            hash = hash * 31 + input.charAt(i);
            hash = hash % 1000000007; // giới hạn số
        }
        return Long.toHexString(hash);
    }
}


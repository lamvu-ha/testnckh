package blockchainvtk;

public class Main {

	public static void main(String[] args) {

		        HashStrategy hashStrategy = new SimpleHash();
		        Blockchain blockchain = new Blockchain(hashStrategy);

		        blockchain.addBlock("Sinh vien A nop bai");
		        blockchain.addBlock("Sinh vien B nop bai");

		        System.out.println("=== BLOCKCHAIN BAN DAU ===");
		        blockchain.printChain();
		        System.out.println("Valid: " + blockchain.isValid());

		        // Sinh viên sửa dữ liệu
		        System.out.println("\n=== SUA DU LIEU BLOCK 1 ===");
		        blockchain.getBlock(1).setData("Sinh vien A gian lan");

		        blockchain.printChain();
		        System.out.println("Valid: " + blockchain.isValid());
		    }
		


	}


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Block {
    private String hash;
    private String previousHash;
    private List<Transaction> transactions;
    private long timeStamp;
    private int nonce;

    public Block(List<Transaction> transactions, String previousHash, long timeStamp){
        this.transactions = transactions;
        this.previousHash = previousHash;
        this.timeStamp = timeStamp;
        this.hash = calculateBlockHash();
    }

    public String calculateBlockHash(){
        String dataToHash = previousHash
                + Long.toString(timeStamp)
                + Integer.toString(nonce)
                + transactions.toString();

        MessageDigest digest = null;
        byte[] bytes = null;

        try {
            digest = MessageDigest.getInstance("SHA-256");
            bytes = digest.digest(dataToHash.getBytes(UTF_8));
        }catch (NoSuchAlgorithmException ex){
            ex.printStackTrace();
        }

        StringBuffer buffer = new StringBuffer();
        for (byte b : bytes){
            buffer.append(String.format("%02x",b));
        }
        return buffer.toString();
    }

    public String mineBlock(int prefix) {
        String prefixString = new String(new char[prefix]).replace('\0','0');
        while(!hash.substring(0,prefix).equals(prefixString)){
            nonce++;
            hash = calculateBlockHash();
        }

        return hash;
    }

    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public int getNonce() {
        return nonce;
    }
}

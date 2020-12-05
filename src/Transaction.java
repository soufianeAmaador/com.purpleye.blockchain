public class Transaction {
    private String sender;
    private String receiver;
    private double amount;
    private long timeStamp;

    public Transaction(String sender, String receiver, double amount, long timeStamp) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return System.out.printf("Transaction from %s to %S, amount: %f at %f",sender,receiver,amount,timeStamp)
                .toString();
    }
}

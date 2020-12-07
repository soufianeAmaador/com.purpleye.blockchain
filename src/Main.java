import java.awt.*;
import java.util.*;
import java.util.List;

public class Main {
    public static List<Block> blockchain = new ArrayList<>();
    public static Queue<Block> unverifiedBlocks = new LinkedList<>();
    public static String[] namesSenders = {"Soufiane","James","Marianne","Frank","benjamin","Tessa"};
    public static String[] namesReceivers = {"Avery","Veronica","Ezekiel","Moe","Darius","DeShawn"};
    public static int prefix;

    public static void main(String[] args) {
        prefix = 4;
        generateBlocks();

        for(int i = 0; i < unverifiedBlocks.size(); ++i){



            Long before = new Date().getTime();
            unverifiedBlocks.poll().mineBlock(prefix);
            System.out.println("time it took " + (System.currentTimeMillis() - before));

            blockchain.add(unverifiedBlocks.poll());
        }

    }

    private void validateBlockchain(){
        boolean flag = true;

        for (int i = 0; i < blockchain.size(); i++) {
            String previousHash = i==0 ? "0" : blockchain.get(i-1).getHash();
            flag = blockchain.get(i).getHash().equals(blockchain.get(i).calculateBlockHash()) &&
                    previousHash.equals(blockchain.get(i).getPreviousHash()) &&
                    blockchain.get(i).getHash().substring(0,prefix).equals("0000");
            if (!flag) break;
        }

        System.out.println("BLOCKCHAIN IS VALIDATED");
    }

    private static void generateBlocks(){

        for (int i = 0; i <= 20; ++i) {
            unverifiedBlocks.add(new Block(
                    List.of(new Transaction(namesSenders[new Random().nextInt(6)],
                            namesReceivers[new Random().nextInt(6)],new Random().nextDouble(),
                            new Date().getTime())),
                    blockchain.size() > 1 ? blockchain.get(blockchain.size()-1).getHash() : "",
                    new Date().getTime()));
        }

    }
}

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        final int TOPICS_COUNT = 1;
        int topic = 0;
        do {
            Service.IService.cleanConsole();
            System.out.println("SELECT TOPIC");
            System.out.println("1 - JDBC");
            System.out.println("0 - Exit");
            System.out.print("Your choice:");
            do {
                topic = Service.IService.getInteger();
                if (topic < 0 || topic > TOPICS_COUNT) {
                    System.out.println("Incorrect input. Integer from the range 0.." + TOPICS_COUNT + " is expected.");
                    System.out.print("Try again: ");
                }
            } while (topic < 0 || topic > TOPICS_COUNT);
            if (topic != 0) {
                int choice = 0;
                boolean flag = false;
                Service.IService.cleanConsole();
                String[] messages = null;
                switch (topic) {
                    case 1: {
                        messages = new String[]{"1 - Statement",
                        "2 - PreparedStatement",
                        "3 - SQL injections",
                        "4 - BLOB",
                        "5 - Stored procedures"};
                        break;
                    }
                    default: {
                        messages = new String[]{"There are no problems, described in this topic"};
                        flag = true;
                    }
                }
                System.out.println("SELECT CONCRETE PROBLEM");
                for (String message : messages) {
                    System.out.println(message);
                }
                System.out.println("0 - Exit");
                System.out.print("Your choice:");
                do {
                    choice = Service.IService.getInteger();
                    if (!flag && (choice < 0 || choice > messages.length)) {
                        System.out.println("Incorrect input. Integer from the range 0.." + messages.length + " is expected.");
                        System.out.print("Try again: ");
                    }
                } while (!flag && (choice < 0 || choice > messages.length));
                if (!flag && choice > 0) {
                    Service.IProblem problem = Service.ICreator.create(topic, choice);
                    problem.solve();
                }
                System.out.print("Press enter to continue");
                char str = (char) System.in.read();
            }
        } while (topic != 0);
        System.out.println("Buy-buy!");
    }
}

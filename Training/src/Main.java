import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        final int TOPICS_COUNT = 23;
        int topic = 0;
        do {
            Interfaces.IConsole.cleanConsole();
            System.out.println("SELECT TOPIC");
            System.out.println("1 - Casting");
            System.out.println("2 - Operations");
            System.out.println("3 - Operators");
            System.out.println("4 - Classes");
            System.out.println("5 - Inheritance and polymorphism");
            System.out.println("6 - Packages and interfaces");
            System.out.println("7 - Exception");
            System.out.println("8 - Threads");
            System.out.println("9 - Enum, Autopacking, Annotations");
            System.out.println("10 - IO, NIO, Assert");
            System.out.println("11 - Generics");
            System.out.println("12 - Reflection");
            System.out.println("13 - Lambdas");
            System.out.println("14 - Patterns");
            System.out.println("15 - String");
            System.out.println("16 - Serialization");
            System.out.println("17 - Java.lang");
            System.out.println("18 - Collections");
            System.out.println("19 - Java.util");
            System.out.println("20 - Java.net");
            System.out.println("21 - JDBC");
            System.out.println("22 - Stream API");
            System.out.println("23 - Regular expressions");
            System.out.println("0 - Exit");
            System.out.print("Your choice:");
            do {
                topic = Interfaces.IConsole.getInteger();
                if (topic < 0 || topic > TOPICS_COUNT) {
                    System.out.println("Incorrect input. Integer from the range 0.." + TOPICS_COUNT + " is expected.");
                    System.out.print("Try again: ");
                }
            } while (topic < 0 || topic > TOPICS_COUNT);
            if (topic != 0) {
                int choice = 0;
                boolean flag = false;
                Interfaces.IConsole.cleanConsole();
                String[] messages = null;
                switch (topic) {
                    case 1: {
                        messages = new String[]{"1 - Casting",
                                "2 - Operator instanceof"};
                        break;
                    }
                    case 4: {
                        messages = new String[]{"1 - Inner classes",
                                "2 - Static methods",
                                "3 - Variable-length arguments",
                                "4 - Anonymous classes",
                                "5 - Access levels",
                                "6 - Abstract classes and methods",
                                "7 - Inner and nested classes"};
                        break;
                    }
                    case 5: {
                        messages = new String[]{"1 - Inheritance and references",
                        "2 - Polymorphism and mathods parameters",
                        "3 - Final methods"};
                        break;
                    }
                    case 6: {
                        messages = new String[]{"1 - Interfaces inheritance, polymorphism and default methods",
                                "2 - Comparable vs Comparator",
                                "3 - Variables and interfaces"};
                        break;
                    }
                    case 7: {
                        messages = new String[]{"1 - Generating different exceptions",
                                "2 - Exception-throwing methods inheritance"};
                        break;
                    }
                    case 8: {
                        messages = new String[]{"1 - Basics",
                                "2 - Interface Runnable",
                                "3 - Use of Locker class, myresume() & mysuspend() methods",
                                "4 - Use of Locker class & interrupt() method",
                                "5 - Executor",
                                "6 - Dead lock",
                                "7 - Callable",
                                "8 - Semaphore",
                                "9 - Synchronyzing with semaphore",
                                "10 - CountDownLatch",
                                "11 - Exchanger",
                                "12 - CyclicBarrier",
                                "13 - Phaser",
                                "14 - java.util.concurrent.lock",
                                "15 - Phaser revelation"};
                        break;
                    }
                    case 9: {
                        messages = new String[]{"1 - Enum",
                                "2 - Enum Factory",
                                "3 - Autopacking",
                                "4 - Annotations (getMethod, getAnnotation, getField, getConstructor)",
                                "5 - Annotations (default values)",
                                "6 - Annotation markers and annotations with one member",
                                "7 - Build-in annotations"};
                        break;
                    }
                    case 10: {
                        messages = new String[]{"1 - In-Out",
                                "2 - Assert",
                                "3 - File",
                                "4 - Flushable",
                                "5 - Console",
                                "6 - NIO, Buffer usage",
                                "7 - NIO, Channel usage",
                                "8 - NIO, Path, Files.",
                                "9 - Some crap with System.in"};
                        break;
                    }
                    case 11: {
                        messages = new String[]{"1 - Meta symbols",
                                "2 - Generic interfaces",
                                "3 - Generic restrictions",
                                "4 - <T extends Comparable<T>>",
                                "5 - Meta symbols. Revelation",
                                "6 - Wildcards",
                                "7 - Wildcards revelation"};
                        break;
                    }
                    case 12: {
                        messages = new String[]{"1 - Reflection"};
                        break;
                    }
                    case 13: {
                        messages = new String[]{"1 - Lambdas",
                                "2 - None-static methods references",
                                "3 - Predefined functional interfaces"};
                        break;
                    }
                    case 14: {
                        messages = new String[]{"1 - Singleton",
                                "2 - Singleton (enum)",
                                "3 - Builder",
                                "4 - Decorator",
                                "5 - Factory",
                                "6 - Finite state machine"};
                        break;
                    }
                    case 15: {
                        messages = new String[]{"1 - Different String-methods"};
                        break;
                    }
                    case 16: {
                        messages = new String[]{"1 - Serializable (Standard)",
                                "2 - Serializable (writeObject, readObject)",
                                "3 - Externalizable"};
                        break;
                    }
                    case 17: {
                        messages = new String[]{"1 - Java.lang",
                                "2 - Class ClassVoid"};
                        break;
                    }
                    case 18: {
                        messages = new String[]{"1 - Collections Basics",
                                "2 - Iterators",
                                "3 - Maps",
                                "4 - Arrays",
                                "5 - Spliterators",
                                "6 - Questions from interviews"};
                        break;
                    }
                    case 19: {
                        messages = new String[]{"1 - JavaUtil.Optional",
                                "2 - Observable",
                                "3 - BitSet, bit operatons",
                                "4 - Calendar, Date...",
                                "5 - Foramtter, Scanner"};
                        break;
                    }
                    case 20: {
                        messages = new String[]{"1 - InetAddress",
                                "2 - Socket",
                                "3 - URL",
                                "4 - Server-Client"};
                        break;
                    }
                    case 21: {
                        messages = new String[]{"1 - Basics"};
                        break;
                    }
                    case 22: {
                        messages = new String[]{"1 - Basics"};
                        break;
                    }
                    case 23: {
                        messages = new String[]{"1 - Basics"};
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
                    choice = Interfaces.IConsole.getInteger();
                    if (!flag && (choice < 0 || choice > messages.length)) {
                        System.out.println("Incorrect input. Integer from the range 0.." + messages.length + " is expected.");
                        System.out.print("Try again: ");
                    }
                } while (!flag && (choice < 0 || choice > messages.length));
                if (!flag && choice > 0) {
                    Interfaces.IProblem problem = Interfaces.ICreator.create(topic, choice);
                    problem.solve();
                }
                System.out.print("Press enter to continue");
                char str = (char) System.in.read();
            }
        } while (topic != 0);
        System.out.println("Buy-buy!");
    }
}

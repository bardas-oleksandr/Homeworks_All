package Problem_04;

import Interfaces.IProblem;
import Interfaces.IService;

import java.io.File;
import java.io.FileFilter;
import java.util.Scanner;

public class Problem implements IProblem {
    @Override
    public void solve() {
        int choice;
        final int COUNT = 2;
        do{
            IService.clearConsole();
            System.out.println("MENU");
            System.out.println("1 - Set file path to show full information");
            System.out.println("2 - Use default file path");
            System.out.println("0 - Exit");
            System.out.print("Your choice:");
            choice = IService.getIntegerBounded(0,COUNT);
            String path = null;
            switch(choice){
                case 1:
                    System.out.print("Set the path: ");
                    path = new Scanner(System.in).nextLine();
                    break;
                case 2:
                    path = "src/main/resources";
                    break;
            }
            if(path != null){
                File file = new File(path);
                String mask = ".txt";
                System.out.println("FILE LIST");
                search(file, (pathname)->pathname.getAbsolutePath().endsWith(mask));
            }
            if(choice != 0){
                IService.pressEnterToContinue();
            }
        }while(choice != 0);
    }

    private void search(File file, FileFilter filter){
        if(file.isDirectory()){
            File[] files = file.listFiles(filter);
            if(files != null){
                for(File item: files){
                    System.out.println(item.getAbsolutePath());
                }
            }
            File[] allFiles = file.listFiles();
            if(allFiles != null){
                for(File item: allFiles){
                    search(item,filter);
                }
            }
        }
    }
}

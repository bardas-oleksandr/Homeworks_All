package Problem_02;

import Interfaces.IProblem;
import Interfaces.IService;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;

public class Problem implements IProblem {
    @Override
    public void solve() {
        int choice;
        final int COUNT = 2;
        do{
            IService.clearConsole();
            System.out.println("MENU");
            System.out.println("1 - Set file path to copy");
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
                    path = "src/main/resources/photo_01.jpg";
                    break;
            }
            if(path != null){
                File file = new File(path);
                Base64.Encoder encoder = Base64.getEncoder();
                try (FileInputStream in = new FileInputStream(file)){
                    ArrayList<Byte> list = new ArrayList<>();
                    int val;
                    while((val = in.read()) != -1){
                        list.add((byte)val);
                    }
                    byte[] src = new byte[list.size()];
                    for(int i = 0; i < src.length; i++){
                        src[i] = list.get(i);
                    }
                    src = encoder.encode(src);
                    Base64.Decoder decoder = Base64.getDecoder();
                    int index = path.lastIndexOf(".");
                    path = path.substring(0, index) + "_copy" + path.substring(index, path.length());
                    File fileCopy = new File(path);
                    try(OutputStream out = new FileOutputStream(fileCopy)) {
                        out.write(decoder.decode(src)); //decoder.decode(src) возвращает массив байтов
                        System.out.println("Your file was successfully copied.");
                        System.out.println("File path: " + fileCopy.getAbsolutePath());

                        String command;
                        if(System.getProperty("os.name").contains("Windows")){
                            command="mspaint";
                        }else{
                            command="gwenview"; //Будем надеяться что есть такая
                        }
                        try{
                            new ProcessBuilder(command, fileCopy.getAbsolutePath()).start();
                        } catch(Exception e){
                            System.out.println("Error while graphic viewer loading");
                        }

                    } catch(FileNotFoundException e){
                        System.out.println("Something bad while decoding has happenned");
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                } catch(IOException e){
                    System.out.println("There is no file, corresponding with the specified path");
                }
            }
            if(choice != 0){
                IService.pressEnterToContinue();
            }
        }while(choice != 0);
    }
}

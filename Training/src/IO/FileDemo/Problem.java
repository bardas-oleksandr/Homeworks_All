package IO.FileDemo;

import Interfaces.IProblem;

import java.io.*;

public class Problem implements IProblem {
    @Override
    public void solve() {
        File file = new File("src/newFile.txt");

        System.out.println(file.getAbsolutePath());
        System.out.println(file.getName());
        System.out.println(file.getPath());
        if(file.isAbsolute()){
            System.out.println("File has absolute path");
        }else{
            System.out.println("File has relative path");
        }

        try (FileWriter writer = new FileWriter(file)){
            writer.write("Yes, it is");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Free gigabytes on dick C: " + file.getFreeSpace()/1024/1024/1024);
        System.out.println("Usable gigabytes on dick C: " + file.getUsableSpace()/1024/1024/1024);
        System.out.println("Total gigabytes on dick C: " + file.getTotalSpace()/1024/1024/1024);

        boolean flag = new File("src/one/two/three").mkdirs();    //Так можно создавать только папки, но не файлы


    }
}

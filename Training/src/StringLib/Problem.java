package StringLib;

import Interfaces.IProblem;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

public class Problem implements IProblem {
    @Override
    public void solve() {
        System.out.println("Метод trim() обрезает пробелы и символы табуляции");
        String str = "\t     Yes, it is        ";
        System.out.println("str = \"" + str + "\"");
        System.out.println("str.trim() = \"" + str.trim() + "\"");

        System.out.println("\nМетод indexOf() возвращает позицию элемента в строке");
        str = "Yes, it is";
        System.out.println("str = " + str);
        System.out.println("str.indexOf(\"No\") = " + str.indexOf("No"));
        System.out.println("str.indexOf(\"it\") = " + str.indexOf("it"));

        System.out.println("\nКонструкторы символных строк");
        char chars[] = {'a', 'b','c','d','e','f'};
        str = new String(chars, 1, 3);
        System.out.println(str);
        byte symb[] = {48,49,50,51,52,53,54,55,56,57};
        str = new String(symb); //Инициализация строки символами размером 1 байт (ASCII)
        System.out.println(str);
        int arr[] = {422, 423, 439, 936, 326};
        str = new String(arr, 0, 5);
        System.out.println(str);

        System.out.println("\nСпециальные строковые операции");
        str = "Yes, it is";
        str = "Age is " + 5;
        str = 5 + " - age";
        //str = 5; //Incompatible types
        int age = 5;
        str = "Age is " + age;

        class Fish{ //Это называется локальный класс
            int weight;
            Fish(int weight){
                this.weight = weight;
            }
            @Override
            public String toString(){
                return new String("weight = " + weight);
            }
        }
        Fish fish = new Fish(10);
        //str = fish; //toString не срабатывает
        str = "Fish object: " + fish;
        System.out.println(str);
        System.out.println("String.valueOf(fish): " + String.valueOf(fish));   //Метод valueOf вызывает метод toString

        str = "Four: " + 2 + 2; //Получим "Four: 22"
        System.out.println(str);
        str = "Four: " + (2 + 2); //Получим "Four: 4"
        System.out.println(str);

        System.out.println("\nИзвлечение символов");
        char ch = str.charAt(1);
        System.out.println("str: " + str);
        System.out.println("str.charAt(1): " + ch);

        char arr1[] = new char[4];
        str.getChars(0,4, arr1, 0);
        System.out.println("str.getChars(0,4, arr1, 0): " + arr1[0]+ arr1[1]+ arr1[2]+ arr1[3]);

        arr1 = str.toCharArray();
        for(char symbol: arr1){
            System.out.print(symbol);
        }
        System.out.print("\n");

        str = "Yes";
        String str1 = "Oh, yes";
        if(str1.regionMatches(true,4, str, 0, 3)){
            System.out.println("Yes it is");
        }

        if(str1.startsWith("Oh")){
            System.out.println("Yes it is");
        }

        if(str1.endsWith("yes")){
            System.out.println("Yes it is");
        }

        System.out.println("\nПоиск в символьных строках");
        str = "la-la-la-la";
        int x1 = str.indexOf("la");
        int x2 = str.lastIndexOf("la");
        int x3 = str.indexOf("Yes");

        str = "Yes, it is";
        System.out.println(str.substring(5));
        System.out.println(str.substring(5, 7));    //7 - не включается в подстроку

        System.out.println(str.concat(" Oh, no."));

        str = str.replace('Y', 'A');
        System.out.println(str);

        str = "Yes, it is";
        str = str.toUpperCase();
        System.out.println(str);

        str = str.toLowerCase();
        System.out.println(str);

        str = String.join(", ", "one","two", "three", "four", "five");
        System.out.println(str);

        x1 = str.codePointAt(0);
        System.out.println("str.codePointAt(0): " + x1);

        x1 = str.codePointCount(0,1);
        System.out.println("str.codePointCount(0,6): " + x1);

        if(str.contains("two")){
            System.out.println("str.contains(\"two\"): Yes, it is");
        }

        String arr2[] = str.split(", ");
        for (String line: arr2){
            System.out.println(line);
        }

        str = "";
        if(str.isEmpty()){
            System.out.println("Yes, it is");
        }

        StringBuffer sb = new StringBuffer("Yes, it is");
        System.out.println(sb.replace(5,8, "this "));
    }
}

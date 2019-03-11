package regexp.basics;

import Interfaces.IProblem;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem implements IProblem {
    @Override
    public void solve() {
        {
            Pattern pattern = Pattern.compile("Yes, it is");
            Matcher matcher = pattern.matcher("Yes, it is");
            System.out.println("matcher.matches(): " + matcher.matches());  //true если полное совпадение

            pattern = Pattern.compile("Yes");
            matcher = pattern.matcher("Yes, it is");
            System.out.println("matcher.lookingAt(): " + matcher.lookingAt());  //true если совпадение в начале

            pattern = Pattern.compile("la");
            matcher = pattern.matcher("la la la");
            while (matcher.find()) {
                System.out.println("matcher.find(): " + true);    //true если совпадение есть хоть где-то
            }
        }

        {
            System.out.println();
            Pattern pattern = Pattern.compile("it");
            Matcher matcher = pattern.matcher("Yes, it is");
            if(matcher.find()){
                System.out.println("match starts at: " + matcher.start());
                System.out.println("match ends at: " + matcher.end());
            }
        }

        {
            System.out.println();
            Pattern pattern = Pattern.compile("Yes");
            Matcher matcher = pattern.matcher("Yes, it is");
            System.out.println(matcher.replaceAll("No"));
        }

        {
            System.out.println();
            Pattern pattern = Pattern.compile("Yes");
            Matcher matcher = pattern.matcher("Yes, it is");
            if(matcher.find()){
                System.out.println(matcher.group());
            }
        }

        {
            System.out.println();
            Pattern pattern = Pattern.compile("la|li");
            Matcher matcher = pattern.matcher("la-li-la-li");
            while(matcher.find()){
                System.out.println(matcher.group());
            }
        }

        {
            System.out.println();
            Pattern pattern = Pattern.compile("[0-9]");
            Matcher matcher = pattern.matcher("a1s2d3f4g5h6j7k8l9m0");
            while(matcher.find()){
                System.out.print(matcher.group());
            }
        }

        {
            System.out.println();
            Pattern pattern = Pattern.compile("[012]");
            Matcher matcher = pattern.matcher("a1s2d3f4g5h6j7k8l9m0");
            while(matcher.find()){
                System.out.print(matcher.group());
            }
        }

        {
            System.out.println();
            Pattern pattern = Pattern.compile("[^0-9]");
            Matcher matcher = pattern.matcher("a1s2d3f4g5h6j7k8l9m0");
            while(matcher.find()){
                System.out.print(matcher.group());
            }
        }

        {
            System.out.println();
            Pattern pattern = Pattern.compile("[a-zA-Z]");
            Matcher matcher = pattern.matcher("1q2a3z4Q5A6Z");
            while(matcher.find()){
                System.out.print(matcher.group());
            }
        }

        {
            System.out.println();
            Pattern pattern = Pattern.compile("\\d");   //digit
            Matcher matcher = pattern.matcher("q1w2e3r4");
            while(matcher.find()){
                System.out.print(matcher.group());
            }
        }

        {
            System.out.println();
            Pattern pattern = Pattern.compile("\\D");   //Non-digit
            Matcher matcher = pattern.matcher("q1w2e3r4");
            while(matcher.find()){
                System.out.print(matcher.group());
            }
        }

        {
            System.out.println();
            Pattern pattern = Pattern.compile("\\s");   //whitespace
            Matcher matcher = pattern.matcher("1 2  3");
            while(matcher.find()){
                System.out.print(matcher.group());
            }
        }

        {
            System.out.println();
            Pattern pattern = Pattern.compile("\\D");   //Non-whitespace
            Matcher matcher = pattern.matcher("1 2  3");
            while(matcher.find()){
                System.out.print(matcher.group());
            }
        }

        {
            System.out.println();
            Pattern pattern = Pattern.compile("\\w");   //word character
            Matcher matcher = pattern.matcher("q1w2e3r4");
            while(matcher.find()){
                System.out.print(matcher.group());
            }
        }

        {
            System.out.println();
            Pattern pattern = Pattern.compile("\\W");   //Non-word character
            Matcher matcher = pattern.matcher("q1w2e3r4");
            while(matcher.find()){
                System.out.print(matcher.group());
            }
        }

        {
            System.out.println();
            Pattern pattern = Pattern.compile("^first");   //word first at the beginning of the line
            Matcher matcher = pattern.matcher("first, first");
            System.out.println(matcher.replaceAll("last"));
        }

        {
            System.out.println();
            Pattern pattern = Pattern.compile("first$");   //word first at the end of the line
            Matcher matcher = pattern.matcher("first, first");
            System.out.println(matcher.replaceAll("last"));
        }

        {
            System.out.println();
            //символы Java в конце слова
            System.out.println("Java and Javascript".replaceAll("Java\\b", "C++"));
        }

        System.out.println("2018-07-11 12:13:01.106 TRACE  Module=CMN Operation: Login        Execution time: 42867 ms");
        {
            Pattern pattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}.\\d{3}");
            Matcher matcher = pattern.matcher("2018-07-11 12:13:01.106 TRACE  Module=CMN Operation: Login        Execution time: 42867 ms");
            if(matcher.find()){
                System.out.println(matcher.group());
            }
        }

        {
            Pattern pattern = Pattern.compile("(Module=)([\\w]+\\b)");
            Matcher matcher = pattern.matcher("2018-07-11 12:13:01.106 TRACE  Module=CMN Operation: Login        Execution time: 42867 ms");
            if(matcher.find()){
                System.out.println(matcher.group(2));
            }
        }

        {
            Pattern pattern = Pattern.compile("(Execution\\stime:\\s)([\\d]+\\b\\sms)");
            Matcher matcher = pattern.matcher("2018-07-11 12:13:01.106 TRACE  Module=CMN Operation: Login        Execution time: 42867 ms");
            if(matcher.find()){
                System.out.println(matcher.group(2));
            }
        }

        {
            Pattern pattern = Pattern.compile("(Operation:\\s)([\\w\\s]+)(\\sExecution\\stime:)");
            Matcher matcher = pattern.matcher("2018-07-11 12:13:01.106 TRACE  Module=CMN Operation: Login        Execution time: 42867 ms");
            if(matcher.find()){
                System.out.println(matcher.group(2));
            }
        }

        {
            Pattern pattern = Pattern.compile("(Operation:\\s)([\\w\\s]*)(\\sExecution\\stime:)");
            Matcher matcher = pattern.matcher("2018-07-11 12:13:01.106 TRACE  Module=CMN Operation:  Execution time: 42867 ms");
            if(matcher.find()){
                System.out.println("Find this: " + matcher.group(2));
            }
        }

        {
            System.out.println("His watch costs $1000".replaceAll("\\$\\d{4,}", "a lot"));
        }

        {
            Pattern pattern = Pattern.compile("(\\d{3})(\\d{3})(\\d{2})(\\d{2,})");
            Matcher matcher = pattern.matcher("0971391311");
            System.out.println(matcher.replaceAll("($1) $2-$3-$4"));
        }
    }
}

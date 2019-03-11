package Lambdas.Basics;

import Interfaces.IProblem;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class Problem implements IProblem {
    private int value;
    @Override
    public void solve() {
        System.out.println("=========������-��������� � ��������� ������ �� �������������� ���������====================");
        FISimple funcInterface;
        funcInterface = ()->10;
        System.out.println("Value = " + funcInterface.getValue());

        funcInterface = ()->{
            Random rnd = new Random();
            return rnd.nextInt(10);
        };
        System.out.println("Value = " + funcInterface.getValue());

        funcInterface = ()-> (int)Math.random()*100;
        System.out.println("Value = " + funcInterface.getValue());

        funcInterface.doNothing();

        NumericTest isEven = (n)-> n%2 == 0;    //������� ������ ��������� ������, ������������ ���������� NumericTest
        int x = 7;
        if(isEven.test(7)){
            System.out.println(x + " is even number");
        }else{
            System.out.println(x + " is odd number");
        }

        NumericTest isPositive = (n)-> n > 0;   //������� ������ ��������� ������, ������������ ��������� NumericTest
        if(isPositive.test(7)){
            System.out.println(x + " is positive number");
        }else{
            System.out.println(x + " is not positive number");
        }

        NumericTest isOdd = n-> n%2 == 1;   //���� ������-��������� ����� ���� ��������, ��� ������������ ��������� � ������

        //���������� �������������� ����������
        GenInterface<Integer> first = (n)-> new Integer(n*2);
        System.out.println(first.func(2));

        GenInterface<String> second = (n)-> {
            String result = "";
            for(int i = n.length() - 1; i >= 0; i--){
                result += n.charAt(i);
            }
            return result;
        };
        System.out.println(second.func("Yes, it is"));

        System.out.println("=========������-��������� � ��������� �������� ���������� �������===========================");
        String str1 = "hello";
        System.out.println(str1);
        //1 ������ - ��������������� ������ ������-��������� ��� ������ ������
        String str2 = this.function((row)->row.toUpperCase(), str1);  //� ����� function �� �������� ������-��������� � ������ ���� String
        System.out.println(str2);
        //2 ������ - ������� ������-��������� � ��������� ������ �� �������������� ��������� � ����� �������� � ����� ��� ������
        StringFunc stringFunc = (row)-> row.toUpperCase();
        String str3 = this.function(stringFunc, str1);
        System.out.println(str3);

        System.out.println("=========������-��������� � ������ ����������===============================================");
        int y = 10;
        NumericTest moreThanY = (n)-> {
            this.value = 10;    //�� ����� ������ � ��������� this ������, � ������� ��������� ������-��������� (� ������ ������ ����� Problem)
            //y++;  //��� ������. ����������� ��������� ���������� �� ������ ����������.
            return n > y; //����������� ���������� y. ����� ����� ���������� y ���������� ����������� (final)
        };
        if(moreThanY.test(15)){
            System.out.println("Yes, it is");
        }
        //y=11; //������� �������� �������� y �������� � ���� ��� ����������� ������ � ������-���������: Variable should be final or effectively final

        System.out.println("=========������ �� ������===================================================================");
        int res = this.funcMethodRef(MyClass::getIntRandom);    //�������� ������������ ������ �� ������
        System.out.println(res);
        MyClass obj = new MyClass(7);
        res = this.funcMethodRef(obj::getIntValue);    //�������� �������������� ������ �� ������
        System.out.println(res);
        //res = this.funcMethodRef(obj::getIntRandom);    //�������� �������������� ������ �� ������ ����� ������ ������ �� �����������.
                                                          // ���� ������ � ����������� ������� ����� ������� ������ ���������

        //������ �������� ������ �� ������������� ����� ����� ��� ������ (�� ���� ��� ���� �������� ������)
        //�������� � ������� ���� �������� ������ �� ����� ����������� ���� MyInterface<T> methodRef
        //���� ������������� ��������� ����� ����������� ����� � ����� ����������� - boolean func(T caller, T other);
        //� ���������� � �������� ��������� MyInterface<T> methodRef �� �������� ������ �� ����� public boolean sameValue(MyClass other),
        // ������� ����� ������ ���� ��������.
        //������ �� �� ��������� ������? ���� ��������� ��������� ������.
        //��� ���� � ��� ��� ����� public boolean sameValue(MyClass other) ����� ���� ������ ��� ������:
        //this.sameValue(other)
        //� ����� ��� ������ ��� ��� ����� ������� ����� ����������:
        //this.sameValue(this, other)
        //��� ��� ������� this � other ����������� � ������ ������, ���� ��� ��� ����������� � ��������� ������ ��������������� ����������:
        //boolean func(T caller, T other)
        boolean answer = this.funcNoneStaticMethodRef(MyClass::sameValue, MyClass.class);
        System.out.println(answer);

        //������ �� ����������� ������ ������ � ������ �����������
        SuperCaller superCaller = new SuperCaller();
        System.out.println(superCaller.show1());
        System.out.println(superCaller.show2());

        System.out.println("=========������ �� ���������� ������========================================================");
        Integer a1 = new Integer(3);
        Integer a2 = new Integer(4);
        answer = this.genMethodRef(MyClass::genFunc, a1, a2);
        if(answer) {
            System.out.println(a1 + "=" + a2);
        }else{
            System.out.println(a1 + "!=" + a2);
        }

        System.out.println("=========������ �� ������������=============================================================");
        MyClass one = this.constructorRef(MyClass::new);    //�������� �����, � �������� ��������� �������� ��������� ������ �� �����������
        System.out.println(one.getIntValue());

        CtorRefInterface<?> ctorCaller = MyClass::new;    //������� ������ �� ����������� ��� ����������.
        // ��������� ������������ ������������ ����� �������������� ����������
        MyClass two = (MyClass) ctorCaller.constructorCaller();
        System.out.println(two.getIntValue());

        CtorParamRefInterface<?> ctorParamCaller = MyClass::new;    //������� ������ �� ����������� � �����������
        // ��������� ������������ ������������ ����� �������������� ����������
        MyClass three = (MyClass) ctorParamCaller.constructorCaller(10);
        System.out.println(three.getIntValue());
    }

    //����� ��������� ������, ����� �������� ������������� ���������� StringFunc
    //��� ������ ����� ����� ����� ������� ������-���������, ��������������� ��������������� ���������� StringFunc � ������ ���� String
    static String function(StringFunc stringFunc, String str){
        return stringFunc.func(str);
    }

    //����� ��� ������������ �������� ������ �� ����� � �������� ���������
    //������ �������, ������������ ��������� FISimple, ����� ������� ������ �� �����, ������� ��������� �� ��������� �
    //����������� ������� ���������� FISimple
    public static int funcMethodRef(FISimple methodRef){
        return methodRef.getValue();
    }

    //����� ��� ������������ �������� � �������� ��������� ������ �� ������������� ����� (������ �����
    // ��� ������ � �� �������)
    public <T> boolean funcNoneStaticMethodRef(MyInterface<T> methodRef, Class c){
        //��� ��� ����� ����� ����� ���� �� ������������� ��� ���� ����� ����������� � ��� �������,
        // ������� ������������� ������ ������.
        //���������� ��������� ������.
        //� ��������� ���� ������������ ��� ������ ������ ��� ������ � ������ ������� ������ ��������.
        // � ������� ����������� ���
        //����������� ������ ���������� ������� ������ T � �������� ���������� ����� ������.
        //��� �������� �� ���.
        T[]arr = (T[])Array.newInstance(c,2);
        Constructor ctr = null;
        try {
            ctr = c.getConstructor(int.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            arr[0] = (T)ctr.newInstance(10);
            arr[1] = (T)ctr.newInstance(9);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return methodRef.func(arr[0], arr[1]);  //�������� ����� func ���������� MyInterface<T>
    }

    public <T> boolean genMethodRef(MyInterface<T> obj, T first, T second){
        return obj.func(first, second);
    }

    public <T> T constructorRef(CtorRefInterface<T> caller){
        return caller.constructorCaller();
    }
}

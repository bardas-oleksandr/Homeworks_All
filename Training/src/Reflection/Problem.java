package Reflection;

import Interfaces.IProblem;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Problem implements IProblem {
    @Override
    public void solve() {
        Fish fish = new Fish(50);
        System.out.println("Length = " + fish.howLong());

        Class c = fish.getClass();

        Constructor constructor = null;
        try {
            constructor = c.getDeclaredConstructor(int.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        Field[] fields = c.getDeclaredFields();
        Field field = null;

        for(int i = 0; i < fields.length; i++){
            fields[i].setAccessible(true);
            if(fields[i].getType() == int.class){
                field = fields[i];
            }
        }

        try {
            field.set(fish,55);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("New length = " + field.get(fish));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Fish pike = null;
        try {
            pike = (Fish)constructor.newInstance(65);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println("Pike length = " + pike.howLong());


        MyGeneric<Integer> obj = new MyGeneric<>(10);
        Class<?> c1 = obj.getClass();
        try {
            MyGeneric<?> gen1 = (MyGeneric<?>) c1.newInstance();
            System.out.println("" + gen1.getType());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            Field field1 = c1.getDeclaredField("value");
            field1.setAccessible(true);
            try {
                System.out.println(field1.get(obj));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }


        Class c2 = obj.getClass();
        try {
            MyGeneric<?> gen1 = (MyGeneric<?>) c2.newInstance();
            System.out.println("" + gen1.getType());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            Field field1 = c2.getDeclaredField("value");
            field1.setAccessible(true);
            try {
                System.out.println(field1.get(obj));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}

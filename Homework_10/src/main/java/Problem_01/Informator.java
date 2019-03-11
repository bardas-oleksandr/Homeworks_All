package Problem_01;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

public interface Informator {
    static void inform(Class<?> type) {
        System.out.println("CLASS NAME");
        System.out.println(type);

        int modifiers = type.getModifiers();
        System.out.println("CLASS MODIFIERS");
        printClassModifiers(modifiers);

        System.out.println("FIELDS (INCLUDING PUBLIC INHERITED)");
        Field[] fieldsDeclared = type.getDeclaredFields(); //Тут получим все поля, непосредственно объявленные в классе (без унаследованных)
        Field[] fields = type.getFields(); //Тут получим все public-поля (в том числе и унаследованные)
        //Объединим массивы fieldsDeclared и fields с учетом того что они могут иметь общие элементы - public, непосредственно объявленные в классе
        fields = join(fields, fieldsDeclared);
        print(fields);

        System.out.println("CONSTRUCTORS");
        Constructor<?>[] constructors = type.getDeclaredConstructors();
        print(constructors);

        System.out.println("METHODS (INCLUDING PUBLIC INHERITED)");
        Method[] methodsDeclared = type.getDeclaredMethods();
        Method[] methods = type.getMethods();
        methods = join(methods, methodsDeclared);
        print(methods);

        System.out.println("ANNOTATIONS");
        Annotation[] annotations = type.getAnnotations();
        print(annotations);
    }

    static <T extends AccessibleObject> T[] join(T[] destination, T[] source) {
        if(!(destination instanceof Field[]) && !(destination instanceof Method[])){    //Метод расчитан на объединение массивов или типа Field или типа Method
            throw new IllegalArgumentException();
        }
        boolean isField = destination instanceof Field[];
        for (T item : source) {
            int modifiers;
            if (isField) {
                modifiers = ((Field) item).getModifiers();
            } else {
                modifiers = ((Method) item).getModifiers();
            }
            if (!Modifier.isPublic(modifiers)) {
                T[] tmp;
                if (isField) {
                    tmp = (T[]) new Field[destination.length + 1];
                } else {
                    tmp = (T[]) new Method[destination.length + 1];
                }
                for (int i = 0; i < destination.length; i++) {
                    tmp[i] = destination[i];
                }
                tmp[destination.length] = item;
                destination = tmp;
            }
        }
        return destination;
    }

    static <T> void print(T[] source){
        if (source.length != 0) {
            for (int i = 0; i < source.length; i++) {
                System.out.println(source[i]);
            }
        } else {
            System.out.println("The list is empty");
        }
    }

    static void printClassModifiers(int modifiers){
        if(Modifier.isPublic(modifiers)){
            System.out.println("public: Yes");
        }else{
            System.out.println("package level: Yes");
        }
        System.out.print("abstract: ");
        if(Modifier.isAbstract(modifiers)){
            System.out.println("Yes");
        }else{
            System.out.println("No");
        }
        System.out.print("final: ");
        if(Modifier.isFinal(modifiers)){
            System.out.println("Yes");
        }else{
            System.out.println("No");
        }
        System.out.print("static: ");
        if(Modifier.isStatic(modifiers)){
            System.out.println("Yes");
        }else{
            System.out.println("No");
        }
        System.out.print("strictfp: ");
        if(Modifier.isStrict(modifiers)){
            System.out.println("Yes");
        }else{
            System.out.println("No");
        }
    }
}

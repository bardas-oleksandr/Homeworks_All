package Annotations.MarkersAndOneMember;

import Interfaces.IProblem;

import java.lang.reflect.Method;

@OneMemberAnnotation(1) //Сокращенная форма записи для аннотации с одним членом
public class Problem implements IProblem {

    @AnnotationMarker   //Скобки после аннотации без членов необязательны, хотя возможны
    @Override
    public void solve() {
        Class<?> clazz = this.getClass();

        try {
            Method method = clazz.getMethod("solve");
            if (method.isAnnotationPresent(Override.class)) {   //Аннотацию @Override мы получить не сможем из-за ее RetentionPolicy
                System.out.println("Annotation @Override is present in method solve()");
            }
        } catch (NoSuchMethodException e) {
            System.out.println(e.getMessage());
        }

        try {
            Method method = clazz.getMethod("solve");
            if (method.isAnnotationPresent(AnnotationMarker.class)) {
                System.out.println("Annotation @AnnotationMarker is present in method solve()");
            }
        } catch (NoSuchMethodException e) {
            System.out.println(e.getMessage());
        }
    }
}

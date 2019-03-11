package Annotations.Basics;

import Interfaces.IProblem;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Problem implements IProblem {
    @Override
    public void solve() {
        Pike pike = new Pike(3);    //������, ����� �������� ��� �����������

        Class<?> clazz = pike.getClass();
        System.out.println(clazz.getName());

        System.out.println("\n=========getAnnotation===============================================");
        SecondAnnotation ann = clazz.getAnnotation(SecondAnnotation.class); //�������� ���������� ���������
        System.out.println(ann);
        System.out.println("level=" + ann.level());  //�� ����� ����� ���������� � ������ ���������

        try {
            System.out.println("\n=========getConstructor==============================================");
            Constructor fishConstructor = clazz.getConstructor(int.class);
            System.out.println("Constructor: " + fishConstructor);
        }
        catch (NoSuchMethodException e) {
            System.out.println("NoSuchMethodException: " + e.getMessage());
        }

        //���������
        //1. ����� getField() ���� ������ � ��������� ����� �� ������ � �� ��� ������������
        //2. ����� getDeclaredField() ���� ������ �� ���� ����������� (����������������) ����� (��������� � �����������)
        System.out.println("\n��������� ������� getField � getDeclaredField");
        System.out.println("=========getField====================================================");
        try {
            System.out.println("=========public field================================================");
            Field field = clazz.getField("length"); //���� ����� �������� ��� ��� ��� ���������
            System.out.println("field: " + field);
        }
        catch (NoSuchFieldException e) {
            System.out.println("NoSuchFieldException: " + e.getMessage());
        }

        try{
            System.out.println("=========non-public field============================================");
            Field field = clazz.getField("hungry");   //������� ���������� - ���� hungry �� public
            System.out.println("field: " + field);
        }
        catch (NoSuchFieldException e) {
            System.out.println("NoSuchFieldException: " + e.getMessage());
        }

        try {
            System.out.println("=========public field from superclass================================");
            Field field = clazz.getField("age");  //���� ����� �������� ��� ��� ��� ��������� (���� �� �����������)
            System.out.println("field: " + field);
        }
        catch (NoSuchFieldException e) {
            System.out.println("NoSuchFieldException: " + e.getMessage());
        }

        try{
            System.out.println("=========non-public field from superclass============================");
            Field field = clazz.getField("weight");    //����� ������� ���������� - ���� weight �� public
            System.out.println("field: " + field);
        }
        catch (NoSuchFieldException e) {
            System.out.println("NoSuchFieldException: " + e.getMessage());
        }

        System.out.println("\n=========getDeclaredField============================================");
        try {
            System.out.println("=========public field================================================");
            Field field = clazz.getDeclaredField("length"); //���� ����� �������� ��� ��� ��� �� ������������
            System.out.println("field: " + field);
        }
        catch (NoSuchFieldException e) {
            System.out.println("NoSuchFieldException: " + e.getMessage());
        }

        try{
            System.out.println("=========non-public field============================================");
            Field field = clazz.getDeclaredField("hungry"); //���� ����� �������� ��� ��� ��� �� ������������
            System.out.println("field: " + field);
        }
        catch (NoSuchFieldException e) {
            System.out.println("NoSuchFieldException: " + e.getMessage());
        }

        try {
            System.out.println("=========public field from superclass================================");
            Field field = clazz.getDeclaredField("age");  //������� ���������� - ���� ���� age ������������
            System.out.println("field: " + field);
        }
        catch (NoSuchFieldException e) {
            System.out.println("NoSuchFieldException: " + e.getMessage());
        }

        try{
            System.out.println("=========non-public field from superclass============================");
            Field field = clazz.getDeclaredField("weight");    //������� ���������� - ���� weight ���� ������������
            System.out.println("field: " + field);
        }
        catch (NoSuchFieldException e) {
            System.out.println("NoSuchFieldException: " + e.getMessage());
        }

        //���������
        //1. ����� getMethod() ���� ������ � ��������� ������� �� ������ � �� ��� ������������
        //2. ����� getDeclaredMethod() ���� ������ �� ���� ����������� (����������������) ������� (��������� � �����������)
        System.out.println("\n��������� ������� getMethod � getDeclaredMethod");
        Method method = null;
        System.out.println("===============GetMethod=============================================");
        try {
            System.out.println("=============public method===========================================");
            method = clazz.getMethod("publicFromHeir"); //����� ����� ������� ��� ��� �� ���������
            System.out.println("Method: " + method);
        }
        catch (NoSuchMethodException e) {
            System.out.println("NoSuchMethodException: " + e.getMessage());
        }

        try {
            System.out.println("=========non-public method===========================================");
            method = clazz.getMethod("privateFromHeir");    //������� ���������� - ����� �����������
            System.out.println("Method: " + method);
        }
        catch (NoSuchMethodException e) {
            System.out.println("NoSuchMethodException: " + e.getMessage());
        }

        try {
            System.out.println("=========public method from superclass===============================");
            method = clazz.getMethod("publicFromParent"); //����� ����� ������� ��� ��� �� ��������� (���� �� �����������)
            System.out.println("Method: " + method);
        }
        catch (NoSuchMethodException e) {
            System.out.println("NoSuchMethodException: " + e.getMessage());
        }

        try {
            System.out.println("=========non-public method from superclass===========================");
            method = clazz.getMethod("privateFromParent");     //������� ���������� - ����� �����������
            System.out.println("Method: " + method);
        }
        catch (NoSuchMethodException e) {
            System.out.println("NoSuchMethodException: " + e.getMessage());
        }

        System.out.println("\n=========getDeclaredMethod===========================================");
        try {
            System.out.println("=============public method===========================================");
            method = clazz.getDeclaredMethod("publicFromHeir"); //����� ����� ������� ��� ��� �� �� �����������
            System.out.println("Method: " + method);
        }
        catch (NoSuchMethodException e) {
            System.out.println("NoSuchMethodException: " + e.getMessage());
        }

        try {
            System.out.println("=========non-public method===========================================");
            method = clazz.getDeclaredMethod("privateFromHeir"); //����� ����� ������� ��� ��� �� �� �����������
            System.out.println("Method: " + method);
        }
        catch (NoSuchMethodException e) {
            System.out.println("NoSuchMethodException: " + e.getMessage());
        }

        try {
            System.out.println("=========public method from superclass===============================");
            method = clazz.getDeclaredMethod("publicFromParent");   //������� ���������� - ����� �����������
            System.out.println("Method: " + method);
        }
        catch (NoSuchMethodException e) {
            System.out.println("NoSuchMethodException: " + e.getMessage());
        }

        try {
            System.out.println("=========non-public method from superclass===========================");
            method = clazz.getDeclaredMethod("privateFromParent");   //������� ���������� - ����� �����������
            System.out.println("Method: " + method);
        }
        catch (NoSuchMethodException e) {
            System.out.println("NoSuchMethodException: " + e.getMessage());
        }

        System.out.println("\n��������� ������� getAnnotations � getDeclaredAnnotations");
        //���������
        //1. ��� ��� ������ ���������� ���, ��� getDeclaredAnnotations �� �������� � ������������ ������ ���������,
        // ������� ���� ������������. ������������� ��������� ����� ������ ��� ������������� ������� (������ �����������
        // ������ ���������, ������� ���� ������������ ��� ����������� - @Inherited).
        // ������� ������ getAnnotations � getDeclaredAnnotations ���������� ������ ��� ������������� �� ���������
        // � �������
        System.out.println("=========getAnnotations==============================================");
        Annotation[] list = clazz.getAnnotations();//��� �� ������� ���* ��������� �� ������ Pike
        //���� � ����� ����� ��������� ����������� (���������� ��� @Inherited) ��������� �� ����������� Fish
        //*-��� ��������� � ��������� ��������� RUNTIME
        for (Annotation item : list) {
            System.out.println(item);
        }

        System.out.println("\n=========getDeclaredAnnotations======================================");
        list = clazz.getDeclaredAnnotations();  //�������� ��� ��������� ��������������� ��� ������ Pike (��� �����������)
        for (Annotation item : list) {
            System.out.println(item);
        }
    }
}
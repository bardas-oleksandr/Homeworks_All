package ua.levelup;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import ua.levelup.autowired.*;

public class App {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("app-context.xml");

        try{
            PersonByType person = (PersonByType)ctx.getBean("personByType");
            System.out.println("PersonByType was extracted");
            System.out.println("Address: " + person.getAddressByType().getStreet());
            System.out.println("Education: " + person.getEducationByType().getUniversity());
        }catch(NoSuchBeanDefinitionException e){
            System.out.println("Extracting PersonByType failed");
            System.out.println("Exception: " + e.getClass());
        }
        System.out.println("=============================================");

        try{
            PersonByName person = (PersonByName)ctx.getBean("personByName");
            System.out.println("PersonByName was extracted");
            System.out.println("Address: " + person.getAddressByName().getStreet());
            System.out.println("Education: " + person.getEducationByName().getUniversity());
        }catch(NoSuchBeanDefinitionException e){
            System.out.println("Extracting PersonByName failed");
            System.out.println("Exception: " + e.getClass());
        }
        System.out.println("=============================================");

        try{
            PersonConstructor person = (PersonConstructor) ctx.getBean("personConstructor");
            System.out.println("PersonConstructor was extracted");
            System.out.println("Address: " + person.getAddressConstructor().getStreet());
            System.out.println("Education: " + person.getEducationConstructor().getUniversity());
        }catch(NoSuchBeanDefinitionException e){
            System.out.println("Extracting PersonConstructor failed");
            System.out.println("Exception: " + e.getClass());
        }
        System.out.println("=============================================");

        try{
            PersonNo person = (PersonNo)ctx.getBean("personNo");
            System.out.println("PersonNo was extracted");
            System.out.println("Address: " + person.getAddressNo().getStreet());
            System.out.println("Education: " + person.getEducationNo().getUniversity());
        }catch(NoSuchBeanDefinitionException e){
            System.out.println("Extracting PersonNo failed");
            System.out.println("Exception: " + e.getClass());
        }
        System.out.println("=============================================");

        try{
            PersonDefault person = (PersonDefault) ctx.getBean("personDefault");
            System.out.println("PersonDefault was extracted");
            System.out.println("Address: " + person.getAddressDefault().getStreet());
            System.out.println("Education: " + person.getEducationDefault().getUniversity());
        }catch(NoSuchBeanDefinitionException e){
            System.out.println("Extracting PersonDefault failed");
            System.out.println("Exception: " + e.getClass());
        }
        System.out.println("=============================================");

    }
}

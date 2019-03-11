package ua.levelup.XML.MethodReplacement;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

public class MyMethodReplacer implements MethodReplacer {
    @Override
    public Object reimplement(Object o, Method method, Object[] objects) throws Throwable {
        if (isSpeakMethod(method)) {
            System.out.println("I am " + ((Girl)o).getName() + ", and I want you to fuck me as rough as you are able to.");
            return void.class;
        }else{
            throw new IllegalArgumentException("Unable to reimplement method: " + method.getName());
        }
    }

    private boolean isSpeakMethod(Method method) {
        if (method.getParameterTypes().length != 1) {
            return false;
        }
        if (!method.getName().equals("speak")){
            return false;
        }
        if(method.getReturnType() != Void.TYPE){
            return false;
        }
        if(method.getParameterTypes()[0]!= String.class){
            return false;
        }
        return true;
    }
}

package ua.levelup.aspectj.circus;

import org.springframework.stereotype.Service;

@Service("annotatedVolunteer")
public class Volunteer implements Thinker {
    private String thoughts;

    Volunteer(){
        this.thoughts=null;
    }

    @Override
    public void thinkOfSomething(String thoughts) {
        this.thoughts = thoughts;
    }

    @Override
    public String getThoughts() {
        return thoughts;
    }
}

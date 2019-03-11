public class SizeLessThenZero extends Exception {
    @Override
    public String getMessage(){
        return "Array size can't be less then zero.";
    }
}

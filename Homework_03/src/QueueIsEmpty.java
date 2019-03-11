public class QueueIsEmpty extends Exception {
    @Override
    public String getMessage() {
        return "Queue is empty";
    }
}

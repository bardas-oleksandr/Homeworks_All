package ua.levelup;

public class SqueakyMinstrel implements Minstrel {
    @Override
    public void singBefore() {
        System.out.println("Yo'l, 'ol, 'ol, 'ol\n" +
                "Yo'l, 'ol, yeah, 'el\n" +
                "The knight is so brave,\n" +
                "And the dragon is doomed...\n");
    }

    @Override
    public void singAfter() {
        System.out.println("Yo'l, 'ol, 'ol, 'ol\n" +
                "Yo'l, 'ol, yeah, 'el\n" +
                "Rest in peace, brave knight...\n");
    }
}

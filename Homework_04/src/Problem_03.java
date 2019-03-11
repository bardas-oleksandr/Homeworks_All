public class Problem_03 implements IWhatToDo {
    public void solve() {
        System.out.println("WORKING WITH PATTERN \"BUILDER\"");
        System.out.println("Variant 1");
        try{
            Rod rod = Rod.create().hasLength(2.4).hasWeight(200).costs(199.99).hasLowerTest(20).hasHigherTest(30).hasType("Extra fast").madeOf("Carbon").getRod();
            rod.show();
        }
        catch(CountOverflow e){
            System.out.println(e.getMessage());
            System.out.println("Object can't be created!");
        }
        try{
            Rod rod = Rod.create().hasLength(2.5).hasWeight(210).costs(299.99).hasLowerTest(25).hasHigherTest(40).hasType("Fast").madeOf("Carbon").getRod();
            rod.show();
        }
        catch(CountOverflow e){
            System.out.println(e.getMessage());
            System.out.println("Object can't be created!");
        }
        try{
            Rod rod = Rod.create().hasLength(2.4).hasWeight(200).costs(199.99).hasLowerTest(20).hasHigherTest(30).hasType("Extra fast").madeOf("Carbon").getRod();
            rod.show();
        }
        catch(CountOverflow e){
            System.out.println(e.getMessage());
            System.out.println("Object can't be created!");
        }

        System.out.println("Variant 2");
        RodAnother rodAnother = new RodAnother.AnotherRodBuilder().hasLength(2.4).hasWeight(200).costs(199.99).hasLowerTest(20).hasHigherTest(30).hasType("Extra fast").madeOf("Carbon").build();
        rodAnother.show();
    }
}

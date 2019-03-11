public interface Creator {
    static IWhatToDo createProblem(int number){
        switch(number){
            case 1:
                return new Problem_01();
            case 2:
                return new Problem_02();
            case 3:
                return new Problem_03();
            case 4:
                return new Problem_04();
            case 5:
                return new Problem_05();
            default:
                return null;    //Without this line we will have an error "Missing return statement"
        }
    }
}

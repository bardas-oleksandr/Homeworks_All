package Service;

public interface ICreator {
    static IProblem create(int topic, int number){
        switch(topic){
            case 1:
            {
                switch(number){
                    case 1: return new JDBC.Statement.Problem();
                    case 2: return new JDBC.PreparedStatement.Problem();
                    case 3: return new JDBC.SQL_Injection.Problem();
                    case 4: return new JDBC.BLOB.Problem();
                    case 5: return new JDBC.StoredProcedure.Problem();
                }
            }
            default:
                return null;
        }
    }
}

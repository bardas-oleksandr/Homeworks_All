package Problem_03;

public enum StaticUtility {
    ;   //Перечисление пустое

    private static int data = 0;

    public static void setData(int _data){
        data = _data;
    }

    public static int getData(){
        return data;
    }
}

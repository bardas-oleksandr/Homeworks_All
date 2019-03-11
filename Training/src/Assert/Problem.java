package Assert;

import Interfaces.IProblem;
import static java.lang.Math.*; //Статический импорт всех методов класса java.lang.Math

public class Problem implements IProblem {
    @Override
    public void solve() {
        assert this.func(10) >= 3;    //Пример неудачного использования assert. Метод func будет выполнятся только
        // в режиме отладки с активированной проверкой утверждений

        int n = this.func(2);
        assert n < 1 : "Вот же бля";
    }

    public int func(int x){
        return (int)sqrt(x);    //Вызов статически импортированного метода
    }
}

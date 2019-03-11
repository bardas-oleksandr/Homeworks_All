//СУТЬ ПРИМЕРА
//Sender увеличивает запас груза в объекте Freight.
//Receiver забирает этот груз.
//Sender и Receiver работают в параллельных потоках.
//Потоки синхронизированы так что Receiver забирает груз только после того как его доставит Sender.
//Кроме этого, в какой-то момент времени поток Sender останавливается извне (из главного потока)
//При этом поток Receiver также перестает работать, так как он синхронизирован с потоком Sender
//Пример показывает два момента:
//1. Синхронизацию двух потоков, когда потоки сами регулируют какой из них работает а какой ждет.
//Выполняется это с помощью общего объекта класса Locker.
//2. Способ остановки и запуска потока из другого потока. Выполняется это с помощью методов
//waitingWhileSuspended(), pause(), goAhead(). Этот способ не подходит для взаимных
//остановок-запусков. Почему - смотри ОБОБЩЕНИЯ.

// ОБОБЩЕНИЯ
//1. Методы wait() и notify() вызываются только из синхронизированного контекста.
//Например - внутри synchronized-метода. При этом метод не обязательно должен принадлежать
//классу-потоку. Это может быть любой класс. Тогда если у нас есть ОДИН объект (например ThreadsGeneralization.Locker),
//к которому обращаются два класса-потока (например ThreadsGeneralization.Sender и ThreadsGeneralization.Receiver), методы wait() и notify()
//будут работать.

//2. Если надо иметь возможность останавливать и запускать какой-то поток снаружи,
// делать это надо на основе:   переменной типа boolean (хранимой в том же классе, где и метод run())
//                              двух методов - myResume() и mySuspend()
//Если создать такие же методы в другом классе-потоке или просто создать два объекта одного и того же
//класса-потока, создается иллюзия что методы myResume() и mySuspend() можно использовать для взаимной
//синхронизации потоков. (Например - поочередное включение потоков ThreadsGeneralization.Sender и ThreadsGeneralization.Receiver).
//На самом деле так делать НЕЛЬЗЯ. Проблема в том, что каждый поток имеет свою переменную типа boolean,
//которая определяет состояние потока. В таких условиях когда-то неизбежно возникнет следующая ситуация:
//первый поток включает второй поток и не успевает выключить сам себя. Второй поток отработал и
//включает первый поток (при этом второй поток тоже может не успеть выключить сам себя).
//И тут первый поток просто выключает сам себя. Управление снова передается втором потоку и он тоже
//себя выключает. Чтобы такого не было - синхронизация потоков должна выполняться на основе общей
//переменной - например на основе объекта типа ThreadsGeneralization.Locker

//3 - Исходя из пункта 2, для синхронизации двух потоков надо создавать общий объект типа ThreadsGeneralization.Locker,
//который и будет определять какой из потоков работает. Методы класса ThreadsGeneralization.Locker должны быть синхронизированы,
//иначе - из них нельзя будет вызывать методы wait() и notify().
package Threads.ThreadsGeneralization;

import Interfaces.IProblem;

public class Problem implements IProblem {
    public void solve() {
        Freight freight = new Freight();
        final int DELTA = 2;
        Locker locker = new Locker();
        Sender sender = new Sender(freight, DELTA, locker);
        Receiver receiver = new Receiver(freight, DELTA, locker);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        sender.pause();

        System.out.println("Pause - 5 sec");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        sender.goAhead();

        try {
            sender.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
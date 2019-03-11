public class PriorityQueue {
    private Pair queue[];
    private int size;
    private int last;
    public PriorityQueue() throws SizeLessThenZero{
        this(0);
    }
    public PriorityQueue(int size) throws SizeLessThenZero{
        if(size < 0){
            throw new SizeLessThenZero();
        }
        this.size = size;
        if(this.size != 0) {
            this.queue = new Pair[this.size];
        }
        else{
            this.queue = null;
        }
        this.last = -1;
    }
    public void enqueue(Pair pair){
        int pos = 0;    //Позиция, в которую нужно вставить элемент
        if(last > -1) {
            pos = BinarySearch(pair.getPriority(), 0, last); //Ищем куда вставить новый элемент с помощью бинарного поиска
            //При этом pos - номер элемента,который имеет приоритет не выше вставляемого элемента
            //Далее учитываем ситуацию равенства приоритетов найденого элемента queue[pos] и вставляемого pair
            while(pos <= last && pair.getPriority() == queue[pos].getPriority()){
                pos++;
            }
        }
        if(last == size-1){ //Если очередь заполнена, расширяем очередь на один элемент
            Pair tmp[] = new Pair[++size];
            last++;
            for(int i = 0; i < pos; i++) {
                tmp[i] = queue[i];  //Тут мы не копируем весь объект, а просто перенаправляем ссылку.
                // Оба элемента массива теперь указывают на один и тот же объект
            }
            for(int i = size-1; i > pos; i--) {
                tmp[i] = queue[i-1];  //Тут мы не копируем весь объект, а просто перенаправляем ссылку.
                // Оба элемента массива теперь указывают на один и тот же объект
            }
            tmp[pos] = pair;    //Непосредственно вставка нового элемента
            queue = tmp;
        }
        else{   //Если очередь не заполнена до конца
            for(int i=++last; i > pos; i--) {
                queue[i] = queue[i-1];
            }
            queue[pos] = pair;    //Непосредственно вставка нового элемента
        }
    }
    public Pair dequeue() throws QueueIsEmpty{
        if(last == -1){
            throw new QueueIsEmpty();
        }
        //Дальше мы создаем новый объект и в него запишем извлекаемую пару значений. Это нужно для того, чтобы мы после завершения
        // работы метода могли оперировать этой парой не опасаясь за сохранность данных.
        Pair result = new Pair(queue[0].getValue(), queue[0].getPriority());
        for(int i = 0; i < last; i++){
            queue[i]=queue[i+1];    //Объекты не копируем, просто перенаплавляем ссылки
        }
        last--;
        return result;
    }
    public void show(){
        if(last == -1){
            System.out.println("Queue is empty!");
        }
        else {
            for (int i = last; i >= 0; i--) {
                System.out.print("(" + queue[i].getValue() + ";" + queue[i].getPriority() + ")\t");
            }
            System.out.print("\n");
        }
    }
    private int BinarySearch(int priority, int begin, int end){//Метод приватный так как его не предполагается использовать за пределами класса
        //Метод модифицирован с учетом того что необходимо найти не просто элемент с заданным приоритетом, а элемент с заранее неизвестным приоритетом
        //за которым нужно выполнить вставку
        if(begin >= end){
            if(begin < size && priority < queue[begin].getPriority()){  //Это условие учитывает то что мы заранее не знаем какой именно элемент ищем
                begin++;
            }
            return begin;
        }
        else{
            int mid = (begin + end)/2 == begin? (begin + end)/2 + 1: (begin + end)/2;
            if(priority > queue[mid].getPriority()){
                return BinarySearch(priority, begin, mid-1);
            }
            else if(priority < queue[mid].getPriority()){
                return BinarySearch(priority, mid+1, end);
            }else{  //Случай равенства приоритетов
                return mid;
            }
        }
    }
}

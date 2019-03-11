public class Logger {
    private String[] logArray;
    private int count;

    public Logger(){
        this.logArray = null;
        this.count = 0;
    }

    public void addLog(String message){
        if(this.logArray == null || this.logArray.length == count){
            String[] tmp = new String[(int)(this.count * 1.5) + 1];
            for(int i = 0; i < this.count; i++){
                tmp[i] = this.logArray[i];
            }
            this.logArray = tmp;
        }
        this.logArray[this.count++] = message;
    }

    public void print(){
        if(this.logArray != null){
            for(int i = 0; i<this.count; i++){
                System.out.println("===========Log #" + (i+1) + "===========");
                System.out.println(this.logArray[i]);
            }
        }else{
            System.out.println("IAdmin.Logger is empty");
        }
    }
}

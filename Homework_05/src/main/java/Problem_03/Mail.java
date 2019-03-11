package Problem_03;

class Mail implements IDec {
    private StringBuilder head;
    private StringBuilder body;
    private StringBuilder footer;
    private IDec decorator;

    Mail(){
        this.head = this.body = this.footer = null;
    }

    Mail(Mail mail){
        this.head = new StringBuilder(mail.head);
        this.body = new StringBuilder(mail.body);
        this.footer = new StringBuilder(mail.footer);
    }

    Mail(StringBuilder head, StringBuilder body, StringBuilder footer){
        this.head = head;
        this.body = body;
        this.footer = footer;
    }

    void setHead(StringBuilder head){
        this.head = head;
    }

    void setBody(StringBuilder body){
        this.body = body;
    }

    void serFooter(StringBuilder footer){
        this.footer = footer;
    }

    StringBuilder getHead(){
        return this.head;
    }

    public StringBuilder getBody(){
        return this.body;
    }

    StringBuilder getFooter(){
        return this.footer;
    }

    public StringBuilder writeMail(){
        return new StringBuilder().append(head==null?"":head).append(body==null?"":body).append(footer==null?"":footer);
    }
}

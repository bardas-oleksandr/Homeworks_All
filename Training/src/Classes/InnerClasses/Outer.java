package Classes.InnerClasses;

public class Outer {
    private int xOuter;
    private Inner inner;
    private StaticInner staticInner;

    public Outer(int x, int y){
        this.xOuter = x;
        this.inner = new Inner(y);
        this.staticInner = new StaticInner();
    }

    class Inner{
        private int xInner;

        public Inner(int x){
            this.xInner = x;
            Outer.this.xOuter = x+1;  //������������� ���������� ����� ����� ���������������� ������ �� ���� ����� � ������� ����������� ������
                                    //������� ������ �������������� ������ �� ����� ������������ ��� ��������� �����������
        }

        public int getxInner() {
            return xInner;
        }

        //����� ������� ����� ������ �����. ����� ���� �� ������ �������� � main ����� ������ ����������� ������
        //�������� ������ ��������� ������ (���� ���� ���������� ������ ������ �� ������ ����������� ������)
        //� ����� ����� ����� ���������� �������� �������� ��� �������� ������� ��������� ������.
        public Outer getOuter(){
            return Outer.this;
        }
    }

    static class StaticInner{
        private int xStaticInner;

        public StaticInner(){
            this.xStaticInner = 0;
            //Outer.this.xOuter = 1;  //����������� ����� ������� � ������������� ����� � ������� ����������� ������ �� �����
        }

        public int getxStaticInner() {
            return xStaticInner;
        }
    }

    public Inner getInner() {
        return inner;
    }

    public int getxOuter() {
        return xOuter;
    }

    public StaticInner getStaticInner() {
        return staticInner;
    }
}

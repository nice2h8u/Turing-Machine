public class QRule {

   private String a;
   private int move;
   private int rule;
   private String write_symb;
    //алфавит
    //состояние
    //каретка
   //для какого правила какй символ, сдвиг, какое правило переход, символ переписывающийся
    public QRule(String a,String write_symb, int move, int rule ) {
        this.a = a;
        this.move = move;
        this.rule = rule;
        this.write_symb = write_symb;

    }

    public String getWrite_symb() {
        return write_symb;
    }

    public void setWrite_symb(String write_symb) {
        this.write_symb = write_symb;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public int getMove() {
        return move;
    }

    public void setMove(int move) {
        this.move = move;
    }

    public int getRule() {
        return rule;
    }

    public void setRule(int rule) {
        this.rule = rule;
    }
}

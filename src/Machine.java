import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Machine {

    private String alphabet;
    private int dobby; //каретка
    private int k_rule;//текущее правило
    private String k_state_def; //начальное состояние
    private String k_state; //текущее состояние


    private ArrayList<ArrayList<QRule>> rules;


    public Machine(String alphabet, int dobby, String k_state_def, ArrayList<ArrayList<QRule>> rules) {
        this.alphabet = alphabet;
        this.dobby = dobby;
        //this.k_rule = 0;
        this.k_state_def = k_state_def;
        this.k_state = k_state_def;
        this.rules = rules;
    }


    public void turingFunction() {

writingOutput();
        while (k_rule != -1) {
            doOneIteration();
            writingOutput();
        }
    }

    private void doOneIteration() {

        String k_symbol = k_state.substring(dobby, dobby + 1);

        if (alphabet.indexOf(k_symbol) == -1) {
            System.out.println("error");
        } else for (int i = 0; i <= rules.get(k_rule).size() - 1; i++) {


            //if current symbol is in alphabet
            if (rules.get(k_rule).get(i).getA().equals(k_symbol)) {

                //if writting symbol is not empty and in alphabet

                if (!rules.get(k_rule).get(i).getWrite_symb().equals("emp") &
                        (alphabet.contains(rules.get(k_rule).get(i).getWrite_symb())))

                    k_state = k_state.substring(0, dobby)
                            + rules.get(k_rule).get(i).getWrite_symb() +
                            (k_state.substring(dobby + 1, k_state.length()));

                dobby = dobby + rules.get(k_rule).get(i).getMove();
                k_rule = rules.get(k_rule).get(i).getRule();

                return;
            }

        }


    }

    private void writingOutput() {
       /* System.out.println("_____________________________");
        System.out.println("Текущее состояние " + k_state);

        String text = "";
        for (int i=0;i<dobby;i++){
            text = text+" ";
        }
        System.out.println("Положение каретки " +text+ "^");
        String k_symbol = k_state.substring(dobby, dobby + 1);
        if (k_rule!=-1){
        for (int i = 0; i <= rules.get(k_rule).size()-1; i++) {
            if (rules.get(k_rule).get(i).getA().equals(k_symbol)) {

                String move = "";
                switch (rules.get(k_rule).get(i).getMove()) {
                    case 1:
                        move = ">";
                        break;
                    case -1:
                        move = "<";
                        break;
                    case 0:
                        move = "!";
                        break;
                }
                text = "Q" + (i+1) + " "+rules.get(k_rule).get(i).getA() + " will swap with " +
                        rules.get(k_rule).get(i).getWrite_symb() +" "+ move
                        + " Q" + (k_rule+1) ;
                break;
            }


        }
        System.out.println("Текущее правило " + text);}
        else

*/





        try (FileWriter writer = new FileWriter("src/output.txt", false)) {

            String write =System.getProperty("line.separator");
            writer.write(write);


             write =k_state + System.getProperty("line.separator");
            writer.write(write);

            String text = "";
            for (int i=0;i<dobby;i++){
                text = text+" ";
            }

            write =text+ "^" + System.getProperty("line.separator");
            writer.write(write);

            String k_symbol = k_state.substring(dobby, dobby + 1);
            if (k_rule!=-1){
                for (int i = 0; i <= rules.get(k_rule).size()-1; i++) {
                    if (rules.get(k_rule).get(i).getA().equals(k_symbol)) {

                        String move = "";
                        switch (rules.get(k_rule).get(i).getMove()) {
                            case 1:
                                move = ">";
                                break;
                            case -1:
                                move = "<";
                                break;
                            case 0:
                                move = "!";
                                break;
                        }
                        text = "Q" + (k_rule + 1) + " " + rules.get(k_rule).get(i).getA() + " will swap with " +
                                rules.get(k_rule).get(i).getWrite_symb() + " " + move
                                + " Q" + (rules.get(k_rule).get(i).getRule()+1) ;
                        break;
                    }
                }
                write =text + System.getProperty("line.separator");
                writer.write(write);
            }
            else {
                write ="End" + System.getProperty("line.separator");
                writer.write(write);
            }

            writer.flush();
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }

    }
}


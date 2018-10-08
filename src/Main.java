import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

   static public int maxArray(ArrayList<ArrayList<QRule>> rules) {
        int max = 0;
        for (int i = 0; i < rules.size() ; i++) {
            if (max < rules.get(i).size())
                max = rules.get(i).size();
        }
        return max;
    }

    public static void main(String[] args) {
        String[] tempProgram;
        String alphabet = "";
        String k_state = "";
        int dobby = 0;
        int numberOfLine = 1;
        int flag = 1;
        ArrayList<ArrayList<QRule>> rules = new ArrayList<ArrayList<QRule>>();
        ArrayList<QRule> rule = new ArrayList<QRule>();

        Scanner sc = null;
        try {
            sc = new Scanner(new File("src/turing.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (sc.hasNext()) {

            switch (numberOfLine) {
                case 1: {
                    alphabet = sc.nextLine();
                    numberOfLine++;
                    break;
                }
                case 2: {
                    k_state = sc.nextLine();
                    k_state = "___" + k_state + "___";
                    numberOfLine++;
                    break;
                }
                case 3: {
                    numberOfLine++;
                    String temp = sc.nextLine();

                    dobby = temp.length() - temp.replace(" ", "").length() + 3;
                    break;
                }
                default: {
                    String temp = sc.nextLine();
                    tempProgram = temp.split(" ");
                    //if current rule = reading rule
                    if (flag == Integer.parseInt(tempProgram[0])) {
                        rule.add(new QRule(tempProgram[1], tempProgram[2],
                                Integer.parseInt(tempProgram[3]), Integer.parseInt(tempProgram[4]) - 1));
                        if (!sc.hasNext())
                            rules.add(rule);

                    } else {
                        flag = Integer.parseInt(tempProgram[0]);
                        rules.add(rule);
                        rule = new ArrayList<QRule>();
                        rule.add(new QRule(tempProgram[1], tempProgram[2],
                                Integer.parseInt(tempProgram[3]), Integer.parseInt(tempProgram[4]) - 1));
                        //mb error
                    }

                }

            }
        }

        int max = maxArray(rules);
        for (int i = 0 ;i <= rules.size()-1 ;i++)
            for (int j = rules.get(i).size(); j<max;j++ ) {
                if (rules.get(i).size() == max)
                    return;
                rules.get(i).add(new QRule(" ", "_", 0, -1));
            }
        Machine machine = new Machine(alphabet, dobby, k_state, rules);
        machine.turingFunction();
    }
}

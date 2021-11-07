package pl.retsuz.parser;

import java.util.Scanner;

import pl.retsuz.shell.gen.ICommand;

public class DefParser implements IParser {
    ICommand cmdTree;
    Scanner sc;

    public DefParser(ICommand cmdTree) {
        this.cmdTree = cmdTree;
        sc = new Scanner(System.in);
    }

    @Override
    public void doParse() {
        while (true) {
            System.out.print(":3 " + this.cmdTree.getContext().getCurrent().getPath() + " ");
            String line = sc.nextLine();
            try {
                cmdTree.perform(line);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }
}

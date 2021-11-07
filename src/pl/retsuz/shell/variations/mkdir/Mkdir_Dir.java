package pl.retsuz.shell.variations.mkdir;


import pl.retsuz.filesystem.Composite;
import pl.retsuz.shell.gen.ICommand;
import pl.retsuz.shell.variations.gen.CommandVariation;
import pl.retsuz.shell.variations.gen.ICommandVariation;

public class Mkdir_Dir extends CommandVariation {
    public Mkdir_Dir(ICommandVariation next, ICommand parent) {
        super(next, parent, "[a-zA-Z0-9.l_]*");
    }

    @Override
    public void make(String params) {

        Composite c = (Composite) (this.getParent().getContext().getCurrent());
        Composite directory = new Composite();
        directory.setName(params);

        try {
            c.addElement(directory);
            System.out.println("Utworzono katalog " + params);
        } catch (Exception e) {
            System.out.println("Docelowy element nie jest katalogiem lub obecnfiy katalog nie zawiera elementu.");
        }

    }
}
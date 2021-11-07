package pl.retsuz.shell.variations.rm;


import pl.retsuz.filesystem.Composite;
import pl.retsuz.shell.gen.ICommand;
import pl.retsuz.shell.variations.gen.CommandVariation;
import pl.retsuz.shell.variations.gen.ICommandVariation;

public class Rm_Dir extends CommandVariation {
    public Rm_Dir(ICommandVariation next, ICommand parent) {
        super(next, parent, "[a-zA-Z0-9.l_]*");
    }

    @Override
    public void make(String params) {

        Composite c = (Composite) (this.getParent().getContext().getCurrent());
        Composite fileDirectory = new Composite();
        fileDirectory.setName(params);

        try {
            c.removeElement(fileDirectory);
            System.out.println("Usunietpo element " + params);
        } catch (Exception e) {
            System.out.println("Docelowy element nie jest katalogiem lub obecnfiy katalog nie zawiera elementu.");
        }

    }
}
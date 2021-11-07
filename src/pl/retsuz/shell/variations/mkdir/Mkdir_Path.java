package pl.retsuz.shell.variations.mkdir;


import pl.retsuz.filesystem.Composite;
import pl.retsuz.filesystem.IComposite;
import pl.retsuz.shell.gen.ICommand;
import pl.retsuz.shell.variations.gen.CommandVariation;
import pl.retsuz.shell.variations.gen.ICommandVariation;

public class Mkdir_Path extends CommandVariation {
    public Mkdir_Path(ICommandVariation next, ICommand parent) {
        super(next, parent, "[a-zA-Z0-9.l\\/_]*");
    }

    @Override
    public void make(String params) {

        Composite c = (Composite) (this.getParent().getContext().getCurrent());
        String name = params.substring(params.lastIndexOf("/") + 1);
        String path = params.substring(0, params.lastIndexOf("/"));
        Composite directory = new Composite();
        directory.setName(name);

        try {
            IComposite elem = c.findElementByPath(path);
            if(Composite.class.isInstance(elem)){
                c = (Composite) elem;
                c.addElement(directory);
                System.out.println("Utworzono katalog " + name);
            }else System.out.println("Nie ustawiono. Żądany element nie jest katalogiem.");
        } catch (Exception e) {
            System.out.println("Docelowy element nie jest katalogiem lub obecnfiy katalog nie zawiera elementu.");
        }

    }
}
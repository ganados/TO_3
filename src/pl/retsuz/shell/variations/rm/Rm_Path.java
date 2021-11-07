package pl.retsuz.shell.variations.rm;


import pl.retsuz.filesystem.Composite;
import pl.retsuz.filesystem.IComposite;
import pl.retsuz.shell.gen.ICommand;
import pl.retsuz.shell.variations.gen.CommandVariation;
import pl.retsuz.shell.variations.gen.ICommandVariation;

public class Rm_Path extends CommandVariation {
    public Rm_Path(ICommandVariation next, ICommand parent) {
        super(next, parent, "[a-zA-Z0-9.l\\/_]*");
    }

    @Override
    public void make(String params) {

        Composite c = (Composite) (this.getParent().getContext().getCurrent());
        String name = params.substring(params.lastIndexOf("/") + 1);
        String path = params.substring(0, params.lastIndexOf("/"));
        Composite fileDirectory = new Composite();
        fileDirectory.setName(name);

        try {
            IComposite elem = c.findElementByPath(path);
            if(Composite.class.isInstance(elem)){
                c = (Composite) elem;
                c.removeElement(fileDirectory);
                System.out.println("Usunieto element " + name);
            }else System.out.println("Nie ustawiono. Żądany element nie jest katalogiem.");
        } catch (Exception e) {
            System.out.println("Docelowy element nie istnieje lub obecnfiy katalog nie zawiera elementu.");
        }

    }
}
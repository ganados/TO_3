package pl.retsuz.shell.variations.mv;


import pl.retsuz.filesystem.Composite;
import pl.retsuz.filesystem.IComposite;
import pl.retsuz.shell.gen.ICommand;
import pl.retsuz.shell.variations.gen.CommandVariation;
import pl.retsuz.shell.variations.gen.ICommandVariation;

public class Mv_def extends CommandVariation {
    public Mv_def(ICommandVariation next, ICommand parent) {
        super(next, parent, "([a-zA-Z0-9.l\\/_]*\\s[a-zA-Z0-9.l\\/_]*)");
    }

    @Override
    public void make(String params) {

        Composite c = (Composite) (this.getParent().getContext().getCurrent());
        try {
            String[] path = params.split(" ");
            IComposite fileDirectory = c.findElementByPath(path[0]);
            IComposite src = fileDirectory.getParent();
            IComposite dst = c.findElementByPath(path[1]);
            Composite.moveElement(src, dst, fileDirectory);
            System.out.printf("Przeniesiono %s do %s%n", path[0], path[1]);

        } catch (Exception e) {
            System.out.println("Blad przeniesenia " + e.getMessage());
        }

    }
}
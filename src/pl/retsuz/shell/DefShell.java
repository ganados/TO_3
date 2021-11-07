package pl.retsuz.shell;

import pl.retsuz.context.IContext;
import pl.retsuz.shell.gen.ICommand;
import pl.retsuz.shell.specs.Cd;
import pl.retsuz.shell.specs.Ls;
import pl.retsuz.shell.specs.Mkdir;
import pl.retsuz.shell.specs.More;
import pl.retsuz.shell.specs.Mv;
import pl.retsuz.shell.specs.Rm;
import pl.retsuz.shell.specs.Tree;
import pl.retsuz.shell.variations.cd.CD_Path;
import pl.retsuz.shell.variations.cd.CD_ddot;
import pl.retsuz.shell.variations.cd.CD_def;
import pl.retsuz.shell.variations.gen.ICommandVariation;
import pl.retsuz.shell.variations.ls.LS_Def;
import pl.retsuz.shell.variations.ls.LS_Path;
import pl.retsuz.shell.variations.ls.LS_ddot;
import pl.retsuz.shell.variations.mkdir.Mkdir_Dir;
import pl.retsuz.shell.variations.mkdir.Mkdir_Path;
import pl.retsuz.shell.variations.mkdir.Mkdir_def;
import pl.retsuz.shell.variations.more.More_Def;
import pl.retsuz.shell.variations.mv.Mv_def;
import pl.retsuz.shell.variations.rm.Rm_Dir;
import pl.retsuz.shell.variations.rm.Rm_Path;
import pl.retsuz.shell.variations.rm.Rm_def;
import pl.retsuz.shell.variations.tree.Tree_Def;
import pl.retsuz.shell.variations.tree.Tree_Path;
import pl.retsuz.shell.variations.tree.Tree_ddot;

public abstract class DefShell {
    public static ICommand construct(IContext ctx){
        ICommand more = new More(ctx, null);
        ICommandVariation more_def= new More_Def(null, more);
        more.set_default(more_def);

        ICommand tree = new Tree(ctx, more);

        ICommandVariation tree_path= new Tree_Path(null, tree);
        ICommandVariation tree_ddot= new Tree_ddot(tree_path, tree);
        ICommandVariation tree_def= new Tree_Def(tree_ddot, tree);
        tree.set_default(tree_def);

        ICommand cd = new Cd(ctx, tree);

        ICommandVariation cd_path= new CD_Path(null, cd);
        ICommandVariation cd_ddot= new CD_ddot(cd_path, cd);
        ICommandVariation cd_def= new CD_def(cd_ddot, cd);
        cd.set_default(cd_def);

        ICommand ls = new Ls(ctx, cd);
        ICommandVariation ls_path= new LS_Path(null, ls);
        ICommandVariation ls_ddot= new LS_ddot(ls_path, ls);
        ICommandVariation ls_def= new LS_Def(ls_ddot, ls);
        ls.set_default(ls_def);

        ICommand mkdir = new Mkdir(ctx, ls);
        ICommandVariation mkdir_path= new Mkdir_Path(null, mkdir);
        ICommandVariation mkdir_dir= new Mkdir_Dir(mkdir_path, mkdir);
        ICommandVariation mkdir_def= new Mkdir_def(mkdir_dir, mkdir);
        mkdir.set_default(mkdir_def);

        ICommand rm = new Rm(ctx, mkdir);
        ICommandVariation rmdir_path= new Rm_Path(null, rm);
        ICommandVariation rmdir_dir= new Rm_Dir(rmdir_path, rm);
        ICommandVariation rmdir_def= new Rm_def(rmdir_dir, rm);
        rm.set_default(rmdir_def);

        ICommand mv = new Mv(ctx, rm);
        mv.set_pattern(" *([a-zA-Z0-9.l\\/_]*\\s[a-zA-Z0-9.l\\/_]*)");
        ICommandVariation mv_def= new Mv_def(null, mv);
        mv.set_default(mv_def);

        return mv;
    }
}

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){

        List<Atom> atoms = new ArrayList<Atom>();
        atoms.add( new Atom("O",0,0,6));
        atoms.add( new Atom("H2",0,0,2));
        atoms.add( new Atom("Cl",0,0,7));
        atoms.add( new Atom("N",0,0,5));
        atoms.add( new Atom("Ca",0,0,2));
        atoms.add( new Atom("K",0,0,1));
        atoms.add( new Atom("Na",0,0,1));
        atoms.add( new Atom("F",0,0,7));
        atoms.add( new Atom("Ag",0,0,1));
        atoms.add( new Atom("Au",0,0,3));
        atoms.add( new Atom("Pl",0,0,2));
        atoms.add( new Atom("B",0,0,3));
        atoms.add( new Atom("Br",0,0,1));
    }
}

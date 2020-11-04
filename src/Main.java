import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){

        Thread[] t = new Thread[13];

        List<Element> elements = new ArrayList<Element>();
        elements.add( new Atom("O",0,0,6));
        elements.add( new Atom("H2",0,0,2));
        elements.add( new Atom("Cl",0,0,7));
        elements.add( new Atom("N",0,0,5));
        elements.add( new Atom("Ca",0,0,2));
        elements.add( new Atom("K",0,0,1));
        elements.add( new Atom("Na",0,0,1));
        elements.add( new Atom("F",0,0,7));
        elements.add( new Atom("Ag",0,0,1));
        elements.add( new Atom("Au",0,0,3));
        elements.add( new Atom("Pl",0,0,2));
        elements.add( new Atom("B",0,0,3));
        elements.add( new Atom("Br",0,0,1));
        //elements.add( new Molecule(((Atom) (elements.get(1))), ((Atom) (elements.get(2))), elements.get(1).getPositionX(), elements.get(1).getPositionY()));

        Map map = new Map(20);

        for (int i=0; i<13; i++) {
            map.addEntry(((Atom) (elements.get(i))));
            t[i] = new Thread(elements.get(i));
            t[i].start();
        }

    }
}

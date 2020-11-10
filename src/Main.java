import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args){

        Thread[] t = new Thread[13];

        List<Element> elements = new ArrayList<>();
        elements.add( new Atom("O",0,0,6));//6
        elements.add( new Atom("H2",0,1,6));//2
        elements.add( new Atom("Cl",0,2,7));//7
        elements.add( new Atom("N",0,3,2));//5
        elements.add( new Atom("Ca",3,3,2));//2
        elements.add( new Atom("K",1,0,1));
        elements.add( new Atom("Na",1,1,1));
        elements.add( new Atom("F",1,2,7));
        elements.add( new Atom("Ag",1,3,1));
        elements.add( new Atom("Au",2,3,6));
        elements.add( new Atom("Pl",2,0,2));
        elements.add( new Atom("B",2,1,7));
        elements.add( new Atom("Br",2,2,7));

        Map map = Map.getInstance(5);

        Lock[][] locks = new Lock[map.getSize()][map.getSize()];
        for (int i = 0; i < map.getSize(); i++)
            for (int j = 0; j < map.getSize(); j++) {
                locks[i][j] = new ReentrantLock();
            }

        Lock elementsLocks = new ReentrantLock();

        MapHelper mapHelper = MapHelper.getInstance(elements,map,locks,elementsLocks);

        for (int i=0; i<13; i++) {
            map.addEntry(( (elements.get(i))));
        }

        System.out.println("Elements on map at initial coordinates:");
        map.printMap();

        for (int i=0; i<13; i++) {
            t[i] = new Thread(elements.get(i));
        }

        for (int i=0; i<13; i++) {
        t[i].start();
        }

        try {
            for (int i=0; i<13; i++) {
                t[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Elements on map after reactions:");
        map.printMap();
    }
}

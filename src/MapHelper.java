import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MapHelper {
    private static List<Element> elements;
    private static Map map;
    private static CopyOnWriteArrayList<Element> copyElements;
    private static Lock[][] mapLocker;

    public MapHelper(List<Element> elements, Map map, Lock[][] mapLocker) {
        MapHelper.elements = elements;
        MapHelper.map = map;
        copyElements = new CopyOnWriteArrayList<>(elements);
        this.mapLocker = mapLocker;
    }
//map.atomGetter(element.getPositionX(),element.getPositionY())
    public static void operate(Element element) {
        int oldPositionX = element.getPositionX();
        int oldPositionY = element.getPositionY();

        Random rand = new Random();
        int newpositionX = rand.nextInt(map.getSize());
        int newpositionY = rand.nextInt(map.getSize());

        if (mapLocker[newpositionX][newpositionY].tryLock() && mapLocker[element.getPositionX()][element.getPositionY()].tryLock()) {

            try {
                if (!map.verifyIfSlotIsFree(newpositionX, newpositionY)) {
                    if (element instanceof Atom) {
                        for (Element e : copyElements) { //aici ar putea interveni o alta problema de concurenta
                            if (e.getPositionX() == newpositionX && e.getPositionY() == newpositionY && !(e.getName().equals(element.getName()))) {
                                if (e instanceof Atom) {
                                    if (((Atom) element).getValence() + ((Atom) e).getValence() == 8) {
                                        Molecule newMolecule = new Molecule(((Atom) element), ((Atom) e), e.getPositionX(), e.getPositionY());
                                        elements.remove(e);
                                        elements.remove(element);
                                        elements.add(newMolecule);
                                        map.slotSetter("_", element.getPositionX(), element.getPositionY());
                                        map.addEntry(newMolecule);
                                    }
                                }
                            }
                        }
                    }
                } else {
                    map.slotSetter("_", element.getPositionX(), element.getPositionY());
                    element.setPositionX(newpositionX);
                    element.setPositionY(newpositionY);
                    map.addEntry(element);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                mapLocker[newpositionX][newpositionY].unlock();
                mapLocker[oldPositionX][oldPositionY].unlock();
            }
        }

    }

}

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class MapHelper {
    private static List<Element> elements;
    private static Map map;
    private static MapHelper single_instance = null;
    private static Lock[][] mapLocker;
    private static Lock elementsLocker;

    private MapHelper(List<Element> elements, Map map, Lock[][] mapLocker, Lock elementsLocker) {
        MapHelper.elements = elements;
        MapHelper.map = map;
        MapHelper.mapLocker = mapLocker;
        MapHelper.elementsLocker = elementsLocker;
    }

    public static MapHelper getInstance(List<Element> elements, Map map, Lock[][] mapLocker, Lock elementsLocker)
    {
        if (single_instance == null) {
            single_instance = new MapHelper(elements,map,mapLocker,elementsLocker);
        }

        return single_instance;
    }

    public static boolean elementVerifier(Element e){
        boolean result = false;
        if(elements.contains(e))
            result = true;
        return result;
    }

    public static void operate(Element element) {
        int oldPositionX = element.getPositionX();
        int oldPositionY = element.getPositionY();

        Iterator<Element> elementsIterator = elements.iterator();

        Random rand = new Random();
        int newpositionX = rand.nextInt(map.getSize());
        int newpositionY = rand.nextInt(map.getSize());

        if (elementVerifier(element)) {

            if (mapLocker[newpositionX][newpositionY].tryLock() && mapLocker[element.getPositionX()][element.getPositionY()].tryLock() && elementsLocker.tryLock()) {

                try {
                    if (!map.verifyIfSlotIsFree(newpositionX, newpositionY)) {
                        if (element instanceof Atom) {
                            for (Element e : elements) {
                                if (e.getPositionX() == newpositionX && e.getPositionY() == newpositionY && !(e.getName().equals(element.getName()))) {
                                    if (e instanceof Atom) {
                                        if (((Atom) element).getValence() + ((Atom) e).getValence() == 8) {
                                            Molecule newMolecule = new Molecule(((Atom) element), ((Atom) e), e.getPositionX(), e.getPositionY());
                                            elements.remove(e);
                                            elements.remove(element);
                                            elements.add(newMolecule);
                                            map.slotSetter("_", element.getPositionX(), element.getPositionY());
                                            map.addEntry(newMolecule);
                                            break;
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
                    elementsLocker.unlock();
                }
            }
        }

    }

}

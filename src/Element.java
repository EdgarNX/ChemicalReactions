public abstract class Element  implements Runnable{
    protected int positionX;
    protected int positionY;

    public Element(int positionX, int positionY){
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public abstract String getName();

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
}

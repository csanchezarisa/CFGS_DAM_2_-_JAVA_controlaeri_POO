package controlAeri;

public class coordenades {

    private int x;
    private int y;

    public coordenades() {
        x = 0;
        y = 0;
    }

    public coordenades(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}

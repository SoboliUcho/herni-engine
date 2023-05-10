package entity;

public class entita {
    // pozition
    public int xPozition;
    public int yPozition;
    int strange;
    int size;
    int speed;
    public int lives;

    public entita(int x, int y) {
        this.xPozition = x;
        this.yPozition = y;
        this.strange = 0;
        
    }

    void atack() {

    }

    void makeDamage(entita entita){
        entita.lives = entita.lives - strange;
    }

    void moveEntityToPosicion(int newX, int newY) {
        if (newX == xPozition && newY == yPozition) {
            return;
        }
        int x = newX - xPozition;
        int y = newY - yPozition;
        int vector = (int) Math.sqrt((y * y) + (x * x));
        int k = vector / speed;
        // TODO udělat vypočet pohybu
    }

}

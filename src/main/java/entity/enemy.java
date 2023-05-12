package entity;

public class enemy extends entita{
    int eyeshot;
    
    public enemy(int x, int y) {
        super(x, y);
        
        //TODO Auto-generated constructor stub
    }
    void atack (){

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

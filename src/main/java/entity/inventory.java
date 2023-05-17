package entity;

public class inventory {
    element[] inventory = new element[10];

    public inventory() {
        for (element i : inventory) {
            i = null;
        }
    }

    void addEelentToInventory(element element) {
        for (element i : inventory) {
            if (i == null) {
                i = element;
                break;
            }
        }
    }

    
}

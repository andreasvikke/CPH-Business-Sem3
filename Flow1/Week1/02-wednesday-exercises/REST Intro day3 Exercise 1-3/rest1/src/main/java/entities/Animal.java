package entities;

/**
 *
 * @author andreas
 */
public class Animal {
    private String type;
    private int birthYear;
    private String sound;

    public Animal() {
    }

    public Animal(String type, int birthYear, String sound) {
        this.type = type;
        this.birthYear = birthYear;
        this.sound = sound;
    }
}

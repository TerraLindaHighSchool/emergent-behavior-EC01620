import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

/**
 * The world where ants live.
 * 
 * @author Michael Kölling
 * @version 0.1
 */
public class AntWorld extends World
{
    public static final int SIZE = 640;

    /**
     * Create a new world. It will be initialised with a few ant hills
     * and food sources
     */
    public AntWorld()
    {
        super(SIZE, SIZE, 1);
        setPaintOrder(Ant.class, AntHill.class);
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Food food = new Food();
        addObject(food,224,161);
        AntHill antHill = new AntHill();
        addObject(antHill,405,326);
        Food food2 = new Food();
        addObject(food2,229,384);
        Food food3 = new Food();
        addObject(food3,425,572);
        Food food4 = new Food();
        addObject(food4,174,512);
        Food food5 = new Food();
        addObject(food5,562,148);
        Food food6 = new Food();
        addObject(food6,92,210);
        AntHill antHill2 = new AntHill();
        addObject(antHill2,531,530);
        removeObject(antHill2);
        AntHill antHill3 = new AntHill(1000);
        addObject(antHill3,513,468);
    }
}

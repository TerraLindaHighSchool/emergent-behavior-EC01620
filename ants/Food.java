import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
/**
 * Write a description of class Food here.
 * 
 * @author Emerson Coskey
 * @version (a version number or a date)
 */
public class Food extends Actor
{
    private GreenfootImage image;
    private int crumbs;
    private final int size;
    
    public Food()
    {
        crumbs = 100;
        size = 30;
        
        image = new GreenfootImage(size, size);
        updateImage();
    }
    
    /**
     * Act - do whatever the Food wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
    
    private void updateImage()
    {
        Random random = new Random();
        for (int i = 0; i < crumbs; i++)
        {
            int stDev = size / 6;
            int x = (int) (stDev * random.nextGaussian( ) + 3 * stDev);
            int y = (int) (stDev * random.nextGaussian( ) + 3 * stDev);
                
            // keep crumbs in image
            if(x < 0) 
                x = 0;
            if(x >= size) 
                x = size - 1;
            if(y < 0) 
                y = 0;
            if(y >= size) 
                y = size - 1;
     
            Color color = new Color(50, 15, 200);  // pick the color you want by replacing r, g, b with values.
            image.setColorAt(x, y, color);
        }
        setImage(image);
    }
    
    public void removeCrumb()
    {
        crumbs -= 1;
        image.clear();
        updateImage();
        if (crumbs == 0)
        {
            getWorld().removeObject(this);
        }
    }
}

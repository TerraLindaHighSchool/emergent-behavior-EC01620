import greenfoot.*;

/**
 * An ant that collects food.
 * 
 * @author Michael Kölling
 * @version 0.1
 */
public class Ant extends Creature
{
    private boolean carryingFood;
    private GreenfootImage image1;
    private GreenfootImage image2;
    private int followTrailTimeRemaining;
    private int phAvailable;
    private static final int MAX_PH_AVAILABLE = 16;
    private static final int TIME_FOLLOWING_TRAIL = 30;
    
    /**
     * Create an ant with a given home hill. The initial speed is zero (not moving).
     */
    public Ant(AntHill home)
    {
        setHomeHill(home);
        image1 = getImage();
        image2 = new GreenfootImage("ant-with-food.gif");
        
        phAvailable = MAX_PH_AVAILABLE;
        followTrailTimeRemaining = 0;
    }

    /**
     * Do what an ant's gotta do.
     */
    public void act()
    {
        status();
    }
    
    private void checkForFood()
    {
        Food food = (Food) getOneIntersectingObject(Food.class);
        if (food != null) 
        {
            food.removeCrumb();
            carryingFood = true;
            setImage(image2);
        }
    }
    
    private void searchForFood()
    {
        if(followTrailTimeRemaining == 0)
        {
            if (smellsPheromone())
            {
                walkTowardsPheromoneCenter();
            }
            else
            {
                randomWalk();
            }
        }
        else
        {
            followTrailTimeRemaining--;
            walkAwayFromHome();
        }
        checkForFood();
    }

    private boolean atHome()
    {
        if (getHomeHill() != null) {
            return (Math.abs(getX() - getHomeHill().getX()) < 4) && 
                   (Math.abs(getY() - getHomeHill().getY()) < 4);
        }
        else {
            return false;
        }
    }
    
    private void status()
    {
        if (carryingFood)
        {
            walkTowardsHome();
            if (atHome())
            {
                setImage(image1);
                carryingFood = false;
                getHomeHill().countFood();
            }
        } else 
        {
            searchForFood();
            handlePheromoneDrop();
        }
    }
    
    private void handlePheromoneDrop()
    {
        if (phAvailable == MAX_PH_AVAILABLE)
        {
            getWorld().addObject(new Pheromone(), getX(), getY());
            phAvailable = 0;
        }
        else
        {
            phAvailable++;
        }
    }
    
    private boolean smellsPheromone()
    {
        if (getOneIntersectingObject(Pheromone.class) != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    private void walkTowardsPheromoneCenter()
    {
        if (getOneIntersectingObject(Pheromone.class) != null)
        {
            Pheromone pheromone = (Pheromone) getOneIntersectingObject(Pheromone.class);
            if (pheromone.getX() == getX() && pheromone.getY() == getY())
            {
                followTrailTimeRemaining = TIME_FOLLOWING_TRAIL;
            } else
            {
                headTowards(pheromone);
            }
        }
    }
}
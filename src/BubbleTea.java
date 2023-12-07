import java.awt.*;
public class BubbleTea {
    public String name;                //holds the name of the hero
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;
    public int height;
    public boolean isAlive;            //a boolean to denote if the hero is alive or dead.
    public Rectangle rect;
    public boolean isCrashing;

    public BubbleTea(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx = 5;
        dy = 1;
        width = 100;
        height = 100;
        isAlive = true;
        rect = new Rectangle(xpos, ypos, width, height);
        isCrashing = false;
    }

    public void move() { //bounce off east wall
        xpos = xpos + dx;
        ypos = ypos + dy;
        rect = new Rectangle(xpos, ypos, width, height);

    }

    public void bounce(){
        if(xpos < 0){
            dx = -dx;
        }

        if (xpos > 1000-width){ //bounce off west wall
            dx = -dx;
        }

        if(ypos < 0){
            dy = -dy;
        }

        if(ypos > 700-height){
            dy = -dy;
        }

        xpos = xpos + dx;
        ypos = ypos + dy;
        rect = new Rectangle(xpos, ypos, width, height);
    }


    public void wrap(){
        if (xpos < 0){
            xpos = 1000-width;
        }
        if (xpos > 1000-width){ //bounce off west wall
            xpos = 0;
        }

        if(ypos < 0){
            ypos = 700-height;
        }

        if(ypos > 700-height){
            ypos = 0;
        }

        xpos = xpos + dx;
        ypos = ypos + dy;
        rect = new Rectangle(xpos, ypos, width, height);
    }

}

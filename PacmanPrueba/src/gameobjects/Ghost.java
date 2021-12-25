package gameobjects;

import supers.GameObject;

public class Ghost extends GameObject{
    private String icon;
    
    public Ghost() {
        super(24,24,0,0,2);
        icon = "src\\images\\ghost.gif";
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}

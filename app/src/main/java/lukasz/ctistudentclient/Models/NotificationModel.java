package lukasz.ctistudentclient.Models;

import android.media.Image;

/**
 * Created by tukan on 08.01.2017.
 */

public class NotificationModel {

    private String description;
    private int level;
    private int classroom;
    private String placeDescription;
    private int priority;
    private Image image;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getClassroom() {
        return classroom;
    }

    public void setClassroom(int classroom) {
        this.classroom = classroom;
    }

    public String getPlaceDescription() {
        return placeDescription;
    }

    public void setPlaceDescription(String placeDescription) {
        this.placeDescription = placeDescription;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return("Description: "+description+" PlaceLvl: "+level+" PlaceDesc: "+placeDescription+" PlaceCl: "+classroom+" Priority: "+priority);
    }
}

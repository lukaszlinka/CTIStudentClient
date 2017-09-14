package lukasz.ctistudentclient.Models;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.Image;

/**
 * Created by tukan on 08.01.2017.
 */

public class NotificationModel {

    private String description ="Brak...";
    private String scanCode="Brak...";
    private String level="0";
    private String classroom="0";
    private String placeDescription="Brak...";
    private int priority=0;
    private Bitmap image = null;

    public String getScanCode() {
        return scanCode;
    }

    public void setScanCode(String scanCode) {
        this.scanCode = scanCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
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

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {

        Matrix matrix = new Matrix();
        matrix.postRotate(90);
        Bitmap rotatedBitmap = Bitmap.createBitmap(image , 0, 0, image.getWidth(), image.getHeight(), matrix, true);

        this.image = rotatedBitmap;
    }

    @Override
    public String toString() {
        return("Description: "+description+" PlaceLvl: "+level+" PlaceDesc: "+placeDescription+" PlaceCl: "+classroom+" Priority: "+priority);
    }
}

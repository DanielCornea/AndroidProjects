package com.example.dcornea.welcomeclujapp;

/**
 * Created by dcornea on 6/26/17.
 */

public class Location {
    //Declaring private fields of the object
    // these correspond to the list xml

    // Title is for the the white bold title from item list
    private String title;
    // This attribute is for the first raw beneath the title
    private String atribute1;
    // This attribute is for the second raw beneath the title
    private String atribute2;
    // This field is for the image
    private int image_resource1 = NO_IMAGE_PROVIDED;
    // This variable helps us decide wheather there is an image or not
    private static final int NO_IMAGE_PROVIDED = -1;


    // Constructor with image
    public Location(String title, String atribute1, String atribute2, int image_resource1) {
        this.title = title;
        this.atribute1 = atribute1;
        this.atribute2 = atribute2;
        this.image_resource1 = image_resource1;
    }

    //Constructor with no image
    public Location(String title, String atribute1, String atribute2) {
        this.title = title;
        this.atribute1 = atribute1;
        this.atribute2 = atribute2;
    }


    //-----****GETTER AND SETTER methods****----
    public String getTitle() {
        return title;
    }

    public String getAtribute1() {
        return atribute1;
    }

    public String getAtribute2() {
        return atribute2;
    }

    public int getImage_resource1() {
        return image_resource1;
    }

    //here we verify if there is an image or is it absent
    public boolean hasImage1() {
        return image_resource1 != NO_IMAGE_PROVIDED;
    }

}

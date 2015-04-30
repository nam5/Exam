package com.suwonsmartapp.myrss;

/**
 * Created by Shaein on 2015-04-25.
 */
public class RssItem {

    String title;
    String link;
    String description;
    String image;
    String dcDate;

    public RssItem() {

    }

    public RssItem(String title, String link, String description, String image, String dcDate) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.image = image;
        this.dcDate = dcDate;

    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDcDate() {
        return dcDate;
    }

    public void setDcDate(String dcDate) {
        this.dcDate = dcDate;
    }
}

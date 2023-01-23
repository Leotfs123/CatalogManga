package com.leo.catalogmanga.Model;

import java.io.Serializable;

public class Manga implements Serializable
{

    private String MangaTitle;
    private String MangaCover;
    private String MangaShortUrl;
    private String MangaUrl;
    private String MangaSynopsis;
    private String id;
    private String type;
    private String ScrapeDate;

    public Manga()
    {
    }

    public String getMangaTitle() {
        return MangaTitle;
    }

    public void setMangaTitle(String mangaTitle) {
        MangaTitle = mangaTitle;
    }

    public String getMangaCover() {
        return MangaCover;
    }

    public void setMangaCover(String mangaCover) {
        MangaCover = mangaCover;
    }

    public String getMangaShortUrl() {
        return MangaShortUrl;
    }

    public void setMangaShortUrl(String mangaShortUrl) {
        MangaShortUrl = mangaShortUrl;
    }

    public String getMangaUrl() {
        return MangaUrl;
    }

    public void setMangaUrl(String mangaUrl) {
        MangaUrl = mangaUrl;
    }

    public String getMangaSynopsis() {
        return MangaSynopsis;
    }

    public void setMangaSynopsis(String mangaSynopsis) {
        MangaSynopsis = mangaSynopsis;
    }

    public String getType() {return type;}

    public void setType(String type) {this.type = type;}

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

    public String getScrapeDate() {
        return ScrapeDate;
    }

    public void setScrapeDate(String scrapeDate) {
        ScrapeDate = scrapeDate;
    }

/*BOOFPACK*/
}

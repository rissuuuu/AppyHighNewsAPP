package com.appyhigh.newsfeed;

public class News {
    private String title;
    private String urlToImage;
    private String url;
    private String description;
    public News(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public News(String title, String urlToImage, String url, String description){
        this.title=title;
        this.urlToImage=urlToImage;
        this.url=url;
        this.description=description;

    }

}

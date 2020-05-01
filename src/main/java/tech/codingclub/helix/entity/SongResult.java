package tech.codingclub.helix.entity;

public class SongResult {
    private String title;
    private String image_url;
    private String download_link;
    private String source_link;

    public SongResult(String title, String image_url, String source_link, String download_link) {
        this.title = title;
        this.image_url = image_url;
        this.download_link = download_link;
        this.source_link = source_link;
    }


    public String getTitle() {return title;}
    public String getImage_url() {return image_url;}
    public String getDownload_link() {return download_link;}
    public String getSource_link() {return source_link;}


}

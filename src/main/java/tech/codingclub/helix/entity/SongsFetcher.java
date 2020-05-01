package tech.codingclub.helix.entity;

import org.jooq.util.derby.sys.Sys;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import tech.codingclub.helix.global.HttpUrlConnectionExample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SongsFetcher {

    private String songUrl;
    private String keyword;


    public SongsFetcher(String keyword) {
        this.keyword = keyword;
    }

    public SongsFetcher() {

    }

    public SongResult getResult() {
        String image_url = "";
        String title = "";
        String source_link = "";
        String download_link = "";
        String fullUrl = "https://songspk.mobi";
        String response = "";
        songUrl = "https://songspk.mobi/search?q=";
        if(keyword == null || keyword.length() == 0) {
            return null;
        }
        songUrl += keyword;
        try {
            String siteResponse = HttpUrlConnectionExample.sendGet(songUrl);
            Document document = Jsoup.parse(siteResponse);
            Element element = document.body().select(".content-wrapper .archive-body .single-songs figure").get(0);
            response = element.select(".thumb-image a").attr("href");
            fullUrl += response;
            String anotherResponse = HttpUrlConnectionExample.sendGet(fullUrl);
            Document doc = Jsoup.parse(anotherResponse);
            image_url = doc.body().select(".content-wrapper .page-meta-wrapper .row .page-cover img").attr("src");
            title = doc.body().select(".page-meta-wrapper .row .page-meta ul li .text-left").get(0).text();
            source_link = doc.body().select(".page-player-wrapper .media-wrapper audio source").attr("src");
            Elements downloadElements = doc.body().select(".page-zip-wrap .col-body .page-down-btns a");
            download_link = downloadElements.get(1).attr("href");

        } catch(Exception e) {

        }
        SongResult songResult = new SongResult(title, image_url, source_link, download_link);
        return songResult;
    }
}

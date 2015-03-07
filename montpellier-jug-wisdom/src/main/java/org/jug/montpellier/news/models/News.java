package org.jug.montpellier.news.models;

import java.util.Calendar;
import java.util.Date;

public class News {

	public Date date = Calendar.getInstance().getTime();
	public String title = "NO TILE";
	public String content = "NO CONTENT";
	
	public News() {}
	
	public News(String title, String content) {
		this.title = title;
		this.content = content;
	}

    public String toString() {
        return "News :: " + this.title + " => " + this.content + "\n\t the: " + date;
    }
}

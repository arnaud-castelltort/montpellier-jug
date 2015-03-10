package org.jug.montpellier.cartridges.news.models;

import java.util.Calendar;
import java.util.Date;

public class News {

	public Date date = Calendar.getInstance().getTime();
	public String title = "NO TILE";
	public String content = "NO CONTENT";
	public Boolean valid;

	public News() {}
	
	public News(String title, String content) {
		this.title = title;
		this.content = content;
	}
public Boolean getValid() {
    return valid;
}

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String toString() {
        return "News :: " + this.title + " => " + this.content + "\n\t the: " + date;
    }
}

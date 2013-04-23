package net.winterday.android.Model;

import org.json.JSONException;
import org.json.JSONObject;

public class Anime {

	private int id;
	private String title;
	private String url;
	
	public Anime()
	{
		
	}
	
	public Anime(JSONObject source) throws Exception
	{
		title = source.getString("title");

		if (source.has("id"))
			id = source.getInt("id");
		else if (source.has("anime_id"))
			id = source.getInt("anime_id");
		
		if (source.has("url"))
			url = source.getString("url");
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
}

package net.winterday.android.Model;

import java.util.Vector;

import net.winterday.android.Anime.Util.JSONFetcher;

import org.json.JSONArray;
import org.json.JSONObject;


public class AnimeFull extends AnimeSummary {

	private String status;
	private String japaneseTitle = null;

	private Vector<String> genres = new Vector<String>();
	private Vector<String> tags = new Vector<String>();
	
	private Vector<Anime> prequels = new Vector<Anime>();
	private Vector<Anime> sequels = new Vector<Anime>();
	private Vector<Anime> sideStories = new Vector<Anime>();

	public AnimeFull()
	{
		
	}
	
	public AnimeFull(JSONObject source) throws Exception
	{
		super(source);
		
		status = source.getString("status");
		
		addStringArray(source, "tags", tags);
		addStringArray(source, "genres", genres);
		
		addAnimeArray(source, "prequels", prequels);
		addAnimeArray(source, "side_stories", sideStories);
		addAnimeArray(source, "sequels", sequels);
		
		if (source.has("other_titles") && source.getJSONObject("other_titles").has("japanese"))
			japaneseTitle = source.getJSONObject("other_titles").getString("japanese");
			
	}
	
	private void addStringArray(JSONObject object, String sourceName, Vector<String> destination) throws Exception
	{
		if (!object.has(sourceName))
			return;
		
		JSONArray source = object.getJSONArray(sourceName);
		
		for (int i = 0; i < source.length(); i++) {
			destination.add(source.getString(i));
		}
	}
	
	private void addAnimeArray(JSONObject object, String sourceName, Vector<Anime> destination) throws Exception
	{
		if (!object.has(sourceName))
			return;
		
		JSONArray source = object.getJSONArray(sourceName);
		
		for (int i = 0; i < source.length(); i++) {
			destination.add(new Anime(source.getJSONObject(i)));
		}
	}
	
	public String getJapaneseTitle() {
		return japaneseTitle;
	}

	public void setJapaneseTitle(String japaneseTitle) {
		this.japaneseTitle = japaneseTitle;
	}
	
	public Vector<Anime> getPrequels() {
		return prequels;
	}
	public Vector<Anime> getSequels() {
		return sequels;
	}
	public Vector<Anime> getSideStories() {
		return sideStories;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Vector<String> getGenres() {
		return genres;
	}
	public Vector<String> getTags() {
		return tags;
	}
	
	public static AnimeFull findResults(int id) throws Exception
	{
		JSONObject source = JSONFetcher.downloadJSON("http://mal-api.com/anime/" + id);		
		return new AnimeFull(source);
	}
}

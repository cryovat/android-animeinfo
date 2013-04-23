package net.winterday.android.Model;

import java.util.Collection;
import java.util.Vector;

import net.winterday.android.Anime.Util.JSONFetcher;

import org.json.JSONArray;
import org.json.JSONObject;

import android.graphics.drawable.Drawable;
import android.util.Log;

public class AnimeSummary extends Anime {
	
	private String imageUrl;
	private int episodes;
	private String classification;
	private float rating;
	private String synopsis;
	private Drawable image;	
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public AnimeSummary()
	{
		
	}
	
	public AnimeSummary(JSONObject source) throws Exception {
		super(source);
		
		synopsis = source.getString("synopsis");
		type = source.getString("type");
		episodes = source.getInt("episodes");
		classification = source.getString("classification");
		imageUrl = source.getString("image_url");
		rating = source.getLong("members_score");

		try {
			Log.i("AnimeSummary", "Trying to download " + imageUrl);
			image = JSONFetcher.downloadImage(imageUrl);
			Log.i("AnimeSummary", "Succeeded.");
		}
		catch (Exception e)
		{
			Log.e("AnimeSummary", "Failed to download " + imageUrl, e);
		}
	}
	
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	
	public Drawable getImage() {
		return image;
	}
	public void setImage(Drawable image) {
		this.image = image;
	}

	public String getImageUrl() {
		return imageUrl;
	}
	
	public String getTvString() {
		return type + " (" + episodes + ")";
	}
	
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public int getEpisodes() {
		return episodes;
	}
	public void setEpisodes(int episodes) {
		this.episodes = episodes;
	}

	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	
	public static Collection<AnimeSummary> findResults(String query) throws Exception
	{
		JSONObject object = JSONFetcher.downloadJSON("http://mal-api.com/anime/search?q=" + query.replace(' ', '+'));
		Vector<AnimeSummary> animations = new Vector<AnimeSummary>();
		
		JSONArray response = object.getJSONArray("results");
		
		for (int i = 0; i < response.length(); i++) {
			AnimeSummary anime = new AnimeSummary(response.getJSONObject(i));
			
			animations.add(anime);
		}
		
		return animations;
	}
	
}

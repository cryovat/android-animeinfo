package net.winterday.android.Anime.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.drawable.Drawable;

public class JSONFetcher {

	public static JSONObject downloadJSON(String urlString) throws JSONException, IOException {
		String s = downloadString(urlString);
		
		if (s.startsWith("["))
			return new JSONObject("{\"results\":" + s + "}");
		else 
			return new JSONObject(s);		
	}
	
	public static Drawable downloadImage(String url) throws MalformedURLException, IOException
	{
		return Drawable.createFromStream(new URL(url).openStream(), "image");
	}

	private static String downloadString(String urlString) throws IOException {
		BufferedReader reader = null;
		StringBuffer buffer = new StringBuffer();

		URL url = new URL(urlString);
		reader = new BufferedReader(new InputStreamReader(url.openStream()));

		while (reader.ready())
			buffer.append(reader.readLine());

		reader.close();

		return buffer.toString();

	}
}

package net.winterday.android.Anime;

import java.util.Collection;
import java.util.Vector;

import net.winterday.android.Model.AnimeSummary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SearchResultsAdapter extends BaseAdapter {

	private Vector<AnimeSummary> results = new Vector<AnimeSummary>();
	private Context context;
	
	public SearchResultsAdapter(Collection<AnimeSummary> results, Context context)
	{
		this.results.addAll(results);
		this.context = context;
	}
	
	@Override
	public int getCount() {
		return results.size();
	}

	@Override
	public AnimeSummary getItem(int location) {
		return results.get(location);
	}

	@Override
	public long getItemId(int location) {
		return results.get(location).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = LayoutInflater.from(context);
		AnimeSummary result = results.get(position);

		View view = inflater.inflate(R.layout.search_item, parent, false);
		TextView title = (TextView)view.findViewById(R.id.resultTitle);
		TextView synposis = (TextView)view.findViewById(R.id.resultSynopsis);
		TextView episodes = (TextView)view.findViewById(R.id.resultEpisodes);
		TextView rating = (TextView)view.findViewById(R.id.resultRating);
		
		ImageView image = (ImageView)view.findViewById(R.id.resultImage);
		title.setText(result.getTitle());
		synposis.setText(result.getSynopsis());
		image.setImageDrawable(result.getImage());
		episodes.setText(Integer.toString(result.getEpisodes()));
		rating.setText(Float.toString(result.getRating()));
		
		return view;
	}
	
	
	
}

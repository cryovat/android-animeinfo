package net.winterday.android.Anime;

import java.util.Collection;

import net.winterday.android.Model.Anime;
import net.winterday.android.Model.AnimeFull;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout.LayoutParams;

public class DetailsActivity extends Activity {

	private AnimeFull anime;
	
	
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);        
        
        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
        	try {
				anime = AnimeFull.findResults(extras.getInt("id"));
				populate();				
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        else finish();
    }
    
    private void setLabel(int id, String text)
    {
    	TextView view = (TextView)findViewById(id);
    	view.setText(text);
    }
    
    public void showCover(View view)
    {
    	switcher().showPrevious();
    }
    
    public void showDetails(View view)
    {
    	switcher().showNext();
    }
    
    private ViewSwitcher switcher()
    {
    	return null; //(ViewSwitcher)findViewById(R.id.details_switcher);
    }
    
    private void populate() {
	
		setLabel(R.id.details_title, anime.getTitle());
		setLabel(R.id.details_japanese_title, anime.getJapaneseTitle());
		setLabel(R.id.details_tv, anime.getTvString());
		setLabel(R.id.details_classification, anime.getClassification());
		setLabel(R.id.details_rating, Float.toString(anime.getRating()));
		
		setLabel(R.id.details_d_type, anime.getType());
		setLabel(R.id.details_d_synopsis, anime.getSynopsis());
		setLabel(R.id.details_d_episodes, Integer.toString(anime.getEpisodes()));
		setLabel(R.id.details_d_classification, anime.getClassification());
		setLabel(R.id.details_d_rating, Float.toString(anime.getRating()));
		setLabel(R.id.details_d_status, anime.getStatus());
		
		setLabel(R.id.details_d_tags, prettyPrint(anime.getTags()));
		setLabel(R.id.details_d_categories, prettyPrint(anime.getGenres()));
		
		ImageView image = (ImageView)findViewById(R.id.details_cover);
		image.setImageDrawable(anime.getImage());
		
		fillRelatedList(R.id.details_d_prequels, anime.getPrequels());
		fillRelatedList(R.id.details_d_sequels, anime.getSequels());
		fillRelatedList(R.id.details_d_sidestories, anime.getSideStories());
		
		Button viewFull = (Button)findViewById(R.id.details_d_viewFull);
		
		viewFull.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {

					Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://myanimelist.net/anime/" + DetailsActivity.this.anime.getId()));
					DetailsActivity.this.startActivity(i);
					
				}
				catch (Exception e)
				{
					Toast t = Toast.makeText(DetailsActivity.this.getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG);
					t.show();
				}

			}
		});
    }
    
    private void fillRelatedList(int id, Collection<Anime> relatedSeries)
    {
    	if (relatedSeries.size() == 0)
    		return;
    	
    	LinearLayout layout = (LinearLayout)findViewById(id);
    	
    	layout.removeAllViews();
    	
    	for (Anime related : relatedSeries)
    	{
    		Button button = new Button(getApplicationContext());
    		button.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
    		button.setText(related.getTitle());
    		button.setTag(related);
    		button.setTextAppearance(getApplicationContext(), R.style.AnimeButton);
    		button.setBackgroundColor(Color.TRANSPARENT);
    		button.setOnClickListener(new OnClickListener() {				
				@Override
				public void onClick(View view) {
					Button b = (Button)view;
					Anime a = (Anime)b.getTag();
					
					Intent intent = new Intent(DetailsActivity.this, DetailsActivity.class);
					intent.putExtra("id", a.getId());
					
					DetailsActivity.this.startActivity(intent);
				}
			});
    		layout.addView(button);
    	}
    }
    
    private String prettyPrint(Collection<String> strings) {
    	StringBuffer buffer = new StringBuffer();
    	
    	for (String str : strings) {
    		if (buffer.length() > 0)
    			buffer.append(", ");
    		
    		buffer.append(str);
    	}
    	
    	return buffer.toString();
    }
	
}

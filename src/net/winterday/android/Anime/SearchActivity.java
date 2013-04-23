package net.winterday.android.Anime;

import java.io.IOException;
import java.util.Collection;
import java.util.Currency;

import org.json.JSONException;

import net.winterday.android.Anime.Util.JSONFetcher;
import net.winterday.android.Model.AnimeSummary;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SearchActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);        
        
        listView().setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				
				SearchResultsAdapter adapter = (SearchResultsAdapter)parent.getAdapter();
				AnimeSummary result = adapter.getItem(position);
				
				Intent intent = new Intent(SearchActivity.this, DetailsActivity.class);
				intent.putExtra("id", result.getId());
				
				SearchActivity.this.startActivity(intent);
			}        	
		});        
    }
    
    public void search(View view)
    {
    	try {
			Collection<AnimeSummary> results = AnimeSummary.findResults(getQuery());
	        listView().setAdapter(new SearchResultsAdapter(results, this.getApplicationContext()));
			
		} catch (Exception e) {
			Toast toast = Toast.makeText(this, "Something went wrong: " + e.getMessage(), Toast.LENGTH_LONG);
			toast.show();
		}
    }
    
    private ListView listView()
    {
    	return (ListView)findViewById(R.id.resultsList);
    }
    
    private String getQuery() {   	
    	return ((EditText)findViewById(R.id.searchBox)).getText().toString();
    }
}
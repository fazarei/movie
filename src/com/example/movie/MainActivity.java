package com.example.movie;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.movie.model.Movie;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends Activity implements OnItemClickListener {
//I want to test Feature branch
//Create a new change	
	ListView mMovieList;
	ArrayList<Movie> movieArrayList = new ArrayList<Movie>();
	ArrayAdapter<String> movieAdapter;
	Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = this;
		mMovieList = (ListView) findViewById(R.id.movielist);
		mMovieList.setOnItemClickListener(this);
		MovieListTask loaderTask = new MovieListTask();
		loaderTask.execute(); 
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
	private class MovieListTask extends AsyncTask<Void, Void, Void> {
		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {

			dialog = new ProgressDialog(context);
			dialog.setTitle("Loading Movie");
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {

			String jsonData = loadJSONFromAsset();
			Log.d("JsonResponse", "Respons : " + jsonData);
			JSONArray items;
			try {
				items = new JSONArray(jsonData);
				for (int i = 0; i < items.length(); i++) {
					JSONObject movieJsonObjc = items.getJSONObject(i);
					Movie eachMovie = new Movie();
					eachMovie.setTitle(movieJsonObjc.getString("title"));
					eachMovie.setDesc(movieJsonObjc.getString("description"));
					movieArrayList.add(eachMovie);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			dialog.dismiss();
//			MovieAdapter movieAdapter = new MovieAdapter(MainActivity.this,
//					movieArrayList);
			mMovieList.setAdapter(movieAdapter);
		}
	}

	public String loadJSONFromAsset() {
		String json = null;
		try {
			InputStream is = getAssets().open("json.txt");
			int size = is.available();
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();
			json = new String(buffer, "UTF-8");
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
		return json;

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// this is share intent
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(android.content.Intent.EXTRA_TEXT,
				movieArrayList.get(position).getTitle());
		startActivity(intent);
	}
}

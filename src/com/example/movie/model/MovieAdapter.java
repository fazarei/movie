package com.example.movie.model;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.movie.model.Movie;

public class MovieAdapter extends BaseAdapter {

	private ArrayList<Movie> mLstMovies;
	public boolean[] chbfav;

	private Activity activity;

	public MovieAdapter(Activity activity, ArrayList<Movie> listMovies) {
		super();
		this.mLstMovies = listMovies;
		this.activity = activity;
	}

	public int getCount() {
		return mLstMovies.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return 0;
	}

	public static class ViewHolder {
		public TextView txttitle;
		public TextView txtdesc;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder view;
		LayoutInflater inflator = activity.getLayoutInflater();
		if (convertView == null) {
			view = new ViewHolder();
			convertView = inflator.inflate(R.layout.movie_row, null);

			view.txttitle = (TextView) convertView.findViewById(R.id.txttitle);
			view.txtdesc = (TextView) convertView.findViewById(R.id.txtdesc);

			convertView.setTag(view);
		} else {
			view = (ViewHolder) convertView.getTag();
		}

		view.txttitle.setText(mLstMovies.get(position).getTitle());
		view.txtdesc.setText(mLstMovies.get(position).getDesc());

		view.txttitle.setPaintFlags(view.txttitle.getPaintFlags()
				| Paint.UNDERLINE_TEXT_FLAG);

		view.txttitle.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

//				String movieid = view.txtid.getText().toString();
//				Context context = v.getContext();
//
//				Intent intentcheck = new Intent(context, MovieDetail.class)
//						.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//				intentcheck.putExtra("movieid", movieid);
//
//				context.startActivity(intentcheck);

			}
		});

		return convertView;
	}

}

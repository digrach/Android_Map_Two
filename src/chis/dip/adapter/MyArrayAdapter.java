package chis.dip.adapter;

import java.util.List;

import android.content.Context;
import android.widget.ArrayAdapter;

public class MyArrayAdapter extends ArrayAdapter {

	public MyArrayAdapter(Context context, int textViewResourceId , List<String> list) {
		super(context, textViewResourceId, list);
	}
	
	

}

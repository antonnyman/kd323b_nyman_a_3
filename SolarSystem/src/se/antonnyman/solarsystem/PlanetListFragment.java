package se.antonnyman.solarsystem;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PlanetListFragment extends ListFragment {
	
	OnPlanetListSelectedListener monitorCallback;
	
	public interface OnPlanetListSelectedListener {
		public void onPlanetSelected(int position);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		int layout = android.R.layout.simple_list_item_1;
		setListAdapter(new ArrayAdapter<String>(getActivity(), layout, Static.PlanetNames));
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		try {
			monitorCallback = (OnPlanetListSelectedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString() + " must implement OnHeadlineSelectedListener");
		}
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
		if(getFragmentManager().findFragmentById(R.id.planet_fragment) != null) {
			getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		}
	}
	
	public void onListItemClick(ListView listview, View view, int position, long id) {
		monitorCallback.onPlanetSelected(position);
		getListView().setItemChecked(position, true);
	}

}

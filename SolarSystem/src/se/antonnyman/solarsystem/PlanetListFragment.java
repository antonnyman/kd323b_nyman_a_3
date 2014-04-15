package se.antonnyman.solarsystem;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

// Uses ListFragments for displaying a list of items.
public class PlanetListFragment extends ListFragment {
	
//	Monitors what headline is clicked on.
	OnPlanetListSelectedListener monitorCallback;
	
//	MainActivity uses this interface so the Fragment can deliver information
	public interface OnPlanetListSelectedListener {
		
//		This is called when a list item is selected.
		public void onPlanetSelected(int position);
	}
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
//		This creates a new layout with listItems.
		int layout = android.R.layout.simple_list_item_1;
//		setListAdapter populates the ListView with data from the Static arrays.
//		ArrayAdapter deals with the arrays.
//		getActivity() gets an Intent to start the new activity
		setListAdapter(new ArrayAdapter<String>(getActivity(), layout, Static.PlanetNames));
	}
	
//	Called when the Fragment is attached to an Activity
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
//		This sets that the container has implemented the monitorCallback interface
			monitorCallback = (OnPlanetListSelectedListener) activity;
	}
	
//	Called when the Fragment is visible on the screen.
	@Override
	public void onStart() {
		super.onStart();
		
		
//		This checks if the fragment is active. If it is not, then returns the list.
//		The getFragmentManager helps us interact with Fragments associated with the current Activity
		if(getFragmentManager().findFragmentById(R.id.planet_fragment) != null) {
			
//			getListView() gets a ListView of the planetNames.
//			CHOICE_MODE_SINGLE makes sure you can only choose one option from the list.
			getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		}
	}
	
	public void onListItemClick(ListView listview, View view, int position, long id) {
//		Notify the parent activity of the selected item
		monitorCallback.onPlanetSelected(position);
//		Set the item as checked to be highlighted when in two-pane (tablet) layout.
		getListView().setItemChecked(position, true);
	}

}

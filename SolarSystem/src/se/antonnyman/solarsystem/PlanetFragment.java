package se.antonnyman.solarsystem;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class PlanetFragment extends Fragment {
	
//	This is the key for the Bundle, which is used to map which information to hold between activities.
	final static String BUNDLE_POSITION_KEY = "se.antonnyman.solarsystem.CURRENT_POSITION_KEY";

//	This is the default position. Since an array starts with 0, we set this to -1 to show that we want to inflate the
//	planet list
	int currentPosition = -1;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
//		If activity is recreated (like if the screen was rotated), restore the previous article selection
//	    set by onSaveInstanceState().
		if(savedInstanceState != null) {
			currentPosition = savedInstanceState.getInt(BUNDLE_POSITION_KEY);
		}
		
//		Inflate the layout for this fragment. False means that we should be separated from the parent (attachToRoot).
		return inflater.inflate(R.layout.planet_fragment, container, false);
	}
	
//	onSaveInstanceState is used so we can return to the current Fragment when the app is not active.
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

// 		Save current position.
		outState.putInt(BUNDLE_POSITION_KEY, currentPosition);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
//		onStart we check if there are arguments passed to the Fragment (putInt is part of Bundle).
		Bundle arguments = getArguments();
		
//		If there are arguments, update the planet view to the right position
		if(arguments != null) {
			updatePlanetView(arguments.getInt(BUNDLE_POSITION_KEY));

//		  If the currentPosition is not equal to -1 (PlanetListFragment) then update the planet view.
		} else if(currentPosition != -1) {
			updatePlanetView(currentPosition);
		}
	}
	
//	Updates the TextView with the right text passed on from the position. Returns the int text from that position in the array.
	public void updatePlanetView(int position) {
//		Put this in a try/catch if something should fail.
		try {
			TextView planetName = (TextView) getActivity().findViewById(R.id.planetName);
			TextView planetRadius = (TextView) getActivity().findViewById(R.id.planetRadius);
			TextView orbitalPeriod = (TextView) getActivity().findViewById(R.id.orbitalPeriod);
			TextView planetSummaryInformation = (TextView) getActivity().findViewById(R.id.planetSummary);
			ImageView planetImage = (ImageView) getActivity().findViewById(R.id.planetImage);
			
			planetName.setText(Static.PlanetNames[position]);
			planetRadius.setText(Double.toString(Static.PlanetRadius[position]));
			orbitalPeriod.setText(Integer.toString(Static.OrbitalPeriod[position]));
			planetSummaryInformation.setText(Static.PlanetSummary[position]);
			planetImage.setImageResource(Static.PlanetImageId[position]);
		} catch (Exception e){
			Log.i("Error: ", e+" ");
		}
		
		currentPosition = position;
	}

}

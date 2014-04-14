package se.antonnyman.solarsystem;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class PlanetFragment extends Fragment {
	
	final static String ARG_POSITION = "position";
	int currentPosition = -1;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if(savedInstanceState != null) {
			currentPosition = savedInstanceState.getInt(ARG_POSITION);
			Log.i("SS", "Already inflated, getting current position.");
		}
		return inflater.inflate(R.layout.planet_fragment, container, false);
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.i("SS", "Saving instance state.");
		outState.putInt(ARG_POSITION, currentPosition);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		Log.i("SS", "Starting up.");
		
		Bundle arguments = getArguments();
		
		if(arguments != null) {
			updatePlanetView(arguments.getInt(ARG_POSITION));
			Log.i("SS", "Found the argument, setting the article");
		} else if(currentPosition != -1) {
			Log.i("SS", "Setting the article based on saved instance.");
			updatePlanetView(currentPosition);
		}
	}
	
	public void updatePlanetView(int position) {
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
		
	}

}

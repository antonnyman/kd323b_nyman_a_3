package se.antonnyman.solarsystem;

import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


public class MainActivity extends Activity implements PlanetListFragment.OnPlanetListSelectedListener {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.planets_list);
		
		if(findViewById(R.id.fragment_container) != null) {
			Log.i("SS", "It seems we are on a tablet!");
			if(savedInstanceState != null) {
				Log.i("SS", "Restoring fragment from savedInstanceState");
				return;
			}
			Log.i("SS", "Starting the fragmentmanager, adding the fragment to the fragment_container FrameLayout.");
			PlanetListFragment planetList = new PlanetListFragment();
			planetList.setArguments(getIntent().getExtras());
			getFragmentManager().beginTransaction().add(R.id.fragment_container, planetList).commit();
		}
	}
	
	@Override
	public void onPlanetSelected(int position) {
		Log.i("SS", "Selected a planet!");
		PlanetFragment planet = (PlanetFragment) getFragmentManager().findFragmentById(R.id.planet_fragment);
		
		if(planet != null) {
			planet.updatePlanetView(position);
		} else {
			PlanetFragment newPlanet = new PlanetFragment();
			Bundle arguments = new Bundle();
			
			arguments.putInt(PlanetFragment.ARG_POSITION, position);
			newPlanet.setArguments(arguments);
			
			FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
			fragmentTransaction.replace(R.id.fragment_container, newPlanet);
			fragmentTransaction.addToBackStack(null);
			fragmentTransaction.commit();
		}
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.main, menu);
    	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	if (item.getItemId() == R.id.action_about){
    		Dialog dialog = new Dialog(this);
			dialog.setTitle("About");
			dialog.setContentView(R.layout.about_fragment);
			dialog.show();
    		
    		return super.onOptionsItemSelected(item);
    	}
    	return false;
    }

}

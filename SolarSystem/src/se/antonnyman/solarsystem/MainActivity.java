package se.antonnyman.solarsystem;

import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


public class MainActivity extends Activity implements PlanetListFragment.OnPlanetListSelectedListener {
	
	/*
	 * This is the Solar System app, made by Anton Nyman. 
	 * It is meant to be used as a framework learning tool for fragments.
	 * 
	 * Acitivity states goes: onCreate() -> onStart() -> onResume()
	 * Check out http://developer.android.com/reference/android/app/Activity.html
	 */

//	Called when the activity is created. This is called when the app is visible, coming from onPause();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
//		Set the contentView to planets_list. This is the list from PlanetListFragment with the array of planet names.
		setContentView(R.layout.planets_list);
		
//		Check what layout the Activity is using (tablet or phone)
//		If it uses the fragment_container FrameLayout, we need to add the first fragment.
		if(findViewById(R.id.fragment_container) != null) {
			
//			Check if it has to restore from savedInstanceState. 
//			If it has to, then we're not returning anything since we don't want overlapping fragments.
			if(savedInstanceState != null) {
				return;
			}
		
//		As said before, if the fragment_container is used for the first time we need to add the first fragment.	
		PlanetListFragment firstPlanet = new PlanetListFragment();
		
//		In case the activity started with special instructions from the Intent, get the extras as arguments
		firstPlanet.setArguments(getIntent().getExtras());
		
//		Add the fragment to the fragment_container FrameLayout.
		getFragmentManager().beginTransaction().add(R.id.fragment_container, firstPlanet).commit();
		}
	}
	
//	If the OnHeadlineSelectedListener interface is implemented, this method is called when a planet in the list is clicked.
	@Override
	public void onPlanetSelected(int position) {

//		Get the PlanetFragment from the Activity layout.
		PlanetFragment planet = (PlanetFragment) getFragmentManager().findFragmentById(R.id.planet_fragment);

//		If the planet Fragment is already set, then update the planet view with the new position.
		if(planet != null) {	
			planet.updatePlanetView(position);
		} else {
//			Create the Fragent and give it the selected arguments for the planet.
			PlanetFragment newPlanet = new PlanetFragment();
			
//			Bundle is used for passing information between activities.
			Bundle arguments = new Bundle();
			
//			Save the current planet value. putInt is part of Bundle.
			arguments.putInt(PlanetFragment.BUNDLE_POSITION_KEY, position);
			newPlanet.setArguments(arguments);
			
//			FragmentTransaction adds, removes and replaces Fragments. beginTransaction is uesd to begin the edit of Fragments.
			FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
			
//			Sets in/out animations (check the animator folder).
//			The pop values are for backStack (pressing back button).
			fragmentTransaction.setCustomAnimations(R.animator.enter, R.animator.exit, R.animator.pop_enter, R.animator.pop_exit);
			
//			Replace the fragment (or add if there is none) in fragment_container with this fragment.
			fragmentTransaction.replace(R.id.fragment_container, newPlanet);
			
//			Add the transaction to the backStack so the user can navigate using the back button. 
			fragmentTransaction.addToBackStack(null);
			
//			Schedule the transaction of the Fragment
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
//    		Just a simple dialog, probably a better way to do this...
    		Dialog dialog = new Dialog(this);
			dialog.setTitle("About");
			dialog.setContentView(R.layout.about_fragment);
			dialog.show();
    		
    		return super.onOptionsItemSelected(item);
    	}
    	return false;
    }

}

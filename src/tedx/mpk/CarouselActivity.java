package tedx.mpk;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

public class CarouselActivity extends FragmentActivity {

    SimpleAdapter mAdapter;
    ViewPager mPager;
    
    
	private final PlacesManager placesManager = new PlacesManager();
	private List<Place> places;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carousel);
        places = placesManager.getAllPlaces();
        
        mAdapter = new SimpleAdapter(getSupportFragmentManager());

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);
    }
    
    class SimpleAdapter extends FragmentPagerAdapter {
        public SimpleAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
	    	return TestFragment.newInstance(places.get(position % places.size()).getName());
        }

        @Override
        public int getCount() {
            return places.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return places.get(position % places.size()).getName();
        }
    }
    
}

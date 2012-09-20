package tedx.mpk;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;

public final class TestFragment extends Fragment {
    private static final String KEY_PLACE = "TestFragment:Place";
    private Place mPlace;
    
	private TextView mDescrptionText;
	private ImageView mPlaceImage;
	
    public static TestFragment newInstance(Place place) {
        TestFragment fragment = new TestFragment();

        fragment.mPlace = place;

        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if ((savedInstanceState != null) && savedInstanceState.containsKey(KEY_PLACE)) {
            mPlace = (Place) savedInstanceState.getSerializable(KEY_PLACE);
        }
        

       	
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
           	mDescrptionText = (TextView)view.findViewById(R.id.description_text);
           	mPlaceImage = (ImageView)view.findViewById(R.id.monument_image);
    		mDescrptionText.setText(mPlace.getName());
            UrlImageViewHelper.setUrlDrawable(mPlaceImage, mPlace.getImageUrl());    }
    
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.monument_fragment, null);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(KEY_PLACE, mPlace);
    }
}

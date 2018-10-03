package ic.lab.incrediblehome;


import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;


/**
 * A simple {@link Fragment} subclass.
 */
public class ArkademyFragment extends Fragment {

View v;
private Switch aSwitch;
private ImageView imageView;
    public ArkademyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=  inflater.inflate(R.layout.fragment_arkademy, container, false);
       aSwitch = (Switch)v.findViewById(R.id.switch1);
       imageView = (ImageView)v.findViewById(R.id.imageView);
       aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
              if(b) {
                  imageView.setImageResource(R.drawable.lightnyala);
              }
              else {imageView.setImageResource(R.drawable.lampumati);}
           }
       });
        return v;
    }

}

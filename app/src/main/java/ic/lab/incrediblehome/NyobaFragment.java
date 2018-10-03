package ic.lab.incrediblehome;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class NyobaFragment extends Fragment {

View v;
private Button button;
private Switch aSwitch;
private TextView textView;
    public NyobaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_nyoba, container, false);
       // button = (Button)v.findViewById(R.id.button);
        textView = (TextView)v.findViewById(R.id.textView12);
        aSwitch =(Switch)v.findViewById(R.id.switch2);
       // aSwitch.setChecked(true);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    textView.setText("ON!!");}
                    else {textView.setText("");}
            }
        });


        return v;
    }

}

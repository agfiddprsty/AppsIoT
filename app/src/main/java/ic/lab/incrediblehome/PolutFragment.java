package ic.lab.incrediblehome;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import static ic.lab.incrediblehome.BuildConfig.DEVICE_IC;
import static ic.lab.incrediblehome.BuildConfig.URL_API;


/**
 * A simple {@link Fragment} subclass.
 */
public class PolutFragment extends Fragment {
private TextView txt;
View v;
private Button cek;
    private String mURL = URL_API + "/" + DEVICE_IC;

    public PolutFragment() {


        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =inflater.inflate(R.layout.fragment_polut, container, false);
        txt = (TextView)v.findViewById(R.id.textView7) ;
        cek =(Button)v.findViewById(R.id.button3);
        cek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cekStatus();
            }
        });
        cekStatus();
        return v;
    }
    public void cekStatus() {
        new DownloadWebpageTask(new AsyncResult() {
            @Override
            public void onResult(JSONObject object) {
                getStatus(object);
            }
        }).execute(mURL + "/adc/data");

    }

    public void getStatus(JSONObject object) {
        try {
            //JSONObject table = new JSONObject(jsonResponse);
            JSONObject rows = object.getJSONObject("data");
            JSONObject result = rows.getJSONObject("result");
            String pin_4 = result.getString("0");
           // String pin_5 = result.getString("5");
        //    pin_4.
            Log.d("coba", pin_4);
            float a = Float.valueOf(pin_4);
            if (pin_4.contains("63.99")) {
             //   ivAula.setImageResource(R.drawable.telkom_white_open);
                new HTTPAsyncGPIO(getContext()).execute(mURL + "/gpio/control", "5", "1");
            } else if (pin_4.equals("1")) {
               // ivAula.setImageResource(R.drawable.telkom_dark_close);
            }
             else {
                Toast.makeText(getContext(), "Pembacaan status gagal...", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}

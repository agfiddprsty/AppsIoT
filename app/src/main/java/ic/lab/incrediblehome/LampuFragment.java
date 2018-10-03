package ic.lab.incrediblehome;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
import org.json.JSONException;
import org.json.JSONObject;

import static ic.lab.incrediblehome.BuildConfig.DEVICE_IC;
import static ic.lab.incrediblehome.BuildConfig.URL_API;
import static  ic.lab.incrediblehome.BuildConfig.TOKEN_API;
public class LampuFragment extends Fragment implements View.OnClickListener {
    View v;
    private ImageView ivAula = null, ivlampukamarkiri = null, ivlampukamarkanan = null;
    private String mURL = URL_API + "/" + DEVICE_IC;
    private Button onkmr;
    private  Button offkmr;
    public LampuFragment() {
        // Required empty public constructor
    }
    public void cekStatus() {
        new DownloadWebpageTask(new AsyncResult() {
            @Override
            public void onResult(JSONObject object) {
                getStatus(object);
            }
        }).execute(mURL + "/gpio/data");

    }
    public void getStatus(JSONObject object) {
        try {
            //JSONObject table = new JSONObject(jsonResponse);
            JSONObject rows = object.getJSONObject("data");
            JSONObject result = rows.getJSONObject("result");
           // String pin_4 = result.getString("4");
            String pin_5 = result.getString("5");
            Log.d("coba", pin_5 );
            //if (pin_4.equals("0")) {
               // ivAula.setImageResource(R.drawable.telkom_white_open);
            //} else if (pin_4.equals("1")) {
             //   ivAula.setImageResource(R.drawable.telkom_dark_close);
            //}
            if (pin_5.equals("0")) {
            //    ivlampukamarkanan.setImageResource(R.drawable.lampumati);
              //  ivlampukamarkiri.setImageResource(R.drawable.lampumati);
            } else if (pin_5.equals("1")) {
                //ivlampukamarkanan.setImageResource(R.drawable.lightnyala);
                //ivlampukamarkiri.setImageResource(R.drawable.lightnyala);
            } else {
                Toast.makeText(getContext(), "Pembacaan status gagal...", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


    v=inflater.inflate(R.layout.fragment_lampu, container, false);

        // Inflate the layout for this fragment
        cekStatus();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                cekStatus();
                Log.d("MainActivity", "runDelay");
            }
        }, 3000);
        return v;

    }
    View.OnClickListener onClickListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new HTTPAsyncGPIO(getContext()).execute(mURL + "/gpio/control", "5", "1");
           // ivlampukamarkanan.setImageResource(R.drawable.lightnyala);
            //ivlampukamarkiri.setImageResource(R.drawable.lightnyala);
         //   ivlampukamarkiri.setMaxHeight(50);
           // ivlampukamarkanan.setMaxWidth(100);
           // ivlampukamarkanan.
        }
    };
    View.OnClickListener offlmpkmr= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new HTTPAsyncGPIO(getContext()).execute(mURL + "/gpio/control", "5", "0");
           // ivlampukamarkanan.setImageResource(R.drawable.lampumati);
            //ivlampukamarkiri.setImageResource(R.drawable.lampumati);
        }
    };


    @Override
    public void onClick(View view) {

    }
}

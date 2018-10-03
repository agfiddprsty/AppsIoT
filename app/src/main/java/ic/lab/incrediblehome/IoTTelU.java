package ic.lab.incrediblehome;


import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.design.widget.BottomNavigationView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
import static ic.lab.incrediblehome.BuildConfig.DEVICE_IC;
import static ic.lab.incrediblehome.BuildConfig.URL_API;
import static  ic.lab.incrediblehome.BuildConfig.TOKEN_API;
public class IoTTelU extends Fragment {
    View v;
    private FrameLayout layout;
    private LineChart chart;
    private TextView mTextMessage;
    private Float b;
    private TextView suhu,humidity;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_lampu:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_pintu:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_suhu:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };
    private String mURL = URL_API + "/" + DEVICE_IC;

    public SuhuFragment() {


        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.activity_io_ttel_u, container, false);
        // Inflate the layout for this fragment
        //  suhu =(TextView)v.findViewById(R.id.textView11);
        // humidity = (TextView)v.findViewById(R.id.textView13);
        //String a = "10.123";
        //  Integer b = String.valueOf(a);
        // float b=Float.valueOf(a);
        //float c = Float.parseFloat(a);
        //if (c > 10){
        //   suhu.setText("Sedang!!!");
        // }
        //suhu.setText('b');
        //kie gawe chart e fid
        chart = new LineChart(this);
//kie munculna meng layout
        layout.addView(chart);
        // kie ganti model line chart
        chart.setDescription("");
        chart.setNoDataText("Tidak Ada Data");
// kie enable value highlighting ( ora ngerti b.indone )
        chart.setHighlightEnabled(true);
        // kie touch
        chart.setTouchEnabled(true);
        // kie drag
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        chart.setDrawGridBackground(true);
        // ben isa di zoom
        chart.setPinchZoom(true);
        // background color
        chart.setBackgroundColor(Color.LTGRAY);
        // DATA
        LineData data = new LineData();
        data.setValueTextColor(Color.WHITE);
        // Nambahin data ke chart
        chart.setData(data);
        // object
        Legend l = chart.getLegend();
        // customize legend
        l.setForm(Legend.LegendForm.LINE);
        l.setTextColor(Color.WHITE);
// sumbu -x
        XAxis xl = chart.getXAxis();
        xl.setTextColor(Color.WHITE);
        xl.setDrawGridLines(false);
        xl.setAvoidFirstLastClipping(true);
        // sumbu - y
        YAxis yl = chart.getAxisLeft();
        yl.setTextColor(Color.WHITE);
        yl.setAxisMaxValue(100f);
        xl.setDrawGridLines(true);
        YAxis yl2 = chart.getAxisRight();
        yl.setEnabled(false);
    }
    private void addentry(){

        LineData data = chart.getData();
        if(data!=null){
            LineDataSet set = (LineDataSet) data.getDataSetByIndex(0);
            if (set==null){
                set = createset();
                data.addDataSet(set);
                //   Log.d("coba",pin_4);
            }

//
        }
        // getStatus(JSONObject object);
        // cekStatus();

        chart.notifyDataSetChanged();
        chart.setVisibleXRange(6);
        chart.moveViewToX(data.getXValCount() - 7);

    }
    private  LineDataSet createset(){
        LineDataSet set = new LineDataSet(null, "SUHU");
        set.setDrawCubic(true);
        set.setCubicIntensity(0.2f);
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setColor(ColorTemplate.getHoloBlue());
        set.getCircleColor(ColorTemplate.getHoloBlue());
        set.setLineWidth(2f);
        set.setCircleSize(4f);
        set.setFillAlpha(65);
        set.setFillColor(ColorTemplate.getHoloBlue());
        set.setHighLightColor(Color.rgb(244,117,177));
        set.setValueTextColor(Color.WHITE);
        set.setValueTextSize(10f);
        return set;
    }
    public void getStatus(JSONObject object) {
        try {
            //JSONObject table = new JSONObject(jsonResponse);
            JSONObject rows = object.getJSONObject("data");
            JSONObject result = rows.getJSONObject("result");
            String pin_4 = result.getString("temperature");
            String pin_5 = result.getString("humidity");
            Log.d("coba", pin_5 );
            Log.d("coba",pin_4);
            //    String b = "11.1";
            //LineData data = chart.getData();
            // LineDataSet set = (LineDataSet) data.getDataSetByIndex(0);
            float a= Float.parseFloat(pin_4);
            LineData data = chart.getData();
            if(data!=null){
                LineDataSet set = (LineDataSet) data.getDataSetByIndex(0);
                if (set==null){
                    set = createset();
                    data.addDataSet(set);
                    //   Log.d("coba",pin_4);
                }
//data.addEntry(new Entry(float)a,set.getEntryCount());
//
                //buat percobaan realtime karena gk ada data dari sensor
                data.addEntry(new Entry((float) (Math.random())*75 +10f,set.getEntryCount()),0);
                //
                // kalo ada tinggal
                // data.addEntry(new Entry(a,set.getEntryCount()),0);
            }
            // getStatus(JSONObject object);
            // cekStatus();

            chart.notifyDataSetChanged();
            chart.setVisibleXRange(6);
            chart.moveViewToX(data.getXValCount() - 7);
            b = 60.0f;
            //data.addEntry(new Entry((float) b,set.getEntryCount()),0);
//suhu.setText(pin_4);
//humidity.setText(pin_5);
            if(a > 26){
                // suhu.setText("Panas Banget!!");
                NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(this,"1")
                        .setSmallIcon(R.mipmap.ic_launcher) //ikon notification
                        .setContentTitle("Warning!!!") //judul konten
                        .setAutoCancel(true)//untuk menswipe atau menghapus notification
                        .setContentText("Suhu Ruangan A mendekati suhu "+pin_4); //isi text

/*
Kemudian kita harus menambahkan Notification dengan menggunakan NotificationManager
 */

                NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

                notificationManager.notify(1, builder.build()
                );
            }
            else if(a==32){
                NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(this,"1")
                        .setSmallIcon(R.mipmap.ic_launcher) //ikon notification
                        .setContentTitle("Segera Ambil Tindakan") //judul konten
                        .setAutoCancel(true)//untuk menswipe atau menghapus notification
                        .setContentText("Suhu Ruangan A sudah maximum "); //isi text

/*
Kemudian kita harus menambahkan Notification dengan menggunakan NotificationManager
 */

                NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

                notificationManager.notify(2, builder.build()
                );

            }
            else{
                //suhu.setText("masih bagus");
            }

            {
                Toast.makeText(this, "Pembacaan status gagal...", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    public void getStatus(JSONObject object) {
        try {
            //JSONObject table = new JSONObject(jsonResponse);
            JSONObject rows = object.getJSONObject("data");
            JSONObject result = rows.getJSONObject("result");
            String pin_4 = result.getString("temperature");
            String pin_5 = result.getString("humidity");
            Log.d("coba", pin_5 );
            Log.d("coba",pin_4);
            //    String b = "11.1";
            float a= Float.parseFloat(pin_4);
//suhu.setText(pin_4);
//humidity.setText(pin_5);
            if(a > 30){
                // suhu.setText("Panas Banget!!");
            }
            else{
                //suhu.setText("masih bagus");
            }

            {
                Toast.makeText(getContext(), "Pembacaan status gagal...", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    }
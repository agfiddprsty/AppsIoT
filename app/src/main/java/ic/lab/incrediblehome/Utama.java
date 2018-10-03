package ic.lab.incrediblehome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Utama extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama);
        ImageView lampu = (ImageView) findViewById(R.id.imageButton);
        lampu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //   Intent lampu = new Intent(Utama.this,lampu.class);
              //  startActivity(lampu);
            }
        });

    }

    /**
     * Created by ZAQI on 5/9/2018.
     */

    public static class HTTPAsyncDHT {
    }
}

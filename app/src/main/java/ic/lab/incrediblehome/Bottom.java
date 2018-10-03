package ic.lab.incrediblehome;

//import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Bottom extends AppCompatActivity {

    private TextView mTextMessage;
    private FrameLayout mMainFrame;
    private ArkademyFragment arkademyFragment;
private LampuFragment lampuFragment;
private PintuFragment pintuFragment;
private SuhuFragment suhuFragment;
private PolutFragment polutFragment;
private  NyobaFragment jajalfragm;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_lampu:
                    //mTextMessage.setText(R.string.title_home);
                    setFragment(arkademyFragment);
                    return true;
                case R.id.navigation_pintu:
                    //mTextMessage.setText(R.string.title_dashboard);
                    setFragment(pintuFragment);
                    return true;
                case R.id.navigation_suhu:
                    //mTextMessage.setText(R.string.title_notifications);
                    setFragment(suhuFragment);
                    return true;
            }
            return false;
        }
    };

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.commit();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom);
        arkademyFragment = new ArkademyFragment();
lampuFragment = new LampuFragment();
pintuFragment = new PintuFragment();
suhuFragment = new SuhuFragment();
polutFragment = new PolutFragment();
jajalfragm = new NyobaFragment();
mMainFrame = (FrameLayout)findViewById(R.id.main_frame);
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}

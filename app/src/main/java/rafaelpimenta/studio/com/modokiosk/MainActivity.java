package rafaelpimenta.studio.com.modokiosk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.os.UserManager;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] packages = {this.getPackageName()};
        DevicePolicyManager dpm;
        ComponentName cn;


        //TODO: LEMBRAR DE RODAR ESSE COMANDO NO TERMINAL dpm set-device-owner rafaelpimenta.studio.com.modokiosk/.DevAdmin
        dpm = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        cn = new ComponentName(this, DevAdmin.class);
        if (dpm != null) {
        dpm.addUserRestriction(cn, UserManager.DISALLOW_ADJUST_VOLUME);
        dpm.setLockTaskPackages(cn, packages);
        startLockTask();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // full screen mode
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
// display always ON
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
}

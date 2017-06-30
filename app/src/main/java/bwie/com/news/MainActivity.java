package bwie.com.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import static bwie.com.news.R.id.b2;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private NewsFragment newsFragment;
    private DirFragment dirFragment;
    private FragmentTransaction transaction;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.b1).setOnClickListener(this);
        findViewById(R.id.b2).setOnClickListener(this);
        findViewById(R.id.v1).setOnClickListener(this);
        findViewById(R.id.v2).setOnClickListener(this);
        manager = getSupportFragmentManager();

        newsFragment = (NewsFragment) manager.findFragmentById(R.id.f1);
        dirFragment = (DirFragment) manager.findFragmentById(R.id.f2);


        manager.beginTransaction().show(newsFragment).hide(dirFragment).commit();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b1:
                manager.beginTransaction().show(newsFragment).hide(dirFragment).commit();
                break;
            case b2:
                manager.beginTransaction().show(dirFragment).hide(newsFragment).commit();

                break;
            case R.id.v1:
                yiChang();
                break;
            case R.id.v2:

                startActivity(new Intent(getApplication(),ImageViewerActivity.class));
                break;

        }
    }
    private void yiChang() {

        TextView textView=null;
textView.setText("dasd");
    }
}

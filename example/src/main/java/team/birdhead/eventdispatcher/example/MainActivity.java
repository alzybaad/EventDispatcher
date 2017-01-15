package team.birdhead.eventdispatcher.example;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import team.birdhead.eventdispatcher.OnActivityResult;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_IMAGE_COLOR = 0;
    private static final int REQUEST_CODE_BACKGROUND_COLOR = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getClass().getSimpleName());
        setSupportActionBar(toolbar);

        findViewById(R.id.button_image_color).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(SubActivity.createIntent(MainActivity.this), REQUEST_CODE_IMAGE_COLOR);
            }
        });

        findViewById(R.id.button_background_color).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(SubActivity.createIntent(MainActivity.this), REQUEST_CODE_BACKGROUND_COLOR);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!MainActivityEventDispatcher.onActivityResult(this, requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @OnActivityResult(requestCode = REQUEST_CODE_IMAGE_COLOR, resultCode = RESULT_OK)
    void onImageColorSelected(Intent data) {
        ((ImageView) findViewById(R.id.image)).setColorFilter(SubActivity.getColor(data), PorterDuff.Mode.SRC_IN);
    }

    @OnActivityResult(requestCode = REQUEST_CODE_BACKGROUND_COLOR, resultCode = RESULT_OK)
    void onBackgroundColorSelected(Intent data) {
        findViewById(R.id.content).setBackgroundColor(SubActivity.getColor(data));
    }

    @OnActivityResult(resultCode = RESULT_CANCELED)
    void onCancelled() {
        Toast.makeText(this, R.string.cancelled, Toast.LENGTH_SHORT).show();
    }
}

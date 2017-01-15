package team.birdhead.eventdispatcher.example;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import team.birdhead.eventdispatcher.OnOptionsItemSelected;

public class SubActivity extends AppCompatActivity {

    private static final String EXTRA_COLOR = "EXTRA_COLOR";

    public static Intent createIntent(Context context) {
        return new Intent(context, SubActivity.class);
    }

    public static int getColor(Intent data) {
        return data.getIntExtra(EXTRA_COLOR, Color.BLACK);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sub);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getClass().getSimpleName());
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5, LinearLayoutManager.VERTICAL, false);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(new ItemAdapter());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return SubActivityEventDispatcher.onOptionsItemSelected(this, item) || super.onOptionsItemSelected(item);
    }

    @OnOptionsItemSelected(android.R.id.home)
    boolean onHomeSelected() {
        finish();
        return true;
    }

    void onColorSelected(int color) {
        setResult(RESULT_OK, new Intent().putExtra(EXTRA_COLOR, color));
        finish();
    }

    private class ItemAdapter extends RecyclerView.Adapter {

        private final int[] mColors;

        ItemAdapter() {
            final TypedArray array = getResources().obtainTypedArray(R.array.colors);
            try {
                mColors = new int[array.length()];
                for (int i = 0; i < array.length(); ++i) {
                    mColors[i] = array.getColor(i, 0);
                }
            } finally {
                array.recycle();
            }
        }

        @Override
        public int getItemCount() {
            return mColors.length;
        }

        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ItemViewHolder(getLayoutInflater().inflate(R.layout.activity_sub_item, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((ItemViewHolder) holder).setColor(mColors[position]);
        }
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {

        private int mColor;

        ItemViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onColorSelected(mColor);
                }
            });
        }

        void setColor(int color) {
            mColor = color;
            itemView.setBackgroundColor(color);
        }
    }
}

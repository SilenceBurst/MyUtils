package com.sign.myutils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CenterRecyclerViewActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private Context mContext;
    private TextAdapter textAdapter;
    private CenterLayoutManager centerLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center_recycler_view);
        mContext = this;
        mRecyclerView = findViewById(R.id.recycler_view);
        centerLayoutManager = new CenterLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(centerLayoutManager);
        textAdapter = new TextAdapter();
        textAdapter.setClickListener(new ClickListener() {
            @Override
            public void onClick(int position) {
                if (position > 1 && position < 100 - 2) {
                    smoothScrollAndRefreshData(position);
                }
            }
        });
        mRecyclerView.setAdapter(textAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                textAdapter.setSelectIndex(centerLayoutManager.findFirstVisibleItemPosition() + 2);
            }

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    smoothScrollAndRefreshData(centerLayoutManager.findFirstVisibleItemPosition() + 2);
                }
            }
        });
        smoothScrollAndRefreshData(50);
    }

    private void smoothScrollAndRefreshData(int position) {
        if (mRecyclerView != null) {
            mRecyclerView.smoothScrollToPosition(position);
            //根据selectIndex 刷新数据
            //refreshData
        }
    }

    class TextAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private int selectIndex = -1;
        private ClickListener clickListener;

        public void setClickListener(ClickListener clickListener) {
            this.clickListener = clickListener;
        }

        public void setSelectIndex(int selectIndex) {
            this.selectIndex = selectIndex;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            TextView textView = new TextView(parent.getContext());
            textView.setGravity(Gravity.CENTER);
            textView.setLayoutParams(new ViewGroup.LayoutParams(getScreenWidth(mContext) / 4, ViewGroup.LayoutParams.MATCH_PARENT));
            return new RecyclerView.ViewHolder(textView) {
            };
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
            TextView textView = (TextView) holder.itemView;
            textView.setText(String.valueOf(position));
            textView.setTypeface(Typeface.defaultFromStyle(position == selectIndex ? Typeface.BOLD : Typeface.NORMAL));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null) {
                        clickListener.onClick(position);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return 100;
        }
    }

    public interface ClickListener {
        void onClick(int position);
    }

    /**
     * 获取屏幕宽度(px)
     */
    public int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRecyclerView = null;
    }
}

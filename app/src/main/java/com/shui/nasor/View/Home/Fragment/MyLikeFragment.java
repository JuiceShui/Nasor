package com.shui.nasor.View.Home.Fragment;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shui.nasor.Base.BaseNormalFragment;
import com.shui.nasor.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者： max_Shui on 2016/12/28.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public class MyLikeFragment extends BaseNormalFragment {

    @BindView(R.id.infoRecyclerView)
    RecyclerView infoRecyclerView;
    private List<String> data=new ArrayList<>();
    private MyAdapter myAdapter;
    @Override
    protected void initEventAndData() {
        for (int i=0;i<20;i++)
        {
            data.add("Data!!!!"+i);
        }
        myAdapter=new MyAdapter(data,mContext);
        infoRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        infoRecyclerView.setAdapter(myAdapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_user_test_list;
    }

    public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private List<String> mData;
        private Context context;
        private LayoutInflater inflater;

        public MyAdapter(List<String> mData, Context context) {
            this.mData = mData;
            this.context = context;
            this.inflater=LayoutInflater.from(context);
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new myViewHolder(inflater.inflate(R.layout.holder_footer,parent,false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            myViewHolder myViewHolder= (MyAdapter.myViewHolder) holder;
            myViewHolder.imgJokerTVPage.setText(mData.get(position));
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public class myViewHolder extends RecyclerView.ViewHolder{
            @BindView(R.id.imgJokerTVPage)
            TextView imgJokerTVPage;
            @BindView(R.id.cv_item)
            CardView cvItem;

            myViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }
}

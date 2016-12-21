package com.shui.nasor.View.Like.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.shui.nasor.Base.BaseFragment;
import com.shui.nasor.Model.RealmBean.LikeBean;
import com.shui.nasor.Presenter.Contract.LikeContract;
import com.shui.nasor.Presenter.LikePresenter;
import com.shui.nasor.R;
import com.shui.nasor.Utils.RecyclerViewItemTouchHelper;
import com.shui.nasor.View.Like.Adapter.LikeAdapter;
import com.shui.nasor.View.Zhihu.Decoration.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

/**
 * 作者： max_Shui on 2016/12/11.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public class LikeFragment extends BaseFragment<LikePresenter> implements LikeContract.View {

    @BindView(R.id.likeRecyclerView)
    RecyclerView likeRecyclerView;
    private LikeAdapter mAdapter;
    private List<LikeBean> mData=new ArrayList<>();
    private SpacesItemDecoration spacesItemDecoration;
    private boolean isFirst=true;
    private RecyclerViewItemTouchHelper mItemTouchHelper;
    @Override
    protected void InjectView() {
        getFragmentComponent().Inject(this);
    }

    @Override
    protected void initEventAndData() {
        isFirst=false;//标记为不是第一次
        spacesItemDecoration=new SpacesItemDecoration(3);
        mAdapter=new LikeAdapter(mData,mContext);
        likeRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        likeRecyclerView.setAdapter(mAdapter);
        mItemTouchHelper=new RecyclerViewItemTouchHelper(new RecyclerViewItemTouchHelper.OnItemTouchCallbackListener() {
            @Override
            public void onSwiped(int adapterPosition) {
                //滑动删除
                if (mData!=null)
                {
                    mPresenter.deleteData(mData.get(adapterPosition).getId());//从数据库删除
                    mData.remove(adapterPosition);//从集合中移除
                    mAdapter.notifyItemRemoved(adapterPosition);//刷新UI
                }
            }

            @Override
            public boolean onMove(int srcPosition, int targetPosition) {
                if (mData!=null)
                {
                    boolean isUp=srcPosition<targetPosition;//是否是向下更新
                    /**
                     * 先获取滑动的item的id  mData.get(srcPosition).getId()
                     * 再获取 目标，即是滑动过后的位置 item的时间
                     * 如果是向下 则是时间越近 所以是up
                     */
                    mPresenter.changeTime(mData.get(srcPosition).getId(),mData.get(targetPosition).getTime(),isUp);//更改数据库中的时间
                    Collections.swap(mData,srcPosition,targetPosition);//在集合中变更位置
                    mAdapter.notifyItemMoved(srcPosition,targetPosition);//刷新UI
                    return true;
                }
                return false;
            }
        });
        mItemTouchHelper.setDragEnable(true);//设置可拖拽
        mItemTouchHelper.setSwipeEnable(true);//设置可滑动
        ItemTouchHelper helper=new ItemTouchHelper(mItemTouchHelper);
        helper.attachToRecyclerView(likeRecyclerView);//绑定recyclerView
        mPresenter.getData();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_like;
    }

    @Override
    public void showData(List<LikeBean> data) {
        System.out.println("size"+data.size());
        mData.clear();
        mData.addAll(data);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isFirst) {
            mPresenter.getData();
        }
    }
}

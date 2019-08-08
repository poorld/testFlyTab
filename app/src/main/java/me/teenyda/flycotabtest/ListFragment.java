package me.teenyda.flycotabtest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.teenyda.flycotabtest.adapter.ListAdapter;

public class ListFragment extends Fragment {

    private XRecyclerView list_rv;

    private List<Integer> mList;
    private ListAdapter mListAdapter;
    private boolean refresh = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_list, null);
        list_rv = view.findViewById(R.id.list_rv);

        list_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        list_rv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                // 刷新
                refresh = true;
                List<Integer> list = refreshData();
                setData(list);
            }

            @Override
            public void onLoadMore() {
                // 加载更多
                refresh = false;
                List<Integer> list = refreshData();
                setData(list);
            }
        });

        mList = new ArrayList<>();

        mListAdapter = new ListAdapter(getContext(), mList);

        list_rv.setAdapter(mListAdapter);

        return view;
    }

    /**
     * 模拟获取数据
     */
    public List<Integer> refreshData() {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        int i = random.nextInt(10);
        for (int j = 0; j < i; j++) {
            list.add(random.nextInt(100));
        }
        return list;
    }

    /**
     * 设置数据
     */
    public void setData(List<Integer> list) {

        // 刷新
        if (refresh) {

            if (list != null && list.size() > 0) {
                mList.clear();
                mList.addAll(list);
                mListAdapter.notifyDataSetChanged();
            }

            // 刷新完成
            list_rv.refreshComplete();

        // 加载更多
        } else {
            if (list != null && list.size() > 0) {
                mList.addAll(list);
                mListAdapter.notifyDataSetChanged();
                // 加载完成
                list_rv.loadMoreComplete();
            } else {
                // 没有数据，设置不能加载更多
                list_rv.setNoMore(true);
            }
        }


    }

    /**
     * 调用刷新 获取数据
     */
    public void getList() {
        if (list_rv == null)
            return;

        list_rv.refresh();
    }


}

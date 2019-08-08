package me.teenyda.flycotabtest.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import me.teenyda.flycotabtest.R;

/**
 * 适配器
 */
public class ListAdapter extends XRecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<Integer> mList;

    private Context mContext;

    public ListAdapter(Context context, List<Integer> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Integer integer = mList.get(i);
        viewHolder.item_list_tv.setText(integer + "");
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    class ViewHolder extends XRecyclerView.ViewHolder{

        private TextView item_list_tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            item_list_tv = itemView.findViewById(R.id.item_list_tv);
        }
    }
}

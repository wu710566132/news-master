package bwie.com.news;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by $USER_NAME on 2017/6/30.
 */

public class MyBaseAdapter extends BaseAdapter {

    List<Bean.LayoutsBean> list;
    Context context;

    public MyBaseAdapter(List<Bean.LayoutsBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = View.inflate(context, android.R.layout.simple_list_item_1, null);

        }
        TextView textView = (TextView) convertView.findViewById(android.R.id.text1);

            textView.setText(list.get(position).getName());

        return convertView;
    }
}

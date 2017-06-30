package bwie.com.news;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.R.attr.button;

public class DirFragment extends Fragment implements View.OnClickListener {

    private ListView lv;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            list = (List<Bean.LayoutsBean>) msg.obj;
            adapter = new MyBaseAdapter(list, getActivity());
            lv.setAdapter(adapter);
        }
    };
    private ListView pop;
    private MyBaseAdapter adapter;
    private List<Bean.LayoutsBean> list;
    private View view;
    private ListView poplist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dir, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.dir_b).setOnClickListener(this);
        lv = (ListView) view.findViewById(R.id.lv);
        getList();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getActivity(), list.get(position).getName(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dir_b:
                pop();
                pw.showAsDropDown(view);
                break;
        }
    }


    public void getList() {


        OkHttpClient client = new OkHttpClient();


        Request request = new Request.Builder()
                .url("http://h5test.newaircloud.com/api/getLayouts?sid=xkycs&cid=10024&date=")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson = new Gson();
                System.out.println("string = " + string);
                Bean bean = gson.fromJson(string, Bean.class);
                List<Bean.LayoutsBean> layouts = bean.getLayouts();

                Message msg = new Message();
                msg.obj = layouts;
                handler.sendMessage(msg);

            }

        });


    }

    private PopupWindow pw;

    private void pop() {
        View view = View.inflate(getActivity(), R.layout.pop, null);

        poplist = (ListView) view.findViewById(R.id.pop);
poplist.setAdapter(adapter);
        pw = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        pw.setOutsideTouchable(true);
        pw.setFocusable(true);
        pw.setBackgroundDrawable(new ColorDrawable(Color.GRAY));
    }

}

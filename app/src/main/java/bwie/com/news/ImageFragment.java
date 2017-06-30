package bwie.com.news;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ImageFragment extends Fragment {

    private ImageView viewpage;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            List<Bean.LayoutsBean> list = (List<Bean.LayoutsBean>) msg.obj;
            Bundle arguments = getArguments();
            int pos = arguments.getInt("pos");
            ImageLoader.getInstance().displayImage(list.get(pos).getPicUrl(),viewpage);
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.image, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewpage = (ImageView) view.findViewById(R.id.viewpage);
        getList();

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
}

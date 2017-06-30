package bwie.com.news;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import java.util.List;

public class NewsFragment extends Fragment {

    private ViewPager vp;
    private List<Bean.LayoutsBean> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.news, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        vp = (ViewPager) view.findViewById(R.id.vp);


        vp.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                ImageFragment imageFragment = new ImageFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("pos", position);
                imageFragment.setArguments(bundle);
                return imageFragment;
            }

            @Override
            public int getCount() {
                return 15;
            }
        });

    }



}

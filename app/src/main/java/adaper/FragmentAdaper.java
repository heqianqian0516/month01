package adaper;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import fragment.ClassifyFragment;
import fragment.CountFargment;
import fragment.HomeFragment;


public class FragmentAdaper extends FragmentPagerAdapter {
    String[] str = new String[]{
            "首页","分类","觅Me","购物车","我的"
    };
    public FragmentAdaper(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new HomeFragment();
            case 1:
                return new ClassifyFragment();
                default:
                    return new CountFargment();
        }
    }

    @Override
    public int getCount() {
        return str.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return str[position];
    }
}

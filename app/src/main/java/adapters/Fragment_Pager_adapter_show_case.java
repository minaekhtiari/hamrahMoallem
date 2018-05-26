package adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import fragments.Fragment_Register;
import fragments.Fragment_show_case1;
import fragments.Fragment_show_case2;
import fragments.Fragment_show_case3;
import fragments.Fragment_show_case4;
import fragments.Fragment_show_case5;
import fragments.Fragment_show_case6;

/**
 * Created by A.Mohammadi on 1/7/2018.
 */

public class Fragment_Pager_adapter_show_case extends FragmentPagerAdapter {


    public Fragment_Pager_adapter_show_case(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 : return new Fragment_show_case1();
            case 1 : return new Fragment_show_case2();
            case 2 : return new Fragment_show_case3();
            case 3 : return new Fragment_show_case4();
            case 4 : return new Fragment_Register();
            default:return null;
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}

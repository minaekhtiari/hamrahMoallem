package adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import apps.hillavas.com.hamrahdars.R;
import fragments.Fragment_AboutUs;
import fragments.Fragment_Base;
import fragments.Fragment_Messages;
import fragments.Fragment_consultant;
import fragments.Fragment_content2;
import fragments.Fragment_pager;
import fragments.Fragment_personal;
import fragments.PlusOneFragment;

/**
 * Created by A.Mohammadi on 1/14/2018.
 */

public class BottomNavigationAdapter extends FragmentPagerAdapter {

    public BottomNavigationAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);

    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
//            case 4:
//                return  new Fragment_personal();
            case 3:
                return  new Fragment_personal();
            case 2:
                return  new Fragment_content2();
            case 1:
                return  new Fragment_Base();
            case 0:
                return  new Fragment_Messages();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

//    @Override
//    public CharSequence getPageTitle(int position) {
//        switch (position) {
//            case 4:
//                return "سایر";
//            case 3:
//                return "جستجو";
////            case 2:
////                return "فروشگاه";
//            case 2:
//                return "فیلم\u200Cها";
//            case 1:
//                return "کتاب\u200Cها";
//        }
//        return "";
//    }
//
//    public int getPageIcon(int position) {
//        switch (position) {
//            case 4:
//                return R.drawable.ic_people;
//            case 3:
//                return R.drawable.ic_shot;
//            case 2:
//                return R.drawable.ic_match;
//            case 1:
//                return R.drawable.ic_subscribe;
//            case 0:
//                return R.drawable.ic_news;
//        }
//        return 0;
//    }
}

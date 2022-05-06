package hcmute.edu.vn.caodinhsyvy_19110143.foody2.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import hcmute.edu.vn.caodinhsyvy_19110143.foody2.fragment.AccountFragment;
import hcmute.edu.vn.caodinhsyvy_19110143.foody2.fragment.HomeFragment;
import hcmute.edu.vn.caodinhsyvy_19110143.foody2.fragment.OrdersFragment;

public class ContainerViewPagerAdapter extends FragmentStatePagerAdapter {
    public ContainerViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm,behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return new HomeFragment();
        else if (position == 1)
            return new AccountFragment();
        else if (position == 2)
            return new OrdersFragment();

        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0)
            return "Home";
        else if (position == 1)
            return "Account";
        else if (position == 2)
            return "Orders";

        return "Home";
    }
}

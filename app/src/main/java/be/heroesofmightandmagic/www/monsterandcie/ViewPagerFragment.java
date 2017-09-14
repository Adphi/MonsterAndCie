package be.heroesofmightandmagic.www.monsterandcie;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class ViewPagerFragment extends AppCompatActivity {

    SectionsPagerAdapter mSectionsPagerAdapter;

    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        int currentIndex = getIntent().getIntExtra("monsterNameIndex", 0);
        mViewPager.setCurrentItem(currentIndex);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new DetailsActivity();
            Bundle args = new Bundle();
            args.putInt(DetailsActivity.MONSTERNAME_INDEX, position);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            return MyAdapter.monstersNameList.length;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.enter_left, R.anim.out_right);
    }

}

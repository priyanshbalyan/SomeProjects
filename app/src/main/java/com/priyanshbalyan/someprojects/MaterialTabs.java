package com.priyanshbalyan.someprojects;

import android.os.*;
import android.support.design.widget.*;
import android.support.v4.app.*;
import android.support.v4.view.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import java.util.*;

public class MaterialTabs extends AppCompatActivity
{
	Toolbar toolbar ;
	TabLayout tablayout ;
	ViewPager viewpager ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.materialtabs);
		
		toolbar = (Toolbar)findViewById(R.id.toolbar);
		//toolbar.setLogo(R.drawable.ic_launcher);
		setSupportActionBar(toolbar);
		//sets the back arrow button
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		viewpager = (ViewPager)findViewById(R.id.viewpager);
		
		ViewPagerAdapter viewpageradapter = new ViewPagerAdapter(getSupportFragmentManager());
		viewpageradapter.addFragment(new TabFragment1(), "ONE");
		viewpageradapter.addFragment(new TabFragment2(), "TWO");
		viewpager.setAdapter(viewpageradapter);
		
		tablayout = (TabLayout) findViewById(R.id.tabs);
		tablayout.setupWithViewPager(viewpager);
	}
	
	class ViewPagerAdapter extends FragmentPagerAdapter
	{
		List<Fragment> fragmentlist = new ArrayList<>();
		List<String> fragmenttitlelist = new ArrayList<>() ;
		
		public ViewPagerAdapter(FragmentManager manager){
			super(manager);
		}
		
		@Override
		public int getCount()
		{
			// TODO: Implement this method
			return fragmentlist.size();
		}

		@Override
		public Fragment getItem(int p1)
		{
			// TODO: Implement this method
			return fragmentlist.get(p1);
		}
		
		public void addFragment(Fragment fragment, String title){
			fragmentlist.add(fragment);
			fragmenttitlelist.add(title);
		}
		
		public CharSequence getPageTitle(int position){
			return fragmenttitlelist.get(position);
		}
	}
}

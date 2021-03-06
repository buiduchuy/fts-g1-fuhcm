package vn.edu.fpt.fts.activity;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import vn.edu.fpt.fts.adapter.GoodsDetailPagerAdapter;
import vn.edu.fpt.fts.fragment.R;

public class GoodsDetailActivity extends FragmentActivity implements
		TabListener {
	private ActionBar actionBar;
	private String goodsCategoryID, goodsID;
	private GoodsDetailPagerAdapter mAdapter;
	private String[] tabs = { "Giao dịch", "Thông tin" };
	private ViewPager viewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goods_detail);

		goodsID = getIntent().getStringExtra("goodsID");
		goodsCategoryID = getIntent().getStringExtra("goodsCategoryID");
		String categoryName = "";
		switch (Integer.parseInt(goodsCategoryID)) {
		case 1:
			categoryName = "Hàng thực phẩm";
			break;
		case 2:
			categoryName = "Hàng đông lạnh";
			break;
		case 4:
			categoryName = "Hàng dễ vỡ";
			break;
		case 5:
			categoryName = "Hàng dễ cháy nổ";
			break;
		default:
			break;
		}
		SharedPreferences preferences = getSharedPreferences("MyPrefs",
				Context.MODE_PRIVATE);
		this.getActionBar().setTitle(
				categoryName + " - " + preferences.getString("ownerName", ""));

		viewPager = (ViewPager) findViewById(R.id.pager_goods);
		actionBar = getActionBar();
		mAdapter = new GoodsDetailPagerAdapter(getSupportFragmentManager());

		viewPager.setAdapter(mAdapter);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		for (String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this));
		}

		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				actionBar.setSelectedNavigationItem(position);
			}
		});

		// ListView listView = (ListView)
		// findViewById(R.id.listview_goods_detail);
		// ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_1,deals);
		// listView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.goods_detail, menu);
		return true;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent(GoodsDetailActivity.this,
					MainActivity.class);
			startActivity(intent);
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		} else if (id == R.id.suggest_driver) {
			Intent intent = new Intent(this, SuggestActivity.class);
			intent.putExtra("cate", goodsCategoryID);
			intent.putExtra("goodsID", goodsID);
			startActivity(intent);
		}
		if (id == R.id.action_history) {
			Intent intent = new Intent(GoodsDetailActivity.this,
					HistoryActivity.class);
			startActivity(intent);
		}
		if (id == android.R.id.home) {
			Intent intent = new Intent(GoodsDetailActivity.this,
					MainActivity.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		viewPager.setCurrentItem(tab.getPosition());

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

}

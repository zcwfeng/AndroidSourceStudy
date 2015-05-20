package com.zcwfeng.sourcestudy.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Description:Fragment管理<br/>
 * Copyright (C), 2005-2015,David<br/>
 * Thies program is protected by copyright<br/> David.
 * Program name<br/>
 * Date:2015-5-1<br/>
 * @author David zcwfeng@126.com<br/>
 * @version 1.0
 *
 */
public class AbstractFragment extends Fragment {
	
	private FragmentManager fm;
	/**
	 * 替换Fragment
	 * 
	 * @param fragment
	 */
	public void switchFragmentbyAdd(int containerId, Fragment fragment, String fragmentTag) {
		try {
			FragmentTransaction ft = getFragmentManager().beginTransaction();
			Fragment currentFragment = getFragmentManager().findFragmentByTag(fragmentTag);
			if (currentFragment != null) {
				ft.hide(currentFragment).add(containerId, fragment, fragmentTag).addToBackStack(null).commit();
			} else {
				ft.add(containerId, fragment, fragmentTag).addToBackStack(null).commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	


	public AbstractFragment() {
		fm = getFragmentManager();
	}
	
	/**
	 * 替换当前Fragment
	 * 
	 * @param layoutId
	 *            新的Fragment显示位置资源ID
	 * 
	 * @param fragmentc
	 *            要显示的fragment,默认加入返回栈
	 */
	public void replaceFm(int layoutId, Fragment fragment) {

		replaceFm(layoutId, fragment, true);
	}

	/**
	 * 替换当前Fragment
	 * 
	 * @param layoutId
	 *            新的Fragment显示位置资源ID
	 * 
	 * @param fragmentc
	 *            要显示的fragment
	 * 
	 * @param backStack
	 *            是否加入返回栈
	 */
	public void replaceFm(int layoutId, Fragment fragment, Boolean backStack) {
		if (fm == null) {
			fm = getFragmentManager();
		}
		FragmentTransaction transaction = fm.beginTransaction();

		transaction.replace(layoutId, fragment, fragment.getClass()
				.getSimpleName());
		if (backStack) {
			transaction.addToBackStack(fragment.getClass().getSimpleName());
		}
		transaction.commitAllowingStateLoss();
	}

	/**
	 * 添加Fragment
	 * 
	 * @param layoutId
	 *            新的Fragment显示位置资源ID
	 * 
	 * @param fragment
	 *            需要显示的Fragment
	 */
	public void addFm(int layoutId, Fragment fragment) {
		if (fm == null) {
			fm = getFragmentManager();
		}

		FragmentTransaction transaction = fm.beginTransaction();

		transaction.add(layoutId, fragment).addToBackStack(null)
				.commitAllowingStateLoss();
	}

	/**
	 * 移除Fragment
	 * 
	 * @param fragmentName
	 *            fragment名字
	 */
	public void removeFm(String fragmentName) {
		if (fm == null) {
			fm = getFragmentManager();
		}
		Fragment fragment = fm.findFragmentByTag(fragmentName);
		FragmentTransaction transaction = fm.beginTransaction();
		transaction.remove(fragment).commitAllowingStateLoss();

	}
}

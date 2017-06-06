// Generated code from Butter Knife. Do not modify!
package com.yimeng.yunma.lib.sign;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yimeng.common.ultraviewpager.UltraViewPager;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Sign_MainActivity_ViewBinding implements Unbinder {
  private Sign_MainActivity target;

  private View view2131427488;

  @UiThread
  public Sign_MainActivity_ViewBinding(Sign_MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Sign_MainActivity_ViewBinding(final Sign_MainActivity target, View source) {
    this.target = target;

    View view;
    target.calendarsUltraVp = Utils.findRequiredViewAsType(source, R.id.calendars_ultra_vp, "field 'calendarsUltraVp'", UltraViewPager.class);
    view = Utils.findRequiredView(source, R.id.tv, "field 'tv' and method 'onClick'");
    target.tv = Utils.castView(view, R.id.tv, "field 'tv'", TextView.class);
    view2131427488 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    Sign_MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.calendarsUltraVp = null;
    target.tv = null;

    view2131427488.setOnClickListener(null);
    view2131427488 = null;
  }
}

package com.github.mzule.activityrouter.router;

import com.yimeng.yunma.lib.sign.ui.Sign_MainActivity;

public final class RouterMapping_sign {
  public static final void map() {
    java.util.Map<String,String> transfer = null;
    com.github.mzule.activityrouter.router.ExtraTypes extraTypes;

    transfer = null;
    extraTypes = new com.github.mzule.activityrouter.router.ExtraTypes();
    extraTypes.setTransfer(transfer);
    com.github.mzule.activityrouter.router.Routers.map("sign_mainActivity/:text", Sign_MainActivity.class, null, extraTypes);

  }
}

package com.dicicilaja.app;

import androidx.multidex.MultiDexApplication;

import com.instabug.library.Instabug;
import com.instabug.library.invocation.InstabugInvocationEvent;

public class    BaseApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();

        new Instabug.Builder(this, "7cf2f1bf79c1a536ff5ecd88d4d76c02")
                .setInvocationEvents(InstabugInvocationEvent.SHAKE, InstabugInvocationEvent.SCREENSHOT)
                .build();
    }
}

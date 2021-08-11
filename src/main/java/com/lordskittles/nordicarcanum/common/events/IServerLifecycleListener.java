package com.lordskittles.nordicarcanum.common.events;

import javax.annotation.Nullable;

public interface IServerLifecycleListener {

    void onServerStart();

    void onServerStop();

    static IServerLifecycleListener stop(Runnable onStop) {

        return wrap(null, onStop);
    }

    static IServerLifecycleListener start(Runnable onStart) {

        return wrap(onStart, null);
    }

    static IServerLifecycleListener wrap(@Nullable Runnable onStart, @Nullable Runnable onStop) {

        return new IServerLifecycleListener() {

            @Override
            public void onServerStart() {

                if(onStart != null) {
                    onStart.run();
                }
            }

            @Override
            public void onServerStop() {

                if(onStop != null) {
                    onStop.run();
                }
            }
        };
    }
}

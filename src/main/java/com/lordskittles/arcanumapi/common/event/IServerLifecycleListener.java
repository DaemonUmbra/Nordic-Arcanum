package com.lordskittles.arcanumapi.common.event;

import javax.annotation.Nullable;

public interface IServerLifecycleListener
{
    public void onServerStart();
    public void onServerStop();

    public static IServerLifecycleListener stop(Runnable onStop)
    {
        return wrap(null, onStop);
    }

    public static IServerLifecycleListener start(Runnable onStart)
    {
        return wrap(onStart, null);
    }

    public static IServerLifecycleListener wrap(@Nullable Runnable onStart, @Nullable Runnable onStop)
    {
        return new IServerLifecycleListener()
        {
            @Override
            public void onServerStart()
            {
                if (onStart != null)
                {
                    onStart.run();
                }
            }

            @Override
            public void onServerStop()
            {
                if (onStop != null)
                {
                    onStop.run();
                }
            }
        };
    }
}

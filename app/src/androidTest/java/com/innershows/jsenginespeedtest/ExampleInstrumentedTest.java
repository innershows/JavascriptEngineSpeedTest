package com.innershows.jsenginespeedtest;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.innershows.jsenginespeedtest", appContext.getPackageName());
    }

    @Test
    public void testTime() throws Exception {
        //1000,0
        //1000,000
        //1000,000,00
        doCaculate(10000L);
        doCaculate(1000000L);
        doCaculate(100000000L);

    }

    @Test
    public void testTimeWithJava() throws Exception {
        //1000,0
        //1000,000
        //1000,000,00
        doCaculateWithJava(10000L);
        doCaculateWithJava(1000000L);
        doCaculateWithJava(100000000L);
    }


    private void doCaculate(long time) {
        String js = generateScript(time);
        List<Long> RhinoDuration = new ArrayList<>();
        List<Long> V8Duration = new ArrayList<>();
        List<Long> JSCoreDuration = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            executeScript(js, RhinoDuration, V8Duration, JSCoreDuration);
        }

        Log.e("====", "======>平均时间🎺Rhino消耗时间 =" + perTimeDuration(RhinoDuration));
        Log.e("====", "======>平均时间🎺V8消耗时间 =" + perTimeDuration(V8Duration));
        Log.e("====", "======>平均时间🎺JSCore消耗时间 =" + perTimeDuration(JSCoreDuration));
    }


    private void doCaculateWithJava(long time) {
        List<Long> RhinoDuration = new ArrayList<>();
        List<Long> V8Duration = new ArrayList<>();
        List<Long> JSCoreDuration = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            executeScriptWithJava(time, RhinoDuration, V8Duration, JSCoreDuration);
        }

        Log.e("====", "======>平均时间🎺Rhino消耗时间 =" + perTimeDuration(RhinoDuration));
        Log.e("====", "======>平均时间🎺V8消耗时间 =" + perTimeDuration(V8Duration));
        Log.e("====", "======>平均时间🎺JSCore消耗时间 =" + perTimeDuration(JSCoreDuration));
    }

    public long perTimeDuration(List<Long> durations) {
        long total = 0L;
        for (long ll : durations) {
            total += ll;
        }
        return total / durations.size();
    }


    public String generateScriptWithJava(long times) {
        return "for(var i = 0 ;i<" + times + ";i++){func();}";
    }


    //Rhino需要对象传过去才行。
    public String generateScriptWithJavaRhino(long times) {
        return "function rhinofunc(obj){for(var i = 0 ;i<" + times + ";i++){obj.func();}}";
    }

    private void executeScriptWithJava(long time, List<Long> rhinoDuration, List<Long> v8Duration, List<Long> JSCoreDuration) {

        String js = generateScriptWithJavaRhino(time);

        long start = System.currentTimeMillis();
        new RhinoUtils().callJSWithJava(js);
        long end = System.currentTimeMillis();

        Log.e("====", "======>🎺Rhino消耗时间 =" + (end - start));
        rhinoDuration.add(end - start);


        js = generateScriptWithJava(time);
        start = System.currentTimeMillis();
        new J2V8Utils().callJSWithJava(js);
        end = System.currentTimeMillis();

        Log.e("====", "======>🎺J2V8消耗时间 = " + (end - start));
        v8Duration.add(end - start);

        start = System.currentTimeMillis();
        new JSCoreUtils().callJSWithJava(js);
        end = System.currentTimeMillis();

        Log.e("====", "======>🎺JSCore消耗时间 = " + (end - start));
        JSCoreDuration.add(end - start);
    }


    private void executeScript(String js, List<Long> rhinoDuration, List<Long> v8Duration, List<Long> JSCoreDuration) {
        long start = System.currentTimeMillis();
        RhinoUtils.callJS(js);
        long end = System.currentTimeMillis();

        Log.e("====", "======>🎺Rhino消耗时间 =" + (end - start));
        rhinoDuration.add(end - start);

        start = System.currentTimeMillis();
        J2V8Utils.callJS(js);
        end = System.currentTimeMillis();

        Log.e("====", "======>🎺J2V8消耗时间 = " + (end - start));
        v8Duration.add(end - start);

        start = System.currentTimeMillis();
        JSCoreUtils.callJS(js);
        end = System.currentTimeMillis();

        Log.e("====", "======>🎺JSCore消耗时间 = " + (end - start));
        JSCoreDuration.add(end - start);
    }


    public String generateScript(long times) {
        return "for(var i = 0 ;i<" + times + ";i++){}";
    }

}

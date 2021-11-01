package com.peditor.green.Utils;

import android.content.SharedPreferences;

public class ScoreChecker {
    public static String replace_param_home(String a, SharedPreferences mPrefs, String adid, String afid, String adset, String comid) {
        if (a.contains("gaid")) {
            a = a.replace("gaid", adid);
        }
        if (a.contains("apfid")) {
            a = a.replace("apfid", afid);
        }
        if (a.contains("adset")) {
            adset = mPrefs.getString(Variables.PARAM_3, "");
            a = a.replace("adset", adset);
        }
        if (a.contains("compid")) {
            comid = mPrefs.getString(Variables.PARAM_4, "");
            a = a.replace("compid", comid);
        }
        return a;
    }

    public static String replace_param_out(String b, SharedPreferences mPrefs, String sub_id1, String sub_id2) {
        if (b.contains("para1")) {
            sub_id1 = mPrefs.getString(Variables.PARAM_1, "");
            b = b.replace("para1", sub_id1);
        }
        if (b.contains("para2")) {
            sub_id2 = mPrefs.getString(Variables.PARAM_2, "");
            b = b.replace("para2", sub_id2);
        }
        return b;
    }
}

package com.example.oespartner.Extras;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

import java.io.ByteArrayOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Utility {

    public static String dateConverterTH(String date) {

        SimpleDateFormat formatter;

        if (date.equals("")) {
            return "";
        } else {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
            Date testDate = null;
            try {
                testDate = sdf.parse(date);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            String date1 = String.valueOf(testDate.getDate());

            if (date1.endsWith("1") && !date1.endsWith("11"))
                formatter = new SimpleDateFormat("MMM d'st', yyyy h:mm a");
            else if (date1.endsWith("2") && !date1.endsWith("12"))
                formatter = new SimpleDateFormat("MMM d'nd', yyyy h:mm a");
            else if (date1.endsWith("3") && !date1.endsWith("13"))
                formatter = new SimpleDateFormat("MMM d'rd', yyyy h:mm a");
            else formatter = new SimpleDateFormat("MMM d'th', yyyy h:mm a");

            return formatter.format(testDate);
        }
    }

    public static String dateConverter(String date) {

        SimpleDateFormat formatter;

        if (date.equals("")) {
            return "";
        } else {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date testDate = null;
            try {
                testDate = sdf.parse(date);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            String date1 = String.valueOf(testDate.getDate());

            if (date1.endsWith("1") && !date1.endsWith("11"))
                formatter = new SimpleDateFormat("MMM d'st', yyyy h:mm a");
            else if (date1.endsWith("2") && !date1.endsWith("12"))
                formatter = new SimpleDateFormat("MMM d'nd', yyyy h:mm a");
            else if (date1.endsWith("3") && !date1.endsWith("13"))
                formatter = new SimpleDateFormat("MMM d'rd', yyyy h:mm a");
            else formatter = new SimpleDateFormat("MMM d'th', yyyy h:mm a");

            return formatter.format(testDate);
        }
    }

    public static String dateConverterHistory(String date) {

        SimpleDateFormat formatter;

        if (date.equals("")) {
            return "";
        } else {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date testDate = null;
            try {
                testDate = sdf.parse(date);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            String date1 = String.valueOf(testDate.getDate());

            if (date1.endsWith("1") && !date1.endsWith("11"))
                formatter = new SimpleDateFormat("MMM d'st', yyyy");
            else if (date1.endsWith("2") && !date1.endsWith("12"))
                formatter = new SimpleDateFormat("MMM d'nd', yyyy");
            else if (date1.endsWith("3") && !date1.endsWith("13"))
                formatter = new SimpleDateFormat("MMM d'rd', yyyy");
            else formatter = new SimpleDateFormat("MMM d'th', yyyy");

            return formatter.format(testDate);
        }
    }

    public static String dateConverterEvents(String date) {

        SimpleDateFormat formatter;

        if (date.equals("")) {
            return "";
        } else {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date testDate = null;
            try {
                testDate = sdf.parse(date);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            String date1 = String.valueOf(testDate.getDate());

            if (date1.endsWith("1") && !date1.endsWith("11"))
                formatter = new SimpleDateFormat("MMM d'st', yyyy'\n'h:mm a");
            else if (date1.endsWith("2") && !date1.endsWith("12"))
                formatter = new SimpleDateFormat("MMM d'nd', yyyy'\n'h:mm a");
            else if (date1.endsWith("3") && !date1.endsWith("13"))
                formatter = new SimpleDateFormat("MMM d'rd', yyyy'\n'h:mm a");
            else formatter = new SimpleDateFormat("MMM d'th', yyyy'\n'h:mm a");

            return formatter.format(testDate);
        }
    }

    public static String getManagedTimeString(int hours, int minutes) {

        String timeStr = "";
        String amPm = "AM";

        if (hours >= 12) {
            amPm = "PM";
            hours = hours - 12;
        }
        if (hours == 0) {
            hours = 12;
        }
        timeStr = (hours < 10 ? ("0" + hours) : hours) + ":" + (minutes < 10 ? ("0" + minutes) : minutes) + " " + amPm;

        return timeStr;
    }

    public static DisplayMetrics getDisplayMetrics(Context context) {

        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }

    public static String getStringImage(Bitmap bmp) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);

        //        int maxLogSize = 500;
        //        for (int i = 0; i <= (encodedImage.length() / maxLogSize); i++) {
        //            int start = i * maxLogSize;
        //            int end = (i + 1) * maxLogSize;
        //            end = end > encodedImage.length() ? encodedImage.length() : end;
        //            Log.d("mapData >>>", encodedImage.substring(start, end));
        //        }

        return encodedImage;
    }


    //    public static void changeStatusBarColorTransparent(Activity activity) {
    //        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    //            Window window = activity.getWindow();
    //            // clear FLAG_TRANSLUCENT_STATUS flag:
    //            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    //            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
    //            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    //            // finally change the color
    //            window.setStatusBarColor(0x88000000);
    //        }
    //    }

    public static void changeStatusBarColorBlack(Activity activity) {
        int STATUS_BAR_COLOR_BLACK = 0xFF000000;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            // clear FLAG_TRANSLUCENT_STATUS flag:
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            // finally change the color
            window.setStatusBarColor(STATUS_BAR_COLOR_BLACK);
        }
    }

    public static boolean isValidEmail(String email) {

        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isConnectedToNetwork(Context context) {

        ConnectivityManager ConnectionManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = ConnectionManager.getActiveNetworkInfo();

        return (networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected());
    }

    public static int dpToPx(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    public static int pxToDp(Context context, int px) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int dp = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }

    public static float pixelsToSp(Context context, float px) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return px / scaledDensity;
    }

    public static int spToPixel(Context context, int sp) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * scaledDensity);
    }

    public static Bitmap getScaledBitmap(Resources res, int resId, int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            // Calculate ratios of height and width to requested height and width
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }

        return inSampleSize;
    }

    public static String extractYoutubeId(String url) throws MalformedURLException, PatternSyntaxException {

        String id = null;
        String query = new URL(url).getQuery();
        if (query != null) {
            String[] param = query.split("&");
            for (String row : param) {
                String[] param1 = row.split("=");
                if (param1[0].equals("v")) {
                    id = param1[1];
                }
            }
        } else {
            String[] param = url.split("/");
            id = param[param.length - 1];
        }

        return id;
    }

    public static String extractVimeoId(String url) throws MalformedURLException, PatternSyntaxException {

        String[] param = url.split("/");
        String id = param[param.length - 1];

        return id;
    }

}
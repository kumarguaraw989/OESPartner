package com.example.oespartner.App_Helper;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Constants {

    public static String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    public static String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
    static DatePickerDialog dpDialog,dpDialog2;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void DateDialog(final EditText editText, Context context) {

        Calendar mcalendar = Calendar.getInstance();
        int day = mcalendar.get(Calendar.DAY_OF_MONTH);
        int year = mcalendar.get(Calendar.YEAR);
        int month = mcalendar.get(Calendar.MONTH);

        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                editText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                editText.setText(Constants.changeDateFormat((monthOfYear + 1) + "-" + dayOfMonth + "-" + year, "mm-dd-yyyy", "dd/mm/yyyy"));
                dpDialog.dismiss();
//                sendingEndDate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;

            }

        };
        dpDialog = new DatePickerDialog(Objects.requireNonNull(context), listener, year, month, day);
        dpDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

        dpDialog.show();
    }


//    public static void DateDialog2(final EditText editText, Context context) {
//
//        Calendar mcalendar = Calendar.getInstance();
//        int day = mcalendar.get(Calendar.DAY_OF_MONTH);
//        int year = mcalendar.get(Calendar.YEAR);
//        int month = mcalendar.get(Calendar.MONTH);
//
//        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
//            @SuppressLint("SetTextI18n")
//            @Override
//            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
////                editText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
//                ;
//                editText.setText(Constants.changeDateFormat((monthOfYear + 1) + "-" + dayOfMonth + "-" + year, "mm-dd-yyyy", "dd/mm/yyyy"));
//                dpDialog2.dismiss();
////                sendingEndDate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
//
//            }
//
//        };
//        dpDialog2 = new DatePickerDialog(Objects.requireNonNull(context), listener, year, month, day);
//        dpDialog2.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
//
//        dpDialog2.show();
//    }

    public static String changeDateFormat(String comingDate, String fromFormat, String toFormat) {
        SimpleDateFormat inputFormat = new SimpleDateFormat(fromFormat);
        SimpleDateFormat outputFormat = new SimpleDateFormat(toFormat);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(comingDate);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static void updateTime(int hours, int mins, EditText editText) {

        String timeSet = "";
        if (hours > 12) {
            hours -= 12;
            timeSet = "PM";
        } else if (hours == 0) {
            hours += 12;
            timeSet = "AM";
        } else if (hours == 12)
            timeSet = "PM";
        else
            timeSet = "AM";


        String minutes = "";
        if (mins < 10)
            minutes = "0" + mins;
        else
            minutes = String.valueOf(mins);

        // Append in a StringBuilder
        String aTime = new StringBuilder().append(hours).append(':')
                .append(minutes).append(" ").append(timeSet).toString();

        editText.setText(aTime);
    }

    public static byte[] getByte(String path) {
        byte[] getBytes = {};
        try {
            File file = new File(path);
            getBytes = new byte[(int) file.length()];
            InputStream is = new FileInputStream(file);
            is.read(getBytes);
            is.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getBytes;
    }

    public static void makeCall(RuntimePermission runtimePermission, Activity activity, String phoneNumber) {
        if (PermissionUtility.hasMarshmallow() && PermissionsUtils.checkSelfForCallPermission(activity)) {
            runtimePermission.checkRuntimePermissionForPhone();
        } else {
            phoneNumber = phoneNumber.replace("-", "");
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + phoneNumber));
            activity.startActivity(callIntent);
        }

    }



    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

    public static String revisedGetTimeDifference() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss aa");


        Date Date1 = null;
        try {
            Date1 = sdf.parse("09:30:00 AM");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date Date2 = null;
        try {
            Date2 = Calendar.getInstance().getTime();
            String myDate = sdf.format(Date2);

            Date2 = sdf.parse(myDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long millse = Date1.getTime() - Date2.getTime();
        long mills = Math.abs(millse);

        int Hours = (int) (mills / (1000 * 60 * 60));
        int Mins = (int) (mills / (1000 * 60)) % 60;
        long Secs = (int) (mills / 1000) % 60;

        String diff = Hours + ":" + Mins + ":" + Secs; // updated value every1 second
        Log.e("Difference : ", diff);

        return diff;
    }



    private String getCompleteAddressString(Context context, double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
                Log.w("My Current loction", strReturnedAddress.toString());
            } else {
                Log.w("My Current loction", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("My Current loction", "Cannot get Address!");
        }
        return strAdd;
    }

    public static Double getFeet(int inches)
    {
        int feet = inches / 12;
        int leftover = inches % 12;
        System.out.println(feet + " feet and " + leftover + " inches");

        return Double.parseDouble(feet+"."+leftover);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

}

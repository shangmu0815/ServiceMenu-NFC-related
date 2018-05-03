package com.example.test1;



import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;


import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.database.Cursor;
import android.hardware.Camera;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.CallLog;
import android.telephony.TelephonyManager;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;


//
import android.app.PendingIntent;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NfcA;
import android.nfc.tech.NfcB;
import android.os.Bundle;
import android.os.Parcelable;
//import android.os.SystemProperties;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.LinearLayout;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.io.IOException;
import android.nfc.NfcAdapter;
import android.util.Log;
//import com.nxp.nfc.NxpNfcAdapter;
import android.os.SystemProperties;

public class MainActivity extends Activity implements OnClickListener, OnCheckedChangeListener{
    TextView tv1 ;
    TextView tv2 ;
    Button button;
    boolean b;

//    private static String file_path = "/dev/block/mmcblk0p21";
//    
//    long system_on_time = 0;
//    private static final String FILE_NODE = "/sys/devices/platform/battery_meter/FG_daemon_log_level";
//    String mFileNode = ""; 
//    String mFileNode2 = "";
    //
    private static final String TAG = "ServiceMenu-NFC";
    private static final String DEFVER = "0000000";
    private static final String TAG_TYPE_A = "Type A";
    private static final String TAG_TYPE_B = "Type B";
    private static final String TAG_TYPE_OTHER = "Other";
    private static final String CHIP_PN547 = "0000";
    private static final String CHIP_PN65T = "0001";
    private static final String ESE_ID = "com.nxp.smart_mx.ID";
    private static final String UICC_ID = "com.nxp.uicc.ID";

    private TextView mBasebandResult;
    private TextView mFirmwareVersion;
    private TextView mHardwareVersion;
    private TextView mModelId;
    private TextView mModelName;
    private TextView mSwpUiccStatus;
    private TextView mSwpESeStatus;
    private TextView mTagReadStatus;
    private TextView mTagReadResult;
    private TextView mTagReadType;
    private TextView mTagReadTagID;
    private TextView mTagReadTagType;
    private LinearLayout mBasebandField;
    private LinearLayout mFWVersionField;
    private LinearLayout mHWVersionField;
    private LinearLayout mModelIdField;
    private LinearLayout mModelNameField;
    private LinearLayout mSWPField;

    private NfcAdapter mNfc;
    private PendingIntent mPendingIntent = null;
    private boolean mOriginalNfcSettingOn = false;
    private boolean mIsSupportESe = false;
    //
    
    //
    public static final int NOT_READY = -1;
    public static final int SUCCESS = 0;
    public static final int NO_NFC_SIM = 1;
    public static final int FAILED = 2;
    //
//    public static int selectDefaultSecureElement(NfcAdapter nfc, String pkg, String seId) {
//        try {
//            NxpNfcAdapter mNxpNfc = NxpNfcAdapter.getNxpNfcAdapter(nfc);
//            if (mNxpNfc != null) {
//                mNxpNfc.selectDefaultSecureElement(pkg, seId);
//                Log.e("vvvv", "---selectDefaultSecureElement-SUCCESS");
//                return SUCCESS;
//            }
//        } catch (IOException e) {
//            Log.e("vvvv", "---selectDefaultSecureElement-NO_NFC_SIM");
//            return NO_NFC_SIM;
//        } catch (Exception e) {
//            Log.e("vvvv", "---selectDefaultSecureElement-FAILED");
//            return FAILED;
//        }
//        return FAILED;
//    }

    
    public RadioGroup xxRadioGroup;//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)this.findViewById(R.id.button1);
        tv1 =(TextView)this.findViewById(R.id.text1);
        tv2 =(TextView)this.findViewById(R.id.text2);
        //tv
        button.setOnClickListener(this);
        tv1.setMovementMethod(new ScrollingMovementMethod());
        
        xxRadioGroup = (RadioGroup)findViewById(R.id.radioGroup1);
        xxRadioGroup.setOnCheckedChangeListener(this);

//        mNfc = NfcAdapter.getDefaultAdapter(this);
//        if (mNfc == null) {
//            Log.e("vvvv", "NFC is not available on this device.");
//        }else{
//            if (!mNfc.isEnabled()) {
////                if (!mNfc.enable()) {
////                    Log.e("vvvv", "enable NFC fail.");
////                } else {
////                    Log.d("vvvv", "1--Detect NFC SIM...");
////                    setSecureElement();
////                }
//            } else {
//                Log.d(TAG, "2--Detect NFC SIM...");
//                mOriginalNfcSettingOn = true;
//                //setSecureElement();
//            }
//        }
//        //
    }
    

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        Log.e("vvvv", "onResume()");
        //Log.e("vvvv","system_on_time:"+system_on_time);
        tv1.setText("");
        tv2.setText("");
        PackageManager pm = getApplicationContext().getPackageManager();
        if(pm.hasSystemFeature(PackageManager.FEATURE_NFC)) {
            //setSecureElement();
        }
        super.onResume();

        
    }
    
    //Read File
//    public static StringBuilder FileRead (int classType){
//        StringBuilder sb = new StringBuilder(); 
////        StringBuilder sb1;
////        DataOutputStream os = null;
////        Process process = null;
////        String command="cat /dev/block/mmcblk0p21";
//        try {
////            Log.e("joseph","FileRead-command:"+command);
////            process = Runtime.getRuntime().exec("su");
////            os = new DataOutputStream(process.getOutputStream());
////            os.writeBytes(command+"\n");
////            os.writeBytes("exit\n");
////            os.flush();
////            process.waitFor();
//            
//            Log.e("joseph","FileRead-sb:"+sb+"-file_path:"+file_path);
//            File file = new File(file_path);
//            Log.e("joseph","FileRead-file:"+file);
//            FileInputStream fin = new FileInputStream(file);
//            Log.e("joseph","FileRead-1-fin:"+fin);
//
//            /*
//            byte[] data = new byte[fin.available()];
//            while (fin.read(data) != -1) {
//                sb.append(new String(data));
//            }
//            */
//            byte[] data = new byte[6000];
//            fin.read(data, 0, 6000);
//            Log.e("joseph","FileRead-2-fin:"+fin);
//            sb.append(new String(data));
//            Log.e("joseph","FileRead-sb:"+sb);
//            fin.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            Log.e("joseph","FileRead-2-FileNotFoundException:"+e.toString());
//            return null;
//        } catch (IOException e) {
//            e.printStackTrace();
//            Log.e("joseph","FileRead-2-IOException:"+e.toString());
//            return null;
//        }
//        return sb;
//    }
    


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void getSingInfo() {  
        try {  
            PackageInfo packageInfo = getPackageManager().getPackageInfo("com.compal.sony.servicemenu", PackageManager.GET_SIGNATURES);
            Signature[] signs = packageInfo.signatures;  
            Signature sign = signs[0];  
            parseSignature(sign.toByteArray());
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }
    public void parseSignature(byte[] signature) {
        try {  
            CertificateFactory certFactory = CertificateFactory.getInstance("X.509");  
            X509Certificate cert = (X509Certificate) certFactory.generateCertificate(new ByteArrayInputStream(signature));  
            String pubKey = cert.getPublicKey().toString();  
            String signNumber = cert.getSerialNumber().toString();
            Log.e("vvvv", "cert.getSigAlgName():"+cert.getSigAlgName());
            Log.e("vvvv", "pubKey:"+pubKey);
            Log.e("vvvv", "signNumber:"+signNumber);
            Log.e("vvvv", "cert.getSubjectDN().toString():"+cert.getSubjectDN().toString());
//            Log.e("vvvv-signName:" + cert.getSigAlgName());  
//            Log.e("vvvv-pubKey:" + pubKey);  
//            Log.e("vvvv-signNumber:" + signNumber);  
//            Log.e("vvvv-subjectDN:"+cert.getSubjectDN().toString());  
        } catch (CertificateException e) {  
            e.printStackTrace();  
        }  
    }
    public String getPackageSignature(Context context, String packageName){
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> apps = pm.getInstalledPackages(PackageManager.GET_SIGNATURES);
        Iterator<PackageInfo> it = apps.iterator();
        while(it.hasNext()){
            PackageInfo info = it.next();
            Log.e("vvvv", "===========================================");
            Log.e("vvvv", "info:"+info);
            if(info.packageName.equals(packageName)){
                String singtocharsstring = info.signatures[0].toCharsString();
                Log.e("vvvv", "singtocharsstring:"+singtocharsstring);
                tv1.setText(singtocharsstring);
                return singtocharsstring;
            }
        }
        return null;
    }
    
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.button1:
//            Process fgstartProcess = null ;
//            Process fgshowProcess = null ;
//            //avc
//            //echo 4 > /sys/devices/platform/battery_meter/FG_daemon_log_level
//            //cat /sys/devices/platform/battery_meter/FG_daemon_log_level
//            Log.e("vvvv", "ai:button1");
//            try {
//                String fgstartCmd = "cat /sys/devices/platform/battery_meter/fg_start";
//                fgstartProcess = Runtime.getRuntime().exec(fgstartCmd);
//                BufferedReader fgstartBr = new BufferedReader(new InputStreamReader(fgstartProcess.getInputStream()));
//                String fgstartResult = fgstartBr.readLine();
//                fgstartBr.close();
//                Log.e("vvvv", "fgstartResult:"+fgstartResult);
//                if (fgstartResult.equals("statr_ok")) {
//                    for (int i = 0; i < 40; i++) {
//                        String cmdline = "cat /sys/devices/platform/battery_meter/fg_show";
//                        fgshowProcess = Runtime.getRuntime().exec(cmdline);
//                        BufferedReader fgshowBr = new BufferedReader(new InputStreamReader(fgshowProcess.getInputStream()));
//                        String fgshowResult;
//                        StringBuffer output = new StringBuffer();
//                        if ((fgshowResult = fgshowBr.readLine()) != null) {
//                            Log.e("vvvv", "333-fgshowResult:" + fgshowResult
//                                    + "====");
//                        }
//                        fgshowBr.close();
//                    }
//                }
//
//            Log.e("vvvv", "output:");
//            } catch (Exception e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//                Log.e("vvvv", "ai error:"+e.toString());
//            }
//            Signature[] sigs;
//            try {
//                //sigs = this.getPackageManager().getPackageInfo(this.getPackageName(), PackageManager.GET_SIGNATURES).signatures;
//                sigs = this.getPackageManager().getPackageInfo("com.compal.sony.servicemenu", PackageManager.GET_SIGNATURES).signatures;
//                for (Signature sig : sigs)
//                {
//                    Log.e("vvvv", "=====================================");
//                    Log.e("vvvv", "getPackageName():-sigs:"+sigs);
//                    Log.e("vvvv", "Signature hashcode : " + sig.hashCode());
//                }
//            } catch (NameNotFoundException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
            getSingInfo();
            getPackageSignature(this, "com.compal.sony.servicemenu"); //test getPackageSignature
            
//            File root = new File("/data/data/AnonymousData/shared_prefs");
//            Log.e("vvvv", "root.isDirectory():" + root.isDirectory());
//            if (root.isDirectory()) {
//               for (File child: root.listFiles()) {
//                   Log.e("vvvv", "child.getPath():"+child.getPath());
//                  Toast.makeText(this, child.getPath(), Toast.LENGTH_SHORT).show();
//               }
//            }
            break;
        case R.id.button2:
            Log.e("vvvv", "ai:button2");
            break;
        }
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        Log.e("vvvv", "=====onPause===tv:");
//        if (tv != null) {
//            tv.setText("");
//        }
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        Log.e("vvvv", "=====onDestroy===tv1:"+tv1);
        Log.e("vvvv", "=====onDestroy===tv2:"+tv2);
//        if (tv1 != null) {
//            tv1.setText("");
//        }
    }
    public String readFileOneLine(String filePath) {
        boolean flag = false;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(new File(filePath))));
            String s = br.readLine();
            br.close();
            return s;
        } catch (Exception e) {
            e.printStackTrace();
            return "-1";
        }
    }
//    public void doExeCmd(String command) {
//        DataOutputStream dos = null;
//        try {
//            Process process = Runtime.getRuntime().exec("su");
//            dos = new DataOutputStream(process.getOutputStream());
//            Log.e("vvvv", "doExeCmd-command:"+command);
//            dos.writeBytes(command);
//            dos.writeBytes("exit\n");
//            dos.flush();
//            dos.close();
//            process.waitFor();
//        } catch (Exception e) {
//            e.printStackTrace();
//            Log.e("vvvv", "doExeCmd-1-e.toString:"+e.toString());
//        } finally {
//            try {
//                if (dos != null) {
//                    dos.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//                Log.e("vvvv", "doExeCmd-2-e.toString:"+e.toString());
//            }
//        }
//    }
    private void setSecureElement() {
        TelephonyManager telephony;
        int sim_state = TelephonyManager.SIM_STATE_READY;
        try {
            telephony = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            sim_state = telephony.getSimState();
            Log.d("vvvv", "Check SIM State=" + sim_state);
        } catch (Exception e) {
            Log.e("vvvv", "setSecureElement exception: " + e);
            //mSwpUiccStatus.setText(getString(R.string.nfc_test_swp_status_ng));
        }

        if (sim_state == TelephonyManager.SIM_STATE_ABSENT) {
            //mSwpUiccStatus.setText(getString(R.string.nfc_test_swp_status_no_sim));
            if (mIsSupportESe) {
                //mSwpESeStatus.setText(getString(R.string.nfc_test_swp_status_no_sim));
            }
        } else {
            //int uicc_result = SetNfcNxpSe.selectDefaultSecureElement(mNfc, getPackageName(), UICC_ID);
            //com.compal.sony.servicemenu
            Log.e("vvvv", "setSecureElement-getPackageName(): " + getPackageName());
            //int uicc_result = selectDefaultSecureElement(mNfc, getPackageName(), UICC_ID);
            //int uicc_result = selectDefaultSecureElement(mNfc, "com.compal.sony.servicemenu", UICC_ID);
            int uicc_result = 0;
            Log.e("vvvv", "setSecureElement-uicc_result:" + uicc_result);
            switch (uicc_result) {
//                case SetNfcNxpSe.NOT_READY:
//                    mSwpUiccStatus.setText(STRING_NOT_READY);
//                    break;
//                case SetNfcNxpSe.SUCCESS:
//                    mSwpUiccStatus.setText(getString(R.string.nfc_test_swp_status_pass));
//                    break;
//                case SetNfcNxpSe.NO_NFC_SIM:
//                    mSwpUiccStatus.setText(getString(R.string.nfc_test_swp_status_no_nfc_sim));
//                    break;
//                case SetNfcNxpSe.FAILED:
//                    mSwpUiccStatus.setText(getString(R.string.nfc_test_swp_status_ng));
//                    break;
                case NOT_READY:
                    //mSwpUiccStatus.setText(STRING_NOT_READY);
                    Log.e("vvvv", "setSecureElement-NOT_READY");
                    tv1.setText("NOT_READY");
                    break;
                case SUCCESS:
                    //mSwpUiccStatus.setText(getString(R.string.nfc_test_swp_status_pass));
                    Log.e("vvvv", "setSecureElement-SUCCESS");
                    tv1.setText("SUCCESS");
                    break;
                case NO_NFC_SIM:
                    //mSwpUiccStatus.setText(getString(R.string.nfc_test_swp_status_no_nfc_sim));
                    Log.e("vvvv", "setSecureElement-NO_NFC_SIM");
                    tv1.setText("NO_NFC_SIM");
                    break;
                case FAILED:
                    //mSwpUiccStatus.setText(getString(R.string.nfc_test_swp_status_ng));
                    Log.e("vvvv", "setSecureElement-FAILED");
                    tv1.setText("FAILED");
                    break;
            }

//            if (mIsSupportESe) {
//                int ese_result = SetNfcNxpSe.selectDefaultSecureElement(mNfc, getPackageName(), ESE_ID);
//                switch (ese_result) {
//                    case SetNfcNxpSe.NOT_READY:
//                        mSwpESeStatus.setText(STRING_NOT_READY);
//                        break;
//                    case SetNfcNxpSe.SUCCESS:
//                        mSwpESeStatus.setText(getString(R.string.nfc_test_swp_status_pass));
//                        break;
//                    case SetNfcNxpSe.NO_NFC_SIM:
//                        mSwpESeStatus.setText(getString(R.string.nfc_test_swp_status_no_nfc_sim));
//                        break;
//                    case SetNfcNxpSe.FAILED:
//                        mSwpESeStatus.setText(getString(R.string.nfc_test_swp_status_ng));
//                        break;
//                    }
//            }
        }

        //mTagReadStatus.setText(getString(R.string.nfc_test_tag_reading_enable));
        //mTagReadResult.setText(getString(R.string.nfc_test_tag_reading_ready));
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        Log.e("vvvv", "onCheckedChanged-checkedId:"+checkedId);
        switch (checkedId) {
        case R.id.radio1:
            //Toast.makeText(this,"radio1",Toast.LENGTH_SHORT).show();
            Log.e("vvvv", "onCheckedChanged-radio1-*****");
            Log.e("vvvv", "onCheckedChanged-radio1-SystemProperties.get(wlan.antenna_mode, 0):"+SystemProperties.get("wlan.antenna_mode", "0"));
            Log.e("vvvv", "onCheckedChanged-radio1-SystemProperties.get(wlan.antenna_mode.action, 0):"+SystemProperties.get("wlan.antenna_mode.action", "0"));
            Log.e("vvvv", "onCheckedChanged-radio1--");
            SystemProperties.set("wlan.antenna_mode", "1");
            SystemProperties.set("wlan.antenna_mode.action", "set");
            Log.e("vvvv", "onCheckedChanged-radio1---*****");
            break;
        case R.id.radio2:
            //Toast.makeText(this,"radio2",Toast.LENGTH_SHORT).show();
            Log.e("vvvv", "onCheckedChanged-radio2-*****");
            Log.e("vvvv", "onCheckedChanged-radio2-SystemProperties.get(wlan.antenna_mode, 0):"+SystemProperties.get("wlan.antenna_mode", "0"));
            Log.e("vvvv", "onCheckedChanged-radio2-SystemProperties.get(wlan.antenna_mode.action, 0):"+SystemProperties.get("wlan.antenna_mode.action", "0"));
            Log.e("vvvv", "onCheckedChanged-radio2--");
            SystemProperties.set("wlan.antenna_mode", "2");
            SystemProperties.set("wlan.antenna_mode.action", "set");
            Log.e("vvvv", "onCheckedChanged-radio2---*****");
            break;
        case R.id.radio3:
            //Toast.makeText(this,"radio3",Toast.LENGTH_SHORT).show();
            Log.e("vvvv", "onCheckedChanged-radio3-*****");
            Log.e("vvvv", "onCheckedChanged-radio3-SystemProperties.get(wlan.antenna_mode, 0):"+SystemProperties.get("wlan.antenna_mode", "0"));
            Log.e("vvvv", "onCheckedChanged-radio3-SystemProperties.get(wlan.antenna_mode.action, 0):"+SystemProperties.get("wlan.antenna_mode.action", "0"));
            Log.e("vvvv", "onCheckedChanged-radio3--");
            SystemProperties.set("wlan.antenna_mode", "3");
            SystemProperties.set("wlan.antenna_mode.action", "set");
            Log.e("vvvv", "onCheckedChanged-radio3---*****");
            break;
        default:
            
            break;
        }
    }

}

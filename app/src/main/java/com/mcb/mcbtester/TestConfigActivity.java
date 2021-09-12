package com.mcb.mcbtester;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.ParcelUuid;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.Set;

public class TestConfigActivity extends AppCompatActivity {

    TextView Any, B, C, Ol, Sc1, Sc2, Curr1, Curr2, Curr4, Curr6, Curr10, Curr16, Curr25, Curr32, StartTest, Connect, NewTest;
    TextView MCBType, TestType, CurrRating, TestCurr, TestVolts, TestDuration, CalliVolts;
    String mcbType, testType;
    int currRating = 0;
    EditText CallibVolts;
    ScrollView scrollView;
    DecimalFormat df = new DecimalFormat("#.##");

    BluetoothAdapter bluetoothAdapter;
    Set<BluetoothDevice> bluetoothDevices;
    OutputStream outputStream;
    InputStream inputStream;

    boolean correctValue = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_config);

        Any = (TextView)findViewById(R.id.btntypeany);
        B = (TextView)findViewById(R.id.btntypeb);
        C = (TextView)findViewById(R.id.btntypec);

        Ol = (TextView)findViewById(R.id.btnttol);
        Sc1 = (TextView)findViewById(R.id.btnttsc1);
        Sc2 = (TextView)findViewById(R.id.btnttsc2);

        Curr1 = (TextView)findViewById(R.id.btncurr1);
        Curr2 = (TextView)findViewById(R.id.btncurr2);
        Curr4 = (TextView)findViewById(R.id.btncurr4);
        Curr6 = (TextView)findViewById(R.id.btncurr6);
        Curr10 = (TextView)findViewById(R.id.btncurr10);
        Curr16 = (TextView)findViewById(R.id.btncurr16);
        Curr25 = (TextView)findViewById(R.id.btncurr25);
        Curr32 = (TextView)findViewById(R.id.btncurr32);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        /*Connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!bluetoothAdapter.isEnabled()) {
                    Intent enableBT = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivity(enableBT);
                }
                else{
                    bluetoothDevices = bluetoothAdapter.getBondedDevices();

                    for (BluetoothDevice bluetoothDevice : bluetoothDevices){
                        if (bluetoothDevice.getName().equals("HC-05")){
                            ParcelUuid[] uuids = bluetoothDevice.getUuids();
                            BluetoothSocket socket;
                            try {
                                socket = bluetoothDevice.createRfcommSocketToServiceRecord(uuids[0].getUuid());
                                socket.connect();

                                if (socket.isConnected()){
                                    Toast.makeText(getApplicationContext(), "Connected to Arduino!", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(getApplicationContext(), "Not Connected!", Toast.LENGTH_SHORT).show();
                                }
                                outputStream = socket.getOutputStream();
                                inputStream = socket.getInputStream();
                            } catch (IOException e) {
                                Toast.makeText(getApplicationContext(), "Error in connection!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }
        });*/

        Any.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mcbType = "ANY";
                clearMCBType();
                select(Any);
            }
        });

        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mcbType = "B";
                clearMCBType();
                select(B);
            }
        });

        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mcbType = "C";
                clearMCBType();
                select(C);
            }
        });

        Ol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testType = "OL";
                clearTestType();
                select(Ol);
            }
        });

        Sc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testType = "SC1";
                clearTestType();
                select(Sc1);
            }
        });

        Sc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testType = "SC2";
                clearTestType();
                select(Sc2);
            }
        });

        Curr1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currRating = 1;
                clearCurrRating();
                select(Curr1);
            }
        });

        Curr2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currRating = 2;
                clearCurrRating();
                select(Curr2);
            }
        });

        Curr4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currRating = 4;
                clearCurrRating();
                select(Curr4);
            }
        });

        Curr6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currRating = 6;
                clearCurrRating();
                select(Curr6);
            }
        });

        Curr10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currRating = 10;
                clearCurrRating();
                select(Curr10);
            }
        });

        Curr16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currRating = 16;
                clearCurrRating();
                select(Curr16);
            }
        });

        Curr25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currRating = 25;
                clearCurrRating();
                select(Curr25);
            }
        });

        Curr32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currRating = 32;
                clearCurrRating();
                select(Curr32);
            }
        });

        /*StartTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!bluetoothAdapter.isEnabled()) {
                    Intent enableBT = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivity(enableBT);
                }
                else{
                    double testCurr=0, testVolts=0, testDur=0;
                    scrollView.smoothScrollTo(0,scrollView.getBottom());
                    MCBType.setText("MCB Type: "+mcbType);
                    TestType.setText("Test Type: "+testType);
                    CurrRating.setText("Current Rating: "+currRating+" A");
                    CalliVolts.setText("Callib Volts: "+CallibVolts.getText().toString()+" V");

                    if (mcbType.equals("ANY")){
                        if (testType.equals("OL")){
                            testCurr = 2*currRating;
                            testVolts = 0.75*testCurr;
                            testDur = 100;
                            correctValue = true;
                        }
                        else{
                            correctValue = false;
                            testCurr = 0; testVolts = 0; testDur = 0;
                            Toast.makeText(getApplicationContext(), "Invalid Combination!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else if (mcbType.equals("B")){
                        if (testType.equals("SC1")){
                            testCurr = 4*currRating;
                            testVolts = 0.75*testCurr;
                            testDur = 0.5;
                            correctValue = true;
                        }
                        else if (testType.equals("SC2")){
                            testCurr = 5*currRating;
                            testVolts = 0.75*testCurr;
                            testDur = 0.5;
                            correctValue = true;
                        }
                        else{
                            correctValue = false;
                            testCurr = 0; testVolts = 0; testDur = 0;
                            Toast.makeText(getApplicationContext(), "Invalid Combination!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else if (mcbType.equals("C")){
                        if (testType.equals("SC1")){
                            testCurr = 7.5*currRating;
                            testVolts = 0.75*testCurr;
                            testDur = 0.5;
                            correctValue = true;
                        }
                        else if (testType.equals("SC2")){
                            testCurr = 10*currRating;
                            testVolts = 0.75*testCurr;
                            testDur = 0.5;
                            correctValue = true;
                        }
                        else{
                            correctValue = false;
                            testCurr = 0; testVolts = 0; testDur = 0;
                            Toast.makeText(getApplicationContext(), "Invalid Combination!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    TestCurr.setText("Test Current: "+df.format(testCurr)+" A");
                    TestVolts.setText("Test Volts: "+df.format(testVolts)+" V");
                    TestDuration.setText("Test Duration: "+df.format(testDur)+" s");

                    if (correctValue){
                        try {
                            double value = testVolts;
                            value = value/240*4800*1.125;
                            String s = ""+(int)value+" "+(int)(testDur*1000)+",";
                            outputStream.write(s.getBytes());
                            int rslt = inputStream.read();
                            Toast.makeText(getApplicationContext(), "Test started successfully!", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            Toast.makeText(getApplicationContext(), "Out of range / Disconnected!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        NewTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scrollView.smoothScrollTo(0,0);
            }
        });*/
    }

    public void connect(){
        final Dialog dialog = new Dialog(this, R.style.myDialog);
        View view = getLayoutInflater().inflate(R.layout.activity_connect_bluetooth, null);
        Button button = (Button)view.findViewById(R.id.btnconnect);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!bluetoothAdapter.isEnabled()) {
                    Intent enableBT = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivity(enableBT);
                }
                else{
                    bluetoothDevices = bluetoothAdapter.getBondedDevices();

                    for (BluetoothDevice bluetoothDevice : bluetoothDevices){
                        if (bluetoothDevice.getName().equals("HC-05")){
                            ParcelUuid[] uuids = bluetoothDevice.getUuids();
                            BluetoothSocket socket;
                            try {
                                socket = bluetoothDevice.createRfcommSocketToServiceRecord(uuids[0].getUuid());
                                socket.connect();

                                if (socket.isConnected()){
                                    Toast.makeText(getApplicationContext(), "Connected to Arduino!", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(getApplicationContext(), "Not Connected!", Toast.LENGTH_SHORT).show();
                                }
                                outputStream = socket.getOutputStream();
                                inputStream = socket.getInputStream();
                            } catch (IOException e) {
                                Toast.makeText(getApplicationContext(), "Error in connection!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
                dialog.dismiss();
            }
        });
        dialog.setContentView(view);
        dialog.show();
    }

    public void clearMCBType(){
        deselect(Any, 0);
        deselect(B, 0);
        deselect(C, 0);
    }

    public void clearTestType(){
        deselect(Ol, 1);
        deselect(Sc1, 1);
        deselect(Sc2, 1);
    }

    public void clearCurrRating(){
        deselect(Curr1, 2);
        deselect(Curr2, 2);
        deselect(Curr4, 2);
        deselect(Curr6, 2);
        deselect(Curr10, 2);
        deselect(Curr16, 2);
        deselect(Curr25, 2);
        deselect(Curr32, 2);
    }

    void select(View view){
        view.setBackgroundTintList(getResources().getColorStateList(R.color.skinColor));
    }

    void deselect(View view, int item){
        if (item == 0){ view.setBackgroundTintList(getResources().getColorStateList(R.color.lightSkin)); }
        else if (item == 1){ view.setBackgroundTintList(getResources().getColorStateList(R.color.periwinkle)); }
        else{ view.setBackgroundTintList(getResources().getColorStateList(R.color.palePink)); }
    }
}
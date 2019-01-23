package com.example.a61979.alipayclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.a61979.alipayservice.IService;

public class MainActivity extends AppCompatActivity {

    private Button bt_connect;
    private Button bt_disconnect;
    private ServiceConnection conn=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                Mybinder = IService.Stub.asInterface(iBinder);
                Mybinder.callSafepay("VirgilG72","123456",300,123456789);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Mybinder=null;
            Log.e("1","无法连接服务");
        }
    };
    private IService Mybinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_connect = (Button) findViewById(R.id.button);
        bt_disconnect = (Button) findViewById(R.id.button2);
        bt_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("com.example.a61979.alipayservice.AliService");
                intent.setPackage("com.example.a61979.alipayservice");

                bindService(intent,conn,BIND_AUTO_CREATE);
            }
        });
        bt_disconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unbindService(conn);
                Log.e("1","成功解绑服务");
            }
        });
    }



}

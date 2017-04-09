package com.example.sin.mapdemo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class InfoActivity extends Activity {
    ImageView imgView;
    TextView  lotText;
    TextView  titleText, addrText;
    TextView  startTime,endTime;
    TextView  camText;
    TextView  msgText;

    String lot, title, addr, stime, etime, cam, msg;
    int available_lot, num;
    int project_no;
    String camnum;

    ServerConnect connect = new ServerConnect();
    JSONParser parser = new JSONParser();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_drawer);

        imgView = (ImageView) findViewById(R.id.imgView);
        lotText = (TextView) findViewById(R.id.lotText);
        titleText = (TextView) findViewById(R.id.titleText);
        addrText = (TextView) findViewById(R.id.addrText);
        startTime = (TextView) findViewById(R.id.startTime);
        endTime = (TextView) findViewById(R.id.endTime);
        camText = (TextView) findViewById(R.id.camText);
        msgText = (TextView) findViewById(R.id.msgText);

        boolean g = getData();

        if(g) {
            Bitmap bm = connect.bringImage(project_no);
            num = connect.availableLot(project_no);
            available_lot = (Integer.parseInt(lot) - num);


            imgView.setImageBitmap(bm);
            lotText.append(available_lot + "/" + lot);
            titleText.append(title);
            addrText.append(addr);
            startTime.append(stime);
            endTime.append(etime);
            camText.append(cam);
            msgText.append(msg);
        }


    }

    private boolean getData() {
        Intent inIntent = getIntent();
        int no = inIntent.getIntExtra("project_no", 0);

        String result = connect.sendByHttp(no);

        if(Objects.equals(result, "connect fail")) {
            Toast.makeText(getApplicationContext(), "server connect fail", Toast.LENGTH_SHORT).show();
            return false;

        } else {
            String[][] parsedData = parser.mapInfoParserList(result);

            project_no = no;
            lot = parsedData[0][0];
            addr = parsedData[0][1];
            title = parsedData[0][2];
            stime = parsedData[0][3];
            etime = parsedData[0][4];
            cam = parsedData[0][5];
            camnum = parsedData[0][6];
            msg = parsedData[0][7];

            return true;
        }
    }

}

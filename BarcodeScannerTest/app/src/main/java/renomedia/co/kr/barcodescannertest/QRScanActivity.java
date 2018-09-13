package renomedia.co.kr.barcodescannertest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import java.util.List;

/**
 * Created by hansu on 2017. 12. 21..
 */

//public class QRScanActivity extends AppCompatActivity {
// damanegi : 30 Lock 처리를 위해 BaseActivity 상속

public class QRScanActivity extends AppCompatActivity {


    DecoratedBarcodeView mBarcodeScannerView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_qr_scan);

        mBarcodeScannerView = (DecoratedBarcodeView) findViewById(R.id.barcode_scanner);
        mBarcodeScannerView.setStatusText("");

        mBarcodeScannerView.decodeSingle(new BarcodeCallback() {
            @Override
            public void barcodeResult(BarcodeResult result) {

                Log.d("BarcodeResult","result : " +result);
                String barcode_result  = result.getText();

                if(barcode_result!=null && barcode_result.contains("qr_token") )
                {
                    try {
                        Log.d("BarcodeResult Init","result : " +result);
//                        Intent i = new Intent(QRScanActivity.this, PassMainActivity.class);
                        Intent i = new Intent();
                        i.putExtra("qrdata", barcode_result);
//                        startActivity(i);
                        setResult(0,i);
                        finish();
                        Log.d("BarcodeResult","barcode_result : "+barcode_result);
                    }catch (Exception e){
                        Log.d("exception","MSG :"+e.getMessage());
                    }
                }
            }

            @Override
            public void possibleResultPoints(List<ResultPoint> resultPoints) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        mBarcodeScannerView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mBarcodeScannerView.pause();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        finish();
    }
}

package renomedia.co.kr.barcodescannertest;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        PermissionListener permissionlistener = new PermissionListener() {
//            @Override
//            public void onPermissionGranted() {
//                Toast.makeText(MainActivity.this, "권한 있다", Toast.LENGTH_SHORT).show();
//
//                // 허락을 한 경우 실행할 부분
//
//                Intent i = new Intent(MainActivity.this,QRScanActivity.class);
//                startActivity(i);
//            }
//
//            @Override
//            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
//                Toast.makeText(MainActivity.this, "권한 없다\n", Toast.LENGTH_SHORT).show();
//
//                // 거절을 한 경우 실행할 부분
//
//            }
//
//
//        };
//
//        new TedPermission(this)
//                .setPermissionListener(permissionlistener)
//                .setRationaleMessage("PASS를 사용하려면 카메라와 푸시 설정 허용이 필수야!!")
//                .setRationaleConfirmText("오키")
//                .setDeniedCloseButtonText("노놉")
//                .setDeniedMessage("필수 허용이야... 빨리 설정가서 허용해 [Setting] > [Permission]")
//                .setGotoSettingButton(true)
//                .setPermissions(Manifest.permission.READ_CONTACTS, Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CAMERA)
//                .check();


        pedPermissionCheckMessage();

    }
    private void pedPermissionCheckMessage() {
        new TedPermission(this)
                .setPermissionListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        //권한이 허락된경우
                        Intent i = new Intent(MainActivity.this,midActivity.class);
                        startActivity(i);
                        Toast.makeText(MainActivity.this, "권한 있다", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                        //권한이 취소된경우
                        Intent i = new Intent(MainActivity.this,midActivity.class);
                        startActivity(i);
                        Toast.makeText(MainActivity.this, "권한 없다", Toast.LENGTH_SHORT).show();
                    }
                })
                .setPermissions(android.Manifest.permission.CAMERA)
                .setRationaleMessage("PASS를 사용하려면 카메라 허용이 필수입니다!")
                .setRationaleConfirmText("네")
                .setDeniedCloseButtonText("아니요")
                .setGotoSettingButton(true)
                .check();
    }
}

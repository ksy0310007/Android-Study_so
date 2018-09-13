package renomedia.co.kr.contentprovidertest;

import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 뷰
        GridView photoListView = (GridView) findViewById(R.id.photo_list);
        // 사진 데이터
        Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,     // From 절 : 무슨 정보에 접근하려는지.
                null,  // Select 절
                null,   // Where 절
                null,  // Where 절
                MediaStore.Images.ImageColumns.DATE_TAKEN+" DESC");   // Order By 절 : 정렬, 사진을 찍은 날짜 내림차순

        // 쿼리의 결과로 cursor를 받는데 행과열이 있는 데이터 형태

        // 어댑터
        MyCursorAdapter adapter = new MyCursorAdapter(this, cursor);
        photoListView.setAdapter(adapter);


        // 클릭 시 처리
        photoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 클릭한 부분의 cursor데이터
                Cursor cursor = (Cursor) parent.getAdapter().getItem(position);
                String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
                Toast.makeText(MainActivity.this,"사진경로 : " +path,Toast.LENGTH_SHORT).show();
            }
        });


    }

}

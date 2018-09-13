package renomedia.co.kr.adapterviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //자료
        ArrayList<Weather> data = new ArrayList<>();
        data.add(new Weather("수원","25도", "장마"));
        data.add(new Weather("서울","26도", "맑음"));
        data.add(new Weather("안양","24도", "비"));
        data.add(new Weather("부산","27도", "구름"));
        data.add(new Weather("인천","24도", "비"));
        data.add(new Weather("대구","25도", "눈"));
        data.add(new Weather("용인","3도", "눈"));
        data.add(new Weather("경주","18도", "장마"));
        data.add(new Weather("수원","25도", "장마"));
        data.add(new Weather("서울","26도", "맑음"));
        data.add(new Weather("안양","24도", "비"));
        data.add(new Weather("부산","27도", "구름"));
        data.add(new Weather("인천","24도", "비"));
        data.add(new Weather("대구","25도", "눈"));
        data.add(new Weather("용인","3도", "눈"));
        data.add(new Weather("경주","18도", "장마"));

        //어댑터
        MyFirstAdapter adapter = new MyFirstAdapter(data);

        // 뷰
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }
}

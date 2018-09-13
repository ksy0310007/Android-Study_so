package renomedia.co.kr.adapterviewtest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyFirstAdapter extends BaseAdapter {   //BaseAdapter는 추상 클래스이므로 정의되지 않은 메서드들을 구현해야 한다. (getCount, getItem, getItemId, getView)

    private final List<Weather> mData;
    private Map<String, Integer> mWeatherImageMap;

    // List를 구현한 모든 것 (ArrayList 등)을 받는 생성자
    public MyFirstAdapter(List<Weather> data){
        mData = data;
        mWeatherImageMap = new HashMap<>();
        mWeatherImageMap.put("맑음",R.drawable.sunny);
        mWeatherImageMap.put("장마",R.drawable.flash);
        mWeatherImageMap.put("구름",R.drawable.clouds);
        mWeatherImageMap.put("비",R.drawable.rain);
        mWeatherImageMap.put("눈",R.drawable.snowflake);

    }

    @Override
    public int getCount() {
        // 아이템의 개수
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        // position 번째의 아이템
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        // position 번째의 아이디
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // position 번째의 아이템의 View를 구성하는 부분, 아이템 하나하나가 화면에 표시 될 때마다 호출
        //LayoutInflater 는 Activity 이외의 클래스에서 Context를 통해 XML로 정의한 레이아웃을 로드하여 View로 반환해주는 클래스
        //convertView는 재사용 되는 뷰

        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather, parent, false);

            // 날씨, 도시, 기온 View
            ImageView weatherImage = (ImageView)convertView.findViewById(R.id.weather_image);
            TextView cityText = (TextView)convertView.findViewById(R.id.city_text);
            TextView tempText = (TextView)convertView.findViewById(R.id.temp_text);

            holder.weatherImage = weatherImage;
            holder.cityText = cityText;
            holder.tempText = tempText;
            convertView.setTag(holder);
        }else {
            // 재사용 할 때 꺼내 씀.
            // setTag() 는 모든 객체를 담을 수 있는 다용도 메서드
            holder = (ViewHolder)convertView.getTag();
        }

        //현재 position의 날씨 데이터
        Weather weather = mData.get(position);

        //데이터 설정
        holder.cityText.setText(weather.getCity());
        holder.tempText.setText(weather.getTemp());
        holder.weatherImage.setImageResource(mWeatherImageMap.get(weather.getWeather()));

        return convertView;

    }

    static class ViewHolder {
        // 자주 사용하는 뷰를 한번 로드하면 재사용하고 표시할 내용만 교체
        ImageView weatherImage;
        TextView cityText;
        TextView tempText;
    }
}

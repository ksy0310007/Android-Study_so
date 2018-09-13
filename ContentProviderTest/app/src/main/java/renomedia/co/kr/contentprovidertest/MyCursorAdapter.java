package renomedia.co.kr.contentprovidertest;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MyCursorAdapter extends CursorAdapter {


    public MyCursorAdapter(Context context, Cursor c) {
        super(context, c,false);
    }

    // BaseAdapter에서의 getView() 가 CursorAdapter 에서는 newView()와 bindView()로 나누어짐.

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // 맨처음 레이아웃을 생성하는 부분
        return LayoutInflater.from(context).inflate(R.layout.item_photo, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // 데이터를 뷰에 바인딩하여 화면에 실제로 표시하는 부분
        ImageView imageView = (ImageView)view.findViewById(R.id.photo_image);
        // 사진경로 가져오기 (URI)
        String uri = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
        // 사진을 이미지뷰에 표시하기
//        imageView.setImageURI(Uri.parse(uri));
        Glide.with(context).load(uri).into(imageView);
    }
}

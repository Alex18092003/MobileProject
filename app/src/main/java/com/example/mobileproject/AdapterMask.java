package com.example.mobileproject;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AdapterMask extends BaseAdapter {
    private Context mContex;
    private ArrayList<Mask> mListMAsk;
    private OnItemClickListener mListener;
    String img ="";
    List<Mask> maskList;

    public interface OnItemClickListener
    {
        void onItemClick(int position);
    }
    public  void  setOnItemClickListener(OnItemClickListener listener)
    {
        mListener = listener;
    }

    public  AdapterMask(Context mContex, List<Mask> maskList)
    {
        this.mContex = mContex;
        this.maskList = maskList;
    }
    @Override
    public int getCount() {
        return maskList.size();
    }
    @Override
    public Object getItem(int i) {
        return maskList.get(i);
    }
    @Override
    public long getItemId(int i)
    {
        return maskList.get(i).getID();
    }


    public static Bitmap loadContactPhoto(ContentResolver cr, long id, Context context) {
        Uri uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, id);
        InputStream input = ContactsContract.Contacts.openContactPhotoInputStream(cr, uri);
        if (input == null) {
            Resources res = context.getResources();
            //return BitmapFactory.decodeResource(res, R.drawable.nophoto);
        }
        return BitmapFactory.decodeStream(input);
    }

    public Bitmap getUserImage(String encodedImg) {

        byte[] bytes = Base64.decode(encodedImg, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    @Override
    public View getView(int p, View convertView, ViewGroup parent) {
        View v = View.inflate(mContex, R.layout.activity_conclusion_fact, null);

        TextView Fact = v.findViewById(R.id.textFact);
        ImageView Images = v.findViewById(R.id.imageView);

        Mask mask = maskList.get(p);
        Fact.setText(mask.getFact());
        Images.setImageBitmap(getUserImage(mask.getImages()));
        return v;
    }
}

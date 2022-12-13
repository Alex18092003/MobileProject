package com.example.mobileproject;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
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
    private Context mContext;
    private ArrayList<Mask> mListMask;
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

    public  AdapterMask(Context mContext, List<Mask> maskList)
    {
        this.mContext = mContext;
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
            return BitmapFactory.decodeResource(res, R.drawable.nophoto);
        }
        return BitmapFactory.decodeStream(input);
    }

    public Bitmap getUserImage(String encodedImg) {
        if(encodedImg!=null && !encodedImg.equals("null")) {
            byte[] bytes = Base64.decode(encodedImg, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        }
        else
            return BitmapFactory.decodeResource(mContext.getResources(), R.drawable.nophoto);
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.activity_conclusion_fact, null);

        TextView Fact = v.findViewById(R.id.textFact);
        ImageView Images = v.findViewById(R.id.imageView);

        Mask mask = maskList.get(i);
        Fact.setText(mask.getFact());
        Images.setImageBitmap(getUserImage(mask.getImages()));

        return v;
    }
}

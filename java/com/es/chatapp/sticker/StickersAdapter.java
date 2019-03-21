package com.es.chatapp.sticker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.es.chatapp.MessageActivity;
import com.es.chatapp.R;
import com.squareup.picasso.Picasso;

public class StickersAdapter extends BaseAdapter {

    private final Context mContext;
    private final Sticker[] stickers;

    // q1
    public StickersAdapter(Context context, Sticker[] stickers) {
        this.mContext = context;
        this.stickers = stickers;
    }

    // a2
    @Override
    public int getCount() {
        return stickers.length;
    }

    // ba
    @Override
    public long getItemId(int position) {
        return 0;
    }

    // a4
    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Sticker sticker = stickers[position];

        // view holder pattern
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.fragment_list_nearly_user, null);

            final ImageView imageViewCoverArt = (ImageView)convertView.findViewById(R.id.imageviewB);
            final ViewHolder viewHolder = new ViewHolder(imageViewCoverArt);
            convertView.setTag(viewHolder);
        }

        final ViewHolder viewHolder = (ViewHolder)convertView.getTag();
        Picasso.with(mContext).load(sticker.getImageResource()).into(viewHolder.imageViewCoverArt);

        if (position == MessageActivity.currentSticker) {
            convertView.setAlpha(0.5f);
        } else {
            convertView.setAlpha(1f);
        }

        return convertView;
    }

    // Your "view holder" that holds references to each subview
    private class ViewHolder {
        private final ImageView imageViewCoverArt;

        public ViewHolder(ImageView imageViewCoverArt) {
            this.imageViewCoverArt = imageViewCoverArt;
        }
    }

}
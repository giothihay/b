package com.es.chatapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.MemoryCategory;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.es.chatapp.Model.Chat;
import com.es.chatapp.R;
import com.squareup.picasso.Picasso;
import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    public static  final int MSG_TYPE_LEFT = 0;
    public static  final int MSG_TYPE_RIGHT = 1;

    private Context mContext;
    private List<Chat> mChat;
    private String imageurl;

    FirebaseUser fuser;

    public MessageAdapter(Context mContext, List<Chat> mChat, String imageurl){
        this.mChat = mChat;
        this.mContext = mContext;
        this.imageurl = imageurl;
    }

    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == MSG_TYPE_RIGHT) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_right, parent, false);
            return new MessageAdapter.ViewHolder(view);
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_left, parent, false);
            return new MessageAdapter.ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder holder, int position) {

        Chat chat = mChat.get(position);
    try {
        if (chat.getType().equalsIgnoreCase("image")) {
            holder.show_message.setVisibility(View.INVISIBLE);
           // Glide.get(holder.img_send.getContext()).setMemoryCategory(MemoryCategory.HIGH);
            Glide.with(holder.img_send.getContext())
                    .load(chat.getMessage())
                    .transition(withCrossFade())
                    .apply(new RequestOptions().override(300, 300)
                            .placeholder(R.drawable.ic_launcher_background)
                            .error(R.drawable.ic_launcher_background).centerCrop()
                    )
                    .into(holder.img_send);
//            Picasso.with(holder.img_send.getContext()).load(chat.getMessage())
//                    .placeholder(R.drawable.default_avatar).into(holder.img_send);

        } else if (chat.getMessage().substring(0,5).equalsIgnoreCase("gift:"))
        {

            holder.show_message.setVisibility(View.INVISIBLE);
           // Glide.get(holder.img_send.getContext()).setMemoryCategory(MemoryCategory.HIGH);
            Glide.with(holder.img_send.getContext())
                    .load(chat.getMessage())//.diskCacheStrategy(DiskCacheStrategy.DATA)
                    .transition(withCrossFade())
                    .apply(new RequestOptions().override(300, 300)
                            .placeholder(R.drawable.ic_launcher_background)
                            .error(R.drawable.ic_launcher_background).centerCrop()
                    )
                    .into(holder.img_send);
           // Picasso.with(holder.img_send.getContext()).load(chat.getMessage().replaceAll("gift:","").trim())
                    //.placeholder(R.drawable.default_avatar).into(holder.img_send);
        }
            else {
        holder.img_send.setVisibility(View.INVISIBLE);
        holder.show_message.setText(chat.getMessage());
      }
} catch (Exception e){
        holder.img_send.setVisibility(View.INVISIBLE);
        holder.show_message.setText(chat.getMessage());
}



        if (imageurl.equals("default")){
            holder.profile_image.setImageResource(R.mipmap.ic_launcher);
        } else {
            Glide.with(mContext).load(imageurl).into(holder.profile_image);
        }

        if (position == mChat.size()-1){
            if (chat.isIsseen()){
                holder.txt_seen.setText("Seen");
            } else {
                holder.txt_seen.setText("Delivered");
            }
        } else {
            holder.txt_seen.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return mChat.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

        public TextView show_message;
        public ImageView profile_image;
        public TextView txt_seen;
        public ImageView img_send;

        public ViewHolder(View itemView) {
            super(itemView);

            show_message = itemView.findViewById(R.id.show_message);
            profile_image = itemView.findViewById(R.id.profile_image);
            txt_seen = itemView.findViewById(R.id.txt_seen);
            img_send = itemView.findViewById(R.id.message_image_layout);
        }
    }

    @Override
    public int getItemViewType(int position) {
        fuser = FirebaseAuth.getInstance().getCurrentUser();
        if (mChat.get(position).getSender().equals(fuser.getUid())){
            return MSG_TYPE_RIGHT;
        } else {
            return MSG_TYPE_LEFT;
        }
    }
}
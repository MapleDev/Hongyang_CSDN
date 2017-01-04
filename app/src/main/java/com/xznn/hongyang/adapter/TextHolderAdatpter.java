package com.xznn.hongyang.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xznn.hongyang.R;
import com.xznn.hongyang.bean.TextBean;

import java.util.List;


/**
 * Created by wuming on 16/10/13.
 */

public class TextHolderAdatpter extends RecyclerView.Adapter<TextHolderAdatpter.TextHolder> {

    private static final String TAG = TextHolderAdatpter.class.getSimpleName();

    public List<TextBean> data;    //adapter中的数据是存放在List里面的
    public Context context;
    public LayoutInflater inflater;
    private TextHolderClickListener listener;

    public TextHolderAdatpter(Context context, List<TextBean> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    public void setTextHolderClickListener(TextHolderClickListener listener) {
        this.listener = listener;
    }

    @Override
    public TextHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TextHolder(inflater.inflate(R.layout.item_text_holder, parent, false));
    }

    @Override
    public void onBindViewHolder(TextHolder holder, final int position) {
        final TextBean textBean = data.get(position);

        holder.num_tv.setText(position + 1 < 10 ? "0" + (position + 1) : "" + (position + 1));
        holder.title_tv.setText(textBean.getTitle());
        holder.subtitle_tv.setText(textBean.getSubTitle());
        if (position < 2) {
            holder.num_tv.setTextColor(Color.WHITE);
            holder.num_tv.setBackgroundColor(Color.parseColor("#FF1111"));
        } else {
            holder.num_tv.setTextColor(Color.DKGRAY);
            holder.num_tv.setBackgroundColor(Color.WHITE);
        }
        if (position + 1 == data.size()) {
            holder.line.setVisibility(View.GONE);
        } else {
            holder.line.setVisibility(View.VISIBLE);
        }

//        ((ViewGroup) holder.num_tv.getParent()).setOnClickListener(new OnTextClick(position));

        ((ViewGroup) holder.num_tv.getParent()).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onTextClick(data.get(position));
                }
            }
        });
    }

//    private class OnTextClick implements View.OnClickListener {
//
//        private int position;
//
//        public OnTextClick(int position) {
//            this.position = position;
//        }
//
//        @Override
//        public void onClick(View v) {
//            if (listener != null) {
//                listener.onTextClick(data.get(position));
//            }
//        }
//    }


    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public interface TextHolderClickListener {
        void onTextClick(TextBean bean);
    }

    static class TextHolder extends RecyclerView.ViewHolder {
        public TextView title_tv;
        public TextView subtitle_tv;
        public TextView num_tv;
        public View line;

        public TextHolder(View itemView) {
            super(itemView);
            num_tv = (TextView) itemView.findViewById(R.id.text_holder_num_tv);
            title_tv = (TextView) itemView.findViewById(R.id.text_holder_title_tv);
            subtitle_tv = (TextView) itemView.findViewById(R.id.text_holder_subtitle_tv);
            line = itemView.findViewById(R.id.text_holder_line);
        }
    }
}

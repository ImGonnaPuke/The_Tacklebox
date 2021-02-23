package com.example.thetacklebox;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LureAdaptor extends RecyclerView.Adapter<LureAdaptor.MyViewHolder> implements Filterable {



    //String data1[], data2[];
    //int images[];
    Context context;
    private LureAdaptor.OnItemClickListener mListener;
    private LureAdaptor.OnItemLongClickListener mListener2;
    private ArrayList<Items> mExList;
    private ArrayList<Items> mExList2;

    public interface OnItemClickListener{
        void onItemClick(int position);
        void onEditClick(int position);
    }

    public void setOnItemClickListener (LureAdaptor.OnItemClickListener listener){
        mListener = listener;
    }

    public interface OnItemLongClickListener{
        void onItemLongClick(int position);
    }

    public void setOnItemLongClickListener (LureAdaptor.OnItemLongClickListener listener){
        mListener2 = listener;
    }


    public LureAdaptor(ArrayList<Items> exList){

        this.mExList = exList;
        mExList2= new ArrayList<>(exList);

    }

    @NonNull
    @Override
    public LureAdaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater inflater = LayoutInflater.from(context);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lure_default_layout, parent, false);
        //View view = inflater.inflate(R.layout.my_row, parent, false);
        LureAdaptor.MyViewHolder mvh = new LureAdaptor.MyViewHolder(view, mListener, mListener2);
        //LureAdaptor.MyViewHolder mvh2 = new LureAdaptor.MyViewHolder(view, mListener2);

        return mvh;
    }



    @Override
    public void onBindViewHolder(@NonNull LureAdaptor.MyViewHolder holder, int position) {
        Items currentI = mExList.get(position);

        holder.myImage.setImageResource(currentI.getImg());
        holder.myName.setText(currentI.getName());
        holder.myType.setText(currentI.getType());
        holder.myColor.setText(currentI.getColor());
        holder.myNumColor.setText(currentI.getNumColor());
        holder.myLength.setText(currentI.getLength());
        holder.myDepth.setText(currentI.getDepth());
        holder.myWeight.setText(currentI.getWeight());
        holder.myModel.setText(currentI.getModel());
        //holder.myText5.setText(currentI.getDesc());


    }

    @Override
    public int getItemCount() {
        return mExList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView myName, myType, myColor, myNumColor, myLength, myDepth, myWeight, myModel;
        public ImageView myImage;

        public MyViewHolder(@NonNull View itemView, final LureAdaptor.OnItemClickListener listener, final LureAdaptor.OnItemLongClickListener listener2) {
            super(itemView);
            myName = itemView.findViewById(R.id.name);
            myType = itemView.findViewById(R.id.type);
            myColor = itemView.findViewById(R.id.color);
            myNumColor = itemView.findViewById(R.id.numCol);
            myLength = itemView.findViewById(R.id.length);
            myDepth = itemView.findViewById(R.id.depth);
            myWeight = itemView.findViewById(R.id.weight);
            myModel = itemView.findViewById(R.id.model);
            myImage = itemView.findViewById(R.id.img);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                            //listener.onItemClick(position);
                        }
                    }
                }
            });

            myImage.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onEditClick(position);
                            //listener.onItemClick(position);
                        }
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View view) {
                    if(listener2 != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener2.onItemLongClick(position);
                            //listener.onItemClick(position);
                        }
                    }
                    return true;
                }
            });


        }

        /*
        public MyViewHolder(@NonNull View itemView, final LureAdaptor.OnItemLongClickListener listener) {
            super(itemView);
            myName = itemView.findViewById(R.id.name);
            myType = itemView.findViewById(R.id.type);
            myColor = itemView.findViewById(R.id.color);
            myNumColor = itemView.findViewById(R.id.numCol);
            myLength = itemView.findViewById(R.id.length);
            myDepth = itemView.findViewById(R.id.depth);
            myWeight = itemView.findViewById(R.id.weight);
            myModel = itemView.findViewById(R.id.model);
            myImage = itemView.findViewById(R.id.img);

            itemView.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View view) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemLongClick(position);
                            //listener.onItemClick(position);
                        }
                    }
                    return true;
                }
            });


        }
        */
    }


    public Filter getFilter(){
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<Items> filteredList = new ArrayList<>();

            if(charSequence == null|| charSequence.length() ==0){
                filteredList.addAll(mExList2);
            }
            else{
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for (Items item: mExList2){
                    if(item.getName().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }

            FilterResults res = new FilterResults();
            res.values = filteredList;
            return res;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults res) {
            mExList.clear();
            mExList.addAll((ArrayList) res.values);
            notifyDataSetChanged();
        }
    };

}
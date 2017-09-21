package tdbank.com.tdbank;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import tdbank.com.tdbank.services.Weather_report;


/**
 * Created by Rana Prathap on 9/19/2017.
 */

public class WeatherResponseFragment extends Fragment {
    RecyclerView recycler_list;

    ArrayList<Weather_report> list_of_info;

    @Override
    public void onStart() {
        super.onStart();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View vi=inflater.inflate(R.layout.weather_response,container,false);
        recycler_list=(RecyclerView) vi.findViewById(R.id.recycler);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_list.setLayoutManager(layoutManager);
        recycler_list.setAdapter(new MyAdapter());
        list_of_info=getArguments().getParcelableArrayList("TDB");

        return vi;
    }


    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>
    {

        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {



            View itemView = LayoutInflater.
                    from(getActivity()).
                    inflate(R.layout.item_view, parent, false);

            return new MyViewHolder(itemView);

        }

        @Override
        public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {
            holder.location_t.setText("Location at: "+list_of_info.get(position).getLat_val()+"\t"+
                    list_of_info.get(position).getLong_val());
            holder.time_t.setText("Today :"+list_of_info.get(position).getTime_zone());
            holder.summary_t.setText("Weather :"+list_of_info.get(position).getCur_summary());
           switch (list_of_info.get(position).getIcon())
           {
               case 1:
                   holder.bg_img.setBackgroundResource(R.drawable.clear);
                   break;
               case 2:
                   holder.bg_img.setBackgroundResource(R.drawable.rain);
                   break;
               case 3:
                   holder.bg_img.setBackgroundResource(R.drawable.snow);
                   break;
               case 4:
                   holder.bg_img.setBackgroundResource(R.drawable.wind);
                   break;
               case 5:
                   holder.bg_img.setBackgroundResource(R.drawable.cloud);
                   break;
               case 6:
                   holder.bg_img.setBackgroundResource(R.drawable.thunder);
                   break;
           }

        }

        @Override
        public int getItemCount() {
            return list_of_info.size();
        }

        public  class MyViewHolder extends RecyclerView.ViewHolder {
            TextView location_t,time_t,summary_t;
            ImageView bg_img;

            public MyViewHolder(View itemView) {
                super(itemView);
                location_t=(TextView)itemView.findViewById(R.id.item_loc);
                time_t=(TextView)itemView.findViewById(R.id.item_timezone);
                summary_t=(TextView)itemView.findViewById(R.id.item_summary);
                bg_img=(ImageView) itemView.findViewById(R.id.back_img);
            }
        }
    }

}

package com.soc.shopmanagmentsoc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.soc.shopmanagmentsoc.R;
import com.soc.shopmanagmentsoc.model.AdminDashbordDataModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;



public class AdminDashbordDataAdapter extends ArrayAdapter<AdminDashbordDataModel> {

    ArrayList<AdminDashbordDataModel> adminDashbordDataModels;
    Context context;
    public AdminDashbordDataAdapter(@NonNull Context context, int resource, ArrayList<AdminDashbordDataModel> adminDashbordDataModels) {
        super(context, R.layout.admin_dashbord_data_adapte, adminDashbordDataModels);
        this.context = context;
        this.adminDashbordDataModels = adminDashbordDataModels;
    }
    private static class ViewHolder{
        ImageView imge;
        TextView complain;
        TextView status;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder holder;
        final View result;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.admin_dashbord_data_adapte,parent,false);
            holder.imge = (ImageView) convertView.findViewById(R.id.imge);
            holder.complain = (TextView) convertView.findViewById(R.id.complain);
            holder.status = (TextView) convertView.findViewById(R.id.status);
            result = convertView;
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Picasso.get().load(adminDashbordDataModels.get(position).getCustomerNumber()).into(holder.imge);
        holder.complain.setText(adminDashbordDataModels.get(position).getComplain());
        holder.status.setText(adminDashbordDataModels.get(position).getStatus());
        return result;
    }
}

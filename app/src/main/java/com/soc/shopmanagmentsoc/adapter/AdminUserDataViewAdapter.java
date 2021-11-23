package com.soc.shopmanagmentsoc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;


import com.soc.shopmanagmentsoc.R;
import com.soc.shopmanagmentsoc.model.AdminUserModel;

import java.util.ArrayList;
import java.util.List;

public class AdminUserDataViewAdapter extends BaseAdapter {

    private Context context;
    private ItemFilter mFilter;
    ArrayList<AdminUserModel> list;
    ArrayList<AdminUserModel> completeAllocationListModels;
    String strType;

    public AdminUserDataViewAdapter(@NonNull Context context, ArrayList<AdminUserModel> contactListtModels, String strType) {
        this.context = context;
        this.strType = strType;
        this.completeAllocationListModels = contactListtModels;
        this.list = new ArrayList<AdminUserModel>();
        this.list.addAll(contactListtModels);
        mFilter = new ItemFilter();
    }


    public int getCount() {
        return completeAllocationListModels.size();
    }

    public Object getItem(int position) {
        return completeAllocationListModels.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public Filter getFilter() {
        return mFilter;
    }

    public void clearFilter() {
        completeAllocationListModels = (ArrayList<AdminUserModel>) list;

    }

    private static class ViewHolder {
        TextView tv_name, status, table_id, tableDataOthers, tvDate;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View result = null;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.dash_bord_view_adapter, parent, false);
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.status = (TextView) convertView.findViewById(R.id.status);
            holder.table_id = (TextView) convertView.findViewById(R.id.table_id);
            holder.tableDataOthers = (TextView) convertView.findViewById(R.id.tableDataOthers);
            holder.tvDate = (TextView) convertView.findViewById(R.id.tvDate);
            result = convertView;
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        if (strType.equals("View Admin Users")) {
            holder.tv_name.setText(completeAllocationListModels.get(position).getFull_name() + "(" + completeAllocationListModels.get(position).getAdmin_user_name() + ")");
            holder.status.setText("Status : " + completeAllocationListModels.get(position).getAdmin_user_status());
            holder.table_id.setText("Id : " + completeAllocationListModels.get(position).getAdmin_id() +"\n"+
                    "User Type Id : " + completeAllocationListModels.get(position).getUser_type_id());
            holder.tableDataOthers.setText("Phone : " + completeAllocationListModels.get(position).getMobile_no() +"\n"+
                    "Email : " + completeAllocationListModels.get(position).getEmail_address()+"\n"+
                    "Location : " + completeAllocationListModels.get(position).getLatitude()+","+ completeAllocationListModels.get(position).getLongitude()
            );

        }
        else if (strType.equals("View User Type")) {
            holder.tv_name.setText(completeAllocationListModels.get(position).getUserType_user_type_name());
            holder.status.setText("Status : " + completeAllocationListModels.get(position).getUserType_user_type_status());
            holder.table_id.setText("Id : " + completeAllocationListModels.get(position).getUserType_user_type_id());
            holder.tableDataOthers.setText("Others Info : " + completeAllocationListModels.get(position).getUserType_create_info());
        }
        else if (strType.equals("View Shop Info")) {
            holder.tv_name.setText(completeAllocationListModels.get(position).getShop_name());
            holder.status.setText("Status : " + completeAllocationListModels.get(position).getShop_status());
            holder.table_id.setText("Id : " + completeAllocationListModels.get(position).getShop_id());
            holder.tableDataOthers.setText("Others Info : " + completeAllocationListModels.get(position).getShop_address()
                                            +"\nTD NO : "+completeAllocationListModels.get(position).getTd_no()
                                            +"\nEst Date : "+completeAllocationListModels.get(position).getEst_date());
        }
        return result;
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterableString = "";

            String filterString = constraint.toString();

            FilterResults results = new FilterResults();

            final List<AdminUserModel> divisModels = completeAllocationListModels;

            int count = divisModels.size();
            final ArrayList<AdminUserModel> nlist = new ArrayList<AdminUserModel>(count);


            for (int i = 0; i < count; i++) {
                filterableString = divisModels.get(i).getFull_name();
                if (!filterableString.equals("")) {
                    if (filterableString.toLowerCase().contains(filterString)) {
                        nlist.add(divisModels.get(i));
                    }
                }

            }


            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            completeAllocationListModels = (ArrayList<AdminUserModel>) results.values;
            notifyDataSetChanged();
        }

    }
}

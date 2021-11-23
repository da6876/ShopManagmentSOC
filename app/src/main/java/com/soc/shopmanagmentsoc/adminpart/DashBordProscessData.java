package com.soc.shopmanagmentsoc.adminpart;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.soc.shopmanagmentsoc.R;
import com.soc.shopmanagmentsoc.adapter.AdminUserDataViewAdapter;
import com.soc.shopmanagmentsoc.adapter.UserTypeDataViewAdapter;
import com.soc.shopmanagmentsoc.model.AdminUserModel;
import com.soc.shopmanagmentsoc.util.httpd.HttpClientPost;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.soc.shopmanagmentsoc.LoginActivity.rootPath;


public class DashBordProscessData extends AppCompatActivity {
    String strType= "",strOpenType= "",strViewType= "",strStatusCode="";

    ListView ivDashBordData;
    EditText searchViewAdminHome;
    boolean show = false;

    ArrayList<AdminUserModel> adminUserModels = new ArrayList<>();
    AdminUserDataViewAdapter adminUserDataViewAdapter;

    ArrayList<AdminUserModel> userTypeModels = new ArrayList<>();
    UserTypeDataViewAdapter userTypeDataViewAdapter;

    SwipeRefreshLayout swipeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dash_bord_proscess_data);

        ivDashBordData = findViewById(R.id.ivDashBordData);
        searchViewAdminHome = findViewById(R.id.searchViewAdminHome);
        Bundle bundle=getIntent().getExtras();
        if (bundle!=null){
            strType=bundle.getString("Name");
            strViewType=bundle.getString("Name");
            setTitle(strType);
            DashBordListData dashBordListData = new DashBordListData();
            dashBordListData.execute();
        }

        searchViewAdminHome.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = searchViewAdminHome.getText().toString().toLowerCase(Locale.getDefault());
                if (strType.equals("View Admin Users")) {
                    adminUserDataViewAdapter.getFilter().filter(text);
                    adminUserDataViewAdapter.clearFilter();
                }

                else if (strType.equals("View User Type")){
                    userTypeDataViewAdapter.getFilter().filter(text);
                    userTypeDataViewAdapter.clearFilter();
                }

                else if (strType.equals("View Shop Info")){
                    userTypeDataViewAdapter.getFilter().filter(text);
                    userTypeDataViewAdapter.clearFilter();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                if (strType.equals("View Admin Users")) {
                    adminUserModels.clear();
                    DashBordListData dashBordListData = new DashBordListData();
                    dashBordListData.execute();
                    swipeLayout.setRefreshing(false);
                }

                else if (strType.equals("View User Type")) {
                    adminUserModels.clear();
                    DashBordListData dashBordListData = new DashBordListData();
                    dashBordListData.execute();
                    swipeLayout.setRefreshing(false);
                }

                else if (strType.equals("View Shop Info")) {
                    adminUserModels.clear();
                    DashBordListData dashBordListData = new DashBordListData();
                    dashBordListData.execute();
                    swipeLayout.setRefreshing(false);
                }

            }
        });

        ivDashBordData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (strType.equals("View Admin Users")) {
                    /*Bundle bundle = new Bundle();
                    bundle.putString("OpenType","Edit "+strOpenType);
                    bundle.putString("ViewType",strViewType);
                    bundle.putString("ID",adminModels.get(position).getAdmins_id());
                    bundle.putString("Name",adminModels.get(position).getName());
                    bundle.putString("AdminStatus",adminModels.get(position).getAdmins_status());
                    bundle.putString("Email",adminModels.get(position).getEmail());
                    bundle.putString("Password",adminModels.get(position).getPassword());
                    bundle.putString("UserName",adminModels.get(position).getUsername());
                    bundle.putString("UserType",adminModels.get(position).getUser_type_id());
                    bundle.putString("Phone",adminModels.get(position).getPhone());
                    bundle.putString("Image",adminModels.get(position).getShop_id());
                    DashBordDataAddFragment toletDetailsFragment = new DashBordDataAddFragment();
                    toletDetailsFragment.setArguments(bundle);
                    toletDetailsFragment.show(getSupportFragmentManager(), toletDetailsFragment.getTag());*/
                }
                else if (strType.equals("View User Type")) {
                    /*Bundle bundle = new Bundle();
                    bundle.putString("OpenType","Edit "+strOpenType);
                    bundle.putString("ViewType",strViewType);
                    bundle.putString("ID",adminModels.get(position).getAdmins_id());
                    bundle.putString("Name",adminModels.get(position).getName());
                    bundle.putString("AdminStatus",adminModels.get(position).getAdmins_status());
                    bundle.putString("Email",adminModels.get(position).getEmail());
                    bundle.putString("Password",adminModels.get(position).getPassword());
                    bundle.putString("UserName",adminModels.get(position).getUsername());
                    bundle.putString("UserType",adminModels.get(position).getUser_type_id());
                    bundle.putString("Phone",adminModels.get(position).getPhone());
                    bundle.putString("Image",adminModels.get(position).getShop_id());
                    DashBordDataAddFragment toletDetailsFragment = new DashBordDataAddFragment();
                    toletDetailsFragment.setArguments(bundle);
                    toletDetailsFragment.show(getSupportFragmentManager(), toletDetailsFragment.getTag());*/
                }

            }
        });

    }

    private class DashBordListData extends AsyncTask<Void, Void, String> {
        ProgressDialog pd = null;


        @Override
        protected void onPreExecute() {
            pd = ProgressDialog.show(DashBordProscessData.this, "Getting Data Data", "Please wait...");
        }

        @Override
        protected String doInBackground(Void... params) {

            List<Pair<String, String>> postParameters = new ArrayList<>();
            postParameters.add(new Pair("Type",strType));
            String result = "";

            try {
                String response = HttpClientPost.execute(rootPath+"DataProcess.php", postParameters);
                result = response.toString();
                result = result.replaceAll("\n", "");

            } catch (Exception e) {
                Log.e("log_tag", "Error in http connection!!" + e.toString());
            }

            if (strType.equals("View Admin Users")){
                try {
                    adminUserModels.clear();
                    JSONArray jsonArray=new JSONArray(result);
                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        AdminUserModel adminUserModel =new AdminUserModel(
                                jsonObject.getString("admin_id"),
                                jsonObject.getString("admin_user_name"),
                                jsonObject.getString("password"),
                                jsonObject.getString("full_name"),
                                jsonObject.getString("mobile_no"),
                                jsonObject.getString("email_address"),
                                jsonObject.getString("address"),
                                jsonObject.getString("latitude"),
                                jsonObject.getString("longitude"),
                                jsonObject.getString("admin_user_status"),
                                jsonObject.getString("user_type_id"),
                                jsonObject.getString("user_type_name")
                        );
                        adminUserModels.add(adminUserModel);
                    }
                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }
            }

            else if (strType.equals("View User Type")){
                try {
                    userTypeModels.clear();
                    JSONArray jsonArray=new JSONArray(result);
                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        AdminUserModel adminUserModel =new AdminUserModel(
                                jsonObject.getString("user_type_id"),
                                jsonObject.getString("user_type_name"),
                                jsonObject.getString("user_type_status"),
                                jsonObject.getString("create_info")
                        );
                        userTypeModels.add(adminUserModel);
                    }
                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }
            }

            else if (strType.equals("View Shop Info")){
                try {
                    userTypeModels.clear();
                    JSONArray jsonArray=new JSONArray(result);
                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        AdminUserModel adminUserModel =new AdminUserModel(
                                jsonObject.getString("shop_id"),
                                jsonObject.getString("admin_user_id"),
                                jsonObject.getString("shop_name"),
                                jsonObject.getString("contact_person_name"),
                                jsonObject.getString("contact_person_phone"),
                                jsonObject.getString("contact_person_email"),
                                jsonObject.getString("address"),
                                jsonObject.getString("td_no"),
                                jsonObject.getString("est_date"),
                                jsonObject.getString("shop_status"),
                                jsonObject.getString("create_info"),
                                jsonObject.getString("update_info"),
                                ""
                        );
                        userTypeModels.add(adminUserModel);
                    }
                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }
            }



            return strStatusCode;
        }

        @Override
        protected void onPostExecute(String result) {
            if (pd != null) {
                pd.dismiss();
            }
            if (!result.equals("null")||!result.equals(null)||!result.equals("")){
                if (strType.equals("View Admin Users")) {
                    strOpenType = "Add Admins";
                    adminUserDataViewAdapter = new AdminUserDataViewAdapter(DashBordProscessData.this, adminUserModels, strType);
                    ivDashBordData.setAdapter(adminUserDataViewAdapter);
                }
                else if (strType.equals("View User Type")) {
                    strOpenType = "Add User Type";
                    adminUserDataViewAdapter = new AdminUserDataViewAdapter(DashBordProscessData.this, userTypeModels, strType);
                    ivDashBordData.setAdapter(adminUserDataViewAdapter);
                }
                else if (strType.equals("View Shop Info")) {
                    strOpenType = "Add Shop";
                    adminUserDataViewAdapter = new AdminUserDataViewAdapter(DashBordProscessData.this, userTypeModels, strType);
                    ivDashBordData.setAdapter(adminUserDataViewAdapter);
                }
            }else {
                Toast.makeText(DashBordProscessData.this,"Error", Toast.LENGTH_LONG);
            }

        }
    }


    public void onResume() {
        super.onResume();
        if (strType.equals("View Admin Users")) {
             adminUserModels.clear();
            setTitle(strType);
        }
       else if (strType.equals("View Admin Users")) {
             userTypeModels.clear();
            setTitle(strType);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dash_bord_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menu_Add:
                Bundle bundle = new Bundle();
                bundle.putString("OpenType",strOpenType);
                bundle.putString("ViewType",strViewType);
                DashBordDataAddFragment toletDetailsFragment = new DashBordDataAddFragment();
                toletDetailsFragment.setArguments(bundle);
                toletDetailsFragment.show(getSupportFragmentManager(), toletDetailsFragment.getTag());
                return true;
            case R.id.menu_Search:
                if (show != true) {
                    searchViewAdminHome.setVisibility(View.VISIBLE);
                    show = true;
                }else {
                    searchViewAdminHome.setVisibility(View.GONE);
                    show = false;
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

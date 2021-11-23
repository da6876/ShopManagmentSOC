package com.soc.shopmanagmentsoc.adminpart;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.util.Pair;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.soc.shopmanagmentsoc.R;
import com.soc.shopmanagmentsoc.util.httpd.HttpClientPost;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.soc.shopmanagmentsoc.LoginActivity.rootPath;


public class DashBordDataAddFragment extends BottomSheetDialogFragment {

    String strViewType = "",strViewType1 = "", status_code = "", msg = "", values = "", result = "";

    TextView tv_name;
    EditText edt_User_Type_Name, edt_User_Type_Name_E,
            edt_Name, edt_Phone, edt_Email, edt_Password,edtAddress, edit_Image,
            edt_Name_E, edt_Phone_E, edt_Email_E, edt_Password_E;
    Button btn_Add, btn_add_Image, btn_add_Image_E;
    Spinner spinner_Status, spinner_UserType, spinner_UserType_E;
    ImageView iv_UserImage;
    ImageButton btn_Delete, btn_cancle, btn_Edit;
    LinearLayout ll_UserTypeAdd, ll_UserTypeEdit, ll_AdminUserEdit, ll_AdminUserAdd;

    String strUserTypeName = "",
            strID = "", strName = "", strUserName = "", strPhone = "", strImageUser = "", strEmail = "", strPassword = "",strAddress = "", strImage = "", strUserType = "",


            strStatus = "";

    private static final int GalleryPick = 1;
    Uri imageUri;
    ArrayList<String> usertypeid = new ArrayList<>();
    ArrayList<String> usertypename = new ArrayList<>();

    ArrayList<String> brands_id = new ArrayList<>();
    ArrayList<String> brands_name = new ArrayList<>();

    ArrayList<String> categories_id = new ArrayList<>();
    ArrayList<String> categories_name = new ArrayList<>();

    ArrayList<String> country_id = new ArrayList<>();
    ArrayList<String> country_name = new ArrayList<>();

    ArrayList<String> product_types_id = new ArrayList<>();
    ArrayList<String> product_types_name = new ArrayList<>();
    boolean pick = false, pick1 = false, pick2 = false, pick3 = false, pick4 = false;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dash_bord_data_add_fragment, container, false);

        tv_name = v.findViewById(R.id.tv_name);

        ll_UserTypeAdd = v.findViewById(R.id.ll_UserTypeAdd);
        edt_User_Type_Name = v.findViewById(R.id.edt_User_Type_Name);

        ll_UserTypeEdit = v.findViewById(R.id.ll_UserTypeEdit);
        edt_User_Type_Name_E = v.findViewById(R.id.edt_User_Type_Name_E);



        ll_AdminUserAdd = v.findViewById(R.id.ll_AdminUserAdd);
        edt_Name = v.findViewById(R.id.edt_Name);
        edt_Phone = v.findViewById(R.id.edt_Phone);
        edt_Email = v.findViewById(R.id.edt_Email);
        edt_Password = v.findViewById(R.id.edt_Password);
        edtAddress = v.findViewById(R.id.edtAddress);
        edit_Image = v.findViewById(R.id.edit_Image);
        btn_add_Image = v.findViewById(R.id.btn_add_Image);
        spinner_UserType = v.findViewById(R.id.spinner_UserType);

        ll_AdminUserEdit = v.findViewById(R.id.ll_AdminUserEdit);
        edt_Name_E = v.findViewById(R.id.edt_Name_E);
        edt_Phone_E = v.findViewById(R.id.edt_Phone_E);
        edt_Email_E = v.findViewById(R.id.edt_Email_E);
        edt_Password_E = v.findViewById(R.id.edt_Password_E);
        btn_add_Image_E = v.findViewById(R.id.btn_add_Image_E);
        spinner_UserType_E = v.findViewById(R.id.spinner_UserType_E);
        iv_UserImage = v.findViewById(R.id.iv_UserImage);


        spinner_Status = v.findViewById(R.id.spinner_Status);
        btn_Add = v.findViewById(R.id.btn_Add);

        btn_Edit = v.findViewById(R.id.btn_Edit);
        btn_Delete = v.findViewById(R.id.btn_Delete);
        btn_cancle = v.findViewById(R.id.btn_cancle);

        btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAlertDialog(getActivity(),"Message !","Are You Sure Want To Delete It?");
            }
        });

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            strViewType = bundle.getString("OpenType");
            strViewType1 = bundle.getString("ViewType");

            tv_name.setText(strViewType);

            if (strViewType.equals("Add Admins")) {
                ll_AdminUserAdd.setVisibility(View.VISIBLE);
                GetTypeDataList getTypeDataList = new GetTypeDataList();
                getTypeDataList.execute();
            }

            else if (strViewType.equals("Edit Add Admins")) {
                ll_AdminUserEdit.setVisibility(View.VISIBLE);
                btn_Delete.setVisibility(View.VISIBLE);
                GetTypeDataList getTypeDataList = new GetTypeDataList();
                getTypeDataList.execute();
                strID = bundle.getString("ID");
                strName = bundle.getString("Name");
                strUserType = bundle.getString("UserType");
                strStatus = bundle.getString("AdminStatus");
                strEmail = bundle.getString("Email");
                strPassword = bundle.getString("Password");
                strUserName = bundle.getString("UserName");
                strPhone = bundle.getString("Phone");
                strImageUser = bundle.getString("Image");
                edt_Name_E.setText(strName);
                edt_Email_E.setText(strEmail);
                edt_Password_E.setText(strPassword);
                edt_Phone_E.setText(strPhone);
                try {
                    if (strImageUser.equals("")) {
                        Picasso.get().load("http://103.91.54.60/apps/SOC_71Bazaar/UserImage/dummy.jpg").into(iv_UserImage);
                    } else {
                        Picasso.get().load(rootPath + strImageUser).into(iv_UserImage);
                    }
                } catch (Exception e) {
                    Log.d("Image Load", e.toString());
                }
                spinner_UserType_E.setSelection(Integer.valueOf(strUserType));
                btn_Add.setText("Update Info");

            }

            else if (strViewType.equals("Add User Type")) {
                ll_UserTypeAdd.setVisibility(View.VISIBLE);
            }

            else if (strViewType.equals("Edit Add User Type")) {
                strID = bundle.getString("ID");
                btn_Delete.setVisibility(View.VISIBLE);
                ll_UserTypeEdit.setVisibility(View.VISIBLE);
                strUserName = bundle.getString("Name");
                edt_User_Type_Name_E.setText(strUserName);
                btn_Add.setText("Update Info");
            }
        }

        ArrayAdapter aa = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Status));
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_Status.setAdapter(aa);



        spinner_Status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strStatus = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_UserType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strUserType = usertypeid.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (strViewType.equals("Add Admins")) {
                    strName = edt_Name.getText().toString();
                    strEmail = edt_Email.getText().toString();
                    strPhone = edt_Phone.getText().toString();
                    strPassword = edt_Password.getText().toString();
                    strAddress = edtAddress.getText().toString();

                    if (!strName.equals("")) {
                        if (!strEmail.equals("")) {
                            if (!strPhone.equals("")) {
                                if (!strPassword.equals("")) {
                                    if (!strAddress.equals("")) {
                                        if (!strUserType.equals("0000000")) {
                                            DashBordListData dashBordListData = new DashBordListData();
                                            dashBordListData.execute();
                                        } else {
                                            Toast.makeText(getActivity(), "Select User Type", Toast.LENGTH_LONG).show();
                                        }
                                    } else {
                                        Toast.makeText(getActivity(), "Select Image", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Toast.makeText(getActivity(), "Enter Password", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(getActivity(), "Enter Phone No", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Enter Email", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getActivity(), "Enter Name", Toast.LENGTH_LONG).show();
                    }

                }

                if (strViewType.equals("Edit Add Admins")) {
                    strName = edt_Name.getText().toString();
                    strEmail = edt_Email.getText().toString();
                    strPhone = edt_Phone.getText().toString();
                    strAddress = edtAddress.getText().toString();

                    if (!strName.equals("")) {
                        if (!strEmail.equals("")) {
                            if (!strPhone.equals("")) {
                                if (!strPassword.equals("")) {
                                    if (!strAddress.equals("")) {
                                        if (!strUserType.equals("0000000")) {
                                            DashBordListData dashBordListData = new DashBordListData();
                                            dashBordListData.execute();
                                        } else {
                                            Toast.makeText(getActivity(), "Select User Type", Toast.LENGTH_LONG).show();
                                        }
                                    } else {
                                        Toast.makeText(getActivity(), "Select Address", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Toast.makeText(getActivity(), "Enter Password", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(getActivity(), "Enter Phone No", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Enter Email", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getActivity(), "Enter Name", Toast.LENGTH_LONG).show();
                    }

                } else if (strViewType.equals("Add User Type")) {
                    strUserTypeName = edt_User_Type_Name.getText().toString().trim();

                    if (!strUserTypeName.equals("")) {
                        DashBordListData dashBordListData = new DashBordListData();
                        dashBordListData.execute();
                    }
                }

            }
        });

        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btn_add_Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pick = true;
                pick1 = false;
                pick2 = false;
                pick3 = false;
                pick4 = false;
                openGallryImage();
            }
        });


        return v;
    }


    private class GetTypeDataList extends AsyncTask<Void, Void, String> {
        ProgressDialog pd = null;


        @Override
        protected void onPreExecute() {
            pd = ProgressDialog.show(getActivity(), "Getting Data", "Please wait...");
        }

        @Override
        protected String doInBackground(Void... params) {

            if (strViewType.equals("Add Admins")) {

                List<Pair<String, String>> postParameters = new ArrayList<>();
                postParameters.add(new Pair("Type", "View User Type"));
                try {
                    String response = HttpClientPost.execute(rootPath + "DataProcess.php", postParameters);
                    result = response.toString();
                    result = result.replaceAll("\n", "");

                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }
                try {
                    JSONArray jsonArray = new JSONArray(result);

                    usertypeid.clear();
                    usertypename.clear();
                    usertypeid.add("0000000");
                    usertypename.add("Select User Type");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        usertypeid.add(jsonObject.getString("user_type_id"));
                        usertypename.add(jsonObject.getString("user_type_name"));

                    }
                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

            }

            else if (strViewType.equals("Edit Add Admins")) {

                List<Pair<String, String>> postParameters = new ArrayList<>();
                postParameters.add(new Pair("Type", "View User Type"));
                try {
                    String response = HttpClientPost.execute(rootPath + "DataProsess.php", postParameters);
                    result = response.toString();
                    result = result.replaceAll("\n", "");

                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }
                try {
                    JSONArray jsonArray = new JSONArray(result);

                    usertypeid.clear();
                    usertypename.clear();
                    usertypeid.add("0000000");
                    usertypename.add("Select User Type");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        usertypeid.add(jsonObject.getString("user_type_id"));
                        usertypename.add(jsonObject.getString("user_type_name"));

                    }
                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

            }

            else if (strViewType.equals("Add Products")) {
                String result1 = "", result2 = "", result3 = "", result4 = "";

                List<Pair<String, String>> postParameters1 = new ArrayList<>();
                postParameters1.add(new Pair("Type", "View Product Types"));

                try {
                    String response = HttpClientPost.execute(rootPath + "DataProsess.php", postParameters1);
                    result1 = response.toString();
                    result1 = result1.replaceAll("\n", "");

                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

                try {
                    JSONArray jsonArray = new JSONArray(result1);

                    product_types_id.clear();
                    product_types_name.clear();
                    product_types_id.add("0000000");
                    product_types_name.add("Select Product Types");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        product_types_id.add(jsonObject.getString("product_types_id"));
                        product_types_name.add(jsonObject.getString("product_types_name"));

                    }
                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

                List<Pair<String, String>> postParameters2 = new ArrayList<>();
                postParameters2.add(new Pair("Type", "View Brands"));

                try {
                    String response = HttpClientPost.execute(rootPath + "DataProsess.php", postParameters2);
                    result2 = response.toString();
                    result2 = result2.replaceAll("\n", "");

                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

                try {
                    JSONArray jsonArray = new JSONArray(result2);

                    brands_id.clear();
                    brands_name.clear();
                    brands_id.add("0000000");
                    brands_name.add("Select Brands");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        brands_id.add(jsonObject.getString("brands_id"));
                        brands_name.add(jsonObject.getString("brands_name"));

                    }
                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

                List<Pair<String, String>> postParameters3 = new ArrayList<>();
                postParameters3.add(new Pair("Type", "View Categories"));

                try {
                    String response = HttpClientPost.execute(rootPath + "DataProsess.php", postParameters3);
                    result3 = response.toString();
                    result3 = result3.replaceAll("\n", "");

                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

                try {
                    JSONArray jsonArray = new JSONArray(result3);

                    categories_id.clear();
                    categories_name.clear();
                    categories_id.add("0000000");
                    categories_name.add("Select Categories");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        categories_id.add(jsonObject.getString("categories_id"));
                        categories_name.add(jsonObject.getString("categories_name"));

                    }
                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

                List<Pair<String, String>> postParameters4 = new ArrayList<>();
                postParameters4.add(new Pair("Type", "View Country"));

                try {
                    String response = HttpClientPost.execute(rootPath + "DataProsess.php", postParameters4);
                    result4 = response.toString();
                    result4 = result4.replaceAll("\n", "");

                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

                try {
                    JSONArray jsonArray = new JSONArray(result4);

                    country_id.clear();
                    country_name.clear();
                    country_id.add("0000000");
                    country_name.add("Select Country");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        country_id.add(jsonObject.getString("id"));
                        country_name.add(jsonObject.getString("name"));

                    }
                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

            }

            else if (strViewType.equals("Edit Add Products")) {
                String result1 = "", result2 = "", result3 = "", result4 = "";

                List<Pair<String, String>> postParameters1 = new ArrayList<>();
                postParameters1.add(new Pair("Type", "View Product Types"));

                try {
                    String response = HttpClientPost.execute(rootPath + "DataProsess.php", postParameters1);
                    result1 = response.toString();
                    result1 = result1.replaceAll("\n", "");

                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

                try {
                    JSONArray jsonArray = new JSONArray(result1);

                    product_types_id.clear();
                    product_types_name.clear();
                    product_types_id.add("0000000");
                    product_types_name.add("Select Product Types");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        product_types_id.add(jsonObject.getString("product_types_id"));
                        product_types_name.add(jsonObject.getString("product_types_name"));

                    }
                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

                List<Pair<String, String>> postParameters2 = new ArrayList<>();
                postParameters2.add(new Pair("Type", "View Brands"));

                try {
                    String response = HttpClientPost.execute(rootPath + "DataProsess.php", postParameters2);
                    result2 = response.toString();
                    result2 = result2.replaceAll("\n", "");

                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

                try {
                    JSONArray jsonArray = new JSONArray(result2);

                    brands_id.clear();
                    brands_name.clear();
                    brands_id.add("0000000");
                    brands_name.add("Select Brands");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        brands_id.add(jsonObject.getString("brands_id"));
                        brands_name.add(jsonObject.getString("brands_name"));

                    }
                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

                List<Pair<String, String>> postParameters3 = new ArrayList<>();
                postParameters3.add(new Pair("Type", "View Categories"));

                try {
                    String response = HttpClientPost.execute(rootPath + "DataProsess.php", postParameters3);
                    result3 = response.toString();
                    result3 = result3.replaceAll("\n", "");

                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

                try {
                    JSONArray jsonArray = new JSONArray(result3);

                    categories_id.clear();
                    categories_name.clear();
                    categories_id.add("0000000");
                    categories_name.add("Select Categories");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        categories_id.add(jsonObject.getString("categories_id"));
                        categories_name.add(jsonObject.getString("categories_name"));

                    }
                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

                List<Pair<String, String>> postParameters4 = new ArrayList<>();
                postParameters4.add(new Pair("Type", "View Country"));

                try {
                    String response = HttpClientPost.execute(rootPath + "DataProsess.php", postParameters4);
                    result4 = response.toString();
                    result4 = result4.replaceAll("\n", "");

                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

                try {
                    JSONArray jsonArray = new JSONArray(result4);

                    country_id.clear();
                    country_name.clear();
                    country_id.add("0000000");
                    country_name.add("Select Country");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        country_id.add(jsonObject.getString("id"));
                        country_name.add(jsonObject.getString("name"));

                    }
                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

            }


            return status_code;
        }

        @Override
        protected void onPostExecute(String result) {
            if (pd != null) {
                pd.dismiss();
            }
            if (!result.equals("null") || !result.equals(null) || !result.equals("")) {
                if (strViewType.equals("Add Admins")) {
                    ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, usertypename);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_UserType.setAdapter(arrayAdapter);
                } else if (strViewType.equals("Edit Add Admins")) {
                    ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, usertypename);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_UserType_E.setAdapter(arrayAdapter);
                }
            } else {
                alertDialog(getActivity(), msg, values);
            }

        }
    }

    private class DashBordListData extends AsyncTask<Void, Void, String> {
        ProgressDialog pd = null;

        @Override
        protected void onPreExecute() {
            pd = ProgressDialog.show(getActivity(), "Sending Data", "Please wait...");
        }

        @Override
        protected String doInBackground(Void... params) {

            if (strViewType.equals("Add User Type")) {
                List<Pair<String, String>> postParameters = new ArrayList<>();
                postParameters.add(new Pair("Type", strViewType));
                postParameters.add(new Pair("user_type_name", strUserTypeName));
                postParameters.add(new Pair("user_type_status", strStatus));
                try {
                    String response = HttpClientPost.execute(rootPath + "DataProcess.php", postParameters);
                    result = response.toString();
                    result = result.replaceAll("\n", "");

                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }
            }

            else if (strViewType.equals("Add Admins")) {

                List<Pair<String, String>> postParameters = new ArrayList<>();
                postParameters.add(new Pair("Type", strViewType));
                postParameters.add(new Pair("admin_user_name", strPhone));
                postParameters.add(new Pair("password", strPassword));
                postParameters.add(new Pair("full_name", strName));
                postParameters.add(new Pair("mobile_no", strPhone));
                postParameters.add(new Pair("email_address", strEmail));
                postParameters.add(new Pair("address", strAddress));
                postParameters.add(new Pair("latitude", "0.0"));
                postParameters.add(new Pair("longitude", "0.0"));
                postParameters.add(new Pair("admin_user_status", strStatus));
                postParameters.add(new Pair("user_type_id", strUserType));
                try {
                    String response = HttpClientPost.execute(rootPath + "DataProcess.php", postParameters);
                    result = response.toString();
                    result = result.replaceAll("\n", "");

                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

            }

            else if (strViewType.equals("Delete User Type")) {

                List<Pair<String, String>> postParameters = new ArrayList<>();
                postParameters.add(new Pair("Type", strViewType));
                postParameters.add(new Pair("ID", strID));
                try {
                    String response = HttpClientPost.execute(rootPath + "DataProcess.php", postParameters);
                    result = response.toString();
                    result = result.replaceAll("\n", "");

                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

            }

            else if (strViewType.equals("Delete Admins")) {

                List<Pair<String, String>> postParameters = new ArrayList<>();
                postParameters.add(new Pair("Type", strViewType));
                postParameters.add(new Pair("ID", strID));
                try {
                    String response = HttpClientPost.execute(rootPath + "DataProcess.php", postParameters);
                    result = response.toString();
                    result = result.replaceAll("\n", "");

                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

            }

            try {

                JSONObject obj = new JSONObject(result);
                status_code = obj.getString("status_code");
                msg = obj.getString("msg");
                values = obj.getString("values");


            } catch (Exception e) {
                Log.e("log_tag", "Error in http connection!!" + e.toString());
            }

            return status_code;
        }

        @Override
        protected void onPostExecute(String result) {
            if (pd != null) {
                pd.dismiss();
            }
            if (status_code.equals("200")) {
                alertDialog1( msg, values);
            } else {
                alertDialog(getActivity(), msg, values);
            }

        }
    }

    private void openGallryImage() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GalleryPick);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == GalleryPick && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();

            File filePath = new File(getRealPathFromURI(imageUri));
            String fileName = filePath.getName();
            String extension = filePath.getAbsolutePath().substring(filePath.getAbsolutePath().lastIndexOf(".") + 1);

            /*if (pick1) {
                edit_Image1.setText(fileName);
                try {
                    InputStream imageStream = getActivity().getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    strImage1 = encodeImage(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            } else if (pick2) {
                edit_Image2.setText(fileName);
                try {
                    InputStream imageStream = getActivity().getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    strImage2 = encodeImage(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (pick3) {
                edit_Image3.setText(fileName);
                try {
                    InputStream imageStream = getActivity().getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    strImage3 = encodeImage(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (pick4) {
                edit_Image4.setText(fileName);
                try {
                    InputStream imageStream = getActivity().getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    strImage4 = encodeImage(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else*/ if (pick) {
                edit_Image.setText(fileName);
                try {
                    InputStream imageStream = getActivity().getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    strImage = encodeImage(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                alertDialog(getActivity(), "Error", "No Picture Is Selected");
            }

        }

    }

    private String getRealPathFromURI(Uri contentURI) {
        String result = "";
        Cursor cursor = getActivity().getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    private String encodeImage(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encImage;
    }

    public void alertDialog(android.content.Context context, String title, String message) {
        new AlertDialog.Builder(context).setTitle(title).setMessage(message).setCancelable(false)
                .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int arg1) {
                        dialog.dismiss();
                    }
                }).show();
    }

    public void alertDialog1(String title, String message) {
        new AlertDialog.Builder(getActivity()).setTitle(title).setMessage(message).setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int arg1) {

                        Intent intent = new Intent(getActivity(),DashBordProscessData.class);
                        intent.putExtra("Name",strViewType1);
                        getActivity().startActivity(intent);
                        getActivity().finish();
                        dialog.dismiss();
                        dismiss();
                    }
                }).show();
    }

    public void deleteAlertDialog(android.content.Context context, String title, String message) {
        new AlertDialog.Builder(context).setTitle(title).setMessage(message).setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int arg1) {
                        if (strViewType.equals("Edit Add Admins")) {
                            strViewType = "Delect Admins";
                            DashBordListData dashBordListData = new DashBordListData();
                            dashBordListData.execute();
                        } else if (strViewType.equals("Edit Add Brands")) {
                            strViewType = "Delete Admins";
                            DashBordListData dashBordListData = new DashBordListData();
                            dashBordListData.execute();
                        } else if (strViewType.equals("Edit Add Categories")) {
                            strViewType = "Delete Categories";
                            DashBordListData dashBordListData = new DashBordListData();
                            dashBordListData.execute();
                        } else if (strViewType.equals("Edit Add Brands")) {
                            strViewType = "Delete Brands";
                            DashBordListData dashBordListData = new DashBordListData();
                            dashBordListData.execute();
                        } else if (strViewType.equals("Edit Add Product Types")) {
                            strViewType = "Delete Product Types";
                            DashBordListData dashBordListData = new DashBordListData();
                            dashBordListData.execute();
                        } else if (strViewType.equals("Edit Add User Type")) {
                            strViewType = "Delete User Type";
                            DashBordListData dashBordListData = new DashBordListData();
                            dashBordListData.execute();
                        } else if (strViewType.equals("Edit Add Products")) {
                            strViewType = "Delete Products";
                            DashBordListData dashBordListData = new DashBordListData();
                            dashBordListData.execute();
                        }
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
    }

    private boolean isInternetAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getActivity()
                .getSystemService(android.content.Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        }
        return false;
    }

}

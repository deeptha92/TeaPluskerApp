package com.example.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.Helper.DatabaseHelperClass;
import com.example.Modal.EmployeeModalClass;
import com.example.Modal.SupplierModalClass;
import com.example.TeaPlucker.MainActivity;
import com.example.TeaPlucker.R;
import com.example.TeaPlucker.SupplierFormActivity;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EmployeeAdapterClass extends RecyclerView.Adapter<EmployeeAdapterClass.ViewHolder> {

    List<EmployeeModalClass> employee;
    Context context;
    DatabaseHelperClass databaseHelperClass;
    SupplierModalClass supplierModalClass;
    String greentea_qua;
    String greentea_up;
    String greentea_pr;
    String additional_qua;
    String additional_up;
    String additional_pr;
    String cash_qua;
    String cash_up;
    String cash_pr;
    String welfare_qua;
    String welfare_up;
    String welfare_pr;
    String mt_qua;
    String mt_up;
    String mt_pr;
    String manure_qua;
    String manure_up;
    String manure_pr;
    String transport_qua;
    String transport_up;
    String transport_pr;
    String kok_qua;
    String kok_up;
    String kok_pr;
    String other_qua;
    String other_up;
    String other_pr;

    String total_earning;
    String total_deduction;
    String total_sum;

    String supplier_name;
    String supplier_id;


    public EmployeeAdapterClass(List<EmployeeModalClass> employee, Context context) {
        this.employee = employee;
        this.context = context;
        databaseHelperClass = new DatabaseHelperClass(context);
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.employee_item_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull EmployeeAdapterClass.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final EmployeeModalClass employeeModalClass = employee.get(position);
//        holder.textViewId.setText(Integer.toString(employeeModalClass.getId()));
//        holder.editTextName.setText(employeeModalClass.getName());
        holder.editTextName.setText(employeeModalClass.getSupplier_name());


        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelperClass.deleteSupplier(employeeModalClass.getSupplier_name());
                employee.remove(position);
                notifyDataSetChanged();
                Intent myIntent = new Intent(context, MainActivity.class);
                context.startActivity(myIntent);

            }
        });

        holder.buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                greentea_qua = employeeModalClass.getGreentea_qua();
                greentea_up = employeeModalClass.getGreentea_up();
                greentea_pr = employeeModalClass.getGreentea_pr();
                additional_qua = employeeModalClass.getAdditional_qua();
                additional_up = employeeModalClass.getAdditional_up();
                additional_pr = employeeModalClass.getAdditional_pr();
                cash_qua = employeeModalClass.getCash_qua();
                cash_up = employeeModalClass.getCash_up();
                cash_pr = employeeModalClass.getCash_pr();
                welfare_qua = employeeModalClass.getWelfare_qua();
                welfare_up = employeeModalClass.getWelfare_up();
                welfare_pr = employeeModalClass.getWelfare_pr();
                mt_qua = employeeModalClass.getMt_qua();
                mt_up = employeeModalClass.getMt_up();
                mt_pr = employeeModalClass.getMt_pr();
                manure_qua = employeeModalClass.getManure_qua();
                manure_up = employeeModalClass.getManure_up();
                manure_pr = employeeModalClass.getManure_pr();
                transport_qua = employeeModalClass.getTransport_qua();
                transport_up = employeeModalClass.getTransport_up();
                transport_pr = employeeModalClass.getTransport_pr();
                kok_qua = employeeModalClass.getKok_qua();
                kok_up = employeeModalClass.getKok_up();
                kok_pr = employeeModalClass.getKok_pr();
                other_qua = employeeModalClass.getOther_qua();
                other_up = employeeModalClass.getOther_up();
                other_pr = employeeModalClass.getOther_pr();
                total_earning = employeeModalClass.getTotal_earning();
                total_deduction = employeeModalClass.getTotal_deduction();
                total_sum = employeeModalClass.getTotal_sum();
                supplier_name = employeeModalClass.getSupplier_name();
                supplier_id = employeeModalClass.getSupplier_id();

//                PopUpClass popUpClass = new PopUpClass();
//                popUpClass.showPopupWindow(v);
//                PopUpClass.greentea_qua.setText(greentea_qua);
//                PopUpClass.greentea_up.setText(greentea_up);
//                PopUpClass.greentea_pr.setText(greentea_pr);
//                PopUpClass.additional_qua.setText(additional_qua);
//                PopUpClass.additional_up.setText(additional_up);
//                PopUpClass.additional_pr.setText(additional_pr);
//                PopUpClass.cash_qua.setText(cash_qua);
//                PopUpClass.cash_up.setText(cash_up);
//                PopUpClass.cash_pr.setText(cash_pr);
//                PopUpClass.welfare_qua.setText(welfare_qua);
//                PopUpClass.welfare_up.setText(welfare_up);
//                PopUpClass.welfare_pr.setText(welfare_pr);
//                PopUpClass.mt_qua.setText(mt_qua);
//                PopUpClass.mt_up.setText(mt_up);
//                PopUpClass.mt_pr.setText(mt_pr);
//                PopUpClass.manure_qua.setText(manure_qua);
//                PopUpClass.manure_up.setText(manure_up);
//                PopUpClass.manure_pr.setText(manure_pr);
//                PopUpClass.transport_qua.setText(transport_qua);
//                PopUpClass.transport_up.setText(transport_up);
//                PopUpClass.transport_pr.setText(transport_pr);
//                PopUpClass.kok_qua.setText(kok_qua);
//                PopUpClass.kok_up.setText(kok_up);
//                PopUpClass.kok_pr.setText(kok_pr);
//                PopUpClass.other_qua.setText(other_qua);
//                PopUpClass.other_up.setText(other_up);
//                PopUpClass.other_pr.setText(other_pr);

                Intent myIntent = new Intent(context, SupplierFormActivity.class);
                Gson gson = new Gson();
                String json = gson.toJson(employeeModalClass);

// Save the JSON string to SharedPreferences
                SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("myModel", json);
                editor.apply();
                context.startActivity(myIntent);




            }
        });
    }

    @Override
    public int getItemCount() {
        return employee.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewId;
        EditText editTextName;
        TextView editTextData;
        Button buttonEdit;
        Button buttonDelete;
        Button buttonView;


        public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            editTextName = itemView.findViewById(R.id.name);
            buttonDelete = itemView.findViewById(R.id.button_delete);
            buttonView = itemView.findViewById(R.id.button_view);
        }
    }
}

package com.example.TeaPlucker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EmployeeAdapterClass extends RecyclerView.Adapter<EmployeeAdapterClass.ViewHolder>{

    List<EmployeeModalClass> employee ;
    Context context;
    DatabaseHelperClass databaseHelperClass;
    SupplierModalClass supplierModalClass;

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


        public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            editTextName = itemView.findViewById(R.id.name);
            buttonDelete = itemView.findViewById(R.id.button_delete);
        }
    }
}

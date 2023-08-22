package com.example.Popups;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.TeaPlucker.R;

public class PopUpClass {
    //PopupWindow display method
    public static TextView greentea_qua, greentea_up, greentea_pr, additional_qua, additional_up, additional_pr,
            cash_qua, cash_up, cash_pr, welfare_qua, welfare_up, welfare_pr, mt_qua, mt_up, mt_pr,
            manure_qua, manure_up, manure_pr, transport_qua, transport_up, transport_pr, kok_qua, kok_up, kok_pr,
            other_qua, other_up, other_pr;
    public Button BtnCloe;

    public void showPopupWindow(final View view) {


        //Create a View object yourself through inflater
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.pop_up_layout, null);

        //Specify the length and width through constants
        int width = 550;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;

        //Make Inactive Items Outside Of PopupWindow
        boolean focusable = false;

        //Create a window with our parameters
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        //Set the location of the window on the screen
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        //Initialize the elements of our window, install the handler

//        TextView test2 = popupView.findViewById(R.id.titleText);
        greentea_qua = popupView.findViewById(R.id.greentea_qua);
        greentea_up = popupView.findViewById(R.id.greentea_up);
        greentea_pr = popupView.findViewById(R.id.greentea_pr);
        additional_qua = popupView.findViewById(R.id.additional_qua);
        additional_up = popupView.findViewById(R.id.additional_up);
        additional_pr = popupView.findViewById(R.id.additional_pr);
        cash_qua = popupView.findViewById(R.id.cash_qua);
        cash_up = popupView.findViewById(R.id.cash_up);
        cash_pr = popupView.findViewById(R.id.cash_pr);
        welfare_qua = popupView.findViewById(R.id.welfare_qua);
        welfare_up = popupView.findViewById(R.id.welfare_up);
        welfare_pr = popupView.findViewById(R.id.welfare_pr);
        mt_qua = popupView.findViewById(R.id.mt_qua);
        mt_up = popupView.findViewById(R.id.mt_up);
        mt_pr = popupView.findViewById(R.id.mt_pr);
        manure_qua = popupView.findViewById(R.id.manure_qua);
        manure_up = popupView.findViewById(R.id.manure_up);
        manure_pr = popupView.findViewById(R.id.manure_pr);
        transport_qua = popupView.findViewById(R.id.transport_qua);
        transport_up = popupView.findViewById(R.id.transport_up);
        transport_pr = popupView.findViewById(R.id.transport_pr);
        kok_qua = popupView.findViewById(R.id.kok_qua);
        kok_up = popupView.findViewById(R.id.kok_up);
        kok_pr = popupView.findViewById(R.id.kok_pr);
        other_qua = popupView.findViewById(R.id.other_qua);
        other_up = popupView.findViewById(R.id.other_up);
        other_pr = popupView.findViewById(R.id.other_pr);

        BtnCloe = popupView.findViewById(R.id.BtnClose);

        //Handler for clicking on the inactive zone of the window

        BtnCloe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });


    }

}

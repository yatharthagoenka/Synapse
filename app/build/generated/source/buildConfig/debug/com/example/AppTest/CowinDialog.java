package com.example.AppTest;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class CowinDialog extends AppCompatDialogFragment {
    private EditText pin,date;
    private DialogListener listener;

    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity(),R.style.CustomAlertDialog);
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.cowin_dialog,null);
        builder.setView(view).setTitle("Information")
                .setPositiveButton("Find", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String dis=pin.getText().toString();
                        String dt=date.getText().toString();
                        listener.sharetext2(dis,dt);
                    }
                });
        pin=(EditText)view.findViewById(R.id.pin);
        date=(EditText)view.findViewById(R.id.date);
        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();
        Button positive=((AlertDialog) getDialog()).getButton(AlertDialog.BUTTON_POSITIVE);
        positive.setTextColor(Color.BLACK);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener=(DialogListener) context;
        } catch (Exception e) {
            throw new ClassCastException(context.toString()+"Must implement Dialog Listener");
        }
    }


    public interface DialogListener{
        void sharetext2(String id,String date);
    }
}

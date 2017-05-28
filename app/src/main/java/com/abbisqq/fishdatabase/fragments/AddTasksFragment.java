package com.abbisqq.fishdatabase.fragments;


import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.abbisqq.fishdatabase.R;
import com.abbisqq.fishdatabase.data.FishDatabaseContract;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddTasksFragment extends Fragment implements View.OnClickListener{
    EditText name, size,review,fishurl;

    ImageButton okButton;

    public AddTasksFragment() {
        // Required empty public constructor
    }


    public static AddTasksFragment newInstance() {
        return new AddTasksFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);

        name = (EditText) view.findViewById(R.id.title_edit_text);
        size = (EditText) view.findViewById(R.id.info_edit_text);
        review = (EditText)view.findViewById(R.id.review_edit_text);
        fishurl = (EditText) view.findViewById(R.id.imageurl_edit_text);
        okButton = (ImageButton)view.findViewById(R.id.ok_button);
        okButton.setOnClickListener(this);

        return view;
    }


    public int hasText() {
        if (name.length() > 0 && size.length() > 0&& review.length() > 0) {
            return 1;
        }
        return 0;
    }

    @Override
    public void onClick(View v) {

        String fishName = name.getText().toString();
        String fishSize = size.getText().toString();
        String fishReview = review.getText().toString();
        String imageurl = fishurl.getText().toString();
        if (fishName.length() == 0) {
            return;
        }


        ContentValues contentValues = new ContentValues();
        // Put the task description and selected mPriority into the ContentValues
        contentValues.put(FishDatabaseContract.TasksEntry.NAME, fishName);
        contentValues.put(FishDatabaseContract.TasksEntry.SIZE, fishSize);
        contentValues.put(FishDatabaseContract.TasksEntry.REVIEW, fishReview);
        contentValues.put(FishDatabaseContract.TasksEntry.IMAGE, imageurl);


        // Insert the content values via a ContentResolver
        Uri uri = getActivity().getContentResolver().insert(FishDatabaseContract.TasksEntry.CONTENT_URI, contentValues);


        if(uri != null) {
            Toast.makeText(getContext(), uri.toString(), Toast.LENGTH_LONG).show();
        }

        // Finish activity (this returns back to MainActivity)

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new StartingFragment())
                .commit();
    }
}
package com.example.alphanetwork.Profile;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.core.widget.TextViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.alphanetwork.R;
import com.example.alphanetwork.Retrofit.RetrofitClient;
import com.example.alphanetwork.addpost.gallery;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by User on 6/4/2017.
 */

public class EditProfileFragment extends Fragment {


    private static final String TAG = "EditProfileFragment";



    //EditProfile Fragment widgets
    private EditText mDisplayName, mUsername, mPhone, mEmail;
    private TextView mChangeProfilePhoto;
    private CircleImageView mProfilePhoto;
    public static List<String> urls = new ArrayList<>();


    //vars
//    private UserSettings mUserSettings;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_editprofile, container, false);
        mProfilePhoto = (CircleImageView) view.findViewById(R.id.profile_photo);
        mPhone = view.findViewById(R.id.phoneNumber);
        mEmail = view.findViewById(R.id.email);
        mUsername = (EditText) view.findViewById(R.id.username);
        mChangeProfilePhoto = (TextView) view.findViewById(R.id.changeProfilePhoto);
        mChangeProfilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), gallery.class);
                i.putExtra(getString(R.string.calling_activity), getString(R.string.profile_activity));
                getActivity().startActivity(i);
            }
        });


        
        //back arrow for navigating back to "ProfileActivity"
        ImageView backArrow = (ImageView) view.findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: navigating back to ProfileActivity");
                getActivity().finish();
            }
        });

        ImageView checkmark = (ImageView) view.findViewById(R.id.saveChanges);
        checkmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: attempting to save changes.");
                try {
                    saveProfileSettings();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        return view;
    }





    private void saveProfileSettings() throws IOException {

        final String user = mUsername.getText().toString();
        final String mail = mEmail.getText().toString();
        final String phoneNumber = mPhone.getText().toString();





//pass it like this

        RequestBody username =
                RequestBody.create(MediaType.parse("multipart/form-data"), user);
        RequestBody phone =
                RequestBody.create(MediaType.parse("multipart/form-data"), phoneNumber);
        RequestBody email =
                RequestBody.create(MediaType.parse("multipart/form-data"), mail);

        if (urls.size() != 0) {


                System.out.println("The urls are :" + urls);

                File file = new File(urls.get(0));

//                RequestBody requestFile =
//                        RequestBody.create(MediaType.parse("multipart/form-data"), file);

                RequestBody requestFile =
                        RequestBody.create(MediaType.parse("multipart/form-data"),new Compressor(getActivity()).compressToFile(file));

// MultipartBody.Part is used to send also the actual file name
                MultipartBody.Part body = MultipartBody.Part.createFormData("photo", file.getName(), requestFile);
            Call<ResponseBody> call = RetrofitClient
                    .getInstance()
                    .getApi()
                    .updateProfile(body,username,phone,email);
            call.enqueue(new Callback<ResponseBody>() {

                @Override
                public void onResponse(Call<ResponseBody> call,
                                       Response<ResponseBody> response) {
                    String m = response.message();
                    System.out.println(m);

                    Log.v("Upload", "success");
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.e("Upload error:", t.getMessage());
                }
            });

        }
        else{
            Call<ResponseBody> call = RetrofitClient
                    .getInstance()
                    .getApi()
                    .updateProfileWithoutPic(username,phone,email);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    String m = response.message();
                    System.out.println(m);

                    Log.v("Upload", "success");
                }


                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.e("Upload error:", t.getMessage());
                }
            });

        }



    }



    /**
     * Check is @param username already exists in teh database
     * @param username
     */
//    private void checkIfUsernameExists(final String username) {
//        Log.d(TAG, "checkIfUsernameExists: Checking if  " + username + " already exists.");
//
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
//        Query query = reference
//                .child(getString(R.string.dbname_users))
//                .orderByChild(getString(R.string.field_username))
//                .equalTo(username);
//        query.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                if(!dataSnapshot.exists()){
//                    //add the username
//                    mFirebaseMethods.updateUsername(username);
//                    Toast.makeText(getActivity(), "saved username.", Toast.LENGTH_SHORT).show();
//
//                }
//                for(DataSnapshot singleSnapshot: dataSnapshot.getChildren()){
//                    if (singleSnapshot.exists()){
//                        Log.d(TAG, "checkIfUsernameExists: FOUND A MATCH: " + singleSnapshot.getValue(User.class).getUsername());
//                        Toast.makeText(getActivity(), "That username already exists.", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }

//    private void setProfileWidgets(UserSettings userSettings){
//
//
//        mUserSettings = userSettings;
//        User user = userSettings.getUser();
//        UserAccountSettings settings = userSettings.getSettings();
//        UniversalImageLoader.setImage(settings.getProfile_photo(), mProfilePhoto, null, "");
//        mDisplayName.setText(settings.getDisplay_name());
//        mUsername.setText(settings.getUsername());
//        mWebsite.setText(settings.getWebsite());
//        mDescription.setText(settings.getDescription());
//        mEmail.setText(userSettings.getUser().getEmail());
//        mPhoneNumber.setText(String.valueOf(userSettings.getUser().getPhone_number()));
//
//        mChangeProfilePhoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG, "onClick: changing profile photo");
//                Intent intent = new Intent(getActivity(), ShareActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //268435456
//                getActivity().startActivity(intent);
//                getActivity().finish();
//            }
//        });
//    }
//



}
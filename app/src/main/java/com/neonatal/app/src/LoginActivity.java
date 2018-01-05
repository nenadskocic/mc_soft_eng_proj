package com.neonatal.app.src;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.neonatal.app.src.database.AppDatabase;
import com.neonatal.app.src.entity.User;

public class LoginActivity extends AppCompatActivity {
    NeonatalApp app;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        app = (NeonatalApp) getApplicationContext();
        db = AppDatabase.getAppDatabase(getApplicationContext());

        populateWithTestData(db);
    }

    public void Register(View v){
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        this.finish();
    }

    private static User getUserByUsername(AppDatabase db, String username) {
        return db.userDAO().getUserByUserName(username);
    }

    private static User addUser(final AppDatabase db, User user) {
        db.userDAO().insertAll(user);
        return user;
    }

    private static void populateWithTestData(AppDatabase db) {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
        user.setPersonId(1);
        addUser(db, user);
    }

    public void Login(View v){
        TextView textView_username =  (TextView) findViewById(R.id.txt_Username);
        TextView textView_password =  (TextView) findViewById(R.id.txt_password);

        String inputUsername = textView_username.getText().toString();
        String inputPassword = textView_password.getText().toString();

        db = AppDatabase.getAppDatabase(getApplicationContext());

        User user = getUserByUsername(db, inputUsername);

        if(inputUsername.equals(user.getUsername()) && inputPassword.equals(user.getPassword()))
        {
            app.setCurrentUser(user.getId());
            startActivity(new Intent(LoginActivity.this, MainMenuActivity.class));
            this.finish();
        }
        else
        {
            Context context = getApplicationContext();
            CharSequence text = "Wrong username/password";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
}

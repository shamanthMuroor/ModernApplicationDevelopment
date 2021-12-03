package www.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText uname, pswd;
    Button login, register;
    DbHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uname = findViewById(R.id.editText1);
        pswd = findViewById(R.id.editText2);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);

        db = new DbHandler(MainActivity.this);

        uname.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                register.setEnabled(true);
                return false;
            }
        });

        pswd.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                register.setEnabled(true);
                return false;
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = uname.getText().toString();
                String password = pswd.getText().toString();
                int id = checkUser(new User(name, password));
                if(id==-1) {
                    Toast.makeText(MainActivity.this, "User doesn't exist, Please register first", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, SecondActivity.class);
                    i.putExtra("msg", name);
                    startActivity(i);
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = uname.getText().toString();
                String password = pswd.getText().toString();
                int id = checkUser(new User(name, password));
                if(id==-1) {
                    db.addUser(new User(name, password));
                    Toast.makeText(MainActivity.this, "User Registered successfully, Please Login", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "User already exists, please login", Toast.LENGTH_SHORT).show();
                    register.setEnabled(false);
                }
            }
        });

//        db = new DbHandler(MainActivity.this);
//        db.addUser(new User("MS", "324"));
    }
    public int checkUser(User user) {
        return db.checkUser(user);
    }
}
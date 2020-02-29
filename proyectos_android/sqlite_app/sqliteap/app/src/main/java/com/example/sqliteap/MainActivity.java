package com.example.sqliteap;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editName,editTelefono,editOrigen,editDestino, editTextId;
    Button btnAdd;
    Button btnView;
    Button btnUpdate;
    Button btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        editName = (EditText)findViewById(R.id.editNombre);
        editTelefono = (EditText)findViewById(R.id.editTelefono);
        editOrigen = (EditText)findViewById(R.id.editOrigen);
        editDestino = (EditText)findViewById(R.id.editDestino);
        editTextId = (EditText)findViewById(R.id.editId);
        btnAdd = (Button)findViewById(R.id.button_add);
        btnView = (Button)findViewById(R.id.button_view);
        btnUpdate = (Button)findViewById(R.id.button_update);
        btnDelete = (Button)findViewById(R.id.button_delete);

        AddData();
        viewAll();
        UpdateData();
        DeleteData();

    }

    public void DeleteData(){
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Integer deletedRows = myDb.deleteData(editTextId.getText().toString());
                        if (deletedRows > 0)

                            Toast.makeText(MainActivity.this,"Datos eliminados",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Datos no eliminados",Toast.LENGTH_LONG).show();


                    }
                }
        );
    }

    public void UpdateData(){
        btnUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isUpdate = myDb.updateData(editTextId.getText().toString(),editName.getText().toString(),editTelefono.getText().toString(),
                                editOrigen.getText().toString(),editDestino.getText().toString());

                        if(isUpdate == true)

                            Toast.makeText(MainActivity.this,"Datos modificados",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Datos no modificados",Toast.LENGTH_LONG).show();


                    }
                }
        );
    }

    public void AddData(){

        btnAdd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isInserted = myDb.insertData(editName.getText().toString(),
                                editTelefono.getText().toString(),
                                editOrigen.getText().toString(),
                                editDestino.getText().toString() );

                        if(isInserted == true)
                            Toast.makeText(MainActivity.this,"Datos insertados",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Datos no insertados",Toast.LENGTH_LONG).show();

                    }
                }
        );

    }

    public void viewAll() {
        btnView.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Cursor res = myDb.getAllData();
                        if(res.getCount() ==0) {
                            //show
                            showMessages("Error","Busqueda fallida");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while(res.moveToNext()){
                            buffer.append("Id :"+ res.getString(0)+"\n");
                            buffer.append("Nombre: "+ res.getString(1)+"\n");
                            buffer.append("Origen: "+ res.getString(2)+"\n");
                            buffer.append("Destino: "+ res.getString(3)+"\n");
                            buffer.append("Extras: "+ res.getString(4)+"\n\n");
                        }

                        //Show all data
                        showMessages("Listado de servicios", buffer.toString());
                    }
                }
        );
    }

    public void showMessages(String title,String Message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }

}

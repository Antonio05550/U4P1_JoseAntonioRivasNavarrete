package mx.edu.ittepic.u4p1_joseantoniorivasnavarrete;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    BD base;
    EditText id, nombre, ingredientes, preparacion,observacion;
    Button agregar,consultar,actualizar,eliminar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        id= findViewById(R.id.id);
        nombre = findViewById( R.id.nombre );
        ingredientes = findViewById( R.id.ingredientes );
        preparacion=findViewById( R.id.preparacion );
        observacion=findViewById( R.id.observacion );

        agregar = findViewById( R.id.agregar );
        consultar=findViewById(R.id.consultar);
        actualizar=findViewById(R.id.actualizar);
        eliminar=findViewById(R.id.eliminar);

        base = new BD( this,"primera",null,1 );
        agregar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               insertar();
            }
        } );

    }

    private void insertar(){
        try{
            SQLiteDatabase tabla = base.getWritableDatabase();
            String SQL = "INSERT INTO RECETA VALUES("+id.getText().toString()+ ",'"+nombre.getText().toString()
                    +"','"+ingredientes.getText().toString()+"','"+preparacion.getText().toString()
                    +"','"+observacion.getText().toString()+"')";
            tabla.execSQL(SQL);
            Toast.makeText(this, "Listo", Toast.LENGTH_LONG).show();
            tabla.close();
        }catch (Exception e){
            Toast.makeText( this,"Error: No se pudo",Toast.LENGTH_LONG);
        }
    }
    private void consultar(){
        final EditText id = new EditText( this );
        id.setInputType( InputType.TYPE_CLASS_NUMBER );
        id.setHint( "Valor mayor a 0" );
        String msj = "Ingrese el ID";

        AlertDialog.Builder alerta = new AlertDialog.Builder( this );
        alerta.setTitle( "Consultar" ).setMessage( msj )
                .setView( id )
                .setPositiveButton( "Buscar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"Listo: " + id,Toast.LENGTH_LONG).show();
                        return;
                    }
                } )
                .setNegativeButton( "Cancelar",null ).show();

    }
    private void modificar(){

    }
    private void eliminar(){

    }
}
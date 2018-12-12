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
        consultar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultar();
            }
        } );
        actualizar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizar();
            }
        } );
        eliminar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminar();
            }
        } );
    }
    private void insertar(){
        try{
            SQLiteDatabase bd = base.getWritableDatabase();
            String SQL = "INSERT INTO RECETA VALUES("+id.getText().toString()+ ",'"+nombre.getText().toString()
                    +"','"+ingredientes.getText().toString()+"','"+preparacion.getText().toString()
                    +"','"+observacion.getText().toString()+"')";
            bd.execSQL(SQL);
            Toast.makeText(this, "Listo", Toast.LENGTH_LONG).show();
            bd.close();
        }catch (SQLiteException e){
            Toast.makeText(this,"No se pudo",Toast.LENGTH_LONG).show();
        }
    }
    public void consultar(){
        final String valor;
        final EditText no = new EditText( this );
        no.setInputType( InputType.TYPE_CLASS_NUMBER );
        no.setHint( "Mayor a cero" );

        AlertDialog.Builder alerta = new AlertDialog.Builder( this );
        alerta.setTitle( "Consultar" ).setMessage( "Ingrese el ID" )
                .setView( no )
                .setPositiveButton( "Buscar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try{
                            SQLiteDatabase bd = base.getReadableDatabase();
                            String SQL = "SELECT *FROM RECETA WHERE Id="+no.getText().toString();
                            Cursor resultado = bd.rawQuery(SQL,null);
                            if (resultado.moveToFirst())
                            {
                                id.setText(resultado.getString(0));
                                nombre.setText(resultado.getString(1));
                                ingredientes.setText(resultado.getString(2));
                                preparacion.setText(resultado.getString(3));
                                observacion.setText(resultado.getString(4));
                            }
                            else {
                                msj( "No existe un propietario con dicho ID" );
                            }
                            bd.close();
                        }catch (SQLiteException e){
                            msj( "Ingrese un valor" );
                        }
                        return;
                    }
                } )
                .setNegativeButton( "Cancelar",null ).show();
    }
    public  void actualizar(){
        try{
            SQLiteDatabase bd = base.getWritableDatabase();
            String SQL = "UPDATE RECETA SET NOMBRE='"+nombre.getText().toString()
                    +"',INGREDIENTES='"+ingredientes.getText().toString()+"',PREPARACION='"+preparacion.getText().toString()
                    +"',OBSERVACIONES='"+observacion.getText().toString() +"' WHERE Id="+id.getText().toString();
            bd.execSQL(SQL);
            Toast.makeText(this, "Listo", Toast.LENGTH_LONG).show();
            bd.close();
        }catch (SQLiteException e){
            Toast.makeText(this,"No se encontro el registro",Toast.LENGTH_LONG).show();
        }
    }
    public void eliminar(){
        try{
            SQLiteDatabase bd = base.getWritableDatabase();
            String SQL = "DELETE FROM RECETA WHERE Id="+id.getText().toString();
            bd.execSQL(SQL);
            Toast.makeText(this, "Listo", Toast.LENGTH_LONG).show();
            bd.close();
            id.setText("");
            nombre.setText("");
            ingredientes.setText("");
            preparacion.setText("");
            observacion.setText("");
        }catch (SQLiteException e){
            Toast.makeText(this,"No se encontro el registro",Toast.LENGTH_LONG).show();
        }

    }
    public void msj(String m){
        Toast.makeText(this, m, Toast.LENGTH_LONG).show();
    }
}

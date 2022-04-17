package com.example.crudfirebase_kel3

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

@Suppress("UNREACHABLE_CODE", "NAME_SHADOWING")
class Adapter(val mCtx: Context, val layoutResId: Int, val list: List<Users> )
    : ArrayAdapter<Users>(mCtx,layoutResId,list){

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(layoutResId,null)

        val textJudul = view.findViewById<TextView>(R.id.textJudul)
        val textNote = view.findViewById<TextView>(R.id.textNote)

        val textUpdate = view.findViewById<TextView>(R.id.TextUpdate)
        val textDelete = view.findViewById<TextView>(R.id.TextDelete)

        val user = list[position]

        textJudul.text = user.judul
        textNote.text = user.note

        textUpdate.setOnClickListener {
            showUpdateDialog(user)
        }
        textDelete.setOnClickListener {
            Deleteinfo(user)
        }

        return view
    }

    private fun Deleteinfo(user: Users) {
        val progressDialog = ProgressDialog(context,
            R.style.Theme_MaterialComponents_Light_Dialog)
        progressDialog.isIndeterminate = true
        progressDialog.setMessage("Deleting...")
        progressDialog.show()
        val mydatabase = FirebaseDatabase.getInstance().getReference("USERS")
        mydatabase.child(user.id).removeValue()
        Toast.makeText(mCtx,"Deleted!!",Toast.LENGTH_SHORT).show()
        val intent = Intent(context, show::class.java)
        context.startActivity(intent)
    }

    private fun showUpdateDialog(user: Users) {
        val builder = AlertDialog.Builder(mCtx)

        builder.setTitle("Update")

        val inflater = LayoutInflater.from(mCtx)

        val view = inflater.inflate(R.layout.update, null)

        val textJudul = view.findViewById<EditText>(R.id.inputJudul)
        val textNote = view.findViewById<EditText>(R.id.inputNote)

        textJudul.setText(user.judul)
        textNote.setText(user.note)

        builder.setView(view)

        builder.setPositiveButton("Update") { dialog, which ->

            val dbUsers = FirebaseDatabase.getInstance().getReference("USERS")

            val judul = textJudul.text.toString().trim()

            val note = textNote.text.toString().trim()

            if (judul.isEmpty()){
                textJudul.error = "please enter name"
                textJudul.requestFocus()
                return@setPositiveButton
            }

            if (note.isEmpty()){
                textNote.error = "please enter status"
                textNote.requestFocus()
                return@setPositiveButton
            }

            val user = Users(user.id,judul,note)

            dbUsers.child(user.id).setValue(user).addOnCompleteListener {
                Toast.makeText(mCtx,"Updated",Toast.LENGTH_SHORT).show()
            }

        }

        builder.setNegativeButton("No") { dialog, which ->

        }

        val alert = builder.create()
        alert.show()

    }

}



//class Adapter(val mCtx: Context, val layoutResId: Int, val list: List<Users> )
//    : ArrayAdapter<Users>(mCtx,layoutResId,list){
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
//        val view: View = layoutInflater.inflate(layoutResId,null)
//
//        val textJudul = view.findViewById<TextView>(R.id.textJudul)
//        val textNote = view.findViewById<TextView>(R.id.textNote)
//
//        val textUpdate = view.findViewById<TextView>(R.id.TextUpdate)
//        val textDelete = view.findViewById<TextView>(R.id.TextDelete)
//
//        val user = list[position]
//
//        textJudul.text = user.judul
//        textNote.text = user.note
//
//        textUpdate.setOnClickListener {
//            showUpdateDialog(user)
//        }
//        textDelete.setOnClickListener {
//            Deleteinfo(user)
//        }
//
//        return view
//
//    }
//
//    private fun showUpdateDialog(user: Users) {
//        val builder = AlertDialog.Builder(mCtx)
//
//        builder.setTitle("Update")
//
//        val inflater = LayoutInflater.from(mCtx)
//
//        val view = inflater.inflate(R.layout.update, null)
//
//        val textJudul = view.findViewById<EditText>(R.id.inputJudul)
//        val textNote = view.findViewById<EditText>(R.id.inputNote)
//
//        textJudul.setText(user.judul)
//        textNote.setText(user.note)
//
//        builder.setView(view)
//
//        builder.setPositiveButton("Update") { dialog, which ->
//
//            val dbUsers = FirebaseDatabase.getInstance().getReference("USERS")
//
//            val judul = textJudul.text.toString().trim()
//
//            val note = textNote.text.toString().trim()
//
//            if (judul.isEmpty()){
//                textJudul.error = "please enter judul"
//                textJudul.requestFocus()
//                return@setPositiveButton
//            }
//
//            if (note.isEmpty()){
//                textNote.error = "please enter note"
//                textNote.requestFocus()
//                return@setPositiveButton
//            }
//
//
//            val user = Users(user.id,judul,note)
//
//            dbUsers.child(user.id).setValue(user).addOnCompleteListener {
//                Toast.makeText(mCtx, "Updated", Toast.LENGTH_SHORT).show()
//            }
//
//        }
//
//        builder.setNegativeButton("No") { dialog, which ->
//
//        }
//
//        val alert = builder.create()
//        alert.show()
//
//    }


//    private fun Deleteinfo(user: Users) {
//        val progressDialog = ProgressDialog(
//            context,
//            com.google.android.material.R.style.Theme_MaterialComponents_Light_Dialog
//        )
//        progressDialog.isIndeterminate = true
//        progressDialog.setMessage("Deleting...")
//        progressDialog.show()
//        val mydatabase = FirebaseDatabase.getInstance().getReference("USERS")
//        mydatabase.child(user.id).removeValue()
//        Toast.makeText(mCtx, "Deleted!!", Toast.LENGTH_SHORT).show()
//        val intent = Intent(context, show::class.java)
//        context.startActivity(intent)
//    }
//}
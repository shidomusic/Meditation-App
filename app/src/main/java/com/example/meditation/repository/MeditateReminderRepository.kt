package com.example.meditation.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.meditation.model.Reminder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MeditateReminderRepository {
    var meditateReminderLiveData = MutableLiveData<Reminder?>()
    private val db : FirebaseFirestore = FirebaseFirestore.getInstance()
    private val mAuth : FirebaseAuth = FirebaseAuth.getInstance()

    fun getData(){
        val docRef = db.collection("User")
            .document(mAuth.currentUser!!.uid)
            .collection("Reminder")
            .document("Meditate_reminder")

        docRef.get().addOnSuccessListener { document ->
            document?.let{
                if (it.data != null){
                    val meditateReminder : Reminder = Reminder(
                        it.data!!["hour"] as Long,
                        it.data!!["minute"] as Long,
                        it.data!!["state"] as Boolean
                    )
                    meditateReminderLiveData.postValue(meditateReminder)
                }
            }
            Log.d("documentReminder", "$document")
        }
    }

    fun createData(meditateReminder : Reminder){
        FirebaseFirestore.getInstance().collection("User")
            .document(mAuth.currentUser!!.uid)
            .collection("Reminder")
            .document("Meditate_reminder")
            .set(meditateReminder)

        meditateReminderLiveData.postValue(meditateReminder)
    }

    fun changeState(state: Boolean){
        val docRef = db.collection("User")
            .document(mAuth.currentUser!!.uid)
            .collection("Reminder")
            .document("Meditate_reminder")

        docRef.update("state", state)

//        meditateReminderLiveData.postValue(meditateReminder)
    }
}
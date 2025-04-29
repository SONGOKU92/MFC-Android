package com.mfc.formation

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class FormationDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formation_detail)

        // Ajout du bouton de retour
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Détail de la formation"

        // Récupération des vues
        val imageFormation = findViewById<ImageView>(R.id.imageFormation)
        val textTitle = findViewById<TextView>(R.id.textTitle)
        val textDescription = findViewById<TextView>(R.id.textDescription)
        val textDuration = findViewById<TextView>(R.id.textDuration)
        val textPrice = findViewById<TextView>(R.id.textPrice)
        val textLevel = findViewById<TextView>(R.id.textLevel)
        val buttonRegister = findViewById<Button>(R.id.buttonRegister)

        // Récupération des données
        val formation = intent.getSerializableExtra("formation") as? Formation
        
        if (formation != null) {
            // Affichage des données
            imageFormation.setImageResource(formation.iconResource)
            textTitle.text = formation.title
            textDescription.text = formation.description
            textDuration.text = "Durée: ${formation.duration}"
            textPrice.text = "Prix: ${formation.price}"
            textLevel.text = "Niveau requis: ${formation.level}"
            
            // Configuration du bouton d'inscription
            buttonRegister.setOnClickListener {
                Toast.makeText(
                    this, 
                    "Inscription à la formation ${formation.title}", 
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            Toast.makeText(
                this, 
                "Aucune information de formation disponible", 
                Toast.LENGTH_SHORT
            ).show()
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
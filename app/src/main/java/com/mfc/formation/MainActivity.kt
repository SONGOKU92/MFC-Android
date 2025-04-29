package com.mfc.formation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// Modèle de données
data class Formation(
    val title: String,
    val description: String,
    val duration: String,
    val price: String,
    val level: String,
    val iconResource: Int
) : java.io.Serializable

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var formationList: List<Formation>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Titre de l'application
        title = "MFC Formation"

        // Initialisation du RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Création des données
        createFormationData()

        // Configuration de l'adaptateur
        recyclerView.adapter = FormationAdapter(formationList) { formation ->
            // Gestionnaire de clic - ouvre l'activité de détail
            val intent = Intent(this, FormationDetailActivity::class.java)
            intent.putExtra("formation", formation)
            startActivity(intent)
        }
    }

    private fun createFormationData() {
        formationList = listOf(
            Formation(
                "Développement HTML/CSS",
                "Maîtrisez les fondamentaux du développement web et créez des sites internet modernes et responsifs",
                "3 mois",
                "2500 €",
                "Débutant",
                android.R.drawable.ic_menu_edit
            ),
            Formation(
                "Programmation Java",
                "Développez des applications robustes avec Java. Du langage aux frameworks professionnels",
                "6 mois",
                "3500 €",
                "Intermédiaire",
                android.R.drawable.ic_menu_manage
            ),
            Formation(
                "Bureautique",
                "Perfectionnez-vous sur les outils bureautiques essentiels. Word, Excel, PowerPoint",
                "2 mois",
                "1500 €",
                "Tous niveaux",
                android.R.drawable.ic_menu_agenda
            ),
            Formation(
                "Cybersécurité",
                "Protégez les systèmes d'information et anticipez les menaces. Formez-vous aux dernières techniques",
                "4 mois",
                "4000 €",
                "Avancé",
                android.R.drawable.ic_lock_lock
            )
        )
    }

    // Adaptateur interne pour les formations
    inner class FormationAdapter(
        private val formationList: List<Formation>,
        private val onItemClick: (Formation) -> Unit
    ) : RecyclerView.Adapter<FormationAdapter.FormationViewHolder>() {

        inner class FormationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val imageFormation: ImageView = itemView.findViewById(R.id.imageFormation)
            val textTitle: TextView = itemView.findViewById(R.id.textTitle)
            val textDescription: TextView = itemView.findViewById(R.id.textDescription)
            val textDuration: TextView = itemView.findViewById(R.id.textDuration)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FormationViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_formation, parent, false)
            return FormationViewHolder(view)
        }

        override fun onBindViewHolder(holder: FormationViewHolder, position: Int) {
            val formation = formationList[position]
            
            holder.imageFormation.setImageResource(formation.iconResource)
            holder.textTitle.text = formation.title
            holder.textDescription.text = formation.description
            holder.textDuration.text = formation.duration
            
            holder.itemView.setOnClickListener { onItemClick(formation) }
        }

        override fun getItemCount(): Int = formationList.size
    }
}
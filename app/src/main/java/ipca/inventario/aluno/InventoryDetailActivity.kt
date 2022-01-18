package ipca.inventario.aluno

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class InventoryDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory_detail)


        findViewById<TextView>(R.id.textViewNameItem).text = intent.getStringExtra("item_name")
        findViewById<TextView>(R.id.textViewDescriptionItem).text = intent.getStringExtra("item_description")
        findViewById<TextView>(R.id.textViewQtdItem).text = intent.getStringExtra("item_qtd")
    }
}
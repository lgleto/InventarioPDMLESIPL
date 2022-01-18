package ipca.inventario.aluno

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class AddInventoryItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_inventory_item)

        val editTextName = findViewById<EditText>(R.id.editTextName)
        val editTextDescription = findViewById<EditText>(R.id.editTextDescription)
        val edittextQtd = findViewById<EditText>(R.id.edittextQtd)
        val buttonAddItem = findViewById<Button>(R.id.buttonAddItem)

        buttonAddItem.setOnClickListener {
            val intent = Intent()
            intent.putExtra("item_name", editTextName.text.toString())
            intent.putExtra("item_description", editTextDescription.text.toString())
            intent.putExtra("item_qtd", edittextQtd.text.toString())
            setResult(RESULT_OK, intent)
            finish()

        }


    }
}
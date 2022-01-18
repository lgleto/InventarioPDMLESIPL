package ipca.inventario.aluno

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import java.text.FieldPosition

class MainActivity : AppCompatActivity() {

    var inventoryItems = arrayListOf(
        InventoryItem(1,"Mesa", "Tampo com 4 pernas", 12F),
        InventoryItem(2,"Cadeira", "Tampo com 4 pernas e um encosto", 24F),
        InventoryItem(3,"Computdores", "Coisas que dão para programar", 10F),
    )

    lateinit var listViewItems : ListView
    lateinit var inventoryAdapter : InventoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listViewItems = findViewById(R.id.listViewItems)
        inventoryAdapter = InventoryAdapter()
        listViewItems.adapter = inventoryAdapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add -> {
                //val item = InventoryItem(4,"Monitor", "Serve para ver", 12F)
                //inventoryItems.add(item)
                //inventoryAdapter.notifyDataSetChanged()
                val intent = Intent(this, AddInventoryItemActivity::class.java)
                startActivityForResult(intent, 1001)
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1001){
            if (resultCode == Activity.RESULT_OK){

                data?.let {
                    val name = it.getStringExtra("item_name")
                    val description = it.getStringExtra("item_description")
                    val qtd = it.getStringExtra("item_qtd")

                    val item = InventoryItem(4,
                        name,
                        description,
                        qtd?.toFloat())

                    inventoryItems.add(item)
                    inventoryAdapter.notifyDataSetChanged()
                }

            }
        }
    }



    inner class InventoryAdapter : BaseAdapter(){

        override fun getCount(): Int {
            return inventoryItems.size
        }

        override fun getItem(position: Int): Any {
            return inventoryItems[position]
        }

        override fun getItemId(position: Int): Long {
            return 0L
        }

        override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
            var rootView = layoutInflater.inflate(R.layout.row_item,viewGroup,false)
            val textViewName = rootView.findViewById<TextView>(R.id.textViewName)
            val textViewQty = rootView.findViewById<TextView>(R.id.textViewQty)

            val buttonAdd = rootView.findViewById<Button>(R.id.buttonAdd)
            val buttonSubtract = rootView.findViewById<Button>(R.id.buttonSubtract)

            textViewName.text = inventoryItems[position].name
            textViewQty.text = "${inventoryItems[position].quantity}"

            buttonAdd.setOnClickListener {
                inventoryItems[position].quantity = (inventoryItems[position].quantity?:0f) + 1f
                inventoryAdapter.notifyDataSetChanged()
            }
            buttonSubtract.setOnClickListener {
                inventoryItems[position].quantity = (inventoryItems[position].quantity?:0f) - 1f

                if(inventoryItems[position].quantity?:0F <= 0f){
                    inventoryItems.removeAt(position)
                }
                inventoryAdapter.notifyDataSetChanged()

            }

            rootView.setOnClickListener {
                val intent = Intent(this@MainActivity, InventoryDetailActivity::class.java)
                intent.putExtra("item_name",inventoryItems[position].name )
                intent.putExtra("item_description",inventoryItems[position].description )
                intent.putExtra("item_qtd",inventoryItems[position].quantity.toString() )
                startActivity(intent)
            }
            return rootView
        }

    }


}
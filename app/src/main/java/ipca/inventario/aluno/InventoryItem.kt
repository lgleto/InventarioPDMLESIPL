package ipca.inventario.aluno

class InventoryItem {

    var id          : Int?=null
    var name        : String? = null
    var description : String? = null
    var quantity    : Float? = null

    constructor(id: Int?, name: String?, description: String?, quantity: Float?) {
        this.id = id
        this.name = name
        this.description = description
        this.quantity = quantity
    }
}
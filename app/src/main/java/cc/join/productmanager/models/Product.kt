package cc.join.productmanager.models

import java.io.Serializable

data class Product(val name: String, val stock: Int, val price: Float, val description: String): Serializable
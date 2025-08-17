package com.kidboi666.roomdemo

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
class Product {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="productId")
    var id: Int = 0
    @ColumnInfo(name = "productName")
    var productName: String = ""
    @ColumnInfo(name = "quantity")
    var quantity: Int = 0

    constructor()

    constructor(productName: String, quantity: Int) {
        this.productName = productName
        this.quantity = quantity
    }
}
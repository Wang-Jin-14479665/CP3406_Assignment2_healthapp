package com.example.healthapp.remote

import android.util.Log
import com.google.gson.*
import java.lang.reflect.Type

class ItemsAdapter : JsonDeserializer<HealthTipResult> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): HealthTipResult {
        val jsonObject = json.asJsonObject

        // 获取 Items 对象
        val itemsObject = jsonObject.getAsJsonObject("Items")

        // 获取 Items 对象下的 Item 字段
        val itemElement = itemsObject.get("Item")

        Log.d("ItemsAdapter", "真正的 Item 字段: $itemElement")

        val items: List<HealthTipResponseItem> = when {
            itemElement.isJsonArray -> {
                context.deserialize<JsonArray>(itemElement, JsonArray::class.java)
                    .map { element ->
                        context.deserialize(element, HealthTipResponseItem::class.java)
                    }
            }
            itemElement.isJsonObject -> {
                listOf(context.deserialize(itemElement, HealthTipResponseItem::class.java))
            }
            else -> emptyList()
        }

        return HealthTipResult(items)
    }

}

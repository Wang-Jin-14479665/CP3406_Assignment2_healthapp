package com.example.healthapp.model

// 接收 API 返回的单条数据
data class HealthTipDto(
    val Title: String?,           // 注意：可空
    val Description: String?      // 注意：可空
)

// DTO 转换为 Entity
fun HealthTipDto.toEntity(): com.example.healthapp.data.HealthTipEntity {
    return com.example.healthapp.data.HealthTipEntity(
        title = this.Title ?: "暂无标题",
        description = this.Description ?: "暂无描述"
    )
}